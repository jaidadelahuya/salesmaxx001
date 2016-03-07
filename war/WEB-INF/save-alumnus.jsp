<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.salesmaxx.entities.UserRole"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Alumnus</title>
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
						<h2 class="text-danger">Alumnus</h2>
					</div>
					<div class="col-sm-1">
						<img class="img-responsive module-image"
							src="/images/icons/add-delete-events.png" alt="Add/Delete Event">
					</div>
				</div>
				<hr style="margin-top: 0" />
				<div class="row">
					<form id="save-alumnus-form" method="post"
						action="/sm-admin/save-alumnus">
						<div class="col-md-6 form-div">
							<div class="col-md-12 form-group">
								<c:choose>
									<c:when test='${alumnusSaved}'>
										<div id="msg-div" class="alert alert-success">Alumnus was saved successfully.</div>
									</c:when>
									<c:when test="${empty alumnusSaved }"></c:when>
									<c:otherwise>
										<div id="msg-div" class="alert alert-danger">We could not save the alumnus at this time</div>
									</c:otherwise>
								</c:choose>
								
							</div>
							<div class="row">
								<div class="col-md-2 form-group">
									<input type="text" name="title" class="form-control"
										placeholder="title" />
								</div>

								<div class="col-md-5 form-group">
									<input type="text" name="first-name" class="form-control"
										placeholder="First Name">
								</div>
								<div class="col-md-5 form-group">
									<input type="text" name="last-name" class="form-control"
										placeholder="Last Name">
								</div>
							</div>
							<div class="row">
								<div class="col-md-12 form-group">
									<input type="email" name="email" class="form-control"
										placeholder="Email address" />
								</div>
							</div>
							<div class="row">
								<div class="col-md-12 form-group">
									<input type="email" name="alt-email" class="form-control"
										placeholder="Alternative Email address" />
								</div>
							</div>
							<div class="row">
								<div class="col-md-6 form-group">
									<input type="text" name="phone1" class="form-control"
										placeholder="Phone 1" />
								</div>

								<div class="col-md-6 form-group">
									<input type="text" name="phone2" class="form-control"
										placeholder="Phone 2" />
								</div>
							</div>
							<div class="row">
								<div class="col-md-12 form-group">
									<input type="text" name="company" class="form-control"
										placeholder="Company" />
								</div>
							</div>
							<div class="row">
								<div class="col-md-12 form-group">
									<textarea rows="3" class="form-control" name="programs-attended"
										placeholder="Programs Attended (sepaated by ;)"></textarea>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6 form-group">
									<input type="date" name="birthdate" class="form-control"
										placeholder="Birth Date" />
								</div>

								<div class="col-md-6 form-group">
									<input type="submit" class="btn btn-danger" value="Submit" />
								</div>
							</div>

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
</body>
</html>