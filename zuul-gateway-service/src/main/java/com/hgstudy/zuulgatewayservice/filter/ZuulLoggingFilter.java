package com.hgstudy.zuulgatewayservice.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

// Filter : 로그, 인증정보 확인, 변환작업 등등 처리 가능
// ZuulFilter를 사용하기 위해 ZuulFilter 상속
@Slf4j
@Component
public class ZuulLoggingFilter extends ZuulFilter {

    //~어디에서 어떤 요청이 들어왔다는 로그 필터
    @Override
    public Object run() throws ZuulException {
        log.info("********** printing logs :");

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info("********** :"+ request.getRequestURI());

        return null;
    }

    //사전 필터인지 사후 필터인지 정의해주는 것
    //pre 사전필터
    @Override
    public String filterType() {
        return "pre";
    }

    //여러개의 필터가 있을경우 필터 순서
    //하나밖에 없으니 1
    @Override
    public int filterOrder() {
        return 1;
    }

    //필터 사용 여부
    @Override
    public boolean shouldFilter() {
        return true;
    }
}
