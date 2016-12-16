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
<title>Workshop</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="/style/bootstrap.min.css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" type="text/css"
	href="/style/jquery.webui-popover.min.css">
<link rel="stylesheet" type="text/css" href="/style/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="/style/slick.css" />
<link rel="stylesheet" type="text/css" href="/style/slick-theme.css" />
<link rel="stylesheet" type="text/css" href="/style/waitMe.css">
<link rel="stylesheet" type="text/css" href="/style/index.css">
<link rel="stylesheet" type="text/css" href="/style/custom.css">
<link rel="stylesheet" type="text/css" href="/style/main-style.css">
<link rel="stylesheet" type="text/css"
	href="/style/bootstrap-rating.css">


<script src="https://apis.google.com/js/platform.js" async defer></script>
<meta name="google-signin-client_id"
	content="1082599418027-i7l89ubpe432n7lfiu9jus3cc0a99vqs.apps.googleusercontent.com">
	<%@ include file="/js/google-analytics"%>
<meta property="fb:app_id" content="1134790883222273" />
<meta property="og:title" content="${workshopTemplate.workshopName}" />
<meta property="og:type" content="website" />
<meta property="og:url" content="http://www.salesmaxx.com/sm/open/workshop-information?id=${workshopTemplate.workshopId.name}" />
<meta property="og:image" content="${workshopTemplate.imageUrl}" />
<meta property="og:description" content="${workshopTemplate.shortDescription.value}" />
<meta property="og:site_name" content="SalesMaxx" />
<meta name="twitter:card" content="Summary" />
<meta name="twitter:title" content="${workshopTemplate.workshopName}" />
<meta name="twitter:description" content="${workshopTemplate.shortDescription.value}" />
<meta name="twitter:image" content="${workshopTemplate.imageUrl}" />
<meta name="twitter:url" content="http://www.salesmaxx.com/sm/open/workshop-information?id=${workshopTemplate.workshopId.name}" />
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
	<div class="container-fluid"
		style="width: 95%; margin: 0 auto; height: 100%;">
		<div class="row">
			<div class="panel panel-default col-md-3" style="padding: 0">
				<%@ include file="/WEB-INF/sm/open/workshop-sidebar"%>
			</div>
			<div class="col-md-9" style="padding-right: 0px">
				<div class="panel panel-default col-md-12" style="padding: 0">
					<%@ include
						file="/WEB-INF/sm/open/workshop-information-content.html"%>
				</div>
				<div class="col-md-12 well" style="background-color: white">
					<h3 class="text-muted"
						style="text-align: center; padding-bottom: 2%;">Related
						Workshops</h3>
					<div class="col-sm-12" id="slick">

						<c:forEach var="item" items="${relatedWorkshops}">
							<div class="col-sm-3"
								style="font-family: calibri; font-size: 11pt; text-align: center;">
								<a
									href="<c:url value='/sm/open/workshop-information?id=${item.workshopId.name}'/>"><img
									style="width: 80%; height: auto; margin: 0 auto"
									alt="${item.workshopName}" src="${item.imageUrl}"
									class="img img-responsive"></a> <a
									href="<c:url value='/sm/open/workshop-information?id=${item.workshopId.name}'/>"><c:out
										value='${item.workshopName}' /></a>
								<p>
									<strong>Skill Level</strong>
									<c:forEach var='val' items='${item.skillLevel}'>${val} </c:forEach>
								</p>
								<p>
									<input type="hidden" class="rating" data-readonly
										data-fractions="5" value="rating" style='display: block' /> (
									<c:out value="${fn:length(item.reviews)}" />
									) reviews
								</p>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>

		</div>

	</div>
	<div style="margin-top: 2%;">
		<%@ include file="/WEB-INF/footer.html"%>
	</div>
	<script src="https://apis.google.com/js/platform.js" async defer></script>
	<script src="/js/jquery-1.11.2.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	<script type="text/javascript" src="/js/slick.min.js"></script>
	<script src="/js/jquery-ui.min.js"></script>
	<script src="/js/jquery.webui-popover.min.js"></script>
	<script type="text/javascript" src="/js/waitMe.js"></script>
	<script type="text/javascript" src="/js/modules.js"></script>
	<script type="text/javascript" src="/js/index.js"></script>
	<script type="text/javascript" src="/js/bootstrap-rating.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".main-menu-item").removeClass("active");
			//$("#workshop-menu").addClass("active");
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