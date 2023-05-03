package com.sheldon.couponcenter.template;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Slf4j
public class SentinelOriginParser implements RequestOriginParser {

    @Override
    public String parseOrigin(HttpServletRequest httpServletRequest) {
        // 来源标记传递
        log.info("request {}, header={}", httpServletRequest.getParameterMap(), httpServletRequest.getHeaderNames());
        return httpServletRequest.getHeader("SentinelSource");
    }

}
