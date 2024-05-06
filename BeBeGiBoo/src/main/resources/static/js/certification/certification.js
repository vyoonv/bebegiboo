/* 생년월일 유효성 검사 */

const memberBirth = document.querySelector("#memberBirth"); 
const formMessage = document.querySelector("#formMessage"); 

memberBirth.addEventListener("input", e => {

    const inputBirth = e.target.value; 

     // 입력되지 않은 경우 
     if(inputBirth.trim().length === 0) {
        formMessage.innerText = "생년월일 8자리 '-' 없이 입력해주세요"; 
        formMessage.classList.remove('confirm', 'error'); 
        memberBirth.value = ""; 
        return; 
    }

    // 생일 정규식
    const regExp = /^\d{8}$/; 
    
    // 유효하지 않은 경우 
    if( !regExp.test(inputBirth) ) {
        formMessage.innerText = "생년월일 8자리 '-' 없이 입력해주세요"; 
        formMessage.classList.add('error'); 
        formMessage.classList.remove('confirm'); 
        return; 
    }

    // 유효한 경우 
    formMessage.innerText = ""; 
    formMessage.classList.add('confirm'); 
    formMessage.classList.remove('error'); 


}); 



/* 핸드폰 번호 유효성 검사 */

const phone = document.querySelector("#phone"); 

phone.addEventListener("input", e => {

    const inputPhone = e.target.value; 

     // 입력되지 않은 경우 
     if(inputPhone.trim().length === 0) {
        formMessage.innerText = "핸드폰번호 '-'포함해서 입력해주세요"; 
        formMessage.classList.remove('confirm', 'error'); 
        phone.value = ""; 
        return; 
    }

    // 핸드폰번호 정규식
    const regExp = /^(010|011|016|017|018|019)-\d{3,4}-\d{4}$/; 
    
    // 유효하지 않은 경우 
    if( !regExp.test(inputPhone) ) {
        formMessage.innerText = "핸드폰번호 '-'포함해서 입력해주세요"; 
        formMessage.classList.add('error'); 
        formMessage.classList.remove('confirm'); 
        return; 
    }

    // 유효한 경우 
    formMessage.innerText = ""; 
    formMessage.classList.add('confirm'); 
    formMessage.classList.remove('error'); 

}); 




/* 다음 주소 API 활용 */

function DaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('postcode').value = data.zonecode;
            document.getElementById("mainAddress").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("detailAddress").focus();
        }
    }).open();
}

// 주소 검색 버튼 클릭 시
document.querySelector("#searchBtn").addEventListener("click", DaumPostcode);


/* 모달 */

const openBtn = document.getElementById('openBtn');
const openBtn2 = document.getElementById('openBtn2');
const closeBtn = document.getElementById('closeBtn');
const closeBtn2 = document.getElementById('closeBtn2');
const modal = document.getElementById('modal');

openBtn.addEventListener('click', () => {
  modal.classList.remove('hidden');
});
openBtn2.addEventListener('click', () => {
  modal.classList.remove('hidden');
});

closeBtn.addEventListener('click', () => {
  modal.classList.add('hidden');
});
closeBtn2.addEventListener('click', () => {
  modal.classList.add('hidden');
});