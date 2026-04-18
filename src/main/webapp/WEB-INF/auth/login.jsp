<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="uz">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Kirish</title>
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:opsz,wght@9..40,400;9..40,500;9..40,600;9..40,700&display=swap" rel="stylesheet">
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { min-height: 100vh; background: #f4f4f2; display: flex; align-items: center; justify-content: center; font-family: 'DM Sans', sans-serif; }
        .container { width: 100%; max-width: 380px; padding: 20px; }
        .card { background: #fff; border-radius: 18px; padding: 30px 28px; border: 1px solid #e8e8e5; box-shadow: 0 2px 12px rgba(0,0,0,.05); }
        .card-title { font-size: 19px; font-weight: 700; color: #1c1c1c; letter-spacing: -.4px; margin-bottom: 3px; }
        .card-sub { font-size: 13.5px; color: #aaa; margin-bottom: 24px; }
        .field { margin-bottom: 13px; }
        .field label { display: block; font-size: 12.5px; font-weight: 600; color: #666; margin-bottom: 6px; }
        .field input { width: 100%; padding: 10px 13px; border: 1.5px solid #e8e8e5; border-radius: 9px; font-size: 14px; font-family: 'DM Sans', sans-serif; color: #1c1c1c; background: #fafaf8; outline: none; transition: border-color .15s, background .15s; }
        .field input:focus { border-color: #1c1c1c; background: #fff; }
        .field input::placeholder { color: #ccc; }
        .btn { width: 100%; padding: 11px; background: #1c1c1c; color: #fff; border: none; border-radius: 9px; font-size: 14px; font-weight: 600; font-family: 'DM Sans', sans-serif; cursor: pointer; margin-top: 6px; transition: background .15s; }
        .btn:hover { background: #333; }
        .error-box { background: #fff5f5; border: 1px solid #ffd6d6; color: #c0392b; font-size: 13px; padding: 9px 12px; border-radius: 8px; margin-bottom: 16px; }
        .sep { display: flex; align-items: center; gap: 10px; margin: 18px 0 14px; }
        .sep::before, .sep::after { content: ''; flex: 1; height: 1px; background: #ebebeb; }
        .sep span { font-size: 12px; color: #ccc; }
        .foot { text-align: center; font-size: 13px; color: #aaa; }
        .foot a { color: #1c1c1c; font-weight: 600; text-decoration: none; }
        .foot a:hover { text-decoration: underline; }
    </style>
</head>
<body>
<div class="container">
    <div class="card">
        <p class="card-title">Kirish</p>
        <p class="card-sub">Hisobingizga kiring</p>
        <% String error = (String) request.getAttribute("error"); %>
        <% if (error != null) { %>
        <div class="error-box">&#9888; <%= error %></div>
        <% } %>
        <form method="post" action="${pageContext.request.contextPath}login">
            <div class="field">
                <label>USERNAME</label>
                <input type="text" name="username" placeholder="username kiriting" required>
            </div>
            <div class="field">
                <label>PAROL</label>
                <input type="password" name="password" placeholder="••••••••" required>
            </div>
            <button class="btn">Kirish</button>
        </form>
        <div class="sep"><span>yoki</span></div>
        <p class="foot">Hisobingiz yo'qmi? <a href="${pageContext.request.contextPath}register">Ro'yxatdan o'ting</a></p>
    </div>
</div>
</body>
</html>
