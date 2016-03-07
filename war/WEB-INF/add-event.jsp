<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.salesmaxx.entities.UserRole" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Events</title>
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
						<h2 class="text-danger">Events</h2>
					</div>
					<div class="col-sm-1">
						<img class="img-responsive module-image"
							src="/images/icons/add-delete-events.png" alt="Add/Delete Event">
					</div>
				</div>
				<hr style="margin-top: 0" />
				<div class="row">
				<form id="save-event-form">
					<div class="col-md-6 form-div">
					<div class="col-md-12 form-group">
							<div id="msg-div"></div>
						</div>
						
						<div class="col-md-12 form-group">
							<label for="event-name">Event Name:</label> <input type="text"
								name="event-name" id="event-name" class="form-control" required="required" />
						</div>
						
						<div class="col-md-12 form-group">
							<label for="image">Event Image:</label> <input type="file"
								id="image" name="image" class="form-control" required="required">
						</div>
						<div class="col-md-6 form-group">
							<label for="event-id">Event ID:</label> <input type="text"
								id="event-id" name="event-id" class="form-control" required="required">
						</div>
						<div class="col-md-6 form-group">
							<label for="credit">SalesMaxx Credits:</label> <input type="text"
								name="credits" id="credits" class="form-control" required="required" />
						</div>
						
						<div class="col-md-12 form-group">
							<label for="description">Description:</label>
							<textarea class="form-control" rows="3" cols="40"
								id="description" name="description" required="required"></textarea>
						</div>
						<div class="form-group col-sm-12">
							<input type="button" class="btn btn-danger btn-md mobile-font"
								id="add-event" value="Add Event" style="margin-bottom: 1%" />
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
		function validate () {
			var msgD = $("#msg-div");
			var input = $("#event-name");
			var ok = required(input);
			if(!ok) {
				addError(msgD,"Event Name cannot be empty");
				return false;
			}
			
			input = $("#image");
			ok = required(input);
			if(!ok) {
				addError(msgD,"You have to select an image");
				return false;
			}
			
			input = $("#event-id");
			ok = required(input);
			if(!ok) {
				addError(msgD,"Event ID cannot be empty");
				return false;
			}
			input = $("#credits");
			ok = required(input);
			if(!ok) {
				addError(msgD,"SalesMaxx Credit cannot be empty");
				return false;
			}
			ok = allNumeric(input);
			if(!ok) {
				addError(msgD,"SalesMaxx Credit cannot contain any other character other than a digit");
				return false;
			}
			
			
			input = $("#description");
			ok = required(input);
			if(!ok) {
				addError(msgD,"Description cannot be empty");
				return false;
			}
			
			return true;
			
		}
		$(document).ready(function() {
			var msgD = $("#msg-div");
			var myForm = $("#save-event-form");
			myForm.on('submit', function(e) {
				var x = $(this).attr('action');

				$.ajax({
					url : x,
					type : 'POST',
					data : new FormData(this),
					processData : false,
					contentType : false,
					success : function(data) {
						addSuccess(msgD,"The Event has been saved successfully.");
					},
					error : function(jqXHR, status, errorThrown) {
						addError(msgD,errorThrown);
					}, complete : function() {
						$(".form-div").waitMe("hide");
						clearForm(myForm);
					}
				});
				e.preventDefault();
			});
			$("#add-event").click(function() {
				$(".form-div").waitMe({
					effect : 'ios',
					color : '#b1010c',
					sizeW : '15',
					sizeH : '15'
				});
				var cont = validate();
				if(cont) {
					var jqxhr = $.post("/sm-admin/getuploadurl", {"callback":"/sm-admin/save-event"}).done(function(data) {
						myForm.prop('action', data);
						myForm.submit();
					}).error(function() {
						addError(msgD,"We could not save the Event at this time. Please try again later.");
					});
				} else {
					$(".form-div").waitMe("hide");
				}
				
			});
		});
	</script>
</body>
</html>