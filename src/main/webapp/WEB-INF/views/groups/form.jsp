<%@ page import="org.azamov.learnjakarta.lms_system.Group" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Group group = (Group) request.getAttribute("group");
    String action = (String) request.getAttribute("action");
    String defaultCreatedAt = (String) request.getAttribute("defaultCreatedAt");
    boolean edit = group != null && group.getId() != null;
%>
<!DOCTYPE html>
<html>
<head>
    <title><%= edit ? "Guruhni Tahrirlash" : "Yangi Guruh" %></title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/lms.css">
</head>
<body>
<div class="page-shell">
    <div class="topbar">
        <div class="brand">
            <div class="brand-mark">L</div>
            <div class="brand-text">
                <strong>Learn Jakarta</strong>
                <span>Guruh formasi</span>
            </div>
        </div>
        <div class="topbar-nav">
            <a class="nav-link" href="<%= request.getContextPath() %>/">Bosh sahifa</a>
            <a class="nav-link active" href="<%= request.getContextPath() %>/groups">Guruhlar</a>
            <a class="nav-link" href="<%= request.getContextPath() %>/students">Talabalar</a>
        </div>
    </div>
    <div class="page-header">
        <div>
            <span class="eyebrow">Guruh Formasi</span>
            <h1 class="page-title"><%= edit ? "Guruhni Tahrirlash" : "Yangi Guruh Qo'shish" %></h1>
            <p class="page-subtitle">Guruh ma'lumotlarini kiriting va saqlang.</p>
        </div>
        <div class="page-actions">
            <a class="action-btn secondary" href="<%= request.getContextPath() %>/groups">Orqaga</a>
        </div>
    </div>
    <section class="form-shell">
        <form action="<%= action %>" method="post">
            <% if (edit) { %>
            <input type="hidden" name="id" value="<%= group.getId() %>">
            <% } %>
            <div class="form-grid">
                <div class="field">
                    <label>Talabalar soni</label>
                    <input type="number" name="studentCount" value="<%= group != null ? group.getStudentCount() : 0 %>" required>
                </div>

                <% if (edit) { %>
                <div class="field">
                    <label>Guruh ID</label>
                    <input type="text" value="<%= group.getId() %>" readonly>
                </div>
                <% } %>

                <div class="field full">
                    <label>Guruh nomi</label>
                    <input type="text" name="name" value="<%= group != null && group.getName() != null ? group.getName() : "" %>" required>
                </div>

                <div class="field full">
                    <label>Yaratilgan vaqt</label>
                    <input type="datetime-local" name="createdAt" value="<%= group != null && group.getCreatedAt() != null ? group.getCreatedAt().replace(" ", "T").substring(0, 16) : defaultCreatedAt != null ? defaultCreatedAt : "" %>" required>
                </div>
            </div>

            <div class="form-actions">
                <button class="action-btn" type="submit">Saqlash</button>
                <a class="action-btn secondary" href="<%= request.getContextPath() %>/groups">Bekor qilish</a>
            </div>
        </form>
    </section>
</div>
</body>
</html>
