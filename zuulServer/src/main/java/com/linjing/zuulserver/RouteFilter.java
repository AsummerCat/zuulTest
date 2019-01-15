package com.linjing.zuulserver;

import com.netflix.zuul.ZuulFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RouteFilter extends ZuulFilter{

private static Logger logger= LoggerFactory.getLogger(RouteFilter.class);


    @Override
    public String filterType() {
        return "route";
    }

    @Override
    public int filterOrder() {
        //filterOrder：过滤器的执行顺序。当请求在一个阶段中存在多个过滤器时，需要根据该方法返回的值来依次执行。
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        logger.info("启动了在路由请求时候被调用的过滤器了");
        return null;
    }
}
