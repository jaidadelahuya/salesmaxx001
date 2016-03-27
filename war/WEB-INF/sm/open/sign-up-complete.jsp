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
	width: 35%;
	margin: 0 auto;
	margin-top: 1%;
	font-family: calibri;
}

@media screen and (max-width: 1200px) {
	.login-div {
		width: 35%;
	}
}

@media screen and (max-width: 992px) {
	.login-div {
		width: 55%;
	}
}

@media screen and (max-width: 767px) {
	.login-div {
		width: 65%;
	}
}

@media screen and (max-width: 480px) {
	.login-div {
		width: 85%;
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

			<div class="login-div well"
				style="margin-top: 2%; background-color: white; padding-bottom: 0px; padding-top: 0px">
				<h3 class="text-success" style="text-align: center;">Congratulations!</h3>
				<p>Your SalesMaxx account has been created successfully and you
					have recieved 150.00SL Credits.</p>
				<p>
					Login with either your Username, <span
						style="background-color: #666666; color: white; padding: 2px 5px; font-weight: bold; margin-bottom: 2px"><c:out
							value='${signUp.username}' /></span> or Registration ID, <span
						style="background-color: #666666; color: white; padding: 2px; font-weight: bold; margin-right: 1%; margin-bottom: 3px">
						<c:out value='${regid}' />
					</span> and your password.
				</p>
				<div class="row">
					<div class="col-sm-12 no-padding-div"
						style="border-bottom: 1px #eeeeee solid; margin-bottom: 2%;">
						<h4 style="text-align: center; margin-bottom: 2px"
							class="text-danger">Phone Number Verification</h4>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<p>To get free SMS alerts when you blah blah blah blah blah
							blah blah blah blah blah blah blah...</p>
						<p>We will verify that you own this number by sending you an
							SMS containing a code you will be asked to enter in the next
							prompt</p>
						<form action="<c:url value='/sm/open/send-sms'/>">
							<div class="form-group">
								<input type="text" placeholder="Enter your phone number"
									value='${signUp.phone}' name="phone" /> <input type="submit"
									class="btn btn-success" value="Send Code" />
							</div>
						</form>
					</div>
				</div>
				<div class="row" style="padding: 3%;">
					<div class="col-sm-12" style="text-align: center">
						I will verify later, I want to <strong><a
							href="<c:url value='/sm/open/login-page' />">Login</a></strong>
					</div>
				</div>

			</div>


		</div>
	</div>

</body>
</html>