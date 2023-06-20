package cqgcxy.javaweb.Servlet;

import cqgcxy.javaweb.dao.NewsDao;
import cqgcxy.javaweb.news.News;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@MultipartConfig
@WebServlet(urlPatterns = "/UpdateNew.do")//注解
public class UpdateNewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session =request.getSession();
        request.setCharacterEncoding("UTF-8");
        // 获取请求参数
        String newsId = request.getParameter("news_id");
        NewsDao newsDao = new NewsDao();
        News news = newsDao.findnewId(session,newsId);
        if(news!=null){
            session.setAttribute("newsall",news);
            response.sendRedirect("lesson_07/updateNews.jsp");
        }else {
            System.out.println("未获取到参数，跳转失败");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        // 获取发布新闻页面传来的数据
        int newsId = Integer.parseInt(request.getParameter("newsId"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        int state = Integer.parseInt(request.getParameter("state"));
        String category = request.getParameter("category");
        String content = request.getParameter("content");
        Part cover = request.getPart("cover");
        String hidden_url = request.getParameter("before_url");
        String coverUrl = "";
        if(cover == null || cover.getSize()==0){
            coverUrl = hidden_url;
        }else {
            String savePath = request.getServletContext().getRealPath("/upload");
            System.out.println("文件上传目录"+savePath);
            new File(savePath).mkdirs();
            String filename = cover.getSubmittedFileName();
            System.out.println("文件名"+filename);
            cover.write(savePath+"/"+filename);
            coverUrl = "/upload/"+filename;
        }

        //创建news对象，设置属性值
        News news = new News();
        news.setNewsId(newsId);
        news.setTitle(title);
        news.setAuthor(author);
        news.setState(state);
        news.setCategory(category);
        news.setCoverUrl(coverUrl);
        news.setContent(content);

        NewsDao newsDao = new NewsDao();
        boolean flag = newsDao.updateNew(news);
        if(flag){
            response.sendRedirect(request.getContextPath() + "/NewsListPage.do");
        }else {
            System.out.println("更新数据失败");
        }
    }
}
