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
	<!-- Google Font: Source Sans Pro -->
	<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
</head>

<body class="hold-transition register-page">
	<div class="register-box">
		<h4>Registration Successful</h4>
		<p>Now go to <a href="/login">Login</a> page to login</p>
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

	<script type="application/javascript">

	</script>
</body>
</html>