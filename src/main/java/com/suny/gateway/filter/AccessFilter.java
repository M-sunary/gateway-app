package com.suny.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @version V1.0
 * @Title: AccessFilter
 * @Package com.freemud.gateway.filter
 * @Description: 网关过滤，可在过滤器中写实际业务规则
 *    以下示例中会过拒绝 header中没传递 token的请求
 * @author: yu.sun
 * @date: 2019/4/3 16:31
 */
public class AccessFilter extends ZuulFilter {
    private Logger log = LoggerFactory.getLogger(AccessFilter.class);
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));

        Object accessToken = request.getHeader("token");
        if(accessToken == null) {
            log.warn("access token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            return null;
        }
        log.info("access token :{}",accessToken);
        log.info("logId :{}",request.getHeader("logId"));
        return null;
    }

}
