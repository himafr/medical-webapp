<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.hema.medical_backend_spring.mapper.HelperDto" %>
<%@ page import="com.hema.medical_backend_spring.model.Doctor.Specialty" %>


<!DOCTYPE html>
<html lang="ar" dir="rtl">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title> Appointment </title>
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
                            نموذج اضافة خدمة أونلاين
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
                        <form class="mb-3 mt-3" action="/services/add-service" method="post" onsubmit="this.querySelector('button[type=submit]').disabled=true">
                            <div class="info-grid " style="grid-template-columns: 1fr" >
                                <div class="info-item ">
                                    <label class="form-label text-primary" >اسم الخدمه  </label>
                                    <input reuired class="form-control" type="text" name="name" style="direction:rtl" >
                                </div>
                                <div class="mb-3 mt-3">
                                    <label for="description" class="form-label text-primary">وصف الخدمه </label>
                                    <textarea class="form-control" required name="description" id="description" rows="5" placeholder="يرجى كتابة وصف كامل عن الخدمه المقدمه ..."></textarea>
                                </div>
                                <div class="info-item">
                                    <label class="form-label" >  الاسعار </label>
                                    <div class="d-flex gap-2">
                                        <input placeholder="السعر" class="form-control" type="number" name="price" style="direction:rtl" >
                                        <input placeholder="يبدا من / اختياري"  class="form-control" type="number" name="startFrom" style="direction:rtl" >
                                        <input placeholder="استشاره / اختياري"  class="form-control" type="number" name="consulating" style="direction:rtl" >
                                    </div>
                                </div>

                                <div class="info-item ">
                                    <label for="specialization">اختر التخصص الطبي:</label>
                                    <select id="specialization" name="specialization" class="form-select">
                                        <option value="">اختر التخصص الطبي</option>
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



                    </div>
                </div>
            </section>


        </div>
        <%@ include file="../shared/footer.jsp" %>
        <script src="./js/bootstrap.min.js"></script>
        <script>
            let featureIndex = 0;
            
            function addFeature() {
                const container = document.getElementById("features-container");
                const div = document.createElement("div");
                div.className = "input-group mb-2";
                div.style.direction = "ltr";
                div.innerHTML = `
                <input type="text" required  name="features[${featureIndex}]" class="form-control" style="direction: rtl;" placeholder="أدخل ميزة">
                <button type="button" class="btn btn-danger" onclick="removeFeature(this)">حذف</button>
                `;
                container.appendChild(div);
                featureIndex++;
            }
            
            function removeFeature(btn) {
                btn.closest(".input-group").remove();
                reindexFeatures();
            }
            
            function reindexFeatures() {
                const inputs = document.querySelectorAll("#features-container input");
                inputs.forEach((input, i) => input.name = `features[${i}]`);
                featureIndex = inputs.length;
            }
        </script>
    </body>
</html>
