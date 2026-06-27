<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    Integer page1 = (Integer) request.getAttribute("page");
    Integer number = (Integer) request.getAttribute("number");

    int window = 6;
    if (page1 != null && number != null && number > 1) {
%>

<ul class="pagination justify-content-center">

    <li class="page-item <%= (page1 == 0 ? "disabled" : "") %>">
        <a class="page-link" href="?page=<%=page1-1%>">السابق</a>
    </li>

    <li class="page-item <%= (page1 == 0 ? "active" : "") %>">
        <a class="page-link" href="?page=0">1</a>
    </li>

    <% if (page1 > window) { %>
    <li class="page-item disabled"><span class="page-link">...</span></li>
    <% } %>

    <%
        int start = Math.max(1, page1 - window/2);
        int end = Math.min(number-2, page1 + window/2);
        
        for (int i = start; i <= end; i++) {
        %>
        <li class="page-item <%= (i == page1 ? "active" : "") %>">
            <a class="page-link" href="?page=<%=i%>"><%=i+1%></a>
        </li>
        <% } %>

        <%-- لو باقي صفحات بعد النافذة --%>
        <% if (page1 < number - window - 1) { %>
        <li class="page-item disabled"><span class="page-link">...</span></li>
        <% } %>

        <%-- آخر صفحة --%>
        <% if (number > 1) { %>
        <li class="page-item <%= (page1 == number-1 ? "active" : "") %>">
            <a class="page-link" href="?page=<%=number-1%>"><%=number%></a>
        </li>
        <% } %>

        <%-- Next --%>
        <li class="page-item <%= (page1 == number-1 ? "disabled" : "") %>">
            <a class="page-link" href="?page=<%=page1+1%>">التالي</a>
        </li>
    </ul>
    <%}%>