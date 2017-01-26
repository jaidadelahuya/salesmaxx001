<%@page import="com.salesmaxx.beans.ScheduleWorkshopDisplay"%>
<%@page import="java.util.List"%>
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
<title>Extend Workshop</title>
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
	<%
		String workshopName = request.getParameter("workshop-name");
			if (Util.notNull(workshopName)) {
		String wid = Util.getWorkshopId(workshopName);
		WorkshopTemplate wt = Util.getWorkshopFromList(wid,
		Util.getWorkshopTemplateFromCache());
		List<WorkShop> schedules = Util.getScheduledWorkshops(wt
		.getSchedules());
		List<ScheduleWorkshopDisplay> sch = Util
		.toScheduleWorkshopDisplay(schedules);
		session.setAttribute("extendWorkshopList",sch);
		session.setAttribute("wkname", workshopName);
			}
	%>
	<%@ include file="/WEB-INF/sidebar.html"%>


	<div id="main">
		<%@ include file="/WEB-INF/nav"%>
		<div class="container">
			<div class="row">
				<div class="col-sm-12 form-page">
					<div class="row">
						<div class="col-sm-12">
							<h2 class="text-danger">
								Schedules for
								${wkname}</h2>
						</div>
						<div class="col-sm-12">
							<h6 class="text-primary">You can only extend workshops with
								no seats less than 30</h6>
						</div>
						<div class="col-sm-12">
							<c:choose>
								<c:when test="${not empty errorMsg}">
									<div class="alert alert-danger alert-dismissable">
									<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
									${errorMsg}</div>
								</c:when>
								<c:when test="${not empty successMsg}">
									<div class="alert alert-success alert-dismissable">
									<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
									${successMsg}</div>
								</c:when>
							</c:choose>
						</div>
						<div class="col-sm-12">
							<table class="table table-striped table-responsive">
								<thead>
									<tr>
										<th>Workshop Date</th>
										<th>Location</th>
										<th>Total No. of seats</th>
										<th>No. of seats left</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item" items="${extendWorkshopList}">
										<tr>
											<td>${item.startDate}</td>
											<td>${item.location.state}</td>
											<td>${item.totalSeats}</td>
											<td>${item.seatsLeft}</td>
											<td><c:if test='${item.totalSeats < 30}'><a href="/sm-admin/workshop/extend-class?id=${item.id}">Extend Class Size</a></c:if></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>

					</div>
					<div class="row">
						<div class="col-sm-12">
							<a href="/sm-admin/workshop/1/extend" class="btn btn-primary">Extend another workshop</a>
						</div>
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