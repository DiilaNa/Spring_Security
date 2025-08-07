$(document).ready(function () {
    const token = localStorage.getItem("token");
    if (!token) {
        redirectToLogin();
        return;
    }

    validateToken(token)
        .then(isValid => {
            if (!isValid) {
                alert("Your session is invalid or has expired. Please log in again.");
                logoutAndRedirect();
            } else {
                setupDashboard();
            }
        })
        .catch(err => {
            console.error("Validation error:", err);
            logoutAndRedirect();
        });
});

function validateToken(token) {
  return $.ajax({
        url: "http://localhost:8080/hello/user",
        method: "GET",
        headers: { "Authorization": "Bearer " + token }
    }).then(() => true).catch(xhr => {

        return false;
    });
}

function setupDashboard() {
    // Fetch user data or dashboard details
    $.ajax({
        url: "http://localhost:8080/dashboard/data",
        method: "GET",
        headers: { "Authorization": "Bearer " + localStorage.getItem("token") },
        success: function (data) {
            renderDashboard(data);
        },
        error: function () {
            alert("Failed to load dashboard");
            logoutAndRedirect();
        }
    });
}

function renderDashboard(data) {
    $("#welcome").text(`Welcome, ${data.username || "User"}`);
}

function logoutAndRedirect() {
    localStorage.removeItem("token");
    redirectToLogin();
}

function redirectToLogin() {
    window.location.href = "../Pages/Sign_In.html";
}
