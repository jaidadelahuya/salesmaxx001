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
</head>
<body>
	<%@ include file="/WEB-INF/main-nav"%>
	<%@ include file="/WEB-INF/sm/open/sub-nav"%>
	<div class="container-fluid"
		style="width: 80%; margin: 0 auto; height: 100%;">
		<div class="row">

			<div class="col-md-8">
				<div class="panel panel-default col-md-12" style="padding: 0">
					<div class='section-heading'>
						<ul class="breadcrumb" style="">
							<li><a href="<c:url value='/index'/>">Home</a></li>
							<li><a href='<c:url value="/coaching" />'>Coaching</a></li>
							<li><a href="#"><c:out value="${coachingCategory}" /></a></li>
							<li class="active"><c:out value="New Discussion" /></li>
						</ul>
					</div>
					<div class="col-md-12"padding-top: 5%;">
						<h3 class="text-primary">
							<c:out value="New Question/Discussion" />
						</h3>
						<h5 class="text-muted">
							<strong class="text-danger">Category: </strong>
							<c:out value="${coachingCategory}" />
						</h5>
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
						<form action="/sm/closed/submit-discussion" method="post" id="post-discussion-form" >
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
									<textarea rows="8" class="form-control" name="body"></textarea>
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

			<div class="col-md-4" style="padding: 0">
				<div style="margin-bottom: 4%">
					<a href='/calendar'><img class="img img-responsive"
						src="/images/calender.jpg" alt="Calendar" /></a>
				</div>

				<div style="margin-bottom: 4%">
					<a><img class="img img-responsive"
						src="/images/download-howtobuy.jpg"
						alt="How to buy sales training" /></a>
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
	<script type="text/javascript" src="/js/modules.js"></script>
	<script type="text/javascript" src="/js/waitMe.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".main-menu-item").removeClass("active");
		});
	</script>
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