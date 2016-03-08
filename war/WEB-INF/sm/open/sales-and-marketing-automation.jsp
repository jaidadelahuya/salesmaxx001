<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sales and Marketing Automation</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="/style/bootstrap.min.css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" type="text/css"
	href="/style/jquery.webui-popover.min.css">
<link rel="stylesheet" type="text/css" href="/style/waitMe.css">
<link rel="stylesheet" type="text/css" href="/style/index.css">
<link rel="stylesheet" type="text/css" href="/style/custom.css">
<link rel="stylesheet" type="text/css" href="/style/main-style.css">
<link rel="stylesheet" type="text/css"
	href="/style/bootstrap-rating.css">
<style type="text/css">
.template-header {
	padding-top: 2%;
	padding-bottom: 2%;
	margin-bottom: 0px;
	cursor: pointer;
}

.zoho-img {
	margin: 0 auto;
	margin-bottom: 2%;
	margin-top: 2%;
}

.bullet-points {
	font-size: 10pt;
	font-family: arial
}


</style>

</head>


<body>
	<%@ include file="/WEB-INF/main-nav"%>
	<%@ include file="/WEB-INF/sm/open/sub-nav"%>
	<div class="container-fluid"
		style="width: 80%; margin: 0 auto; height: 100%;">
		<div class="row">
			<div class="col-md-9" style="padding-left: 0px">
				<%@ include
					file="/WEB-INF/sales-and-marketing-automation-content.html"%>
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
					<h4><a href="/coaching">Coaching Categories</a></h4>
					<ul>
						<li><a
							href="/sm/open/view-all-discussion?category=Interview Coaching">Interview
								Coaching</a></li>
						<li><a
							href="/sm/open/view-all-discussion?category=Excecutive Coaching">Executive
								Coaching</a></li>
						<li><a
							href="/sm/open/view-all-discussion?category=Sales Performance Coaching">Sales
								Performance Coaching</a></li>
					</ul>
				</div>
				
				<div class="discussion-sidebar">
					<h4><a href="/calendar">Workshops by Month</a></h4>
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
			
				<div style="margin-bottom: 4%">
					<a><img class="img img-responsive"
						src="/images/download-howtobuy.jpg"
						alt="How to buy sales training" /></a>
				</div>

				<div style="margin-bottom: 4%">
					<a href='href=' /sm/open/search-for-workshop?format=online''><img
						class="img img-responsive" src="/images/e-learning.jpg"
						alt="online courses" /></a>
				</div>

			</div>
		</div>

	</div>
	

	<script src="/js/jquery-1.11.2.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script src="/js/jquery-ui.min.js"></script>
	<script src="/js/jquery.webui-popover.min.js"></script>
	<script type="text/javascript" src="/js/waitMe.js"></script>
	<script type="text/javascript" src="/js/bootstrap-datepicker.min.js"></script>
	<script type="text/javascript" src="/js/waitMe.js"></script>
	<script type="text/javascript" src="/js/validate.js"></script>
	<script type="text/javascript" src="/js/modules.js"></script>
	<script type="text/javascript" src="/js/bootstrap-rating.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".main-menu-item").removeClass("active");
			$("#tools-menu").addClass("active");
		});

		$(".template-div").click(function() {
			$(this).find("ul").slideToggle();
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