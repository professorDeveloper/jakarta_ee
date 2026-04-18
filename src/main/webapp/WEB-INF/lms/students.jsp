<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.azamov.learnjakarta.task7_1.model.Student" %>
<%@ page import="org.azamov.learnjakarta.task7_1.model.Group" %>
<!DOCTYPE html>
<html lang="uz">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Talabalar</title>
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
            <a href="${pageContext.request.contextPath}/groups" class="nav-link">Guruhlar</a>
            <a href="${pageContext.request.contextPath}/students" class="nav-link active">Talabalar</a>
            <a href="${pageContext.request.contextPath}/logout" class="nav-link">Chiqish</a>
        </nav>
    </div>

    <div class="page-header">
        <div>
            <h1 class="page-title">Talabalar</h1>
            <p class="page-subtitle">Barcha talabalar ro'yxati</p>
        </div>
    </div>

    <div class="form-shell" style="margin-bottom: 20px;">
        <form method="post" action="${pageContext.request.contextPath}/students">
            <div class="form-grid">
                <div class="field">
                    <label>To'liq ism</label>
                    <input type="text" name="fullName" placeholder="Talaba ismi" required>
                </div>
                <div class="field">
                    <label>Yoshi</label>
                    <input type="number" name="age" placeholder="Yosh" min="1" max="100" required>
                </div>
                <div class="field full">
                    <label>Guruh</label>
                    <select name="groupId" required>
                        <option value="">— Guruhni tanlang —</option>
                        <%
                            List<Group> groups = (List<Group>) request.getAttribute("groups");
                            if (groups != null) {
                                for (Group g : groups) {
                        %>
                        <option value="<%= g.getId() %>"><%= g.getName() %></option>
                        <%
                                }
                            }
                        %>
                    </select>
                </div>
            </div>
            <div class="form-actions">
                <button type="submit" class="action-btn">+ Talaba qo'shish</button>
            </div>
        </form>
    </div>

    <div class="table-shell">
        <div class="table-scroll">
            <table>
                <thead>
                <tr>
                    <th>#</th>
                    <th>To'liq ism</th>
                    <th>Yoshi</th>
                    <th>Guruh ID</th>
                    <th>Yaratuvchi ID</th> <th></th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<Student> students = (List<Student>) request.getAttribute("students");
                    if (students != null && !students.isEmpty()) {
                        for (Student s : students) {
                %>
                <tr>
                    <td class="muted"><%= s.getId() %></td>
                    <td><strong><%= s.getFullName() %></strong></td>
                    <td><%= s.getAge() %></td>
                    <td><span class="pill">Guruh #<%= s.getGroupId() %></span></td>
                    <td><span class="muted">User #<%= s.getCreatedBy() %></span></td>
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/students" class="inline-form">
                            <input type="hidden" name="_method" value="DELETE">
                            <input type="hidden" name="id" value="<%= s.getId() %>">
                            <button type="submit" class="danger-btn" onclick="return confirm('O\'chirishni tasdiqlaysizmi?')">O'chirish</button>
                        </form>
                    </td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr>
                    <td colspan="6" class="empty-state">Talabalar mavjud emas</td> </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>