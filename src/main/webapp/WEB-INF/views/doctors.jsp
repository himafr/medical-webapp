<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.hema.medical_backend_spring.model.Doctor" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="ar" dir="rtl">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title> Doctors | <%= request.getParameter("specialty") != null ? request.getParameter("specialty") : "all" %> </title>
        <link rel="stylesheet" href="/css/bootstrap.min.css">
        <link rel="stylesheet" href="/style.css">
        <link rel="stylesheet" href="/css/doctors.css">
        <link rel="stylesheet" href="/responsive.css">
    </head>
    <body>
        <div class="container">
            <%@ include file="shared/header.jsp" %>

            <section>
                <h1 class="text-primary">
                    نخبة
                    <span>الأطباء والمتخصصين</span>
                </h1>
                <p class="text-secondary">
                    جودة الرعاية تبدأ من اختيار الكفاءات. نضع بين أيديكم فريقاً من أمهر
                    <br>
                    الاستشاريين في بيئة طبية فاخرة.
                </p>
                <form method="get" action="/doctors/search" >
                    <div class="mb-3 col-md-6" >
                        <div  class="input-holder">
                            <input required type="text"  class="form-control"  placeholder="ادخل اسم طبيب او معلومه عنه " style="padding: 13px 48px 13px 16px; direction: rtl; border-radius: 24px; " name="s">
                            <span onclick="this.closest('form').submit()" style="cursor:pointer;">   <img src="/svgs/search.svg" alt="email icon">
                            </span>
                        </div>
                    </div>
                </form>
            </section>

            <section class="section-buttons">
                <%
                    String specialtyParam = request.getParameter("specialty");
                %>
                <button class="<%= (specialtyParam == null || specialtyParam.isEmpty()) ? "btn-primary" : "btn-secondary" %>"
                    onclick="window.location = '/doctors'">الكل
                </button>

                <button class="<%= "general".equalsIgnoreCase(specialtyParam) ? "btn-primary" : "btn-secondary" %>"
                    onclick="window.location = '/doctors?specialty=general'">طب عام
                </button>

                <button class="<%= "dentistry".equalsIgnoreCase(specialtyParam) ? "btn-primary" : "btn-secondary" %>"
                    onclick="window.location = '/doctors?specialty=dentistry'">أسنان
                </button>

                <button class="<%= "dermatology".equalsIgnoreCase(specialtyParam) ? "btn-primary" : "btn-secondary" %>"
                    onclick="window.location = '/doctors?specialty=dermatology'">جلدية
                </button>

                <button class="<%= "ophthalmology".equalsIgnoreCase(specialtyParam) ? "btn-primary" : "btn-secondary" %>"
                    onclick="window.location = '/doctors?specialty=ophthalmology'">عيون
                </button>

                <button class="<%= "cardiology".equalsIgnoreCase(specialtyParam) ? "btn-primary" : "btn-secondary" %>"
                    onclick="window.location = '/doctors?specialty=cardiology'">القلب والأوعية الدموية
                </button>

                <button class="<%= "cosmetic_dermatology".equalsIgnoreCase(specialtyParam) ? "btn-primary" : "btn-secondary" %>"
                    onclick="window.location = '/doctors?specialty=cosmetic_dermatology'">الجلدية والتجميل
                </button>

                <button class="<%= "pediatrics".equalsIgnoreCase(specialtyParam) ? "btn-primary" : "btn-secondary" %>"
                    onclick="window.location = '/doctors?specialty=pediatrics'">طب الأطفال
                </button>

                <button class="<%= "family_medicine".equalsIgnoreCase(specialtyParam) ? "btn-primary" : "btn-secondary" %>"
                    onclick="window.location = '/doctors?specialty=family_medicine'">طب الأسرة
                </button>
            </section>


            <section >
                <div class="row justify-content-between">
                    <% List<Doctor> doctors =(List<Doctor>) request.getAttribute("doctors"); %>
                    <% for(Doctor doctor:doctors){%>
                    <div class="col-md-3">
                        <div class="card card-doctor" >
                            <div class="tag-in-img">
                                <img src="/images/doc1.png" class="card-img-top" alt="image">
                                <div class="img-tag">
                                    <p class="btn-primary">
                                        <img src="/svgs/star-outlet.svg" alt="star icon">
                                        <span style="color:white;">4.9</span>
                                    </p>
                                </div>
                            </div>
                            <div class="card-body">
                                <div class="btn-primary">   <%= doctor.getSpecialtyString() %> </div>
                                <h3 class="text-primary">  د. <%= doctor.getFullName() %>  </h3>
                                <h6 class="text-gold">
                                    <%= (doctor.getSpecialization() != null && doctor.getSpecialization().split(" ").length > 0
                                    ? doctor.getSpecialization().split(" ")[0] : "") %>
                                    <%= (doctor.getSpecialization() != null && doctor.getSpecialization().split(" ").length > 1
                                    ? doctor.getSpecialization().split(" ")[1] : "") %>
                                    - خبرة 15 عاماً</h6>
                                    <p class="text-secondary">
                                        <%
                                            String about = doctor.getAbout();
                                            int maxLength = 62;
                                            String safeText = (about != null && about.length() > maxLength)
                                            ? about.substring(0, maxLength)
                                            : about;
                                        %>
                                        <%= safeText %>...
                                    </p>
                                    <a href="/appointments/book-appointment?doctorId=<%=doctor.getId()%>&specialization=<%= doctor.getSpecialty()%>"class="btn-gold flex-between">
                                    
                                        <p class="text-primary">     احجز مع الطبيب  </p>
                                        <img width="18" src="/svgs/arrow-left-short-dark.svg" alt="icon" />
                                    </a>
                                </div>
                            </div>
                        </div>
                        <%}%>
                    </div>

                    <% if(doctors.isEmpty()) { %>
                    <p class="text-secondary" style="text-align: center;">لا يوجد اطباء متاحين في هذا المجال الان .</p>
                    <% } %>
                </section>
            <%@ include file="shared/pagination.jsp" %>

                <section>
                    <div class="green-card row align-items-center justify-content-around " style="margin-top: 64px; padding: 32px;">
                        <div  class="col-9 " >
                            <h2 class="mb-md-5" style="text-align: justify;">
                                هل تنتمي إلى
                                <span class="text-gold">النخبة؟</span>
                            </h2>
                            <p class='text-gold' style="text-align: justify;">
                                نحن نبحث دائماً عن الأفضل. انضم إلى صرحنا الطبي الفاخر وساهم في رسم
                                مستقبل الرعاية الصحية الراقية.
                            </p>
                        </div>

                        <div class="col-md ">
                            <a href="#" class="btn btn-gold btn-lg text-gold col-5" style="border-radius: 12px; line-height: normal; width: 100%;"> انضم لفريقنا </a>
                        </div>
                    </div>
                </section>
            </div>


            <%@ include file="shared/footer.jsp" %>
            <script src="/js/bootstrap.min.js"></script>
        </body>
    </html>
