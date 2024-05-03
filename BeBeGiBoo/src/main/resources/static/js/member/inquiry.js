const memberName = document.querySelector("#memberName");
const phoneNum = document.querySelector("#phoneNum");
const email =  document.querySelector("#email");

//라디오 버튼 클릭 시 input 영역 나타나도록

const phoneArea = document.querySelector("#phoneArea");
const emailArea = document.querySelector("#emailArea");

phoneNum.addEventListener("change", ()=>{
    if(phoneArea.style.display == "none"){
        phoneArea.style.display = "block";
    }
    if(emailArea.style.display == "block"){
        emailArea.value = "";
        emailArea.style.display = "none";
        
    }
    
});
email.addEventListener("change", ()=>{
    if(emailArea.style.display == "none"){
        emailArea.style.display = "block"
    }
    if(phoneArea.style.display == "block"){
        phoneArea.value = "";
        phoneArea.style.display = "none";
        
    }
});


//이름, input 영역 값 넘기기POST -> 잘못 입력하면 alert하기 
//"잘못된 이메일입니다" / "잘못된 아이디입니다" -> 순서대로 비동기 검사하기

const IdCheckObj = {
    "memberName"    : false,
    "memberPhone"   : false,
    "memberEamil"   : false
}



const findBtn = document.querySelector("#findBtn");

findBtn.addEventListener("submit", ()=>{


});
