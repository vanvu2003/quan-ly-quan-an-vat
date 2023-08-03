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
				<ul class="nav menu">
					<li class="active"><a href="/AssignmentJava4/Admin">&nbsp; VIDEO</a></li>
					<li><a href="/AssignmentJava4/Admin/EditUser">&nbsp; USER</a></li>
					<li><a href="/AssignmentJava4/FavouriteAdmin">&nbsp;
							FAVOURITE</a></li>
					<li><a href="/AssignmentJava4/logout"><em class="fa fa-power-off">&nbsp;</em>
							LOGOUT</a></li>
				</ul>
			</div>
			<!--/.sidebar-->

			<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">VIDEO</h1>
					</div>
				</div>
				<!--/.row-->
				<div class="container">
					<form action="AdminServlet" method="post" enctype="multipart/form-data">
						<div class="row">
							<div class="col-md-7">
								<div class="form-group">
									<label for="">ID:</label>
									<input type="text" class="form-control" name="idVideo" value="${video.idVideo }">
								</div>
								<div class="form-group">
									<label for="">TITLE:</label>
									<input type="text" class="form-control" name="tiltle" value="${video.tiltle }"
										required>
								</div>
								<div class="form-group">
									<label for="">LINK:</label> <input type="file" name="link"
										onchange="previewVideo(event)" value="${video.link }">
								</div>
								<div class="form-group">
									<label for="">POSTER:</label> <input type="file" name="foster"
										value="${video.poster }">
								</div>
							</div>
							<div class="col-md-5">
								<div class="image">
									<video src="#" id="preview" width="400px" height="300px"></video>
								</div>
							</div>
						</div>

						<div class="form-group">
							<label for="">DESCRIPTION:</label><br>
							<textarea name="description" cols="150" rows="6">${video.description }</textarea>
						</div>

						<button class="btn btn-success" formaction="/AssignmentJava4/Admin/insert">SAVE</button>
						<button class="btn btn-warning" formaction="/AssignmentJava4/Admin/update">UPDATE</button>
						<button class="btn btn-danger" type="submit"formaction="/AssignmentJava4/Admin/delete">DELETE</button>
					</form>
				</div>
				<br>
				<div>
					<div class="card">
						<div class="card-heder">
							<h4>${countVideo} video</h4>
						</div>
						<div class="card-body">
							<table class="table table-bordered">
								<thead>
									<tr>
										<th scope="col">ID</th>
										<th scope="col">TILTLE</th>
										<th scope="col">POSTER</th>
										<th scope="col">DESCRIPTION</th>
										<th scope="col">VIEW</th>
										<th scope="col"></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item" items="${listVideo }">
										<tr>
											<th scope="row">${item.idVideo}</th>
											<td>${item.tiltle}</td>
											<td><img src="/AssignmentJava4/uploads/${item.poster}" height="100px"
													width="200px"></td>
											<td>${item.description}</td>
											<td>${item.views}</td>
											<td class="text-center justify-content-center"><a
													href="AssignmentJava4/Admin/edit?id=${item.idVideo}">Edit</a>
												&nbsp;&nbsp;&nbsp;
												<a href="AssignmentJava4/Admin/UserLikeVideo?id=${item.idVideo}">Like</a>
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
				function previewVideo(event) {
					var input = event.target;
					var reader = new FileReader();
					reader.onload = function () {
						var video = document.getElementById('preview');
						video.src = reader.result;
					};
					reader.readAsDataURL(input.files[0]);
				}
				function deleteVideo() {

					swal({
						title: "Are you sure?",
						text: "Once deleted, you will not be able to recover this imaginary file!",
						icon: "warning",
						buttons: true,
						dangerMode: true,
					})
						.then((willDelete) => {
							if (willDelete) {
								// Send delete request via AJAX
								$.ajax({
									url: "/AssignmentJava4/Admin/delete",
									method: "POST",
									data: { fileId: fileId },
									success: function (response) {
										swal("Poof! Your imaginary file has been deleted!", {
											icon: "success",
										});
									},
									error: function (xhr, status, error) {
										swal("Error deleting file: " + error);
									}
								});
							} else {
								swal("Your imaginary file is safe!");
							}
						});
				}
			</script>

		</body>

		</html>