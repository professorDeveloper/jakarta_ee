<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.azamov.learnjakarta.jakarta_bean_validation.entity.Book" %>
<!DOCTYPE html>
<html lang="uz">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Kitob</title>
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:opsz,wght@9..40,400;9..40,500;9..40,600;9..40,700&display=swap" rel="stylesheet">
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { background: #f4f4f2; font-family: 'DM Sans', sans-serif; color: #1c1c1c; min-height: 100vh; }

        .topbar { background: #fff; border-bottom: 1px solid #e8e8e5; padding: 0 32px; height: 56px; display: flex; align-items: center; justify-content: space-between; }
        .brand { font-size: 15px; font-weight: 700; color: #1c1c1c; text-decoration: none; }
        .nav-right { display: flex; gap: 8px; }
        .btn { font-size: 13px; font-weight: 600; font-family: 'DM Sans', sans-serif; padding: 6px 13px; border-radius: 7px; border: 1px solid transparent; text-decoration: none; cursor: pointer; transition: background .15s; }
        .btn-ghost { background: #f4f4f2; color: #1c1c1c; border-color: #e8e8e5; }
        .btn-ghost:hover { background: #ebebeb; color: #1c1c1c; }
        .btn-blue { background: #f0f6ff; color: #1a6fc4; border-color: #c8dffa; }
        .btn-blue:hover { background: #dceeff; color: #1a6fc4; }
        .btn-red { background: #fff5f5; color: #c0392b; border-color: #ffd6d6; }
        .btn-red:hover { background: #ffe8e8; color: #c0392b; }
        .btn-dark { background: #1c1c1c; color: #fff; }
        .btn-dark:hover { background: #333; color: #fff; }

        .main { max-width: 800px; margin: 40px auto; padding: 0 24px 48px; }
        .layout { display: flex; gap: 32px; align-items: flex-start; }

        .cover-img { width: 180px; height: 252px; border-radius: 10px; object-fit: cover; border: 1px solid #e8e8e5; box-shadow: 0 4px 20px rgba(0,0,0,.08); display: block; flex-shrink: 0; }
        .cover-empty { width: 180px; height: 252px; border-radius: 10px; background: #efefed; display: block; flex-shrink: 0; }

        .info { flex: 1; min-width: 0; }
        .book-title { font-size: 22px; font-weight: 700; letter-spacing: -.4px; line-height: 1.2; margin-bottom: 8px; }
        .book-author { font-size: 14px; color: #666; margin-bottom: 20px; }
        .divider { border: none; border-top: 1px solid #e8e8e5; margin: 18px 0; }
        .label { font-size: 11px; font-weight: 700; text-transform: uppercase; letter-spacing: .6px; color: #bbb; margin-bottom: 8px; }
        .desc { font-size: 14px; color: #555; line-height: 1.7; }
        .no-desc { font-size: 14px; color: #bbb; }
        .pdf-btn { display: inline-block; margin-top: 20px; }
    </style>
</head>
<body>
<%
    Book book = (Book) request.getAttribute("book");
    if (book == null) { response.sendRedirect(request.getContextPath() + "/book/list"); return; }
    boolean isAdmin = "ADMIN".equals(session.getAttribute("role"));
%>

<div class="topbar">
    <a href="${pageContext.request.contextPath}/" class="brand">Library</a>
    <div class="nav-right">
        <a href="${pageContext.request.contextPath}/book/list" class="btn btn-ghost">Orqaga</a>
        <% if (isAdmin) { %>
        <a href="${pageContext.request.contextPath}/admin/book/update?id=<%= book.getId() %>" class="btn btn-blue">Tahrirlash</a>
        <a href="${pageContext.request.contextPath}/admin/book/delete?id=<%= book.getId() %>" class="btn btn-red">O'chirish</a>
        <% } %>
    </div>
</div>

<div class="main">
    <div class="layout">
        <% if (book.getCover() != null) { %>
        <img class="cover-img" src="${pageContext.request.contextPath}/uploads/<%= book.getCover().getId() %>" alt="">
        <% } else { %>
        <span class="cover-empty"></span>
        <% } %>

        <div class="info">
            <div class="book-title"><%= book.getTitle() != null ? book.getTitle() : "Nomsiz" %></div>
            <div class="book-author"><%= book.getAuthor() != null ? book.getAuthor() : "Muallif noma'lum" %></div>

            <hr class="divider">

            <div class="label">Tavsif</div>
            <% if (book.getDescription() != null && !book.getDescription().isBlank()) { %>
            <div class="desc"><%= book.getDescription() %></div>
            <% } else { %>
            <div class="no-desc">Tavsif kiritilmagan</div>
            <% } %>

            <% if (book.getPdf() != null) { %>
            <a href="${pageContext.request.contextPath}/uploads/<%= book.getPdf().getId() %>"
               download="<%= book.getPdf().getOriginalName() %>" class="btn btn-dark pdf-btn">
                PDF yuklab olish
            </a>
            <% } %>
        </div>
    </div>
</div>
</body>
</html>
