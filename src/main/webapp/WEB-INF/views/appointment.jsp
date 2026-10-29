<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.hema.medical_backend_spring.mapper.HelperDto" %>
<%@ page import="com.hema.medical_backend_spring.model.Doctor.Specialty" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="ar" dir="rtl">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title> Appointment </title>
        <link rel="icon" type="image/svg+xml" href="/images/round_royal_logo.png">
        <link rel="stylesheet" href="/css/bootstrap.min.css">
        <link rel="stylesheet" href="/style.css">
        <link rel="stylesheet" href="/css/appointment.css">
        <link rel="stylesheet" href="/responsive.css">
    </head>
    <body>
        <div class="container">
            <%@ include file="shared/header.jsp" %>
            <section class="text-center">
                <div class="flex-column">
                    <div class="icon-holder">
                        <img src="/svgs/calender.svg" alt="icon"/>
                    </div>
                    <div>
                        <h1 class="text-primary">
                            نموذج حجز المواعيد أونلاين
                        </h1>
                        <div class="app-hr"><span class="footer-hr"></span></div>
                        <p class="text-secondary">
                            يسعدنا انضمامكم لمركزنا. يرجى تعبئة البيانات المطلوبة بدقة لضمان حصولكم على أفضل
                            <br>
                            رعاية طبية في الموعد المفضل.
                        </p>
                    </div>
                    <div class="form row">
                        <div class="top-gradient"></div>
                        <form  method="post" action="/appointments/book-appointment">
                            <div class="flex text-primary mb-2 mt-2" >
                                <img src="/svgs/doc-thing.svg" style="margin-inline:8px;"/>
                                تحديد الخدمة الطبية
                            </div>

                            <div class="row justify-content-around">
                                <div class="mb-3 col-md-5">
                                    <label for="specialization" class="form-label"> القسم / التخصص </label>
                                    <select class="form-control" name="specialization" id="specialization">
                                        <option>اختر القسم المطلوب</option>
                                        <%
                                            for (Specialty sp : Specialty.values()) {
                                            %>
                                            <option value="<%= sp.name() %>" >
                                                <%= HelperDto.getSpecialtyString(sp) %>

                                            </option>
                                            <%
                                            }
                                        %>
                                    </select>
                                </div>



                                <div class="mb-3 col-md-5">
                                    <label for="doctor_id" class="form-label"> الطبيب المختص </label>
                                    <select class="form-control" id="doctor_id" name="doctorId">
                                        <option >اختر الطبيب المفضل</option>
                                    </select>
                                </div>

                                <div class="mb-3 col-md-11 mx-auto">
                                    <label for="service_id" class="form-label"> الخدمة الطبية </label>
                                    <select class="form-control" id="service_id" name="serviceId">
                                        <option >اختر الخدمه المناسبه</option>
                                    </select>
                                </div>
                            </div>

                            <div class="flex text-primary mb-2 mt-2 " >
                                <img src="/svgs/date-out.svg" style="margin-inline:8px;"/>
                                الموعد المقترح
                            </div>

                            <div class="row justify-content-around">

                                <div class="mb-3 col-md-5">
                                    <label for="appointmentDate" class="form-label">تاريخ الزيارة</label>
                                    <input type="date" class="form-control" id="appointmentDate" name="appointmentDate" disabled>


                                    <div class="note mt-4 text-secondary flex">
                                        <img src="/svgs/info.svg" alt="icon" />
                                        المواعيد تخضع للتأكيد النهائي من خلال اتصال قسم المواعيد بكم.
                                    </div>

                                </div>



                                <div class="mb-3 col-md-5">
                                    <div id="time-slots-container" style="display:none" class="mt-3">
                                        <label class="form-label">الفترات المتاحة</label>
                                        <div class="row justify-content-between">
                                            <label class="time-slot col-md-5 mb-3">
                                                <input type="radio" required name="appointmentTime" value="09:00" hidden>
                                                ص 9:00
                                            </label>
                                            <label class="time-slot col-md-5 mb-3">
                                                <input type="radio" required name="appointmentTime" value="11:30" hidden>
                                                ص 11:30
                                            </label>
                                            <label class="time-slot col-md-5 mb-3">
                                                <input type="radio" required name="appointmentTime" value="16:30" hidden>
                                                م 4:30
                                            </label>
                                            <label class="time-slot col-md-5 mb-3">
                                                <input type="radio" required name="appointmentTime" value="19:00" hidden>
                                                م 7:00
                                            </label>
                                        </div>
                                    </div>
                                </div>

                                <div class="mb-3 mt-3 col-12">
                                    <label for="notes" class="form-label text-primary">ملاحظات طبية إضافية</label>
                                    <textarea class="form-control" id="notes" name="notes" rows="5" placeholder="يرجى كتابة أي ملاحظات أو استفسارات طبية ترغب في مشاركتها مع الطبيب..."></textarea>
                                </div>
                            </div>

                            <div class="row mb-5 mt-5 justify-content-around">
                                <p class="text-secondary col-md-5">
                                    بتقديم هذا الطلب، فإنك توافق على سياسة الخصوصية الخاصة
                                    <br>
                                    بالمركز الطبي واستخدام بياناتك لأغراض جدولة المواعيد.
                                </p>
                                <button type="submit" class="btn-primary col-md-4"  >  تأكيد طلب الحجز
                                    <img src="/svgs/appointment.svg" alt="icon" style="margin-right: 16px;">
                                </button>
                            </div>
                        </form>
                    </div>
                </section>
                <section class="row justify-content-around features-section">

                    <div class="flex-column  col-md-3 ">
                        <div class="feature-icon mb-4">
                            <img src="/svgs/certification.svg" alt="icon"/>
                        </div>

                        <h4 class="text-primary">شهادات عالمية</h4>
                        <p class="text-secondary">معايير جودة طبية تتوافق مع البروتوكولات العالمية
                        </p>
                    </div>
                    <div class="flex-column  col-md-3 text-center ">
                        <div class="feature-icon mb-4">
                            <img src="/svgs/privacy.svg" alt="icon"/>
                        </div>
                        <h4 class="text-primary"> خصوصية تامة</h4>
                        <p class="text-secondary">بياناتكم الطبية مشفرة ومحفوظة بأعلى درجات السرية
                        </p>
                    </div>
                    <div class="flex-column  col-md-3 ">
                        <div class="feature-icon mb-4">
                            <img src="/svgs/support.svg" alt="icon"/>
                        </div>

                        <h4 class="text-primary"> دعم متواصل</h4>
                        <p class="text-secondary">
                            فريق التنسيق الطبي متاح للإجابة على اتصالاتكم
                        </p>
                    </div>
                </section>

            </div>
            <%@ include file="shared/footer.jsp" %>
            <script src="/js/bootstrap.min.js"></script>
            <script src="/js/appointment.js"></script>

        </body>
    </html>
