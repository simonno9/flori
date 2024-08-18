export function setProfileImg() {
    const image = localStorage.getItem('image');
    const profileImages = document.querySelectorAll('.profileImg');
    profileImages.forEach(img => {
        img.src = image;
    });
}
