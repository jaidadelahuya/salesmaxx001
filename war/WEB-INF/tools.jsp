<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Tools</title>
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
<style type="text/css">
.template-header {
	padding-top: 2%;
	padding-bottom: 2%;
	margin-bottom: 0px;
	cursor: pointer;
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/main-nav"%>
	<%@ include file="/WEB-INF/sm/open/sub-nav"%>
	<div class="container-fluid" style="width: 80%; margin: 0 auto;">
		<div class="row">
			<div class="col-md-9" style="padding-left: 0px">
				<%@ include file="/WEB-INF/tools-content.html"%>
			</div>
			<div class="col-md-3" style="padding: 0">
				<div class="discussion-sidebar">
					<h4>Coaching Categories</h4>
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