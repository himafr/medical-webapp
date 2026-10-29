<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.hema.medical_backend_spring.mapper.HelperDto" %>
<%@ page import="com.hema.medical_backend_spring.model.Doctor.Specialty" %>
<%@ page import="com.hema.medical_backend_spring.model.sub.ServiceFeatures" %>
<%@ page import="com.hema.medical_backend_spring.model.MedicalServiceEntity" %>
<%@ page import="java.util.List" %>



<!DOCTYPE html>
<html lang="ar" dir="rtl">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title> update service </title>
        <link rel="stylesheet" href="/css/bootstrap.min.css">
        <link rel="stylesheet" href="/style.css">
        <link rel="stylesheet" href="/css/appointment.css">
        <link rel="stylesheet" href="/responsive.css">
    </head>
    <body>
        <div class="container">
            <%@ include file="../shared/header.jsp" %>

            <section class="text-center">
                <div class="flex-column">
                    <div class="icon-holder">
                        <img src="/svgs/calender.svg" alt="icon"/>
                    </div>
                    <div>
                        <h1 class="text-primary">
                            نموذج تعديل الخدمة
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
                        <%-- add Card --%>
                        <form class="mb-3 mt-3" action="/services/edit-service/${service.id}" method="post" onsubmit="this.querySelector('button[type=submit]').disabled=true">
                            <div class="info-grid " style="grid-template-columns: 1fr" >
                                <div class="info-item ">
                                    <label class="form-label text-primary" >اسم الخدمه  </label>
                                    <input reuired class="form-control" type="text" name="name" style="direction:rtl" value="${service.name}">
                                </div>
                                <div class="mb-3 mt-3">
                                    <label for="description" class="form-label text-primary">وصف الخدمه </label>
                                    <textarea class="form-control" required name="description" id="description" rows="5" placeholder="يرجى كتابة وصف كامل عن الخدمه المقدمه ...">${service.description}</textarea>
                                </div>
                                <div class="info-item">
                                    <label class="form-label" >  الاسعار </label>
                                    <div class="d-flex gap-2">
                                        <input placeholder="السعر" class="form-control" type="number" name="price" style="direction:rtl" value="${service.price}">
                                        <input placeholder="يبدا من / اختياري"  class="form-control" type="number" name="startFrom" style="direction:rtl" value="${service.startFrom}">
                                        <input placeholder="استشاره / اختياري"  class="form-control" type="number" name="consulating" style="direction:rtl" value="${service.consulating}">
                                    </div>
                                </div>

                                <div class="info-item ">
                                    <label for="specialization">اختر التخصص الطبي: </label>
                                    <select id="specialization" name="specialization" class="form-select">
                                        <option value="">اختر التخصص الطبي</option>
                                        <<%
                                        MedicalServiceEntity service = (MedicalServiceEntity) request.getAttribute("service");
                                        String currentSpec = (service != null && service.getSpecialization() != null)
                                        ? service.getSpecialization().toString()
                                        : "GENERAL";
                                        for (Specialty sp : Specialty.values()) {
                                        %>
                                        <option value="<%= sp.name() %>"
                                            <%= sp.name().equals(currentSpec) ? "selected" : "" %>
                                            >
                                            <%= HelperDto.getSpecialtyString(sp) %>

                                        </option>
                                        <%
                                        }
                                    %>
                                </select>
                            </div>

                            <div class="mb-3 mt-3">
                                <div id="features-container" class="grid-2">
                                </div>
                                <button type="button" class="btn btn-secondary mt-2" onclick="addFeature()">+ إضافة ميزة</button>
                            </div>


                        </div>
                        <div style="display:flex; gap:10px; margin-top:15px">
                            <button class="btn-primary" type="submit">حفظ</button>
                            <button class="btn-secondary" type="button" onclick="window.history.back()">إلغاء</button>
                        </div>
                    </form>


                    <%-- //TODO complete the page --%>
                </div>
            </div>
        </section>


    </div>
    <%@ include file="../shared/footer.jsp" %>
    <script src="./js/bootstrap.min.js"></script>
    <script>
        <%
        List<ServiceFeatures> features = (List<ServiceFeatures>) request.getAttribute("features");
        %>
        const existingFeatures = [
        <%
        if (features != null) {
            for (int i = 0; i < features.size(); i++) {
                out.print("\"" + features.get(i).getFeature().toString() + "\"");
                if (i < features.size() - 1) out.print(",");
            }
        }
        %>
        ];
        let featureIndex = 0;
        
        // لما الصفحة تفتح لود الـ existing features
        document.addEventListener("DOMContentLoaded", function() {
            if (existingFeatures.length > 0) {
                existingFeatures.forEach(f => addFeature(f));
            }
        });
        
        function addFeature(value = "") {
            const container = document.getElementById("features-container");
            const div = document.createElement("div");
            div.className = "input-group mb-2";
            div.style.direction = "ltr";
            
            const input = document.createElement("input");
            input.type = "text";
            input.required = true;
            input.name = "features[" + featureIndex + "]";
            input.className = "form-control";
            input.style.direction = "rtl";
            input.placeholder = "أدخل ميزة";
            input.value = value;
            
            const btn = document.createElement("button");
            btn.type = "button";
            btn.className = "btn btn-danger";
            btn.textContent = "حذف";
            btn.onclick = function() { removeFeature(this); };
            
            div.appendChild(input);
            div.appendChild(btn);
            container.appendChild(div);
            featureIndex++;
        }
        
        function reindexFeatures() {
            const inputs = document.querySelectorAll("#features-container input");
            inputs.forEach((input, i) => input.name = `features[${i}]`);
            featureIndex = inputs.length;
        }
        
        function removeFeature(btn) {
            btn.closest(".input-group").remove();
            reindexFeatures();
        }
        
    </script>
</body>
</html>
