<%@ page import="java.util.ArrayList" %>
<%@ page import="cqgcxy.javaweb.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="user" class="cqgcxy.javaweb.User"/>
<jsp:setProperty name="user" property="*"/><%--将表单里面的同名参数，批量赋值给user对象--%>
<%--
  Created by IntelliJ IDEA.
  mian.java.cqgcxy: 86135
  Date: 2023/3/28
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>

<%
  //将表单的参数全部取出来
//  String username=request.getParameter("username");
//  out.println(user.getUsername());
//  out.println(user.getHobby());//爱好有多个值，用JavaBean赋值只能得到第一个
//
//  user.setHobby(Hobby);//修正爱好属性的数据
//  String passwd=request.getParameter("passwd");
  String password=request.getParameter("password2");
//  request.getParameter("hobby");//值能取一个值
  //正确取某个参数的多个值的方法

  String[] hobbies = request.getParameterValues("hobby");
  user.setHobby(String.join(",", hobbies));

  //判断能否满足注册的条件
  //两次密码是否相等
//  if(!passwd2.equals(user.getPassword())){
//    out.println("密码不一致");
//    return;
//  }
  String msg="";
  if(!user.getPasswd().equals(password)){
    msg="两次密码不一致";
    out.println(msg);
    return;
  }
  // 从application对象中，取出已经注册成功的用户数据
  //用户名被占用
  List<User> userList=(List)application.getAttribute("userList");
  if(userList==null){
    userList=new ArrayList<>();//首次使用的时候，初始化一次
  }

    if(userList.contains(user)){
      msg="用户名被占用";
      out.println(msg);
      return;
    }

  //如果满足注册要求，则存储当前注册用户数据
  //多个用户如何储存？单个用户的属性如何存？

//  mian.java.cqgcxy user=new mian.java.cqgcxy();
//  user.setUsername(username);
//  user.setPassword(passwd);
  //将当前注册用户添加到集合中，并更新到application中的数据

  userList.add(user);
  application.setAttribute("userList",userList);
//  out.println("<br>当前用户数:"+userList.size());
  response.sendRedirect("");
%>
