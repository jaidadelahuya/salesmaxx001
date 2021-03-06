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
	<div class="container"
		style="width: 80%; margin: 0 auto; height: 100%;">
		<div class="row">

			<div class="col-md-12" style="padding-left: 0px; text-align: center">

				<h2 style="text-shadow: 0 0 3px #3b5998;">What Kind of coaching
					do you need?</h2>
				<br>
				<form action="/sm/open/solution/crazy-servlet">
					
					<input value="What Kind of coaching do you need?" name="question" type="hidden" >
					<div class="row">
						<div class="col-sm-4">
							<div style="margin: 2%; background-color: #59983b; color: white;"
								class="card inner">
								<div class="radio">
									<label><input type="radio" value="Interview"
										name="answer"><strong>Interview</strong></label>

								</div>
								<p class="soln">Getting the right kind of help to prep for a
									sales job interview. Our coaches provide guidance on how to be
									successful at the interview stage and how to be productive the
									first 100-days of getting the role. Everything from your
									dressing to the right sales compensation. We are here to help.</p>
							</div>
						</div>
						<div class="col-sm-4">
							<div
								style="margin: 2%; background-color: #3b987a; color: white; margin-top: 8%"
								class="card inner">
								<div class="radio">
									<label><input type="radio" name="answer"
										value="Sales Performance"><strong>Sales
											Performance</strong></label>
								</div>
								<p class="soln">For that moment where you need a sounding
									board on your deal strategy or opportunity pursuit plan. Our
									well experienced coaches will help you develop and refine a
									focused plan to win. At other ties they will work with you to
									ensure your professional growth and career development if sales
									& marketing is your chosen career.</p>
							</div>
						</div>
						<div class="col-sm-4">
							<div style="margin: 2%; background-color: #3b5998; color: white;"
								class="card inner">
								<div class="radio">
									<label><input type="radio" name="answer"
										value="Sales Management"><strong>Sales
											Management / Leadership</strong></label>
								</div>
								<p class="soln">Whether you're faced with navigating the
									tough terrain of sales management or the leader challenged to
									align sales with your business strategy, you can stand on the
									shoulder of giants using this platform to gain leading insight
									& cutting edge ideas on most sales management issues. Our
									advise is based on experience & extensive research of what
									works.</p>
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