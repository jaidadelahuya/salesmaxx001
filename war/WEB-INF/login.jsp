<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html style="height: 100%;">
<head>
<meta charset="ISO-8859-1">
<title>SalesMaxx Login</title>
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
			<c:choose>
				<c:when test="${not empty loginError}">
					<div class="login-div alert alert-danger">
						<p>
							<span class="glyphicon glyphicon-exclamation-sign"></span>
							<c:out value="${loginError}" />
						</p>
					</div>
				</c:when>
				<c:when test="${not empty loginSuccess}">
					<div class="login-div alert alert-success">
						<p>
							<span class="glyphicon glyphicon-ok"></span>
							<c:out value="${loginSuccess}" />
						</p>
					</div>
				</c:when>
			</c:choose>
			<div class="login-div login-input-div" style="margin-top: 1%;">
				<h1>Sign In</h1>
				<form id="login-form"
					action='<c:url value="/sm/open/non-ajax-login"/>' method="post">
					<div class="form-group" style="margin: 2%;">
						<label>Email or Registration ID</label> <input type="text" value="${loginID}"
							class="form-control" name="username" />
					</div>
					<div class="form-group" style="margin: 2%;">
						<label>Password</label> <a href="<c:url value='/sm/open/i/forgot/password' />"
							class="pull-right">Forgot your password?</a> <input
							type="password" class="form-control" name="password" />
					</div>
					<div class="form-group" style="margin: 2%;">

						<input type="submit" class="form-control btn btn-success"
							style="margin-top: 4%;" value="Login" />
					</div>
				</form>
				<div class="form-group" style="margin: 2%;">
					<strong>Or Login with</strong>  <a href="/sm/open/facebook-signin">Facebook</a> or <a
						href="/sm/open/linkedin-signin">LinkedIn</a>
				</div>
				<div class="form-group" style="margin: 2%; margin-top: 5%;">
					<h5 class="text-muted" style="text-align: center; margin: 0">
						<strong>New to SalesMaxx?</strong>
					</h5>
				</div>
				<div class="form-group" style="margin: 2%;">
					<a class="form-control btn btn-primary" href="<c:url value='/sm/open/account/init/new' />"
						style="margin-top: 4%;" >Create an account</a>
				</div>
				<div class="form-group" style="margin: 2%; font-size: 9pt">
					<p>
						By clicking Sign Up, you agree to our <a href="#">terms and
							conditions </a>and you have read our <a href="#">Use Cookie
							policy</a>
					</p>
				</div>

			</div>
		</div>
		</div>
	
	
</body>
</html>