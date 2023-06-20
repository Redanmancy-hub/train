package cqgcxy.javaweb.Servlet;
import cqgcxy.javaweb.dao.CommentDao;
import cqgcxy.javaweb.news.Comment;
import cqgcxy.javaweb.news.News;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/AddComment.do")//注解
public class AddCommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        request.setCharacterEncoding("utf-8");
        // 获取表单的参数
        String commentText = request.getParameter("comment_text");

        News news = (News) session.getAttribute("newsDetailAll");
        int userId = (int) session.getAttribute("userId");
        int newsId = news.getNewsId();
        Comment comment = new Comment();
        CommentDao commentDao = new CommentDao();
        comment.setCommentText(commentText);
        comment.setUserId(userId);
        comment.setNewsId(newsId);
        boolean flag = commentDao.AddComment(comment);
        if(flag){
            response.sendRedirect("lesson_07/newsDetail.jsp");
        }else {
            System.out.println("发布评论失败");
        }
    }
}
