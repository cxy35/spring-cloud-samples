package com.cxy35.sample.springcloud.zuul.client.consumer.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author cxy35
 * @Date 2020/5/3 17:16
 */
@Component
public class PermissionFilter extends ZuulFilter {
    /**
     * 过滤器类型，权限判断一般是 pre
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器优先级
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否过滤
     *
     * @return
     */


    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 核心的过滤逻辑写在这里
     *
     * @return 这个方法虽然有返回值，但是这个返回值目前无所谓
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();//获取当前请求
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (!"cxy35".equals(username) || !"123456".equals(password)) {
            //如果请求条件不满足的话，直接从这里给出响应
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.addZuulResponseHeader("content-type", "text/html;charset=utf-8");
            ctx.setResponseBody("非法访问");
        }
        return null;
    }
}
