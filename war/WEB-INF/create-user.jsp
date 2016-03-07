<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<style type="text/css">
</style>
</head>
<body style="padding-top:50px">
	<%@ include file="/WEB-INF/nav"%>
	<div class="container-fluid">
		<div class="row" style="margin-top: 7%;">
			<div class="col-sm-6 col-lg-4 col-centered" style="margin-top: 5%;">
				<div class="dialog-div">
					<c:choose>
						<c:when test="${authenticated}">
							<h4
								style="margin-top: 4%; color: rgb(144, 144, 144); font-family: arial">Create
								Admin User</h4>
							<hr style="margin: 0; margin-bottom: 4%;" />
							<div id="msg-div"></div>
							<form id="create-user-form">
								<div class="row">
									<div class="form-group col-md-8">
										<input type="text" class="form-control" autofocus="autofocus"
											placeholder="Admin Email" name="username" id="username"/>
									</div>
									<div class="form-group col-md-4"></div>
								</div>
								<div class="row">
									<div class="form-group col-md-8">
										<input type="password" class="form-control" id="password"
											placeholder="Admin Password" name="password" />
									</div>
									<div class="form-group col-md-4">
										<input type="button" value="Generate"
											class="btn btn-small btn-danger form-btn" id="generate"/>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-md-8">
										<select class="form-control" name="role" id="role">
											<option>--User Role--</option>
											<option value="ADMIN">Admin</option>
											<option value="COURSE_ADMIN">Course Admin</option>
											<option value="SYSADMIN">System Admin</option>
										</select>
									</div>
									<div class="form-group col-md-4">
										<input type="button" value="Create User"
											class="btn btn-small btn-danger form-btn" id="create-user-btn"/>
									</div>
								</div>
							</form>
						</c:when>
						<c:otherwise>
							<h4
								style="margin-top: 4%; color: rgb(144, 144, 144); font-family: arial">Enter
								Developer Password</h4>
							<hr style="margin: 0; margin-bottom: 4%;" />

							<form id="developer-password-form">
								<div class="row">
									<div class="form-group col-md-8">
										<input type="password" class="form-control"
											placeholder="Developer Password" name="password" />
									</div>
									<div class="form-group col-md-4"></div>
								</div>
								<div class="row">
									<div class="col-sm-5 col-md-4 form-group">
										<input type="button" class="btn btn-danger btn-sm pull-left"
											id="send-code" value="Authenticate" style="margin-bottom: 1%" />
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
	<script type="text/javascript" src="/js/modules.js"></script>
	<script type="text/javascript" src="/js/validate.js"></script>
	<script type="text/javascript" src="/js/create-admin-user.js"></script>
</body>
</html>