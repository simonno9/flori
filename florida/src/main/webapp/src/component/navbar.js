import { setProfileImg } from '../java/profile.js';

export function createNavbar() {
    window.addEventListener('DOMContentLoaded', () => {
        setProfileImg();

        const body = document.querySelector('body');

        const sidenav = document.createElement('div');
        sidenav.classList.add('sidenav');
        sidenav.id = 'mySidenav';
        sidenav.innerHTML = `
            <div class="options">
                <a href="/admin-dashboard.html" class="active"><img class="icon" src="/images/icons8-home-48.png" alt="Home"><span>Home</span></a>
                <a href="/admin-charter-service.html"><img class="icon" src="/images/settings.png" alt="service charter"><span>service</span></a>
                <a href="/admin-product-order.html"><img class="icon" src="/images/products.png" alt="ordered Products"><span>product orders</span></a>
                <a href="/admin-product.html"><img class="icon" src="images/list.png" alt="products shop"><span>product list</span></a>
                <a href="/admin-charter-booking.html"><img class="icon" src="/images/fishing-boat.png" alt="charter bookings"><span>charter bookings</span></a>
                <a href="/admin-fishing-report.html"><img class="icon" src="/images/fish.png" alt="fishing report"><span>fishing report</span></a>
                <div class="line"></div>
            </div>
            <div class="bottom-links">
                <a href="/profile"><img class="icon profileImg" src="/images/profile.jpeg" alt="Profile"><span>Profile</span></a>
                <div class="line"></div>
                <a id="logoutButton"><img class="icon" src="/images/power-off.png" alt="Logout"><span>Logout</span></a>
            </div>
        `;

        body.insertBefore(sidenav, body.firstChild);

        const toggleBtn = document.createElement('button');
        toggleBtn.classList.add('toggle-btn');
        toggleBtn.textContent = '☰';
        toggleBtn.onclick = toggleNav;
        body.insertBefore(toggleBtn, body.firstChild);

        document.getElementById('logoutButton').addEventListener('click', function() {
            localStorage.removeItem('jwt');
            window.location.href = 'login.html';
        });
    });
}

export function toggleNav() {
    const sidenav = document.getElementById("mySidenav");
    sidenav.classList.toggle("open");

    const containerSection = document.querySelector(".container-section");
    containerSection.classList.toggle("sidenav-open");
}
