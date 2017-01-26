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
	margin-top: 3%;
}

.radio {
	font-size: 15pt;
	font-weight: bold;
}

.soln {
	font-size: 13pt;
	padding: 2% 4%;
}

.inner {
	height: 12em;
}
</style>
<body>
	<%@ include file="/WEB-INF/main-nav"%>
	<%@ include file="/WEB-INF/sm/open/sub-nav"%>
	<div class="container"
		style="width: 80%; margin: 0 auto; height: 100%;">
		<div class="row">
			<form method="post" action="/coaching/get-coaching-preference">
				<div class="col-md-12" style="padding-left: 0px;text-align: center">
						<input type="hidden" value="pq2" name="redirect">
						<input type="hidden"
							value="experience" name="question" />
						<h2 style="text-shadow: 0 0 3px #3b5998;">How would you
							describe your skill level?</h2>
						<br>
						<div class="row">
							<div class="col-sm-4">
								<div style="margin: 2%; background-color: #3b5998; color: white"
									class="card inner">
									<div class="radio">
										<label><input value="foundation" type="radio" name="answer"><strong>Beginner</strong></label>

									</div>
									<p class="soln">0 - 2 years</p>
								</div>
							</div>
							<div class="col-sm-4">
								<div style="margin: 2%; background-color: #983b59; color: white"
									class="card inner">
									<div class="radio">
										<label><input value="intermediate" type="radio" name="answer"><strong>Intermediate</strong></label>

									</div>
									<p class="soln">3 - 7 years</p>
								</div>
							</div>
							<div class="col-sm-4">
								<div style="margin: 2%; background-color: #59983b; color: white"
									class="card inner">
									<div class="radio">
										<label><input value="advanced" type="radio" name="answer"><strong>Advanced</strong></label>

									</div>
									<p class="soln">More than 7 years</p>
								</div>
							</div>

						</div>
						<br>
						<div class="row" style="text-align: center">
							<div class="col-sm-12">
								<input type="submit" value="Continue"
									class="btn btn-info btn-lg">
							</div>
						</div>
				
				</div>
			</form>

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