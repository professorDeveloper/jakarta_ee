<%@ page import="org.azamov.learnjakarta.user.Student" %>
<%@ page import="org.azamov.learnjakarta.user.Group" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Student student = (Student) request.getAttribute("student");
    List<Group> groups = (List<Group>) request.getAttribute("groups");
    String action = (String) request.getAttribute("action");
    String defaultCreatedAt = (String) request.getAttribute("defaultCreatedAt");
    boolean edit = student != null && student.getId() != null;
%>
<!DOCTYPE html>
<html>
<head>
    <title><%= edit ? "Talabani Tahrirlash" : "Yangi Talaba" %></title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/lms.css">
</head>
<body>
<div class="page-shell">
    <div class="topbar">
        <div class="brand">
            <div class="brand-mark">L</div>
            <div class="brand-text">
                <strong>Learn Jakarta</strong>
                <span>Talaba formasi</span>
            </div>
        </div>
        <div class="topbar-nav">
            <a class="nav-link" href="<%= request.getContextPath() %>/">Bosh sahifa</a>
            <a class="nav-link" href="<%= request.getContextPath() %>/groups">Guruhlar</a>
            <a class="nav-link active" href="<%= request.getContextPath() %>/students">Talabalar</a>
        </div>
    </div>
    <div class="page-header">
        <div>
            <span class="eyebrow">Talaba Formasi</span>
            <h1 class="page-title"><%= edit ? "Talabani Tahrirlash" : "Yangi Talaba Qo'shish" %></h1>
            <p class="page-subtitle">Talaba ma'lumotlarini kiriting va guruhga biriktiring.</p>
        </div>
        <div class="page-actions">
            <a class="action-btn secondary" href="<%= request.getContextPath() %>/students">Orqaga</a>
        </div>
    </div>
    <section class="form-shell">
        <form action="<%= action %>" method="post">
            <% if (edit) { %>
            <input type="hidden" name="id" value="<%= student.getId() %>">
            <% } %>
            <div class="form-grid">
                <div class="field">
                    <label>Yosh</label>
                    <input type="number" name="age" value="<%= student != null ? student.getAge() : 0 %>" required>
                </div>

                <% if (edit) { %>
                <div class="field">
                    <label>Talaba ID</label>
                    <input type="text" value="<%= student.getId() %>" readonly>
                </div>
                <% } %>

                <div class="field full">
                    <label>To'liq ism</label>
                    <input type="text" name="fullName" value="<%= student != null && student.getFullName() != null ? student.getFullName() : "" %>" required>
                </div>

                <div class="field">
                    <label>Yaratilgan vaqt</label>
                    <input type="datetime-local" name="createdAt" value="<%= student != null && student.getCreatedAt() != null ? student.getCreatedAt().replace(" ", "T").substring(0, 16) : defaultCreatedAt != null ? defaultCreatedAt : "" %>" required>
                </div>

                <div class="field">
                    <label>Guruh</label>
                    <select name="groupId" required>
                        <option value="">Guruhni tanlang</option>
                        <% if (groups != null) { %>
                        <% for (Group group : groups) { %>
                        <option value="<%= group.getId() %>" <%= student != null && group.getId().equals(student.getGroupId()) ? "selected" : "" %>>
                            <%= group.getName() %> (<%= group.getId() %>)
                        </option>
                        <% } %>
                        <% } %>
                    </select>
                </div>
            </div>

            <div class="form-actions">
                <button class="action-btn" type="submit">Saqlash</button>
                <a class="action-btn secondary" href="<%= request.getContextPath() %>/students">Bekor qilish</a>
            </div>
        </form>
    </section>
</div>
</body>
</html>
