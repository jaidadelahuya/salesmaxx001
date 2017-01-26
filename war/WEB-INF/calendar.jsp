<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/WEB-INF/custom-functions.tld" prefix="pn"%>
<%@page import="com.salesmaxx.entities.UserRole"%>
<!DOCTYPE html>
<html style="height: 100%;">
<head>
<meta charset="ISO-8859-1">
<title>SalesMaxx Calendar</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="/style/bootstrap.min.css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/cupertino/jquery-ui.css">
<link rel="stylesheet" type="text/css"
	href="/style/jquery.webui-popover.min.css">
<link rel="stylesheet" type="text/css" href="/style/index.css">
<link rel="stylesheet" type="text/css" href="/style/custom.css">
<link rel="stylesheet" type="text/css" href="/style/main-style.css">
<link rel='stylesheet' href='/style/fullcalendar.css' />
<link rel="canonical" href="http://www.salesmaxx.com/calendar">
<meta name="image_src"
	content="http://www.salesmaxx.com/images/calender.jpg" />
<meta name="image_url"
	content="http://www.salesmaxx.com/images/calender.jpg" />
<meta property="fb:app_id" content="1134790883222273" />
<meta property="og:title" content="SalesMaxx Calendar" />
<meta property="og:type" content="website" />
<meta property="og:url" content="http://www.salesmaxx.com/calendar" />
<meta property="og:image"
	content="http://www.salesmaxx.com/images/calender.jpg" />
<meta property="og:description"
	content="Interesting SalesMaxx training workshops for 2017" />
<meta property="og:site_name" content="SalesMaxx" />
<meta name="twitter:card" content="Summary" />
<meta name="twitter:title" content="SalesMaxx Calendar" />
<meta name="twitter:description"
	content="Interesting SalesMaxx training workshops for 2017" />
<meta name="twitter:image"
	content="http://www.salesmaxx.com/images/calender.jpg" />
