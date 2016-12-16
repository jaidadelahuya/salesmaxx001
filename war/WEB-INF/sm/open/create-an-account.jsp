<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html style="height: 100%;">
<head>
<meta charset="ISO-8859-1">
<title>Create an account</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="/style/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="/style/jquery.webui-popover.min.css">
<link rel="stylesheet" type="text/css" href="/style/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="/style/index.css">
<link rel="stylesheet" type="text/css" href="/style/custom.css">
<link rel="stylesheet" type="text/css" href="/style/main-style.css">
<link rel="stylesheet" type="text/css" href="/style/font-awesome.min.css">
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
				<c:when test="${not empty signUpError}">
					<div class="login-div alert alert-danger">
						<p>
							<span class="glyphicon glyphicon-exclamation-sign"></span>
							<c:out value="${signUpError}" />
						</p>
					</div>
				</c:when>

			</c:choose>
			<div class="login-div login-input-div" style="margin-top: 1%;">
				<h1>Create account</h1>
				<form id="create-acount-form"
					action='<c:url value="/sm/open/register-user"/>' method="post">
					<div class="col-sm-6 no-padding-div">
						<div class="form-group" style="margin: 2%;">
							<label>First Name</label> <input type="text" class="form-control"
								name="first-name" value="${signUp.firstName}" />
						</div>
					</div>
					<div class="col-sm-6 no-padding-div">
						<div class="form-group" style="margin: 2%;">
							<label>Last Name</label> <input type="text" class="form-control"
								name="last-name" value="${signUp.lastName}" />
						</div>
					</div>
					<div class="form-group" style="margin: 2%;">
						<label>Email</label> <input type="text" class="form-control"
							name="email" value="${signUp.username}" />
					</div>
					<div class="form-group" style="margin: 2%;">
						<label>Password</label> <input type="password"
							class="form-control" name="pass1"
							placeholder="At least six characters" />
					</div>
					<div class="form-group" style="margin: 2%;">
						<label>Re-type Password</label> <input type="password"
							class="form-control" name="pass2" />
					</div>
					<div class="form-group" style="margin: 2%;">
						<label>Phone Number <small class="text-danger">(For
								fast notifications)</small></label> <input type="text" class="form-control"
							name="phone" value="${signUp.phone}" />
					</div>
					<div class="form-group" style="margin: 2%;">

						<input type="submit" class="form-control btn btn-success"
							style="margin-top: 4%;" value="Create Your SalesMaxx Account" />
					</div>
					<div class="form-group" style="margin: 2%; font-size: 9pt">
						<p>
							By creating an account, you agree to our <a href="#">terms
								and conditions </a>and you have read our <a href="#">Use Cookie
								policy</a>
						</p>
					</div>
				</form>
				<div style="margin-left: -15px; margin-right: -15px; padding: 10px 15px; text-align: center" class="divider form-group">
					<p>
						Already have an account? <a href="/sm/open/i/login">Sign in</a> or login with a social network
					</p>
					<a
						href="/sm/open/facebook-signin"><i class="fa fa-facebook-square fa-2x" aria-hidden="true"></i></a>  <a
						href="/sm/open/linkedin-signin"><i class="fa fa-linkedin-square fa-2x" aria-hidden="true"></i></a>
				</div>

			</div>
		</div>
	</div>

</body>
</html>