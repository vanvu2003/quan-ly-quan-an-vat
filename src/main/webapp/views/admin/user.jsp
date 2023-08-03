<%@ page language="java" pageEncoding="utf-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<!DOCTYPE html>
		<html>

		<head>
			<meta charset="utf-8">
			<meta name="viewport" content="width=device-width, initial-scale=1">
			<title>Lumino - Dashboard</title>
			<link href="/AssignmentJava4/views/admin/css/bootstrap.min.css" rel="stylesheet">
			<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
			<link href="/AssignmentJava4/views/css/datepickerAdmin.css" rel="stylesheet">
			<link href="/AssignmentJava4/views/css/styleAdmin.css" rel="stylesheet">
			<link rel="stylesheet" href="path/to/toastr.css">
			<script src="path/to/jquery.js"></script>
			<script src="path/to/toastr.js"></script>
			<base href="/">
			<!--Custom Font-->
			<link href="https://fonts.googleapis.com/css?family=Montserrat:300,300i,400,400i,500,500i,600,600i,700,700i"
				rel="stylesheet">

		</head>

		<body>
			<nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
				<div class="container-fluid">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
							data-target="#sidebar-collapse">
							<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span
								class="icon-bar"></span> <span class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="#"><span>CINEMA</span>Admin</a>
					</div>
				</div>
				<!-- /.container-fluid -->
			</nav>
			<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
				<div class="profile-sidebar">
					<div class="profile-userpic">
						<img src="http://placehold.it/50/30a5ff/fff" class="img-responsive" alt="">
					</div>
					<div class="profile-usertitle">
						<div class="profile-usertitle-name">Username</div>
						<div class="profile-usertitle-status">
							<span class="indicator label-success"></span>Online
						</div>
					</div>
					<div class="clear"></div>
				</div>
				<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
				<div class="profile-sidebar">
					<div class="profile-userpic">
						<img src="http://placehold.it/50/30a5ff/fff" class="img-responsive" alt="">
					</div>
					<div class="profile-usertitle">
						<div class="profile-usertitle-name">Username</div>
						<div class="profile-usertitle-status">
							<span class="indicator label-success"></span>Online
						</div>
					</div>
					<div class="clear"></div>
				</div>
				<ul class="nav menu">
					<li><a href="/AssignmentJava4/Admin">&nbsp; VIDEO</a></li>
					<li class="active"><a href="/AssignmentJava4/Admin/EditUser">&nbsp; USER</a></li>
					<li><a href="/AssignmentJava4/FavouriteAdmin">&nbsp;
							FAVOURITE</a></li>
					<li><a href="/AssignmentJava4/logout"><em class="fa fa-power-off">&nbsp;</em>
							LOGOUT</a></li>
				</ul>
			</div>
			</div>
			<!--/.sidebar-->

			<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">USER</h1>
					</div>
				</div>
				<!--/.row-->
				<div class="container">
					<form action="" method="post" enctype="multipart/form-data">
						<div class="form-group">
							<label for="">USERNAME:</label> <input type="text" class="form-control" name="userID"
								value="${userEdit.userID }">
						</div>
						<div class="form-group">
							<label for="">FULLNAME:</label> <input type="text" class="form-control" name="fullname"
								value="${userEdit.fullname }" required>
						</div>
						<div class="form-group">
							<label for="">EMAIL:</label> <input type="text" class="form-control" name="email"
								value="${userEdit.email }" required>
						</div>
						<div class="form-group">
							<label for="">PASSWORD:</label> <input type="text" class="form-control" name="password"
								value="${userEdit.password }" required>
						</div>
						<button class="btn btn-warning" type="submit"
							formaction="/AssignmentJava4/Admin/EditUser/update">UPDATE</button>
						<button class="btn btn-danger" formaction="/AssignmentJava4/Admin/EditUser/delete">DELETE</button>
					</form>
				</div>
				<br>
				<div>
					<div class="card">
						<div class="card-heder">
							<h4>${countUser} USER</h4>
						</div>
						<div class="card-body">
							<table class="table table-bordered">
								<thead>
									<tr>
										<th scope="col">USERNAME</th>
										<th scope="col">PASSWORD</th>
										<th scope="col">FULLNAME</th>
										<th scope="col">EMAIL</th>
										<th scope="col">ROLE</th>
										<th scope="col"></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item" items="${users }">
										<tr>
											<td scope="row">${item.userID}</td>
											<td>${item.password}</td>
											<td>${item.fullname}</td>
											<td>${item.email}</td>
											<c:if test="${item.role == true }">
												<td>User</td>
											</c:if>
											<c:if test="${item.role == false }">
												<td>Admin</td>
											</c:if>
											<td class="text-center"><a
													href="AssignmentJava4/Admin/EditUser/edit?id=${item.userID}">Edit</a>
											</td>
										</tr>
									</c:forEach>


								</tbody>
							</table>
						</div>
					</div>
				</div>


			</div>
			<!--/.main-->

			<script src="/AssignmentJava4/views/js/jquery-1.11.1.min.js"></script>
			<script src="/AssignmentJava4/views/js/bootstrap.min.js"></script>
			<script src="/AssignmentJava4/views/js/custom.js"></script>

			<script type="text/javascript">
				var message = "${message}"
				if (message) {
					toastr.success('Thông báo thành công', 'Thành công');
				}
			</script>

		</body>