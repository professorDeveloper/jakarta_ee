<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.azamov.learnjakarta.jakarta_bean_validation.entity.Book" %>
<!DOCTYPE html>
<html lang="uz">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Kitoblar</title>
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:opsz,wght@9..40,400;9..40,500;9..40,600;9..40,700&display=swap" rel="stylesheet">
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { background: #f4f4f2; font-family: 'DM Sans', sans-serif; color: #1c1c1c; min-height: 100vh; }

        .topbar { background: #fff; border-bottom: 1px solid #e8e8e5; padding: 0 32px; height: 56px; display: flex; align-items: center; justify-content: space-between; }
        .brand { font-size: 15px; font-weight: 700; color: #1c1c1c; text-decoration: none; }
        .nav-right { display: flex; align-items: center; gap: 10px; }
        .nav-user { font-size: 13.5px; color: #666; }
        .nav-user b { color: #1c1c1c; font-weight: 600; }
        .btn { font-size: 13px; font-weight: 600; font-family: 'DM Sans', sans-serif; padding: 6px 13px; border-radius: 7px; border: 1px solid transparent; text-decoration: none; cursor: pointer; transition: background .15s; }
        .btn-ghost { background: #f4f4f2; color: #1c1c1c; border-color: #e8e8e5; }
        .btn-ghost:hover { background: #ebebeb; color: #1c1c1c; }
        .btn-dark { background: #1c1c1c; color: #fff; }
        .btn-dark:hover { background: #333; color: #fff; }
        .btn-red { background: #fff5f5; color: #c0392b; border-color: #ffd6d6; }
        .btn-red:hover { background: #ffe8e8; color: #c0392b; }

        .main { max-width: 1000px; margin: 32px auto; padding: 0 24px; }
        .page-header { display: flex; align-items: center; justify-content: space-between; margin-bottom: 20px; }
        .page-title { font-size: 17px; font-weight: 700; }

        .card { background: #fff; border-radius: 12px; border: 1px solid #e8e8e5; overflow: hidden; }

        table { width: 100%; border-collapse: collapse; }
        thead th { padding: 11px 16px; font-size: 11.5px; font-weight: 700; text-transform: uppercase; letter-spacing: .5px; color: #aaa; text-align: left; border-bottom: 1px solid #f0f0ee; }
        tbody tr { border-bottom: 1px solid #f8f8f6; transition: background .1s; }
        tbody tr:last-child { border-bottom: none; }
        tbody tr:hover { background: #fafaf8; }
        td { padding: 13px 16px; font-size: 14px; vertical-align: middle; }

        .cover-img { width: 36px; height: 50px; border-radius: 5px; object-fit: cover; border: 1px solid #e8e8e5; display: block; }
        .cover-empty { width: 36px; height: 50px; border-radius: 5px; background: #efefed; display: block; }

        .book-title { font-weight: 600; font-size: 13.5px; margin-bottom: 2px; }
        .book-desc { font-size: 12.5px; color: #aaa; max-width: 300px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap; }

        .author-tag { background: #f4f4f2; border: 1px solid #e8e8e5; color: #555; border-radius: 20px; padding: 3px 10px; font-size: 12.5px; }

        .actions { display: flex; gap: 5px; }
        .btn-sm { font-size: 12px; font-weight: 600; font-family: 'DM Sans', sans-serif; padding: 4px 11px; border-radius: 6px; border: 1px solid transparent; text-decoration: none; cursor: pointer; transition: background .15s; }
        .btn-sm-ghost { background: #f4f4f2; color: #1c1c1c; border-color: #e8e8e5; }
        .btn-sm-ghost:hover { background: #ebebeb; color: #1c1c1c; }
        .btn-sm-blue { background: #f0f6ff; color: #1a6fc4; border-color: #c8dffa; }
        .btn-sm-blue:hover { background: #dceeff; color: #1a6fc4; }
        .btn-sm-red { background: #fff5f5; color: #c0392b; border-color: #ffd6d6; }
        .btn-sm-red:hover { background: #ffe8e8; color: #c0392b; }

        .empty { text-align: center; padding: 56px 32px; }
        .empty-title { font-size: 14px; font-weight: 600; margin-bottom: 5px; }
        .empty-sub { font-size: 13.5px; color: #aaa; }
    </style>
</head>
<body>
<%
    String username = (String) session.getAttribute("username");
    String role = (String) session.getAttribute("role");
    boolean isAdmin = "ADMIN".equals(role);
    List<Book> books = (List<Book>) request.getAttribute("books");
%>

<div class="topbar">
    <a href="${pageContext.request.contextPath}/" class="brand">Library</a>
    <div class="nav-right">
        <span class="nav-user">Salom, <b><%= username %></b></span>
        <% if (isAdmin) { %>
        <a href="${pageContext.request.contextPath}/admin/book/create" class="btn btn-dark">Kitob qo'sh</a>
        <% } %>
        <a href="${pageContext.request.contextPath}/logout" class="btn btn-red">Chiqish</a>
    </div>
</div>

<div class="main">
    <div class="page-header">
        <div class="page-title">Kitoblar</div>
    </div>

    <div class="card">
        <% if (books == null || books.isEmpty()) { %>
        <div class="empty">
            <div class="empty-title">Hali kitoblar yo'q</div>
            <div class="empty-sub">
                <% if (isAdmin) { %>
                <a href="${pageContext.request.contextPath}/admin/book/create" style="color:#1c1c1c;font-weight:600;">Birinchi kitobni qo'shing</a>
                <% } else { %>
                Tez orada kitoblar qo'shiladi
                <% } %>
            </div>
        </div>
        <% } else { %>
        <table>
            <thead>
            <tr>
                <th>#</th>
                <th>Muqova</th>
                <th>Nomi</th>
                <th>Muallif</th>
                <th>Amallar</th>
            </tr>
            </thead>
            <tbody>
            <% int i = 1; for (Book book : books) { %>
            <tr>
                <td style="color:#bbb;width:36px;"><%= i++ %></td>
                <td style="width:52px;">
                    <% if (book.getCover() != null) { %>
                    <img class="cover-img" src="${pageContext.request.contextPath}/uploads/<%= book.getCover().getId() %>" alt="">
                    <% } else { %>
                    <span class="cover-empty"></span>
                    <% } %>
                </td>
                <td>
                    <div class="book-title"><%= book.getTitle() != null ? book.getTitle() : "—" %></div>
                    <div class="book-desc"><%= book.getDescription() != null ? book.getDescription() : "" %></div>
                </td>
                <td><span class="author-tag"><%= book.getAuthor() != null ? book.getAuthor() : "—" %></span></td>
                <td>
                    <div class="actions">
                        <a href="${pageContext.request.contextPath}/book/detail?id=<%= book.getId() %>" class="btn-sm btn-sm-ghost">Ko'rish</a>
                        <% if (isAdmin) { %>
                        <a href="${pageContext.request.contextPath}/admin/book/update?id=<%= book.getId() %>" class="btn-sm btn-sm-blue">Tahrir</a>
                        <a href="${pageContext.request.contextPath}/admin/book/delete?id=<%= book.getId() %>" class="btn-sm btn-sm-red">O'chir</a>
                        <% } %>
                    </div>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
        <% } %>
    </div>
</div>
</body>
</html>
