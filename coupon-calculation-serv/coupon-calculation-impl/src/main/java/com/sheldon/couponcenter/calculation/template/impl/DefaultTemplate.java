package com.sheldon.couponcenter.calculation.template.impl;


import com.sheldon.couponcenter.calculation.api.beans.ShoppingCart;
import com.sheldon.couponcenter.calculation.template.AbstractRuleTemplate;
import com.sheldon.couponcenter.calculation.template.RuleTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 空实现
 */
@Slf4j
@Component
public class DefaultTemplate extends AbstractRuleTemplate implements RuleTemplate {

    @Override
    public ShoppingCart calculate(ShoppingCart order) {
        // 获取订单总价
        Long orderTotalAmount = getTotalPrice(order.getProducts());

        order.setCost(orderTotalAmount);
        return order;
    }

    @Override
    protected Long calculateNewPrice(Long orderTotalAmount, Long shopTotalAmount, Long quota) {
        return orderTotalAmount;
    }
}
