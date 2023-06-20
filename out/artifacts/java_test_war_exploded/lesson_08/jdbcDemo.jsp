<%@ page import="java.sql.Driver" %>
<%@ page import="java.sql.*" %><%--
    1.注册驱动程序
    2.连接数据库
    3.创建Statement对象
    4.编写SQL
    5.执行SQL
    6.处理结果
    7.关闭连接
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

    // 演示第一个JDBC程序的编写步骤
// 1.注册驱动程序
    try {
        Class.forName("com.mysql.cj.jdbc.Driver"); // 利用反射技术实现类的加载
        out.println("驱动注册成功！可以进行数据库连接了");
    } catch (Exception e) {
        out.println("注册驱动失败！原因:" + e.getMessage());
        e.printStackTrace();
    }


    try {
        // 2.链接数据库
        // jdbc_url格式：协议-ip-端口-数据库名称
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/work", "root", "hu020808");
        out.println("<br>链接数据库成功！可以编写sql语句了");

        // 3.Statement（执行sql的容器）
        Statement stmt = conn.createStatement();
        String sql = "INSERT INTO `user` (`username`, `password`, `gender`, `profession`, `favor`, `description`, `role`) VALUES ('"
                + "admin2" +	"', '"
                + "123456" + "', '"
                + "男" + "', '"
                + "教师" + "', '"
                + "编程" + "', '"
                +"测试jdbc写入数据" + "', "
                + 9 + ")";
//        // 由于sql字符串拼接了很多变量，导致sql字符串极难阅读和检查，所以将其输出到控制台看看最终的拼接效果，便于检查sql语法是否正确
        System.out.println("当前正在执行的sql是：" + sql);
        // 5.执行sql语句
        int rows = stmt.executeUpdate(sql); // insert/update/delete指令用executeUpdate
        // 6.处理执行结果
        if(rows > 0) {
            out.println("<br>sql执行成功！");
        } else {
            out.println("<br>sql执行失败！");
        }
        // 7.关闭数据库连接
        conn.close();
    } catch (Exception e) {
        out.println("<br>链接数据库失败或者SQL语句执行失败！原因:" + e.getMessage());
        e.printStackTrace();
    }
%>