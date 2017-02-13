<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.salesmaxx.entities.UserRole"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Delegates</title>
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
	<div id="main">
		<%@ include file="/WEB-INF/nav"%>
		<div class="container">
			<div class="row">
				<div class="col-sm-12 col-md-offset-1 col-md-10">
					<div class="panel panel-danger">
						<div class="panel-heading">
							<h3>
								<c:out value='${delegatesPage.workshopName}' />
								<small>(<c:out value='${delegatesPage.workshopCode}' />)
								</small>
							</h3>
							<c:out value='${delegatesPage.state}' />
						</div>
					</div>
					<div class="panel-body">
						<table class="table table-responsive table-bordered table-striped">
							<thead>
								<tr>
									<th>Name</th>
									<th>Email</th>
									<th>Phone</th>
									<th>Registrar</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${delegatesPage.delegates}">
									<tr>
										<td><c:out value='${item.delegateName}' /></td>
										<td><c:out value='${item.email}' /></td>
										<td><c:out value='${item.phone}' /></td>
										<td><c:out value='${item.registrar}' /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
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
	<script type="text/javascript" src="/js/add-workshop.js"></script>
</body>
</html>