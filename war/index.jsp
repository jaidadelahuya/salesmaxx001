<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.salesmaxx.entities.UserRole"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SalesMaxx</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="/style/bootstrap.min.css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="/style/slick.css" />
<link rel="stylesheet" type="text/css" href="/style/slick-theme.css" />
<link rel="stylesheet" type="text/css"
	href="/style/jquery.webui-popover.min.css">
<link rel="stylesheet" type="text/css" href="/style/waitMe.css">
<link rel="stylesheet" type="text/css" href="/style/index.css">
<link rel="stylesheet" type="text/css" href="/style/custom.css">
<link rel="stylesheet" type="text/css" href="/style/main-style.css">
<meta name="image_src"
	content="http://www.salesmaxx.com/images/calender.jpg" />
<meta name="image_url"
	content="http://www.salesmaxx.com/images/calender.jpg" />
<meta property="fb:app_id" content="1134790883222273" />
<meta property="og:title" content="SalesMaxx Calendar" />
<meta property="og:type" content="website" />
<meta property="og:url" content="http://www.salesmaxx.com/calendar" />
<meta property="og:image"
	content="http://www.salesmaxx.com/images/calender.jpg" />
<meta property="og:description"
	content="Interesting SalesMaxx training workshops for 2017" />
<meta property="og:site_name" content="SalesMaxx" />
<meta name="twitter:card" content="Summary" />
<meta name="twitter:title" content="SalesMaxx Calendar" />
<meta name="twitter:description"
	content="Interesting SalesMaxx training workshops for 2017" />
<meta name="twitter:image"
	content="http://www.salesmaxx.com/images/calender.jpg" />
