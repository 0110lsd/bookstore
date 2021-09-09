<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
<%@ include file="/pages/common/head.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">我的订单</span>

		<%@ include file="/pages/common/login_success_menu.jsp"%>
	</div>
	
	<div id="main">
		
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情</td>
			</tr>


			<c:forEach items="${requestScope.page.items}" var="order">
				<tr>
					<td>
<%--						<fmt:formatDate value="${order.createTime}" type="time"></fmt:formatDate>--%>
						${order.createTime}
					</td>
					<td>${order.price}</td>
					<td>${order.status == 0 ? "未发货" : "已发货"}</td>
					<td>感谢您的光顾</td>
				</tr>
			</c:forEach>


		</table>
		
		<%@ include file="/pages/common/page_nav.jsp"%>
	</div>
	
	<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>