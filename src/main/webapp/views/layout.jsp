<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/layout.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>
    <div class="container">
        <!-- Header -->
        <header class="header">
            <h1>Transit Fleet Management System</h1>
            <div class="header-buttons">
            </div>
        </header>

        <!-- Main Layout (no sidebar for login) -->
        <div class="main">
            <section class="content">
                <div class="login-box">
                    <div class="login-image">
                        <img src="${pageContext.request.contextPath}/images/bus.jpg" alt="Login Image">
                    </div>
                    <div class="login-form">
                        <form action="${pageContext.request.contextPath}/Login" method="post">
                            <label for="email">Email</label>
                            <input type="email" id="email" name="email">
                            <label for="password">Password:</label>
                            <input type="password" id="password" name="password">

                            <div class="login-actions">
                                <input type="submit" name="action" value="Login" class="login-btn" onclick="return validateLogin();">
                                <input type="submit" name="action" value="Register" class="register-btn">
                            </div>
                        </form>
                    </div>
                </div>
            </section>
        </div>

        <!-- Footer -->
        <footer class="footer">
            Contact us: support@example.com | Phone: 123-456-7890
        </footer>
    </div>
    <script>
        function validateLogin() {
            const email = document.getElementById('email').value.trim();
            const password = document.getElementById('password').value.trim();

            if (!email || !password) {
                alert('Email and password are required for login.');
                return false; // Prevent form submission
            }

            return true; // Allow form submission
        }
    </script>
</body>
</html>