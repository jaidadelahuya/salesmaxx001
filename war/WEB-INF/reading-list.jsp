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
<title>Recommended Reading List</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="/style/bootstrap.min.css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" type="text/css"
	href="/style/jquery.webui-popover.min.css">
<link rel="stylesheet" type="text/css" href="/style/waitMe.css">
<link rel="stylesheet" type="text/css" href="/style/index.css">
<link rel="stylesheet" type="text/css" href="/style/custom.css">
<link rel="stylesheet" type="text/css" href="/style/main-style.css">
<link rel="stylesheet" type="text/css"
	href="/style/bootstrap-rating.css">
	
<meta name="image_src"
	content="http://www.salesmaxx.com/images/reading-list.png" />
<meta name="image_url"
	content="http://www.salesmaxx.com/images/reading-list.png" />
<meta property="fb:app_id" content="1134790883222273" />
<meta property="og:title" content="SalesMaxx Recommended Reading List" />
<meta property="og:type" content="website" />
<meta property="og:url" content="http://www.salesmaxx.com/readinglist" />
<meta property="og:image"
	content="http://www.salesmaxx.com/images/reading-list.png" />
<meta property="og:description"
	content="See what our experts are reading now" />
<meta property="og:site_name" content="SalesMaxx" />
<meta name="twitter:card" content="Summary" />
<meta name="twitter:title" content="SalesMaxx Recommended Reading List" />
<meta name="twitter:description"
	content="See what our experts are reading now" />
<meta name="twitter:image"
	content="http://www.salesmaxx.com/images/reading-list.png" />
<meta name="twitter:url" content="http://www.salesmaxx.com/readinglist" />
<script src="https://apis.google.com/js/platform.js" async defer></script>
<meta name="google-signin-client_id"
	content="1082599418027-i7l89ubpe432n7lfiu9jus3cc0a99vqs.apps.googleusercontent.com">
<%@ include file="/js/google-analytics"%>
<script type="text/javascript">
	
</script>
</head>
<body>
	<%@ include file="/WEB-INF/main-nav"%>
	<%@ include file="/WEB-INF/sm/open/sub-nav"%>
	<div id="par" class="container" style="height: 100%">
		<div class="row">
			<div class="panel panel-default col-sm-12" style="padding: 0">
				<iframe style="height: 1000px" id="frame"
					src="http://astore.amazon.com/salesmaxx-20" width="100%"
					frameborder="0" scrolling="yes"></iframe>
			</div>

		</div>
		<div class="row">
			<div class="col-sm-offset-2 col-sm-8 text-muted" style="text-align: center;">
				<h6>
					<strong style="font-size: 8pt">Affiliate Marketing Program</strong>
				</h6>
				<p style="font-size: 7pt; font-family: calibri">
					Profiliant is a participant in the Amazon Services LLC Associates
					Program, an affiliate advertising program designed to provide a
					means for sites to earn advertising fees by advertising and linking
					to amazon.com</p>
			</div>
		</div>

	</div>
	<div style="margin-top: 2%;">
		<%@ include file="/WEB-INF/footer.html"%>
	</div>
	<script src="/js/jquery-1.11.2.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	<script src="/js/jquery.webui-popover.min.js"></script>
	<script type="text/javascript" src="/js/waitMe.js"></script>
	<script type="text/javascript" src="/js/modules.js"></script>
	<script type="text/javascript" src="/js/bootstrap-rating.min.js"></script>
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
			$(".main-menu-item").removeClass("active");
			$(".main-menu-item").removeClass("menu-active");
			$("#reading-list").addClass("menu-active");

		});
	</script>

</body>
</html>