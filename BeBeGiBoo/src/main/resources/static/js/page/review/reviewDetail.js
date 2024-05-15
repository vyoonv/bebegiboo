const updateBtn = document.querySelector("#updateBtn");

//게시글 수정 버튼 클릭 -> 주소를 review/boardNo?cp=1 에서 editReview/boardNo/update?cp=1로 바꿔줌
if(updateBtn != null){
    updateBtn.addEventListener("click", e=>{
        location.href = location.pathname.replace('review', 'editReview')
                            + "/update"
                            +location.search;
    });
}

//게시글 삭제
const deleteBtn = document.querySelector("#deleteBtn");

if(deleteBtn != null){
    deleteBtn.addEventListener("click", ()=>{
        if(!confirm("삭제하시겠습니까?")){
            return;
        }

        const url = location.pathname.replace("review", "editReview") + "/delete";
        const queryString = location.search; //?cp=1
        location.href= url + queryString;
    });
}

//목록으로 돌아가기
const goToListBtn = document.querySelector("#goToListBtn");

goToListBtn.addEventListener("click", () => {

  // 상세조회 : /review/2011?cp=1
  // 목록     : /review?cp=1

  let url = location.pathname;
  url = url.substring(0, url.lastIndexOf("/"));

  location.href = url + location.search;
                        // 쿼리스트링
});