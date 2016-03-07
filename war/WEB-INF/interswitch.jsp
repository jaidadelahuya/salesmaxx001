<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>Interswitch</title>
</head>
<body>
	<form action="https://stageserv.interswitchng.com/test_paydirect/pay"
		method="post">
		<input name="product_id" type="hidden" value="4220" /> <input
			name="pay_item_id" type="hidden" value="101" /> <input name="amount"
			type="hidden" value="10000" /> <input name="currency" type="hidden"
			value="566" /> <input name="site_redirect_url" type="hidden"
			value="http://salesmaxx001.appspot.com/sm/closed/interswitch-callback" /> <input
			name="txn_ref" type="hidden" value="11111" /> <input name="hash"
			type="hidden"
			value="199F6031F20C63C18E2DC6F9CBA7689137661A05ADD4114ED10F5AFB64BE625B6A9993A634F590B64887EEB93FCFECB513EF9DE1C0B53FA33D287221D75643AB" />
		<input name="cust_id" type="hidden" value="steve" /> <input
			name="site_name" type="hidden" value="salesmaxx001.appspot.com">
		<input type="submit" value="go to interswitch" />
	</form>
</body>
</html>