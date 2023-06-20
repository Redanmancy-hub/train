package cqgcxy.javaweb.filter;//package cqgcxy.cqgcxy.javaweb.filter;
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.*;
//import java.io.IOException;
//import java.io.PrintWriter;
//
///*
//* 登录过滤器
//* */
//@WebFilter(urlPatterns = "/user/*") // 拦截用户模块（/user）下的所有功能
//public class LoginFilter extends HttpFilter implements Filter {    // 通过一个类实现一个接口
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        System.out.println("过滤器正在初始化");
//    }
//
//    @Override
//    public void destroy() {
//        System.out.println("过滤器被摧毁");
//    }
//
//    @Override
//    public void doFilter(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        System.out.println("请求第一次被检查");
//        // 放入业务逻辑：登录认证-判断系统里面有没有保存当前用户的凭据
//        HttpSession session =servletRequest.getSession();
//        if(session.getAttribute("username")!=null){
//            // 检查合格，就放行，让它进入后面的流程
//            filterChain.doFilter(servletRequest,servletResponse);
//        }else{
//            // 未登录，就做错误处理
//            servletResponse.setContentType("text/html;charset=UTF-8");
//            PrintWriter out = servletResponse.getWriter();
//            out.println("您未登录");
//        }
//
//        System.out.println("返回的时候再检查一次");
//    }
//}
