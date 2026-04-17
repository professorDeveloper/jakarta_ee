<%@ page import="org.azamov.learnjakarta.user.Group" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Group> groups = (List<Group>) request.getAttribute("groups");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Guruhlar Ro'yxati</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/lms.css">
</head>
<body>
<div class="page-shell">
    <div class="topbar">
        <div class="brand">
            <div class="brand-mark">L</div>
            <div class="brand-text">
                <strong>Learn Jakarta</strong>
                <span>Guruhlar bo'limi</span>
            </div>
        </div>
        <div class="topbar-nav">
            <a class="nav-link" href="<%= request.getContextPath() %>/">Bosh sahifa</a>
            <a class="nav-link active" href="<%= request.getContextPath() %>/groups">Guruhlar</a>
            <a class="nav-link" href="<%= request.getContextPath() %>/students">Talabalar</a>
        </div>
    </div>
    <div class="content-stack">
        <div class="page-header">
            <div>
                <span class="eyebrow">Guruhlar</span>
                <h1 class="page-title">Guruhlar Ro'yxati</h1>
                <p class="page-subtitle">Barcha guruhlarni shu sahifa orqali boshqaring.</p>
            </div>
            <div class="page-actions">
                <a class="action-btn secondary" href="<%= request.getContextPath() %>/">Bosh sahifa</a>
                <a class="action-btn" href="<%= request.getContextPath() %>/groups/create">Yangi guruh</a>
            </div>
        </div>
        <div class="stats">
            <div class="panel stat-card">
                <span class="stat-label">Jami Guruhlar</span>
                <span class="stat-value"><%= groups != null ? groups.size() : 0 %></span>
            </div>
        </div>
        <section class="table-shell">
            <div class="table-scroll">
                <table>
                    <thead>
                    <tr>
                        <th>Guruh ID</th>
                        <th>Guruh nomi</th>
                        <th>Yaratilgan vaqt</th>
                        <th>Talabalar soni</th>
                        <th>Amallar</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% if (groups != null && !groups.isEmpty()) { %>
                    <% for (Group group : groups) { %>
                    <tr>
                        <td><span class="pill"><%= group.getId() %></span></td>
                        <td><strong><%= group.getName() %></strong></td>
                        <td><%= group.getCreatedAt() %></td>
                        <td><%= group.getStudentCount() %></td>
                        <td>
                            <div class="row-actions">
                                <a class="icon-btn" href="<%= request.getContextPath() %>/groups/edit?id=<%= group.getId() %>">Tahrirlash</a>
                                <form class="inline-form" action="<%= request.getContextPath() %>/groups/delete" method="post">
                                    <input type="hidden" name="id" value="<%= group.getId() %>">
                                    <button class="danger-btn" type="submit">O'chirish</button>
                                </form>
                            </div>
                        </td>
                    </tr>
                    <% } %>
                    <% } else { %>
                    <tr>
                        <td class="empty-state" colspan="5">Hozircha guruhlar mavjud emas. Yangi guruh qo'shib boshlang.</td>
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
