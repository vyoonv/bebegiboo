/* ***** 댓글 목록 조회(ajax) ***** */
const selectCommentList=()=>{
  console.log(boardNo);
    fetch("/comment?boardNo="+boardNo)
    .then(resp => resp.json())
    .then(commentList => {
        console.log(commentList);

        const ul = document.querySelector("#commentList");

        ul.innerHTML="";

        const commentCount = document.createElement("li");
        commentCount.classList.add("commentCount");
        commentCount.innerText = "전체 댓글 " +commentList.length+"개";

        ul.append(commentCount);


        for(let comment of commentList){
            //댓글 행 만들기
            const commentRow = document.createElement('li');
            commentRow.classList.add('commentRow');
            //대댓글인 경우
            if(comment.parentCommentNo != 0)
                commentRow.classList.add("childComment");
            //삭제된 경우
            if(comment.commentDelF == 'Y')
                commentRow.innerText = "삭제된 댓글입니다";
            //삭제되지 않은 경우
            else{ 
                
                const commentWrite = document.createElement("div");
                commentWrite.classList.add("commentWrite");

                //아이디, 작성일
                const infoArea = document.createElement("div");
                infoArea.classList.add("infoArea");

                const memberId = document.createElement("span");
                memberId.innerText = "@"+comment.memberId;

                const commentDate = document.createElement("span");
                commentDate.classList.add("commentWriteDate");
                commentDate.innerText = comment.commentWriteDate;

                infoArea.append(memberId, commentDate);
                commentWrite.append(infoArea);
                commentRow.append(commentWrite);

                //--------------------------------------
                //에딧 버튼 
                const editBtn = document.createElement("img");
                editBtn.setAttribute("src", "/images/addMore.png");
                editBtn.classList.add("editBtn");
                commentWrite.append(editBtn);


                const editArea=document.createElement("div");
                editArea.classList.add("editArea");
                editArea.style.display ='none';

                const childCommentBtn = document.createElement("button");
                childCommentBtn.classList.add("addChildBtn");
                childCommentBtn.innerText = "답글";

                editArea.append(childCommentBtn);
                commentWrite.append(editArea);

                if(loginMemberNo != null && loginMemberNo == comment.memberNo){
                    const updateBtn = document.createElement("button");
                    updateBtn.classList.add("updateBtn");
                    updateBtn.innerText="수정";

                    updateBtn.setAttribute("onclick", `showUpdateComment(${comment.commentNo},this)`);

                    const deleteBtn = document.createElement("button");
                    deleteBtn.classList.add("deleteBtn");
                    deleteBtn.innderText = "삭제";

                    deleteBtn.setAttribute("onclick", `deleteComment(${comment.commentNo})`);

                    editArea.append(updateBtn, deleteBtn);
                }

                //-------------------------------
                const content = document.createElement("div");
                content.classList.add("comment-content");

                content.innerText = comment.commentContent;

                 commentRow.append(content);
            }
            ul.append(commentRow);
        }
    });
}

/***********editBtn 클릭 시 답글, 수정, 삭제 버튼 보이도록************ */
const editBtnArray = document.getElementsByClassName("editBtn");


Array.from(editBtnArray).forEach(e => {

  e.addEventListener("click", ()=>{
    const editArea = e.previousElementSibling;
      if(editArea.style.display == "none"){
          editArea.style.display = "block";
      }else{
          editArea.style.display="none";
      }
  });
});


/* ***** 댓글 등록(ajax) ***** */

const addContent = document.querySelector("#addComment"); // button
const commentContent = document.querySelector("#commentContent"); // textarea

addContent.addEventListener("click", e => {

    // 댓글 내용이 작성되지 않은 경우
    if(commentContent.value.trim().length == 0){
        alert("내용 작성 후 등록 버튼을 클릭해 주세요");
        commentContent.focus();
        return;
    }



    // ajax를 이용해 댓글 등록하기
    const data = {
        "commentContent" : commentContent.value,
        "boardNo"        : boardNo,
        "memberNo"       : loginMemberNo  // 또는 Session 회원 번호 이용도 가능
    };

    fetch("/comment", {
        method : "POST",
        headers : {"Content-Type" : "application/json"},
        body : JSON.stringify(data)
    })
    .then(response => response.text())
    .then(result => {
        if(result > 0){
        alert("댓글이 등록 되었습니다");
        commentContent.value = ""; // 작성한 댓글 내용 지우기
        selectCommentList(); // 댓글 목록을 다시 조회해서 화면에 출력
        } else{
        alert("댓글 등록 실패");
        }

    })
    .catch(err => console.log(err));
})


/** 답글 작성 화면 추가
 * @param {*} parentCommentNo 
 * @param {*} btn 
 */
