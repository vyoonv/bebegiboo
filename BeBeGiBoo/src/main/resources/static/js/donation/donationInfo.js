const detailsList = document.querySelectorAll('#donation-detail');
detailsList.forEach(details => {
    details.addEventListener('click', () => {
        const plus = details.querySelector('#plus');

        if (details.open) {
            plus.innerText = "+"; 
        } else {
            plus.innerText = "-"; 
        }
    });
});