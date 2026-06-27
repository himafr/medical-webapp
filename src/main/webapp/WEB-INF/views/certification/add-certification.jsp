<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.hema.medical_backend_spring.model.sub.Allergies" %>
<%@ page import="com.hema.medical_backend_spring.model.sub.ChronicDiseases" %>
<%@ page import="com.hema.medical_backend_spring.model.sub.Medications" %>
<%@ page import="com.hema.medical_backend_spring.model.MedicalRecord" %>
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
                <%-- add Card --%>
                <div class="card" id="personalDetailsEdit" style="width:100%" >
                    <div class="profile-top">
                        <div class="section-title" style="margin-bottom:0">اضافة شهاده </div>
                    </div>
                    <form class="mb-3 mt-3" action="/doctors/add-certification" method="post" onsubmit="this.querySelector('button[type=submit]').disabled=true">
                        <div class="info-grid " style="grid-template-columns: 1fr" >
                            <div class="info-item ">
                                <label>الاسم </label>
                                <input reuired class="form-control" type="text" name="name" style="direction:rtl" >
                            </div>
                            <div class="info-item">
                                <label> المعهد / الجامعه </label>
                                <input required class="form-control" type="text" name="organization" style="direction:rtl" >
                            </div>
                            <div class="info-item">
                                <label> البلد /المدينه</label>
                                <input class="form-control" type="text" name="place" style="direction:rtl" >
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
