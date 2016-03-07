<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.salesmaxx.entities.UserRole"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Workshop</title>
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
						<div class="col-sm-3">
							<h4 class="text-danger" style="font-weight: bolder;">New
								Workshop</h4>
						</div>
						<div class="col-sm-3">
							<a href="/sm-admin/edit-workshop"
								class="text-muted page-action-menu"><h4>Edit Workshop</h4></a>
						</div>
						<div class="col-sm-3">
							<a href="/sm-admin/workshop-schedule"
								class="text-muted page-action-menu"><h4>Schedule
									Workshop</h4></a>
						</div>
						<div class="col-sm-3">
							<a href="#" class="text-muted page-action-menu"><h4>View
									All</h4></a>
						</div>

					</div>
					<div class="col-sm-1">
						<img class="img-responsive admin-page-img"
							src="/images/icons/add-workshop.png" alt="Add workshop">
					</div>
				</div>
				<hr style="margin-top: 0" />
				<div class="row form-div">
					<form id="add-workshop-form">
						<div class="col-md-12">
							<div id="msg-div"></div>
						</div>
						<div class="col-md-6">
							<div class="col-md-12 form-group">
								<label for="workshop-name">Workshop Name:</label> <select
									id="workshop-name" name="workshop-name" class="form-control">
									<option>Situational Sales Negotiation</option>
									<option>Coaching Sales People For Performance</option>
									<option>Consultative Selling</option>
									<option>Developing A Winning Sales Presentation</option>
									<option>Developing & Implementing Winning Strategic
										Account Plans</option>
									<option>Indirect Channel Sales Bootcamp</option>
									<option>Interviewing, Hiring & Onboarding Top Sales
										Talent</option>
									<option>Negotiating Beyond Price & Tactical Approaches</option>
									<option>Presenting To Senior Executives</option>
									<option>Professional Selling Bootcamp</option>
									<option>Prospecting Bootcamp</option>
									<option>Relationship Marketing</option>
									<option>Retail Selling</option>
									<option>Sales-Call Management</option>
									<option>Sales Compensation Bootcamp</option>
									<option>Sales Pipeline Management</option>
									<option>Sales Pipeline Management Bootcamp</option>
									<option>Sales Promotion Bootcamp</option>
									<option>Sales Opportunity Management</option>
									<option>Selling to Senior Executives</option>
									<option>Situational Sales Negotiation</option>
									<option>Solution Selling Workshop</option>
									<option>Strategic Account Management</option>
									<option>Technology Sales Training Workshop</option>
									<option>The Telesales Bootcamp</option>
									<option>Trust Selling</option>
								</select>
							</div>
							<div class="col-md-6 form-group">
								<label for="format">Format:</label> <select id="format"
									name="format" class="form-control">
									<option value="instructor-lead">Instructor Led</option>
									<option value="ilv">Instructor Led Virtual</option>
									<option value="on-demand">On-Demand</option>
								</select>
							</div>
							<div class="col-md-6 form-group">
								<label for="gender">Gender:</label> <select id="gender"
									name="gender" class="form-control">
									<option value="both">Both</option>
									<option value="female">Female</option>
									<option value="male">Male</option>
								</select>
							</div>
							<div class="col-md-6 form-group">
								<label for="priority">Priority:</label> <select id="priority"
									name="priority" class="form-control">
									<option>First Five</option>
									<option>Second Five</option>
									<option>Third Five</option>
									<option>Other</option>
								</select>
							</div>

							<div class="col-md-6  form-group">
								<label for="price">Price:</label> <input type="text"
									name="price" id="price" class="form-control" value="99999" />
							</div>

							<div class="form-group col-md-12">
								<label for="image">Workshop Image:</label> <input type="file"
									class="form-control mobile-input" id="image" name="image" />
							</div>

							<div class="col-md-12 form-group">
								<label for="skill-level">Skill Level:</label> <select
									id="skill-level" name="skill-level" class="form-control">
									<option value="foundation">Foundation</option>
									<option value="intermediate">Intermediate</option>
									<option value="advanced">Advanced</option>
								</select>
							</div>

							<div class="col-md-12 form-group">
								<label for="Industries">Industries:</label> <select
									id="industries" name="industries" class="form-control"
									multiple="multiple">
									<option>Consumer Sales</option>
									<option>Oil & Gas</option>
									<option>Financial Services</option>
									<option>Telecomms</option>
									<option>Manufacturing</option>
									<option>Healthcare</option>
									<option>Professional services</option>
									<option>Other services</option>

								</select>
							</div>
							<div class="col-md-12 form-group">
								<label for="Professions">Professions:</label> <select
									id="professions" name="professions" class="form-control"
									multiple="multiple">
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

						</div>
						<div class="col-md-6">
							<div class="col-md-12 form-group">
								<label for="description">Short Description:</label>
								<textarea class="form-control" rows="3" cols="40"
									id="description" name="description"></textarea>
							</div>
							<div class="col-md-12 form-group">
								<label for="audience">Audience (; separated list):</label>
								<textarea class="form-control" rows="3" cols="40" id="audience"
									name="audience"
									placeholder="Separate entries with SEMI COLON(;)"></textarea>
							</div>
							<div class="col-md-12 form-group">
								<label for="course-content">Course Content (; separated
									list):</label>
								<textarea class="form-control" rows="3" cols="40"
									id="course-content" name="course-content"
									placeholder="Separate entries with SEMI COLON(;)"></textarea>
							</div>

							<div class="col-md-12 form-group">
								<label for="learning-outcomes">Learning Outcomes (;
									separated list):</label>
								<textarea class="form-control" rows="3" cols="40"
									id="learning-outcomes" name="learning-outcomes"
									placeholder="Separate entries with SEMI COLON(;)"></textarea>
							</div>

							<div class="col-md-12 form-group">
								<label for="related-workshops">Related Workshops:</label> <select
									class="form-control" id="related-workshops"
									name="related-workshops" multiple="multiple">
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

						</div>
						<div class="col-md-12">
							<hr />
							<div class="form-group col-sm-12 ">
								<input type="button"
									class="btn btn-danger btn-md mobile-font pull-right"
									id="save-workshop" value="Save Workshop"
									style="margin-bottom: 1%" />
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
	<script type="text/javascript" src="/js/validate.js"></script>
	<script type="text/javascript" src="/js/add-workshop.js"></script>
</body>
</html>