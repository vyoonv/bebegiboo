const question = document.querySelector("#question"); 
const answer = document.querySelector("#answer"); 
const categoryNo = document.querySelector("#categoryNo");  

/* 수정하기 유효성 검사 */
document.getElementById('editFaq').addEventListener("submit", e => {

    if(categoryNo.value == null ) {
        alert("카테고리 선택!!"); 
        e.preventDefault(); 
        return; 
    }

    if(question.value.trim().length == 0) {
        alert("질문 작성!"); 
        question.focus(); 
        e.preventDefault(); 
        return; 
    }

    if(answer.value.trim().length == 0) {
        alert("답변 입력!!"); 
        answer.focus(); 
        e.preventDefault(); 
        return; 
    }
  
}); 


document.getElementById('cancelBtn').addEventListener('click', e =>{
    e.preventDefault(); 
    location.href = "faqBoard"; 
});