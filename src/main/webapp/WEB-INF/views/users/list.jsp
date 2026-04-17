<%@ page import="org.azamov.learnjakarta.user.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<User> users = (List<User>) request.getAttribute("users");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Foydalanuvchilar Ro'yxati</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/lms.css">
</head>
<body>
<div class="page-shell">
    <div class="topbar">
        <div class="brand">
            <div class="brand-mark">L</div>
            <div class="brand-text">
                <strong>Learn Jakarta</strong>
                <span>Foydalanuvchilar bo'limi</span>
            </div>
        </div>
        <div class="topbar-nav">
            <a class="nav-link" href="<%= request.getContextPath() %>/">Bosh sahifa</a>
            <a class="nav-link active" href="<%= request.getContextPath() %>/users">Foydalanuvchilar</a>
        </div>
    </div>
    <div class="content-stack">
        <div class="page-header">
            <div>
                <span class="eyebrow">Foydalanuvchilar</span>
                <h1 class="page-title">Foydalanuvchilar Ro'yxati</h1>
                <p class="page-subtitle">Barcha foydalanuvchilarni shu sahifa orqali boshqaring.</p>
            </div>
            <div class="page-actions">
                <a class="action-btn secondary" href="<%= request.getContextPath() %>/">Bosh sahifa</a>
                <a class="action-btn" href="<%= request.getContextPath() %>/users/create">Yangi foydalanuvchi</a>
            </div>
        </div>
        <div class="stats">
            <div class="panel stat-card">
                <span class="stat-label">Jami Foydalanuvchilar</span>
                <span class="stat-value"><%= users != null ? users.size() : 0 %></span>
            </div>
        </div>
        <section class="table-shell">
            <div class="table-scroll">
                <table>
                    <thead>
                    <tr>
                        <th>User ID</th>
                        <th>Username</th>
                        <th>Amallar</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% if (users != null && !users.isEmpty()) { %>
                    <% for (User user : users) { %>
                    <tr>
                        <td><span class="pill"><%= user.getId() %></span></td>
                        <td><strong><%= user.getUsername() %></strong></td>
                        <td>
                            <div class="row-actions">
                                <a class="icon-btn" href="<%= request.getContextPath() %>/users/edit?id=<%= user.getId() %>">Tahrirlash</a>
                                <form class="inline-form" action="<%= request.getContextPath() %>/users/delete" method="post">
                                    <input type="hidden" name="id" value="<%= user.getId() %>">
                                    <button class="danger-btn" type="submit">O'chirish</button>
                                </form>
                            </div>
                        </td>
                    </tr>
                    <% } %>
                    <% } else { %>
                    <tr>
                        <td class="empty-state" colspan="3">Hozircha foydalanuvchilar yo'q. Birinchi foydalanuvchini qo'shing.</td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>
        </section>
    </div>
</div>
</body>
</html>
