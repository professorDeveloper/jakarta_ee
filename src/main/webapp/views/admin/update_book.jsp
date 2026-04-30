<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.azamov.learnjakarta.jakarta_bean_validation.entity.Book" %>
<!DOCTYPE html>
<html lang="uz">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Kitobni tahrirlash</title>
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:opsz,wght@9..40,400;9..40,500;9..40,600;9..40,700&display=swap" rel="stylesheet">
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { background: #f4f4f2; font-family: 'DM Sans', sans-serif; color: #1c1c1c; min-height: 100vh; }
        .topbar { background: #fff; border-bottom: 1px solid #e8e8e5; padding: 0 32px; height: 56px; display: flex; align-items: center; justify-content: space-between; }
        .topbar-brand { font-size: 16px; font-weight: 700; color: #1c1c1c; text-decoration: none; }
        .btn-back { font-size: 13px; font-weight: 600; color: #666; background: #f4f4f2; border: 1px solid #e8e8e5; border-radius: 8px; padding: 6px 14px; text-decoration: none; }
        .btn-back:hover { background: #ebebeb; color: #1c1c1c; }
        .main { max-width: 600px; margin: 40px auto; padding: 0 20px; }
        .page-title { font-size: 20px; font-weight: 700; letter-spacing: -.4px; margin-bottom: 24px; }
        .card { background: #fff; border-radius: 16px; border: 1px solid #e8e8e5; padding: 28px; }
        .field { margin-bottom: 16px; }
        .field label { display: block; font-size: 12.5px; font-weight: 600; color: #666; margin-bottom: 6px; text-transform: uppercase; letter-spacing: .4px; }
        .field input, .field textarea { width: 100%; padding: 10px 13px; border: 1.5px solid #e8e8e5; border-radius: 9px; font-size: 14px; font-family: 'DM Sans', sans-serif; color: #1c1c1c; background: #fafaf8; outline: none; transition: border-color .15s; }
        .field input:focus, .field textarea:focus { border-color: #1c1c1c; background: #fff; }
        .field textarea { resize: vertical; min-height: 90px; }
        .field input[type="file"] { padding: 8px 13px; cursor: pointer; }
        .field-hint { font-size: 12px; color: #bbb; margin-top: 5px; }
        .divider { border: none; border-top: 1px solid #f0f0ee; margin: 20px 0; }
        .btn-submit { width: 100%; padding: 12px; background: #1c1c1c; color: #fff; border: none; border-radius: 9px; font-size: 14px; font-weight: 600; font-family: 'DM Sans', sans-serif; cursor: pointer; transition: background .15s; margin-top: 8px; }
        .btn-submit:hover { background: #333; }
        .section-label { font-size: 13px; font-weight: 700; color: #aaa; text-transform: uppercase; letter-spacing: .5px; margin-bottom: 14px; }
        .current-cover { width: 80px; height: 110px; border-radius: 8px; object-fit: cover; border: 1px solid #e8e8e5; margin-bottom: 8px; display: block; }
        .current-label { font-size: 12px; color: #888; margin-bottom: 8px; }
        .pdf-badge { display: inline-flex; align-items: center; gap: 6px; background: #f0f6ff; border: 1px solid #c8dffa; color: #1a6fc4; border-radius: 8px; padding: 5px 12px; font-size: 12.5px; font-weight: 500; margin-bottom: 8px; }
    </style>
</head>
<body>
<%
    Book book = (Book) request.getAttribute("book");
    if (book == null) { response.sendRedirect(request.getContextPath() + "/book/list"); return; }
%>
<div class="topbar">
    <a href="${pageContext.request.contextPath}/" class="topbar-brand">Library</a>
    <a href="${pageContext.request.contextPath}/book/list" class="btn-back">Orqaga</a>
</div>

<div class="main">
    <div class="page-title">Kitobni tahrirlash</div>
    <div class="card">
        <form method="post" action="${pageContext.request.contextPath}/admin/book/update" enctype="multipart/form-data">
            <input type="hidden" name="id" value="<%= book.getId() %>">

            <div class="section-label">Asosiy ma'lumotlar</div>

            <div class="field">
                <label>Sarlavha *</label>
                <input type="text" name="title" value="<%= book.getTitle() != null ? book.getTitle() : "" %>">
            </div>

            <div class="field">
                <label>Muallif *</label>
                <input type="text" name="author" value="<%= book.getAuthor() != null ? book.getAuthor() : "" %>">
            </div>

            <div class="field">
                <label>Tavsif</label>
                <textarea name="description"><%= book.getDescription() != null ? book.getDescription() : "" %></textarea>
            </div>

            <hr class="divider">
            <div class="section-label">Fayllar</div>

            <div class="field">
                <label>Muqova (rasm)</label>
                <% if (book.getCover() != null) { %>
                <div class="current-label">Hozirgi muqova:</div>
                <img class="current-cover" src="${pageContext.request.contextPath}/uploads/<%= book.getCover().getId() %>" alt="muqova">
                <% } %>
                <input type="file" name="cover" accept="image/*">
                <div class="field-hint">Yangi fayl tanlasangiz almashtiriladi</div>
            </div>

            <div class="field">
                <label>PDF fayl</label>
                <% if (book.getPdf() != null) { %>
                <div class="pdf-badge"><%= book.getPdf().getOriginalName() %></div>
                <% } %>
                <input type="file" name="pdf" accept="application/pdf">
                <div class="field-hint">Yangi fayl tanlasangiz almashtiriladi</div>
            </div>

            <button class="btn-submit" type="submit">Saqlash</button>
        </form>
    </div>
</div>
</body>
</html>
