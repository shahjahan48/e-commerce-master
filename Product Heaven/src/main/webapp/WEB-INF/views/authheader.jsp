	<div class="authbar">
		<c:choose>
			<c:when test="${loggedinuser != ' '}">
				<span>Dear <strong>${loggedinuser}</strong>, Welcome to CrazyUsers.</span> <span class="floatRight">
				<a href="<c:url value="/logout" />">Logout</a></span>
			</c:when>
			<c:otherwise>
				<a href="<c:url value="/login" />">Login</a></span>
			</c:otherwise>
		</c:choose>
	</div>
