<%--
  Created by IntelliJ IDEA.
  User: 86135
  Date: 2023/4/11
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.sql.Driver" %>
<%@ page import="java.sql.*" %>
<%@ page import="cqgcxy.javaweb.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
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

    // 3.获得Statement对象（执行sql的容器）
    Statement stmt = conn.createStatement();
    // 4.写sql语句
    // 注册用户的sql语句
    String sql = "select * from user";
    // 由于sql字符串拼接了很多变量，导致sql字符串极难阅读和检查，所以将其输出到控制台看看最终的拼接效果，便于检查sql语法是否正确
    System.out.println("当前正在执行的sql是：" + sql);
    // 5.执行sql语句
    ResultSet rs=stmt.executeQuery(sql);
    // 6.处理执行结果
    //将一行数据转换成一个User对象，在将User对象添加到集合中
    List<User> list =new ArrayList<>();
    //boolean flag=rs.next();//从0开始，向下移动一行

    //通过next方法循环移动行数据
    while(rs.next()){
      String username=rs.getString("username");//处理字符串类型，通过gerXxx方法获取某个字段的数据
      int userId=rs.getInt("user_id");//处理int类型
      String favor=rs.getString("favor");
      String gender=rs.getString("gender");
      String description = rs.getString("description");
      String role = rs.getString("role");
      int state = rs.getInt("state");
      //转换为User对象
      User user=new User();
      user.setUserId(userId);
      user.setUsername(username);//给Javabean赋值，使用set方法
      user.setGender(gender);
      user.setFavor(favor);
      user.setDescription(description);
      user.setState(state);
      user.setRole(role);

      list.add(user);
    }

    //数据已转换就位,需关联userList.jsp中的代码
    application.setAttribute("userList",list);

    // 7.关闭数据库连接
    conn.close();
  } catch (Exception e) {
    out.println("<br>链接数据库失败或者SQL语句执行失败！原因:" + e.getMessage());
    e.printStackTrace();
  }

  response.sendRedirect("userList.jsp");
%>
