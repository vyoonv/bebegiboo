
//댓글 1개 이상인 경우 current클래스 추가(파란색 글씨)
const commentCount = document.querySelector("#commentCount");

if(commentCount.value > 0){
    commentCount.classList.add("current");
}