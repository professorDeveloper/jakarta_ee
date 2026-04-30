<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.azamov.learnjakarta.jakarta_bean_validation.entity.Book" %>
<!DOCTYPE html>
<html lang="uz">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Library</title>
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:opsz,wght@9..40,400;9..40,500;9..40,600;9..40,700&display=swap" rel="stylesheet">
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { background: #f4f4f2; font-family: 'DM Sans', sans-serif; color: #1c1c1c; min-height: 100vh; }

        .topbar { background: #fff; border-bottom: 1px solid #e8e8e5; padding: 0 32px; height: 56px; display: flex; align-items: center; justify-content: space-between; position: sticky; top: 0; z-index: 10; }
        .brand { font-size: 15px; font-weight: 700; color: #1c1c1c; text-decoration: none; }
        .nav-right { display: flex; align-items: center; gap: 10px; }
        .nav-user { font-size: 13.5px; color: #666; }
        .nav-user span { font-weight: 600; color: #1c1c1c; }
        .btn { font-size: 13px; font-weight: 600; font-family: 'DM Sans', sans-serif; border-radius: 8px; padding: 6px 14px; text-decoration: none; border: 1px solid transparent; cursor: pointer; transition: background .15s; }
        .btn-ghost { background: #f4f4f2; color: #1c1c1c; border-color: #e8e8e5; }
        .btn-ghost:hover { background: #ebebeb; color: #1c1c1c; }
        .btn-dark { background: #1c1c1c; color: #fff; }
        .btn-dark:hover { background: #333; color: #fff; }
        .btn-red { background: #fff5f5; color: #c0392b; border-color: #ffd6d6; }
        .btn-red:hover { background: #ffe8e8; color: #c0392b; }

        .main { max-width: 1000px; margin: 32px auto; padding: 0 24px 48px; }

        .guest-banner { background: #1c1c1c; border-radius: 12px; padding: 24px 28px; display: flex; align-items: center; justify-content: space-between; gap: 24px; flex-wrap: wrap; margin-bottom: 28px; }
        .guest-banner-title { font-size: 15px; font-weight: 700; color: #fff; margin-bottom: 3px; }
        .guest-banner-sub { font-size: 13px; color: #777; }
        .guest-banner-actions { display: flex; gap: 8px; }
        .btn-banner-main { background: #fff; color: #1c1c1c; border: none; border-radius: 8px; padding: 8px 16px; font-size: 13px; font-weight: 600; font-family: 'DM Sans', sans-serif; text-decoration: none; transition: background .15s; }
        .btn-banner-main:hover { background: #f0f0ee; color: #1c1c1c; }
        .btn-banner-sec { background: transparent; color: #777; border: 1px solid #333; border-radius: 8px; padding: 8px 16px; font-size: 13px; font-weight: 600; font-family: 'DM Sans', sans-serif; text-decoration: none; }
        .btn-banner-sec:hover { border-color: #666; color: #aaa; }

        .page-header { display: flex; align-items: center; justify-content: space-between; margin-bottom: 16px; }
        .page-title { font-size: 17px; font-weight: 700; }
        .page-link { font-size: 13px; font-weight: 600; color: #888; text-decoration: none; }
        .page-link:hover { color: #1c1c1c; }

        .card { background: #fff; border-radius: 12px; border: 1px solid #e8e8e5; overflow: hidden; }

        table { width: 100%; border-collapse: collapse; }
        thead th { padding: 10px 16px; font-size: 11.5px; font-weight: 700; text-transform: uppercase; letter-spacing: .5px; color: #aaa; text-align: left; border-bottom: 1px solid #f0f0ee; }
        tbody tr { border-bottom: 1px solid #f8f8f6; transition: background .1s; }
        tbody tr:last-child { border-bottom: none; }
        tbody tr:hover { background: #fafaf8; }
        td { padding: 12px 16px; font-size: 14px; vertical-align: middle; }

        .thumb { width: 34px; height: 48px; border-radius: 5px; object-fit: cover; border: 1px solid #e8e8e5; display: block; }
        .thumb-empty { width: 34px; height: 48px; border-radius: 5px; background: #efefed; display: block; }

        .book-title { font-weight: 600; font-size: 13.5px; }
        .author-tag { background: #f4f4f2; border: 1px solid #e8e8e5; color: #555; border-radius: 20px; padding: 3px 10px; font-size: 12.5px; }
        .mime-tag { background: #f0f6ff; border: 1px solid #c8dffa; color: #1a6fc4; border-radius: 6px; padding: 2px 8px; font-size: 12px; font-weight: 500; }
        .size-text { font-size: 12.5px; color: #888; }
        .no-file { font-size: 12px; color: #ccc; }

        .empty-state { text-align: center; padding: 56px 32px; }
        .empty-title { font-size: 14px; font-weight: 600; margin-bottom: 5px; }
        .empty-sub { font-size: 13.5px; color: #aaa; }

        .btn-sm { font-size: 12px; font-weight: 600; font-family: 'DM Sans', sans-serif; padding: 4px 11px; border-radius: 6px; border: 1px solid transparent; text-decoration: none; cursor: pointer; transition: background .15s; }
        .btn-sm-ghost { background: #f4f4f2; color: #1c1c1c; border-color: #e8e8e5; }
        .btn-sm-ghost:hover { background: #ebebeb; }
    </style>
</head>
<body>
<%
    String username = (String) session.getAttribute("username");
    String role = (String) session.getAttribute("role");
    boolean isLoggedIn = username != null;
    boolean isAdmin = "ADMIN".equals(role);
    List<Book> books = (List<Book>) request.getAttribute("books");
%>

<div class="topbar">
    <a href="${pageContext.request.contextPath}/" class="brand">Library</a>
    <div class="nav-right">
        <% if (isLoggedIn) { %>
        <span class="nav-user">Salom, <span><%= username %></span></span>
        <a href="${pageContext.request.contextPath}/book/list" class="btn btn-ghost">Kitoblar</a>
        <% if (isAdmin) { %>
        <a href="${pageContext.request.contextPath}/admin/book/create" class="btn btn-dark">Kitob qo'sh</a>
        <a href="${pageContext.request.contextPath}/admin/users" class="btn btn-ghost">Foydalanuvchilar</a>
        <% } %>
        <a href="${pageContext.request.contextPath}/logout" class="btn btn-red">Chiqish</a>
        <% } else { %>
        <a href="${pageContext.request.contextPath}/login" class="btn btn-ghost">Kirish</a>
        <a href="${pageContext.request.contextPath}/register" class="btn btn-dark">Ro'yxatdan o'tish</a>
        <% } %>
    </div>
</div>

<div class="main">
    <% if (!isLoggedIn) { %>
    <div class="guest-banner">
        <div>
            <div class="guest-banner-title">Hisobingiz bormi?</div>
            <div class="guest-banner-sub">Ro'yxatdan o'tib barcha imkoniyatlardan foydalaning</div>
        </div>
        <div class="guest-banner-actions">
            <a href="${pageContext.request.contextPath}/register" class="btn-banner-main">Ro'yxatdan o'tish</a>
            <a href="${pageContext.request.contextPath}/login" class="btn-banner-sec">Kirish</a>
        </div>
    </div>
    <% } %>

    <div class="page-header">
        <div class="page-title">Kitoblar</div>
        <a href="${pageContext.request.contextPath}/book/list" class="page-link">Hammasini ko'rish</a>
    </div>

    <div class="card">
        <% if (books == null || books.isEmpty()) { %>
        <div class="empty-state">
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
                <th>Muqova</th>
                <th>Nomi</th>
                <th>Muallif</th>
                <th>Rasm turi</th>
                <th>Hajmi</th>
                <th>PDF</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <%
                int limit = Math.min(books.size(), 8);
                for (int i = 0; i < limit; i++) {
                    Book book = books.get(i);
            %>
            <tr>
                <td style="width:52px;">
                    <% if (book.getCover() != null) { %>
                    <img class="thumb" src="${pageContext.request.contextPath}/uploads/<%= book.getCover().getId() %>" alt="">
                    <% } else { %>
                    <span class="thumb-empty"></span>
                    <% } %>
                </td>
                <td><div class="book-title"><%= book.getTitle() != null ? book.getTitle() : "—" %></div></td>
                <td><span class="author-tag"><%= book.getAuthor() != null ? book.getAuthor() : "—" %></span></td>
                <td>
                    <% if (book.getCover() != null) { %>
                    <span class="mime-tag"><%= book.getCover().getMimeType() %></span>
                    <% } else { %>
                    <span class="no-file">—</span>
                    <% } %>
                </td>
                <td>
                    <% if (book.getCover() != null) { %>
                    <%
                        long bytes = book.getCover().getSize();
                        String sizeStr;
                        if (bytes >= 1024 * 1024) sizeStr = String.format("%.1f MB", bytes / (1024.0 * 1024));
                        else sizeStr = String.format("%.0f KB", bytes / 1024.0);
                    %>
                    <span class="size-text"><%= sizeStr %></span>
                    <% } else { %>
                    <span class="no-file">—</span>
                    <% } %>
                </td>
                <td>
                    <% if (book.getPdf() != null) { %>
                    <%
                        long pdfBytes = book.getPdf().getSize();
                        String pdfSize;
                        if (pdfBytes >= 1024 * 1024) pdfSize = String.format("%.1f MB", pdfBytes / (1024.0 * 1024));
                        else pdfSize = String.format("%.0f KB", pdfBytes / 1024.0);
                    %>
                    <span class="size-text"><%= pdfSize %></span>
                    <% } else { %>
                    <span class="no-file">—</span>
                    <% } %>
                </td>
                <td><a href="${pageContext.request.contextPath}/book/detail?id=<%= book.getId() %>" class="btn-sm btn-sm-ghost">Ko'rish</a></td>
            </tr>
            <% } %>
            </tbody>
        </table>
        <% } %>
    </div>
</div>
</body>
</html>
