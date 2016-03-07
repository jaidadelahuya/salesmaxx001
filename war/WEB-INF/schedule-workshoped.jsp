<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.salesmaxx.entities.Facilitator"%>
<%@page import="java.util.List"%>
<%@page
	import="com.salesmaxx.persistence.controllers.FacilitatorController"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.salesmaxx.entities.UserRole"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Schedule Workshop</title>
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
	<%
		FacilitatorController cont = new FacilitatorController();
			List<Facilitator> facs = cont.findAllFacilitators();
			synchronized(session) {
		session.setAttribute("facilitators", facs);
			}
	%>
	<%@ include file="/WEB-INF/nav"%>
	<div class="container-fluid">
		<div class="row">
			<%@ include file="/WEB-INF/sidebar"%>
			<div class="col-sm-10 form-page">
				<div class="row">
					<div class="col-sm-11">
						<h2 class="text-danger">Schedule Workshop</h2>
					</div>
					<div class="col-sm-1">
						<img class="img-responsive module-image"
							src="/images/icons/schedule-workshop.png" alt="Schedule workshop">
					</div>
				</div>
				<hr style="margin-top: 0" />
				<div class="row form-div">
					<form id="schedule-workshop-form">
						<div class="msg-div"></div>
						<div class="col-md-6">
							<div class="col-md-12 form-group">
								<label for="workshop-name">Workshop Name:</label> <select
									id="workshop-name" name="workshop-name" class="form-control">
									<c:forEach var="et" items="${workshopTemplates}">
										<option value="${et.webSafeKey}"><c:out
												value="${et.workshopName}" /></option>
									</c:forEach>
								</select>
							</div>
							<div class="col-md-12 form-group">
								<label for="venue">Venue:</label> <input type="text" id="venue"
									name="venue" class="form-control" />
							</div>
							<div class="col-md-6  form-group">
								<label for="street">Street:</label> <input type="text"
									name="street" id="street" class="form-control" />
							</div>
							<div class="col-md-6 form-group">
								<label for="city">City:</label> <input type="text" name="city"
									id="city" class="form-control" />
							</div>
							<div class="col-md-6 form-group">
								<label for="state">State:</label> <input list="states"
									id="state" name="state" class="form-control" />
								<datalist id="states">
									<option value="Abia"></option>
									<option value="Adamawa"></option>
									<option value="Akwa Ibom"></option>
									<option value="Anambra"></option>
									<option value="Bauchi"></option>
									<option value="Bayelsa"></option>
									<option value="Benue"></option>
									<option value="Borno"></option>
									<option value="Cross River"></option>
									<option value="Delta"></option>
									<option value="Ebonyi"></option>
									<option value="Edo"></option>
									<option value="Ekiti"></option>
									<option value="Enugu"></option>
									<option value="F.C.T"></option>
									<option value="Gombe"></option>
									<option value="Imo"></option>
									<option value="Jigawa"></option>
									<option value="Kaduna"></option>
									<option value="Kano"></option>
									<option value="Katsina"></option>
									<option value="Kebbi"></option>
									<option value="Kogi"></option>
									<option value="Kwara"></option>
									<option value="Lagos"></option>
									<option value="Nasarawa"></option>
									<option value="Niger"></option>
									<option value="Ogun"></option>
									<option value="Ondo"></option>
									<option value="Osun"></option>
									<option value="Oyo"></option>
									<option value="Plateau"></option>
									<option value="Rivers"></option>
									<option value="Sokoto"></option>
									<option value="Taraba"></option>
									<option value="Yobe"></option>
									<option value="Zamfara"></option>
								</datalist>
							</div>
							<div class="col-md-6 form-group">
								<label for="country">Country:</label> <input type="text"
									name="country" id="country" class="form-control"
									value="Nigeria" />
							</div>
							<div class="col-md-6  form-group">
								<label for="start-date">Start Date:</label> <input type="date"
									name="start-date" id="start-date" class="form-control" />
							</div>
							<div class="col-md-6 form-group">
								<label for="end-date">End Date:</label> <input type="date"
									name="end-date" id="end-date" class="form-control" />
							</div>
							<div class="col-md-6 form-group">
								<label for="format">Format:</label> <select id="format"
									name="format" class="form-control">
									<option>Instructor Led</option>
									<option>Instructor Led Virtual</option>
									<option>On-Demand</option>
								</select>
							</div>
							<div class="col-md-6 form-group">
								<label for="type">Type:</label> <select id="type" name="type"
									class="form-control">
									<option>Paid</option>
									<option>Free</option>
								</select>
							</div>
						</div>
						<div class="col-md-6">
							<div class="col-md-12 form-group">
								<label for="flyer">Flyer:</label> <input type="file" id="flyer"
									name="flyer" class="form-control" required="required">
							</div>
							<div class="col-md-12 form-group">
								<label for="facilitators">Facilitators:</label> <select
									id="facilitators" name="facilitators" class="form-control"
									multiple="multiple">
									<c:forEach var="fac" items="${facilitators}">
										<option value="fac.id"><c:out
												value="${fac.firstName} ${fac.lastName}" /></option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group col-sm-12">
							<hr />
							<input type="button"
								class="btn btn-danger btn-md mobile-font pull-right"
								id="schedule-workshop" value="Add to Calendar" style="margin-bottom: 1%" />
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
	<script type="text/javascript">
		var msgD = $("#msg-div");
		var myForm = $("#schedule-workshop-form");
		var formDiv = $(".form-div");
		function validate() {
			console.log("hello 3");
			var input = $("#venue");
			var ok = required(input);
			if (!ok) {
				console.log("fuck");
				addError(msgD, "Venue cannot be empty");
				return false;
			}
			
			console.log("hello 4");
			input = $("#street");
			ok = required(input);
			if (!ok) {
				addError(msgD, "Street cannot be empty");
				return false;
			}

			input = $("#city");
			ok = required(input);
			if (!ok) {
				addError(msgD, "City cannot be empty");
				return false;
			}

			input = $("#state");
			ok = required(input);
			if (!ok) {
				addError(msgD, "State cannot be empty");
				return false;
			}

			input = $("#country");
			ok = required(input);
			if (!ok) {
				addError(msgD, "Country cannot be empty");
				return false;
			}
			console.log("hello 4");
			input = $("#start-date");
			ok = required(input);
			if (!ok) {
				addError(msgD, "Start Date cannot be empty");
				return false;
			}

			input = $("#end-date");
			ok = required(input);
			if (!ok) {
				addError(msgD, "End Date cannot be empty");
				return false;
			}
			
			input = $("#flyer");
			ok = required(input);
			if (!ok) {
				addError(msgD, "You have to select a Flyer");
				return false;
			}
	
			return true;
		}
		
		
		$(document).ready(function() {
			myForm.on('submit', function(e) {
				var x = $(this).attr('action');

				$.ajax({
					url : x,
					type : 'POST',
					data : new FormData(this),
					processData : false,
					contentType : false,
					success : function(data) {
						addSuccess(msgD,"The workshop has been scheduled.");
					},
					error : function(jqXHR, status, errorThrown) {
						addError(msgD,errorThrown);
					}, complete : function() {
						formDiv.waitMe("hide");
						clearForm(myForm);
					}
				});
				e.preventDefault();
			});
			
			$("#schedule-workshop").click(function() {
				console.log("hello");
				formDiv.waitMe({
					effect : 'ios',
					color : '#b1010c',
					sizeW : '15',
					sizeH : '15'
				});
				console.log("hello 1");
				//var cont = validate();
				
				if(true) {
					console.log("hello 7");
					var jqxhr = $.post("/sm-admin/getuploadurl", {"callback":"/sm-admin/workshop-schedule-1"}).done(function(data) {
						myForm.prop('action', data);
						myForm.submit();
					}).error(function() {
						addError(msgD,errorThrown);
					});
				} else {
					formDiv.waitMe("hide");
				}
				
			});
		});
	</script>
</body>
</html>