<meta name="twitter:url" content="http://www.salesmaxx.com/calendar" />
<%@ include file="/js/google-analytics"%>
<style type="text/css">
#grad {
	background: white; /* For browsers that do not support gradients */
	background: -webkit-linear-gradient(white, #d7ddea);
	/* For Safari 5.1 to 6.0 */
	background: -o-linear-gradient(white, #d7ddea);
	/* For Opera 11.1 to 12.0 */
	background: -moz-linear-gradient(white, #d7ddea);
	/* For Firefox 3.6 to 15 */
	background: linear-gradient(white, #d7ddea); /* Standard syntax */
}

#modal-header {
	text-shadow: 1px 1px 2px black, 0 0 1em #d5b0bc, 0 0 0.2em #b6758a;
	color: white;
	text-align: center;
}
</style>
</head>
<body style="overflow-x: hidden">


	<%@ include file="/WEB-INF/main-nav"%>
	<div id="carousel-wrapper" class="container-fluid">

		<div id="myCarousel" class="carousel slide row hidden-xs"
			data-ride="carousel">

			<!-- Indicators -->
			<ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
				<li data-target="#myCarousel" data-slide-to="2"></li>
				<li data-target="#myCarousel" data-slide-to="3"></li>
			</ol>

			<!-- Wrapper for slides -->
			<div class="carousel-inner" role="listbox">
				<div class="item active">
					<img class="img img-responsive"
						src="/images/salesmaxx_slider_Consultative-Selling.jpg"
						alt="slide 1" style="width: 100%">
					<div class="carousel-caption">
						<h3></h3>
						<p></p>
					</div>
				</div>

				<div class="item">
					<img class="img img-responsive" style="width: 100%;"
						src="/images/salesmaxx_slider_road.jpg" alt="slide 2">
					<div class="carousel-caption">
						<h3></h3>
						<p></p>
					</div>
				</div>

				<div class="item">
					<img class="img img-responsive" style="width: 100%;"
						src="/images/slide5.jpg" alt="slide 3">
					<div class="carousel-caption">
						<h3>Acing The Sales Target</h3>
						<p>Pipeline Management Workshop</p>
					</div>
				</div>

				<div class="item">
					<img class="img img-responsive" style="width: 100%;"
						src="/images/slide6.jpg" alt="slide 3">
					<div class="carousel-caption">
						<h3>Acing The Sales Target</h3>
						<p>Pipeline Management Workshop</p>
					</div>
				</div>

				<div class="item">
					<img class="img img-responsive"
						src="/images/salesmaxx_slider_yes.jpg" style="width: 100%;"
						alt="slide 4">
					<div class="carousel-caption">
						<h3></h3>
						<p></p>
					</div>
				</div>
			</div>

			<!-- Left and right controls -->
			<a class="left carousel-control" href="#myCarousel" role="button"
				data-slide="prev"> <span
				class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				<span class="sr-only">Previous</span>
			</a> <a class="right carousel-control" href="#myCarousel" role="button"
				data-slide="next"> <span
				class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				<span class="sr-only">Next</span>
			</a>
		</div>

	</div>


	<div id="main-wrapper" class="container-fluid">
		<div class="row" id="tools-div" style="margin: 0 auto;">
			<div class="col-md-8 discussion-sidebar">
				<h3 class="text-danger"
					style="text-align: center; margin-top: 2%; margin-bottom: 2%; font-weight: bolder;">Find
					the right workshop for you</h3>
				<form action="<c:url value='/sm/open/search-for-workshop'/>"
					role="form" class="form-horizontal">
					<c:if test="${indexWorkshopFinderError}">
						<div class="alert alert-danger">
							<c:out value="${indexWorkshopFinderErrorMsg}" />
						</div>
					</c:if>
					<div class="form-group">
						<label class="control-label col-sm-2" for="industry"
							style="font-family: calibri; padding: 0px">Current
							Industry:</label>
						<div class="col-sm-4">
							<select class="form-control" name="industry">
								<option class="placeholder" selected disabled value="">Select
									Industry</option>
								<option>Consumer Sales</option>
								<option>Financial Services</option>
								<option>Healthcare</option>
								<option>Manufacturing</option>
								<option>Oil & Gas</option>
								<option>Professional Services</option>
								<option>Telecomms</option>
							</select>
						</div>
						<label class="control-label col-sm-2" for="role"
							style="font-family: calibri; padding: 0px">Job Role:</label>
						<div class="col-sm-4">
							<select class="form-control" name="job-role">
								<%@ include file="/partials/job-roles.html"%>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="experience"
							style="font-family: calibri; padding: 0px">Years of
							Experience:</label>
						<div class="col-sm-4">
							<input class="form-control" type="number" min="0"
								name="experience-no" placeholder="Number of years">
						</div>
						<label class="control-label col-sm-2" for="role"
							style="font-family: calibri; padding: 0px">Workshop
							Location:</label>
						<div class="col-sm-4">
							<select class="form-control" name="location">
								<option class="placeholder" selected disabled value="">Select
									Location</option>
								<option>Abuja</option>
								<option>Lagos</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="date"
							style="font-family: calibri; padding: 0px">Date:</label>
						<div class="col-sm-4">
							<input type="text" class="form-control date-picker" name="date"
								placeholder="mm/dd/yyyy">
						</div>
						<label class="control-label col-xs-4 col-sm-2" for="role"
							style="font-family: calibri; padding-right: 0px">Type:</label>
						<div class="col-xs-8 col-sm-4">
							<label class="checkbox-inline"><input type="checkbox"
								value="free" name="type">Free</label> <label
								class="checkbox-inline"><input type="checkbox"
								value="paid" name="type">Paid</label>
						</div>
					</div>
					<div class="form-group" style="text-align: center">
						<input style="width: 60%;" type="submit"
							class="btn btn-lg btn-danger" value="Find Workshop">
					</div>
				</form>
			</div>
			<div class="hidden-xs hidden-sm col-md-4">
				<div style="position: relative;">
					<iframe src="/images/zoho-crm/index.html" width="336" height="280"
						scrolling="no" frameBorder='0'></iframe>
					<a href="/sm/open/sales-and-marketing-automation#crm"
						style="position: absolute; top: 0; left: 0; display: inline-block; width: 336px; height: 280px; z-index: 5;"></a>
				</div>

			</div>
		</div>

		<div id="featured-workshops-div" class="alt-div row">
			<h2 class="text-muted">Featured Workshops</h2>
			<div class="col-sm-10 col-centered" id="slick">

				<div class="featured-workshop col-sm-3"
					style="background-color: #337ab7;">
					<div>
						<h3>
							<a
								href="<c:url value='/sm/open/workshop-information?id=PSS-PB-104'/>">Prospecting
								Bootcamp</a>
						</h3>
					</div>
					<div>
						<p>
							<a
								href="<c:url value='/sm/open/workshop-information?id=PSS-PB-104'/>">View
								Course &#8594;</a>
						</p>
					</div>
				</div>


				<div class="featured-workshop col-sm-3"
					style="background-color: #5bc0de">
					<div>
						<h3>
							<a
								href="<c:url value='/sm/open/workshop-information?id=PSS-SCM-103'/>">Sales-Call
								Management</a>
						</h3>
					</div>
					<div>
						<p>
							<a
								href="<c:url value='/sm/open/workshop-information?id=PSS-SCM-103'/>">View
								Course &#8594;</a>
						</p>
					</div>
				</div>


				<div class="featured-workshop col-sm-3"
					style="background-color: #5cb85c;">
					<div>
						<h3>
							<a
								href="<c:url value='/sm/open/workshop-information?id=TEAM-SAM-201'/>">Strategic
								Account Management</a>
						</h3>
					</div>
					<div>
						<p>
							<a
								href="<c:url value='/sm/open/workshop-information?id=TEAM-SAM-201'/>">View
								Course &#8594;</a>
						</p>
					</div>
				</div>


				<div class="featured-workshop col-sm-3"
					style="background-color: #f0ad4e">
					<div>
						<h3>
							<a
								href="<c:url value='/sm/open/workshop-information?id=TEAM-DIW-202'/>">Developing
								& Implementing Winning Strategic Account Plans</a>
						</h3>
					</div>
					<div>
						<p>
							<a
								href="<c:url value='/sm/open/workshop-information?id=TEAM-DIW-202'/>">View
								Course &#8594;</a>
						</p>
					</div>
				</div>



				<div class="featured-workshop col-sm-3"
					style="background-color: #d9534f">
					<div>
						<h3>
							<a
								href="<c:url value='/sm/open/workshop-information?id=PSS-PSB-101'/>">Professional
								Selling Bootcamp</a>
						</h3>
					</div>
					<div>
						<p>
							<a
								href="<c:url value='/sm/open/workshop-information?id=PSS-PSB-101'/>">View
								Course &#8594;</a>
						</p>
					</div>
				</div>

				<div class="featured-workshop col-sm-3"
					style="background-color: #337ab7;">
					<div>
						<h3>
							<a
								href="<c:url value='/sm/open/workshop-information?id=PSS-SPM-102'/>">Sales
								Pipeline Management Bootcamp</a>
						</h3>
					</div>
					<div>
						<p>
							<a
								href="<c:url value='/sm/open/workshop-information?id=PSS-SPM-102'/>">View
								Course &#8594;</a>
						</p>
					</div>
				</div>

				<div class="featured-workshop col-sm-3"
					style="background-color: #5bc0de">
					<div>
						<h3>
							<a
								href="<c:url value='/sm/open/workshop-information?id=TEAM-SOM-203'/>">Sales
								Opportunity Management</a>
						</h3>
					</div>
					<div>
						<p>
							<a
								href="<c:url value='/sm/open/workshop-information?id=PSS-SPM-102'/>">View
								Course &#8594;</a>
						</p>
					</div>
				</div>

				<div class="featured-workshop col-sm-3"
					style="background-color: #5cb85c">
					<div>
						<h3>
							<a
								href="<c:url value='/sm/open/workshop-information?id=NG-SSN-101'/>">Situational
								Sales Negotiation</a>
						</h3>
					</div>
					<div>
						<p>
							<a
								href="<c:url value='/sm/open/workshop-information?id=NG-SSN-101'/>">View
								Course &#8594;</a>
						</p>
					</div>
				</div>

				<div class="featured-workshop col-sm-3"
					style="background-color: #f0ad4e">
					<div>
						<h3>
							<a
								href="<c:url value='/sm/open/workshop-information?id=NG-NPT-201'/>">Negotiating
								Beyond Price & Tactical Approaches</a>
						</h3>
					</div>
					<div>
						<p>
							<a
								href="<c:url value='/sm/open/workshop-information?id=NG-NPT-201'/>">View
								Course &#8594;</a>
						</p>
					</div>
				</div>

				<div class="featured-workshop col-sm-3"
					style="background-color: #d9534f">
					<div>
						<h3>
							<a
								href="<c:url value='/sm/open/workshop-information?id=RM-SSE-201'/>">Selling
								to Senior Executives</a>
						</h3>
					</div>
					<div>
						<p>
							<a
								href="<c:url value='/sm/open/workshop-information?id=RM-SSE-201'/>">View
								Course &#8594;</a>
						</p>
					</div>
				</div>

				<div class="featured-workshop col-sm-3"
					style="background-color: #337ab7">
					<div>
						<h3>
							<a
								href="<c:url value='/sm/open/workshop-information?id=RM-RM-101'/>">Relationship
								Marketing</a>
						</h3>
					</div>
					<div>
						<p>
							<a
								href="<c:url value='/sm/open/workshop-information?id=RM-RM-101'/>">View
								Course &#8594;</a>
						</p>
					</div>
				</div>

				<div class="featured-workshop col-sm-3"
					style="background-color: #5bc0de">
					<div>
						<h3>
							<a
								href="<c:url value='/sm/open/workshop-information?id=RM-TS-102'/>">Trust
								Selling</a>
						</h3>
					</div>
					<div>
						<p>
							<a
								href="<c:url value='/sm/open/workshop-information?id=RM-TS-102'/>">View
								Course &#8594;</a>
						</p>
					</div>
				</div>

				<div class="featured-workshop col-sm-3"
					style="background-color: #5cb85c">
					<div>
						<h3>
							<a
								href="<c:url value='/sm/open/workshop-information?id=RM-CS-202'/>">Consultative
								Selling</a>
						</h3>
					</div>
					<div>
						<p>
							<a
								href="<c:url value='/sm/open/workshop-information?id=RM-CS-202'/>">View
								Course &#8594;</a>
						</p>
					</div>
				</div>

				<div class="featured-workshop col-sm-3"
					style="background-color: #f0ad4e">
					<div>
						<h3>
							<a
								href="<c:url value='/sm/open/workshop-information?id=PS-DWS-101'/>">Developing
								A Winning Sales Presentation</a>
						</h3>
					</div>
					<div>
						<p>
							<a
								href="<c:url value='/sm/open/workshop-information?id=PS-DWS-101'/>">View
								Course &#8594;</a>
						</p>
					</div>
				</div>

				<div class="featured-workshop col-sm-3"
					style="background-color: #d9534f">
					<div>
						<h3>
							<a
								href="<c:url value='/sm/open/workshop-information?id=PS-PSE-201'/>">Presenting
								To Senior Executives</a>
						</h3>
					</div>
					<div>
						<p>
							<a
								href="<c:url value='/sm/open/workshop-information?id=PS-PSE-201'/>">View
								Course &#8594;</a>
						</p>
					</div>
				</div>

				<div class="featured-workshop col-sm-3"
					style="background-color: #337ab7">
					<div>
						<h3>
							<a
								href="<c:url value='/sm/open/workshop-information?id=SM-SPM-201'/>">Sales
								Pipeline Management</a>
						</h3>
					</div>
					<div>
						<p>
							<a
								href="<c:url value='/sm/open/workshop-information?id=SM-SPM-201'/>">View
								Course &#8594;</a>
						</p>
					</div>
				</div>

				<div class="featured-workshop col-sm-3"
					style="background-color: #5bc0de">
					<div>
						<h3>
							<a
								href="<c:url value='/sm/open/workshop-information?id=SM-CSP-202'/>">Coaching
								Sales People For Performance</a>
						</h3>
					</div>
					<div>
						<p>
							<a
								href="<c:url value='/sm/open/workshop-information?id=SM-CSP-202'/>">View
								Course &#8594;</a>
						</p>
					</div>
				</div>

				<div class="featured-workshop col-sm-3"
					style="background-color: #5cb85c">
					<div>
						<h3>
							<a
								href="<c:url value='/sm/open/workshop-information?id=SM-IHT-203'/>">Interviewing,
								Hiring & Onboarding Top Sales Talent</a>
						</h3>
					</div>
					<div>
						<p>
							<a
								href="<c:url value='/sm/open/workshop-information?id=SM-IHT-203'/>">View
								Course &#8594;</a>
						</p>
					</div>
				</div>

				<div class="featured-workshop col-sm-3"
					style="background-color: #f0ad4e">
					<div>
						<h3>
							<a
								href="<c:url value='/sm/open/workshop-information?id=SM-ISC-204'/>">Introduction
								To Sales Compensation</a>
						</h3>
					</div>
					<div>
						<p>
							<a
								href="<c:url value='/sm/open/workshop-information?id=SM-ISC-204'/>">View
								Course &#8594;</a>
						</p>
					</div>
				</div>
				<div class="featured-workshop col-sm-3"
					style="background-color: #d9534f">
					<div>
						<h3>
							<a
								href="<c:url value='/sm/open/workshop-information?id=CDS-ICS-101'/>">Introduction
								to Indirect Channel Sales</a>
						</h3>
					</div>
					<div>
						<p>
							<a
								href="<c:url value='/sm/open/workshop-information?id=CDS-ICS-101'/>">View
								Course &#8594;</a>
						</p>
					</div>
				</div>
				<div class="featured-workshop col-sm-3"
					style="background-color: #337ab7">
					<div>
						<h3>
							<a
								href="<c:url value='/sm/open/workshop-information?id=CDS-ISP-102'/>">Introduction
								to Sales Promotion</a>
						</h3>
					</div>
					<div>
						<p>
							<a
								href="<c:url value='/sm/open/workshop-information?id=CDS-ISP-102'/>">View
								Course &#8594;</a>
						</p>
					</div>
				</div>
				<div class="featured-workshop col-sm-3"
					style="background-color: #5bc0de">
					<div>
						<h3>
							<a
								href="<c:url value='/sm/open/workshop-information?id=CDS-RS-103'/>">Retail
								Selling</a>
						</h3>
					</div>
					<div>
						<p>
							<a
								href="<c:url value='/sm/open/workshop-information?id=CDS-RS-103'/>">View
								Course &#8594;</a>
						</p>
					</div>
				</div>

				<div class="featured-workshop col-sm-3"
					style="background-color: #5cb85c">
					<div>
						<h3>
							<a
								href="<c:url value='/sm/open/workshop-information?id=TTS-TB-101'/>">The
								Telesales Bootcamp</a>
						</h3>
					</div>
					<div>
						<p>
							<a
								href="<c:url value='/sm/open/workshop-information?id=TTS-TB-101'/>">View
								Course &#8594;</a>
						</p>
					</div>
				</div>

				<div class="featured-workshop col-sm-3"
					style="background-color: #f0ad4e">
					<div>
						<h3>
							<a
								href="<c:url value='/sm/open/workshop-information?id=TTS-SSW-201'/>">Solution
								Selling Workshop</a>
						</h3>
					</div>
					<div>
						<p>
							<a
								href="<c:url value='/sm/open/workshop-information?id=TTS-SSW-201'/>">View
								Course &#8594;</a>
						</p>
					</div>
				</div>

				<div class="featured-workshop col-sm-3"
					style="background-color: #d9534f">
					<div>
						<h3>
							<a
								href="<c:url value='/sm/open/workshop-information?id=TTS-TST-102'/>">Technology
								Sales Training Workshop</a>
						</h3>
					</div>
					<div>
						<p>
							<a
								href="<c:url value='/sm/open/workshop-information?id=TTS-TST-102'/>">View
								Course &#8594;</a>
						</p>
					</div>
				</div>


			</div>
		</div>




		<div id="categories" class="row"
			style="margin: 0 auto; margin-top: 2%;">
			<h2 class="well text-primary" style="text-align: center;">Check
				out our collection of sales capability development workshops</h2>
			<h3 style="text-align: center; margin-bottom: 2%;">Whatever your
				job type, as long as you are in sales, we've got you covered.</h3>
			<div class="col-sm-10 col-centered">
				<div class="col-sm-6 col-md-4">
					<div class="thumbnail index-mod">
						<a
							href="<c:url value='/sm/open/get-workshop-category?categoryName=bootcamp'/>"><img
							class="img-responsive module-image" src="/images/boot.jpg" alt=""></a>
						<div class="caption" style="text-align: center">
							<h4>(14)</h4>
							<p class="module-desc"></p>
						</div>
					</div>
				</div>
				<div class="col-sm-6 col-md-4">
					<div class="thumbnail index-mod">
						<a
							href="<c:url value='/sm/open/get-workshop-category?categoryName=Channel and Distribution Sales'/>"><img
							class="img-responsive module-image"
							src="/images/channel-workshops.jpg"
							alt="Channel & Distribution Sales"></a>
						<div class="caption" style="text-align: center">
							<h4 class="circle">(3)</h4>
							<p class="module-desc"></p>
						</div>
					</div>
				</div>
				<div class="col-sm-6 col-md-4">
					<div class="thumbnail index-mod">
						<a
							href="<c:url value='/sm/open/get-workshop-category?categoryName=Negotiation'/>"><img
							class="img-responsive module-image"
							src="/images/negotiation-workshops.jpg" alt="Negotiation"></a>
						<div class="caption" style="text-align: center">
							<h4>(2)</h4>
							<p class="module-desc"></p>
						</div>
					</div>
				</div>
				<div class="col-sm-6 col-md-4">
					<div class="thumbnail index-mod">
						<a
							href="<c:url value='/sm/open/get-workshop-category?categoryName=Presentation'/>"><img
							class="img-responsive module-image"
							src="/images/presentation-workshops.jpg" alt="Presentation"></a>
						<div class="caption" style="text-align: center">
							<h4>(2)</h4>
							<p class="module-desc"></p>
						</div>
					</div>
				</div>
				<div class="col-sm-6 col-md-4">
					<div class="thumbnail index-mod">
						<a
							href="<c:url value='/sm/open/get-workshop-category?categoryName=Professional Selling Skills'/>"><img
							class="img-responsive module-image"
							src="/images/pss-workshops.jpg" alt="Professional Selling Skills"></a>
						<div class="caption" style="text-align: center;">
							<h4>(4)</h4>
							<p class="module-desc"></p>
						</div>
					</div>
				</div>
				<div class="col-sm-6 col-md-4">
					<div class="thumbnail index-mod">
						<a
							href="<c:url value='/sm/open/get-workshop-category?categoryName=Relationship Management'/>"><img
							class="img-responsive module-image"
							src="/images/relationship-management-workshops.jpg"
							alt="Relationship Management"></a>
						<div class="caption" style="text-align: center;">
							<h4>(4)</h4>
							<p class="module-desc"></p>
						</div>
					</div>
				</div>
				<div class="col-sm-6 col-md-4">
					<div class="thumbnail index-mod">
						<a
							href="<c:url value='/sm/open/get-workshop-category?categoryName=Sales Management'/>"><img
							class="img-responsive module-image"
							src="/images/sales-management-workshops.jpg"
							alt="Sales Management"></a>
						<div class="caption" style="text-align: center">
							<h4>(4)</h4>
							<p class="module-desc"></p>
						</div>
					</div>
				</div>
				<div class="col-sm-6 col-md-4">
					<div class="thumbnail index-mod">
						<a
							href="<c:url value='/sm/open/get-workshop-category?categoryName=Training For Every Account Manager'/>"><img
							class="img-responsive module-image"
							src="/images/t_e_a_m_workshops.jpg"
							alt="Training For Every Account Manager"></a>
						<div class="caption" style="text-align: center">
							<h4>(3)</h4>
							<p class="module-desc"></p>
						</div>
					</div>
				</div>
				<div class="col-sm-6 col-md-4">
					<div class="thumbnail index-mod">
						<a
							href="<c:url value='/sm/open/get-workshop-category?categoryName=Technology and Telco Sales'/>"><img
							class="img-responsive module-image"
							src="/images/technology_telecoms_sales_workshops.jpg"
							alt="Technology & Telco Sales"></a>
						<div class="caption" style="text-align: center">
							<h4>(3)</h4>
							<p class="module-desc"></p>
						</div>
					</div>
				</div>

			</div>
		</div>
		<div id="ad-2" class="row" style="background-color: #484848">
			<a href="/sm/open/get-workshop-category?categoryName=Negotiation"><img
				alt="Workshop" src="/images/salesmaxx-banner-sales.jpg"
				class="img img-responsive" style="width: 100%; margin: 0 auto;" /></a>
		</div>

		<div class="row" style="width: 80%; margin: 0 auto; margin-top: 2%;">
			<div class="col-sm-6 col-md-4">
				<a
					href='<c:out value='/sm/open/search-for-workshop?format=online' />'><img
					style="margin-top: 2%;" alt="Download" src="/images/e-learning.jpg"
					class="img-responsive" /></a>
			</div>
			<div class="hidden-xs hidden-sm col-md-4">
				<div style="position: relative;">
					<iframe src="/images/zoho-social/index.html" width="336"
						height="255" scrolling="no" frameBorder='0'></iframe>
					<a href="/sm/open/sales-and-marketing-automation#social"
						style="position: absolute; top: 0; left: 0; display: inline-block; width: 336px; height: 280px; z-index: 5;"></a>
				</div>
			</div>
			<div class="col-sm-6 col-md-4">
				<a href="/calendar"><img alt="Download Whitepaper"
					style="margin-top: 2%;" src="/images/calender.jpg"
					class="img-responsive" /></a>
			</div>
		</div>

	</div>

	<!-- Modal -->
	<div id="myModal" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content" id="grad">
				<div class="modal-header" style="border-bottom: none;">
					<button type="button" class="close" data-dismiss="modal">&times;</button>

				</div>
				<div class="modal-body">
					<h2 id="modal-header">You are moments away from a sales
						solution that delivers real business results...REVENUE!</h2>
					<p
						style="margin: 4%; text-align: center; font-size: 14pt; font-weight: bold;">Just
						answer a few questions and we will post you to the right solution
						to deliver sales revenue now.</p>
					<br />
					<div style="text-align: center;">
						<a href="/sm/open/solutions" class="btn btn-info btn-lg">Get
							Started</a>
					</div>

				</div>

			</div>

		</div>
	</div>
	<!-- <div id="bootcamp-ad-div" class="row"
		style="background-color: #b1010c; margin-top: 3%;">
		<img alt="Bootcamp" src="/images/bootcamp.png"
			class="img img-responsive" style="width: 80%; margin: 0 auto;" />
	</div> -->
	<div style="margin-top: 2%;">
		<%@ include file="/WEB-INF/footer.html"%>
	</div>
	<script src="/js/jquery-1.11.2.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>

	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	<script type="text/javascript" src="/js/slick.min.js"></script>
	<script src="/js/jquery.webui-popover.min.js"></script>
	<script type="text/javascript" src="/js/waitMe.js"></script>
	<script type="text/javascript" src="/js/modules.js"></script>
	<script type="text/javascript" src="/js/index.js"></script>
	<script type="text/javascript" src="/js/validate.js"></script>
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
			$("#home-menu").addClass("menu-active");
			var x = sessionStorage.getItem("nShown");
			if (x) {
				x = false;
			} else {
				x = true;
			}

			$(this).scroll(function() {
				if ($(this).scrollTop() > 500 && x) {
					$("#myModal").modal("show");
					sessionStorage.setItem("nShown", false);
					x = false;
				}
			});

		});
	</script>

</body>
</html>