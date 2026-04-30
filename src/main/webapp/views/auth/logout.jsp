<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="uz">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chiqish</title>
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:opsz,wght@9..40,400;9..40,500;9..40,600;9..40,700&display=swap" rel="stylesheet">
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { min-height: 100vh; background: #f4f4f2; display: flex; align-items: center; justify-content: center; font-family: 'DM Sans', sans-serif; }
        .container { width: 100%; max-width: 380px; padding: 20px; }
        .card { background: #fff; border-radius: 18px; padding: 30px 28px; border: 1px solid #e8e8e5; box-shadow: 0 2px 12px rgba(0,0,0,.05); }
        .card-title { font-size: 19px; font-weight: 700; color: #1c1c1c; letter-spacing: -.4px; margin-bottom: 3px; }
        .card-sub { font-size: 13.5px; color: #aaa; margin-bottom: 24px; }
        .btn { width: 100%; padding: 11px; background: #1c1c1c; color: #fff; border: none; border-radius: 9px; font-size: 14px; font-weight: 600; font-family: 'DM Sans', sans-serif; cursor: pointer; margin-top: 6px; transition: background .15s; }
        .btn:hover { background: #333; }
    </style>
</head>
<body>
<div class="container">
    <div class="card">
        <p class="card-title">Chiqish</p>
        <p class="card-sub">Chiqish uchun quyidagi tugmani bosing</p>
        <form method="post" action="${pageContext.request.contextPath}logout">
            <button class="btn">Chiqish</button>
        </form>
    </div>
</div>
</body>
</html>