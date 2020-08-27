<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<input type="hidden" id="baseUrl"
				value="${ pageContext.request.contextPath }"><a
				class="navbar-brand" href="${pageContext.request.contextPath }/"
				style="margin-top: -9px;"><img
				src="${ pageContext.request.contextPath }/resources/images/logo2.png"></a>
		</div>
		<c:if test="${ user ne null }">
			<ul class="nav navbar-nav navbar-right">
				<li class=""><a
					href="${ pageContext.request.contextPath }/loginApi/logout"
					class="btn-link1 user-panel"><span
						class="glyphicon glyphicon-user"></span> Log Out</a></li>
			</ul>
		</c:if>
	</div>
</nav>
<div style="margin-bottom: 50px;"></div>