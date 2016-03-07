<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.salesmaxx.entities.UserRole" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Workshop Material</title>
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
						<h2 class="text-danger">Add New Workshop Material</h2>
					</div>
					<div class="col-sm-1">
						<img class="img-responsive module-image"
							src="/images/icons/add-workshop-material.png"
							alt="Add workshop material">
					</div>
				</div>
				<hr style="margin-top: 0" />
				<div class="row">
					<div class="col-md-6 form-div">
						<form id="add-workshop-material-form" method="post">
								<div class="col-md-12">
									<div id="msg-div"></div>
								</div>
							<div class="form-group col-md-12">
								<label for="mat-name">Workshop Material Name:</label> <input
									type="text" class="form-control mobile-input" id="mat-name"
									name="mat-name" />
							</div>
							<div class="form-group col-md-6">
								<label for="type">Type:</label> <select id="type" name="type"
									class="form-control">
									<option>Article</option>
									<option>Video</option>
									<option>White Paper</option>
								</select>
							</div>
							<div class="form-group col-md-6">
								<label for="link">Link:</label> <input type="text"
									class="form-control mobile-input" id="link" name="link" />
							</div>
							<div class="form-group col-md-12">
								<label for="workshop">Workshop(ID only):</label> <input
									type="text" class="form-control mobile-input" id="workshop"
									name="workshop" />
							</div>
							<div class="form-group col-sm-12">
								<input type="button" class="btn btn-danger btn-md mobile-font"
									id="save-material" value="Save Workshop Material"
									style="margin-bottom: 1%" />
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
	<script type="text/javascript">
		var msgD = $("#msg-div");
		var myForm = $("#add-workshop-material-form");
		var formDiv = $(".form-div");
		function validate() {
			var input = $("#mat-name");
			var ok = required(input);
			if (!ok) {
				addError(msgD, "Workshop Material Name cannot be empty");
				return false;
			}
			input = $("#type");
			ok = required(input);
			if (!ok) {
				addError(msgD, "Please select a type");
				return false;
			}
			
			input = $("#link");
			ok = required(input);
			if (!ok) {
				addError(msgD, "Link cannot be empty");
				return false;
			}
			
			input = $("#workshop");
			ok = required(input);
			if (!ok) {
				addError(msgD, "Workshop cannot be empty");
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
						addSuccess(msgD,"The Workshop Material has been saved successfully.");
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
			
			$("#save-material").click(function() {
				formDiv.waitMe({
					effect : 'ios',
					color : '#b1010c',
					sizeW : '15',
					sizeH : '15'
				});
				var cont = validate();	
				if(cont) {
					var jqxhr = $.post("/sm-admin/save-workshop-material", myForm.serialize() ).done(function(data) {
						myForm.prop('action', data);
						myForm.submit();
					}).error(function(jqXHR,status,errorThrown) {
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