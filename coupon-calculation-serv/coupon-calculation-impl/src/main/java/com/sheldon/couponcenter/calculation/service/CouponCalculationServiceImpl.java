package com.sheldon.couponcenter.calculation.service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.sheldon.couponcenter.calculation.api.beans.ShoppingCart;
import com.sheldon.couponcenter.calculation.api.beans.SimulationOrder;
import com.sheldon.couponcenter.calculation.api.beans.SimulationResponse;
import com.sheldon.couponcenter.calculation.service.intf.CouponCalculationService;
import com.sheldon.couponcenter.calculation.template.CouponTemplateFactory;
import com.sheldon.couponcenter.calculation.template.RuleTemplate;
import com.sheldon.couponcenter.template.api.beans.CouponInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Service
public class CouponCalculationServiceImpl implements CouponCalculationService {

    @Autowired
    private CouponTemplateFactory couponProcessorFactory;

    /**
     * 优惠券结算
     * 这里通过Factory类决定使用哪个底层Rule，底层规则对上层透明
     * @param cart
     * @return
     */
    @Override
    public ShoppingCart calculateOrderPrice(@RequestBody ShoppingCart cart) {
        log.info("calculate order price: {}", JSON.toJSONString(cart));
        RuleTemplate ruleTemplate = couponProcessorFactory.getTemplate(cart);
        return ruleTemplate.calculate(cart);
    }

    /**
     * 对所有优惠券进行试算，看哪个最赚钱
     * @param order
     * @return
     */
    @Override
    public SimulationResponse simulateOrder(@RequestBody SimulationOrder order) {
        SimulationResponse response = new SimulationResponse();
        Long minOrderPrice = Long.MAX_VALUE;

        // 计算每一个优惠券的订单价格
        for (CouponInfo coupon : order.getCouponInfos()) {
            ShoppingCart cart = new ShoppingCart();
            cart.setProducts(order.getProducts());
            cart.setCouponInfos(Lists.newArrayList(coupon));
            cart = couponProcessorFactory.getTemplate(cart).calculate(cart);

            Long couponId = coupon.getId();
            Long orderPrice = cart.getCost();

            // 设置当前优惠券对应的订单价格
            response.getCouponToOrderPrice().put(couponId, orderPrice);

            // 比较订单价格，设置当前最优优惠券的ID
            if (minOrderPrice > orderPrice) {
                response.setBestCouponId(coupon.getId());
                minOrderPrice = orderPrice;
            }
        }
        return response;
    }
}
