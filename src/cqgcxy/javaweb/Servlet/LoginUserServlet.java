package cqgcxy.javaweb.Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(urlPatterns = "/LoginUser.do")//注解
public class LoginUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("get请求被触发");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //session对象
        HttpSession session = request.getSession();

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String username = request.getParameter("username");
        String passwd = request.getParameter("passwd");
        // 注册驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 利用反射技术实现类的加载
        } catch (Exception e) {
            System.out.println("注册驱动失败！原因:" + e.getMessage());
            e.printStackTrace();
        }
        //连接数据库
        String sql1="";
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/work", "root", "hu020808");
            try {
                sql1 = "SELECT * FROM user WHERE username = '"+username+"'";
                PreparedStatement pstmt1 = conn.prepareStatement(sql1);

                ResultSet rs2 = pstmt1.executeQuery();
                //获取密码
                String password1 = "";
                int userId = -1;
                int role = -1;
                int state=-1;
                if (rs2.next()) {
                    userId = rs2.getInt("user_id");
                    password1 = rs2.getString("password");
                    role = rs2.getInt("role");
                    state = rs2.getInt("state");
                }
                if (passwd.equals(password1)) {
                    session.setAttribute("username", username);
                    session.setAttribute("Login_role",role);
                    session.setAttribute("userId",userId);
                    session.setAttribute("state",state);
                    if(state==0){
                        response.sendRedirect("lesson_07/stateLimit.jsp");//过滤状态为禁用的友好界面
                    }else {
                        response.sendRedirect("lesson_07/news/new.jsp");//跳转主页
                    }
                } else {
                    System.out.println("用户名错误！" + "<a href=\" \">请重新登录</a>");
                    response.sendRedirect("lesson_07/login_fail.jsp");//登陆失败页面
                }
                conn.close();
            }catch (Exception e){
                System.out.print("sql执行失败！：" + e.getMessage());
                System.out.print(sql1);
                e.printStackTrace();
            }
        }catch (Exception e) {
            System.out.print("连接数据库失败：" + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
