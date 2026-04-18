<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.azamov.learnjakarta.task7_1.model.Group" %>
<!DOCTYPE html>
<html lang="uz">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Guruhlar</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/lms.css">
</head>
<body>
<div class="page-shell">
    <div class="topbar">
        <div class="brand">
            <div class="brand-mark">LMS</div>
            <div class="brand-text">
                <strong>Task 3</strong>
                <span>LMS System</span>
            </div>
        </div>
        <nav class="topbar-nav">
            <a href="${pageContext.request.contextPath}groups" class="nav-link active">Guruhlar</a>
            <a href="${pageContext.request.contextPath}students" class="nav-link">Talabalar</a>
            <a href="${pageContext.request.contextPath}logout" class="nav-link">Chiqish</a>
        </nav>
    </div>

    <div class="page-header">
        <div>
            <h1 class="page-title">Guruhlar</h1>
            <p class="page-subtitle">Barcha guruhlar ro'yxati</p>
        </div>
    </div>

    <div class="form-shell" style="margin-bottom: 20px;">
        <form method="post" action="${pageContext.request.contextPath}groups">
            <div class="form-grid">
                <div class="field">
                    <label>Guruh nomi</label>
                    <input type="text" name="name" placeholder="Guruh nomini kiriting" required>
                </div>
            </div>
            <div class="form-actions">
                <button type="submit" class="action-btn">+ Guruh qo'shish</button>
            </div>
        </form>
    </div>

    <div class="table-shell">
        <div class="table-scroll">
            <table>
                <thead>
                <tr>
                    <th>#</th>
                    <th>Nomi</th>
                    <th>Talabalar soni</th>
                    <th>Yaratuvchi ID</th> <th>Yaratilgan</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<Group> groups = (List<Group>) request.getAttribute("groups");
                    if (groups != null && !groups.isEmpty()) {
                        for (Group g : groups) {
                %>
                <tr>
                    <td class="muted"><%= g.getId() %></td>
                    <td><strong><%= g.getName() %></strong></td>
                    <td><span class="pill"><%= g.getStudentCount() %></span></td>
                    <td><span class="muted">User #<%= g.getCreatedBy() %></span></td>
                    <td class="muted"><%= g.getCreatedAt() %></td>
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/groups" class="inline-form">
                            <input type="hidden" name="_method" value="DELETE">
                            <input type="hidden" name="id" value="<%= g.getId() %>">
                            <button type="submit" class="danger-btn" onclick="return confirm('O\'chirishni tasdiqlaysizmi?')">O'chirish</button>
                        </form>
                    </td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr>
                    <td colspan="6" class="empty-state">Guruhlar mavjud emas</td> </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
