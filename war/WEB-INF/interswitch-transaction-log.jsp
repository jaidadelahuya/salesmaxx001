<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.salesmaxx.entities.UserRole"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Interswitch log</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" type="text/css" href="/style/jquery-ui.css">
<link rel="stylesheet" href="/style/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/style/main-style.css">
<link rel="stylesheet" type="text/css"
	href="/style/jquery.webui-popover.min.css">
<link rel="stylesheet" type="text/css" href="/style/waitMe.css">
<script src="/js/sidebar.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/sidebar.html"%>
	<div id="main">
		<%@ include file="/WEB-INF/nav"%>
		<div class="container">
			<div class="row">
				
				<div class="col-sm-12 form-page">
					<div class="row">
						<div class="col-sm-11">
							<h2 class="text-danger">InterSwitch Transactions</h2>
						</div>
						<div class="col-sm-1">
							<img class="img-responsive module-image"
								src="/images/icons/organization.png" alt="Interswitch">
						</div>
					</div>
					<hr style="margin-top: 0" />
					<c:choose>
						<c:when test='${interswitchQueryStatus eq "error"}'>
							<div class="alert alert-danger">
								<p>We could not get response from Interswitch at this time.</p>
								<p>Try again in 30 secs.</p>
							</div>
						</c:when>
						<c:when test='${interswitchQueryStatus eq "success"}'>
							<div class="alert alert-success">
								<p>${interswitchQueryStatusMesssage}</p>
							</div>
						</c:when>
					</c:choose>

					<c:if test="${fn:length(interswitchTransactionLog) > 0}">
						<strong class="pull-right">Showing <c:out
								value="${sb.pageStart + 1}" /> - <c:out
								value="${sb.pageEnd + 1}" /> out of <c:out
								value="${fn:length(interswitchTransactionLog)}" /></strong>
					</c:if>
					<table class="table table-responsive table-condensed table-striped">
						<thead>
							<tr class="text-danger">
								<th>Date</th>
								<th>Customer ID</th>
								<th>Response Code</th>
								<th>Response Description</th>
								<th style="text-align: right;">Amount (NGN)</th>
								<th>Transaction Ref</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${fn:length(interswitchTransactionLog) > 0}">
								<c:forEach var="item" items="${interswitchTransactionLog}"
									begin="${sb.pageStart}" end="${sb.pageEnd}">
									<tr style="font-family: calibri">
										<td>${item.date}</td>
										<td>${item.customerId}</td>
										<td>${item.responseCode}</td>
										<td>${item.responseDescription}</td>
										<td style="text-align: right;">${item.amount}</td>
										<td>${item.tnxRef}</td>
										<td><a class="btn btn-xs btn-danger"
											href="<c:url value='/sm-admin/query-interswitch?txnref=${item.tnxRef}' />">Query</a></td>
									</tr>
								</c:forEach>
								<tr>
									<td colspan="7" style="text-align: center;">
										<ul class="pagination pagination-sm">
											<c:forEach var="i" begin="1" end="${sb.pagination}">
												<c:choose>
													<c:when test="${sb.currentDisplay eq i}">
														<li class="active"><a
															href="<c:url value='/sm/open/show-next?val=${i}&name=interswitch-log' />">${i}</a></li>
													</c:when>
													<c:otherwise>
														<li><a
															href="<c:url value='/sm/open/show-next?val=${i}&name=interswitch-log' />">${i}</a></li>
													</c:otherwise>
												</c:choose>

											</c:forEach>
										</ul>
									</td>
								</tr>
							</c:if>


						</tbody>
					</table>
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

</body>
</html>