<%@ include file="header.jsp" %>

<div class="container mt-5">
    <h2>Add a New Journal Entry</h2>
    <form action="/save_User" method="post">
        <div class="mb-3">
            <label class="form-label">Name</label>
            <input type="text" name="name" class="form-control" />
        </div>
        <div class="mb-3">
            <label class="form-label">Email</label>
            <input type="email" name="email" class="form-control" />
        </div>
        <div class="mb-3">
            <label class="form-label">Password</label>
            <input type="password" name="password" class="form-control" />
        </div>
        <button type="submit" class="btn btn-primary">Save</button>
    </form>
</div>

<!-- Footer -->
<div class="footer">
    <p>&copy; 2025 My Journal | Made with <i class="fa-solid fa-heart text-danger"></i> by You</p>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
