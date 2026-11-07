<%@ page import="org.springframework.web.util.HtmlUtils" %>
<%
    Object toastMessage = request.getAttribute("failMessage");
    String toastType = "error";
    if (toastMessage == null) {
        toastMessage = request.getAttribute("error");
    }
    if (toastMessage == null) {
        toastMessage = request.getAttribute("successMessage");
        toastType = "success";
    }
    if (toastMessage == null) {
        toastMessage = request.getAttribute("message");
        toastType = "success";
    }
%>
<% if (toastMessage != null && !toastMessage.toString().isBlank()) { %>
<span data-toast-message data-toast-type="<%= toastType %>" hidden><%= HtmlUtils.htmlEscape(toastMessage.toString()) %></span>
<% } %>
<script src="/js/toastify.min.js" defer></script>
<script src="/js/app-toast.js" defer></script>
