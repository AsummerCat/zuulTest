package com.linjing.zuulserver;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

@Component
public class PostFilter extends ZuulFilter{

private static Logger logger= LoggerFactory.getLogger(PostFilter.class);


    @Override
    public String filterType() {
        return "post";
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
        logger.info("启动了在在routing和error过滤器之后被调用的过滤器了");
        //RequestContext ctx= RequestContext.getCurrentContext();
        //try{
        //ctx.setResponseBody("post后置数据");
            doSomething();
        //}catch (Exception e){    //过滤器需要有严格的try()catch 进行处理
        //    ctx.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);//错误编码
        //    ctx.set("error.exception", e);//错误对象
        //    ctx.set("error.message", e.getMessage());//错误信息
        //}


        return null;
    }



    private void doSomething() {
        throw new RuntimeException("错误信息...");
    }
}
