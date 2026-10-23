<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.hema.medical_backend_spring.model.MedicalServiceEntity" %>
<%@ page import="com.hema.medical_backend_spring.model.sub.ServiceFeatures" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="ar" dir="rtl">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title> services | <%= request.getParameter("specialty") != null ? request.getParameter("specialty") : "all" %>  </title>
        <link rel="icon" type="image/svg+xml" href="/images/round_royal_logo.png">
        <link rel="stylesheet" href="./css/bootstrap.min.css">
        <link rel="stylesheet" href="./style.css">
        <link rel="stylesheet" href="./css/services.css">
        <link rel="stylesheet" href="./responsive.css">
    </head>
    <body>
        <div class="container">
            <%@ include file="shared/header.jsp" %>
            <section class="text-center">
                <div class="flex-column service-hero">
                    <p class="btn-gold">تأسست لترتقي بتجربتك الصحية</p>
                    <div>
                        <h1 class="text-primary ">
                            رعاية صحية ملكية تليق بك وبعائلتك
                        </h1>
                        <p class="text-secondary">
                            نجمع بين الخبرة الطبية العريقة وأحدث التقنيات العالمية في أجواء من الفخامة والخصوصية
                            <br>
                            التامة، لضمان رحلة علاجية استثنائية.
                        </p>
                    </section>
                    <section class="section-two">

                        <%
                            String specialtyParam = request.getParameter("specialty");
                        %>
                        <button class="<%= (specialtyParam == null || specialtyParam.isEmpty()) ? "btn-primary" : "btn-secondary" %>"
                            onclick="window.location = '/services'">الكل
                        </button>

                        <button class="<%= "general".equalsIgnoreCase(specialtyParam) ? "btn-primary" : "btn-secondary" %>"
                            onclick="window.location = '/services?specialty=general'">طب عام
                        </button>

                        <button class="<%= "dentistry".equalsIgnoreCase(specialtyParam) ? "btn-primary" : "btn-secondary" %>"
                            onclick="window.location = '/services?specialty=dentistry'">أسنان
                        </button>

                        <button class="<%= "dermatology".equalsIgnoreCase(specialtyParam) ? "btn-primary" : "btn-secondary" %>"
                            onclick="window.location = '/services?specialty=dermatology'">جلدية
                        </button>

                        <button class="<%= "ophthalmology".equalsIgnoreCase(specialtyParam) ? "btn-primary" : "btn-secondary" %>"
                            onclick="window.location = '/services?specialty=ophthalmology'">عيون
                        </button>

                        <button class="<%= "cardiology".equalsIgnoreCase(specialtyParam) ? "btn-primary" : "btn-secondary" %>"
                            onclick="window.location = '/services?specialty=cardiology'">القلب والأوعية الدموية
                        </button>

                        <button class="<%= "cosmetic_dermatology".equalsIgnoreCase(specialtyParam) ? "btn-primary" : "btn-secondary" %>"
                            onclick="window.location = '/services?specialty=cosmetic_dermatology'">الجلدية والتجميل
                        </button>

                        <button class="<%= "pediatrics".equalsIgnoreCase(specialtyParam) ? "btn-primary" : "btn-secondary" %>"
                            onclick="window.location = '/services?specialty=pediatrics'">طب الأطفال
                        </button>

                        <button class="<%= "family_medicine".equalsIgnoreCase(specialtyParam) ? "btn-primary" : "btn-secondary" %>"
                            onclick="window.location = '/services?specialty=family_medicine'">طب الأسرة
                        </button>
                    </section>
                </div>

                <section class="section-three">
                    <div class="grid-3 gap-5">
                        <% List<MedicalServiceEntity> services =(List<MedicalServiceEntity>) request.getAttribute("services"); %>
                        <% for(MedicalServiceEntity service:services){%>

                        <div class="gray-card  mb-3">
                            <div class="flex-between">
                                <p class="btn-square-gold">
                                    <img src="/svgs/<%= service.getSpecialization() %>.svg" alt="icon" />
                                </p>
                                <%-- <p class="btn-gold">الأكثر طلباً</p> --%>
                            </div>

                            <h4 class="text-primary mt-3"> <%= service.getName()%>
                            </h4>
                            <p class="text-secondary mb-3">
                                <%= service.getDescription() %>
                            </p>

                            <% for(ServiceFeatures feature: service.getFeatures()) { %>

                            <div class="text-icon ">
                                <img src="./svgs/check-mark.svg" alt="logo" width="16" >
                                <p class="text-secondary">
                                    <%= feature.getFeature() %>
                                </p>
                            </div>
                            <% } %>

                            <div class="flex-between mt-3">
                                <div>
                                    <% if (service.getConsulating() != null && service.getConsulating() !=0 ) { %>
                                    <div class="text-primary"> الاستشارة</div>
                                    <p class="text-primary"><%= service.getConsulating() %> <span class="text-gold">ر.س </span></p>
                                    <% } else if(service.getStartFrom() !=null && service.getStartFrom()!=0) { %>
                                    <div class="text-primary">تبدأ الجلسات من</div>
                                    <p class="text-primary"><%= service.getStartFrom() %> <span class="text-gold">ر.س </span></p>

                                    <%}else {%>
                                    <div class="text-primary">سعر الكشف</div>
                                    <p class="text-primary"><%= service.getPrice() %> <span class="text-gold">ر.س </span></p>

                                    <%}%>
                                </div>
                                <div class="flex">
                                    <a class="btn-primary me-2" href="/appointments/book-appointment?serviceId=<%= service.getId() %>&specialization=<%= service.getSpecialization()%>">احجز الآن</a>
                                </div>
                            </div>
                        </div>
                        <%}%>
                    </div>
                    <% if(services.isEmpty()) { %>
                    <p class="text-secondary" style="text-align: center;">لا توجد خدمات متاحة.</p>
                    <% } %>
                </section>

                <section class="bg-gray full-width-section">
                    <div class="container">
                        <div class="row align-items-center">

                            <div class="col-md-12" >
                                <h2 class="text-primary head-line-right-border ">
                                    الأقسام التخصصية
                                </h2>
                                <div class="sub-header-holder">
                                    <p class="lead text-secondary">
                                        خبرات طبية متكاملة لضمان حياة صحية أفضل
                                    </p>
                                    <a href="#" class="btn-link">
                                        اكتشف جميع الأقسام
                                        <img src="/svgs/arrow-left-short.svg" alt="icon" />
                                    </a>
                                </div>
                            </div>

                            <div class="row" style="justify-content:space-around;gap:16px">
                                <div class="col-md-3">
                                    <div class="card" style="width: 22rem;">
                                        <div class="text-in-img">
                                            <img src="/images/d1.png" class="card-img-top" alt="image">
                                            <div class="overlay"></div>
                                            <h5 class="card-title">
                                                <img src="/svgs/doc1.svg" alt="icon" style="margin: 10px;"/>
                                                رعاية الأمومة والطفولة
                                            </h5>
                                        </div>
                                        <div class="card-body">
                                            <p class="card-text text-secondary">
                                                متابعة دقيقة لمراحل الحمل والولادة بأعلى معايير
                                                الأمان والراحة للأم والجنين.
                                            </p>
                                            <a href="#" class="btn-link">تفاصيل القسم
                                                <img src="/svgs/arrow-left-long.svg" alt="icon" />
                                            </a>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-3">
                                    <div class="card" style="width: 22rem;">
                                        <div class="text-in-img">
                                            <img src="/images/d2.png" class="card-img-top" alt="image">
                                            <div class="overlay"></div>
                                            <h5 class="card-title">
                                                <img src="/svgs/doc2.svg" alt="icon" style="margin: 10px;"/>
                                                مركز التأهيل الطبي
                                            </h5>
                                        </div>
                                        <div class="card-body">
                                            <p class="card-text text-secondary">
                                                أحدث تقنيات العلاج الطبيعي لاستعادة الحيوية والنشاط
                                                بعد الإصابات والعمليات.
                                            </p>
                                            <a href="#" class="btn-link">تفاصيل القسم
                                                <img src="/svgs/arrow-left-long.svg" alt="icon" />
                                            </a>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-3">
                                    <div class="card" style="width: 22rem;">
                                        <div class="text-in-img">
                                            <img src="/images/d3.png" class="card-img-top" alt="image">
                                            <div class="overlay"></div>
                                            <h5 class="card-title">
                                                <img src="/svgs/doc3.svg" alt="icon" style="margin: 10px;"/>
                                                المختبر الرقمي المتطور
                                            </h5>
                                        </div>
                                        <div class="card-body">
                                            <p class="card-text text-secondary">
                                                نتائج تحليلية فورية وبدقة متناهية تدعم قرارات الأطباء
                                                التشخيصية بكفاءة عالية.
                                            </p>
                                            <a href="#" class="btn-link">تفاصيل القسم
                                                <img src="/svgs/arrow-left-long.svg" alt="icon" />
                                            </a>
                                        </div>
                                    </div>
                                </div>


                            </div>
                        </div>
                    </div>
                </section>
                <section class="flex justify-content-center ">
                    <div class="green-card row align-items-center justify-content-between text-center">
                        <div  class="mb-3">
                            <img src="/svgs/shield.svg" alt="icon" width="50px">
                        </div>
                        <h2 class="mb-md-5">
                            صحتك أمانة.. ومكانها عيادة
                            الشفاء
                        </h2>
                        <p class='text-gold'>
                            انضم إلى آلاف المستفيدين من خدماتنا الطبية المتميزة. نحن هنا لخدمتك على مدار الساعة
                            بمهنية وفخامة.
                        </p>

                        <div class="row flex justify-content-around mt-md-5" style="gap:16px;">
                            <a href="/appointments/book-appointment" class="btn btn-gold btn-lg text-gold col-5" style="border-radius: 12px; line-height: normal;"> احجز موعدك الآن</a>
                            <a href="/contact" class="btn btn-secondary btn-lg text-white col-5" style="background-color: inherit;">
                                تحدث مع خدمة العملاء</a>
                            </div>
                        </div>
                    </section>
                    <%@ include file="shared/footer.jsp" %>
                    <script src="./js/bootstrap.min.js"></script>
                </body>
            </html>
