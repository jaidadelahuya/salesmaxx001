<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.salesmaxx.entities.UserRole"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cheque Payments</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" type="text/css" href="/style/jquery-ui.css">
<link rel="stylesheet" href="/style/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/style/main-style.css">
<link rel="stylesheet" type="text/css"
	href="/style/jquery.webui-popover.min.css">
<link rel="stylesheet" type="text/css" href="/style/waitMe.css">
<style type="text/css">
.sublist {
	display: inline;
	margin-right: 2%;
	padding: 1%;
	background-color: #eeeeee;
	color: darkred;
	border: 1px #ddd solid
}

.sublist:hover {
	background-color: #cccccc;
	color: red;
	border: 1px #aaaaaa solid
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
						<h2 class="text-danger">Cheque Payments</h2>
					</div>
					<div class="col-sm-1">
						<img class="img-responsive module-image"
							src="/images/icons/organization.png" alt="Add Organization">
					</div>
				</div>
				<hr style="margin-top: 0" />
				<div class="col-sm-12">
					<ul>
						<c:set var="paymentCategory1" scope="session" value="pending" />
						<c:set var="paymentCategory2" scope="session" value="cleared" />
						<c:set var="paymentCategory3" scope="session" value="overdue" />
						<c:choose>
							<c:when test='${chequePaymentBean.category eq paymentCategory1}'>
								<li class="sublist sublist-active"><a
									href="<c:url value='/sm-admin/cheque-payment?category=pending' />">Pending</a></li>
							</c:when>
							<c:otherwise>
								<li class="sublist"><a
									href="<c:url value='/sm-admin/cheque-payment?category=pending' />">Pending</a></li>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test='${chequePaymentBean.category eq paymentCategory2}'>
								<li class="sublist sublist-active"><a
									href="<c:url value='/sm-admin/cheque-payment?category=cleared' />">Cleared</a></li>
							</c:when>
							<c:otherwise>
								<li class="sublist"><a
									href="<c:url value='/sm-admin/cheque-payment?category=cleared'/> ">Cleared
								</a></li>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test='${chequePaymentBean.category eq paymentCategory3}'>
								<li class="sublist sublist-active"><a
									href="<c:url value='/sm-admin/cheque-payment?category=overdue' />">Overdue</a></li>
							</c:when>
							<c:otherwise>
								<li class="sublist"><a
									href="<c:url value='/sm-admin/cheque-payment?category=overdue' />">Overdue</a></li>
							</c:otherwise>
						</c:choose>

					</ul>
				</div>

				<div class="col-sm-6" style="padding-top: 2%; padding-bottom: 1%;">
					<form action="<c:url value='/sm-admin/cheque-payment' />">
						Show <select name="no-of-entries" onchange="this.form.submit()">
							<c:choose>
								<c:when test="${chequePaymentBean.noOfEntries eq 10}">
									<option selected="selected">10</option>
								</c:when>
								<c:otherwise>
									<option>10</option>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${chequePaymentBean.noOfEntries eq 25}">
									<option selected="selected">25</option>
								</c:when>
								<c:otherwise>
									<option>25</option>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${chequePaymentBean.noOfEntries eq 50}">
									<option selected="selected">50</option>
								</c:when>
								<c:otherwise>
									<option>50</option>
								</c:otherwise>
							</c:choose>
						</select> entries
					</form>
				</div>
				<div class="col-sm-6"
					style="text-align: right; padding-top: 2%; padding-bottom: 1%;">
					<input type="text"><input type="submit" value="Search">
				</div>

				<table class="table table-responsive table-striped">
					<thead>
						<tr>
							<th>Transaction ID</th>
							<th>Issue Date</th>
							<th>Overdue Date</th>
							<th>Customer Name</th>
							<th style="text-align: right">Total Amount</th>
							<th style="text-align: center">View Details</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="x" items="${chequePaymentBean.mpbs}">
							<tr>
								<td style="font-weight: bold"><c:out value='${x.txnRef}' /></td>
								<td><c:out value='${x.issueDate}' /></td>
								<td><c:out value='${x.overdueDate}' /></td>
								<td><c:out value='${x.customerName}' /></td>
								<td style="text-align: right"><c:out value='${x.totalAmount}' /></td>
								<td style="text-align: center"><a class="display-cheque-component"><span class="glyphicon glyphicon-circle-arrow-down"></span></a></td>
							</tr>
							<tr id="${x.txnRef}">

								<td colspan="6" style=""><c:forEach var="y" items="${x.pwbs}">
										<div class="row" style="font-family: calibri; margin-top: 1%;">
											<div class="col-sm-6">
												<p style="margin-bottom: 0px"><c:out value='${y.workshopName}' /> ( <c:out value='${y.workshopCode}' /> )</p>
												<p style="margin-bottom: 0px"><strong>Workshop date:</strong><span style="margin-right: 3%;"> <c:out value='${y.date}' /> </span>  <strong>Location: </strong><c:out value='${y.location}' /></p>
											</div>
											<div class="col-sm-2">
												<strong>No of Delegates:</strong> <c:out value='${y.qty}' />
											</div>
											<div class="col-sm-3">
												<strong>Amount Paid:</strong> <c:out value='${y.totalPrice}' />
											</div>
											<div class="col-sm-1">
												<a href="<c:url value='/sm-admin/clear-manual-payment?txnRef=${x.txnRef}&id=${y.scheduleID}&qty=${y.qty}' />">Clear</a>
											</div>
										</div>
									</c:forEach></td>

							</tr>

						</c:forEach>
					</tbody>
				</table>

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