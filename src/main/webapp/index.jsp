<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>LMS Dashboard</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/lms.css">
</head>
<body>
<div class="page-shell">
    <div class="topbar">
        <div class="brand">
            <div class="brand-mark">L</div>
            <div class="brand-text">
                <strong>Learn Jakarta</strong>
                <span>LMS boshqaruv paneli</span>
            </div>
        </div>
        <div class="topbar-nav">
            <a class="nav-link active" href="<%= request.getContextPath() %>/">Bosh sahifa</a>
            <a class="nav-link" href="<%= request.getContextPath() %>/groups">Guruhlar</a>
            <a class="nav-link" href="<%= request.getContextPath() %>/students">Talabalar</a>
        </div>
    </div>
    <section class="hero-card">
        <span class="eyebrow">LMS Dashboard</span>
        <h1 class="hero-title">O'quv Jarayonini Boshqarish</h1>
        <p class="hero-text">Guruhlar va talabalarni bitta joydan boshqarish uchun sodda va tartibli panel.</p>
        <div class="grid-2">
            <a class="nav-card" href="<%= request.getContextPath() %>/groups">
                <h2 class="nav-title">Guruhlar</h2>
                <p class="nav-text">Guruh qo'shish, tahrirlash va mavjud guruhlar ro'yxatini ko'rish.</p>
            </a>
            <a class="nav-card" href="<%= request.getContextPath() %>/students">
                <h2 class="nav-title">Talabalar</h2>
                <p class="nav-text">Talaba ma'lumotlarini boshqarish va ularni guruhlarga biriktirish.</p>
            </a>
        </div>
        <div class="hero-footer">
            <div class="hero-note">2 ta asosiy bo'lim: Guruhlar va Talabalar</div>
            <div class="hero-note">Asosiy amallar: Create, Read, Update, Delete</div>
        </div>
    </section>
</div>
</body>
</html>
