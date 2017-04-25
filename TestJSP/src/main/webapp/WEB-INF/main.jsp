<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8"%>
<html>
<head>
	<title>Report</title>
	<style type="text/css">
		.error-message {
			color: red;
		}
		
		.form-element {
			margin-top: 5px;
		}
	</style>
</head>
<body>
<form method = "GET">
	<div class="form-element">
		<label for="start-date">Start Date:</label><br/>
		<input name="start" type="text" id="start-date" value="${start}">
	</div>
	<div class="form-element">
		<label for="end-date">End Date:</label><br/>
		<input name="end" type="text" id="end-date" value="${end}">
	</div>
	<div class="form-element">
		<label for="performer">Performer:</label><br/>
		<select name="performer" id="performer">
		   <option>All Performers</option>
		   <option <c:if test='${performer eq "admin"}'> selected </c:if> >admin</option>
		   <option <c:if test='${performer eq "user"}'> selected </c:if> >user</option>		   
		</select>
	</div>
	<div class="form-element">
		<label for="time-perioad">Time Period:</label><br/>
		<select id="time-perioad" onchange="changeHandler(event)">
		   <option></option>
		   <option>Last Qtr</option>
		   <option>Last Month</option>
		   <option>Last Calendar Year</option>
		   <option>Current Year to Date</option>
		   <option>Current Qtr to Date</option>
		   <option>Current Month to Date</option>
		</select>
	</div>
	<div class="form-element">
		<input type="submit" value="Submit">
	</div>
</form>
<c:if test="${not empty error}">
   <p class="error-message"><c:out value="${error}"/></p>
</c:if>

<c:if test="${not empty reports}">
	<table border="1">
		<tr>
			<th>Report ID</th>
			<th>Start Date</th> 
			<th>End Date</th>
			<th>Performer</th>
			<th>Activity</th>
		</tr>
		<c:forEach items="${reports}" var="report">
		    <tr>
		        <td><c:out value="${report.id}"/></td>
		        <td><c:out value="${report.startDate}"/></td>  
		        <td> <c:out value="${report.endDate}"/></td>
		        <td><c:out value="${report.performer}"/></td>
		        <td><c:out value="${report.activity}"/></td>
		    </tr>
		</c:forEach>
	</table>
</c:if>
<script type="text/javascript">
	function changeHandler(event) {
		var timePeriod = event.target.value;
		var startDateEl = document.getElementById("start-date");
		var endDateEl = document.getElementById("end-date");		
		var startDate;
		var endDate;
		var tmp = new Date();
				
		if(timePeriod.localeCompare('Current Month to Date') == 0) {
			var date = new Date();
			startDate = new Date(date.getFullYear(), date.getMonth(), 1);
			endDate = new Date();
		} else if(timePeriod.localeCompare('Current Qtr to Date') == 0) {
			var date = new Date();		
			startDate = new Date(date.getFullYear(), date.getMonth() - date.getMonth() % 3, 1)
			endDate = new Date();
		} else if(timePeriod.localeCompare('Current Year to Date') == 0) {
			var date = new Date();
			startDate = new Date(date.getFullYear(),0,1);
			endDate = new Date();
		} else if(timePeriod.localeCompare('Last Calendar Year') == 0) {
			var date = new Date();
			startDate = new Date(date.getFullYear() - 1,0,1);
			endDate = new Date(date.getFullYear() - 1,11,31);			
		} else if(timePeriod.localeCompare('Last Month') == 0) {
			var date = new Date();			
			startDate = new Date(date.getFullYear(),date.getMonth() - 1,1);
			endDate = new Date(date.getFullYear(),date.getMonth(),0);
		} else if(timePeriod.localeCompare('Last Qtr') == 0) {
			var date = new Date();			
			startDate = new Date(date.getMonth() < 3 ? date.getFullYear() - 1 : date.getFullYear(), date.getMonth() < 3 ? 9 : date.getMonth() - date.getMonth() % 3 - 3, 1 );
			endDate = new Date(date.getMonth() < 3 ? date.getFullYear() - 1 : date.getFullYear(), date.getMonth() < 3 ? 11 : date.getMonth() + date.getMonth() % 3, 0 );			
		}
		
		var options = { year: 'numeric', month: 'short', day: 'numeric'};
		startDateEl.value = new Date(startDate).toLocaleString("en-us", options);
		endDateEl.value = new Date(endDate).toLocaleString("en-us", options);
		console.log(new Date(startDate).toLocaleString("en-us", options));
		console.log(new Date(endDate).toLocaleString("en-us", options));
	}

</script>

</body>
</html>


