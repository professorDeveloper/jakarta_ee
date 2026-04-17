<%@ page import="org.azamov.learnjakarta.user.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user = (User) request.getAttribute("user");
    String action = (String) request.getAttribute("action");
    boolean edit = user != null && user.getId() != null;
%>
<!DOCTYPE html>
<html>
<head>
    <title><%= edit ? "Foydalanuvchini Tahrirlash" : "Yangi Foydalanuvchi" %></title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/lms.css">
</head>
<body>
<div class="page-shell">
    <div class="topbar">
        <div class="brand">
            <div class="brand-mark">L</div>
            <div class="brand-text">
                <strong>Learn Jakarta</strong>
                <span>Foydalanuvchi formasi</span>
            </div>
        </div>
        <div class="topbar-nav">
            <a class="nav-link" href="<%= request.getContextPath() %>/">Bosh sahifa</a>
            <a class="nav-link active" href="<%= request.getContextPath() %>/users">Foydalanuvchilar</a>
        </div>
    </div>
    <div class="page-header">
        <div>
            <span class="eyebrow">Foydalanuvchi Formasi</span>
            <h1 class="page-title"><%= edit ? "Foydalanuvchini Tahrirlash" : "Yangi Foydalanuvchi Qo'shish" %></h1>
        </div>
        <div class="page-actions">
            <a class="action-btn secondary" href="<%= request.getContextPath() %>/users">Orqaga</a>
        </div>
    </div>
    <section class="form-shell">
        <form action="<%= action %>" method="post">
            <% if (edit) { %>
            <input type="hidden" name="id" value="<%= user.getId() %>">
            <% } %>
            <div class="form-grid">
                <% if (edit) { %>
                <div class="field">
                    <label>User ID</label>
                    <input type="text" value="<%= user.getId() %>" readonly>
                </div>
                <% } %>
                <div class="field full">
                    <label>Username</label>
                    <input type="text" name="username" value="<%= user != null && user.getUsername() != null ? user.getUsername() : "" %>" required>
                </div>
            </div>
            <div class="form-actions">
                <button class="action-btn" type="submit">Saqlash</button>
                <a class="action-btn secondary" href="<%= request.getContextPath() %>/users">Bekor qilish</a>
            </div>
        </form>
    </section>
</div>
</body>
</html>
