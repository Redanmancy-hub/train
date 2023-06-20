package cqgcxy.javaweb.Servlet;

import cqgcxy.javaweb.User;
import cqgcxy.javaweb.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/resetPassword.do")
public class ResetPasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String passwd = request.getParameter("passwd");
        //创建user对象，设置属性值
        User user = new User();
        user.setPasswd(passwd);
        UserDao userDao = new UserDao();
        boolean flag = userDao.resetPassword(user);
        if(flag){
            response.sendRedirect("lesson_07/login.jsp");
            System.out.println("修改密码成功");
        }else {
            System.out.println("修改密码失败");
        }
    }
}
