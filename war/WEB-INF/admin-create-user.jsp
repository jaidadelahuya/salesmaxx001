<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.salesmaxx.entities.UserRole" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create User</title>
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
			<div class="col-sm-10 form-page">
				<div class="row">
					<div class="col-sm-11">
						<h2 class="text-danger">Create New Admin User</h2>
					</div>
					<div class="col-sm-1">
						<img class="img-responsive module-image"
							src="/images/icons/user.png" alt="Add User">
					</div>
				</div>
				<hr style="margin-top: 0" />
				<div class="row">
					<div class="col-md-6 form-div">
						<form id="create-user-form">
							<div id="msg-div"></div>
							<div class="form-group col-md-12">
								<label for="username">Admin Email</label> <input type="text"
									class="form-control" autofocus="autofocus" name="username"
									id="username" />
							</div>
							<div class="form-group col-md-12">
								<label for="password">Password:</label> <input type="password"
									class="form-control" id="password" name="password" />
							</div>

							<div class="form-group col-md-12">
								<label for="role">Role:</label> <select class="form-control"
									name="role" id="role">
									<option value="role">--User Role--</option>
									<option value="ADMIN">Admin</option>
									<option value="COURSE_ADMIN">Course Admin</option>
									<option value="SYSADMIN">System Admin</option>
								</select>
							</div>
							<div class="form-group col-md-12">
								<input type="button" value="Generate Password"
									class="btn btn-small btn-danger form-btn" id="generate" />
								<input type="button" value="Create User"
									class="btn btn-small btn-danger form-btn" id="create-user-btn" />
							</div>
						</form>
					</div>

					<div class="col-md-6"></div>
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
	<script type="text/javascript" src="/js/create-admin-user.js"></script>
</body>
</html>