
<%@page
	import="com.google.appengine.api.blobstore.BlobstoreServiceFactory"%>
<%@page import="com.google.appengine.api.blobstore.BlobstoreService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.salesmaxx.entities.UserRole" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Admin Profile</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" type="text/css" href="/style/jquery-ui.css">
<link rel="stylesheet" href="/style/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/style/main-style.css">
<link rel="stylesheet" type="text/css"
	href="/style/jquery.webui-popover.min.css">
<link rel="stylesheet" type="text/css" href="/style/waitMe.css">
</head>
<body style="padding-top: 50px">
	<%@ include file="/WEB-INF/nav"%>
	<div class="container-fluid">
		<div class="row">
			<%@ include file="/WEB-INF/sidebar"%>
			<div class="col-md-10 form-page">
				<div class="row">
					<div class="col-sm-11">
						<h2 class="text-danger">Edit Your Profile</h2>
					</div>
					<div class="col-sm-1">
						<img class="img-responsive admin-page-img"
							src="/images/icons/edit-profile.png" alt="Edit profile">
					</div>
				</div>
				<div class="row">
					<div class="col-sm-6">
						<div class="panel panel-default mobile-view-div col-sm-12">
							<h4
								style="margin-top: 4%; color: rgb(144, 144, 144); font-family: arial">
								Edit your personal information</h4>
							<hr />
							<div id="msg-div-1"></div>
							<form id="edit-personal-info" enctype="multipart/form-data"
								method="post">
								<div class="form-group">
									<label for="first-name">First Name:</label> <input type="text"
										id="first-name" name="first-name" class="form-control" value="${user.firstName}" >
								</div>
								<div class="form-group">
									<label for="last-name">Last Name:</label> <input type="text"
										id="last-name" name="last-name" class="form-control" value="${user.lastName}">
								</div>
								<div class="form-group">
									<label for="picture">Add your picture:</label> <input
										type="file" id="picture" name="picture" class="form-control">
								</div>
								<hr />
							<div class="form-group">
								<input id="edit-info" class="btn btn-md btn-danger" type="button"
									value="Save Changes" />
							</div>
							</form>
							
							

						</div>

					</div>
					<div class="col-sm-6">
						<div class="panel panel-default mobile-view-div col-sm-12">
							<h4
								style="margin-top: 4%; color: rgb(144, 144, 144); font-family: arial">Change
								your password</h4>
							<hr />
							<div id="msg-div-2"></div>
							<form id="change-password-form">
								<div class="form-group">
									<label for="old-password">Old Password:</label> <input
										type="password" name="old-password" class="form-control">
								</div>
								<div class="form-group">
									<label for="password1">New Password:</label> <input
										type="password" id="password1" name="password1"
										class="form-control">
								</div>
								<div class="form-group">
									<label for="password2">Re-enter New password:</label> <input
										type="password" id="password2" name="password2"
										class="form-control">
								</div>
							</form>
							<hr />
							<div class="form-group">
								<input id="change-password" class="btn btn-md btn-danger"
									value="Change Password" type="button" />
							</div>
						</div>

					</div>

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
	<script type="text/javascript" src="/js/validate.js"></script>
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$("#change-password")
									.click(
											function() {
												var myForm = $("#change-password-form");
												myForm.waitMe({
													effect : 'ios',
													color : '#b1010c',
													sizeW : '15',
													sizeH : '15'
												});
												var pass1 = $("#password1")
														.val();
												var pass2 = $("#password2")
														.val();
												var msgD = $("#msg-div-2");

												if (pass1 === pass2) {

													var jqxhr = $
															.post(
																	"/sm-admin/change-password",
																	myForm
																			.serialize(),
																	function() {

																	}, 'text')
															.done(
																	function(
																			data) {
																		msgD
																				.removeClass("alert alert-danger");
																		msgD
																				.addClass("alert alert-success");
																		msgD
																				.html("Password changed successfully.");
																	})
															.fail(
																	function(
																			jqXHR,
																			status,
																			errorThrown) {
																		msgD
																				.removeClass("alert alert-success");
																		msgD
																				.addClass("alert alert-danger");
																		msgD
																				.html("The old password you entered is not correct.");
																	})
															.always(
																	function() {
																		myForm
																				.waitMe('hide');
																	});
												} else {
													msgD
															.removeClass("alert alert-success");
													msgD
															.addClass("alert alert-danger");
													msgD
															.html("The passwords do not match");
													myForm.waitMe("hide");
												}
											});
							var myForm = $("#edit-personal-info");
							myForm.on('submit', function(e) {
								var x = $(this).attr('action');
								var msgD = $("#msg-div-1");
								$.ajax({
									url : x,
									type : 'POST',
									dataType : "Json",
									data : new FormData(this),
									processData : false,
									contentType : false,
									success : function(data) {
										addSuccess(msgD,"Your profile has been updated successfully.");
										console.log(data);
										$("#first-name").val(data.firstName);
										$("#last-name").val(data.lastName);
										$("#popover-img").attr("src",data.image);
										clearForm(myForm);
									},
									error : function(jqXHR, status, errorThrown) {
										addError(msgD,"We could not update your profile at this time. Please try again later..");
									}, complete : function() {
										myForm.waitMe("hide");
										clearForm(myForm);
									}
								});
								e.preventDefault();
							});


							$("#edit-info").click(function() {
								var msgD = $("#msg-div-1");
								myForm = $("#edit-personal-info");
								myForm.waitMe({
									effect : 'ios',
									color : '#b1010c',
									sizeW : '15',
									sizeH : '15'
								});
								var cont = validateProfile();
								if(cont) {
									var jqxhr = $.post("/sm-admin/getuploadurl", {"callback":"/sm-admin/save-admin-profile"}).done(function(data) {
										myForm.prop('action', data);
										myForm.submit();
									}).error(function() {
										addError(msgD,"We could not update your profile at this time. Please try again later.");
									});
								}
							});
						});
		
						function validateProfile() {
							var msgD = $("#msg-div-1");
							var input = $("#first-name");
							var ok = required(input);
							if(!ok) {
								addError(msgD,"Enter your first name");
								return false;
							}
							var input = $("#last-name");
							var ok = required(input);
							if(!ok) {
								addError(msgD,"Enter your last name");
								return false;
							}
							return true;
						}
	</script>
</body>
</html>