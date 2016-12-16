<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/WEB-INF/custom-functions.tld" prefix="pn"%>
<%@page import="com.salesmaxx.entities.UserRole"%>
<!DOCTYPE html>
<html style="height: 100%;">
<head>
<meta charset="ISO-8859-1">
<title><c:out value="${name }" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="/style/bootstrap.min.css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" type="text/css"
	href="/style/jquery.webui-popover.min.css">
<link rel="stylesheet" type="text/css" href="/style/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="/style/waitMe.css">
<link rel="stylesheet" type="text/css" href="/style/index.css">
<link rel="stylesheet" type="text/css" href="/style/custom.css">
<link rel="stylesheet" type="text/css" href="/style/main-style.css">
<link rel="stylesheet" type="text/css"
	href="/style/bootstrap-rating.css">
<script src="https://apis.google.com/js/platform.js" async defer></script>
<meta name="google-signin-client_id"
	content="1082599418027-i7l89ubpe432n7lfiu9jus3cc0a99vqs.apps.googleusercontent.com">
<%@ include file="/js/google-analytics"%>
</head>
<body>

	<%@ include file="/WEB-INF/main-nav"%>
	<%@ include file="/WEB-INF/sm/open/sub-nav"%>
	<div class="container-fluid"
		style="width: 95%; margin: 0 auto; height: 100%;">
		<div class="row">
			<div class="panel panel-default col-md-3" style="padding: 0">
				<%@ include file="/WEB-INF/sm/open/workshop-sidebar"%>
			</div>
			<div class=" col-md-9" style="padding-right: 0px">
				<div class=" panel panel-default col-md-12" style="padding: 0">
					<%@ include
						file="/WEB-INF/sm/open/find-workshop-by-prop-content.html"%>
				</div>
				<c:if test="${sb.resultsFound < 3}">
					<c:if test="${(searchBeanX.pageEnd - searchBeanX.pageStart) < 2}">
						<div class="panel panel-default col-md-12" style="padding: 0">
							<h1 class="text-muted" style="text-align: center;">
								Need Help?<a href="#"> <img alt="Speak to a consultant"
									src="/images/consultants.jpg" style="width: 30%; padding: 1%;" /></a>
							</h1>
						</div>
					</c:if>
					<div class="well col-md-12" style="padding: 0">
						<h1 style="text-align: center;">
							<a href="<c:url value='/calendar'/>"><img alt='SalesMaxx'
								src="/images/salesmaxx-logo.jpg"
								style="width: 30%; padding: 1%;" />2016 Calendar</a>
						</h1>
					</div>
				</c:if>
			</div>
			<!--  <div class="col-md-3" style="padding: 0">
				<%@ include file="/WEB-INF/sm/open/workshop-sidebar-right"%>
			</div> -->
		</div>

		<table id="t-row" style="display: none;">
			<tr>
				<td><h4>
						<a class="workshop-name"></a>
					</h4>
					<h5>
						<strong>Workshop Code :</strong> <a class="workshop-code"></a>
					</h5>
					<div style="font-family: calibri" class="row">
						<div class="col-md-3">
							<a class="workshop-img-link"><img
								class="img img-rounded img-responsive workshop-img"></a>
						</div>
						<div class="col-md-9">
							<p class="workshop-desc"></p>
						</div>
					</div>
					<p style="padding-top: 1%;">
						<strong>Date:</strong> <span class="workshop-date"
							style="font-family: georgia"></span> <strong>Location:</strong> <span
							class="workshop-state"></span> , <span class="workshop-country"></span>
						<a class="btn btn-xs btn-danger add-to-cart">Enroll Now</a> <a
							class="btn btn-xs btn-danger brochure" target="_blank">Download
							Brochure</a>
					</p></td>
			</tr>
		</table>

	</div>

	<script src="/js/jquery-1.11.2.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	<script src="/js/jquery-ui.min.js"></script>
	<script src="/js/jquery.webui-popover.min.js"></script>
	<script type="text/javascript" src="/js/waitMe.js"></script>
	<script type="text/javascript" src="/js/modules.js"></script>
	<script type="text/javascript" src="/js/bootstrap-rating.min.js"></script>
	<script type="text/javascript">
	function addResult(data) {
		$row = $("#t-row").clone();
		$tr = $row.find("tr");
		$tr.css("display", "block");
		$tr.find(".workshop-name").prop("href","/sm/open/get-workshop-template-by-schedule-workshop?id="+data.id);
		$tr.find(".workshop-name").text(data.name);
		$tr.find(".workshop-code").prop("href","/sm/open/get-workshop-template-by-schedule-workshop?id="+data.id);
		$tr.find(".workshop-code").text(data.workshopCode);
		$tr.find(".workshop-img-link").prop("href","/sm/open/get-workshop-template-by-schedule-workshop?id="+data.id);
		$tr.find(".workshop-img").prop("src",data.imageUrl);
		$tr.find(".workshop-desc").text(data.description);
		$tr.find(".workshop-date").text(data.startDate);
		$tr.find(".workshop-state").text(data.location.state);
		$tr.find(".workshop-country").text(data.location.country);
		$tr.find(".add-to-cart").prop("href","/sm/open/add-to-cart?id="+data.id+"&qty=1");
		$tr.find(".brochure").prop("href",data.catalogueLink);
		
		$("#result-list").append($tr);
	}
		window.onscroll = function(ev) {
			if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
				$.ajax({
					url : "/sm/open/workshop/search/next",
					dataType : "json",
					success : function(data) {
						console.log(data[0]);
						for(i=0; data.length; i++) {
							addResult(data[i]);
						}
					}
				});
			}
		};
		$(document).ready(function() {
			$(".main-menu-item").removeClass("active");
			//$("#workshop-menu").addClass("active");

		});
	</script>
	<script type='text/javascript'>
		var $zoho = $zoho || {
			salesiq : {
				values : {},
				ready : function() {
				}
			}
		};
		var d = document;
		s = d.createElement('script');
		s.type = 'text/javascript';
		s.defer = true;
		s.src = 'https://salesiq.zoho.com/profiliantngr/float.ls?embedname=speaktoaconsultant';
		t = d.getElementsByTagName('script')[0];
		t.parentNode.insertBefore(s, t);
	</script>
</body>
</html>