<meta name="twitter:url" content="http://www.salesmaxx.com/calendar" />
<script src="https://apis.google.com/js/platform.js" async defer></script>
<style type="text/css">
.fc-event {
	text-align: center;
	height: 40px;
	padding: 5px 2px;
}
</style>
<%@ include file="/js/google-analytics"%>
</head>
<body>
	<div id="fb-root"></div>
	<script>
		(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id))
				return;
			js = d.createElement(s);
			js.id = id;
			js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.5";
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
	</script>
	<script>
		window.twttr = (function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0], t = window.twttr || {};
			if (d.getElementById(id))
				return t;
			js = d.createElement(s);
			js.id = id;
			js.src = "https://platform.twitter.com/widgets.js";
			fjs.parentNode.insertBefore(js, fjs);

			t._e = [];
			t.ready = function(f) {
				t._e.push(f);
			};

			return t;
		}(document, "script", "twitter-wjs"));
	</script>
	<%@ include file="/WEB-INF/main-nav"%>
	<%@ include file="/WEB-INF/sm/open/sub-nav"%>
	<div class="container">
		<div class="row">

			<div class="col-md-9">
				<%@ include file="/WEB-INF/sm/open/new-calendar.html"%>
				<div class="row">
					<div class="col-md-12" style="text-align: center; padding: 2%;">
						<div class="fb-share-button"
							style="line-height: 0.7; vertical-align: baseline; display: inline-block;"
							data-href="http://www.salesmaxx.com/calendar"
							data-layout="button"></div>

						<script src="//platform.linkedin.com/in.js" type="text/javascript">
							lang: en_US
						</script>
						<script type="IN/Share"
							data-url="http://www.salesmaxx.com/calendar"></script>
						<div class="g-plus" data-action="share" data-annotation="none"
							data-href="http://www.salesmaxx.com/calendar"></div>
						<a class="twitter-share-button"
							href="https://twitter.com/intent/tweet"> Tweet</a>
						<div style="display: inline-block;">
							<a
								href="mailto:?Subject=Interesting workshops on SalesMaxx calendar&amp;Body=Hello%2C%0D%0AI found this calendar of very interesting sales productivity workshops.%0D%0ACheck it out.http%3A%2F%2Fwww.salesmaxx.com%2Fcalenda">
								<img src="/images/email-big.png"
								style="vertical-align: baseline;" alt="Email" />
							</a>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="discussion-sidebar">
					<h4>Coaching Categories</h4>
					<ul>
						<li><a
							href="<c:url value='/sm/open/view-all-discussion?category=Interview Coaching'/>">Interview
								Coaching</a></li>
						<li><a
							href="<c:url value='/sm/open/view-all-discussion?category=Excecutive Coaching'/>">Executive
								Coaching</a></li>
						<li><a
							href="<c:url value='/sm/open/view-all-discussion?category=Sales Performance Coaching'/>">Sales
								Performance Coaching</a></li>
					</ul>
				</div>

				<div class="discussion-sidebar">
					<h4>
						<a href="/sm/open/sales-and-marketing-templates">Sales &
							Marketing Automation</a>
					</h4>
					<div class="col-sm-12 no-padding-div" style="padding-bottom: 2%;">
						<div class="col-sm-2 no-padding-div">
							<a href="/sm/open/sales-and-marketing-templates?format=ms-wordl"><img
								alt="" src="/images/zoho_crm.jpg" class="img img-responsive"></a>
						</div>
						<div class="col-sm-10" style="padding-top: 2%;">
							<a href="/sm/open/sales-and-marketing-templates?format=ms-word">CRM</a>
						</div>
					</div>
					<div class="col-sm-12 no-padding-div" style="padding-bottom: 2%;">
						<div class="col-sm-2 no-padding-div" style="padding-right: 0px">
							<a href="/sm/open/sales-and-marketing-templates?format=excel"><img
								alt="" src="/images/zoho_campaign.jpg"
								class="img img-responsive"></a>
						</div>
						<div class="col-sm-10" style="padding-top: 2%;">
							<a href="/sm/open/sales-and-marketing-templates?format=excel">CAMPAIGNS</a>
						</div>
					</div>
					<div class="col-sm-12 no-padding-div" style="padding-bottom: 2%;">
						<div class="col-sm-2 no-padding-div" style="padding-right: 0px">
							<a
								href="/sm/open/sales-and-marketing-templates?format=power-point"><img
								alt="" src="/images/zoho_salesIQ.jpg" class="img img-responsive"></a>
						</div>
						<div class="col-sm-10" style="padding-top: 2%;">
							<a
								href="/sm/open/sales-and-marketing-templates?format=power-point">SALES
								IQ</a>
						</div>
					</div>

					<div class="col-sm-12 no-padding-div" style="padding-bottom: 2%;">
						<div class="col-sm-2 no-padding-div" style="padding-right: 0px">
							<a href="/sm/open/sales-and-marketing-templates?format=pdf"><img
								alt="" src="/images/zoho_social.jpg" class="img img-responsive"></a>
						</div>
						<div class="col-sm-10" style="padding-top: 2%;">
							<a href="/sm/open/sales-and-marketing-templates?format=pdf">SOCIAL</a>
						</div>
					</div>
					<div class="col-sm-12 no-padding-div" style="padding-bottom: 2%;">
						<div class="col-sm-2 no-padding-div" style="padding-right: 0px">
							<a href="/sm/open/sales-and-marketing-templates?format=pdf"><img
								alt="" src="/images/motivator.png" class="img img-responsive"></a>
						</div>
						<div class="col-sm-10" style="padding-top: 2%;">
							<a href="/sm/open/sales-and-marketing-templates?format=pdf">MOTIVATOR</a>
						</div>
					</div>
					<div class="col-sm-12 no-padding-div" style="padding-bottom: 2%;">
						<div class="col-sm-2 no-padding-div" style="padding-right: 0px">
							<a href="/sm/open/sales-and-marketing-templates?format=pdf"><img
								alt="" src="/images/zoho_mail.jpg" class="img img-responsive"></a>
						</div>
						<div class="col-sm-10" style="padding-top: 2%;">
							<a href="/sm/open/sales-and-marketing-templates?format=pdf">MAIL
								SUITE</a>
						</div>
					</div>
				</div>

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
			</div>
		</div>

	</div>
	<div style="margin-top: 2%;">
		<%@ include file="/WEB-INF/footer.html"%>
	</div>
	<script src="/js/jquery-1.11.2.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	<script src="/js/jquery-ui.min.js"></script>
	<script src="/js/jquery.webui-popover.min.js"></script>
	<script src='/js/moment.min.js'></script>
	<script src='/js/fullcalendar.js'></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".main-menu-item").removeClass("active");
			//$("#workshop-menu").addClass("active");
			$('#calendar').fullCalendar({
				events : "/calendar/get",
				defaultView: 'listMonth',
				header : {
					left : 'prev,next today',
					center : 'title',
					right : 'listMonth,basicWeek,basicDay,month'
				},
				theme : true,
				weekNumbers : true

			});
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
	<script type="text/javascript">
		$(document).ready(function() {
			$(".main-menu-item").removeClass("active");
			$(".main-menu-item").removeClass("menu-active");
			$("#calendar-menu").addClass("menu-active");
		});

		
	</script>

</body>
</html>