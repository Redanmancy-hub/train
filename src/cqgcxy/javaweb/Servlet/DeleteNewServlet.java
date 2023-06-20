package cqgcxy.javaweb.Servlet;

import cqgcxy.javaweb.dao.NewsDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/DeleteNew.do")//注解
public class DeleteNewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String newsId = request.getParameter("news_id");
        // 从servlet中将业务逻辑比分提取出去，交由JavaBean完成
        NewsDao newsDao = new NewsDao();
        boolean flag = newsDao.deleteNew(Integer.parseInt(newsId));
        if(flag) {
            //跳转到查询界面刷新数据（返回为一个视图）
            response.sendRedirect(request.getContextPath() + "/NewsListPage.do");
        } else {
            // 失败了就返回一个错误提示
            response.getWriter().print("删除失败！");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
