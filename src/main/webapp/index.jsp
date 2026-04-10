<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rasm Yuklash</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 800px;
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
        .upload-form {
            text-align: center;
        }
        .file-input {
            margin: 20px 0;
            padding: 10px;
            border: 2px dashed #ddd;
            border-radius: 5px;
            width: 100%;
            max-width: 400px;
        }
        .submit-btn {
            background-color: #007bff;
            color: white;
            padding: 12px 30px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }
        .submit-btn:hover {
            background-color: #0056b3;
        }
        .gallery-link {
            display: inline-block;
            margin-top: 20px;
            color: #007bff;
            text-decoration: none;
            font-weight: bold;
        }
        .gallery-link:hover {
            text-decoration: underline;
        }
        .error {
            color: red;
            text-align: center;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Rasm Galeriyasi</h1>
        
        <% if (request.getParameter("error") != null) { %>
            <div class="error">
                Rasm yuklashda xatolik yuz berdi. Iltimos, qayta urinib ko'ring.
            </div>
        <% } %>
        
        <div class="upload-form">
            <form action="upload" method="post" enctype="multipart/form-data">
                <div class="file-input">
                    <input type="file" name="image" accept="image/*" required>
                </div>
                <button type="submit" class="submit-btn">Rasm Yuklash</button>
            </form>
        </div>
        
        <div style="text-align: center; margin-top: 30px;">
            <a href="gallery.jsp" class="gallery-link">Galereyani Ko'rish</a>
        </div>
    </div>
</body>
</html>
