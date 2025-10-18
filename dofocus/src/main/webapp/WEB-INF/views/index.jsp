<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>DoFocus - Productivity Manager</title>
    <link rel="stylesheet" href="<c:url value='/public/css/style.css' />">
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f5f7fa;
            margin: 0;
            padding: 0;
        }
        header {
            background: #2d3436;
            color: white;
            padding: 20px;
            text-align: center;
        }
        nav {
            text-align: center;
            margin: 20px 0;
        }
        nav a {
            text-decoration: none;
            color: #2d3436;
            padding: 10px 20px;
            margin: 5px;
            border: 1px solid #2d3436;
            border-radius: 6px;
            transition: all 0.3s ease;
        }
        nav a:hover {
            background: #2d3436;
            color: white;
        }
        .features {
            display: flex;
            justify-content: center;
            flex-wrap: wrap;
            margin: 40px auto;
            max-width: 900px;
        }
        .card {
            background: white;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            padding: 20px;
            margin: 15px;
            flex: 1 1 250px;
            text-align: center;
        }
        .card h3 {
            color: #0984e3;
        }
        footer {
            text-align: center;
            padding: 15px;
            background: #dfe6e9;
            margin-top: 40px;
        }
    </style>
</head>
<body>
<header>
    <h1>Welcome to DoFocus</h1>
    <p>Your personal productivity & focus microservice platform</p>
</header>

<nav>
    <a href="<c:url value='/users/signin' />">Sign In</a>
    <a href="<c:url value='/users/signup' />">Sign Up</a>
    <a href="<c:url value='/public/info.jsp' />">About</a>
</nav>

<section class="features">
    <div class="card">
        <h3>Task Management</h3>
        <p>Create, update and track your daily tasks effectively.</p>
    </div>
    <div class="card">
        <h3>Focus Timer</h3>
        <p>Use Pomodoro technique to stay focused and avoid distractions.</p>
    </div>
    <div class="card">
        <h3>Analytics</h3>
        <p>Get insights into your productivity with visual reports.</p>
    </div>
    <div class="card">
        <h3>Microservices</h3>
        <p>Each feature is a scalable microservice working together seamlessly.</p>
    </div>
</section>

<footer>
    <p>&copy; 2025 DoFocus. All rights reserved.</p>
</footer>
</body>
</html>
