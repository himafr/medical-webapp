<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html dir="rtl">
    <head>
        <meta charset="UTF-8">
        <title>تفعيل الحساب</title>
        <link rel="icon" type="image/svg+xml" href="/images/round_royal_logo.png">

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>
    <body class="bg-light">
        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-5">
                    <div class="card shadow p-4">
                        <h4 class="text-center mb-3">تفعيل الحساب</h4>
                        <p class="text-center text-muted">
                            سيتم إرسال كود التفعيل إلى بريدك الإلكتروني
                        </p>

                        <% if (request.getAttribute("error") != null) { %>
                        <div class="alert alert-danger">
                            <%= request.getAttribute("error") %>
                        </div>
                        <% } %>

                        <%-- فورم بعت الـ PIN --%>
                        <form id="sendForm" action="/activate/send" method="post">
                            <input type="hidden" name="email" value="<%= request.getAttribute("email") %>">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                            <div class="d-grid mb-2">
                                <button id="sendBtn" type="button" class="btn btn-outline-primary">
                                    إرسال كود التفعيل
                                </button>
                            </div>
                            <div id="countdown" class="text-center text-muted mb-3" style="display:none">
                                إعادة الإرسال بعد <span id="timer">90</span> ثانية
                            </div>
                        </form>

                        <%-- فورم التحقق من الـ PIN --%>
                        <form action="/activate/verify" method="post">
                            <input type="hidden" name="email" value="<%= request.getAttribute("email") %>">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                            <div class="mb-3">
                                <label class="form-label">أدخل كود التفعيل</label>
                                <input type="text" name="pin" class="form-control text-center"
                                maxlength="6" placeholder="XXXXXX" required>
                            </div>
                            <div class="d-grid">
                                <button type="submit" class="btn btn-primary">تفعيل الحساب</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <script>
            document.addEventListener("DOMContentLoaded", function() {
                const lastSent = localStorage.getItem("pinSentAt");
                if (lastSent) {
                    const elapsed = Math.floor((Date.now() - parseInt(lastSent)) / 1000);
                    const remaining = 90 - elapsed;
                    if (remaining > 0) {
                        startCountdown(remaining);
                    }
                }
            });

            document.getElementById("sendBtn").addEventListener("click", function() {
                localStorage.setItem("pinSentAt", Date.now().toString());
                startCountdown();
                document.getElementById("sendForm").submit();
            });

            function startCountdown(seconds = 90) {
                const btn = document.getElementById("sendBtn");
                const countdown = document.getElementById("countdown");

                btn.disabled = true;
                countdown.style.display = "block";

                const interval = setInterval(() => {
                    seconds--;
                    document.getElementById("timer").textContent = seconds;

                    if (seconds <= 0) {
                        clearInterval(interval);
                        btn.disabled = false;
                        countdown.style.display = "none";
                        localStorage.removeItem("pinSentAt");
                    }
                }, 1000);
            }
        </script>
    </body>
</html>