package com.obviously20.filter;

import jakarta.servlet.*;

import java.io.IOException;



/*todo
    * 1.Filter的开发步骤?
    定义:定义一个类实现Filter接口(init、doFilter、destroy)
    配置:
    @WebFilter(urlPatterns="/*")
    @ServletComponentScan//注解来开启SpringBoot项目对于Servlet组件的支持
    2.注意事项
    如果过滤器中不执行放行操作，过滤器拦截到请求之后，就不会访问对应的资源。
    放行: chain.doFilter(request,response)
*/

//@WebFilter(urlPatterns = "/*") //todo 配置过滤器要拦截的请求路径（ /* 表示拦截浏览器的所有请求 ）
public class DemoFilter implements Filter {

    //初始化方法, web服务器启动, 创建Filter实例时调用, 只调用一次
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init...");
    }


    //拦截到请求时,调用该方法,可以调用多次
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("doFilter... 拦截请求");

        // 放行请求(Request和Response:请求和响应)
        filterChain.doFilter(servletRequest, servletResponse);
    }


    // 销毁方法, web服务器关闭时调用, 只调用一次
    @Override
    public void destroy() {
        System.out.println("destroy...");
    }
}
