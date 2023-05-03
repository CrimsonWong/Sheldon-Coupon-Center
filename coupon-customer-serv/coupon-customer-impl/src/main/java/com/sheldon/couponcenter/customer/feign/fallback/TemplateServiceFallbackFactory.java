package com.sheldon.couponcenter.customer.feign.fallback;

import com.google.common.collect.Maps;
import com.sheldon.couponcenter.customer.feign.TemplateService;
import com.sheldon.couponcenter.template.api.beans.CouponTemplateInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

@Slf4j
@Component
public class TemplateServiceFallbackFactory implements FallbackFactory<TemplateService>{
    @Override
    public TemplateService create(Throwable cause) {

        // 使用这种方法可以捕捉到具体的异常cause
        return new TemplateService() {
            @Override
            public CouponTemplateInfo getTemplate(Long id) {
                log.info("fallback factory method test" + cause);
                return null;
            }

            @Override
            public Map<Long, CouponTemplateInfo> getTemplateInBatch(Collection<Long> ids) {
                log.info("fallback factory method test" + cause);
                return Maps.newHashMap();
            }
        };
    }
}
