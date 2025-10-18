<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Get Started - MyAuthApp</title>
    <meta name="viewport" content="width=device-width, initial-scale=1"> <!-- Responsive Meta -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: #f8f9fa;
        }
        .auth-card {
            max-width: 1000px;
            margin: 50px auto;
            border-radius: 15px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
            overflow: hidden;
        }
        .left-panel {
            background: linear-gradient(135deg, #0d6efd, #6610f2);
            color: #fff;
            padding: 40px;
            text-align: center;
        }
        .left-panel h2 {
            font-weight: bold;
        }
        .notice-text {
            font-size: 0.9rem;
            margin-top: 15px;
            color: #f1f1f1;
        }
        .form-container {
            padding: 40px;
        }
    </style>
</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-light bg-white shadow-sm">
    <div class="container">
        <a class="navbar-brand fw-bold" href="/"><i class="fa-solid fa-book"></i> My Journal</a>
        <div class="ms-auto">
            <a href="/" class="btn btn-outline-primary">     Home</a>
        </div>
    </div>
</nav>

<div class="auth-card row">
    <!-- Left Panel -->
    <div class="col-md-5 left-panel d-flex flex-column justify-content-center align-items-center">
        <h2> Get Started</h2>
        <p class="text-light">Create an account or login to continue your journey.</p>
        <p class="notice-text"> Account sirf <b>15 minute</b> ke liye banega.
            15 minute ke baad automatic delete ho jayega.</p>
    </div>

    <!-- Right Panel -->
    <div class="col-md-7 bg-white">
        <div class="form-container">
            <ul class="nav nav-tabs mb-4" id="authTabs" role="tablist">
                <li class="nav-item" role="presentation">
                    <button class="nav-link active" id="signup-tab" data-bs-toggle="tab" data-bs-target="#signup" type="button" role="tab">
                        Sign Up
                    </button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link" id="signin-tab" data-bs-toggle="tab" data-bs-target="#signin" type="button" role="tab">
                        Sign In
                    </button>
                </li>
            </ul>

            <div class="tab-content">
                <!-- Sign Up -->
                <div class="tab-pane fade show active" id="signup" role="tabpanel">
                    <form action="/save_User" method="post">
                        <div class="mb-3">
                            <label class="form-label">Full Name</label>
                            <input type="text" name="name" class="form-control" placeholder="Enter your name" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Email</label>
                            <input type="email" name="email" class="form-control" placeholder="Enter email" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Password</label>
                            <input type="password" name="password" class="form-control" placeholder="Enter password" required>
                        </div>
                        <button type="submit" class="btn btn-primary w-100">Sign Up</button>
                    </form>
                </div>

                <!-- Sign In -->
                <div class="tab-pane fade" id="signin" role="tabpanel">
                    <form action="/login" method="post">
                        <div class="mb-3">
                            <label class="form-label">Email</label>
                            <input type="email" name="email" class="form-control" placeholder="Enter email" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Password</label>
                            <input type="password" name="password" class="form-control" placeholder="Enter password" required>
                        </div>
                        <button type="submit" class="btn btn-success w-100">Sign In</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/js/all.min.js"></script>
</body>
</html>
