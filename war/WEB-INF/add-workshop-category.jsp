<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.salesmaxx.entities.UserRole"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Workshop Category</title>
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
						<h2 class="text-danger">Add New Workshop Category</h2>
					</div>
					<div class="col-sm-1">
						<img class="img-responsive module-image"
							src="/images/icons/add-workshop-category.png"
							alt="Add workshop category">
					</div>
				</div>
				<hr style="margin-top: 0" />
				<div class="row">
					<form id="add-workshop-category-form" method="post">
						<div class="col-md-6 form-div">
							<div class="col-md-12">
								<div id="msg-div"></div>
							</div>
							<div class="form-group col-md-12">
								<label for="category-name">Workshop Category Name:</label>
								<select id="category-name" name="category-name" class="form-control">
									<option>Bootcamp</option>
									<option>Channel and Distribution Sales</option>
									<option>Negotiation</option>
									<option>Presentation</option>
									<option>Professional Selling Skills</option>
									<option>Relationship Management</option>
									<option>Sales Management</option>
									<option>Technology and Telco Sales</option>
									<option>Training For Every Account Manager</option>
								</select>
							</div>
							<div class="form-group col-md-12">
								<label for="category-image">Upload Image:</label> <input
									type="file" class="form-control mobile-input"
									id="category-image" name="category-image" />
							</div>
							<div class="form-group col-md-12">
								<label for="description">Workshops:</label> <select
									class="form-control" id="workshops" name="workshops"
									multiple="multiple">
									<option value="NG-SSN-101">Situational Sales Negotiation</option>
									<option value="SM-CSP-202">Coaching Sales People For
										Performance</option>
									<option value="RM-CS-201">Consultative Selling</option>
									<option value="PS-DWS-101">Developing A Winning Sales
										Presentation</option>
									<option value="TEAM-DIW-202">Developing & Implementing
										Winning Strategic Account Plans</option>
									<option value="SM-IHT-203">Interviewing, Hiring &
										Onboarding Top Sales Talent</option>
									<option value="CDS-ICS-101">Indirect Channel Sales
										Bootcamp</option>
									<option value="NG-NPT-201">Negotiating Beyond Price &
										Tactical Approaches</option>
									<option value="PS-PSE-201">Presenting To Senior
										Executives</option>
									<option value="PSS-PSB-101">Professional Selling
										Bootcamp</option>
									<option value="PSS-PB-104">Prospecting Bootcamp</option>
									<option value="RM-RM-101">Relationship Marketing</option>
									<option value="CDS-RS-103">Retail Selling</option>
									<option value="PSS-SCM-103">Sales-Call Management</option>
									<option value="SM-SCB-101">Sales Compensation Bootcamp</option>
									<option value="SM-SPM-201">Sales Pipeline Management</option>
									<option value="PSS-SPM-102">Sales Pipeline Management
										Bootcamp</option>
									<option value="CDS-SPB-102">Sales Promotion Bootcamp</option>
									<option value="TEAM-SOM-203">Sales Opportunity
										Management</option>
									<option value="RM-SSE-201">Selling to Senior
										Executives</option>
									<option value="TTS-SSW-201">Solution Selling Workshop</option>
									<option value="TEAM-SAM-201">Strategic Account
										Management</option>
									<option value="TTS-TST-102">Technology Sales Training
										Workshop</option>
									<option value="TTS-TB-101">The Telesales Bootcamp</option>
									<option value="RM-TS-101">Trust Selling</option>
								</select>
							</div>
							<div class="form-group col-sm-12">
								<input type="button" class="btn btn-danger btn-md mobile-font"
									id="save-category" value="Save Workshop Category"
									style="margin-bottom: 1%" />
							</div>
						</div>
					</form>
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
	<script type="text/javascript">
		var msgD = $("#msg-div");
		var formDiv = $(".form-div");
		var myForm = $("#add-workshop-category-form");
		function validate() {

			var input = $("#category-name");
			var ok = required(input);
			if (!ok) {
				addError(msgD, "Workshop Category Name cannot be empty");
				return false;
			}
			

			input = $("#category-image");
			ok = required(input);
			if (!ok) {
				addError(msgD, "You have to select an image");
				return false;
			}

			input = $("#workshops");
			ok = required(input);
			if (!ok) {
				addError(msgD, "Workshops cannot be empty");
				return false;
			}

			return true;

		}
		$(document)
				.ready(
						function() {
							myForm
									.on(
											'submit',
											function(e) {
												var x = $(this).attr('action');

												$
														.ajax({
															url : x,
															type : 'POST',
															data : new FormData(
																	this),
															processData : false,
															contentType : false,
															success : function(
																	data) {
																addSuccess(
																		msgD,
																		"The Workshop Category has been saved successfully.");
															},
															error : function(
																	jqXHR,
																	status,
																	errorThrown) {
																addError(msgD,
																		errorThrown);
															},
															complete : function() {
																formDiv
																		.waitMe("hide");
																clearForm(myForm);
															}
														});
												e.preventDefault();
											});

							$("#save-category")
									.click(
											function() {
												formDiv.waitMe({
													effect : 'ios',
													color : '#b1010c',
													sizeW : '15',
													sizeH : '15'
												});
												var cont = validate();

												if (cont) {
													console.log("hello 7");
													var jqxhr = $
															.post(
																	"/sm-admin/getuploadurl",
																	{
																		"callback" : "/sm-admin/save-workshop-category"
																	})
															.done(
																	function(
																			data) {
																		myForm
																				.prop(
																						'action',
																						data);
																		myForm
																				.submit();
																	})
															.error(
																	function() {
																		addError(
																				msgD,
																				errorThrown);
																	});
												} else {
													formDiv.waitMe("hide");
												}

											});
						});
	</script>
</body>
</html>