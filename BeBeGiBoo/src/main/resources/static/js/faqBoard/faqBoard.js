/* 글쓰기 버튼 */

document.addEventListener('DOMContentLoaded', () => {
    const insertBtn = document.getElementById('insertBtn');
    if (insertBtn) {
        insertBtn.addEventListener("click", () => {
            location.href = "/faqBoard/insertFaq";
        });
    }
});


/* 삭제 버튼 */
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


/* 카테고리 클릭시 목록 전환 */
document.addEventListener('DOMContentLoaded', function() {

    var categoryLinks = document.querySelectorAll('.category-link');

    if (categoryLinks.length > 0) {

        categoryLinks.forEach(function(link) {
            
            link.addEventListener('click', function() {
                var categoryNo = this.getAttribute('data-category');

                // 페이지네이션 숨기기
                var pagination = document.getElementById('pagination');
                pagination.style.display = 'none';

                fetch("/faqBoard/getFaqByCategory?categoryNo=" + categoryNo)
                    .then(resp => resp.json())
                    .then(list => {
                        console.log(list); 

                        var faqListTable = document.getElementById('faqListTable');
                        faqListTable.innerHTML = '';

                        list.forEach((faqBoard, index) => {
                            const tr = document.createElement('tr');
                            const details = document.createElement('details');
                            const summary = document.createElement('summary');
                            const figure = document.createElement('figure');
                            summary.textContent = "[" + faqBoard.categoryNo + "] " + faqBoard.question;
                            figure.textContent = faqBoard.answer;
                            details.appendChild(summary);
                            details.appendChild(figure);
                            tr.appendChild(details);
                            faqListTable.appendChild(tr);
                        });
                    })
                    .catch(error => {
                        console.error('Error fetching data:', error);
                    });
            });
        });
    }
});
