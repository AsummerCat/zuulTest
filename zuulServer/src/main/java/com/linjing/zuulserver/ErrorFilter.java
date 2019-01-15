package com.linjing.zuulserver;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

@Component
public class ErrorFilter extends ZuulFilter{

private static Logger logger= LoggerFactory.getLogger(ErrorFilter.class);


    @Override
    public String filterType() {
      return   FilterConstants.ERROR_TYPE;
       // return "error";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        // only forward to errorPath if it hasn't been forwarded to already
        return true;

    }

    @Override
    public Object run() {
        logger.info("进入异常过滤器");
        RequestContext ctx = RequestContext.getCurrentContext();

        System.out.println(ctx.getResponseBody());

        ctx.setResponseBody("出现异常");

        return null;
    }
}
