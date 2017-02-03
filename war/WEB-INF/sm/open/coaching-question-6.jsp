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
	font-size: 14pt;
}

.inner {
	height: 6em;
	text-align: left;
	padding: 2% 4%
}
</style>
<body>
	<%@ include file="/WEB-INF/main-nav"%>
	<%@ include file="/WEB-INF/sm/open/sub-nav"%>
	<div class="container"
		style="width: 80%; margin: 0 auto; height: 100%;">
		<div class="row">

			<div class="col-md-12" style="padding-left: 0px; text-align: center">

				<h2 style="text-shadow: 0 0 3px #3b5998;">This is my</h2>
				<br>
				<form action="/sm/open/solution/crazy-servlet">
					<input value="This is my" name="question" type="hidden" >
					<input value="This is my" name="group" type="hidden" >
					<div class="row">
						<div
							style="margin: 2%; background-color: #3b987a; color: white; text-align: left; padding: 2%; font-size: 12pt"
							class="card inner">
							<div class="col-sm-3">
								<div class="radio">
									<label><input type="radio" value="First Interview"
										name="answer">First Interview</label>
								</div>
							</div>
							<div class="col-sm-3">
								<div class="radio">
									<label><input class="category" type="radio"
										value="Second Interview" name="answer">Second
										Interview</label>
								</div>
							</div>
							<div class="col-sm-3">
								<div class="radio">
									<label><input class="category" type="radio"
										value="Third Inteview" name="answer">Third Interview</label>
								</div>
							</div>
							<div class="col-sm-3">
								<div class="radio">
									<label><input class="category" type="radio"
										value="Fourth Interview" name="answer">Fourth
										Interview</label>
								</div>
							</div>
						</div>

					</div>

					<br>
					<div class="row" style="text-align: center">
						<div class="col-sm-12">
							<button type="submit" class="btn btn-info btn-lg">Continue</button>
						</div>
					</div>
				</form>

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