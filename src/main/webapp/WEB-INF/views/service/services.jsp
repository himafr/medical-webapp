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
        <link rel="stylesheet" href="./css/bootstrap.min.css">
        <link rel="stylesheet" href="./style.css">
        <link rel="stylesheet" href="./css/services.css">
        <link rel="stylesheet" href="./responsive.css">
    </head>
    <body>
        <div class="container">
            <%@ include file="../shared/header.jsp" %>
            <div class="floating-button" onclick="window.location='/services/add-service'">
                <img src="/svgs/add-plus-circle.svg" alt="icon" width="48" />
            </div>

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
            <div class="grid-3 gap-3">
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
                            <form action="/services/delete-service/<%= service.getId() %>" method="post" onsubmit="return confirm('هل أنت متأكد أنك تريد حذف هذه الخدمة؟');">
                                <button class="btn-secondary "> مسح</button>
                            </form>

                            <a class="btn-primary me-2" href="/services/edit/<%= service.getId() %>">تعديل</a>
                        </div>
                    </div>
                </div>

                <%}%>



            </div>
            <% if(services.isEmpty()) { %>
            <p class="text-secondary" style="text-align: center;">لا توجد خدمات متاحة.</p>
            <% } %>
        </section>

        <%@ include file="../shared/footer.jsp" %>
        <script src="./js/bootstrap.min.js"></script>
    </body>
</html>
