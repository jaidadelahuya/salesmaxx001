<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Change Password</title>
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
<%@ include file="/js/google-analytics"%>
</head>
<body class="login-body">

	<div class="container-fluid">
		<div class="row">
			<div class="login-div" style="text-align: center">
				<img alt="" src="/images/salesmaxx-logo.png"
					style="width: 40%; margin: 0 auto;">
			</div>
			<c:if test="${not empty changePasswordError}">
				<div class="login-div alert alert-danger">
					<p>
						<span class="glyphicon glyphicon-exclamation-sign"></span>
						<c:out value="${changePasswordError}" />
					</p>
				</div>
			</c:if>
			<div class="login-div login-input-div" style="margin-top: 1%;">
				<h1>Change Password</h1>
				<p>You can now change your password. Enter a password that will
					not be easily guessed. You can use a mix of lowercase and uppercase
					alphabets, number and special characters to get a strong password</p>
				<form id="change-password-form"
					action='<c:url value="/sm/open/change-password-from-login"/>' method="post">
					<div class="form-group" style="margin: 2%;">
						<label>password</label> <input type="password"
							class="form-control" name="pass1" value="${pass1}" />
					</div>
					<div class="form-group" style="margin: 2%;">
						<label>Re-Type password</label> <input type="password"
							class="form-control" name="pass2" value="${pass2}" />
					</div>
					

					<div class="form-group" style="margin: 2%;">

						<input type="submit" class="form-control btn btn-success"
							style="margin-top: 4%;" value="Change Password" />
					</div>
				</form>
				
				

			</div>
		</div>
	</div>
	
</body>
</html>