const showInsertComment = (parentCommentNo, btn) => {
  
  // ** 답글 작성 textarea가 한 개만 열릴 수 있도록 만들기 **
  const temp = document.getElementsByClassName("commentInsertContent");
  
  if(temp.length > 0){ // 답글 작성 textara가 이미 화면에 존재하는 경우
    
    if(confirm("답글을 작성 중입니다. 현재 댓글에 답글을 작성 하시겠습니까?")){
      temp[0].nextElementSibling.remove(); // 버튼 영역부터 삭제
      const editArea = temp[0].previousElementSibling.previousElementSibling.children[1];
      editArea.style.display = "none";
      temp[0].remove(); // textara 삭제 (기준점은 마지막에 삭제해야 된다!)
    } else{
      return; // 함수를 종료시켜 답글이 생성되지 않게함.
    }
  }
  
  // 답글을 작성할 textarea 요소 생성
  const textarea = document.createElement("textarea");
  textarea.classList.add("commentInsertContent");
  
  // 버튼의 부모의 형제 다음에 textarea 추가
  // after(요소) : 뒤쪽에 추가

  btn.parentElement.parentElement.nextElementSibling.after(textarea);


  // 답글 버튼 영역 + 등록/취소 버튼 생성 및 추가
  const commentBtnArea = document.createElement("div");
  commentBtnArea.classList.add("editArea");

  const insertBtn = document.createElement("button");
  insertBtn.innerText = "등록";
  insertBtn.classList.add("insertBtn");
  insertBtn.setAttribute("onclick", "insertChildComment("+parentCommentNo+", this)");

  const cancelBtn = document.createElement("button");
  cancelBtn.innerText = "취소";
  cancelBtn.classList.add("cancelBtn");
  cancelBtn.setAttribute("onclick", "insertCancel(this)");


  // 답글 버튼 영역의 자식으로 등록/취소 버튼 추가
  commentBtnArea.append(insertBtn, cancelBtn);

  // 답글 버튼 영역을 화면에 추가된 textarea 뒤쪽에 추가
  textarea.after(commentBtnArea);
} 



// ---------------------------------------

/** 답글 (자식 댓글) 작성 취소 
 * @param {*} cancelBtn : 취소 버튼
 */
const insertCancel = (cancelBtn) => {

  // 취소 버튼 부모의 이전 요소(textarea) 삭제
  cancelBtn.parentElement.previousElementSibling.remove();

  // 취소 버튼이 존재하는 버튼영역 삭제
  cancelBtn.parentElement.remove();
}


/** 답글 (자식 댓글) 등록
 * @param {*} parentCommentNo : 부모 댓글 번호
 * @param {*} btn  :  클릭된 등록 버튼
 */
const insertChildComment = (parentCommentNo, btn) => {

  // 답글 내용이 작성된 textarea
  const textarea = btn.parentElement.previousElementSibling;

  // 유효성 검사
  if(textarea.value.trim().length == 0){
    alert("내용 작성 후 등록 버튼을 클릭해 주세요");
    textarea.focus();
    return;
  }

  // ajax를 이용해 댓글 등록 요청
  const data = {
    "commentContent" : textarea.value,
    "boardNo"        : boardNo,
    "memberNo"       : loginMemberNo,  // 또는 Session 회원 번호 이용도 가능
    "parentCommentNo" : parentCommentNo // 부모 댓글 번호
  };

  fetch("/comment", {
    method : "POST",
    headers : {"Content-Type" : "application/json"},
    body : JSON.stringify(data) // data 객체를 JSON 문자열로 변환
  })

  .then(response => response.text())
  .then(result => {

    if(result > 0){
      alert("답글이 등록 되었습니다");
      selectCommentList(); // 댓글 목록을 다시 조회해서 화면에 출력
  
    } else{
      alert("답글 등록 실패");
    }

  })
  .catch(err => console.log(err));



}


// --------------------------------------------------

/** 댓글 삭제
 * @param {*} commentNo 
 */
const deleteComment = commentNo => {

  // 취소 선택 시
  if(!confirm("삭제 하시겠습니까?")) return;

  fetch("/comment",{
    method : "DELETE",
    headers : {"Content-Type" : "application/json"},
    body : commentNo
  })
  .then( resp => resp.text() )
  .then( result => {

    if(result > 0){
      alert("삭제 되었습니다");
      selectCommentList(); // 다시 조회해서 화면 다시 만들기
    
    } else {
      alert("삭제 실패");
      
    }

  })
  .catch( err => console.log(err));

}


// ----------------------------------

// 수정 취소 시 원래 댓글 형태로 돌아가기 위한 백업 변수
let beforeCommentRow;

/** 댓글 수정 화면 전환
 * @param {*} commentNo 
 * @param {*} btn 
 */
