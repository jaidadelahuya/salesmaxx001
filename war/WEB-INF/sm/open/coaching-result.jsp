<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SalesMaxx Coaching</title>
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
<link rel="stylesheet" type="text/css"
	href="/style/font-awesome.min.css">

<%@ include file="/js/google-analytics"%>
</head>
<body>
	<%@ include file="/WEB-INF/main-nav"%>
	<%@ include file="/WEB-INF/sm/open/sub-nav"%>

	<div class="container">
		<div class="row">
			<div class="col-md-9">
				<div class="alert alert-success alert-dismissable">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<p>Your request for coaching has been recieved. One of our
						coaches will contact you shortly.</p>
					<p>Meanwhile, take your time to review the resources below</p>

				</div>

				<div class="panel panel-default">
					<div class="panel-body">
						<h2>Coaching sub category name</h2>
						<p>Lorem ipsum dolor sit amet, neque arcu vestibulum, orci
							eros cum arcu nisl pede dignissim. Erat mauris, rhoncus volutpat
							porta magnis, molestiae nam aut eu ut turpis in, ac dolor id ut
							odio. Elit mauris lorem augue id, curabitur inceptos eleifend
							volutpat arcu, netus bibendum.</p>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4>Podcast</h4>
					</div>
					<div class="panel-body">
						<div class="row" style="text-align: center">
							<div class="col-md-2">
								<img class="img img-responsive" alt="" src="/images/podcast.png">
								<p style="font-size: 9pt">Podcast name</p>
							</div>
							<div class="col-md-2">
								<img class="img img-responsive" alt="" src="/images/podcast.png">
								<p style="font-size: 9pt">Podcast name</p>
							</div>
							<div class="col-md-2">
								<img class="img img-responsive" alt="" src="/images/podcast.png">
								<p style="font-size: 9pt">Podcast name</p>
							</div>
							<div class="col-md-2">
								<img class="img img-responsive" alt="" src="/images/podcast.png">
								<p style="font-size: 9pt">Podcast name</p>
							</div>
						</div>
					</div>
				</div>

				<div class="panel panel-default">
					<div class="panel-heading">
						<h4>Books</h4>
					</div>
					<div class="panel-body">
						<div class="row" style="text-align: center">
							<div class="col-md-2">
								<img class="img img-responsive" alt="" src="/images/book.jpg"><br>
								<p style="font-size: 9pt">Book Name</p>
							</div>
							<div class="col-md-2">
								<img class="img img-responsive" alt="" src="/images/book.jpg"><br>
								<p style="font-size: 9pt">Book Name</p>
							</div>
							<div class="col-md-2">
								<img class="img img-responsive" alt="" src="/images/book.jpg"><br>
								<p style="font-size: 9pt">Book Name</p>
							</div>
							<div class="col-md-2">
								<img class="img img-responsive" alt="" src="/images/book.jpg"><br>
								<p style="font-size: 9pt">Book Name</p>
							</div>
							<div class="col-md-2">
								<img class="img img-responsive" alt="" src="/images/book.jpg"><br>
								<p style="font-size: 9pt">Book Name</p>
							</div>
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4>Articles</h4>
					</div>
					<div class="panel-body">
						<ul style="list-style: circle;">
							<li><a href="#">Lorem ipsum dolor sit amet, proin
									tincidunt a metus sagittis</a></li>
							<li><a href="#">Lorem ipsum dolor sit amet, proin
									tincidunt </a></li>
							<li><a href="#">Lorem ipsum dolor sit amet, proin
									tincidunt a metus</a></li>
							<li><a href="#">Lorem ipsum dolor sit amet, proin
									tincidunt a metus sagittis</a></li>
							<li><a href="#">Lorem ipsum dolor sit amet, proin </a></li>
						</ul>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4>Zoho Tools</h4>

					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-sm-2">
								<img style="width: 90%; margin: 0 auto"
									src="/images/motivator.png">
							</div>
							<div class="col-sm-10">
								<h5>
									<strong>Zoho Motivator</strong>
								</h5>
								<p>Lorem ipsum dolor sit amet, neque arcu vestibulum, orci
									eros cum arcu nisl pede dignissim. Erat mauris, rhoncus
									volutpat porta magnis, molestiae nam aut eu ut turpis in, ac
									dolor id ut odio. Elit mauris lorem augue id, curabitur
									inceptos eleifend volutpat arcu, netus bibendum</p>
							</div>
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4>Featured Workshops</h4>
					</div>
					<div class="panel-body">
						<div class="row" style="text-align: center">
							<div class="col-md-2">
								<img class="img img-responsive" alt=""
									src="http://127.0.0.1:8888/_ah/img/QMYO1GzfjnQ0f7xIzXmQ2Q"><br>
								<p style="font-size: 9pt">Workshop Name</p>
							</div>
							<div class="col-md-2">
								<img class="img img-responsive" alt=""
									src="http://127.0.0.1:8888/_ah/img/QMYO1GzfjnQ0f7xIzXmQ2Q"><br>
								<p style="font-size: 9pt">Workshop Name</p>
							</div>
							<div class="col-md-2">
								<img class="img img-responsive" alt=""
									src="http://127.0.0.1:8888/_ah/img/QMYO1GzfjnQ0f7xIzXmQ2Q"><br>
								<p style="font-size: 9pt">Workshop Name</p>
							</div>
							<div class="col-md-2">
								<img class="img img-responsive" alt=""
									src="http://127.0.0.1:8888/_ah/img/QMYO1GzfjnQ0f7xIzXmQ2Q"><br>
								<p style="font-size: 9pt">Workshop Name</p>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-3">
				<div class="discussion-sidebar">
					<h4>
						<a href="/sm/open/sales-and-marketing-templates">Sales &
							Marketing Templates</a>
					</h4>
					<div class="col-sm-12 no-padding-div" style="padding-bottom: 2%;">
						<div class="col-sm-2 no-padding-div">
							<a href="/sm/open/sales-and-marketing-templates?format=ms-wordl"><img
								alt="" src="/images/word.png" class="img img-responsive"></a>
						</div>
						<div class="col-sm-10" style="padding-top: 2%;">
							<a href="/sm/open/sales-and-marketing-templates?format=ms-word">MS-Word
								templates</a>
						</div>
					</div>
					<div class="col-sm-12 no-padding-div" style="padding-bottom: 2%;">
						<div class="col-sm-2 no-padding-div" style="padding-right: 0px">
							<a href="/sm/open/sales-and-marketing-templates?format=excel"><img
								alt="" src="/images/excel.png" class="img img-responsive"></a>
						</div>
						<div class="col-sm-10" style="padding-top: 2%;">
							<a href="/sm/open/sales-and-marketing-templates?format=excel">Excel
								templates</a>
						</div>
					</div>
					<div class="col-sm-12 no-padding-div" style="padding-bottom: 2%;">
						<div class="col-sm-2 no-padding-div" style="padding-right: 0px">
							<a
								href="/sm/open/sales-and-marketing-templates?format=power-point"><img
								alt="" src="/images/powerpoint.png" class="img img-responsive"></a>
						</div>
						<div class="col-sm-10" style="padding-top: 2%;">
							<a
								href="/sm/open/sales-and-marketing-templates?format=power-point">Power
								Point templates</a>
						</div>
					</div>

					<div class="col-sm-12 no-padding-div" style="padding-bottom: 2%;">
						<div class="col-sm-2 no-padding-div" style="padding-right: 0px">
							<a href="/sm/open/sales-and-marketing-templates?format=pdf"><img
								alt="" src="/images/pdf.png" class="img img-responsive"></a>
						</div>
						<div class="col-sm-10" style="padding-top: 2%;">
							<a href="/sm/open/sales-and-marketing-templates?format=pdf">PDF
								templates</a>
						</div>
					</div>
				</div>
				<div class="discussion-sidebar">
					<h4>Coaching Categories</h4>
					<ul>
						<li><a
							href="<c:url value='/sm/open/view-all-discussion?category=Interview Coaching'/>">Interview
								Coaching</a></li>

						<li><a
							href="<c:url value='/sm/open/view-all-discussion?category=Sales Performance Coaching'/>">Sales
								Performance Coaching</a></li>
						<li><a
							href="<c:url value='/sm/open/view-all-discussion?category=Excecutive Coaching'/>">Sales
								Management/Leadership</a></li>
					</ul>
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
	<script type="text/javascript" src="/js/slick.min.js"></script>
	<script type="text/javascript" src="/js/waitMe.js"></script>
	<script type="text/javascript" src="/js/validate.js"></script>
	<script type="text/javascript" src="/js/modules.js"></script>
	<script type="text/javascript" src="/js/bootstrap-rating.min.js"></script>


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