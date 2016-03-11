<div
	style="background-color: #5bc0de; padding: 1%; color: white; margin-bottom: 0">
	<span><h4
			style="text-align: center; font-weight: bolder; padding: 0;">Find a Workshop by</h4></span>
</div>
<div style="background-color:#d9edf7; padding: 0;">
	<span><h4
			style="text-align: center; font-weight: bolder; padding: 1%; margin-top: 0; margin-bottom: 0">Job
			Role</h4></span>
</div>
<ul class="list-group">
	<c:forEach var="item" items="${jobRoleItems}">
		<li class="list-group-item">
			<div>
				<a href="${item.href}"><c:out value="${item.name}" /> (<c:out
						value="${item.freq}" />)</a>
			</div>
		</li>
	</c:forEach>
</ul>