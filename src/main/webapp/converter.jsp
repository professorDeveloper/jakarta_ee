<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="uz">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sanoq Sistemasi Konvertori</title>
    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            padding: 30px;
        }

        .card {
            background-color: #fff;
            max-width: 500px;
            margin: 0 auto;
            padding: 25px;
            border: 1px solid #ccc;
            border-radius: 8px;
        }

        h1 {
            text-align: center;
            margin-bottom: 10px;
            color: #333;
        }

        .subtitle {
            text-align: center;
            color: #666;
            margin-bottom: 20px;
            font-size: 14px;
        }

        .bases-row {
            display: flex;
            justify-content: space-between;
            align-items: end;
            gap: 10px;
            margin-bottom: 20px;
        }

        .bases-row > div {
            flex: 1;
        }

        .arrow {
            flex: 0;
            font-size: 22px;
            padding-bottom: 8px;
            color: #333;
        }

        label {
            display: block;
            margin-bottom: 5px;
            font-size: 14px;
            color: #333;
        }

        select,
        input[type="text"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #aaa;
            border-radius: 5px;
            font-size: 14px;
        }

        .input-group {
            margin-bottom: 20px;
        }

        button {
            width: 100%;
            padding: 12px;
            background-color: #2d89ef;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
        }

        button:hover {
            background-color: #1b5fa7;
        }

        .result-box {
            margin-top: 20px;
            padding: 15px;
            background-color: #eaf7ea;
            border: 1px solid #8bc48b;
            border-radius: 5px;
            text-align: center;
        }

        .result-label {
            font-size: 14px;
            color: #444;
            margin-bottom: 8px;
        }

        .result-value {
            font-size: 24px;
            font-weight: bold;
            color: #2e7d32;
        }

        .result-detail {
            margin-top: 10px;
            font-size: 13px;
            color: #555;
        }

        .error-box {
            margin-top: 20px;
            padding: 12px;
            background-color: #fdeaea;
            border: 1px solid #e57373;
            border-radius: 5px;
            color: #c62828;
            text-align: center;
        }

        .base-badge {
            display: inline-block;
            padding: 3px 8px;
            background-color: #f1f1f1;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 12px;
            color: #333;
        }
    </style>
</head>
<body>

<div class="card">
    <h1>Sanoq Sistemasi</h1>
    <p class="subtitle">2 &nbsp;|&nbsp; 8 &nbsp;|&nbsp; 10 &nbsp;|&nbsp; 16</p>

    <form action="convert" method="post">

        <div class="bases-row">
            <div>
                <label>Manba sistema</label>
                <select name="fromBase">
                    <option value="2"  <%= "2".equals(request.getAttribute("fromBase"))  ? "selected" : "" %>>Ikkilik (2)</option>
                    <option value="8"  <%= "8".equals(request.getAttribute("fromBase"))  ? "selected" : "" %>>Sakkizlik (8)</option>
                    <option value="10" <%= "10".equals(request.getAttribute("fromBase")) || request.getAttribute("fromBase") == null ? "selected" : "" %>>O'nlik (10)</option>
                    <option value="16" <%= "16".equals(request.getAttribute("fromBase")) ? "selected" : "" %>>O'n oltilik (16)</option>
                </select>
            </div>

            <div class="arrow">&#8594;</div>

            <div>
                <label>Nishon sistema</label>
                <select name="toBase">
                    <option value="2"  <%= "2".equals(request.getAttribute("toBase"))  ? "selected" : "" %>>Ikkilik (2)</option>
                    <option value="8"  <%= "8".equals(request.getAttribute("toBase"))  ? "selected" : "" %>>Sakkizlik (8)</option>
                    <option value="10" <%= "10".equals(request.getAttribute("toBase")) ? "selected" : "" %>>O'nlik (10)</option>
                    <option value="16" <%= "16".equals(request.getAttribute("toBase")) || request.getAttribute("toBase") == null ? "selected" : "" %>>O'n oltilik (16)</option>
                </select>
            </div>
        </div>

        <div class="input-group">
            <label>Sonni kiriting</label>
            <input type="text"
                   name="number"
                   placeholder="Masalan: 255, FF, 11111111 ..."
                   value="<%= request.getAttribute("number") != null ? request.getAttribute("number") : "" %>"
                   autocomplete="off" />
        </div>

        <button type="submit">O'TKAZISH</button>
    </form>

    <%
        String result = (String) request.getAttribute("result");
        String error  = (String) request.getAttribute("error");
        String number = (String) request.getAttribute("number");
        String fromBase = (String) request.getAttribute("fromBase");
        String toBase   = (String) request.getAttribute("toBase");

        if (error != null) {
    %>
        <div class="error-box"><%= error %></div>
    <%
        } else if (result != null) {
    %>
        <div class="result-box">
            <div class="result-label">Natija</div>
            <div class="result-value"><%= result %></div>
            <div class="result-detail">
                <span class="base-badge"><%= number %> (base&#8209;<%= fromBase %>)</span>
                &nbsp;=&nbsp;
                <span class="base-badge"><%= result %> (base&#8209;<%= toBase %>)</span>
            </div>
        </div>
    <% } %>
</div>

</body>
</html>
