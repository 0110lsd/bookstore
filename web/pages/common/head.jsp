<%--
  Created by IntelliJ IDEA.
  User: liusuodong
  Date: 2021/3/17
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String basePath = request.getScheme()
        + "://"
        + request.getServerName()
        + ":"
        + request.getServerPort()
        + request.getContextPath()
        + "/";
%>
<base href="<%=basePath%>">
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
<link rel="stylesheet" href="static/css/style.css">
