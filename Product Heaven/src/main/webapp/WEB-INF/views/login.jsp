<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge; text/html; charset=ISO-8859-1;">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>JCOM | ${title}</title>
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
        <!-- Google Font: Source Sans Pro -->
        <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">

		<style type="text/css">
			.login-page-remember-align {line-height: 38px;}
		</style>
	</head>

	<body class="hold-transition login-page">
		<div class="login-box">
			<div class="login-logo">
				<a href="javascript:void(0)"><b>LOGIN</b></a>
<%--				<p>${}</p>--%>
			</div>
			<!-- /.login-logo -->
			<div class="card">
				<div class="card-body login-card-body">
					<p class="login-box-msg">Sign in to System</p>
					<form action="/login" method="post">
						<c:if test="${param.error != null}">
							<div class="alert alert-danger">
								<ul class="pl-3 mb-0">
									<li>Invalid username and password</li>
									<li>Account not activated yet</li>
								</ul>
							</div>
						</c:if>
						<c:if test="${param.logout != null}">
							<div class="alert alert-success">
								<p>You have been logged out successfully.</p>
							</div>
						</c:if>
						<div class="input-group mb-3">
							<input type="email" class="form-control" id="username" name="username" placeholder="Enter Email" required>
							<div class="input-group-append">
								<div class="input-group-text">
									<span class="fas fa-envelope"></span>
								</div>
							</div>
						</div>
						<div class="input-group mb-3">
							<input type="password" class="form-control" id="password" name="password" placeholder="Enter Password" required>
							<div class="input-group-append">
								<div class="input-group-text">
									<span class="fas fa-lock"></span>
								</div>
							</div>
						</div>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						<div class="row">
							<div class="col-8">
								<div class="icheck-primary login-page-remember-align">
									<%--@declare id="remember-me"--%>
									<input type="checkbox" id="rememberme" name="remember-me">
									<label for="remember-me">
										Remember Me
									</label>
								</div>
							</div>
							<!-- /.col -->
							<div class="col-4">
								<button type="submit" class="btn btn-primary btn-block">Sign In</button>
							</div>
							<!-- /.col -->
						</div>
					</form>

					<div class="social-auth-links text-center mb-3">
						<p class="login-box-msg">Don't you have an account?</p>
						<a href="javascript:void(0)" onclick="UnderConstruction();" class="btn btn-block btn-primary">
							<i class="fab fa-facebook mr-2"></i> Sign in using Facebook
						</a>
						<a href="javascript:void(0)" onclick="UnderConstruction();" class="btn btn-block btn-danger">
							<i class="fab fa-google mr-2"></i> Sign in using Google Mail
						</a>
					</div>
					<!-- /.social-auth-links -->

					<p class="mb-1">
						<a href="javascript:void(0)" onclick="UnderConstruction();">I forgot my password</a>
					</p>
					<p class="mb-0">
						<a href="/registration" class="text-center">Register a new membership</a>
					</p>
				</div>
				<!-- /.login-card-body -->
			</div>
		</div>

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

		<script type="application/javascript">
			const Toast = Swal.mixin({
				toast: true,
				position: 'bottom-end',
				showConfirmButton: false,
				timer: 3000
			});

			function UnderConstruction(){
				Toast.fire({
					icon: 'error',
					title: 'Under construction! Will be available Soon!'
				})
			}
		</script>
	</body>
</html>