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
<title>SalesMaxx Solutions</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="/style/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/style/waitMe.css">
<link rel="stylesheet" type="text/css" href="/style/index.css">
<link rel="stylesheet" type="text/css" href="/style/custom.css">
<link rel="stylesheet" type="text/css" href="/style/main-style.css">
<style type="text/css">
.card {
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.19);
	text-align: center;
	padding: 2%;
	border-radius: 5px;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
}

input[type=radio] {
	margin-top: 2%;
}

.radio {
	font-size: 15pt;
	font-weight: bold;
}

.soln {
	font-size: 11pt;
	padding: 2% 4%;
	text-align: justify;
}

.inner {
	height: 22em;
}
</style>
<body>
	<%@ include file="/WEB-INF/main-nav"%>
	<%@ include file="/WEB-INF/sm/open/sub-nav"%>
	<div class="container">
		<div class="row">

			<div class="col-sm-9">
				<h3>Private Coaching Confirmation</h3>
				<div class="alert alert-success">Your request for a private
					coach has been received. One of our coaches will contact you within
					24 hrs via your email.</div>
				<div style="margin-top: 4%">
					<strong>View More :</strong> <span style="margin-right: 2%"><a
						href="/sm/open/view-all-discussion?category=Interview Coaching">Interview
							Coaching</a></span><span style="margin-right: 2%"><a
						href="/sm/open/view-all-discussion?category=Excecutive Coaching">Executive
							Coaching</a></span><span style="margin-right: 2%"><a
						href="/sm/open/view-all-discussion?category=Sales Performance Coaching">Sales Performance
							Coaching</a></span>
				</div>
			</div>
			<div class="col-sm-3">
				<div>
					<a href="/calendar"><img class="img img-responsive"
						alt="SalesMaxx Calendar" src="/images/calender.jpg"></a>
				</div>
				
			</div>


		</div>
		<br>


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