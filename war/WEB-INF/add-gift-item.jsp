<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.salesmaxx.entities.UserRole" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Gift Item</title>
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
						<h2 class="text-danger">Gift Item</h2>
					</div>
					<div class="col-sm-1">
						<img class="img-responsive admin-page-img"
							src="/images/icons/gift-item.png" alt="Gift Items">
					</div>
				</div>
				<hr style="margin-top: 0" />
				<div class="row">
					<form id="add-gift-item-form" method="post">
						<div class="col-md-6 form-div">
							<div class="col-md-12 form-group">
								<div id="msg-div"></div>
							</div>
							<div class="form-group col-md-12">
								<label for="gift-name">Gift Name:</label> <input type="text"
									class="form-control mobile-input" id="gift-name"
									name="gift-name" />
							</div>
							<div class="form-group col-md-6">
								<label for="price">Price:</label> <input type="text"
									class="form-control mobile-input" id="price" name="price" />
							</div>
							<div class="form-group col-md-6">
								<label for="quantity">Quantity:</label> <input type="text"
									class="form-control mobile-input" id="quantity" name="quantity" />
							</div>
							<div class="form-group col-md-12">
								<label for="image">Item Image:</label> <input
									type="file" class="form-control mobile-input" id="image"
									name="image" />
							</div>
							<div class="form-group col-md-12">
								<label for="category">Category:</label> <select id="category"
									class="form-control" name="category">
									<option>cat 1</option>
									<option>cat 1</option>
								</select>
							</div>
							<div class="form-group col-md-12">
								<label for="description">Short Description:</label>
								<textarea class="form-control" rows="4" cols="20"
									id="description" name="description"></textarea>
							</div>
							<div class="form-group col-sm-12">
								<input type="button" class="btn btn-danger btn-md mobile-font"
									id="add-gift-item" value="Save Item" style="margin-bottom: 1%" />
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
		var input = $("#gift-name");
		var ok = required(input);
		var msgD = $("#msg-div");
		if(!ok) {
			addError(msgD,"Gift Name cannot be empty");
			return false;
		}
		input = $("#price");
		var ok = required(input);
		if(!ok) {
			addError(msgD,"Price cannot be empty");
			return false;
		}
		ok = allNumeric(input);
		if(!ok) {
			addError(msgD,"Price cannot contain a non-digit character");
			return false;
		}
		input = $("#quantity");
		var ok = required(input);
		if(!ok) {
			addError(msgD,"Quantity cannot be empty");
			return false;
		}
		ok = allNumeric(input);
		if(!ok) {
			addError(msgD,"Quantity cannot contain a non-digit character");
			return false;
		}
		input = $("#image");
		var ok = required(input);
		if(!ok) {
			addError(msgD,"You have to add an image");
			return false;
		}
		return true;
	}

		$(document).ready(function() {
			var myForm = $("#add-gift-item-form");
			var msgD = $("#msg-div");
						
			myForm.on('submit', function(e) {
				var x = $(this).attr('action');

				$.ajax({
					url : x,
					type : 'POST',
					data : new FormData(this),
					processData : false,
					contentType : false,
					success : function(data) {
						addSuccess(msgD,"The gift item has been saved successfully.");
					},
					error : function(jqXHR, status, errorThrown) {
						addError(msgD,"We could not save the gift item at this time. Please try again later.");
					}, complete : function() {
						$(".form-div").waitMe("hide");
						clearForm(myForm);
					}
				});
				e.preventDefault();
			});
			
			$("#add-gift-item").click(function() {
				
				$(".form-div").waitMe({
					effect : 'ios',
					color : '#b1010c',
					sizeW : '15',
					sizeH : '15'
				});
				var cont = validate();
				if(cont) {
					var jqxhr = $.post("/sm-admin/getuploadurl", {"callback":"/sm-admin/save-gift"}).done(function(data) {
						myForm.prop('action', data);
						myForm.submit();
					}).error(function() {
						addError(msgD,"We could not save the gift item at this time. Please try again later.");
					});
				} else {
					$(".form-div").waitMe("hide");
				}
			});
		});
	</script>

</body>
</html>