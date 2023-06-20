<%@ page import="java.util.ArrayList" %>
<%@ page import="cqgcxy.javaweb.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="user" class="cqgcxy.javaweb.User"/>
<jsp:setProperty name="user" property="*"/><%--将表单里面的同名参数，批量赋值给user对象--%>

<%--<%--%>
<%--  //将表单的参数全部取出来--%>
<%--  String passwd2=request.getParameter("passwd2");--%>

<%--  //判断能否满足注册的条件--%>
<%--  //两次密码是否相等--%>
<%--  if(!passwd2.equals(user.getPasswd())){--%>
<%--    out.println("两次密码不一致");--%>
<%--    return;--%>
<%--  }--%>

<%--  // 从application对象中，取出已经注册成功的用户数据--%>
<%--  List<User> userList=(List)application.getAttribute("userList");--%>
<%--  if(userList==null){--%>
<%--    userList=new ArrayList<>();//首次使用的时候，初始化一次--%>
<%--  }--%>
<%--  //用户名被占用--%>
<%--    if(userList.contains(user)){--%>
<%--      out.println("用户名被占用");--%>
<%--      return;--%>
<%--    }--%>

<%--  //如果满足注册要求，则存储当前注册用户数据--%>
<%--  //多个用户如何储存？单个用户的属性如何存？--%>
<%--  out.println("注册成功！"+"<br>");--%>
<%--  out.println("用户名为:"+user.getUsername()+"<br>");--%>
<%--  out.println("性别为:"+user.getGender()+"<br>");--%>
<%--  String[] favor = request.getParameterValues("favor");--%>
<%--  String favors=String.join(",",favor);--%>
<%--  out.println("爱好为:"+favors+"<br>");--%>
<%--  user.setFavor(favors);--%>
<%--  out.println("年龄为:"+user.getAge()+"<br>");--%>

<%--  //将当前注册用户添加到集合中，并更新到application中的数据--%>
<%--  userList.add(user);--%>
<%--  application.setAttribute("userList",userList);--%>
<%--  response.sendRedirect("register_success.jsp");--%>
<%--%>--%>
