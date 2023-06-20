package cqgcxy.javaweb.Servlet;

import cqgcxy.javaweb.news.News;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;


@WebServlet(urlPatterns = "/NewsDetail.do")
public class NewsDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        News news = new News();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 利用反射技术实现类的加载
        } catch (Exception e) {
            System.out.println("注册驱动失败！原因:" + e.getMessage());
            e.printStackTrace();
        }
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/xscj", "root", "hu020808");
            try {

                String sql = "SELECT * FROM news WHERE category= ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setObject(1,news.getCategory());
                ResultSet rs = pstmt.executeQuery(sql);
                if (rs.next()) {
                    String title = rs.getString("title");
//                    String category = rs.getString("category");
                    news.setTitle(title);
                     session.setAttribute("titleAll",title);
//                   news.setCategory(category);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("SQL语句执行失败！" + e.getMessage());
            } finally {
                conn.close();
            }
        } catch (Exception e) {
            System.out.println("连接失败！" + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
