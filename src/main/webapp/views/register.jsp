<%@ page language="java" pageEncoding="utf-8" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ĐĂNG KÝ</title>
        <link rel="shortcut icon" href="data:image/jpeg;base64,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" type="image/png">
        <link rel="stylesheet" href="/AssignmentJava4/views/css/bootstrap.min.css">
        <link rel="stylesheet" href="/AssignmentJava4/views/css/register.css">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
    </head>

    <body class="bg-dark">
        <!--Source frome https://www.w3schools.com/howto/howto_css_signup_form.asp-->
        <div class="container rounded col-6" style="background-color: #C0C0C0;">
            <form action="" method="post">
                <div class="container">
                    <h1>Đăng Ký</h1>
                    <p>Xin hãy nhập biểu mẫu bên dưới để đăng ký.</p>
                    <hr>
                    <label for=""><b>Tài Khoản</b></label>
                    <input type="text" placeholder="Nhập tài khoản" name="userID" id="username" value="${user.userID }"
                        required>

                    <label for=""><b>Họ Và Tên</b></label>
                    <input type="text" placeholder="Nhập họ và tên" name="fullname" id="fullname"
                        value="${user.fullname }" required>


                    <label for="email"><b>Email</b></label>
                    <input type="text" placeholder="Nhập Email" name="email" id="email" value="${user.email }" required>

                    <label for=""><b>Vai Trò</b></label>
                    <div class="form-check">
                        <input class="form-check-inline" type="radio" name="role" id="flexRadioDefault1" value="1"
                            checked>
                        <label class="form-check-label" for="flexRadioDefault1">
                            User
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-inline" type="radio" name="role" id="flexRadioDefault2" value="0">
                        <label class="form-check-label" for="flexRadioDefault2">
                            Admin
                        </label>
                    </div>
                    <br>
                    <label for="psw"><b>Mật Khẩu</b></label>
                    <input type="password" placeholder="Nhập Mật Khẩu" name="password" value="${user.password }"
                        required>

                    <label for="psw-repeat"><b>Nhập Lại Mật Khẩu</b></label>
                    <input type="password" placeholder="Nhập Lại Mật Khẩu" name="rePassword" value="${rePasss}"
                        required>

                    <label for="psw-repeat"><b>Mã xác nhận:</b></label><br>
                    <div class="row">
                        <input type="text" placeholder="Nhập mã xác nhận" name="check" style="width: 200px">
                        <button class="btn btn-info ml-2" type="submit"
                            style="width: 100px; height: 53px; margin-top: 6px;" formaction="sendMail">Tạo
                            mã</button>
                    </div>
                    <p style="color: red">${error }</p>

                    <div class="clearfix">
                        <button type="submit" class="signupbtn" formaction="register">Đăng Ký</button>
                    </div>
                    <div class="text-center mt-3">
                        <a href="/AssignmentJava4/views/login.jsp">Đăng nhập</a>
                    </div>
                </div>
            </form>
        </div>
        <script type="text/javascript">
            var message = '${message}'

            if (message) {
                Swal.fire(
                    'Thông Báo',
                    message,
                    'info'
                )
            }

            // var email = document.getElementById("email")
            // var emailValue = email.value

            // if (emailValue) {
            // Swal.fire({
            //     icon: 'success',
            //     title: 'Vui lòng check mail',
            //     showConfirmButton: false,
            //     timer: 2500
            // })
            // } else {
            // Swal.fire(
            //     'Lỗi!',
            //     'Vui lòng nhập mail trước!',
            //     'error'
            // )
            // }

        </script>
    </body>

    </html>