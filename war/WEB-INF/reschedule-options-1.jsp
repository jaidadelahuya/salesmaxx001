<%@page import="com.salesmaxx.entities.WorkshopTemplate"%>
<%@page import="com.salesmaxx.util.Util"%>
<%@page import="com.salesmaxx.entities.WorkShop"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.salesmaxx.entities.UserRole"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Reschedule Options</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" type="text/css" href="/style/jquery-ui.css">
<link rel="stylesheet" href="/style/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/style/main-style.css">
<link rel="stylesheet" type="text/css"
	href="/style/jquery.webui-popover.min.css">
<link rel="stylesheet" type="text/css" href="/style/waitMe.css">
<script src="/js/sidebar.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/sidebar.html"%>
	<%
		String txn = request.getParameter("txn-id");
		String id = request.getParameter("id");
		String qty = request.getParameter("qty");
	%>

	<div id="main">
		<%@ include file="/WEB-INF/nav"%>
		<div class="container">
			<div class="row">
				<div class="col-sm-12 form-page">
					<div class="row">
						<div class="col-sm-12">
							<h2 class="text-danger">Reschedule Options</h2>
						</div>
						<div class="col-sm-12">
							<h6 class="text-primary">Select a workshop</h6>
						</div>

						<form action="/sm-admin/workshop/schedule/get">
							<div class="form-group col-sm-6">
								<%@ include file="/partials/workshops.html"%>
								<input name="txn-ref" type="hidden" value="<%=txn%>"> <input
									name="id" type="hidden" value="<%=id%>"> <input
									name="qty" type="hidden" value="<%=qty%>">
							</div>
							
							<div class="form-group col-sm-12">
								<input class="btn btn-primary" type="submit" value="Continue">
							</div>
						</form>


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
		$(document).ready(function() {
			$("#a-search").click(function(e) {
				$(".search-div").show();
			});
			$(".display-cheque-component").click(function(e) {
				e.preventDefault();
				parent = $(this).parent().parent();
				parent.next(".xxxx").slideToggle();
			});
		});
	</script>

</body>
</html>