<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Enter Verification Code</title>
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
				<img alt="" src="/images/salesmaxx-logo.jpg"
					style="width: 40%; margin: 0 auto;">
			</div>
			<c:if test="${not empty wrongSMSCode}">
				<div class="login-div alert alert-danger">
					<p>
						<span class="glyphicon glyphicon-exclamation-sign"></span> The
						Code you entered is not correct. Your Phone number has not been
						verified
					</p>
				</div>
			</c:if>
			<c:if test="${not empty codeVerified}">
				<div class="login-div alert alert-success">
					<p>
						<span class="glyphicon glyphicon-exclamation-ok"></span> The Code
						you entered is correct. Congratulations! Your Phone number has
						been verified
					</p>
					<p>Continue to <a href="<c:url value='/sm/open/login-page' />">login</a></p>
				</div>
			</c:if>
			<div class="login-div login-input-div" style="margin-top: 1%;">
				<h1>Enter Verification Code</h1>
				<p>
					An SMS with a verification code has been sent to <i
						class="text-success"><c:out value='${signUp.phone}' /></i>.
				</p>
				<p>Enter the code in the input field below then click verify.</p>
				<form id="verification-code-form"
					action='<c:url value="/sm/open/confirm-sms-code"/>'>
					<div class="form-group" style="margin: 2%;">
						<label>Verification Code</label> <input type="text"
							class="form-control" name="code" />
					</div>

					<c:choose>
						<c:when test="${not empty codeVerified}">
							<div class="form-group" style="margin: 2%;">

								<input type="submit" class="form-control btn btn-success"
									style="margin-top: 4%;" value="Verify" disabled="disabled" />
							</div>
						</c:when>
						<c:otherwise>
							<div class="form-group" style="margin: 2%;">

								<input type="submit" class="form-control btn btn-success"
									style="margin-top: 4%;" value="Verify" />
							</div>
						</c:otherwise>
					</c:choose>

				</form>
				<c:if test="${empty codeVerified}">
					<div class="form-group" style="margin: 2%;">
						Did not get code? <a
							href="<c:url value='/sm/open/send-sms?to=${user.primaryPhone}' />">Resend
							Code</a> or I will verify later, Take me to <a
							href="<c:url value='/sm/open/login-page' />">Login</a>
					</div>
				</c:if>


			</div>
		</div>
	</div>

</body>
</html>