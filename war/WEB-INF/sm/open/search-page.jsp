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
<title>Workshops</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="/style/bootstrap.min.css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" type="text/css"
	href="/style/jquery.webui-popover.min.css">
<link rel="stylesheet" type="text/css" href="/style/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="/style/slick.css" />
<link rel="stylesheet" type="text/css" href="/style/slick-theme.css" />
<link rel="stylesheet" type="text/css" href="/style/waitMe.css">
<link rel="stylesheet" type="text/css" href="/style/index.css">
<link rel="stylesheet" type="text/css" href="/style/custom.css">
<link rel="stylesheet" type="text/css" href="/style/main-style.css">
<link rel="stylesheet" type="text/css"
	href="/style/bootstrap-rating.css">
<link rel="stylesheet" type="text/css" href="/style/datepicker.css"><script src="https://apis.google.com/js/platform.js" async defer></script>
<meta name="google-signin-client_id"
	content="1082599418027-i7l89ubpe432n7lfiu9jus3cc0a99vqs.apps.googleusercontent.com">
</head>
<body>
	<%@ include file="/WEB-INF/main-nav"%>
	<%@ include file="/WEB-INF/sm/open/sub-nav"%>
	<div class="container-fluid"
		style="width: 80%; margin: 0 auto; height: 100%;">
		<div class="row">
			<div class="panel panel-default col-md-3" style="padding: 0">
				<%@ include file="/WEB-INF/sm/open/workshop-sidebar"%>
			</div>
			<div class="col-md-9">
				<div class="panel panel-default col-md-12" style="padding: 0;">
					<%@ include file="/WEB-INF/sm/open/search-page.html"%>
				</div>
				<c:if test="${(sb1.pageEnd - sb1.pageStart) < 3}">
					<c:if test="${(sb1.pageEnd - sb1.pageStart) < 2}">
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
					<c:if test="${fn:length(searchDocs) < 2}">
						<div
							style="padding: 1%; padding-bottom: 2%; background-color: white;"
							class="well col-md-12">
							<div class="col-md-12" style="text-align: center;">
								<h3 class="text-primary" style="padding: 2%;">Check out our
									Bootcamps</h3>
								<div class="col-md-12" id="slick">
									<c:forEach var='item' items='${bootcamps}'>
										<div class="col-sm-3" style="font-family: calibri">
											<a
												href="<c:url value='/sm/open/workshop-information?id=${item.workshopId.name}'/>"><img
												style="width: 80%; height: auto; margin: 0 auto"
												alt="${item.workshopName}" src="${item.imageUrl}"
												class="img img-responsive"></a> <a
												href="<c:url value='/sm/open/workshop-information?id=${item.workshopId.name}'/>"><c:out
													value='${item.workshopName}' /></a>
											<p>
												<input type="hidden" class="rating" data-readonly
													data-fractions="5" value="rating" style='display: block' />
												(
												<c:out value="${fn:length(item.reviews)}" />
												) reviews
											</p>
										</div>
									</c:forEach>
								</div>
							</div>

						</div>
					</c:if>
				</c:if>
			</div>
			<!-- <div class="col-md-3" style="padding: 0">
				<%@ include file="/WEB-INF/sm/open/workshop-sidebar-right"%>
			</div> -->
		</div>

	</div>
	<div style="margin-top: 2%;">
		<%@ include file="/WEB-INF/footer.html"%>
	</div>
	<script src="/js/jquery-1.11.2.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	<script src="/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="/js/slick.min.js"></script>
	<script src="/js/jquery.webui-popover.min.js"></script>
	<script type="text/javascript" src="/js/waitMe.js"></script>
	<script type="text/javascript" src="/js/bootstrap-datepicker.min.js"></script>
	<script type="text/javascript" src="/js/modules.js"></script>
	<script type="text/javascript" src="/js/bootstrap-rating.min.js"></script>
	<script type="text/javascript" src="/js/index.js"></script>
	<script type="text/javascript">
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