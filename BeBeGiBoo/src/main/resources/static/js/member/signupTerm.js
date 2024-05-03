
/* ************* TERM ************** */
const checkboxesTerm = document.querySelectorAll('.check');
const checkAll = document.querySelector("#checkAll"); 
const check1 = document.querySelector("#check1"); 
const check2 = document.querySelector("#check2"); 
const agreeBtn = document.querySelector("#agreeBtn"); 



/* 모두 선택 인풋 */
checkAll.addEventListener('change', e => {
    
    let checkAll = e.target; 

    if (checkAll.checked) {
        // 모든 체크박스를 선택함
        checkboxesTerm.forEach(function(checkbox) {
            checkbox.checked = true;
        });
    }  
    else {
        // checkAll 체크박스가 체크 해제되었을 때, 모든 체크박스를 선택 해제함
        checkboxesTerm.forEach(function(checkbox) {
            checkbox.checked = false;
        });
    }
});


const authorityValue = document.querySelector("#authority"); 
const authority = authorityValue.value; 
console.log(authority); 


/* 동의 버튼 클릭시 */
agreeBtn.addEventListener("click", e => {

    e.preventDefault(); 

    // check1과 check2가 모두 선택되었는지 확인
    if (check1.checked && check2.checked) {
        
        location.href = "/member/signup/signupForm?authority=" + authority; 
        
    } else {        
        
        alert("이용약관 및 개인정보 수집 및 이용에 모두 동의해주세요.");
    }

}); 