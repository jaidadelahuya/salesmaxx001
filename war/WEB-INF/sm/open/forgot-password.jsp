<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Forgot password</title>
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
</head>
<body class="login-body">

	<div class="container-fluid">
		<div class="row">
			<div class="login-div" style="text-align: center">
				<img alt="" src="/images/salesmaxx-logo.jpg"
					style="width: 40%; margin: 0 auto;">
			</div>
			<c:if test="${not empty forgotPasswordError}">
				<div class="login-div alert alert-danger">
					<p>
						<span class="glyphicon glyphicon-exclamation-sign"></span>
						<c:out value="${forgotPasswordError}" />
					</p>
				</div>
			</c:if>
			<div class="login-div login-input-div" style="margin-top: 1%;">
				<h1>Forgot your password?</h1>
				<p>Enter your email address or registration ID then click
					continue. A verification code with be sent to your email address.
					Copy it, you will be asked to paste it in the next prompt.</p>
				<form id="forgot-password-form"
					action='<c:url value="/sm/open/forgot-password"/>' method="post">
					<div class="form-group" style="margin: 2%;">
						<label>Email or Registration ID</label> <input type="text"
							class="form-control" name="username" />
					</div>
					<div class="form-group" style="margin: 2%; font-size: 12pt">
						<h5 class="text-danger">Let us know you are not a robot.</h5>
						<span style="padding-right: 5%;"><c:out value="${captha}" /></span>
						<input name="result" type="text" style="width: 3em" /><a
							href="<c:url value="/sm/open/forgot-password-page" />">
							Reload Captha</a>
					</div>

					<div class="form-group" style="margin: 2%;">

						<input type="submit" class="form-control btn btn-success"
							style="margin-top: 4%;" value="Continue" />
					</div>
				</form>
				<div class="form-group" style="margin: 2%;">
					<strong>Or Login with</strong> <a href="#">Facebook</a> or <a
						href="#">LinkedIn</a>
				</div>
				<div class="form-group" style="margin: 2%; margin-top: 5%;">
					<h5 class="text-muted" style="text-align: center; margin: 0">
						<strong>New to SalesMaxx?</strong>
					</h5>
				</div>
				<div class="form-group" style="margin: 2%;">
					<a class="form-control btn btn-primary"
						href="<c:url value='/sm/open/create-an-account' />"
						style="margin-top: 4%;">Create an account</a>
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
	<div style="margin-top: 2%;">
		<%@ include file="/WEB-INF/footer.html"%>
	</div>
</body>
</html>