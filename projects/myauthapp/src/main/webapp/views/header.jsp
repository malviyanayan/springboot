<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><c:out value="${pageTitle}" default="My Journal"/></title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #f9f9f9;
            font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
        }
        .navbar {
            box-shadow: 0px 2px 6px rgba(0,0,0,0.1);
        }
        .hero {
            padding: 60px 20px;
            text-align: center;
            background-color: #ffffff;
            border-radius: 12px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.05);
            margin-top: 40px;
        }
        .hero h1 {
            font-weight: 600;
            color: #333;
        }
        .hero p {
            color: #666;
            font-size: 1.1rem;
        }
        .features i {
            font-size: 2rem;
            color: #0d6efd;
            margin-bottom: 10px;
        }
        .footer {
            margin-top: 60px;
            padding: 20px;
            text-align: center;
            background: #ffffff;
            border-top: 1px solid #eaeaea;
            color: #777;
            font-size: 0.9rem;
        }
    </style>
</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg bg-light">
    <div class="container">
        <a class="navbar-brand fw-bold" href="#"><i class="fa-solid fa-book"></i> My Journal</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link ${page eq 'home' ? 'active' : ''}" href="/"><i class="fa-solid fa-house"></i> Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link ${page eq 'write' ? 'active' : ''}" href="/write"><i class="fa-solid fa-pen"></i> Write</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#"><i class="fa-solid fa-book-open"></i> My Entries</a>
                </li>

                <!-- Check session for user -->
                <c:choose>
                    <c:when test="${not empty sessionScope.user}">
                        <!-- Show Profile if user is logged in -->
                        <li class="nav-item">
                            <a class="nav-link" href="/profile"><i class="fa-solid fa-user"></i> Profile</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <!-- Show Get Started button if no user -->
                        <li class="nav-item">
                            <a class="btn btn-primary ms-3" href="/signup"><i class="fa-solid fa-arrow-right-to-bracket"></i> Get Started</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>
