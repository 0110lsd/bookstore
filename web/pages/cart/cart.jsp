<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
<%@ include file="/pages/common/head.jsp"%>

	<script>

		$(function () {

			$(".updateCount").change(function () {

				let bookId = $(this).attr("bookId");

				let name = $(this).parent().parent().find("td:first").text();

				let count = this.value;

				if(confirm("Are you sure you want to change the [" + name + "]'s amount to " + count + "?")) {

					location.href = "http://localhost:8080/bookstore/cartServlet?action=updateCount&id=" + bookId + "&count=" + count;
				} else {
					this.value = this.defaultValue;
				}
			});

			$(".clearCart").click(function () {

				return confirm("Are you sure you want to clear the cart?");
			});

			$(".deleteCartItem").click(function () {

				let name = $(this).parent().parent().find("td:first").text();

				return confirm("Are you sure you want to delete the " + name + "?");
			})
		});
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>

			<%@ include file="/pages/common/login_success_menu.jsp"%>>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>

			<c:if test="${empty sessionScope.cart.items}">

				<tr>
					<td colspan="5">The current shopping cart is empty... Do something, man</td>
				</tr>
			</c:if>

			<c:if test="${not empty sessionScope.cart.items}">
				<c:forEach items="${sessionScope.cart.items}" var="entry">
					<tr>
						<td>${entry.value.name}</td>
						<td>
							<input type="text" value="${entry.value.count}" style="width: 80px" bookId="${entry.value.id}" class="updateCount">
						</td>
						<td>${entry.value.price}</td>
						<td>${entry.value.totalPrice}</td>
						<td><a href="cartServlet?action=deleteItem&id=${entry.value.id}" class="deleteCartItem">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		
		<div class="cart_info">
			<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
			<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
			<span class="cart_span"><a href="cartServlet?action=clear" class="clearCart">清空购物车</a></span>
			<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>

		</div>
	
	</div>
	
	<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>