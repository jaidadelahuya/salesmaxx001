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
<%@ include file="/js/google-analytics"%>
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
				<div class="discussion-sidebar">
					<h4>
						<a href="/calendar">Workshops by Month</a>
					</h4>
					<span class="workshop-by-month"><a href="/calendar#jan">JAN</a></span>
					<span class="workshop-by-month"><a href="/calendar#feb">FEB</a></span>
					<span class="workshop-by-month"><a href="/calendar#mar">MAR</a></span>
					<span class="workshop-by-month"><a href="/calendar#apr">APR</a></span>
					<span class="workshop-by-month"><a href="/calendar#may">MAY</a></span>
					<span class="workshop-by-month"><a href="/calendar#jun">JUN</a></span>
					<span class="workshop-by-month"><a href="/calendar#jul">JUL</a></span>
					<span class="workshop-by-month"><a href="/calendar#aug">AUG</a></span>
					<span class="workshop-by-month"><a href="/calendar#sep">SEP</a></span>
					<span class="workshop-by-month"><a href="/calendar#oct">OCT</a></span>
					<span class="workshop-by-month"><a href="/calendar#nov">NOV</a></span>
					<span class="workshop-by-month"><a href="/calendar#dec">DEC</a></span>
				</div>
				<div style="position: relative;">
					<iframe src="/images/tools_or_luck_internal/tools_or_luck_internal.html"
						width="310" height="255" scrolling="no" frameBorder='0'></iframe>
					<a href="/tools"
						style="position: absolute; top: 0; left: 0; display: inline-block; width: 336px; height: 280px; z-index: 5;"></a>
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
	<script type="text/javascript">
		$(document).ready(function() {
			$(".main-menu-item").removeClass("active");
			$(".main-menu-item").removeClass("menu-active");
			$("#about-menu").addClass("menu-active");
		});
	</script>
</body>
</html>