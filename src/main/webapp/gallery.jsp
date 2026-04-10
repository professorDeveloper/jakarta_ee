<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.azamov.learnjakarta.ImageUploadServlet" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.File" %>
<html>
<head>
    <title>Rasm Galeriyasi</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
            background-color: #f5f5f5;
        }
        .container {
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        h1 {
            color: #333;
            text-align: center;
            margin-bottom: 30px;
        }
        .gallery {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
            gap: 20px;
            margin-top: 20px;
        }
        .gallery-item {
            border: 1px solid #ddd;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            transition: transform 0.3s ease;
        }
        .gallery-item:hover {
            transform: scale(1.05);
        }
        .gallery-item img {
            width: 100%;
            height: 200px;
            object-fit: cover;
        }
        .image-name {
            padding: 10px;
            text-align: center;
            background-color: #f8f9fa;
            font-size: 14px;
            color: #666;
        }
        .back-link {
            display: inline-block;
            margin-bottom: 20px;
            color: #007bff;
            text-decoration: none;
            font-weight: bold;
        }
        .back-link:hover {
            text-decoration: underline;
        }
        .no-images {
            text-align: center;
            color: #666;
            font-style: italic;
            margin: 50px 0;
        }
        .header-actions {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header-actions">
            <a href="index.jsp" class="back-link">← Orqaga</a>
            <h1>Rasm Galeriyasi</h1>
            <div></div>
        </div>
        
        <%
            String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
            List<String> images = ImageUploadServlet.getUploadedImages(uploadPath);
        %>
        
        <% if (images == null || images.isEmpty()) { %>
            <div class="no-images">
                <h3>Hech qanday rasm topilmadi</h3>
                <p>Rasm yuklash uchun <a href="index.jsp">bu yerga</a> o'ting.</p>
            </div>
        <% } else { %>
            <div class="gallery">
                <% for (String imageName : images) { %>
                    <div class="gallery-item">
                        <img src="uploads/<%= imageName %>" alt="<%= imageName %>" 
                             onerror="this.src='data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMjUwIiBoZWlnaHQ9IjIwMCIgdmlld0JveD0iMCAwIDI1MCAyMDAiIGZpbGw9Im5vbmUiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+CjxyZWN0IHdpZHRoPSIyNTAiIGhlaWdodD0iMjAwIiBmaWxsPSIjRjVGNUY1Ii8+CjxwYXRoIGQ9Ik0xMjUgNzVDMTQ0LjMzIDc1IDE2MCA5MC42NyAxNjAgMTEwQzE2MCAxMjkuMzMgMTQ0LjMzIDE0NSAxMjUgMTQ1QzEwNS42NyAxNDUgOTAgMTI5LjMzIDkwIDExMEM5MCA5MC42NyAxMDUuNjcgNzUgMTI1IDc1WiIgZmlsbD0iI0NDQyIvPgo8cGF0aCBkPSJNMjA1IDE2MEg0NVYxNDBINzVMMTAwIDEwMEwxMzUgMTM1TDE3NSA5NUgyMDVWMTYwWiIgZmlsbD0iIzk5OSIvPgo8L3N2Zz4K'">
                        <div class="image-name"><%= imageName %></div>
                    </div>
                <% } %>
            </div>
            
            <div style="text-align: center; margin-top: 30px; color: #666;">
                <p>Jami rasm soni: <%= images.size() %></p>
            </div>
        <% } %>
    </div>
</body>
</html>
