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
<title>About SalesMaxx</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="/style/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="/style/jquery.webui-popover.min.css">
<link rel="stylesheet" type="text/css" href="/style/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="/style/waitMe.css">
<link rel="stylesheet" type="text/css" href="/style/index.css">
<link rel="stylesheet" type="text/css" href="/style/custom.css">
<link rel="stylesheet" type="text/css" href="/style/main-style.css">
<link rel="stylesheet" type="text/css"
	href="/style/bootstrap-rating.css">
<link rel="stylesheet" type="text/css" href="/style/datepicker.css">
</head>
<body>
	<%@ include file="/WEB-INF/main-nav"%>
	<%@ include file="/WEB-INF/sm/open/sub-nav"%>
	<div class="container-fluid"
		style="width: 95%; margin: 0 auto; height: 100%;">
		<div class="row">

			<div class="col-md-9" style="padding-left: 0px">
				<div class="panel panel-default col-md-12" style="padding: 0">
					
					<div class="col-md-12" style="text-align: center; padding: 5%;">
						<img alt="SalesMaxx" src="/images/salesmaxx-logo.jpg" />
						<h4>QUALITY AND AFFORDABLE SALES TRAININGS</h4>
					</div>
					<hr />
					<p style="padding-right: 3%; padding-left: 3%;">Salesmaxx is
						the leading provider of multi-platform Sales capability
						development workshops for sales people at different levels of
						proficiency, across multiple sales expertise domains, and for many
						industry verticals. Sales is a rich body of professional practice
						and as diverse as professionals like medicine or law, and one
						where specialization brings great benefits. Therefore, we offer
						sales training that are not generic, but aligned to each
						individual's work and industry or even type of customer one sells
						to.</p>
					<p style="padding-right: 3%; padding-left: 3%;">Salesmaxx is
						known as one of the leading provider of Sales & Marketing
						Capability Development Programs & Services in Africa, offering
						high-quality products at an affordable price.</p>
					<p style="padding-right: 3%; padding-left: 3%;">We believe that
						you don't have to make the trade-off between getting quality sales
						training and affordability. That is why we deliver through
						Instructor-Led workshops, with eLearning, as well as a blended
						learning approach to ensure our learners can go back to work and
						apply the skills learned.</p>
					<p style="padding-right: 3%; padding-left: 3%;">To ensure sales
						people learn from subject matter experts, we use the Coach: Train
						Model of delivering. Our facilitators are some of the world's best
						when the subject is selling. They come with depth and breadth in
						sales methodology consulting, being themselves practicing sales &
						marketing professionals who have led (and continue to lead) sales
						teams, dealing with the same issues as the delegates face daily.
						There is no better way of coaching someone into successful
						outcomes.</p>
					<p style="padding-right: 3%; padding-left: 3%;">Since its
						inception in 2009, Salesmaxx is acknowledged for supporting sales
						people's advancement in their careers by providing content & tools
						that they can afford, easily assimilate and apply to their work.</p>
				</div>
			</div>
			<div class="col-md-3" style="padding: 0">
				<div class="discussion-sidebar">
					<h4>
						<a href="/sm/open/sales-and-marketing-templates">Sales &
							Marketing Templates</a>
					</h4>
					<div class="col-sm-12 no-padding-div" style="padding-bottom: 2%;">
						<div class="col-sm-2 no-padding-div">
							<a href="/sm/open/sales-and-marketing-templates?format=ms-wordl"><img
								alt="" src="/images/word.png" class="img img-responsive"></a>
						</div>
						<div class="col-sm-10" style="padding-top: 2%;">
							<a href="/sm/open/sales-and-marketing-templates?format=ms-word">MS-Word
								templates</a>
						</div>
					</div>
					<div class="col-sm-12 no-padding-div" style="padding-bottom: 2%;">
						<div class="col-sm-2 no-padding-div" style="padding-right: 0px">
							<a href="/sm/open/sales-and-marketing-templates?format=excel"><img
								alt="" src="/images/excel.png" class="img img-responsive"></a>
						</div>
						<div class="col-sm-10" style="padding-top: 2%;">
							<a href="/sm/open/sales-and-marketing-templates?format=excel">Excel
								templates</a>
						</div>
					</div>
					<div class="col-sm-12 no-padding-div" style="padding-bottom: 2%;">
						<div class="col-sm-2 no-padding-div" style="padding-right: 0px">
							<a
								href="/sm/open/sales-and-marketing-templates?format=power-point"><img
								alt="" src="/images/powerpoint.png" class="img img-responsive"></a>
						</div>
						<div class="col-sm-10" style="padding-top: 2%;">
							<a
								href="/sm/open/sales-and-marketing-templates?format=power-point">Power
								Point templates</a>
						</div>
					</div>

					<div class="col-sm-12 no-padding-div" style="padding-bottom: 2%;">
						<div class="col-sm-2 no-padding-div" style="padding-right: 0px">
							<a href="/sm/open/sales-and-marketing-templates?format=pdf"><img
								alt="" src="/images/pdf.png" class="img img-responsive"></a>
						</div>
						<div class="col-sm-10" style="padding-top: 2%;">
							<a href="/sm/open/sales-and-marketing-templates?format=pdf">PDF
								templates</a>
						</div>
					</div>
				</div>

				<div class="discussion-sidebar">
					<h4>
						<a href="/coaching">Coaching Categories</a>
					</h4>
					<ul>
						<li><a
							href="<c:url value='/sm/open/view-all-discussion?category=Interview Coaching'/>">Interview
								Coaching</a></li>
						<li><a
							href="<c:url value='/sm/open/view-all-discussion?category=Excecutive Coaching'/>">Executive
								Coaching</a></li>
						<li><a
							href="<c:url value='/sm/open/view-all-discussion?category=Sales Performance Coaching'/>">Sales
								Performance Coaching</a></li>
					</ul>
				</div>
				<div style="margin-bottom: 4%">
					<a href='/calendar'><img class="img img-responsive"
						src="/images/calender.jpg" alt="Calendar" /></a>
				</div>


			</div>
		</div>

	</div>
	<div style="margin-top: 2%;">
		<%@ include file="/WEB-INF/footer.html"%>
	</div>

	<script src="/js/jquery-1.11.2.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script src="/js/jquery-ui.min.js"></script>
	<script src="/js/jquery.webui-popover.min.js"></script>
	<script type="text/javascript" src="/js/waitMe.js"></script>
	<script type="text/javascript" src="/js/bootstrap-datepicker.min.js"></script>
	<script type="text/javascript" src="/js/modules.js"></script>
	<script type="text/javascript" src="/js/bootstrap-rating.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".main-menu-item").removeClass("active");
			$("#about-menu").addClass("active");
		});
	</script>
	<script type='text/javascript'>
		var $zoho = $zoho || {
			salesiq : {
				values : {},
				ready : function() {
					$zoho.salesiq.floatbutton.visible('hide');
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