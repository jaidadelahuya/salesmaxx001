<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.salesmaxx.entities.UserRole"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Workshop</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" type="text/css" href="/style/jquery-ui.css">
<link rel="stylesheet" href="/style/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/style/main-style.css">
<link rel="stylesheet" type="text/css"
	href="/style/jquery.webui-popover.min.css">
<link rel="stylesheet" type="text/css" href="/style/waitMe.css">
<style type="text/css">
.temp-div:hover {
	background-color: #f1f1f1
}
.sublist {
	display: inline;
	margin-right: 2%;
	padding: 1%;
	background-color: #eeeeee;
	color: darkred;
	border: 1px #ddd solid;
}

.sublist:hover,.sublist-active {
	background-color: #666666;
	border: 1px #aaaaaa solid
}

.sublist-active a,sublist:hover a {
	color: white;
}

.sort li {
	display: inline;
	margin-right: 2%;
}

.sort-active {
	border-bottom: 1px red solid;
	color: red;
}
</style>
</head>
<body style="padding-top: 50px">
	<%@ include file="/WEB-INF/nav"%>
	<div class="container-fluid">
		<div class="row">
			<%@ include file="/WEB-INF/sidebar"%>
			<div class="col-sm-10 form-page">
				<div class="row">
					<div class="col-sm-11">
						<h2 class="text-danger">Workshops</h2>
					</div>
					<div class="col-sm-1">
						<img class="img-responsive admin-page-img"
							src="/images/icons/add-workshop.png" alt="Add workshop">
					</div>
				</div>

				<hr style="margin-top: 0" />
				<div class="row">
					<div class="col-sm-12" style="margin-bottom: 2%;">
						<ul class="col-sm-6">
							<c:set var="mod1" scope="session" value="new" />
							<c:set var="mod2" scope="session" value="all" />
							<c:set var="mod3" scope="session" value="canceled" />
							<c:choose>
								<c:when test='${workshop.module eq mod1}'>
									<li class="sublist sublist-active"><a
										href="<c:url value='/sm-admin/add-workshop?mod=new' />">New</a></li>
								</c:when>
								<c:otherwise>
									<li class="sublist"><a
										href="<c:url value='/sm-admin/add-workshop?mod=new' />">New</a></li>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test='${workshop.module eq mod2}'>
									<li class="sublist sublist-active"><a
										href="<c:url value='/sm-admin/add-workshop?mod=all&sort=name' />">All</a></li>
								</c:when>
								<c:otherwise>
									<li class="sublist"><a
										href="<c:url value='/sm-admin/add-workshop?mod=all&sort=name'/> ">All
									</a></li>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test='${workshop.module eq mod3}'>
									<li class="sublist sublist-active"><a
										href="<c:url value='/sm-admin/add-workshop?mod=canceled' />">Canceled</a></li>
								</c:when>
								<c:otherwise>
									<li class="sublist"><a
										href="<c:url value='/sm-admin/add-workshop?mod=canceled' />">Canceled</a></li>
								</c:otherwise>
							</c:choose>

						</ul>
						<ul class="col-sm-6 sort" style="text-align: right">
							<c:if test="${workshop.module eq mod2}">
								<c:set var="sort1" scope="session" value="name" />
								<c:set var="sort2" scope="session" value="month" />
								<c:set var="sort3" scope="session" value="city" />
								<li><strong class="text-muted">View By: </strong></li>
								<c:choose>
									<c:when test='${workshop.sort eq sort1}'>
										<li><a class="sort-active"
											href="<c:url value='/sm-admin/add-workshop?mod=all&sort=name'/>">Name</a></li>
									</c:when>
									<c:otherwise>
										<li><a
											href="<c:url value='/sm-admin/add-workshop?mod=all&sort=name'/>">Name</a></li>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test='${workshop.sort eq sort2}'>
										<li><a class="sort-active"
											href="<c:url value='/sm-admin/add-workshop?mod=all&sort=month'/>">Month</a></li>
									</c:when>
									<c:otherwise>
										<li><a
											href="<c:url value='/sm-admin/add-workshop?mod=all&sort=month'/>">Month</a></li>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test='${workshop.sort eq sort3}'>
										<li><a class="sort-active"
											href="<c:url value='/sm-admin/add-workshop?mod=all&sort=city'/>">City</a></li>
									</c:when>
									<c:otherwise>
										<li><a
											href="<c:url value='/sm-admin/add-workshop?mod=all&sort=city'/>">City</a></li>
									</c:otherwise>
								</c:choose>
							</c:if>
						</ul>
					</div>
				</div>
				<div class="row form-div">
					<c:choose>
						<c:when test="${workshop.module eq mod1}">
							<%@ include file="/WEB-INF/add-new-workshop.html"%>
						</c:when>
						<c:when test="${workshop.module eq mod2}">
							<%@ include file="/WEB-INF/show-all-workshops.html"%>
						</c:when>
						<c:when test="${workshop.module eq mod3}">
							<%@ include file="/WEB-INF/canceled-workshops.html"%>
						</c:when>
					</c:choose>

				</div>

			</div>
		</div>
	</div>
	<script src="/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="/js/jquery-ui.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script src="/js/jquery.webui-popover.min.js"></script>
	<script type="text/javascript" src="/js/waitMe.js"></script>
	<script type="text/javascript" src="/js/modules.js"></script>
	<script type="text/javascript" src="/js/validate.js"></script>
	<script type="text/javascript" src="/js/add-workshop.js"></script>
</body>
</html>