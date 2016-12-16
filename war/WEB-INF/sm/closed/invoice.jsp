<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html style="height: 100%;">
<head>
<meta charset="ISO-8859-1">
<title>Invoice for transaction : <c:out
		value="${invoice.txnRef}" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="/style/bootstrap.min.css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" type="text/css"
	href="/style/jquery.webui-popover.min.css">
<link rel="stylesheet" type="text/css" href="/style/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="/style/waitMe.css">
<link rel="stylesheet" type="text/css" href="/style/index.css">
<link rel="stylesheet" type="text/css" href="/style/custom.css">
<link rel="stylesheet" type="text/css" href="/style/main-style.css">
<link rel="stylesheet" type="text/css" media="print" href="/style/bootstrap.min.css"> 
<%@ include file="/js/google-analytics"%>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-12" style="margin-top: 2%;">
				<img alt="SalesMaxx" src="/images/salesmaxx-logo.jpg"
					style="height: 3em; width: auto;">
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<h4
					style="font-weight: bold; text-align: center; margin-bottom: 3px"
					class="text-danger">
					Details for Transaction
					<c:out value="${invoice.txnRef}" />
				</h4>
				<div style="text-align: center">
					<a id="print" href="#">Print this page for your record</a>
				</div>
			</div>
		</div>
		<div class="row" style="margin-top: 4%; margin-bottom: 1%;">
			<div class="col-sm-12">
				<strong>Transaction Date:</strong>
				<c:out value="${invoice.date}" />
			</div>
			<div class="col-sm-12">
				<strong>Transaction Ref:</strong>
				<c:out value="${invoice.txnRef}" />
			</div>
			<div class="col-sm-12">
				<strong>Total Amount:</strong>
				NGN <c:out value="${invoice.total}" />
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<div class="col-sm-12"
					style="border: 2px black solid; padding-top: 1%; padding-bottom: 1%;border-bottom: none;">
					<div class="col-sm-8">
						<strong>Items Ordered</strong>
					</div>
					<div class="col-sm-2" style="text-align: right;">
						<strong>No. of Delegates</strong>
					</div>
					<div class="col-sm-2" style="text-align: right;">
						<strong>Price (NGN)</strong>
					</div>
				</div>
				<div class="col-sm-12"
					style="border: 2px black solid; padding-top: 1%; padding-bottom: 1%; border-bottom: 1px black dotted;">
					<c:forEach var="item" items="${invoice.items}">
						<div class="col-md-8">
							<c:out value="${item.name}" />
							(
							<c:out value="${item.workshopCode}" />
							)
						</div>
						<div class="col-sm-2" style="text-align: right;">
							<c:out value="${item.qty}" />
						</div>
						<div class="col-sm-2" style="text-align: right;">
							<c:out value="${item.formattedTotalPrice}" />
						</div>
					</c:forEach>
				</div>
				<div class="col-sm-12"
					style="border: 2px black solid; padding-top: 1%; padding-bottom: 1%;border-top: none;">
					<div class="col-md-8">
						
					</div>
					<div class="col-sm-2" style="text-align: right;">
						<strong>Items Subtotal</strong>
					</div>
					<div class="col-sm-2" style="text-align: right;">
						<c:out value="${invoice.total}" />
					</div>
				</div>
			</div>
		</div>
		<div class="row" style="margin-top: 2%;">
			<div class="col-sm-12" style="text-align: center"> <a href="<c:url value="/sm/open/cancellation-policy" />">Refund and Cancellation Policy</a></div>
		</div>
	</div>
	<script src="/js/jquery-1.11.2.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	<script src="/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="/js/slick.min.js"></script>
	<script src="/js/jquery.webui-popover.min.js"></script>
	<script type="text/javascript" src="/js/waitMe.js"></script>
	<script type="text/javascript" src="/js/modules.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#print").click(function(e){
				e.preventDefault();
				window.print();
			});
			$(".main-menu-item").removeClass("active");
			//$("#workshop-menu").addClass("active");
		});
	</script>
	
</body>
</html>