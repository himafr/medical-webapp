<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% if (request.getAttribute("role").toString().equals("PATIENT")) { %>
<aside class="sidebar">
    <div class="sidebar-item " onclick="window.location.hash ='#personalDetails'">
        <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><circle cx="12" cy="8" r="4"/><path d="M4 20c0-4 3.6-7 8-7s8 3 8 7"/></svg>
        المعلومات الشخصية
    </div>
    <div class="sidebar-item" onclick="window.location.hash ='#appointments'">
        <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><rect x="3" y="4" width="18" height="18" rx="2"/><path d="M16 2v4M8 2v4M3 10h18"/></svg>
        مواعيدي
    </div>
    <div class="sidebar-item" onclick="window.location.hash ='#emergency'">
        <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><path d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2"/></svg>
        السجل الطبي
    </div>
    <div class="sidebar-item" onclick="window.location.hash ='#fullRecord'">
        <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><path d="M14 2H6a2 2 0 00-2 2v16a2 2 0 002 2h12a2 2 0 002-2V8z"/><polyline points="14 2 14 8 20 8"/></svg>
        الملف الطبي الكامل
    </div>
    <div class="sidebar-item" onclick="window.location.hash ='#privacy'">
        <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><path d="M12 22s8-4 8-10V5l-8-3-8 3v7c0 6 8 10 8 10z"/></svg>
        الأمان
    </div>
    <div class="sidebar-item" onclick="window.location.hash ='#notifications'">
        <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><path d="M18 8A6 6 0 006 8c0 7-3 9-3 9h18s-3-2-3-9"/><path d="M13.73 21a2 2 0 01-3.46 0"/></svg>
        التنبيهات
    </div>
    <div class="sidebar-item sidebar-logout" onclick="window.location='/logout'">
        <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <path d="M9 21H5a2 2 0 01-2-2V5a2 2 0 012-2h4M16 17l5-5-5-5M21 12H9"/>
        </svg>
        تسجيل الخروج
    </div>
    <form action="/users/delete-account" method="post" onsubmit="return confirm('هل أنت متأكد أنك تريد حذف هذا الحساب؟');">
        <button class="btn btn-danger" type="submit" >مسح الاكونت </button>
    </form>
</aside>
<%  }else if (request.getAttribute("role").toString().equals("DOCTOR")) { %>

<aside class="sidebar">
    <div class="sidebar-item " onclick="window.location.hash ='#careerDetails'">
        <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><circle cx="12" cy="8" r="4"/><path d="M4 20c0-4 3.6-7 8-7s8 3 8 7"/></svg>
        المعلومات المهنية
    </div>
    <div class="sidebar-item" onclick="window.location.hash ='#appointments'">
        <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><rect x="3" y="4" width="18" height="18" rx="2"/><path d="M16 2v4M8 2v4M3 10h18"/></svg>
        مواعيدي
    </div>

    <div class="sidebar-item" onclick="window.location.hash ='#privacy'">
        <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><path d="M12 22s8-4 8-10V5l-8-3-8 3v7c0 6 8 10 8 10z"/></svg>
        الأمان
    </div>
    <div class="sidebar-item" onclick="window.location.hash ='#notifications'">
        <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><path d="M18 8A6 6 0 006 8c0 7-3 9-3 9h18s-3-2-3-9"/><path d="M13.73 21a2 2 0 01-3.46 0"/></svg>
        التنبيهات
    </div>
    <div class="sidebar-item sidebar-logout" onclick="window.location='/logout'">
        <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <path d="M9 21H5a2 2 0 01-2-2V5a2 2 0 012-2h4M16 17l5-5-5-5M21 12H9"/>
        </svg>
        تسجيل الخروج
    </div>
    <form action="/users/delete-account" method="post" onsubmit="return confirm('هل أنت متأكد أنك تريد حذف هذا الحساب؟');">
        <button class="btn btn-danger" type="submit" >مسح الاكونت </button>
    </form>
</aside>

<% }else {%>

<aside class="sidebar">
    <div class="sidebar-item " onclick="window.location.hash ='#careerDetails'">
        <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><circle cx="12" cy="8" r="4"/><path d="M4 20c0-4 3.6-7 8-7s8 3 8 7"/></svg>
        المعلومات المهنية
    </div>
    <div class="sidebar-item" onclick="window.location.hash ='#appointments'">
        <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><rect x="3" y="4" width="18" height="18" rx="2"/><path d="M16 2v4M8 2v4M3 10h18"/></svg>
        سجل النشاطات
    </div>

    <div class="sidebar-item" onclick="window.location.hash ='#privacy'">
        <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><path d="M12 22s8-4 8-10V5l-8-3-8 3v7c0 6 8 10 8 10z"/></svg>
        الأمان
    </div>
    <div class="sidebar-item" onclick="window.location.hash ='#notifications'">
        <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><path d="M18 8A6 6 0 006 8c0 7-3 9-3 9h18s-3-2-3-9"/><path d="M13.73 21a2 2 0 01-3.46 0"/></svg>
        التنبيهات
    </div>
    <div class="sidebar-item sidebar-logout" onclick="window.location='/logout'">
        <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <path d="M9 21H5a2 2 0 01-2-2V5a2 2 0 012-2h4M16 17l5-5-5-5M21 12H9"/>
        </svg>
        تسجيل الخروج
    </div>
    <form action="/users/delete-account" method="post" onsubmit="return confirm('هل أنت متأكد أنك تريد حذف هذا الحساب؟');">
        <button class="btn btn-danger" type="submit" >مسح الاكونت </button>
    </form>
</aside>

<% } %>

<script>
    
    const sidebar = document.querySelector('.sidebar');
    const smallMenu = document.querySelector('.user-small-menu');
    smallMenu.addEventListener('click', () => {
        sidebar.style.display = 
        sidebar.style.display === 'block' ? 'none' : 'block';
    });

    document.addEventListener('click', (event) => {
  const isClickInsideSidebar = sidebar.contains(event.target);
  const isClickOnMenuButton = smallMenu.contains(event.target);

  if (!isClickInsideSidebar && !isClickOnMenuButton) {
    if(window.innerWidth <= 600){
    sidebar.style.display = 'none';
    }
  }
});
</script>