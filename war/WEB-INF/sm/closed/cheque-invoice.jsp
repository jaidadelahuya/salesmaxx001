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
<%@ include file="/js/google-analytics"%>
</head>
<body>
	<div class="container" style="margin-bottom: 2%;">
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
									<strong style="color: red">TRANSACTION ID:</strong>
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
				<div class="col-sm-2">Workshop Date</div>
				<div class="col-sm-4">Workshop</div>

				<div class="col-sm-2" style="text-align: right">No. of
					Delegates</div>
				<div class="col-sm-2" style="text-align: right">Unit Price
					(NGN)</div>
				<div class="col-sm-2" style="text-align: right">Total (NGN)</div>
			</div>
			<div class="col-sm-12"
				style="padding-top: 1%; padding-bottom: 1%; border: 1px #ddd solid; border-top: none;">
				<c:forEach var="x" items="${chequeInvoice.items}">
					<div class="col-sm-2">
						<c:out value="${x.date }" />
					</div>
					<div class="col-sm-4">
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
					<div class="col-sm-12" style="text-align: right;">
						<c:out value="${chequeInvoice.subTotal}" />
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12" style="border: 1px #ddd solid; margin-top: 4%">
				<div class="col-sm-12" style="padding-top: 2%;">
					<strong style="color: red;">Please ensure that you
						indicate your transaction ID when making payment for tracking.</strong>
				</div>
				<div class="col-sm-12">
					<h4>Bank Details</h4>
				</div>
				<div class="col-sm-6">
					<p>
						<strong>Bank Name: </strong><span>First Bank Nigeria</span>
					</p>
					<p>
						<strong>Account Name: </strong><span>Profiliant Development
							Resources Limited</span>
					</p>
					<p>
						<strong>Account Number: </strong><span>2009173282</span>
					</p>
					<p>
						<strong>Branch: </strong><span>Lagos (Western House)</span>
					</p>
				</div>
				<div class="col-sm-6"></div>
			</div>
		</div>
		<div class="row">
			<ul style="margin-top: 2%;">
				<li style="display: inline; margin-right: 2%;"><a id="print"
					href="#"><strong>Print</strong></a></li>
				<li style="display: inline; margin-right: 2%;"><a href="#"><strong>Save</strong></a></li>
				<li style="display: inline; margin-right: 2%;"><a
					href="mailto:${user.username}?Subject=Invoice%20for%20SalesMaxx%20workshop&Body=Hi,%0AI%20just%20requested%20this%20invoice%20from%20http://www.SalesMaxx.com.%0AHere%20is%20the%20link%20http://www.salesmaxx.com/sm/open/get-cheque-invoice?admin=admin&txnRef=${chequeInvoice.txnRef}"><strong>Mail</strong></a></li>
				<li style="display: inline; margin-right: 2%;"><a
					href="<c:url value='/sm/closed/profile/pending-orders' />"><strong>Go
							To Purchase History</strong></a></li>
			</ul>

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

		});
	</script>

</body>
</html>