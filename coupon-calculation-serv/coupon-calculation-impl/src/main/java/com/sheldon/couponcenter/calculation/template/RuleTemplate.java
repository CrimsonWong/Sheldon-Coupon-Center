package com.sheldon.couponcenter.calculation.template;

import com.sheldon.couponcenter.calculation.api.beans.ShoppingCart;

public interface RuleTemplate {
    // 计算优惠券
    ShoppingCart calculate(ShoppingCart settlement);
}
