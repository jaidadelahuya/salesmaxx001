<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<head>
<meta charset="ISO-8859-1">
<title><c:out value="${discussionPageBean.category }" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="/style/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/style/waitMe.css">
<link rel="stylesheet" type="text/css" href="/style/index.css">
<link rel="stylesheet" type="text/css" href="/style/custom.css">
<link rel="stylesheet" type="text/css" href="/style/main-style.css">
<link rel="stylesheet" type="text/css" href="/style/font-awesome.min.css">
<style type="text/css">
.trending {
	height: 70px;
	border-bottom: 1px solid #dadada;
	border-right: 1px solid #dadada;
	border-left: 1px solid #dadada;
	padding : 2%;
	font-size: 10pt;
	background-color: white;
	
}



.discussion-meta {
	color: #983b59
}



</style>
<%@ include file="/js/google-analytics"%>
</head>
<body>
	<%@ include file="/WEB-INF/main-nav"%>
	<%@ include file="/WEB-INF/sm/open/sub-nav"%>
	<div class="container">
		<div class="row">
			
				<%@ include
					file="/WEB-INF/sm/open/view-all-discussions-content.html"%>
			
			
		</div>

	</div>
	<div style="margin-top: 2%;">
		<%@ include file="/WEB-INF/footer.html"%>
	</div>
	<script src="/js/jquery-1.11.2.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/js/waitMe.js"></script>
	<script type="text/javascript" src="/js/modules.js"></script>
</body>
</html>