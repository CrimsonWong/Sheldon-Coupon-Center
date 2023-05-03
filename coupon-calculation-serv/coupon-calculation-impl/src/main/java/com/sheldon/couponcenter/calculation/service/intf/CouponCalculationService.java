package com.sheldon.couponcenter.calculation.service.intf;

import com.sheldon.couponcenter.calculation.api.beans.ShoppingCart;
import com.sheldon.couponcenter.calculation.api.beans.SimulationOrder;
import com.sheldon.couponcenter.calculation.api.beans.SimulationResponse;
import org.springframework.web.bind.annotation.RequestBody;

public interface CouponCalculationService {

    ShoppingCart calculateOrderPrice(@RequestBody ShoppingCart cart);

    SimulationResponse simulateOrder(@RequestBody SimulationOrder cart);

}
