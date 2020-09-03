<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>JCOM | ${title}</title>
		<!-- Tell the browser to be responsive to screen width -->
		<meta name="viewport" content="width=device-width, initial-scale=1">

		<!-- Font Awesome -->
		<link rel="stylesheet" href="<c:url value="/assets/plugins/fontawesome-free/css/all.min.css" />">
		<!-- Ionicons -->
		<link rel="stylesheet" href="<c:url value="/assets/css/ionicons.min.css" />">
		<!-- SweetAlert2 -->
		<link rel="stylesheet" href="<c:url value="/assets/plugins/sweetalert2-theme-bootstrap-4/bootstrap-4.min.css" />">
		<!-- Toastr -->
		<link rel="stylesheet" href="<c:url value="/assets/plugins/toastr/toastr.min.css" />">
		<!-- Theme style -->
		<link rel="stylesheet" href="<c:url value="/assets/css/adminlte.min.css"/>">
		<!-- Custom style -->
		<link rel="stylesheet" href="<c:url value="/assets/css/style.css"/>">
		<!-- Google Font: Source Sans Pro -->
		<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
	</head>

	<body class="hold-transition register-page">
		<%@include file="authentication.jsp" %>
		<div class="register-box">
			<div class="register-logo">
				<a href="javascript:void(0)"><b>REGISTER</b></a>
			</div>

			<div class="card">
				<div class="card-body register-card-body">
					<p class="login-box-msg">Register a new membership</p>

					<%--@elvariable id="user" type="model"--%>
					<form:form action="/registration" modelAttribute="user" method="post" onsubmit="return ValidateRegister();">
                        <form:errors path="*" cssClass="error-content" element="div"/>
						<form:input type="hidden" path="id"/>
						<div class="input-group">
							<form:input path="emailAddress" type="text" class="form-control" placeholder="Email"/>
							<div class="input-group-append">
								<div class="input-group-text">
									<span class="fas fa-envelope"></span>
								</div>
							</div>
						</div>
						<p id="emailError" class="text-danger mt-1 font-80 hide"></p>
						<div class="input-group">
							<form:input path="password" type="password" class="form-control" placeholder="Password"/>
							<div class="input-group-append">
								<div class="input-group-text">
									<span class="fas fa-lock"></span>
								</div>
							</div>
						</div>
						<p id="passwordError" class="text-danger mt-1 font-80 hide"></p>
						<div class="input-group">
							<input type="password" class="form-control" placeholder="Retype password" id="confirmPassword">
							<div class="input-group-append">
								<div class="input-group-text">
									<span class="fas fa-lock"></span>
								</div>
							</div>
						</div>
						<p id="rePasswordError" class="text-danger mt-1 font-80 hide"></p>
						<div class="input-group">
							<form:input path="firstName" type="text" class="form-control" placeholder="First Name"/>
							<div class="input-group-append">
								<div class="input-group-text">
									<span class="fas fa-user"></span>
								</div>
							</div>
						</div>
						<p id="firstNameError" class="text-danger mt-1 font-80 hide"></p>
						<div class="input-group mb-3">
							<form:input path="lastName" type="text" class="form-control" placeholder="Last Name"/>
							<div class="input-group-append">
								<div class="input-group-text">
									<span class="fas fa-user"></span>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-4 offset-4">
								<button type="submit" class="btn btn-primary btn-block">Register</button>
							</div>
							<!-- /.col -->
						</div>
					</form:form>

					<a href="/login" class="text-center">I already have a membership</a>
				</div>
				<!-- /.form-box -->
			</div><!-- /.card -->
		</div>
		<!-- /.register-box -->
		<!-- jQuery -->
		<script src="<c:url value="/assets/plugins/jquery/jquery.min.js" />"></script>
		<!-- Bootstrap 4 -->
		<script src="<c:url value="/assets/plugins/bootstrap/js/bootstrap.bundle.min.js" />"></script>
		<!-- SweetAlert2 -->
		<script src="<c:url value="/assets/plugins/sweetalert2/sweetalert2.min.js" />"></script>
		<!-- Toastr -->
		<script src="<c:url value="/assets/plugins/toastr/toastr.min.js" />"></script>
		<!-- AdminLTE App -->
		<script src="<c:url value="/assets/js/adminlte.js" />"></script>
		<!-- User registration functions -->
		<script src="<c:url value="/assets/js/app/user_registration.js" />"></script>
	</body>
</html>