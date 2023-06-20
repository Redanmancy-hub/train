package cqgcxy.javaweb.Servlet;

import cqgcxy.javaweb.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * 演示第一个servlet程序
 * */
@WebServlet("/DeleteUser.do")//注解
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        String userId = request.getParameter("user_id");
        System.out.println(userId);

        // 从servlet中将业务逻辑比分提取出去，交由JavaBean完成
        UserDao userDao = new UserDao();
        boolean flag = userDao.deleteById(Integer.parseInt(userId));
        if(flag) {
            //跳转到查询界面刷新数据（返回为一个视图）
            response.sendRedirect(request.getContextPath() + "/UserListPage.do");
        } else {
          // 失败了就返回一个错误提示
          response.getWriter().print("删除失败！");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("post请求触发");
    }
    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }
}
