document.getElementById('logoutButton').addEventListener('click', function() {
    localStorage.removeItem('jwt');
    window.location.href = 'login.html';
});