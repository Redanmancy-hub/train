package cqgcxy.javaweb.Servlet;

import cqgcxy.javaweb.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/*
* 演示第一个servlet程序
* */
@WebServlet(urlPatterns = "/Demo.do")//注解
public class DemoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("get请求被触发了");
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("post请求被触发了");
        super.doPost(request, response);
        // 在取参数之前，设置字符集，解决乱码问题
        request.setCharacterEncoding("utf-8");
        //2.和已有的数据做比较
        final String USER = "胡虞强";
        final String PASS = "123456";
        String username = request.getParameter("username");
        String passwd = request.getParameter("passwd");

        ServletContext application = getServletContext();
        List<User> userList = (List) application.getAttribute("userList");
        if (userList == null) { // 首次注册的时候，application没有对象
            // 跳转到fail.jsp
            response.sendRedirect("login_fail.jsp");
            return;
        }

        for (User user : userList) {
            if (user.getUsername().equals(username) && user.getPasswd().equals(passwd)) {
                // 跳转到index.jsp
                // 在跳转之前，将用户登录信息存入session，方便在其他页面提取
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                // 用重定向跳转网页，避免表单被重放。
                response.sendRedirect("index.jsp");
                return; // 登录成功直接结束当前程序，防止继续执行后续代码
            }
        }

        // 跳转到fail.jsp
        response.sendRedirect("login_fail.jsp");
    }

    @Override
    public void destroy() {
        System.out.println("servlet正在销货");
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        System.out.println("servlet正在初始化");

        super.init();
    }

}
