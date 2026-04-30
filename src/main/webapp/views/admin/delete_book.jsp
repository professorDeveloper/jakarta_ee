<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.azamov.learnjakarta.jakarta_bean_validation.entity.Book" %>
<!DOCTYPE html>
<html lang="uz">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>O'chirish</title>
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:opsz,wght@9..40,400;9..40,500;9..40,600;9..40,700&display=swap" rel="stylesheet">
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { background: #f4f4f2; font-family: 'DM Sans', sans-serif; color: #1c1c1c; min-height: 100vh; }
        .topbar { background: #fff; border-bottom: 1px solid #e8e8e5; padding: 0 32px; height: 56px; display: flex; align-items: center; justify-content: space-between; }
        .brand { font-size: 15px; font-weight: 700; color: #1c1c1c; text-decoration: none; }
        .btn-back { font-size: 13px; font-weight: 600; color: #666; background: #f4f4f2; border: 1px solid #e8e8e5; border-radius: 7px; padding: 6px 13px; text-decoration: none; }
        .main { max-width: 440px; margin: 80px auto; padding: 0 20px; }
        .card { background: #fff; border-radius: 12px; border: 1px solid #e8e8e5; padding: 28px; text-align: center; }
        .card-title { font-size: 17px; font-weight: 700; margin-bottom: 8px; }
        .card-sub { font-size: 13.5px; color: #888; line-height: 1.5; margin-bottom: 16px; }
        .book-name { font-size: 14px; font-weight: 600; background: #f4f4f2; border-radius: 8px; padding: 9px 14px; margin-bottom: 24px; display: inline-block; }
        .actions { display: flex; gap: 8px; }
        .btn-cancel { flex: 1; padding: 10px; background: #f4f4f2; color: #1c1c1c; border: 1px solid #e8e8e5; border-radius: 8px; font-size: 13.5px; font-weight: 600; font-family: 'DM Sans', sans-serif; text-decoration: none; text-align: center; transition: background .15s; }
        .btn-cancel:hover { background: #ebebeb; color: #1c1c1c; }
        .btn-delete { flex: 1; padding: 10px; background: #c0392b; color: #fff; border: none; border-radius: 8px; font-size: 13.5px; font-weight: 600; font-family: 'DM Sans', sans-serif; cursor: pointer; transition: background .15s; }
        .btn-delete:hover { background: #a93226; }
    </style>
</head>
<body>
<%
    Book book = (Book) request.getAttribute("book");
    if (book == null) { response.sendRedirect(request.getContextPath() + "/book/list"); return; }
%>
<div class="topbar">
    <a href="${pageContext.request.contextPath}/" class="brand">Library</a>
    <a href="${pageContext.request.contextPath}/book/list" class="btn-back">Orqaga</a>
</div>

<div class="main">
    <div class="card">
        <div class="card-title">O'chirishni tasdiqlang</div>
        <div class="card-sub">Bu amal qaytarib bo'lmaydi. Kitob va barcha fayllari o'chib ketadi.</div>
        <div class="book-name"><%= book.getTitle() %></div>
        <div class="actions">
            <a href="${pageContext.request.contextPath}/book/list" class="btn-cancel">Bekor qilish</a>
            <form method="post" action="${pageContext.request.contextPath}/admin/book/delete" style="flex:1">
                <input type="hidden" name="id" value="<%= book.getId() %>">
                <button class="btn-delete" style="width:100%" type="submit">O'chirish</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
