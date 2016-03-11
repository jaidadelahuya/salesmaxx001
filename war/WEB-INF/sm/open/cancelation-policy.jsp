<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/WEB-INF/custom-functions.tld" prefix="pn"%>
<%@page import="com.salesmaxx.entities.UserRole"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cancellation Policy</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="/style/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/style/waitMe.css">
<link rel="stylesheet" type="text/css" href="/style/index.css">
<link rel="stylesheet" type="text/css" href="/style/custom.css">
<link rel="stylesheet" type="text/css" href="/style/main-style.css">
<body>
	<%@ include file="/WEB-INF/main-nav"%>
	<%@ include file="/WEB-INF/sm/open/sub-nav"%>
	<div class="container-fluid"
		style="width: 80%; margin: 0 auto; height: 100%;">
		<div class="row">

			<div class="col-md-9" style="padding-left: 0px">
				<%@ include file="/cancellation-policy.html"%>
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
			</div>
		</div>

	</div>
	<div style="margin-top: 2%;">
		<%@ include file="/WEB-INF/footer.html"%>
	</div>
	<script src="/js/jquery-1.11.2.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	<script type="text/javascript" src="/js/waitMe.js"></script>
	<script type="text/javascript" src="/js/modules.js"></script>
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