<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/WEB-INF/custom-functions.tld" prefix="pn"%>
<!DOCTYPE html>
<html style="height: 100%;">
<head>
<meta charset="ISO-8859-1">
<title>Start a discussion</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="/style/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/style/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="/style/waitMe.css">
<link rel="stylesheet" type="text/css" href="/style/index.css">
<link rel="stylesheet" type="text/css" href="/style/custom.css">
<link rel="stylesheet" type="text/css" href="/style/main-style.css">
<%@ include file="/js/google-analytics"%>
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

.inner {
	height: 22em;
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/main-nav"%>
	<%@ include file="/WEB-INF/sm/open/sub-nav"%>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<h2 style="text-shadow: 0 0 3px #3b5998; text-align: center">Tell
					us what challenge you are having?</h2>
				<div class="col-md-offset-2 col-md-8">
					<div class="panel panel-default">

						<div class="panel-body" style="background-color: lavender">
							<div class="col-sm-12">
								<c:choose>
									<c:when test="${startDiscussionError}">
										<div class="alert alert-danger">
											<p>
												<c:out value='${startDiscussionErrorMessage}' />
											</p>
										</div>

									</c:when>
									<c:when test="${startDiscussionSuccess}">
										<div class="alert alert-success">
											<p>
												<c:out value='${startDiscussionSuccessMessage}' />
											</p>
										</div>

									</c:when>
								</c:choose>
								<form action="/sm/closed/submit-discussion" method="post"
									id="post-discussion-form">
									<div class="row">
										<div class="form-group col-sm-12">
											<label  for="title">Title:</label> <input type="text"
												class="form-control" name="title">
										</div>

									</div>
									<div class="row">
										<div class="form-group col-sm-12">
											<label  for="Body">Body:</label>
											<textarea id="discussion-msg" rows="8" class="form-control"
												name="body"></textarea>
										</div>

									</div>
									<p class="text-muted;"
										style="font-family: calibri; font-size: 10pt; text-align: left">This
										will be featured as a post on our coaching board and other
										members will be able to view and offer advice via comments.</p>

									
									<div class="row">
										<div class="col-md-12" style="font-size: 10pt">
										<h4 style="text-shadow: 0 0 3px #3b5998;">Options</h4>
										<label class="checkbox-inline" style="margin-left: 10px"><input type="checkbox" name="anonymous"
											value="true">Post as anonymous</label> <label class="checkbox-inline"><input name="privacy"
											type="checkbox" value="true">Make Private <span style="color: red; font-size: 8pt;"> (Login Required)</span></label> <label
											class="checkbox-inline"><input type="checkbox" name="notify-me"
											value="true">Send me response via email<span style="color: red; font-size: 8pt"> (Login Required)</span></label></div>
									</div>
									<br>
									<div class="row">
										<div class="form-group col-sm-12">
											<input type="submit" class="btn btn-sm btn-info"
												value="Submit" id="post-discussion">
										</div>
									</div>
								</form>
							</div>

						</div>
					</div>
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
	<script src="//cdn.tinymce.com/4/tinymce.min.js"></script>
	<script type="text/javascript" src="/js/modules.js"></script>
	<script type="text/javascript" src="/js/waitMe.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".main-menu-item").removeClass("active");
			$("#tools-menu").addClass("active");
			tinymce.init({
				selector : '#discussion-msg'
			});
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