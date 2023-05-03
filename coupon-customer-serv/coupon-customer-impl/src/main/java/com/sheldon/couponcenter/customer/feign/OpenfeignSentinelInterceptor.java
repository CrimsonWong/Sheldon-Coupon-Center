package com.sheldon.couponcenter.customer.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenfeignSentinelInterceptor implements RequestInterceptor{

    @Override
    public void apply(RequestTemplate requestTemplate) {
        // 标记流量来源
        requestTemplate.header("SentinelSource", "coupon-customer-serv");
    }

}