const showUpdateComment = (commentNo, btn) => {

  /* 댓글 수정 화면이 1개만 열릴 수 있게 하기 */
  const temp = document.querySelector(".update-textarea");

  // .update-textarea 존재 == 열려있는 댓글 수정창이 존재
  if(temp != null){

    if(confirm("수정 중인 댓글이 있습니다. 현재 댓글을 수정 하시겠습니까?")){

      const commentRow = temp.parentElement; // 기존 댓글 행
      commentRow.after(beforeCommentRow); // 기존 댓글 다음에 백업 추가
      commentRow.remove(); // 기존 삭제 -> 백업이 기존 행 위치로 이동

    } else{ // 취소
      return;
    }
  }


  // -------------------------------------------

  // 1. 댓글 수정이 클릭된 행 (.comment-row) 선택
  const commentRow = btn.closest("li"); 

  // 2. 행 전체를 백업(복제)
  // 요소.cloneNode(true) : 요소 복제, 
  //           매개변수 true == 하위 요소도 복제
  beforeCommentRow = commentRow.cloneNode(true);
  console.log("beforeCommentRow",beforeCommentRow);

  // 3. 기존 댓글에 작성되어 있던 내용만 얻어오기
  let beforeContent = commentRow.children[1].innerText;

  // 4. 댓글 행 내부를 모두 삭제
  commentRow.innerHTML = "";

  // 5. 아이디와 등록일 영역 추가
  const infoArea = document.createElement("div");
  infoArea.classList.add("infoArea");

  const memberId = document.createElement("span");
  memberId.classList.add("memberId");
  memberId.innerText = beforeCommentRow.querySelector(".memberId").innerText;

  const commentWriteDate = document.createElement("span");
  commentWriteDate.classList.add("commentWriteDate");
  commentWriteDate.innerText= beforeCommentRow.querySelector(".commentWriteDate").innerText;

  infoArea.append(memberId, commentWriteDate);

  // 5-1. textarea 생성 + 클래스 추가 + 내용 추가
  const textarea = document.createElement("textarea");
  textarea.classList.add("update-textarea");
  textarea.value = beforeContent;

  // 6. 댓글 행에 textarea 추가
  commentRow.append(infoArea,textarea);

  // 7. 버튼 영역 생성
  const commentBtnArea = document.createElement("div");
  commentBtnArea.classList.add("comment-btn-area");

  // 8. 수정 버튼 생성
  const updateBtn = document.createElement("button");
  updateBtn.innerText = "수정";
  updateBtn.classList.add("updateBtn");
  updateBtn.setAttribute("onclick", `updateComment(${commentNo}, this)`);

  // 9. 취소 버튼 생성
  const cancelBtn = document.createElement("button");
  cancelBtn.innerText = "취소";
  cancelBtn.classList.add("cancelBtn");
  cancelBtn.setAttribute("onclick", "updateCancel(this)");

  // 10. 버튼 영역에 수정/취소 버튼 추가 후
  //     댓글 행에 버튼 영역 추가
  commentBtnArea.append(updateBtn, cancelBtn);
  commentRow.append(commentBtnArea);
}


// --------------------------------------------------------------------

/** 댓글 수정 취소
 * @param {*} btn : 취소 버튼
 */
const updateCancel = (btn) => {

  if(confirm("취소 하시겠습니까?")){
    const commentRow = btn.closest("li"); // 기존 댓글 행
    commentRow.after(beforeCommentRow); // 기존 댓글 다음에 백업 추가
    commentRow.remove(); // 기존 삭제 -> 백업이 기존 행 위치로 이동
  }

}


// ----------------------------------------------------------

/** 댓글 수정
 * @param {*} commentNo : 수정할 댓글 번호
 * @param {*} btn       : 클릭된 수정 버튼
 */
const updateComment = (commentNo, btn) => {

  // 수정된 내용이 작성된 textarea 얻어오기
  const textarea = btn.parentElement.parentElement.nextElementSibling;

  // 유효성 검사
  if(textarea.value.trim().length == 0){
    alert("댓글 작성 후 수정 버튼을 클릭해 주세요");
    textarea.focus();
    return;
  }

  // 댓글 수정 (ajax)
  const data = {
    "commentNo" : commentNo,
    "commentContent" : textarea.value
  }

  fetch("/comment", {
    method : "PUT",
    headers : {"Content-Type" : "application/json"},
    body : JSON.stringify(data)
  })
  .then(resp => resp.text())
  .then(result => {
    if(result > 0){
      alert("댓글이 수정 되었습니다");
      selectCommentList();
    } else {
      alert("댓글 수정 실패");
    }

  })
  .catch(err => console.log(err));
}