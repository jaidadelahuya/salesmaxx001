<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/WEB-INF/custom-functions.tld" prefix="pn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html style="height: 100%;">
<head>
<meta charset="ISO-8859-1">
<title>${singleDiscussionPageBean.topic}</title>
<link rel="stylesheet" href="/style/bootstrap.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="/style/waitMe.css">
<link rel="stylesheet" type="text/css" href="/style/index.css">
<link rel="stylesheet" type="text/css" href="/style/custom.css">
<link rel="stylesheet" type="text/css" href="/style/main-style.css">
<link rel="canonical" href="https://www.salesmaxx.com/sm/open/get-discussion?webkey=${singleDiscussionPageBean.webkey}&category=${singleDiscussionPageBean.category}">
<script src="https://apis.google.com/js/platform.js" async defer></script>
<meta name="google-signin-client_id"
	content="1082599418027-i7l89ubpe432n7lfiu9jus3cc0a99vqs.apps.googleusercontent.com">
<meta name="image_src"
	content="https://www.salesmaxx.com/images/calender.jpg" />
<meta name="image_url"
	content="https://www.salesmaxx.com/images/calender.jpg" />
<meta property="fb:app_id" content="1134790883222273" />
<meta property="og:title" content="SalesMaxx Discussion" />
<meta property="og:type" content="website" />
<meta property="og:url" content="https://www.salesmaxx.com/sm/open/get-discussion?webkey=${singleDiscussionPageBean.webkey}&category=${singleDiscussionPageBean.category}"" />
<meta property="og:image"
	content="https://www.salesmaxx.com/images/salesmaxx-logo.png" />
<meta property="og:description"
	content="${singleDiscussionPageBean.topic}" />
<meta property="og:site_name" content="SalesMaxx" />
<meta name="twitter:card" content="Summary" />
<meta name="twitter:title" content="SalesMaxx Discussion" />
<meta name="twitter:description"
	content="${singleDiscussionPageBean.topic}" />
<meta name="twitter:image"
	content="https://www.salesmaxx.com/images/salesmaxx-logo.png" />
<meta name="twitter:url" content="https://www.salesmaxx.com/sm/open/get-discussion?webkey=${singleDiscussionPageBean.webkey}&category=${singleDiscussionPageBean.category}"" />
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
			<div class="col-md-offset-1 col-md-10" style="padding-left: 0px">
				<%@ include file="/WEB-INF/sm/open/view-discussion.html"%>
			</div>
			
		</div>

	</div>
	<div style="margin-top: 2%;">
		<%@ include file="/WEB-INF/footer.html"%>
	</div>
	<script src="/js/jquery-1.11.2.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	<script src="//cdn.tinymce.com/4/tinymce.min.js"></script>
	<script src="/js/jquery.webui-popover.min.js"></script>
	<script type="text/javascript" src="/js/waitMe.js"></script>
	<script type="text/javascript" src="/js/modules.js"></script>
	<script type="text/javascript" src="/js/bootstrap-rating.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#tools-menu").addClass("active");
			tinymce.init({
				selector : '#comment-msg'
			});
		});
	</script>

</body>
</html>