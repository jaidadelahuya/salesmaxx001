
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.salesmaxx.entities.UserRole" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SalesMaxx Admin</title>
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
			<c:choose>
				<c:when test="${authorized}">
					<%@ include file="/WEB-INF/dashboard"%>
				</c:when>
				<c:otherwise>
					<div class="col-sm-6 col-lg-4 col-centered" style="margin-top: 10%;">
						<div id="login-dialog" class="dialog-div">
							<h4
								style="margin-top: 4%; color: rgb(144, 144, 144); font-family: arial">SalesMaxx
								Admin Login</h4>
							<hr style="margin: 0; margin-bottom: 4%;" />
							<!--<c:if test="${not authorized}">
								<div class="alert alert-danger mobile-font">
									<p>You have been denied access to the Admin</p>
									<p>If you are sure you have priviledge to this portal, re-
										enter your login credentials</p>
									<p>Make sure that it is typed correctly.</p>
								</div>
							</c:if> -->
							<div class="bs-example">
								<div id="err-div">
									<span id="err-msg"></span>
								</div>
							</div>
							<form id="login-form">
								<div class="row">
									<div class="col-md-8 col-md-offset-2">
										<div class="form-group">
											<label for="username">Email:</label> <input type="text"
												class="form-control" autofocus="autofocus" name="username" />
										</div>

										<div class="form-group">
											<label for="password">Password:</label> <input
												type="password" name="password" class="form-control" />
										</div>
										<div class="form-group">
											<input class="btn btn-danger btn-md" id="login-btn"
												type="button" value="Login"> <a
												href="<c:url value='/password-recovery'/>"
												style="margin: 2%; font-size: 9pt">Forgotten your
												password?</a>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</c:otherwise>
			</c:choose>
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
			$("#menu-icon").hide();
			$("#menu-icon").click(function() {
				$("#menu-icon").hide();
				$(".menu").toggle("slide");
			});
			$("#slide-in").click(function() {
				$(".menu").toggle("slide", function () {
					$("#menu-icon").show();
				});
				
			});
			$("#login-btn").click(function() {
				var myForm = $("#login-form");
				myForm.waitMe({
					effect : 'ios',
					color : '#b1010c',
					sizeW : '15',
					sizeH : '15'
				});
				var jqxhr = $.post("/login", myForm.serialize(), function() {

				}, 'text').done(function(data) {
					window.location.assign(data);
				}).fail(function(jqXHR, status, errorThrown) {
					$("#err-div").addClass("alert alert-danger");
					$("#err-msg").html("Invalid Username/Password");
				}).always(function() {
		
					myForm.waitMe('hide');
				});
			});
		});
	</script>
</body>
</html>