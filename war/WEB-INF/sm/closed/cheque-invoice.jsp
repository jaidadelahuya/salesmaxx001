<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html style="height: 100%;">
<head>
<meta charset="ISO-8859-1">
<title>Invoice for transaction : <c:out
		value="${chequeInvoice.txnRef}" /></title>
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
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-12" style="margin-top: 2%;">
				<div class="col-sm-12">
					<img alt="Profiliant" src="/images/logoPNG.png"
						class="img img-responsive">
				</div>
				<div class="col-sm-12">
					<p>
						<i>....Performance through proficiency</i>
					</p>
				</div>
				<div class="col-sm-12" style="font-family: calibri">
					<p style="margin-bottom: 0px">3, Adekunle Odunlami Street</p>
					<p style="margin-bottom: 0px">Off Ikorodu Road,</p>
					<p style="margin-bottom: 0px">Ilupeju, Lagos</p>
					<p>Tel: +234 9032703286, 8099901676</p>
				</div>

			</div>
			<div class="row">
				<div class="col-sm-12">
					<h2 style="text-align: right; margin-top: 2px; margin-bottom: 2px"
						class="text-primary">INVOICE</h2>
					<div class="col-sm-12"
						style="border: 1px #ddd dotted; padding-top: 2%; padding-bottom: 2%;">
						<div class="col-sm-6">
							<div class="col-sm-12">
								<strong>BILL TO</strong>
							</div>
							<div class="col-sm-12">
								<c:out value="${chequeInvoice.name}" />
							</div>
							<div class="col-sm-12">
								<i><c:out value="${chequeInvoice.email}" /></i>
							</div>
						</div>

						<div class="col-sm-6">
							<div class="col-sm-12">
								<div class="col-sm-6" style="text-align: right;">
									<strong>DATE:</strong>
								</div>
								<div class="col-sm-6">
									<c:out value="${chequeInvoice.date}" />
								</div>
							</div>
							<div class="col-sm-12">
								<div class="col-sm-6" style="text-align: right;">
									<strong>DUE DATE:</strong>
								</div>
								<div class="col-sm-6">
									<c:out value="${chequeInvoice.dueDate}" />
								</div>
							</div>
							<div class="col-sm-12">
								<div class="col-sm-6" style="text-align: right;">
									<strong>TRANSACTION REF:</strong>
								</div>
								<div class="col-sm-6">
									<c:out value="${chequeInvoice.txnRef}" />
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>

		<div class="row">
			<div class="col-sm-12"
				style="background-color: #ddd; font-weight: bold;">
				<div class="col-sm-6">Item</div>
				<div class="col-sm-2" style="text-align: right">Qty</div>
				<div class="col-sm-2" style="text-align: right">Unit Price
					(NGN)</div>
				<div class="col-sm-2" style="text-align: right">Total (NGN)</div>
			</div>
			<div class="col-sm-12"
				style="padding-top: 1%; padding-bottom: 1%; border: 1px #ddd solid; border-top: none;">
				<c:forEach var="x" items="${chequeInvoice.items}">
					<div class="col-sm-6">
						<c:out value="${x.name }" />
					</div>
					<div class="col-sm-2" style="text-align: right">
						<c:out value="${x.qty }" />
					</div>
					<div class="col-sm-2" style="text-align: right">
						<c:out value="${x.formattedPrice }" />
					</div>
					<div class="col-sm-2" style="text-align: right">
						<c:out value="${x.total }" />
					</div>
				</c:forEach>
			</div>
			<div class="col-sm-12" style="padding-right: 0px">
				<div class="col-sm-8"></div>
				<div class="col-sm-2"
					style="background-color: #ddd; font-weight: bold; text-align: right;">Sub
					Total</div>
				<div class="col-sm-2"
					style="border: 1px #ddd solid; border-top: none;">
					<div class="col-sm-12" style="text-align: right;"><c:out value="${chequeInvoice.subTotal}" /></div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12" style="border: 1px #ddd solid; margin-top: 4%">
				<h4>Bank Details</h4>
				<div class="col-sm-6">Bank 1</div>
				<div class="col-sm-6"></div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">ADDITIONAL INFORMATION</div>
		</div>
	</div>
	<script src="/js/jquery-1.11.2.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	<script src="/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="/js/waitMe.js"></script>
	<script type="text/javascript" src="/js/modules.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#print").click(function(e) {
				e.preventDefault();
				window.print();
			});
			$(".main-menu-item").removeClass("active");
			//$("#workshop-menu").addClass("active");
		});
	</script>

</body>
</html>