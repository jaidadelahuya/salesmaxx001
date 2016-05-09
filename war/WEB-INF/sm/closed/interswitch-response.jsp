<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html style="height: 100%;">
<head>
<meta charset="ISO-8859-1">
<title>Transaction Complete</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" type="text/css" href="/style/jquery-ui.css">
<link rel="stylesheet" href="/style/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/style/slick.css" />
<link rel="stylesheet" type="text/css" href="/style/slick-theme.css" />
<link rel="stylesheet" type="text/css" href="/style/index.css">
<link rel="stylesheet" type="text/css" href="/style/custom.css">
<link rel="stylesheet" type="text/css" href="/style/main-style.css">
<%@ include file="/js/google-analytics"%>
</head>
<body>
	<%@ include file="/WEB-INF/main-nav"%>
	<%@ include file="/WEB-INF/sm/open/sub-nav"%>
	<div class="container-fluid" style="width: 95%; margin: 0 auto;">
		<div class="row">
			<c:choose>
				<c:when test='${empty interswitch.responseCode}'>
					<div class="alert alert-danger col-md-12"
						style="padding: 0; background-color: white;">
						<div class="alert alert-danger">
							<h4>
								<span class="glyphicon glyphicon-remove"></span> Your
								Transaction was not successful.
							</h4>
						</div>

						<p>
							<strong>Reason: </strong>We could not get response from Web Pay.
						</p>

						<p>
							<strong>Transaction Reference: <c:out value='${txnRef}' /></strong>
						</p>

						<br />
						<p>
							<a href="<c:url value='/sm/open/cart' />"
								class="btn btn-xs btn-danger">&#x2190; Try again</a> <a
								href="<c:url value='/sm/closed/proceed-to-checkout?pay-method=cheque' />"
								class="btn btn-xs btn-danger">Pay with Cheque or Electronic
								Transfer</a>
						</p>
					</div>
				</c:when>
				<c:when test='${interswitch.responseCode eq "00"}'>
					<div class="alert alert-success col-md-12"
						style="background-color: white;">
						<div class="alert alert-success">
							<h4>
								<span class="glyphicon glyphicon-ok"></span> Your Transaction
								has been completed successfully
							</h4>
						</div>
						<p style="font-family: calibri">
							A email confirmation has been sent to <i class="text-muted"><c:out
									value="${toEmail}" /></i>
						</p>
						<p style="font-family: calibri">
							<strong>Transaction ID: </strong>
							<c:out value='${interswitch.txnRef}' />
							</span>
						</p>
						<div style="padding-top: 4%;">
							<a class="btn btn-danger btn-xs"
								href="<c:url value="/sm/open/show-all-workshops"/>">Continue
								Browsing</a> <a
								href="<c:url value='/sm/closed/profile/purchase-history' />"
								class="btn btn-xs btn-danger">View Purchase History</a> <a
								href="<c:url value='/sm/closed/profile/my-workshops' />"
								class="btn btn-xs btn-danger">View your workshops</a> <a
								class="btn btn-danger btn-xs"
								href="<c:url value='/sm/closed/get-invoice?&txnRef=${interswitch.txnRef}' />">Print
								Receipt</a>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="alert alert-danger col-md-12"
						style="background-color: white">
						<div class="alert alert-danger">
							<h4>
								<span class="glyphicon glyphicon-remove"></span> Your
								Transaction was not successful.
							</h4>
						</div>
						<p>
							<strong>Reason: </strong><span style="color: black;"><c:out
									value='${interswitch.responseDescriptor}' /></span>
						</p>



						<p>
							<strong>Transaction Reference: </strong><span
								style="color: black;"><c:out
									value='${interswitch.txnRef}' /></span>
						</p>

						<br />
						<p>
							<a href="/sm/open/cart" class="btn btn-xs btn-danger">&#x2190;
								Try again</a> <a
								href="<c:url value='/sm/closed/proceed-to-checkout?pay-method=cheque' />"
								class="btn btn-xs btn-danger">Pay with Cheque or Electronic
								Transfer</a>
						</p>

					</div>
				</c:otherwise>
			</c:choose>

		</div>
		<c:if test='${not empty cartRelatedWorkshops}'>

			<div style="padding: 1%; padding-bottom: 2%;"
				class="row panel panel-default">
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
										data-fractions="5" value="rating" style='display: block' /> (
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