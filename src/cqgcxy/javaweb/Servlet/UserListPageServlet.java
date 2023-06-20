package cqgcxy.javaweb.Servlet;

import cqgcxy.javaweb.User;
import cqgcxy.javaweb.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/UserListPage.do")//注解
public class UserListPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取分页参数
        String currPageStr = request.getParameter("currPage");
        int currPage = 1;//第几页
        if(currPageStr != null&&currPageStr != ""){
            currPage = Integer.parseInt(currPageStr);
        }
        String pageSizeStr = request.getParameter("pageSize");
        int pageSize = 2;// 每页显示的条数
        if(pageSizeStr != null && pageSizeStr != ""){
            pageSize = Integer.parseInt(pageSizeStr);
        }

        // 补全前端需要的变量
        // 通过模型层（UserDao）查询user表总记录数
        UserDao userDao =new UserDao();
        int rows = userDao.count();
        int pageNum = (int)Math.ceil(rows * 1.0/pageSize);// 总页数

        request.getSession().setAttribute("pageNum",pageNum);
        request.getSession().setAttribute("currPage",currPage);// 当前页码
        request.getSession().setAttribute("pageSize",pageSize);// 每页显示几条

        List<User> userListPage = userDao.listPage(currPage,pageSize);//当前分页的数据集
        request.getSession().setAttribute("userList",userListPage);
        // 查询后跳转到视图
        response.sendRedirect("lesson_09/userList.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
