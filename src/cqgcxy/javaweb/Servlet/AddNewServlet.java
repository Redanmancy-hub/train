package cqgcxy.javaweb.Servlet;

import cqgcxy.javaweb.dao.NewsDao;
import cqgcxy.javaweb.news.News;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@MultipartConfig
@WebServlet(urlPatterns = "/AddNew.do")//注解
public class AddNewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        // 获取表单数据
        String title = request.getParameter("title");
        Part cover=request.getPart("cover");
        // 新闻发布功能业务逻辑：1，文件的上传；2，将封面和其他新闻信息存入数据库；3，对客服端作响应
        // 1，文件上传（A，目标文件夹，B，文件名，C，数据库中存的URL是什么）
        // A，目标文件夹策略：1：上传到项目的部署目录；2：上传到项目外的一个固定目录，3：上传到专用文件服务器
        // 上传到当前工程下的upload子目录下
        String savePath = request.getServletContext().getRealPath("/upload");// 从根目录开始的绝对路径
        System.out.println("文件上传目录"+savePath);
        // 创建保存路径的目录树，保障上传前文件夹先存在
        new File(savePath).mkdirs();
        // 文件名的策略:1用上传的文件名（缺点：重名冲突，导致文件被覆盖）；2随机文件名（缺点：无法见名知意）；3根据业务规定指定一个命名算法（缺点：实现复杂）
        String filename = cover.getSubmittedFileName();
        System.out.println("文件名"+filename);
        // 保存文件
        cover.write(savePath+"/"+filename);

        // 数据库中存储的封面url:/upload/..
        String coverUrl = "/upload/"+filename;
        // 将coverUrl和其他新闻的属性一起插入到数据库（调用Dao类中的方法）
        String author = request.getParameter("author");
        String category = request.getParameter("category");
        String content = request.getParameter("content");
        News news = new News();
        NewsDao newsDao = new NewsDao();
        news.setTitle(title);
        news.setAuthor(author);
        news.setCategory(category);
        news.setCoverUrl(coverUrl);
        news.setContent(content);

        boolean flag = newsDao.Addnew(news);
        if(flag){
            response.sendRedirect(request.getContextPath() + "/NewsListPage.do");
        }else {
            System.out.println("发布新闻失败");
        }
        response.getWriter().println("文件上传成功");
    }
}
