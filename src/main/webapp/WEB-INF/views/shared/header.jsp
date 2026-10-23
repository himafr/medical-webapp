<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<header>
    <div class="<%= request.getAttribute("username") != null?"hidden-sm":"" %>">
        <div class="header-logo-holder">
            <img src="/images/round_royal_logo.png" width="48" alt="logo" class="logo">
        </div>
        عيادة الشفاء
    </div>
    <nav>
        <ul>
            <%
                String uri = request.getRequestURI();
                boolean isHome = uri.equals("/WEB-INF/views/home.jsp") ||
                uri.equals("/WEB-INF/views/profile/patient.jsp") ||
                uri.equals("/WEB-INF/views/profile/doctor.jsp") ||
                uri.equals("/WEB-INF/views/profile/admin.jsp");
            %>
            <li><a href="/home" class='<%= isHome ? "active" : "" %>'> الرئيسية</a> </li>
            <li><a href="/appointments/book-appointment" class="<%= request.getRequestURI().equals("/WEB-INF/views/appointment.jsp") ? "active" : "" %>"> حجز موعد </a> </li>
            <li><a href="/about" class="<%= request.getRequestURI().equals("/WEB-INF/views/about.jsp") ? "active" : "" %>">عن العيادة </a> </li>
            <li><a href="/services" class="<%= request.getRequestURI().equals("/WEB-INF/views/services.jsp") ? "active" : "" %>">الخدمات</a> </li>
            <li><a href="/doctors" class="<%= request.getRequestURI().equals("/WEB-INF/views/doctors.jsp") ? "active" : "" %>">الاطباء</a> </li>
            <li><a href="/contact" class="<%= request.getRequestURI().equals("/WEB-INF/views/contact.jsp") ? "active" : "" %>">اتصل بنا </a> </li>
        </ul>
    </nav>


    <div class="menu-toggle">
        ☰
    </div>


    <% if (request.getAttribute("username") == null) { %>
    <div class="header-btn">
        <a href="/signup" type="submit" class="btn btn-secondary " >  انشاء حساب
        </a>
        <a href="/login" type="submit" class="btn btn-primary " >  سجل دخولك
        </a>
    </div>
    <div class="mobile-menu">
        <ul>
            <li><a href="/home" class='<%= request.getRequestURI().equals("/WEB-INF/views/home.jsp") ? "active" : "" %>'> الرئيسية</a> </li>
            <li><a href="/appointments/book-appointment" class="<%= request.getRequestURI().equals("/WEB-INF/views/appointment.jsp") ? "active" : "" %>"> حجز موعد </a> </li>
            <li><a href="/about" class="<%= request.getRequestURI().equals("/WEB-INF/views/about.jsp") ? "active" : "" %>">عن العيادة </a> </li>
            <li><a href="/services" class="<%= request.getRequestURI().equals("/WEB-INF/views/services.jsp") ? "active" : "" %>">الخدمات</a> </li>
            <li><a href="/doctors" class="<%= request.getRequestURI().equals("/WEB-INF/views/doctors.jsp") ? "active" : "" %>">الاطباء</a> </li>
            <li><a href="/contact" class="<%= request.getRequestURI().equals("/WEB-INF/views/contact.jsp") ? "active" : "" %>">اتصل بنا </a> </li>
            <li>
                <a href="/login" class="<%= request.getRequestURI().equals("/WEB-INF/views/login.jsp") ? "active" : "" %>"> سجل دخولك </a> </li>
                <li><a href="/signup" class="<%= request.getRequestURI().equals("/WEB-INF/views/signup.jsp") ? "active" : "" %>"> انشاء حساب </a> </li>
            </ul>
        </div>
        <% }else { %>
        <div class="user-header">
            <img src="/svgs/arrow-left-short.svg" alt="menu" class="user-small-menu">
            <div>
                <p class="text-primary">${username} </p>
                <p class="text-gold">${role =="PATIENT"?"مريض ذهبي ": "دكتور ذهبي " }</p>

            </div>
            <img src="/images/default.png" width="40" alt="user" class="avatar">
        </div>

        <div class="mobile-menu mobile-menu-right" >
            <ul>
                <li><a href="/home" class='<%= request.getRequestURI().equals("/WEB-INF/views/home.jsp") ? "active" : "" %>'> الرئيسية</a> </li>
                <li><a href="/appointments/book-appointment" class="<%= request.getRequestURI().equals("/WEB-INF/views/appointment.jsp") ? "active" : "" %>"> حجز موعد </a> </li>
                <li><a href="/about" class="<%= request.getRequestURI().equals("/WEB-INF/views/about.jsp") ? "active" : "" %>">عن العيادة </a> </li>
                <li><a href="/services" class="<%= request.getRequestURI().equals("/WEB-INF/views/services.jsp") ? "active" : "" %>">الخدمات</a> </li>
                <li><a href="/doctors" class="<%= request.getRequestURI().equals("/WEB-INF/views/doctors.jsp") ? "active" : "" %>">الاطباء</a> </li>
                <li><a href="/contact" class="<%= request.getRequestURI().equals("/WEB-INF/views/contact.jsp") ? "active" : "" %>">اتصل بنا </a> </li>
            </ul>
        </div>
        <% } %>
        <script>
            const toggleBtn = document.querySelector('.menu-toggle');
            const mobileMenu = document.querySelector('.mobile-menu');
            
            toggleBtn.addEventListener('click', () => {
                mobileMenu.style.display =
                mobileMenu.style.display === 'block' ? 'none' : 'block';
            });
            
            document.addEventListener('click', (event) => {
                const isClickInsideMobileMenu = mobileMenu.contains(event.target);
                const isClickOnMenuButton = toggleBtn.contains(event.target);
                
                if (!isClickInsideMobileMenu && !isClickOnMenuButton) {
                    mobileMenu.style.display = 'none';
                }
            });
        </script>
    </header>