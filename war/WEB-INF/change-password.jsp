<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Change your password</title>
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
<%@ include file="/js/google-analytics"%>
</head>
<body style="padding: 50px">
	<%@ include file="/WEB-INF/nav"%>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-10 col-md-4 col-centered" style="margin-top: 10%;">
				<div id="confirmation-dialog" class="dialog-div">
					<h4 class="mobile-font-header"
						style="margin-top: 4%; color: rgb(144, 144, 144); font-family: arial">Change your
						Password</h4>
					<hr style="margin: 0; margin-bottom: 4%;" />
					<div id="msg-div">
					</div>
					<form action="" id="password-reset-form">
						<div class="row">
							<div class="form-group col-md-8">
								<input type="password"
									class="form-control mobile-input"
									placeholder="Enter password" name="password1" />
							</div>
							<div class="form-group col-md-4"></div>
						</div>
						<div class="row">
							<div class="form-group col-md-8">
								<input type="password"
									class="form-control  mobile-input"
									placeholder="Re-enter password" name="password2" />
							</div>
							<div class="form-group col-md-4"></div>
						</div>
						<hr style="margin: 0; margin-bottom: 3%; margin-top: 1%;" />
						<div class="row">
							<div class="col-sm-12 form-group">
								<input type="button" class="btn btn-info btn-sm "
									id="reset-password" value="Change Password" />
							</div>

						</div>
					</form>
				</div>

			</div>

		</div>
	</div>
	<script src="/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="/js/jquery-ui.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script src="/js/jquery.webui-popover.min.js"></script>
	<script type="text/javascript" src="/js/waitMe.js"></script>
	<script type="text/javascript" src="/js/modules.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#reset-password").click(function() {
				var myForm = $("#password-reset-form");
				myForm.waitMe({
					effect : 'ios',
					color : '#b1010c',
					sizeW : '15',
					sizeH : '15'
				});
				var pass1 = $("password1").val();
				var pass2 = $("password2").val();
				if(!(pass1===pass2)) {
					$(msg-div).addClass("alert alert-danger");
					$(msg-div).html("The passwords do not match.");
					myForm.waitMe("hide");
				}
				var jqxhr = $.post("/change-password", myForm.serialize(), function() {

				}, 'text').done(function(data) {
					window.location.assign(data);
				}).fail(function(jqXHR, status, errorThrown) {
					$("#err-div").addClass("alert alert-danger");
					$("#err-msg").html("We could not change your password. please try again later");
				}).always(function() {
		
					myForm.waitMe('hide');
				});
			});
		});
	</script>
</body>
</html>