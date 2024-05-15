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
            link.addEventListener('click', handleCategory);
        });
    }

    function handleCategory() {

        categoryLinks.forEach(function(link) {
            
            link.addEventListener('click', function() {
                
                var categoryNo = this.getAttribute('data-category');
                
                //var loginMemberAuthority = parseInt(document.getElementById('loginMemberAuthority').value);
                var loginMemberAuthorityElement = document.getElementById('loginMemberAuthority');
                var loginMemberAuthority = 0; 
                if (loginMemberAuthorityElement && loginMemberAuthorityElement.value) {
                    loginMemberAuthority = parseInt(loginMemberAuthorityElement.value, 10);
                }
                console.log(loginMemberAuthority);

                // 페이지네이션 숨기기
                var pagination = document.getElementById('pagination');
                pagination.style.display = 'none';

                fetch("/faqBoard/getFaqByCategory?categoryNo=" + categoryNo)
                    .then(resp => resp.json())
                    .then(list => {
                        console.log(list);
                        console.log(list[0].qno);

                        var faqListTable = document.getElementById('faqListTable');
                        faqListTable.innerHTML = '';

                        list.forEach((faqBoard, index) => {
                            const tr = document.createElement('tr');
                            const details = document.createElement('details');
                            const summary = document.createElement('summary');
                            const figure = document.createElement('figure');
                            //summary.textContent = "[" + faqBoard.categoryNo + "]" + "   " + faqBoard.question;
                            summary.innerHTML = "<span class='category-no'>[" + faqBoard.categoryNo + "]  </span> " + faqBoard.question;
                            summary.innerHTML = "<div>" + "<span class='category-no'>[" + faqBoard.categoryNo + "]  </span> " 
                                                + "<span>" + faqBoard.question + "</span>" + "</div>";
                            figure.textContent = faqBoard.answer;
                            summary.id = 'question';
                            faqBoard.question.id = 'qTitle';

                            // 플러스/마이너스 아이콘 추가
                            const plus = document.createElement('span');
                            plus.id = 'plus';
                            plus.innerText = "+";
                            summary.appendChild(plus);
                            details.appendChild(summary);

                            if (loginMemberAuthority === 3) {
                                const buttonsDiv = document.createElement('div');
                               buttonsDiv.innerHTML = `<form><a href="editFaq?qNo=${faqBoard.qno}" id="editBtn">수정</a></form> `;

                                // 새로운 <form> 요소 생성
                                var formElement = document.createElement("form");
                                formElement.method = "post";
                                formElement.id = "deleteForm";  

                                const actionUrl = `/faqBoard/deleteFaq/${faqBoard.qno}`;
                                formElement.action = actionUrl;  

                                formElement.setAttribute("th:action", actionUrl);

                                // <a> 요소 생성, 삭제 버튼으로 사용
                                const deleteLink = document.createElement("a");
                                deleteLink.href = "#"; // 링크를 #으로 설정
                                deleteLink.textContent = "삭제";
                                deleteLink.id = "deleteBtn";  
                                formElement.appendChild(deleteLink);

                                // buttonsDiv에 <form> 요소 추가
                                buttonsDiv.appendChild(formElement);

                                // buttonsDiv를 문서에 추가
                                buttonsDiv.id = 'buttons';
                                details.appendChild(buttonsDiv); 

                                // 버튼에 이벤트 리스너 추가
                                deleteLink.addEventListener('click', e => {
                                    e.preventDefault(); // 기본 링크 동작을 방지

                                    if (!confirm("삭제하시겠습니까?")) {
                                        alert("취소되었습니다.");
                                        return;
                                    }

                                    const form = deleteLink.closest('form');  
                                    form.submit(); 
                                });
                            }
                            details.appendChild(figure);
                            tr.appendChild(details);
                            faqListTable.appendChild(tr);
                           
                            // 엔터 적용 
                            figure.innerHTML = faqBoard.answer.replace(/\n/g, "<br>");

                             // 플러스/마이너스 아이콘 토글 이벤트
                            details.addEventListener('click', () => {
                                const plus = details.querySelector('#plus');
                                plus.innerText = details.open ? "+" : "-";
                            });
                        });
                    })
                    .catch(error => {
                        console.error('Error:', error);
                    });
            });
        });
    }
});



const detailsList = document.querySelectorAll('#faqDetails');
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