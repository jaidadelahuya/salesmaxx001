<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html style="height: 100%;">
<head>
<meta charset="ISO-8859-1">
<title>Cart</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" type="text/css" href="/style/jquery-ui.css">
<link rel="stylesheet" href="/style/bootstrap.min.css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="/style/slick.css" />
<link rel="stylesheet" type="text/css" href="/style/slick-theme.css" />
<link rel="stylesheet" type="text/css"
	href="/style/jquery.webui-popover.min.css">
<link rel="stylesheet" type="text/css" href="/style/waitMe.css">
<link rel="stylesheet" type="text/css" href="/style/index.css">
<link rel="stylesheet" type="text/css" href="/style/custom.css">
<link rel="stylesheet" type="text/css" href="/style/main-style.css">
<link rel="stylesheet" type="text/css"
	href="/style/bootstrap-rating.css">
<link rel="stylesheet" type="text/css"
	href="/style/bootstrap-datepicker3.standalone.min.css">
<script src="https://apis.google.com/js/platform.js" async defer></script>
<meta name="google-signin-client_id"
	content="1082599418027-i7l89ubpe432n7lfiu9jus3cc0a99vqs.apps.googleusercontent.com">
<style type="text/css">

@media screen and (max-width: 1200px) {
	#payment-mode-div {
		width: 30%;
	}
}

@media screen and (max-width: 992px) {
	#payment-mode-div {
		width: 40%;
	}
}

@media screen and (max-width: 767px) {
	#payment-mode-div {
		width: 70%;
	}
}

@media screen and (max-width: 480px) {
	#payment-mode-div {
		width: 80%;
	}
}

@media screen and (max-width: 320px) {
	#payment-mode-div {
		width: 100%
	}
}
</style>
<%@ include file="/js/google-analytics"%>
</head>

<body>
	<%@ include file="/WEB-INF/main-nav"%>
	<%@ include file="/WEB-INF/sm/open/sub-nav"%>
	<div class="container-fluid"
		style="width: 95%; margin: 0 auto; height: 100%;">
		<div class="row">
			<div class="panel panel-default col-md-12" style="padding: 0">
				<%@ include file="/WEB-INF/sm/open/cart-content.html"%>
			</div>

			<div class="panel panel-default col-md-12" style="padding: 0">
				<c:if test='${not empty cartRelatedWorkshops}'>
					<div style="padding: 1%; padding-bottom: 2%;" class="col-md-12">
						<div class="col-md-12" style="text-align: center;">
							<h3 class="text-muted" style="padding: 2%;">You should also
								check out these workshops</h3>
							<div class="col-md-12" id="slick">
								<c:forEach var='item' items='${cartRelatedWorkshops}'>
									<div class="col-sm-3">
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
												data-fractions="5" value="rating" style='display: block' />
											(
											<c:out value="${fn:length(item.reviews)}" />
											) reviews
										</p>
									</div>
								</c:forEach>
							</div>
						</div>

					</div>

				</c:if>


			</div>

		</div>

	</div>
	<div style="margin-top: 2%;">
		<%@ include file="/WEB-INF/footer.html"%>
	</div>
	<script src="/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="/js/jquery-ui.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/js/slick.min.js"></script>
	<script type="text/javascript" src="/js/waitMe.js"></script>
	<script type="text/javascript" src="/js/modules.js"></script>
	<script type="text/javascript" src="/js/index.js"></script>
	<script type="text/javascript" src="/js/bootstrap-rating.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".increment-qty-btn").click(function() {
				var v = $(this).closest("td").prop("id");
				var qty = $(this).closest("td").find(".increment-qty").prop('value');
				$.ajax({
					url : "/sm/open/clear-cart",
					type : "POST",
					data: {
						"id":v,
						"qty":qty
					},
					success : function() {
						window.location.assign("/sm/open/add-to-cart");
					},
					error : function() {
					}
				});
			});
			$(".main-menu-item").removeClass("active");
			//$("#workshop-menu").addClass("active");
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