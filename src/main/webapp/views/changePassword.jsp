<%@ page language="java" pageEncoding="utf-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<!DOCTYPE html>
		<html lang="en">

		<head>
			<meta charset="UTF-8">
			<meta name="viewport" content="width=device-width, initial-scale=1.0">
			<title>CINEMA-V</title>
			<link rel="stylesheet" href="/AssignmentJava4/views/css/bootstrap.min.css">
			<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
			<link rel="stylesheet" href="/AssignmentJava4/views/css/templatemo-style.css">
			<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
			<script src="https://code.jquery.com/jquery-3.6.4.js"
				integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E=" crossorigin="anonymous"></script>
		</head>

		<body>
			<!-- Page Loader -->
			<div id="loader-wrapper">
				<div id="loader"></div>

				<div class="loader-section section-left"></div>
				<div class="loader-section section-right"></div>

			</div>
			<nav class="navbar navbar-expand-lg">
				<div class="container-fluid">
					<a class="navbar-brand" href="index.html"> <i class="fas fa-film mr-2"></i> CINEMA-V
					</a>
					<button class="navbar-toggler" type="button" data-toggle="collapse"
						data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
						aria-expanded="false" aria-label="Toggle navigation">
						<i class="fas fa-bars"></i>
					</button>
					<div class="collapse navbar-collapse" id="navbarSupportedContent">
						<c:if test="${not empty user.userID}">
							<ul class="navbar-nav ml-auto mb-2 mb-lg-0">
								<li class="nav-item"><a class="nav-link nav-link-1" aria-current="page"
										href="/AssignmentJava4/Home">Home</a></li>
								<li class="nav-item"><a class="nav-link nav-link-2" href="/AssignmentJava4/Favourite">My
										Favorites</a></li>
								<li class="nav-item"><a class="nav-link nav-link-3"
										href="/AssignmentJava4/views/about.jsp">About</a></li>
								<li class="nav-item"><a class="nav-link nav-link-4"
										href="/AssignmentJava4/views/contact.jsp">Contact</a></li>
							</ul>
							<div class="nav-item dropdown">
								<div class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink"
									data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Account</div>
								<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">

									<a class="dropdown-item" href="logout">Logout</a> <a class="dropdown-item"
										href="changePassword">Change password</a>
									<a class="dropdown-item" href="/AssignmentJava4/views/editFrofile.jsp">Edit
										frofile</a>
								</div>
							</div>
						</c:if>
						<c:if test="${empty user.userID}">
							<ul class="navbar-nav ml-auto mb-2 mb-lg-0">
								<li class="nav-item"><a class="nav-link nav-link-1" aria-current="page"
										href="/AssignmentJava4/Home">Home</a></li>
								<li class="nav-item"><a class="nav-link nav-link-3"
										href="/AssignmentJava4/views/about.jsp">About</a></li>
								<li class="nav-item"><a class="nav-link nav-link-4"
										href="/AssignmentJava4/views/contact.jsp">Contact</a></li>
							</ul>
							<div class="nav-item dropdown">
								<div class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink"
									data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Account</div>
								<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
									<a class="dropdown-item" href="register">Register</a> <a class="dropdown-item"
										href="login">Login</a>
								</div>
							</div>
						</c:if>
					</div>
				</div>
			</nav>

			<div class="tm-hero d-flex justify-content-center align-items-center" data-parallax="scroll"
				data-image-src="https://statics.vincom.com.vn/xu-huong/anh-revamp/Phong-Ngu-Chu.png"
				style="height: 700px"></div>

			<div class="container col-10">
				<div class="row tm-mb-50 mt-5">
					<div class="col-lg-12 col-12 mb-5">

						<form id="contact-form" action="" method="POST" class="tm-contact-form mx-auto">
							<h2 class="tm-text-primary mb-5 text-center mt-3">Change
								Password</h2>
							<div class="form-group">
								<input type="text" name="username" id="username" class="form-control rounded-0"
									placeholder="Username" required value="${usID}" />
								<p style="color: red" id="errorID"></p>
							</div>
							<div class="form-group">
								<input type="password" name="password" id="password" class="form-control rounded-0"
									placeholder="Curent password" required value="${pass}" />
								<p style="color: red" id="errorPass"></p>
							</div>
							<div class="form-group">
								<input type="password" name="newPassword" id="newPass" class="form-control rounded-0"
									placeholder="New password" required value="${newPass }" />
							</div>
							<div class="form-group">
								<input type="password" name="confirmPassword" id="confirmPassword"
									class="form-control rounded-0" placeholder="Confirm password" required
									value="${confirmPass }" />
								<p style="color: red" id="errorConfirm"></p>
							</div>
							<div class="form-group tm-text-right text-center justify-content-center">
								<button type="button" id="change" onclick="changePass()" class="btn btn-success"
									style="height: 40px;">SEND</button>
							</div>
						</form>
					</div>

				</div>
			</div>
			<!-- container-fluid, tm-container-content -->

			<footer class="tm-bg-gray pt-5 pb-3 tm-text-gray tm-footer bg-dark">
				<div class="container-fluid tm-container-small">
					<div class="row">
						<div class="col-lg-6 col-md-12 col-12 px-5 mb-5">
							<h3 class="tm-text-primary mb-4 tm-footer-title">About
								CINEMA-V</h3>
							<p>CINEMA-V is free Bootstrap 5 Alpha 2 HTML Template for video
								and photo websites. You can freely use this TemplateMo layout for
								a front-end integration with any kind of CMS website.</p>
						</div>
						<div class="col-lg-3 col-md-6 col-sm-6 col-12 px-5 mb-5">
							<h3 class="tm-text-primary mb-4 tm-footer-title">Our Links</h3>
							<ul class="tm-footer-links pl-0">
								<li><a href="#">Advertise</a></li>
								<li><a href="#">Support</a></li>
								<li><a href="#">Our Company</a></li>
								<li><a href="#">Contact</a></li>
							</ul>
						</div>
						<div class="col-lg-3 col-md-6 col-sm-6 col-12 px-5 mb-5">
							<ul class="tm-social-links d-flex justify-content-end pl-0 mb-5">
								<li class="mb-2"><a href="https://facebook.com"><i class="fab fa-facebook"></i></a></li>
								<li class="mb-2"><a href="https://twitter.com"><i class="fab fa-twitter"></i></a></li>
								<li class="mb-2"><a href="https://instagram.com"><i class="fab fa-instagram"></i></a>
								</li>
								<li class="mb-2"><a href="https://pinterest.com"><i class="fab fa-pinterest"></i></a>
								</li>
							</ul>
							<a href="#" class="tm-text-gray text-right d-block mb-2">Terms
								of Use</a> <a href="#" class="tm-text-gray text-right d-block">Privacy
								Policy</a>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-8 col-md-7 col-12 px-5 mb-3">Copyright 2020
							CINEMA-V Company. All rights reserved.</div>
						<div class="col-lg-4 col-md-5 col-12 px-5 text-right">
							Designed by <a href="https://templatemo.com" class="tm-text-gray" rel="sponsored"
								target="_parent">TemplateMo</a>
						</div>
					</div>
				</div>
			</footer>

			<script src="/AssignmentJava4/views/js/plugins.js"></script>
			<script>
				$(window).on("load", function () {
					$('body').addClass('loaded');
				});
				// var message = '${message}'
				// var erorr = '${error}'
				// console.log(message)

				// if (message) {
				// 	Swal.fire({
				// 		icon: 'success',
				// 		title: message,
				// 		showConfirmButton: true,
				// 		timer: 45002
				// 	}).then(function () {
				// 		window.location.href = 'Home';
				// 	});
				// } else if (erorr) {
				// 	Swal.fire({
				// 		icon: 'error',
				// 		title: error,
				// 		showConfirmButton: true,
				// 		timer: 2500
				// 	})
				// }

				function changePass() {
					var username = $('#username').val();
					var password = $('#password').val();
					var newPass = $('#newPass').val();
					var confirmPassword = $('#confirmPassword').val();

					document.getElementById("change").innerHTML = "Đang thay đổi...";
					$.ajax({
						url: '/AssignmentJava4/changePassword',
						method: 'POST',
						data: {
							username: username,
							password: password,
							newPass: newPass,
							confirmPassword: confirmPassword
						},
						success: function (response) {
							var jsonObj = JSON.parse(response)
							var message = jsonObj.message
							var errorID = jsonObj.errorID
							var errorPass = jsonObj.errorPass
							var errorConfirm = jsonObj.errorConfirm

							console.log(errorID)

							if (errorID) {
								document.getElementById("errorID").innerHTML = "Tài khoản không đúng!!";
								document.getElementById("errorConfirm").innerHTML = "";
								document.getElementById("errorPass").innerHTML = "";
								document.getElementById("change").innerHTML = "SEND";
								return
							}
							if (errorPass) {
								document.getElementById("errorPass").innerHTML = "Mật khẩu không đúng!!";
								document.getElementById("errorConfirm").innerHTML = "";
								document.getElementById("errorID").innerHTML = "";
								document.getElementById("change").innerHTML = "SEND";
								return
							}
							if (errorConfirm) {
								document.getElementById("errorConfirm").innerHTML = "Mật khẩu không khớp!!";
								document.getElementById("errorPass").innerHTML = "";
								document.getElementById("errorID").innerHTML = "";
								document.getElementById("change").innerHTML = "SEND";
								return
							}
							Swal.fire('Good job!', message, 'success');
							document.getElementById("errorID").innerHTML = "";
							document.getElementById("errorConfirm").innerHTML = "";
							document.getElementById("errorPass").innerHTML = "";
							document.getElementById("change").innerHTML = "SEND";
						},
					});

				}
			</script>
		</body>

		</html>