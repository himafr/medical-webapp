<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.hema.medical_backend_spring.model.sub.Allergies" %>
<%@ page import="com.hema.medical_backend_spring.model.sub.ChronicDiseases" %>
<%@ page import="com.hema.medical_backend_spring.model.sub.Medications" %>
<%@ page import="com.hema.medical_backend_spring.model.MedicalRecord" %>
<%@ page import="com.hema.medical_backend_spring.model.Doctor.Specialty" %>
<%@ page import="com.hema.medical_backend_spring.model.Doctor" %>
<%@ page import="com.hema.medical_backend_spring.mapper.HelperDto" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="ar" dir="rtl">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title> Home </title>
        <link rel="stylesheet" href="/css/bootstrap.min.css">
        <link rel="stylesheet" href="/style.css">
        <link rel="stylesheet" href="/css/doctor.css">
        <link rel="stylesheet" href="/responsive.css">
    </head>
    <body>
        <div class="container">
            <%@ include file="../shared/header.jsp" %>
            <section>
                <%-- Edit Card --%>
                <div class="card" id="personalDetailsEdit" style="width:100%" >
                    <div class="profile-top">
                        <div class="section-title" style="margin-bottom:0">اضافة شهاده </div>
                    </div>
                    <form class="mb-3 mt-3" action="/doctors/update-career-details" method="post" onsubmit="this.querySelector('button[type=submit]').disabled=true">
                        <div class="info-grid " style="grid-template-columns: 1fr" >

                            <div class="info-item ">
                                <label for="specialty">اختر التخصص الطبي:</label>
                                <select id="specialty" name="specialty" class="form-select">
                                    <option value="">اختر التخصص الطبي</option>
                                    <%
                                        Doctor doctor=(Doctor) request.getAttribute("doctor");
                                        // نجيب كل قيم الـ enum
                                        for (Specialty sp : Specialty.values()) {
                                            // نحدد إذا كان التخصص الحالي هو المختار
                                            boolean selected = (doctor.getSpecialty() == sp);
                                        %>
                                        <option value="<%= sp.name() %>" <%= selected ? "selected=\"selected\"" : "" %>>
                                            <%= HelperDto.getSpecialtyString(sp) %>
                                        </option>
                                        <%
                                        }
                                    %>
                                </select>

                            </div>

                            <div class="info-item ">
                                <label> المسمى الوظيفي </label>
                                <input reuired class="form-control" type="text" name="specialization" value="${doctor.specialization}" style="direction:rtl" >
                            </div>
                            <div class="info-item">
                                <label>   قسم العضويات المهنية أو الانتماءات </label>
                                <input required class="form-control" type="text"  name="work" value="${doctor.work}" style="direction:rtl" >
                            </div>
                            <div class="info-item">
                                <label>نبذة عن الطبيب</label>
                                <textarea class="form-control" type="text" name="about" rows="5" style="direction:rtl" >${doctor.about}</textarea>
                            </div>

                        </div>
                        <div style="display:flex; gap:10px; margin-top:15px">
                            <button class="btn-primary" type="submit">حفظ</button>
                            <button class="btn-secondary" type="button" onclick="window.history.back()">إلغاء</button>
                        </div>
                    </form>


                </section>
            </div>
            <%@ include file="../shared/footer.jsp" %>
            <script src="/js/bootstrap.min.js"></script>

        </body>
    </html>
