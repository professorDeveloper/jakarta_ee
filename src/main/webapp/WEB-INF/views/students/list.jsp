<%@ page import="org.azamov.learnjakarta.lms_system.Student" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Student> students = (List<Student>) request.getAttribute("students");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Talabalar Ro'yxati</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/lms.css">
</head>
<body>
<div class="page-shell">
    <div class="topbar">
        <div class="brand">
            <div class="brand-mark">L</div>
            <div class="brand-text">
                <strong>Learn Jakarta</strong>
                <span>Talabalar bo'limi</span>
            </div>
        </div>
        <div class="topbar-nav">
            <a class="nav-link" href="<%= request.getContextPath() %>/">Bosh sahifa</a>
            <a class="nav-link" href="<%= request.getContextPath() %>/groups">Guruhlar</a>
            <a class="nav-link active" href="<%= request.getContextPath() %>/students">Talabalar</a>
        </div>
    </div>
    <div class="content-stack">
        <div class="page-header">
            <div>
                <span class="eyebrow">Talabalar</span>
                <h1 class="page-title">Talabalar Ro'yxati</h1>
                <p class="page-subtitle">Talabalar ma'lumotlarini shu yerda ko'ring va yangilang.</p>
            </div>
            <div class="page-actions">
                <a class="action-btn secondary" href="<%= request.getContextPath() %>/">Bosh sahifa</a>
                <a class="action-btn" href="<%= request.getContextPath() %>/students/create">Yangi talaba</a>
            </div>
        </div>
        <div class="stats">
            <div class="panel stat-card">
                <span class="stat-label">Jami Talabalar</span>
                <span class="stat-value"><%= students != null ? students.size() : 0 %></span>
            </div>
        </div>
        <section class="table-shell">
            <div class="table-scroll">
                <table>
                    <thead>
                    <tr>
                        <th>Talaba ID</th>
                        <th>Yaratilgan vaqt</th>
                        <th>To'liq ism</th>
                        <th>Yosh</th>
                        <th>Guruh ID</th>
                        <th>Amallar</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% if (students != null && !students.isEmpty()) { %>
                    <% for (Student student : students) { %>
                    <tr>
                        <td><span class="pill"><%= student.getId() %></span></td>
                        <td><%= student.getCreatedAt() %></td>
                        <td><strong><%= student.getFullName() %></strong></td>
                        <td><%= student.getAge() %></td>
                        <td><%= student.getGroupId() %></td>
                        <td>
                            <div class="row-actions">
                                <a class="icon-btn" href="<%= request.getContextPath() %>/students/edit?id=<%= student.getId() %>">Tahrirlash</a>
                                <form class="inline-form" action="<%= request.getContextPath() %>/students/delete" method="post">
                                    <input type="hidden" name="id" value="<%= student.getId() %>">
                                    <button class="danger-btn" type="submit">O'chirish</button>
                                </form>
                            </div>
                        </td>
                    </tr>
                    <% } %>
                    <% } else { %>
                    <tr>
                        <td class="empty-state" colspan="6">Hozircha talabalar yo'q. Birinchi talabani qo'shing.</td>
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
