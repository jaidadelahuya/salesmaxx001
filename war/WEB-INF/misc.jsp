<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.salesmaxx.entities.UserRole"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Misc</title>
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
						<h2 class="text-danger">Miscelleneous</h2>
					</div>
					<div class="col-sm-1">
						<img class="img-responsive module-image"
							src="/images/icons/organization.png" alt="Add Organization">
					</div>
				</div>
				<hr style="margin-top: 0" />
				<div class="row">
					<div class="col-sm-4">
						<div class="panel panel-default">
							<div class="panel-heading">
								<span class="panel-title">Add Industry</span>
							</div>
							<div id="form-div-1">
								<div id="msg-div-1" style="margin: 2%;"></div>
								<form id="add-industry-form">
									<div class="form-group" style="padding: 2%;">
										<label>Industry:</label> <select class="form-control"
											name="name">
											<option>Oil & Gas</option>
											<option>Consumer Sales</option>
											<option>Financial Services</option>
											<option>Telecomms</option>
											<option>Manufacturing</option>
											<option>Healthcare</option>
											<option>Professional services</option>
											<option>Other services</option>
										</select>
									</div>
									<hr />

									<div class="form-group" style="padding: 2%;">
										<input type="button" id="add-industry" value="Add Industry"
											class="btn btn-md btn-danger" />
									</div>
								</form>
							</div>
						</div>

					</div>
					<div class="col-sm-4">
						<div class="panel panel-default">
							<div class="panel-heading">
								<span class="panel-title">Add Job Role</span>
							</div>
							<div id="form-div-2">
								<div id="msg-div-2" style="margin: 2%;"></div>
								<form id="add-job-role-form">
									<div class="form-group" style="padding: 2%;">
										<label>Job Role:</label> <select class="form-control"
											name="name">
											<option>Account Director</option>
											<option>Account Executive</option>
											<option>Account Manager</option>
											<option>Advertising Sales Executive</option>
											<option>Area Sales Manager</option>
											<option>Business Development Director</option>
											<option>Business Development Manager</option>
											<option>Business Relationship Manager</option>
											<option>Call Centre Agent</option>
											<option>Car Sales Executive</option>
											<option>Channel Manager</option>
											<option>Corporate Sales Executive</option>
											<option>Customer Service Advisor</option>
											<option>Customer Service Manager</option>
											<option>Field Sales Executive</option>
											<option>Global Accounts Manager</option>
											<option>Industrial Sales/Account Managers</option>
											<option>Inside Sales Account Manager</option>
											<option>Internal Sales Executive</option>
											<option>IT Sales Executive</option>
											<option>Key Account Manager</option>
											<option>Major Account Manager</option>
											<option>Mortgage Adviser</option>
											<option>National Account Manager</option>
											<option>Presales Consultant</option>
											<option>Product Manager</option>
											<option>Regional Sales Manager</option>
											<option>Retail Channel Manager</option>
											<option>Sales & Distribution Manager</option>
											<option>Sales Administrator</option>
											<option>Sales Executive</option>
											<option>Sales Manager</option>
											<option>Senior Account Executive</option>
											<option>Senior Sales Executive</option>
											<option>Telesales Executive</option>
											<option>Territory Sales Manager</option>
										</select>
									</div>
								</form>
								<hr />
								<div class="form-group" style="padding: 2%;">
									<input type="button" id="add-job-role" value="Add Job Role"
										class="btn btn-md btn-danger" />
								</div>
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
		var msgD = $("#msg-div-1");
		var myForm = $("#add-industry-form");
		var formDiv = $("#form-div-1");
		$("#add-industry")
				.click(

						function() {
							formDiv.waitMe({
								effect : 'ios',
								color : '#b1010c',
								sizeW : '15',
								sizeH : '15'
							});
							var jqXHR = $
									.post("/sm-admin/add-industry",
											myForm.serialize())
									.done(
											function() {
												addSuccess(msgD,
														"Industry saved successful");
											})
									.error(
											function() {
												addError(msgD,
														"We could not save the Industry at this time. Try again later");
											}).complete(function() {
										formDiv.waitMe("hide")
									});
						});
		var msgD1 = $("#msg-div-2");
		var myForm1 = $("#add-job-role-form");
		var formDiv1 = $("#form-div-2");

		$("#add-job-role")
				.click(

						function() {
							formDiv1.waitMe({
								effect : 'ios',
								color : '#b1010c',
								sizeW : '15',
								sizeH : '15'
							});
							var jqXHR = $
									.post("/sm-admin/add-job-role",
											myForm1.serialize())
									.done(
											function() {
												addSuccess(msgD1,
														"Job Role saved successful");
											})
									.error(
											function() {
												addError(msgD1,
														"We could not save the Job Role at this time. Try again later");
											}).complete(function() {
										formDiv1.waitMe("hide")
									});
						});
	</script>
</body>
</html>