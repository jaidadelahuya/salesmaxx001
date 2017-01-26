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
</head>
<body>
	<%@ include file="/WEB-INF/main-nav"%>
	<%@ include file="/WEB-INF/sm/open/sub-nav"%>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div style="width: 80%; margin: 0 auto">
					<h3 class="text-primary" style="font-family: arial; font-weight: bold;">
						<c:out value="Start a New Discussion" />: <small class="text-danger"><c:out value="${coachingCategory}" /></small>
					</h3>
					<div class="discussion-sidebar"
						style="padding: 0; background-color: #d9edfe;">
						<div class="form-group col-sm-12"
							style="text-align: right; margin-bottom: 0px; margin-top: 2%;">

						</div>

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
									<div class="form-group col-sm-12 col-md-10">
										<label for="title">Title:</label> <input type="text"
											class="form-control" name="title">
									</div>
									<div class="col-md-2"></div>
								</div>
								<div class="row">
									<div class="form-group col-sm-12 col-md-10">
										<label for="Body">Body:</label>
										<textarea id="discussion-msg" rows="8" class="form-control"
											name="body"></textarea>
									</div>
									<div class="col-md-2"></div>
								</div>
								<div class="row">
									<div class="form-group col-sm-12 col-md-10">
										<label for="Tags">Tags:</label> <input type="text"
											class="form-control" name="tags">
									</div>
									<div class="col-md-2"></div>
								</div>
								<input type="hidden" value="${coachingCategory}" name="category">
								<div class="row form-group">
									<div class="col-sm-12 col-md-4">
										<label for="privacy">Privacy: </label> <select
											class="form-control" name="privacy">
											<option>Public</option>
											<option>Private</option>
										</select>
									</div>
									<div class="checkbox col-sm-12 col-md-8"
										style="font-family: calibri">
										<label><input type="checkbox" value="true"
											name="notify-me">Send me new responses to my post via
											email</label>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-sm-12">
										<input type="submit" class="btn btn-xs btn-danger"
											value="Post Question/Discussion" id="post-discussion">
									</div>
								</div>
							</form>
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