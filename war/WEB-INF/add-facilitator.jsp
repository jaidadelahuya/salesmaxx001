<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.salesmaxx.entities.UserRole"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Facilitator</title>
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
						<h2 class="text-danger">Add New Facilitator</h2>
					</div>
					<div class="col-sm-1">
						<img class="img-responsive module-image"
							src="/images/icons/user.png" alt="Add Facilitator">
					</div>
				</div>
				<hr style="margin-top: 0" />
				<div class="row form-div">
					<div class="col-md-12">
						<div id="msg-div"></div>
					</div>
					<form id="add-facilitator-form" method="post">
						<div class="col-md-6">

							<div class="form-group col-md-6">
								<label for="first-name">First Name:</label> <input type="text"
									class="form-control mobile-input" id="first-name"
									name="first-name" />
							</div>
							<div class="form-group col-md-6">
								<label for="last-name">Last Name:</label> <input type="text"
									class="form-control mobile-input" id="last-name"
									name="last-name" />
							</div>

							<div class="form-group col-md-12">
								<label for="picture">Upload Picture:</label> <input type="file"
									class="form-control mobile-input" id="picture" name="picture" />
							</div>
							<div class="form-group col-md-12">
								<label for="profile">Profile:</label>
								<textarea class="form-control" rows="5" cols="20" id="profile"
									name="profile"></textarea>
							</div>
							<div class="form-group col-md-12">
								<label for="education">Education (; separated list):</label>
								<textarea class="form-control" rows="5" cols="20" id="education"
									name="education"
									placeholder="Separate entries with SEMI COLON(;)"></textarea>
							</div>
							<div class="form-group col-md-12">
								<label for="Certification">Certification (; separated
									list):</label>
								<textarea class="form-control" rows="5" cols="20"
									id="certification" name="certification"
									placeholder="Separate entries with SEMI COLON(;)"></textarea>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group col-md-12">
								<label for="work-experience">Work Experience (;
									separated list):</label>
								<textarea class="form-control" rows="5" cols="20"
									id="work-experience" name="work-experience"
									placeholder="Separate entries with SEMI COLON(;)"></textarea>
							</div>

							<div class="form-group col-md-12">
								<label for="recent-development">Recent Development (;
									separated list):</label>
								<textarea class="form-control" rows="5" cols="20"
									id="recent-development" name="recent-development"
									placeholder="Separate entries with SEMI COLON(;)"></textarea>
							</div>

							<div class="form-group col-md-12">
								<label for="specialization">Specialization (; separated
									list):</label>
								<textarea class="form-control" rows="5" cols="20"
									id="specialization" name="specialization"
									placeholder="Separate entries with SEMI COLON(;)"></textarea>
							</div>
						</div>

						<div class="col-md-12">
							<hr />
							<div class="form-group col-sm-12 ">
								<input type="button"
									class="btn btn-danger btn-md mobile-font pull-right"
									id="save-facilitator" value="Save Facilitator Info"
									style="margin-bottom: 1%;" />
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
	<script type="text/javascript">
		var msgD = $("#msg-div");
		var myForm = $("#add-facilitator-form");
		var formDiv = $(".form-div");
		function validate() {

			var input = $("#first-name");
			var ok = required(input);
			if (!ok) {
				addError(msgD, "First Name cannot be empty");
				return false;
			}

			input = $("#last-name");
			ok = required(input);
			if (!ok) {
				addError(msgD, "Last Name cannot be empty");
				return false;
			}

			input = $("#picture");
			ok = required(input);
			if (!ok) {
				addError(msgD, "You need to upload a Picture");
				return false;
			}
			
			input = $("#profile");
			ok = required(input);
			if (!ok) {
				addError(msgD, '<p>Profile cannot be empty.</p><p>Enter "NILL" if not available</p>');
				return false;
			}

			input = $("#education");
			ok = required(input);
			if (!ok) {
				addError(msgD, '<p>Education cannot be empty.</p><p>Enter "NILL" if not available</p>');
				return false;
			}

			input = $("#certification");
			ok = required(input);
			if (!ok) {
				addError(msgD, '<p>Certification cannot be empty.</p><p>Enter "NILL" if not available</p>');
				return false;
			}

			input = $("#work-experience");
			ok = required(input);
			if (!ok) {
				addError(msgD, '<p>Work Experience cannot be empty.</p><p>Enter "NILL" if not available</p>');
				return false;
			}

			input = $("#recent-development");
			ok = required(input);
			if (!ok) {
				addError(msgD, '<p>Recent Development cannot be empty.</p><p>Enter "NILL" if not available</p>');
				return false;
			}

			input = $("#specialization");
			ok = required(input);
			if (!ok) {
				addError(msgD, '<p>Profile cannot be empty.</p><p>Enter "NILL" if not available</p>');
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
						addSuccess(msgD, data);
					},
					error : function(jqXHR, status, errorThrown) {
						addError(msgD, errorThrown);
					},
					complete : function() {
						formDiv.waitMe("hide");
						clearForm(myForm);
					}
				});
				e.preventDefault();
			});

			$("#save-facilitator").click(function() {
				formDiv.waitMe({
					effect : 'ios',
					color : '#b1010c',
					sizeW : '15',
					sizeH : '15'
				});
				var cont = validate();

				if (cont) {
					var jqxhr = $.post("/sm-admin/getuploadurl", {
						"callback" : "/sm-admin/save-facilitator"
					}).done(function(data) {
						myForm.prop('action', data);
						myForm.submit();
					}).error(function() {
						addError(msgD, errorThrown);
					});
				} else {
					formDiv.waitMe("hide");
				}

			});
		});
	</script>
</body>
</html>