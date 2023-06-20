package cqgcxy.javaweb.Servlet;

import cqgcxy.javaweb.User;
import cqgcxy.javaweb.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/RegisterUser.do")//注解
public class RegisterUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Get请求被触发");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Post请求被触发");
        request.setCharacterEncoding("utf-8");
        // 获取注册页面传来的数据
        String username = request.getParameter("username");
        String passwd = request.getParameter("passwd");
        String passwd2 = request.getParameter("passwd2");
        String gender = request.getParameter("gender");
        String description = request.getParameter("description");
//        String age = request.getParameter("age");
        String favors = "";
        try {
            // 获取favor
            String[] favor = request.getParameterValues("hobby");
            favors = String.join(",", favor);
        } catch (NullPointerException n) {
            n.printStackTrace();
        }
        //创建user对象，设置属性值
        User user = new User();
        user.setUsername(username);
        user.setPasswd(passwd);
        user.setGender(gender);
        user.setFavor(favors);
//        user.setAge(age);
        user.setDescription(request.getParameter("description"));
//        user.setFavor(favors);

        //判断密码是否为空
//        if(user.getPasswd() == null){
//            response.sendRedirect("lesson_07/register_fail.jsp");
//        }else{
//            System.out.println("密码不为空，可以注册了");
//        }
        //判断两次输入的密码是否一致
        if (!user.getPasswd().equals(passwd2)) {
            response.sendRedirect("lesson_07/register_fail.jsp");
            return;
        }
        // 从servlet中将业务逻辑比分提取出去，交由JavaBean完成
        UserDao userDao = new UserDao();
        boolean flag = userDao.register(user);
        if (flag) {
            //跳转到成功页面
            response.sendRedirect("lesson_07/register_success.jsp");//成功界面
        }else {
            response.sendRedirect("lesson_07/register_fail.jsp");
        }
    }
    @Override
    public void destroy() {
        System.out.println("Servlet摧毁");
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        System.out.println("Servlet正在初始化");
        super.init();
    }
}
