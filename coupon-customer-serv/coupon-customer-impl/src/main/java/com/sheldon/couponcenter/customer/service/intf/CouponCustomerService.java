package com.sheldon.couponcenter.customer.service.intf;

import com.sheldon.couponcenter.calculation.api.beans.ShoppingCart;
import com.sheldon.couponcenter.calculation.api.beans.SimulationOrder;
import com.sheldon.couponcenter.calculation.api.beans.SimulationResponse;
import com.sheldon.couponcenter.customer.api.beans.RequestCoupon;
import com.sheldon.couponcenter.customer.api.beans.SearchCoupon;
import com.sheldon.couponcenter.customer.dao.entity.Coupon;
import com.sheldon.couponcenter.template.api.beans.CouponInfo;

import java.util.List;

// 用户对接服务
public interface CouponCustomerService {
    // 领券接口
    Coupon requestCoupon(RequestCoupon request);

    // 核销优惠券
    ShoppingCart placeOrder(ShoppingCart info);

    // 优惠券金额试算
    SimulationResponse simulateOrderPrice(SimulationOrder order);

    void deleteCoupon(Long userId, Long couponId);

    void deleteCouponTemplate(Long templateId);

    // 查询用户优惠券
    List<CouponInfo> findCoupon(SearchCoupon request);
}
