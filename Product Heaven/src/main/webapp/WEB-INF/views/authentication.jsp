	<div class="authbar">
		<sec:authentication var="principal" property="principal" />
		<c:choose>
			<c:when test="${principal eq 'anonymousUser'}">
<%--				<span>--%>
<%--					<a href="<c:url value="/login" />">Login</a>--%>
<%--					<a href="<c:url value="/newuser"/>">Register</a>--%>
<%--				</span>--%>
				<c:redirect url="/login"/>
			</c:when>
			<c:otherwise>
<%--				<span>Dear <strong>${loggedinuser}</strong>, Welcome to CrazyUsers.</span> <span class="floatRight">--%>
<%--				<a href="<c:url value="/logout" />">Logout</a></span>--%>
			</c:otherwise>
		</c:choose>
	</div>
