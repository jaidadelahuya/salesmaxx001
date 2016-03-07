<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.salesmaxx.entities.UserRole" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Upload Certificate</title>
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
						<h2 class="text-danger">Upload Certificate</h2>
					</div>
					<div class="col-sm-1">
						<img class="img-responsive module-image"
							src="/images/icons/certificate.png" alt="Upload Certificate">
					</div>
				</div>
				<hr style="margin-top: 0" />
				<div class="row">
					<form id="upload-certificate-form" method="post">
						<div class="col-md-6 form-div">
							<div class="col-md-12">
								<div id="msg-div"></div>
							</div>
							<div class="form-group col-md-12">
								<label for="reg-id">Student Registration ID:</label> <input
									type="text" class="form-control mobile-input" id="reg-id"
									name="reg-id" />
							</div>
							<div class="form-group col-md-12">
								<label for="certificate">Upload File:</label> <input type="file"
									class="form-control mobile-input" id="certificate"
									name="certificate" />
							</div>
							<div class="form-group col-sm-12">
								<input type="button" class="btn btn-danger btn-md mobile-font"
									id="upload" value="Upload Certificate"
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
		function validate() {
			var msgD = $("#msg-div");
			var input = $("#reg-id");
			var ok = required(input);
			if (!ok) {
				addError(msgD, "Student Registration ID cannot be empty");
				return false;
			}
			input = $("#certificate");
			ok = required(input);
			if (!ok) {
				addError(msgD, "You have to select a Certificate");
				return false;
			}
			return true;
		}
		$(document).ready(function() {
			var msgD = $("#msg-div");
			var myForm = $("#upload-certificate-form");
			myForm.on('submit', function(e) {
				var x = $(this).attr('action');
				console.log(x);
				$.ajax({
					url : x,
					type : 'POST',
					data : new FormData(this),
					processData : false,
					contentType : false,
					success : function(data) {
						addSuccess(msgD,"The Certificate has been saved successfully.");
					},
					error : function(jqXHR, status, errorThrown) {
						addError(msgD,"We could not save the Certificate at this time. Please try again later.");
					}, complete : function() {
						$(".form-div").waitMe("hide");
						clearForm(myForm);
					}
				});
				
			});
			
			$("#upload").click(function() {
				$(".form-div").waitMe({
					effect : 'ios',
					color : '#b1010c',
					sizeW : '15',
					sizeH : '15'
				});
				var cont = validate();
				if(cont) {
					var jqxhr = $.post("/sm-admin/getuploadurl", {"callback":"/sm-admin/upload-certificate"}).done(function(data) {
						myForm.prop('action', data);
						myForm.submit();
					}).error(function() {
						addError(msgD,"We could not save the Certificate at this time. Please try again later.");
					});
				}
				
			});
		});
	</script>
</body>
</html>