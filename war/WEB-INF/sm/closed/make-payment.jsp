<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html style="height: 100%;">
<head>
<meta charset="ISO-8859-1">
<title>Review Your Order</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" type="text/css" href="/style/jquery-ui.css">
<link rel="stylesheet" href="/style/bootstrap.min.css">
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
</head>
<body>
	<%@ include file="/WEB-INF/main-nav"%>
	<%@ include file="/WEB-INF/sm/open/sub-nav"%>
	<div class="container-fluid"
		style="width: 80%; margin: 0 auto; height: 100%;">
		<div class="row">
			<div class="discussion-sidebar col-sm-12 no-padding-div"
				style="background-color: white;">
				<div style="padding: 1%; padding-bottom: 2%;"
					class="col-md-12 text-muted">
					<div class="col-md-6 no-padding-div">
						<h3 style="padding: 0; margin: 0">Review your order</h3>
					</div>
					<div class="col-md-6" style="text-align: right;">
						<c:if test='${fn:length(cart.cartItems) > 0}'>
							<a class="btn btn-success btn-xs"
								href="/sm/open/show-all-workshops">Continue Browsing</a>
							<a class="btn btn-danger btn-xs"
								href="<c:url value="/sm/open/cart"/>">Back to Cart</a>
						</c:if>
					</div>
				</div>
				<div style="padding-bottom: 2%;" class="col-md-12 no-padding-div">
					<div class="col-md-12">
						<h4 class="text-success">
							<strong>TRANSACTION REFERENCE : </strong> <span
								class="text-muted"><c:out value="${txnRef}" /></span>
						</h4>
					</div>
				</div>
				<form name="form1" id="interswitch-tansaction-form"
					action="https://stageserv.interswitchng.com/test_paydirect/pay"
					method="post">
					<input name="product_id" type="hidden" value="6205" /> <input
						name="amount" type="hidden" value="${cart.subTotal*100}" /> <input
						name="currency" type="hidden" value="566" /> <input
						name="site_redirect_url" type="hidden" value="${interRedirectUrl}" />
					<input name="site_name" type="hidden"
						value="https://www.salesmaxx.com" /> <input name="cust_id"
						type="hidden" value="${user.regId.name}" /><input
						name="cust_name" type="hidden"
						value="${user.firstName} ${user.lastName}" /><input
						name="txn_ref" type="hidden" value="${txnRef}" /> <input
						name="pay_item_id" type="hidden" value="101" /> <input
						name="hash" type="hidden" value="${interHash}" />
					<div id="checkout-review-div"
						class="col-md-12 col-centered no-padding-div"
						style="margin-top: 2%">
						<table class="table table-striped table-responsive">
							<thead>
								<tr
									style="background-color: #5bc0de; color: white; border-bottom: none; border-top: 1px #46b8da solid">
									<th>Item</th>
									<th>Description</th>
									<th style="text-align: right">No. of Delegates</th>
									<th style="text-align: right;">Total (NGN)</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${cart.cartItems}">
									<tr>
										<td><c:out value='${item.name}' /></td>
										<td>
											<p>
												<strong>Date: </strong>
												<c:out value='${item.date}' />
											</p>
											<p>
												<strong>Location: </strong>
												<c:out value='${item.location}' />
											</p>
										</td>
										<td style="text-align: right;"><c:out value="${item.qty}" /></td>
										<td style="text-align: right;"><c:out
												value='${item.total}.00' /></td>
									</tr>
								</c:forEach>
								<tr>
									<td colspan="2"><i class="text-primary">Service
											provided by Profiliant Development Resourses</i></td>
									<th style="text-align: right;">Sub Total</th>
									<th style="text-align: right;"><c:out
											value='${cart.formattedsubTotal}.00' /></th>
								</tr>

							</tbody>
						</table>
						<div class="col-sm-12" style="text-align: right;">
							<div class="checkbox"
								style="font-size: 10pt; font-family: calibri">
								<label><input id="cancel-policy" type="checkbox">I
									have read the <a href="/sm/open/cancellation-policy"
									target="_blank">cancellation policy</a></label>
							</div>
							<c:set var="T1" value="web-pay" />
							<c:set var="T2" value="cheque" />
							<c:set var="T3" value="e-transfer" />
							<c:choose>
								<c:when test="${payMethod eq T1}">
									<input id="make-payment" type="submit"
										class="btn btn-success make-payment-interswitch"
										disabled="disabled" value="Pay With WebPay" />
								</c:when>
								<c:when test="${payMethod eq T2}">
									<a id="make-payment" class="btn btn-success"
										disabled="disabled"
										href="<c:url value='/sm/closed/init-cheque-invoice' />">Get
										Invoice</a>
								</c:when>
								<c:when test="${payMethod eq T3}">
									<a id="make-payment" class="btn btn-success"
										disabled="disabled">Get Bank Details</a>
								</c:when>
							</c:choose>

						</div>
						<c:if test="${payMethod eq T1}">
							<div class="col-sm-12">
								<span><i><strong>convenience fee:</strong> NGN 100.00</i></span>
								<img alt="interswitch" src="/images/interswitch-logo.png"
									class="img img-responsive pull-right">

							</div>
						</c:if>
					</div>
				</form>

			</div>

		</div>

	</div>
	<div style="margin-top: 2%;">
		<%@ include file="/WEB-INF/footer.html"%>
	</div>
	<script src="/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="/js/jquery-ui.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/js/waitMe.js"></script>
	<script type="text/javascript" src="/js/modules.js"></script>
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