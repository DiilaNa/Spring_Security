$(document).ready(function () {
    $("#login").on('click', function (e) {
        e.preventDefault();

        const username = $('#exampleInputUserName').val();
        const password = $('#exampleInputPassword1').val();

        const loginData = {
            userName: username,
            password: password
        };

        $.ajax({
            url: 'http://localhost:8080/auth/login',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(loginData),
            success: function (res) {
                const token = res.data.accessToken;
                localStorage.setItem("token", token);
                if (!token){
                    window.location.href = "../Pages/Sign_In.html"
                }

                // Delay for safety (optional)
                setTimeout(() => {
                    redirectBasedOnRole(token);
                }, 100);
            },
            error: function (xhr) {
                alert("Login failed: " + xhr.responseText);
            }
        });
    });

    function redirectBasedOnRole(token) {
        $.ajax({
            url: "http://localhost:8080/hello/user",
            method: "GET",
            headers: {
                "Authorization": "Bearer " + token
            },
            success: function () {
                window.location.href = "../Pages/UserDashBoard.html";
            },
            error: function () {
                $.ajax({
                    url: "http://localhost:8080/hello/admin",
                    method: "GET",
                    headers: {
                        "Authorization": "Bearer " + token
                    },
                    success: function () {
                        window.location.href = "../Pages/AdminDashBoard.html";
                    },
                    error: function () {
                        alert("Access denied: No role match");
                    }
                });
            }
        });
    }

});
