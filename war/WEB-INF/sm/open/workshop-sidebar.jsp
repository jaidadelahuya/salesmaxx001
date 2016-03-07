<div
	style="background-color: #5bc0de; padding: 1%; color: white; margin-bottom: 0">
	<span><h4
			style="text-align: center; font-weight: bolder; padding: 0;">Browse
			By</h4></span>
</div>
<div style="background-color: #d9edf7; padding: 0;">
	<span><h4
			style="text-align: center; font-weight: bolder; padding: 1%; margin-top: 0; margin-bottom: 0">Industry</h4></span>
</div>
<ul class="list-group">
	<c:forEach var="item" items="${industryItems}">
		<li class="list-group-item">
			<div>
				<a href="${item.href}"><c:out value="${item.name}" /> (<c:out
						value="${item.freq}" />)</a>
			</div>
		</li>
	</c:forEach>
</ul>
<div style="background-color: #d9edf7; padding: 0;">
	<span><h4
			style="text-align: center; font-weight: bolder; padding: 1%; margin-top: 0; margin-bottom: 0">Experience</h4></span>
</div>
<ul class="list-group">
	<c:forEach var="item" items="${experienceItems}">
		<li class="list-group-item">
			<div>
				<a href="${item.href}"><c:out value="${item.name}" /> (<c:out
						value="${item.freq}" />)</a>
			</div>
		</li>
	</c:forEach>
</ul>
<div style="background-color: #d9edf7; padding: 0;">
	<span><h4
			style="text-align: center; font-weight: bolder; padding: 1%; margin-top: 0; margin-bottom: 0">Location</h4></span>
</div>
<ul class="list-group">
	<li class="list-group-item">
		<div>
			<a href="#">Abuja</a>
		</div>
	</li>
	<li class="list-group-item">
		<div>
			<a href="#">Lagos</a>
		</div>
	</li>
</ul>
<div style="background-color: #d9edf7; padding: 0;">
	<span><h4
			style="text-align: center; font-weight: bolder; padding: 1%; margin-top: 0; margin-bottom: 0">Date</h4></span>
</div>
<ul class="list-group">
	<li class="list-group-item">
		<label>Select a date:</label>
		<input type="date" class="form-control"/>
	</li>
	
</ul>