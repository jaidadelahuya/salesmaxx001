<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Recover your password</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" type="text/css" href="/style/jquery-ui.css">
<link rel="stylesheet" href="/style/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/style/main-style.css">
<link rel="stylesheet" type="text/css"
	href="/style/jquery.webui-popover.min.css">
<link rel="stylesheet" type="text/css" href="/style/waitMe.css">
<style type="text/css">
</style>
</head>
<body style="padding-top :50px">
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-6 col-lg-4 col-centered" style="margin-top: 10%;">

				<div id="password-recovery-dialog" class="dialog-div">
					<c:choose>
						<c:when test="${codeSent}">
							<h4 class="mobile-font-header"
								style="margin-top: 4%; color: rgb(144, 144, 144); font-family: arial">SalesMaxx
								Confirmation Code</h4>
							<hr style="margin: 0; margin-bottom: 4%;" />
							<div class="alert alert-info mobile-font" id="msg-div-1">
								<h4 style="color: black;">Have a confirmation code?</h4>
								<p>Enter the confirmation code you recieved.</p>
								<p>You will be redirected to change your password.</p>
							</div>
							<form id="confirmation-code-form" method="post">
								<div class="row">
									<div class="form-group col-md-9">
										<input type="text" class="form-control mobile-input"
											id="confirmation-code"
											placeholder="Enter your confirmation code" name="code" />
									</div>
									<div class="form-group col-md-3"></div>
								</div>
								<hr style="margin: 0; margin-bottom: 3%; margin-top: 1%;" />
								<div class="row">
									<div class="form-group col-sm-12">
										<input type="button" class="btn btn-info btn-sm mobile-font"
											id="verify-code" value="Verify code"
											style="margin-bottom: 1%" />
									</div>
								</div>
							</form>
						</c:when>
						<c:otherwise>
							<h4 class="mobile-font-header"
								style="margin-top: 4%; color: rgb(144, 144, 144); font-family: arial">SalesMaxx
								Password Recovery</h4>
							<hr style="margin: 0; margin-bottom: 4%;" />

							<div class="alert alert-info mobile-font" id="msg-div">
								<h4 style="color: black;">Forgot your password?</h4>
								<p>Enter your email address.</p>
								<p>A confirmation code will be sent to your email address.</p>
							</div>

							<form id="password-recovery-form" method="post">
								<div class="row">
									<div class="form-group col-md-9">
										<input type="text" class="form-control mobile-input"
											id="username" placeholder="Enter your email address"
											name="username" />
									</div>
									<div class="form-group col-md-3"></div>
								</div>
								<hr style="margin: 0; margin-bottom: 3%; margin-top: 1%;" />
								<div class="row">
									<div class="form-group col-sm-12">
										<input type="button" class="btn btn-info btn-sm mobile-font"
											id="send-code" value="Send confirmation code"
											style="margin-bottom: 1%" />
									</div>
								</div>
							</form>
						</c:otherwise>
					</c:choose>
				</div>

			</div>
		</div>
	</div>

	<script src="/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="/js/jquery-ui.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script src="/js/jquery.webui-popover.min.js"></script>
	<script type="text/javascript" src="/js/waitMe.js"></script>
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$("#send-code")
									.click(
											function() {
												var myForm = $("#password-recovery-form");
												var email = $("#username")
														.val();
												myForm.waitMe({
													effect : 'ios',
													color : '#b1010c',
													sizeW : '15',
													sizeH : '15'
												});
												var jqxhr = $
														.post(
																"/send-email",
																myForm
																		.serialize(),
																function() {

																}, 'text')
														.done(
																function(data) {
																	window.location
																			.assign(data);
																})
														.fail(
																function(jqXHR,
																		status,
																		errorThrown) {
																	$(
																			"#msg-div")
																			.removeClass(
																					"alert-info");
																	$(
																			"#msg-div")
																			.addClass(
																					"alert-danger");
																	$(
																			"#msg-div")
																			.html(
																					"<p>We could not a send password recovery code to "
																							+ email
																							+ ".</p><p>Make sure the email address is valid and it is the email address you used to register then try again.</p>");
																})
														.always(
																function() {

																	myForm
																			.waitMe('hide');
																});
											});

							$("#verify-code")
									.click(
											function() {
												var myForm = $("#confirmation-code-form");
												myForm.waitMe({
													effect : 'ios',
													color : '#b1010c',
													sizeW : '15',
													sizeH : '15'
												});
												var jqxhr = $
														.post(
																"/verify-code",
																myForm
																		.serialize(),
																function() {

																}, 'text')
														.done(
																function(data) {
																	window.location
																			.assign(data);
																	console.log(data);
																})
														.fail(
																function(jqXHR,
																		status,
																		errorThrown) {
																	$(
																			"#msg-div-1")
																			.removeClass(
																					"alert-info");
																	$(
																			"#msg-div-1")
																			.addClass(
																					"alert-danger");
																	$(
																			"#msg-div-1")
																			.html(
																					"<p>The code you entered in not valid</p> ");
																})
														.always(
																function() {

																	myForm
																			.waitMe('hide');
																});
											});

						});
	</script>
</body>
</html>