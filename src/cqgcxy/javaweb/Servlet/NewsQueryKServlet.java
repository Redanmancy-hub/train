package cqgcxy.javaweb.Servlet;

import cqgcxy.javaweb.dao.NewsDao;
import cqgcxy.javaweb.news.News;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/newsQueryk.do")
public class NewsQueryKServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        NewsDao newsDao = new NewsDao();
        String search = "";
        if(request.getParameter("newscondition")!=null){
            search=request.getParameter("newscondition");
        }
        List<News> list = newsDao.queryNewsCondition(search);
        session.setAttribute("listNews",list);
        response.sendRedirect("lesson_09/newsList.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
