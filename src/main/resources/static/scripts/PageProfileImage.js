const fileInput = document.querySelector('.file-input');
const imagePreview = document.querySelector('.avatarka');

fileInput.addEventListener('change', function(event) {
    const file = event.target.files[0];
    if (file && (file.type === 'image/jpeg' || file.type === 'image/png')) {
        const reader = new FileReader();
        reader.onload = function(e) {
            imagePreview.src = e.target.result;
            imagePreview.style.display = 'block';
        };
        reader.readAsDataURL(file);
    } else {
        alert('Пожалуйства, загрузите изображение в формате JPEG или PNG.');
        imagePreview.style.display = 'none';
    }
});