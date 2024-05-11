const detailsList = document.querySelectorAll('#donation-detail');
detailsList.forEach(details => {
    details.addEventListener('click', () => {
        const arrow = details.querySelector('#arrow');

        if (details.open) {
            arrow.innerText = "+"; 
        } else {
            arrow.innerText = "-"; 
        }
    });
});