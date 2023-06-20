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
import java.util.List;


@WebServlet(urlPatterns = "/QueryUser.do")//注解
public class QueryUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应内容类型
        HttpSession session =request.getSession();
        response.setContentType("text/html;charset=UTF-8");

        // 获取请求参数
        String userId = request.getParameter("user_id");

        // 从servlet中将业务逻辑比分提取出去，交由JavaBean完成
        UserDao userDao = new UserDao();
        User user = userDao.findById(Integer.parseInt(userId));
        if(user!=null){
            session.setAttribute("userall",user);
            response.sendRedirect("lesson_09/SqlLIst.jsp");
        }else {
            System.out.println("查询失败");
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        // 用户列表的查询逻辑：1，获取查询的参数，2.校验参数的合法性；3.调用JavaBean（UserDao）查询查询数据库
        String username= request.getParameter("username");
        String gender = request.getParameter("gender");
        UserDao userDao = new UserDao();
        User condition = new User();
        condition.setUsername(username);
        condition.setGender(gender);
        List<User> list = userDao.queryByCondition(condition);
        request.getSession().setAttribute("list",list);
        response.sendRedirect(request.getContextPath()+"/lesson_09/userList.jsp");
        System.out.println("刷新成功");
        System.out.println(list.size());
    }
}
