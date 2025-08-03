$(document).ready(function () {
    $("#createAccount").on('click', function (e) {
        e.preventDefault();

        const username = $('#userName').val();
        const password = $('#password').val();
        const confirmPassword = $('#confirmPassword').val();
        const role = $('#role').val();

        if (password !== confirmPassword) {
            alert("Passwords do not match!");
            return;
        }

        const data = {
            userName: username,
            password: password,
            role: role
        };

        $.ajax({
            url: 'http://localhost:8080/auth/register',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (response) {
                alert("Account created successfully!");
                window.location.href = "../Pages/Sign_In.html";
            },
            error: function (xhr) {
                alert("Sign up failed: " + xhr.responseText);
            }
        });
    });
});