<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.hema.medical_backend_spring.model.Appointment" %>
<%@ page import="com.hema.medical_backend_spring.mapper.HelperDto" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="ar" dir="rtl">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title> my appointments | ${username} </title>
        <link rel="icon" type="image/svg+xml" href="/images/round_royal_logo.png">
        <link rel="stylesheet" href="./css/bootstrap.min.css">
        <link rel="stylesheet" href="./style.css">
        <link rel="stylesheet" href="./css/patient.css">
        <link rel="stylesheet" href="./responsive.css">
    </head>
    <body>
        <div class="container">
            <%@ include file="./shared/header.jsp" %>
            <section>
                <!-- Appointments -->
                <div class="card" id="appointments" style="width:100%">
                    <div class="card-title"> مواعيدي القادمة والسابقة

                    </div>
                    <% List<Appointment> appointments = (List<Appointment>) request.getAttribute("appointments"); %>
                    <% if (appointments != null && !appointments.isEmpty()) { %>
                    <table>
                        <thead><tr>
                            <th>الطبيب</th><th class="special">التخصص</th><th>التاريخ والوقت</th><th>الحالة</th><th>الإجراءات</th>
                        </tr></thead>
                        <tbody>
                            <% for (Appointment appointment : appointments) {%>

                            <tr>
                                <td>
                                    <div style="display:flex;align-items:center;gap:8px">
                                        <div class="doctor-avatar"><%= HelperDto.getTwoChars(appointment.getDoctor().getFullName()) %></div>
                                        <span>د. <%= appointment.getDoctor().getFullName() %></span>
                                    </div>
                                </td>
                                <td class="special"><%= appointment.getDoctor().getSpecialization() %></td>
                                <td> <%= HelperDto.getDate(appointment.getAppointmentDate()) %>  |  <%= HelperDto.getTime(appointment.getAppointmentTime()) %> </td>
                                <td><span class="badge badge-green"><%= HelperDto.getStatusString(appointment.getStatus()) %></span></td>
                                <td><a class="action-link">إدارة الموعد</a></td>
                            </tr>
                            <%}%>
                        </tbody>
                    </table>
                    <%}else{%>
                    <p class="text-muted text-center">لا توجد مواعيد مجدولة.</p>
                    <%}%>
                </div>
            </section>
            <section>
            <%@ include file="./shared/pagination.jsp" %>
                </section>
            </div>
            <%@ include file="./shared/footer.jsp" %>
            <script src="./js/bootstrap.min.js"></script>
        </body>
    </html>
