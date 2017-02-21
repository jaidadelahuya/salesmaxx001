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
<style type="text/css">
.product-box {
	border: 1px solid #eee;
	float: left;
	margin: 4px 4px 4px 4px;
	position: relative;
	
	padding: 4px;
	font-family: Helvetica;
}

.product-title h3 {
	margin: 2px 3px 0 2px;
	min-width: 40px;
	font-size: 12px;
	line-height: 15px;
	color: #000;
	position: relative;
	text-align: center;
	display: block;
	overflow: hidden;
	height: 45px;
}

.product-price {
	text-align: center;
	color: #900;
	font-weight: bold;
}

.a-icon-cart {
	left: 2px;
	top: 2px;
	position: absolute;
	height: 25px;
	width: 25px;
	background-position: -35px -5px;
	background-image: url(../images/amzn-sprite.png);
	background-repeat: no-repeat;
	background-size: 400px 600px;
	-webkit-background-size: 400px 600px;
	display: inline-block;
	vertical-align: top;
}

.a-icon-shop-now {
	left: 2px;
	top: 2px;
	position: absolute;
	height: 25px;
	width: 25px;
	background: 0 0;
	display: inline-block;
	vertical-align: top;
}

.a-button {
	background: #d8dde6;
	display: block;
	padding: 0;
	vertical-align: middle;
	height: 31px;
	*height: 29px;
	border: 1px solid;
	border-color: #bcc1c8 #bababa #adb2bb;
	text-align: center;
	overflow: hidden;
	text-decoration: none !important;
	cursor: pointer;
	-webkit-border-radius: 3px;
	-moz-border-radius: 3px;
	border-radius: 3px;
	width: 100%;
	max-width: 130px;
	box-sizing: border-box;
	margin-top: 11px;
}

.a-button-text {
	position: relative;
	z-index: 10;
	color: #111;
	font-size: 12px;
	text-align: center;
	line-height: 29px;
	display: block;
	font-family: Arial, sans-serif;
	white-space: nowrap;
	background-color: transparent;
	margin: 0;
	border: 0;
	outline: 0;
	padding: 0 2px 0 29px;
}

.a-button-text.centered {
	padding: 0 2px;
}

.a-button-input {
	position: absolute;
	z-index: 20;
	height: 100%;
	width: 100%;
	left: 0;
	top: 0;
	background-color: #fff;
	outline: 0;
	border: 0;
	overflow: visible;
	cursor: pointer;
	opacity: .01;
	filter: alpha(opacity = 1);
}

.a-button-primary {
	border-color: #cba957 #bf942a #aa8326;
	background: #f0c14b;
}

.a-button-primary:active {
	border-color: #aa8326 #bf942a #bf942a;
}

.a-button-primary:hover {
	border-color: #c59f43 #aa8326 #957321;
}

.a-button-primary:focus {
	outline: 0;
	border-color: #e47911;
	-webkit-box-shadow: 0 0 3px rgba(228, 121, 17, .5);
	-moz-box-shadow: 0 0 3px rgba(228, 121, 17, .5);
	box-shadow: 0 0 3px rgba(228, 121, 17, .5);
}

.a-button-inner {
	position: relative;
	height: 100%;
	overflow: hidden;
	-webkit-border-radius: 3px;
	-moz-border-radius: 3px;
	border-radius: 3px;
	text-align: center;
	cursor: pointer;
	display: block;
}

.a-button-primary .a-button-inner {
	-webkit-box-shadow: 0 1px 0 rgba(255, 255, 255, .4) inset;
	-moz-box-shadow: 0 1px 0 rgba(255, 255, 255, .4) inset;
	box-shadow: 0 1px 0 rgba(255, 255, 255, .4) inset;
	background-color: #f7dfa5;
	background: -webkit-gradient(linear, left top, left bottom, from(#f7dfa5),
		to(#f0c14b));
	background: -webkit-linear-gradient(top, #f7dfa5, #f0c14b);
	background: -moz-linear-gradient(top, #f7dfa5, #f0c14b);
	background: -ms-linear-gradient(top, #f7dfa5, #f0c14b);
	background: -o-linear-gradient(top, #f7dfa5, #f0c14b);
	background: #f3d078;
}

.lt-ie9 .a-button-primary .a-button-inner {
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#fff7dfa5',
		endColorstr='#fff0c14b', GradientType=0);
}

.a-button-primary:active .a-button-inner {
	-webkit-box-shadow: 0 1px 3px rgba(0, 0, 0, .2) inset;
	-moz-box-shadow: 0 1px 3px rgba(0, 0, 0, .2) inset;
	box-shadow: 0 1px 3px rgba(0, 0, 0, .2) inset;
	background-color: #f0c14b;
	background-image: none;
}

.lt-ie9 .a-button-primary:active .a-button-inner {
	filter: progid:DXImageTransform.Microsoft.gradient(enabled=false);
}

.a-button-primary:hover .a-button-inner {
	background-color: #f5d78e;
	background: -webkit-gradient(linear, left top, left bottom, from(#f5d78e),
		to(#eeb933));
	background: -webkit-linear-gradient(top, #f5d78e, #eeb933);
	background: -moz-linear-gradient(top, #f5d78e, #eeb933);
	background: -ms-linear-gradient(top, #f5d78e, #eeb933);
	background: -o-linear-gradient(top, #f5d78e, #eeb933);
	background: #f1c860;
}

.lt-ie9 .a-button-primary:hover .a-button-inner {
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#fff5d78e',
		endColorstr='#ffeeb933', GradientType=0);
}
</style>
</style>

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
						<h4 style="color: #3b5998">
							<b>Here are additional materials based on the information you
								have given us</b>
						</h4>
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
							
							<c:forEach var="item" items="${coachingSuccess.books}" end='4'>
								<div class="col-sm-4 col-md-2">
									<div class="product-box">
										<a target="_blank"
											href="${item.url}">
											<img
											src="${item.image}"
											width="120" height="160">
										</a>
										<div class="product-title">
											<h3>${item.title}</h3>
										</div>
									<!-- 	<p class="product-price">
											N/A<br>  -->
										</p>
										<div>
											<span class="a-button a-button-primary"> <a
												target="_blank"
												href="${item.url}"
												style="text-decoration: none"> <span
													class="a-button-inner"> <img
														src="http://webservices.amazon.com/scratchpad/assets/images/Amazon-Favicon-64x64.png"
														class="a-icon a-icon-shop-now"> <input
														class="a-button-input" type="submit" value="Add to cart">
														<span class="a-button-text">Shop Now</span>
												</span>
											</a>
											</span>
										</div>
									</div>
								</div>
							</c:forEach>

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