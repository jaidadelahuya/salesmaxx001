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

.soln {
	font-size: 13pt;
	padding: 2% 4%;
}

.inner {
	height: 12em;
}

.industry {
	font-size: 12pt !important;
	text-align: left;
}
</style>
<body>
	<%@ include file="/WEB-INF/main-nav"%>
	<%@ include file="/WEB-INF/sm/open/sub-nav"%>
	<div class="container"
		style="width: 80%; margin: 0 auto; height: 100%;">
		<div class="row">

			<div class="col-md-12" style="padding-left: 0px">
				<form action="/sm/open/solution/crazy-servlet">
					<h2 style="text-shadow: 0 0 3px #3b5998; text-align: center">Which industry represents your organization best?</h2>
					<br>
					<input value="industry" name="group" type="hidden">
					<input value="industry" name="question" type="hidden" >
					<div class="row">
						<div class="col-sm-12">
							<div
								style="margin: 2%; background-color: #983b59; color: white; text-align: left; font-size: 12pt; padding-top: 4%"
								class="card inner">
								<div class="row">

									<div class="col-sm-3">
										<div class="radio">
											<label><input class="industry" type="radio" value="Consumer Sales"
												name="answer">Consumer Sales</label>
										</div>
									</div>
									<div class="col-sm-3">
										<div class="radio">
											<label><input class="industry" type="radio" value="Financial Services"
												name="answer">Financial Services</label>
										</div>
									</div>
									<div class="col-sm-3">
										<div class="radio">
											<label><input class="industry" type="radio" value="Health Care"
												name="answer">Health Care</label>
										</div>
									</div>
									<div class="col-sm-3">
										<div class="radio">
											<label><input class="industry" type="radio" value="Manufacturing"
												name="answer">Manufacturing</label>
										</div>
									</div>

								</div>

								<div class="row">

									<div class="col-sm-3">
										<div class="radio">
											<label><input class="industry" type="radio" value="Oil & Gas"
												name="answer">Oil & Gas</label>
										</div>
									</div>
									<div class="col-sm-3">
										<div class="radio">
											<label><input class="industry" type="radio" value="Professional Services"
												name="answer">Professional Services</label>
										</div>
									</div>
									<div class="col-sm-3">
										<div class="radio">
											<label><input class="industry" type="radio" value="Telecomms"
												name="answer">Telecomms</label>
										</div>
									</div>
									<div class="col-sm-3">
										<div class="radio">
											<label><input class="industry" type="radio" value="Other Sevices"
												name="answer">Other Sevices</label>
										</div>
									</div>

								</div>
							</div>

						</div>


					</div>
					<br>
					<div class="row" style="text-align: center">
						<div class="col-sm-12">
							<input value="Continue" type="submit" class="btn btn-info btn-lg">
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

	<script type="text/javascript">
		$(document).ready(function() {
			$("input[type=radio]").click(function() {
				var par = $(this).closest(".radio");
				var ma = par.find(".url");
				var x = ma.prop("value");
				$("#continue").prop("href", x);
			});
		});
	</script>


</body>
</html>