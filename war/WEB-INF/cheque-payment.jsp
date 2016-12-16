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
						<div class="col-sm-5">
							<h2 class="text-danger">Cheque Payments</h2>
						</div>
						<div class="col-sm-7" style="padding-top: 2%">
							<a class="btn btn-default" disabled><strong>Pending</strong></a>
							<a href="/sm-admin/cheque-payment?category=cleared"
								class="btn btn-primary">Cleared</a> <a
								href="/sm-admin/workshops/canceled" class="btn btn-primary">Overdue</a>
						</div>

					</div>
					<hr style="margin-top: 0; margin-bottom: 2px" />


					<div class="col-sm-12" style="padding-bottom: 1%;">
						<div class="col-sm-12" style="text-align: right;">
							<span id="a-search"
								style="background-color: red; color: white; padding: 2px; cursor: pointer;">Advanced
								Search</span>
						</div>
						<div class="row search-div" style="display: none">
							<div class="col-sm-12">
								<form action="<url value=''/>" method="get">
									<div class="form-group col-sm-3">
										<label for="txn-id">Transaction ID</label> <input
											class="form-control" name="txn-id" id="txn-id">
									</div>
									<div class="form-group col-sm-3">
										<label for="i-date">Issue Date</label> <input
											class="form-control" name="i-date" id="i-date">
									</div>
									<div class="form-group col-sm-3">
										<label for="o-date">OverDue Date</label> <input
											class="form-control" name="o-date" id="o-date">
									</div>
									<div class="form-group col-sm-3">
										<label for="btn"> </label> <input
											class="form-control btn btn-success" type="submit" id="btn">
									</div>
								</form>
							</div>
						</div>

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
									<td style="text-align: right"><c:out
											value='${x.totalAmount}' /></td>
									<td style="text-align: center"><a
										class="display-cheque-component" style="cursor: pointer;"><span
											class="glyphicon glyphicon-circle-arrow-down"></span></a></td>
								</tr>
								<tr class="xxxx" style="display: none;">

									<td colspan="6" style="padding-top: 0px; padding-bottom: 0px"><c:forEach
											var="y" items="${x.pwbs}">
											<div class="row"
												style="font-family: calibri; padding-top: 1%; background-color: #eeeeee">
												<div class="col-sm-6">
													<p style="margin-bottom: 0px">
														<c:out value='${y.workshopName}' />
														(
														<c:out value='${y.workshopCode}' />
														)
													</p>
													<p style="margin-bottom: 0px">
														<strong>Workshop date:</strong><span
															style="margin-right: 3%;"> <c:out
																value='${y.date}' />
														</span> <strong>Location: </strong>
														<c:out value='${y.location}' />
													</p>
												</div>
												<div class="col-sm-2">
													<strong>No of Delegates:</strong>
													<c:out value='${y.qty}' />
												</div>
												<div class="col-sm-3">
													<strong>Amount Paid:</strong>
													<c:out value='${y.totalPrice}' />
												</div>
												<div class="col-sm-1">
													<a
														href="<c:url value='/sm-admin/clear-manual-payment?txnRef=${x.txnRef}&id=${y.scheduleID}&qty=${y.qty}' />">Clear</a>
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
	</div>
	<script src="/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="/js/jquery-ui.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script src="/js/jquery.webui-popover.min.js"></script>
	<script type="text/javascript" src="/js/waitMe.js"></script>
	<script type="text/javascript" src="/js/modules.js"></script>
	<script type="text/javascript" src="/js/validate.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#a-search").click(function(e) {
				$(".search-div").show();
			});
			$(".display-cheque-component").click(function(e) {
				e.preventDefault();
				parent = $(this).parent().parent();
				parent.next(".xxxx").slideToggle();
			});
		});
	</script>

</body>
</html>