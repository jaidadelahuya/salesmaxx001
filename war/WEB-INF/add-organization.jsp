<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.salesmaxx.entities.UserRole" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Organization</title>
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
						<h2 class="text-danger">Loyalty Organizations</h2>
					</div>
					<div class="col-sm-1">
						<img class="img-responsive module-image"
							src="/images/icons/organization.png" alt="Add Organization">
					</div>
				</div>
				<hr style="margin-top: 0" />
				<div class="row form-div">
					<div class="col-md-12">
						<div id="msg-div"></div>
					</div>
					<form id="add-organization-form" method="post">
						<div class="col-md-6">

							<div class="form-group col-md-12">
								<label for="organization-name">Organization Name:</label> <input
									type="text" class="form-control mobile-input"
									id="organization-name" name="organization-name" />
							</div>
							<div class="form-group col-md-12">
								<label for="website">Website:</label> <input type="text"
									class="form-control mobile-input" id="website" name="website" />
							</div>
							<div class="form-group col-md-6">
								<label for="street">Street:</label> <input type="text"
									class="form-control mobile-input" id="street" name="street" />
							</div>
							<div class="form-group col-md-6">
								<label for="city">City:</label> <input type="text"
									class="form-control mobile-input" id="city" name="city" />
							</div>
							<div class="form-group col-md-6">
								<label for="state">State:</label> <select id="state"
									name="state" class="form-control">
									<option>Abia</option>
									<option>Adamawa</option>
									<option>Akwa Ibom</option>
									<option>Anambra</option>
									<option>Bauchi</option>
									<option>Bayelsa</option>
									<option>Benue</option>
									<option>Borno</option>
									<option>Cross River</option>
									<option>Delta</option>
									<option>Ebonyi</option>
									<option>Edo</option>
									<option>Ekiti</option>
									<option>Enugu</option>
									<option>F.C.T</option>
									<option>Gombe</option>
									<option>Imo</option>
									<option>Jigawa</option>
									<option>Kaduna</option>
									<option>Kano</option>
									<option>Katsina</option>
									<option>Kebbi</option>
									<option>Kogi</option>
									<option>Kwara</option>
									<option>Lagos</option>
									<option>Nasarawa</option>
									<option>Niger</option>
									<option>Ogun</option>
									<option>Ondo</option>
									<option>Osun</option>
									<option>Oyo</option>
									<option>Plateau</option>
									<option>Rivers</option>
									<option>Sokoto</option>
									<option>Taraba</option>
									<option>Yobe</option>
									<option>Zamfara</option>
									<option>other</option>
								</select>
							</div>
							<div class="form-group col-md-6">
								<label for="country">Country:</label> <input type="text"
									class="form-control mobile-input" id="country" name="country"
									value="Nigeria" />
							</div>
							<div class="form-group col-md-12">
								<label for="phone">Phones(separate with ,):</label> <input
									type="text" class="form-control mobile-input" id="phone"
									name="phone" />
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group col-md-12">
								<label for="image">Upload Picture:</label> <input type="file"
									class="form-control mobile-input" id="image" name="image" />
							</div>
							<div class="form-group col-md-12">
								<label for="description">Short Description:</label>
								<textarea class="form-control" rows="5" cols="20"
									id="description" name="description"></textarea>
							</div>
						</div>

						<div class="col-md-12">
							<hr />
							<div class="form-group col-sm-12 ">
								<input type="button"
									class="btn btn-danger btn-md mobile-font pull-right"
									id="save-organization" value="Save Organization"
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
	<script type="text/javascript">
		var msgD = $("#msg-div");
		var myForm = $("#add-organization-form");
		var formDiv = $(".form-div");
		function validate() {
			var input = $("#organization-name");
			var ok = required(input);
			if (!ok) {
				addError(msgD, "Organization Name cannot be empty");
				return false;
			}
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

			input = $("#image");
			ok = required(input);
			if (!ok) {
				addError(msgD, "You need to upload an image");
				return false;
			}

			input = $("#description");
			ok = required(input);
			if (!ok) {
				addError(msgD, "Description cannot be empty");
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
						addSuccess(msgD,"The Loyalty Orgainization has been saved successfully.");
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
			
			$("#save-organization").click(function() {
				formDiv.waitMe({
					effect : 'ios',
					color : '#b1010c',
					sizeW : '15',
					sizeH : '15'
				});
				var cont = validate();
				
				if(cont) {
					console.log("hello 7");
					var jqxhr = $.post("/sm-admin/getuploadurl", {"callback":"/sm-admin/save-organization"}).done(function(data) {
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