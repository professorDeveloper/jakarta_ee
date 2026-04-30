<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="uz">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Kitob qo'shish</title>
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:opsz,wght@9..40,400;9..40,500;9..40,600;9..40,700&display=swap" rel="stylesheet">
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { background: #f4f4f2; font-family: 'DM Sans', sans-serif; color: #1c1c1c; min-height: 100vh; }
        .topbar { background: #fff; border-bottom: 1px solid #e8e8e5; padding: 0 32px; height: 56px; display: flex; align-items: center; justify-content: space-between; }
        .brand { font-size: 15px; font-weight: 700; color: #1c1c1c; text-decoration: none; }
        .btn-back { font-size: 13px; font-weight: 600; color: #666; background: #f4f4f2; border: 1px solid #e8e8e5; border-radius: 7px; padding: 6px 13px; text-decoration: none; }
        .btn-back:hover { background: #ebebeb; color: #1c1c1c; }
        .main { max-width: 560px; margin: 36px auto; padding: 0 20px; }
        .page-title { font-size: 17px; font-weight: 700; margin-bottom: 20px; }
        .card { background: #fff; border-radius: 12px; border: 1px solid #e8e8e5; padding: 24px; }
        .field { margin-bottom: 14px; }
        .field label { display: block; font-size: 12px; font-weight: 600; color: #888; margin-bottom: 5px; text-transform: uppercase; letter-spacing: .4px; }
        .field input, .field textarea { width: 100%; padding: 9px 12px; border: 1.5px solid #e8e8e5; border-radius: 8px; font-size: 14px; font-family: 'DM Sans', sans-serif; color: #1c1c1c; background: #fafaf8; outline: none; transition: border-color .15s; }
        .field input:focus, .field textarea:focus { border-color: #1c1c1c; background: #fff; }
        .field textarea { resize: vertical; min-height: 80px; }
        .field input[type="file"] { padding: 7px 12px; cursor: pointer; }
        .hint { font-size: 12px; color: #bbb; margin-top: 4px; }
        .error-msg { font-size: 12.5px; color: #c0392b; margin-top: 4px; }
        .divider { border: none; border-top: 1px solid #f0f0ee; margin: 18px 0; }
        .section-label { font-size: 11.5px; font-weight: 700; color: #bbb; text-transform: uppercase; letter-spacing: .5px; margin-bottom: 12px; }
        .btn-submit { width: 100%; padding: 11px; background: #1c1c1c; color: #fff; border: none; border-radius: 8px; font-size: 14px; font-weight: 600; font-family: 'DM Sans', sans-serif; cursor: pointer; transition: background .15s; margin-top: 6px; }
        .btn-submit:hover { background: #333; }
    </style>
</head>
<body>
<div class="topbar">
    <a href="${pageContext.request.contextPath}/" class="brand">Library</a>
    <a href="${pageContext.request.contextPath}/book/list" class="btn-back">Orqaga</a>
</div>

<div class="main">
    <div class="page-title">Kitob qo'shish</div>
    <div class="card">
        <form method="post" action="${pageContext.request.contextPath}/admin/book/create" enctype="multipart/form-data">
            <div class="section-label">Asosiy ma'lumotlar</div>

            <div class="field">
                <label>Sarlavha *</label>
                <input type="text" name="title" placeholder="Kitob nomi" value="<%= request.getParameter("title") != null ? request.getParameter("title") : "" %>">
                <% String titleErr = (String) request.getAttribute("title_err"); if (titleErr != null) { %><div class="error-msg"><%= titleErr %></div><% } %>
            </div>

            <div class="field">
                <label>Muallif *</label>
                <input type="text" name="author" placeholder="Muallif ismi" value="<%= request.getParameter("author") != null ? request.getParameter("author") : "" %>">
                <% String authorErr = (String) request.getAttribute("author_err"); if (authorErr != null) { %><div class="error-msg"><%= authorErr %></div><% } %>
            </div>

            <div class="field">
                <label>Tavsif</label>
                <textarea name="description" placeholder="Kitob haqida..."><%= request.getParameter("description") != null ? request.getParameter("description") : "" %></textarea>
            </div>

            <hr class="divider">
            <div class="section-label">Fayllar</div>

            <div class="field">
                <label>Muqova</label>
                <input type="file" name="cover" accept="image/*">
                <div class="hint">JPG, PNG, WEBP — max 5MB</div>
            </div>

            <div class="field">
                <label>PDF</label>
                <input type="file" name="pdf" accept="application/pdf">
                <div class="hint">Faqat PDF — max 20MB</div>
            </div>

            <button class="btn-submit" type="submit">Saqlash</button>
        </form>
    </div>
</div>
</body>
</html>
