const insertBtn = document.querySelector("#insertBtn");

if(insertBtn != null){
    insertBtn.addEventListener("click", ()=>{
        location.href = `/review/insert`;
    });
}

//댓글 1개 이상인 경우 current클래스 추가(파란색 글씨)
const commentCount = document.querySelector("#commentCount");

if(commentCount.value > 0){
    commentCount.classList.add("current");
}