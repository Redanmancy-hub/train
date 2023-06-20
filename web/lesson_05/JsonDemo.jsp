<%@ page language="java" contentType="application/json;charset=utf-8"
    pageEncoding="UTF-8"%>

<%
	response.setContentType("application/json;charset=utf-8");
	String jsonStr="{'name':'hx'}";
	response.getWriter().println(jsonStr);
%>
