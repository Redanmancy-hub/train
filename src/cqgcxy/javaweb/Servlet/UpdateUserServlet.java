package cqgcxy.javaweb.Servlet;

import cqgcxy.javaweb.User;
import cqgcxy.javaweb.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/UpdateUser.do")//注解
public class UpdateUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session =request.getSession();
        request.setCharacterEncoding("UTF-8");
        // 获取请求参数
        String userId = request.getParameter("user_id");
        UserDao userDao = new UserDao();
        User user = userDao.findById(Integer.parseInt(userId));
        if(user!=null){
            session.setAttribute("userall",user);
            response.sendRedirect("lesson_07/updateUser.jsp");
        }else {
            System.out.println("未获取到参数，跳转失败");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        // 获取注册页面传来的数据
        int userId = Integer.parseInt(request.getParameter("userId"));
        String username = request.getParameter("username");
        String passwd = request.getParameter("passwd");
        String gender = request.getParameter("gender");
        String role = request.getParameter("role");
        int state = Integer.parseInt(request.getParameter("state"));
        String favors = "";
        try {
            // 获取favor
            String[] favor = request.getParameterValues("hobby");
            favors = String.join(",", favor);
        } catch (NullPointerException n) {
            System.out.println("空");
            n.printStackTrace();
        }
        //创建user对象，设置属性值
        User user = new User();
        user.setUserId(userId);
        user.setUsername(username);
        user.setPasswd(passwd);
        user.setGender(gender);
        user.setFavor(favors);
        user.setRole(role);
        user.setState(state);
        user.setDescription(request.getParameter("description"));
        UserDao userDao = new UserDao();
        boolean flag = userDao.update(user);
        if(flag){
            response.sendRedirect(request.getContextPath() + "/UserListPage.do");
        }else {
            System.out.println("修改失败");
        }
    }
}
