<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html style="height: 100%;">
<head>
<meta charset="ISO-8859-1">
<title>Sign Up complete</title>
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
<style type="text/css">
.login-div {
	width: 25%;
	margin: 0 auto;
	margin-top: 1%;
	font-family: calibri;
}

@media screen and (max-width: 1200px) {
	.login-div {
		width: 25%;
	}
}

@media screen and (max-width: 992px) {
	.login-div {
		width: 40%;
	}
}

@media screen and (max-width: 767px) {
	.login-div {
		width: 50%;
	}
}

@media screen and (max-width: 480px) {
	.login-div {
		width: 65%;
	}
}

@media screen and (max-width: 320px) {
	.login-div {
		width: 100%;
	}
}
</style>
</head>
<body class="login-body">

	<div class="container-fluid">
		<div class="row">
			<div class="login-div" style="text-align: center">
				<img alt="" src="/images/salesmaxx-logo.jpg"
					style="width: 40%; margin: 0 auto;">
			</div>

			<div class="login-div well" style="margin-top: 4%; background-color: white;">
				<h4 style="text-align: center;">Congratulations!</h4>
				<p>
					<c:out value="${signUpSuccess1}" />
				</p>
				<p>
					<c:out value="${signUpSuccess2}" />
				</p>

				<div class="form-group" style="text-align: center">
					<a class="btn btn-info" href="/sm/open/login-page"> Login </a>
				</div>

			</div>


		</div>
	</div>

</body>
</html>