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
			<div class="col-sm-6">
				<div class="col-sm-12"></div>
				<div class="col-sm-12">
					<p><i>....Performance through proficiency</i></p>
				</div>
				<div class="col-sm-12">
					<p>#3 Adekunle Odunlami Street</p>
					<p>Off Ikorodu Road,</p>
					<p>Ilupeju, Lagos</p>
				</div>
				<div class="col-sm-12">
					<p>Tel: +234 9032703286, 8099901676</p>
				</div>
			</div>
			<div class="col-sm-6">
				<h1>INVOICE</h1>
				<div class="col-sm-12">
					<div class="col-sm-6" style="text-align: right;"><strong>DATE:</strong></div>
					<div class="col-sm-6"><c:out value="${chequeInvoice.date}" /></div>
				</div>
				<div class="col-sm-12">
					<div class="col-sm-6" style="text-align: right;"><strong>TRANSACTION REF:</strong></div>
					<div class="col-sm-6"><c:out value="${chequeInvoice.txnRef}" /></div>
				</div>
			</div>
			
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