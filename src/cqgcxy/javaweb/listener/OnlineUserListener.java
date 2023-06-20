package cqgcxy.javaweb.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/*
* 监听器listener
* */
@WebListener // 注解
public class OnlineUserListener implements HttpSessionListener {
    /*
    * 计数器
    * */
    private static int userCount = -2;
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // 监听session对象的创建：客服端首次访问首个web应用的动态资源创建
        System.out.println("创建了一个新的session,代表一个新的用户（客户端）上线了");
        userCount++;

        HttpSession session = se.getSession();      // session之间是无法共享的，需要等大的作用域application来更新
        System.out.println(session.getId());
        session.getServletContext().setAttribute("userCount",userCount);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // 监听session对象的销毁（两个时机）
            // 1.超时销毁（默认30分钟）
            // 2.主动销毁（安全退出功能）（调用session.invalidate方法）重新写一个servlet
        System.out.println("某个session被销毁，代表一个新的用户（客户端）下线了");
        userCount--;

        HttpSession session = se.getSession();
        System.out.println(session.getId());
        session.getServletContext().setAttribute("userCount",userCount);
    }
}
