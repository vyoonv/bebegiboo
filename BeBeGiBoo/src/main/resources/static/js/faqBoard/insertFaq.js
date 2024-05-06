const question = document.querySelector("#question"); 
const answer = document.querySelector("#answer"); 
const categoryNo = document.querySelector("#categoryNo"); 



/* 글쓰기 유효성 검사 */
document.getElementById('insertBtn').addEventListener("click", e => {

  

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

