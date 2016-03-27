<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/WEB-INF/custom-functions.tld" prefix="pn"%>
<%@page import="com.salesmaxx.entities.UserRole"%>
<!DOCTYPE html>
<html style="height: 100%;">
<head>
<meta charset="ISO-8859-1">
<title>Contact Us</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="/style/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="/style/jquery.webui-popover.min.css">
<link rel="stylesheet" type="text/css" href="/style/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="/style/waitMe.css">
<link rel="stylesheet" type="text/css" href="/style/index.css">
<link rel="stylesheet" type="text/css" href="/style/custom.css">
<link rel="stylesheet" type="text/css" href="/style/main-style.css">
<link rel="stylesheet" type="text/css"
	href="/style/bootstrap-rating.css">
<link rel="stylesheet" type="text/css" href="/style/datepicker.css">
<link rel="stylesheet" type="text/css" href="/style/waitMe.css">
<script src="https://apis.google.com/js/platform.js" async defer></script>
<meta name="google-signin-client_id"
	content="1082599418027-i7l89ubpe432n7lfiu9jus3cc0a99vqs.apps.googleusercontent.com">
</head>
<body>
	<%@ include file="/WEB-INF/main-nav"%>
	<%@ include file="/WEB-INF/sm/open/sub-nav"%>
	<div class="container-fluid" style="width: 95%; margin: 0 auto;">
		<div class="row">

			<div class="well col-md-9"
				style="padding: 0; background-color: white;">
				<%@ include file="/WEB-INF/contact-content.html"%>
			</div>
			<div class="col-md-3" style="padding: 0">
				<%@ include file="/WEB-INF/contact-sidebar-right.html"%>
			</div>
		</div>

	</div>
	<div class="container-fluid" style="margin-top: 2%;">
		<div id="footer-div" style="font-family: calibri; font-size: 9pt">
			<div class="row"
				style="padding: 1%; background-color: #d9edf7; border-color: #bce8f1">
				<div class="col-md-2">
					<img alt="SalesMaxx" src="/images/salesmaxx-logo.jpg"
						class="img-responsive"
						style="width: auto; height: 2em; margin: 0 auto; margin-bottom: 2%;">
					<p style="text-align: left">SalesMaxx is the leading provider
						of multi-platform Sales capability development workshops for sales
						people at different levels of proficiency, across multiple sales
						expertise domains, and for many industry verticals.</p>
				</div>
				<div class="col-md-2" style="text-align: center">
					<strong style="font-size: 10pt; font-weight: bolder;">Other
						Sites</strong>
					<div class="col-md-12" style="padding: 2%; margin: 2%;">
						<a href="http://www.profiliant.com/"><img alt="Profiliant"
							src="/images/logoPNG.png" class="img img-responsive"></a>
					</div>
					<div class="col-md-12" style="padding: 2%; margin: 2%;">
						<a href="http://pdr1.servername.com/~bqadmin/"><img
							alt="Best Qualified" src="/images/bq.png"
							class="img img-responsive"></a>
					</div>
					<div class="col-md-12" style="padding: 2%; margin: 2%;">
						<a href="http://www.forsaleseducation.org/"><img alt="FSED"
							src="/images/logo-horizontal.jpg" class="img img-responsive"></a>
					</div>
				</div>
				<div class="col-md-3">
					<div style="margin-bottom: 4%">
						<a href='/calendar'><img class="img img-responsive"
							src="/images/calender.jpg" alt="Calendar" /></a>
					</div>
				</div>
				<div class="col-md-2" style="text-align: center">
					<strong style="font-size: 10pt; font-weight: bold;">Contact
						Us</strong>
					<h5 style="text-align: left">
						<span class="glyphicon glyphicon-phone-alt text-danger"></span>
						+234-702-111-222
					</h5>
					<h5 style="text-align: left">
						<span class="glyphicon glyphicon-envelope text-danger"></span><a
							href="mailto:enquiry@salesmaxx.com"> enquiry@salesmaxx.com</a>
					</h5>
				</div>
				<div class="col-md-3" style="text-align: center;">
					<div style="margin-bottom: 4%">
						<a><img class="img img-responsive"
							src="/images/download-howtobuy.jpg"
							alt="How to buy sales training" /></a>
					</div>
				</div>
			</div>
			<div class="row text-danger"
				style="background-color: #5bc0de; border-color: #46b8da">
				<div class="col-md-6"
					style="text-align: left; font-weight: bold; padding: 1%; padding-bottom: 0px">
					<p>
						&#169; SalesMaxx<br />An imprint of Profiliant
				</div>
				<div class="col-md-offset-4 col-md-2">
					<div class="col-xs-3 col-sm-3"
						style="padding: 0; margin: 0; margin-top: 4%;">
						<a href="https://www.facebook.com/salesmaxxng/"><img
							class="img-responsive facebook" alt=""></a>
					</div>
					<div class="col-xs-3 col-sm-3"
						style="padding: 0; margin: 0; margin-top: 4%;">
						<a href="https://www.linkedin.com/nhome/"><img
							class="img-responsive linkedin" alt=""></a>
					</div>
					<div class="col-xs-3 col-sm-3"
						style="padding: 0; margin: 0; margin-top: 4%;">
						<a href="https://plus.google.com/u/0/"><img
							class="img-responsive googleplus" alt=""></a>
					</div>
					<div class="col-xs-3 col-sm-3"
						style="padding: 0; margin: 0; margin-top: 4%;">
						<a href="https://twitter.com/SalesMaxx"><img
							class="img-responsive twitter" alt=""></a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="/js/jquery-1.11.2.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script src="/js/jquery-ui.min.js"></script>
	<script src="/js/jquery.webui-popover.min.js"></script>
	<script type="text/javascript" src="/js/waitMe.js"></script>
	<script type="text/javascript" src="/js/bootstrap-datepicker.min.js"></script>
	<script type="text/javascript" src="/js/waitMe.js"></script>
	<script type="text/javascript" src="/js/validate.js"></script>
	<script type="text/javascript" src="/js/modules.js"></script>
	<script type="text/javascript" src="/js/bootstrap-rating.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".main-menu-item").removeClass("active");
			$("#contact-menu").addClass("active");
		});
	</script>
	<script type='text/javascript'>
var $zoho= $zoho || {salesiq:{values:{},ready:function(){$zoho.salesiq.floatbutton.visible('hide');}}}; var d=document; s=d.createElement('script'); s.type='text/javascript'; s.defer=true; s.src='https://salesiq.zoho.com/profiliantngr/float.ls?embedname=speaktoaconsultant'; t=d.getElementsByTagName('script')[0]; t.parentNode.insertBefore(s,t);
</script>
</body>
</html>