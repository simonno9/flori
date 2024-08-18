export async function deleteRow(id, update,query) {
    const jwt = localStorage.getItem('jwt');
    console.log('test',id,update,query)
    const response = await fetch(query, {
        method: 'DELETE',
        headers: {
            'Authorization': `Bearer ${jwt}`
        }
    });
    if (response.ok) {
        update(); // Call the update function passed as an argument
    } else {
        console.error('Error deleting report:', response.statusText);
    }
}