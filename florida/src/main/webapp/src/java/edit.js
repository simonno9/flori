
export function editRow(id,location,type,saved) {
    const rows = JSON.parse(localStorage.getItem(type)) || [];
    const row = rows.find(r => r.id === id);
    localStorage.setItem(`${saved}`, JSON.stringify(row));
    window.location.href = `${location}?id=${id}`;
}
// function editform(id, type) {
//     const data = JSON.parse(localStorage.getItem(type)) || [];
//     const item = data.find(r => r.id === id);
//     if (item) {
//         localStorage.setItem('selectedProduct', JSON.stringify(item));
//         window.location.href = `admin-product-edit.html?id=${id}`;
//     }
// }