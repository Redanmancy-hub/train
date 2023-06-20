package cqgcxy.javaweb.Servlet;

import cqgcxy.javaweb.dao.CommentDao;
import cqgcxy.javaweb.dao.NewsDao;
import cqgcxy.javaweb.news.Comment;
import cqgcxy.javaweb.news.News;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(urlPatterns = "/QueryNew.do")//注解
public class QueryNewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        // 设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");

        // 获取请求参数
        String newsId = request.getParameter("news_id");
        String title = request.getParameter("title");
        String serach = "";
        if(title == null){
            serach = newsId;
        }else {
            serach = title;
        }
        // 从servlet中将业务逻辑比分提取出去，交由JavaBean完成
        NewsDao newsDao = new NewsDao();
        News news = newsDao.findnewId(session,serach);
        CommentDao commentDao = new CommentDao();
        //根据news_id查询评论
        ArrayList<Comment> commentArrayList = commentDao.quryNewsComment(Integer.parseInt(newsId));
        if(commentArrayList != null){
            session.setAttribute("newsIdComments",commentArrayList);
        }else {
            System.out.println("查询评论失败");
        }
        if(news!=null){
            session.setAttribute("newsDetailAll",news);
            response.sendRedirect("lesson_07/newsDetail.jsp");
        }else {
            System.out.println("查询失败");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();

        NewsDao newsDao = new NewsDao();
        List<News> list =  newsDao.queryNewsCondition("");

        if(list!=null){
            session.setAttribute("listNews",list);
            System.out.println(list.size());
            response.sendRedirect(request.getContextPath()+"/lesson_09/newsList.jsp");
        }else {
            System.out.println("查询失败");
        }
    }
}
