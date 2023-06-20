package cqgcxy.javaweb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/TestServlet.do")//注解
public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //session对象
        HttpSession session = request.getSession();
        String name = request.getParameter("name");
        // 获取多个属性的值
        String[] hobby = request.getParameterValues("hobby");
        String hobbys = "";
        hobbys = String.join(",",hobby);
        session.setAttribute("name",name);
        session.setAttribute("hobbys",hobbys);
        // 重定向到lesson_10/result.jsp界面
        response.sendRedirect("lesson_10/result.jsp");
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }
}
