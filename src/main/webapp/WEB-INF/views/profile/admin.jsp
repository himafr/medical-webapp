<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Arrays" %>


<!DOCTYPE html>
<html lang="ar" dir="rtl">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title> Home page | ${user.fullName} </title>
        <link rel="stylesheet" href="./css/bootstrap.min.css">
        <link rel="stylesheet" href="./style.css">
        <link rel="stylesheet" href="./css/doctor.css">
        <link rel="stylesheet" href="./responsive.css">
    </head>
    <body>
        <div class="container">
            <%@ include file="../shared/header.jsp" %>
            <section>
                <div class="content">

                    <div class="card" id="personalDetails">
                        <img src="./svgs/edit.svg" alt="edit" width="35" class="edit-icon"  onclick="toggleEditPersonalDetails()" >
                        <div class="doctor-details">
                            <div class="flex-between " style="gap:20px">
                                <img class="avatar"  src="./images/${user.photo}" alt="user photo">
                                <div>
                                    <div class="info-username mb-3"> ${user.fullName}</div>
                                    <div class="info-major">${user}</div>
                                    <div class="text-secondary">${user}</div>
                                </div>
                            </div>
                            <a href="/doctors/dashboard" class="dashboard-link"> <img src="/svgs/dashboard.svg" width='18' alt="dashboard icon " />  الانتقال إلى لوحة التحكم</a>
                        </div>
                    </div>

                    <%-- Edit Card --%>
                    <div class="card" id="personalDetailsEdit" style="display:none">
                        <div class="profile-top">
                            <div class="section-title" style="margin-bottom:0">تعديل المعلومات الشخصية</div>
                            <img src="./svgs/close.svg" alt="cancel" width="35" class="edit-icon" onclick="toggleEditPersonalDetails()">
                        </div>
                        <form class="mb-3 mt-3" action="/users/update-personal-details" method="post" onsubmit="this.querySelector('button[type=submit]').disabled=true">
                            <div class="info-grid ">
                                <div class="info-item">
                                    <label>الاسم الكامل</label>
                                    <input class="form-control" type="text" name="fullName" value="${user.fullName}">
                                </div>
                                <div class="info-item">
                                    <label>البريد الإلكتروني</label>
                                    <input disabled class="form-control" type="email" name="email" value="${user.email}">
                                </div>
                                <div class="info-item">
                                    <label>رقم الجوال</label>
                                    <input class="form-control" type="text" name="phoneNumber" value="${user.phoneNumber}">
                                </div>
                                <div class="info-item">
                                    <label>تاريخ الميلاد</label>
                                    <input class="form-control" type="date" name="dateOfBirth" value="${user.dateOfBirth}">
                                </div>
                                <div class="info-item">
                                    <label>الجنس</label>
                                    <select name="gender" class="form-control">
                                        <option value="ذكر" ${user.gender=='ذكر'?'selected':''}>ذكر</option>
                                        <option value="انثي" ${user.gender=='انثي'?'selected':''}>أنثى</option>
                                    </select>
                                </div>
                                <div class="info-item">
                                    <label>العنوان</label>
                                    <input class="form-control" type="text" name="address" value="${user.address}">
                                </div>
                            </div>
                            <div style="display:flex; gap:10px; margin-top:15px">
                                <button class="btn-primary" type="submit">حفظ</button>
                                <button class="btn-secondary" type="button" onclick="toggleEditPersonalDetails()">إلغاء</button>
                            </div>
                        </form>
                    </div>







                    <!-- Medical + Emergency -->
                    <div class="two-col" ">

                        <div class="card" style="margin-bottom:0" id="careerDetails">
                            <div class="card-title"> المعلومات المهنية
                                <img src="./svgs/edit.svg" alt="edit" width="35" class="edit-icon" onclick="toggleEditEmergency()">
                            </div>
                            <div class="card-body">
                                <div class="medical-row"><span class="medical-label"> <img src="/svgs/file.svg" alt="file" class="ms-1" />  نبذة عن الطبيب</span>
                                    <span class="text-secondary me-2">
                                        <%
                                            String[] aboutSentences = (String[]) request.getAttribute("about");
                                            if (aboutSentences != null) {
                                                for (String sentence : aboutSentences) {
                                                    sentence = sentence.trim();
                                                    if (!sentence.isEmpty()) {
                                                    %>
                                                    <p style="text-indent: 20px;"><%= sentence %></p>
                                                    <%
                                                    }
                                                }
                                            }
                                        %>
                                    </span>
                                </div>
                                <div class="medical-row"><span class="medical-label"><img src="/svgs/education.svg" alt="education" class="ms-1"/> التعليم والشهادات</span>
                                    <span class="medical-val" style="direction:ltr;text-align:right">
                                        <div class="medical-row">
                                            <a href="/doctors/certification/" class="medical-val">

                                                <img src="/svgs/dot.svg" alt="file" class="ms-2 me-2" />
                                            </a>
                                            <span class="text-secondary me-4">
                                            </span>
                                        </div>

                                    </span></div>

                                    <button onclick="window.location ='/doctors/add-certification'" class="update-btn">اضافة شهاده</button>
                                    <button onclick="window.location ='/doctors/update-career-details'" class="update-btn">تعديل المعلمومات المهنية </button>
                                </div>
                            </div>
                            <div class="card" style="margin-bottom:0" id="careerDetails">
                                <div class="card-title"> المعلومات المهنية
                                    <img src="./svgs/edit.svg" alt="edit" width="35" class="edit-icon" onclick="toggleEditEmergency()">
                                </div>
                                <div class="card-body">
                                    <div class="brief brief-grid">
                                        <div class="brief-card">
                                            <div>
                                                <p class="text-secondary mb-0" >إجمالي المرضى</p>
                                                <p class="text">1,250</p>
                                            </div>
                                        </div>
                                        <div class="brief-card">
                                            <div>
                                                <p class="text-secondary mb-0" > متوسط التقييم</p>
                                                <p class="text-secondary">
                                                    <span class="text">4.9 </span>
                                                    /5
                                                </p>
                                            </div>
                                        </div>
                                        <div class="brief-card">
                                            <div>
                                                <p class="text-secondary mb-0" >سنوات الخبرة </p>
                                                <p class="text-secondary">
                                                    <span class="text">15+ </span>
                                                    عاما
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>


                        </div>


                        <!-- Appointments -->
                        <div class="card" id="appointments">
                            <div class="card-title"> سجل النشاطات الأخيرة
                                <img src="./svgs/view-all.svg" alt="edit" width="35" class="edit-icon">

                            </div>
                            <table>
                                <thead><tr>
                                    <th>النشاط</th><th class="special">التوقيت</th><th>الحالة</th><th>التفاصيل</th>
                                </tr></thead>
                                <tbody>
                                    <tr>
                                        <td>
                                            <div style="display:flex;align-items:center;gap:8px">
                                                <div class="doctor-avatar">سأ</div>
                                                <span> سارة الأحمد</span>
                                            </div>
                                        </td>
                                        <td>24 أكتوبر 2023 | 10:00 ص</td>
                                        <td><span class="badge badge-green">مؤكد</span></td>
                                        <td><a class="btn text-gold">عرض </a></td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div style="display:flex;align-items:center;gap:8px">
                                                <div class="doctor-avatar">خف</div>
                                                <span> خالد الفيصل</span>
                                            </div>
                                        </td>
                                        <td>12 سبتمبر 2023 | 04:30 م</td>
                                        <td><span class="badge badge-gray">مكتمل</span></td>
                                        <td><a class="btn text-gold">عرض </a></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <!-- Notifications + Password -->
                        <div class="two-col">

                            <div class="card" style="margin-bottom:0" id="privacy">
                                <div class="card-title"> تغيير كلمة المرور</div>
                                <div class="card-body">
                                    <form action="users/update-password" method="post" onsubmit="this.querySelector('button[type=submit]').disabled=true">
                                        <div class="mb-3" style="width:100%">
                                            <label for="password" class="form-label">كلمة المرور الحالية </label>
                                            <div class="input-holder">
                                                <input required type="password" name="oldPass" class="form-control" id="password" placeholder="Enter your password">
                                                <span onclick="togglePassword()"><img src="./svgs/lock.svg" alt="icon"></span>
                                            </div>
                                        </div>
                                        <div class="mb-3" style="width:100%">
                                            <label for="password" class="form-label"> كلمة المرور الجديدة </label>
                                            <div class="input-holder">
                                                <input required type="password" name="newPass" class="form-control" id="newPassword" placeholder="Enter your password">
                                                <span onclick="togglePassword()"><img src="./svgs/lock.svg" alt="icon"></span>
                                            </div>
                                        </div>
                                        <button class="update-btn">🔒 تحديث الأمان</button>
                                    </form>
                                </div>
                            </div>

                            <div class="card" style="margin-bottom:0" id="notifications">
                                <div class="card-title">🔔 إعدادات التنبيهات</div>
                                <div class="card-body">
                                    <div class="toggle-row">
                                        <div>
                                            <div class="toggle-label">تنبيهات البريد الإلكتروني</div>
                                            <div class="toggle-sub">تقارير طبية وفواتير إلكترونية</div>
                                        </div>
                                        <label class="toggle"><input type="checkbox" checked><span class="toggle-slider"></span></label>
                                    </div>
                                    <div class="toggle-row">
                                        <div>
                                            <div class="toggle-label">الرسائل النصية SMS</div>
                                            <div class="toggle-sub">تنبيهات المواعيد والتحقق</div>
                                        </div>
                                        <label class="toggle"><input type="checkbox" checked><span class="toggle-slider"></span></label>
                                    </div>
                                    <div class="toggle-row">
                                        <div>
                                            <div class="toggle-label">تذكر المواعيد</div>
                                            <div class="toggle-sub">قبل 24 ساعة من الموعد</div>
                                        </div>
                                        <label class="toggle"><input type="checkbox"><span class="toggle-slider"></span></label>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>

                    <%@ include file="../shared/aside.jsp" %>

                </section>
            </div>
            <%@ include file="../shared/footer.jsp" %>
            <script src="./js/bootstrap.min.js"></script>
            <script >
                function togglePassword() {
                    const input = document.getElementById('password');
                    const input1 = document.getElementById('newPassword');
                    if (input.type === "password") {
                        input.type = "text";
                        input1.type = "text";
                    } else {
                        input.type = "password";
                        input1.type = "password";
                    }
                }
                document.addEventListener("DOMContentLoaded", function() {
                    function setActive() {
                        const hash = window.location.hash || "#profile";
                        const items = document.querySelectorAll(".sidebar-item");
                        
                        items.forEach(item => item.classList.remove("active"));
                        
                        const map = {
                            "#careerDetails":items[0],
                            "#appointments":   items[1],
                            "#emergency":      items[2],
                            "#fullRecord":     items[3],
                            "#privacy":        items[4],
                            "#notifications":  items[5]
                        };
                        
                        if (map[hash]) map[hash].classList.add("active");
                    }
                    
                    setActive(); // run on page load
                    window.addEventListener("hashchange", setActive); // run on hash change
                });
                
                function toggleEditPersonalDetails() {
                    const viewPersonalDetails = document.getElementById("personalDetails");
                    const editPersonalDetails = document.getElementById("personalDetailsEdit");
                    viewPersonalDetails.style.display = viewPersonalDetails.style.display === "none" ? "block" : "none";
                    editPersonalDetails.style.display = editPersonalDetails.style.display === "none" ? "block" : "none";
                }
                
                
            </script>
        </body>
    </html>
