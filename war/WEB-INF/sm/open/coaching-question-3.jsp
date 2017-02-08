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
	margin-top: 1%;
}

.radio {
	font-size: 12pt;
	font-weight: bold;
}

.soln {
	font-size: 13pt;
	padding: 2% 4%;
}

.inner {
	height: 12.3em;
}
</style>
<body>
	<%@ include file="/WEB-INF/main-nav"%>
	<%@ include file="/WEB-INF/sm/open/sub-nav"%>
	<div class="container"
		style="width: 80%; margin: 0 auto; height: 100%;">
		<div class="row">
			<form  action="/sm/open/solution/crazy-servlet">
				<div class="col-md-12" style="padding-left: 0px; text-align: center">

					<h2 style="text-shadow: 0 0 3px #3b5998;">Which type of Sales
						Performance Coaching do you need?</h2>

					<input type="hidden" value="Sales Performance Type" name="group"> <input
						type="hidden" value="Which type of Sales Performance Coaching do you need?" name="question" /> <br>
					<div class="row">
						<div class="col-md-12">
							<div style="margin: 2%; background-color: #983b59; color: white; text-align: left; padding: 3%; font-size: 12pt"
								class="card inner">
								<div class="row">
									<div class="col-sm-4">
										<div class="radio">
											<label><input class="category" type="radio"
												value="Opportunity Pursuit" name="answer">Opportunity Pursuit</label>
										</div>
									</div>
									<div class="col-sm-4">
										<div class="radio">
											<label><input class="category" type="radio"
												value="Sales Call Management" name="answer">Sales Call Management</label>
										</div>
									</div>
									<div class="col-sm-4">
										<div class="radio">
											<label><input class="category" type="radio"
												value="Sales Proposal Development" name="answer">Sales Proposal Development</label>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-4">
										<div class="radio">
											<label><input class="category" type="radio"
												value="Funnel Management" name="answer">Funnel Management</label>
										</div>
									</div>
									<div class="col-sm-4">
										<div class="radio">
											<label><input class="category" type="radio"
												value="Sales Motivation" name="answer">Sales Motivation</label>
										</div>
									</div>
									<div class="col-sm-4">
										<div class="radio">
											<label><input class="category" type="radio" id="other" name="answer">Other</label>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12" id="other-div" style="text-align: left; display: none;">
										<div class="form-group">
											<label for="answer">Describe the type of coaching you need</label>
											<textarea rows="1" class="form-control" name="answer"></textarea>
										</div>
									</div>
								</div>
							</div>
						</div>

					</div>
					<br>
					<div class="row" style="text-align: center">
						<div class="col-sm-12">
							<button type="submit" id="continue" class="btn btn-info btn-lg">Continue</button>
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
	
	<script type="text/javascript">
		$(document).ready(function() {
			$(".category").click(function(e) {
				var x = $(this);
				var id = x.prop("id");
				var y = $("#other-div");
				var z = y.find('textarea');
				if(id=="other") {
					y.slideDown();
					
					$("#other").prop("disabled", true);
					
				}else {
					y.slideUp();
					
					$("#other").prop("disabled", false);
				}
			});
		});
	</script>



</body>
</html>