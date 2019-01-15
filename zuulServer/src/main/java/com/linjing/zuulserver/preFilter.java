package com.linjing.zuulserver;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class preFilter  extends ZuulFilter{

private static Logger logger= LoggerFactory.getLogger(preFilter.class);

    //filterType：过滤器的类型，它决定过滤器在请求的哪个生命周期中执行。这里定义为pre，代表会在请求被路由之前执行。
    //filterOrder：过滤器的执行顺序。当请求在一个阶段中存在多个过滤器时，需要根据该方法返回的值来依次执行。
    //shouldFilter：判断该过滤器是否需要被执行。这里我们直接返回了true，因此该过滤器对所有请求都会生效。实际运用中我们可以利用该函数来指定过滤器的有效范围。
    //run：过滤器的具体逻辑。这里我们通过ctx.setSendZuulResponse(false)令zuul过滤该请求，不对其进行路由，然后通过ctx.setResponseStatusCode(401)设置了其返回的错误码，当然我们也可以进一步优化我们的返回，
    //比如，通过ctx.setResponseBody(body)对返回body内容进行编辑等。

    @Override
    public String filterType() {
        //filterType：过滤器的类型，它决定过滤器在请求的哪个生命周期中执行。这里定义为pre，代表会在请求被路由之前执行。
        //return null;
      //  pre：路由之前
        //route：路由之时
        //post： 路由之后
        //error：发送错误调用
        return "pre";
    }

    @Override
    public int filterOrder() {
        //filterOrder：过滤器的执行顺序。当请求在一个阶段中存在多个过滤器时，需要根据该方法返回的值来依次执行。
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //shouldFilter：判断该过滤器是否需要被执行。这里我们直接返回了true，
        //              因此该过滤器对所有请求都会生效。实际运用中我们可以利用该函数来指定过滤器的有效范围。

        return true;

    }

    @Override
    public Object run() {
        //run：过滤器的具体逻辑。这里我们通过ctx.setSendZuulResponse(false)令zuul过滤该请求，不对其进行路由
        //然后通过ctx.setResponseStatusCode(401)设置了其返回的错误码，当然我们也可以进一步优化我们的返回
        //比如，通过ctx.setResponseBody(body)对返回body内容进行编辑等。
        logger.info("启动了路由之前的过滤器了 1");

        RequestContext ctx=RequestContext.getCurrentContext();
        HttpServletRequest request=ctx.getRequest();
        logger.info("send {} request to {}",request.getMethod(),request.getRequestURI().toString());

        //Object accessToken = request.getParameter("accessToken");
        //if(accessToken == null) {
        //    logger.warn("access token is empty");
        //    ctx.setSendZuulResponse(false);
        //    //ctx.setResponseStatusCode(401); //返回错误状态码
        //
        //    ctx.setResponseBody("该页面的accessToken 无法访问");
        //    logger.info(ctx.getResponseBody());
        //    return null;
        //}
        logger.info("access token ok");
        return null;
    }
}
