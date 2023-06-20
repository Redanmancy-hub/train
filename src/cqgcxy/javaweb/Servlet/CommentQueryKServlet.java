package cqgcxy.javaweb.Servlet;

import cqgcxy.javaweb.dao.CommentDao;
import cqgcxy.javaweb.news.Comment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/commentQueryk.do")
public class CommentQueryKServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session =request.getSession();
        CommentDao commentDao = new CommentDao();
        String query = "";
        if(request.getParameter("comcondition")!=null){
            query = request.getParameter("comcondition");
        }
        List<Comment> CommentList = commentDao.queryCommentCondition(query);
        session.setAttribute("commentList",CommentList);
        response.sendRedirect("lesson_09/commentList.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
