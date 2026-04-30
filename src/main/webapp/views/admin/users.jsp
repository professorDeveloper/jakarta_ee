<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.azamov.learnjakarta.jakarta_bean_validation.entity.AuthUser" %>
<!DOCTYPE html>
<html lang="uz">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Foydalanuvchilar</title>
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

        .main { max-width: 1000px; margin: 32px auto; padding: 0 24px 48px; }
        .page-header { display: flex; align-items: center; justify-content: space-between; margin-bottom: 16px; }
        .page-title { font-size: 17px; font-weight: 700; }
        .count-badge { font-size: 12px; font-weight: 600; background: #f4f4f2; border: 1px solid #e8e8e5; border-radius: 20px; padding: 3px 10px; color: #888; }

        .card { background: #fff; border-radius: 12px; border: 1px solid #e8e8e5; overflow: hidden; }

        table { width: 100%; border-collapse: collapse; }
        thead th { padding: 10px 16px; font-size: 11.5px; font-weight: 700; text-transform: uppercase; letter-spacing: .5px; color: #aaa; text-align: left; border-bottom: 1px solid #f0f0ee; }
        tbody tr { border-bottom: 1px solid #f8f8f6; transition: background .1s; }
        tbody tr:last-child { border-bottom: none; }
        tbody tr:hover { background: #fafaf8; }
        td { padding: 12px 16px; font-size: 14px; vertical-align: middle; }

        .username { font-weight: 600; font-size: 13.5px; }
        .email-text { font-size: 13px; color: #666; }

        .role-admin { background: #1c1c1c; color: #fff; border-radius: 6px; padding: 2px 9px; font-size: 11.5px; font-weight: 700; letter-spacing: .3px; }
        .role-user { background: #f4f4f2; border: 1px solid #e8e8e5; color: #555; border-radius: 6px; padding: 2px 9px; font-size: 11.5px; font-weight: 600; }

        .status-active { background: #f0faf4; border: 1px solid #b6e8c8; color: #1a7a46; border-radius: 6px; padding: 2px 9px; font-size: 11.5px; font-weight: 600; }
        .status-inactive { background: #fffbf0; border: 1px solid #f0e0a0; color: #8a6a00; border-radius: 6px; padding: 2px 9px; font-size: 11.5px; font-weight: 600; }
        .status-blocked { background: #fff5f5; border: 1px solid #ffd6d6; color: #c0392b; border-radius: 6px; padding: 2px 9px; font-size: 11.5px; font-weight: 600; }

        .actions { display: flex; gap: 5px; }
        .btn-sm { font-size: 12px; font-weight: 600; font-family: 'DM Sans', sans-serif; padding: 4px 11px; border-radius: 6px; border: 1px solid transparent; text-decoration: none; cursor: pointer; transition: background .15s; }
        .btn-sm-blue { background: #f0f6ff; color: #1a6fc4; border-color: #c8dffa; }
        .btn-sm-blue:hover { background: #dceeff; }
        .btn-sm-ghost { background: #f4f4f2; color: #555; border-color: #e8e8e5; }
        .btn-sm-ghost:hover { background: #ebebeb; }
        .btn-sm-red { background: #fff5f5; color: #c0392b; border-color: #ffd6d6; }
        .btn-sm-red:hover { background: #ffe8e8; }

        .empty { text-align: center; padding: 48px 32px; color: #aaa; font-size: 14px; }
    </style>
</head>
<body>
<%
    List<AuthUser> users = (List<AuthUser>) request.getAttribute("users");
    String currentId = (String) session.getAttribute("id");
%>
<div class="topbar">
    <a href="${pageContext.request.contextPath}/" class="brand">Library</a>
    <div class="nav-right">
        <a href="${pageContext.request.contextPath}/" class="btn btn-ghost">Orqaga</a>
    </div>
</div>

<div class="main">
    <div class="page-header">
        <div class="page-title">Foydalanuvchilar</div>
        <% if (users != null) { %><span class="count-badge"><%= users.size() %> ta</span><% } %>
    </div>

    <div class="card">
        <% if (users == null || users.isEmpty()) { %>
        <div class="empty">Hali foydalanuvchilar yo'q</div>
        <% } else { %>
        <table>
            <thead>
            <tr>
                <th>#</th>
                <th>Foydalanuvchi</th>
                <th>Email</th>
                <th>Rol</th>
                <th>Holat</th>
                <th>Amallar</th>
            </tr>
            </thead>
            <tbody>
            <% int i = 1; for (AuthUser user : users) { %>
            <tr>
                <td style="color:#bbb;width:36px;"><%= i++ %></td>
                <td><div class="username"><%= user.getUsername() %></div></td>
                <td><span class="email-text"><%= user.getEmail() %></span></td>
                <td>
                    <% if ("ADMIN".equals(user.getRole())) { %>
                    <span class="role-admin">ADMIN</span>
                    <% } else { %>
                    <span class="role-user">USER</span>
                    <% } %>
                </td>
                <td>
                    <% if (user.getStatus() == AuthUser.Status.ACTIVE) { %>
                    <span class="status-active">Faol</span>
                    <% } else if (user.getStatus() == AuthUser.Status.BLOCKED) { %>
                    <span class="status-blocked">Bloklangan</span>
                    <% } else { %>
                    <span class="status-inactive">Tasdiqlanmagan</span>
                    <% } %>
                </td>
                <td>
                    <% if (!user.getId().equals(currentId)) { %>
                    <div class="actions">
                        <% if (user.getStatus() == AuthUser.Status.BLOCKED) { %>
                        <form method="post" action="${pageContext.request.contextPath}/admin/users/action">
                            <input type="hidden" name="id" value="<%= user.getId() %>">
                            <input type="hidden" name="action" value="unblock">
                            <button class="btn-sm btn-sm-blue" type="submit">Blokdan chiqar</button>
                        </form>
                        <% } else { %>
                        <form method="post" action="${pageContext.request.contextPath}/admin/users/action">
                            <input type="hidden" name="id" value="<%= user.getId() %>">
                            <input type="hidden" name="action" value="block">
                            <button class="btn-sm btn-sm-ghost" type="submit">Bloklash</button>
                        </form>
                        <% } %>
                        <form method="post" action="${pageContext.request.contextPath}/admin/users/action"
                              onsubmit="return confirm('O\'chirmoqchimisiz?')">
                            <input type="hidden" name="id" value="<%= user.getId() %>">
                            <input type="hidden" name="action" value="delete">
                            <button class="btn-sm btn-sm-red" type="submit">O'chirish</button>
                        </form>
                    </div>
                    <% } else { %>
                    <span style="font-size:12px;color:#bbb;">Siz</span>
                    <% } %>
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
