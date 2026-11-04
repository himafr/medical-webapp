<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ar" dir="rtl">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>تسجيل الدخول</title>
        <link rel="stylesheet" href="./css/bootstrap.min.css">
        <link rel="stylesheet" href="./css/login.css">
        <link rel="stylesheet" href="./responsive.css">
    </head>
    <body >
        <%-- full window --%>
        <main class="login-main">
            <%-- form card --%>
            <div class="card">
                <%-- logo --%>
                <div class="logo-holder">
                    <img src="./svgs/med_bag.svg" alt="logo" class="logo">
                </div>
                <%-- text --%>
                <div class="header-logo">العيادة</div>


                <div class="hero-sub-header">الفخامه في الرعايه</div>
                <div class="log-text">تسجيل الدخول</div>
                <div class="hr"></div>
                <%-- form details --%>
                <form action="/perform_login?redirect=${param.redirect}" method="post">
                    <div class="mb-3">
                        <label for="email" class="form-label">البريد الالكتروني </label>

                        <div  class="input-holder">
                            <input required type="text" name="username" class="form-control" id="email" placeholder="hema@example.com">
                            <span>   <img src="./svgs/at_mark.svg" alt="email icon">
                            </span>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="password" class="form-label">كلمة المرور </label>
                        <div class="input-holder">
                            <input required type="password" name="password" class="form-control" id="password" placeholder="أدخل كلمة المرور">
                            <span onclick="togglePassword()"><img src="./svgs/lock.svg" alt="icon"></span>
                        </div>
                    </div>

                    <% if (request.getParameter("error") != null) { %>
                    <p style="color:red;">البريد الإلكتروني أو كلمة المرور غير صحيحة</p>
                    <% } %>

                    <% if (request.getParameter("logout") != null) { %>
                    <p style="color:green;">تم تسجيل الخروج بنجاح!</p>
                    <% } %>


                    <button type="submit" class="btn-login" > تسجيل الدخول
                        <img src="./svgs/arrow-left-log.svg" alt="icon" style="margin-right: 16px;">
                    </button>
                </form>

                <div class="signup-box">
                    <div class="signup-link">
                        ليس لديك حساب ؟
                        <a href="/signup">إنشاء حساب جديد</a>
                    </div>
                </div>
            </div>
        </main>
        <footer>&copy;  <%= java.time.Year.now().getValue() %> العيادة الملكية - جميع الحقوق محفوظة</footer>
        <script src="./js/bootstrap.min.js"></script>
        <script >
            function togglePassword() {
                const input = document.getElementById('password');
                if (input.type === "password") {
                    input.type = "text";
                } else {
                    input.type = "password";
                }
            }
        </script>
    </body>
</html>
