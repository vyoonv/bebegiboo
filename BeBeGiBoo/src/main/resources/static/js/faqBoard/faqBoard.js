document.getElementById('insertBtn').addEventListener("click", ()=>{
    location.href = "/faqBoard/insertFaq"
})



document.addEventListener('DOMContentLoaded', () => {
    const deleteBtns = document.querySelectorAll('#deleteBtn');

    deleteBtns.forEach(deleteBtn => {
        deleteBtn.addEventListener('click', e => {

            e.preventDefault(); 

            if (!confirm("삭제하시겠습니까?")) {
                alert("취소");
                return;
            }

            const form = deleteBtn.closest('form');  
            form.submit(); 

        });
    });
});

