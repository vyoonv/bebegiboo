

// 주소 검색 버튼 클릭 시
document.querySelector("#searchBtn").addEventListener("click", e => {
    e.preventDefault(); 
    DaumPostcode(); 
});

/* 다음 주소 API 활용 */

function DaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {

            var addr = ''; 

            if (data.userSelectedType === 'R') { 
                addr = data.roadAddress;
            } else { 
                addr = data.jibunAddress;
            }
            document.getElementById('postcode').value = data.zonecode;
            document.getElementById("mainAddress").value = addr;
            document.getElementById("detailAddress").focus();
        }
    }).open();
}



/* 수정 폼 제출시 */
const updateForm = document.querySelector("#updateForm"); 

if(updateForm != null) {
    
    updateForm.addEventListener("submit", e => {
        
        /* 이메일 */
        const email = document.querySelector("#email"); 

        /* 핸드폰 번호*/
        const phone = document.querySelector("#phone"); 

        /* 비밀번호*/
        const newPw = document.querySelector("#newPw");
        const newPwConfirm = document.querySelector("#newPwConfirm");



        /* 이메일 유효성 검사 */
        if(email.value.trim().length === 0) {
            alert("이메일을 입력해주세요"); 
            e.preventDefault(); 
            return; 
        }

        let regExp = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        if( !regExp.test(email.value)){
            alert("사용 가능한 이메일을 입력해주세요~!!"); 
            e.preventDefault(); 
            return; 
        }

        // 이메일 중복검사 //


        /* 핸드폰번호 유효성 검사 */
        if(phone.value.trim().length === 0) {
            alert("전화번호를 입력해주세요"); 
            e.preventDefault(); 
            return; 
        }

        regExp = /^(010|011|016|017|018|019)-\d{3,4}-\d{4}$/; 
        if( !regExp.test(phone.value)) {
            alert("입력하신 전화번호를 확인해주세요"); 
            e.preventDefault(); 
            return;
        }

        /* 비밀번호 유효성 검사
        if(newPw.value.trim().length != null) {
            regExp = /^[a-zA-Z0-9_-]{8,12}$/; 

            if( !regExp.test(newPw.value) ) {
                alert("영어, 숫자, 특수문자(-,_)8~12글자 이내로 입력해주세요."); 
                e.preventDefault(); 
                return; 
            }

            if(newPw.value != newPwConfirm.value) {
                alert("새 비밀번호가 일치하지 않습니다"); 
                e.preventDefault(); 
                return; 
            }
        } */

        /* 비밀번호 유효성 검사 */
        if (newPw.value.trim().length !== 0) {
            regExp = /^[a-zA-Z0-9_-]{8,12}$/;

            if (!regExp.test(newPw.value)) {
                alert("영어, 숫자, 특수문자(-,_)8~12글자 이내로 입력해주세요.");
                e.preventDefault();
                return;
            }

            if (newPw.value !== newPwConfirm.value) {
                alert("새 비밀번호가 일치하지 않습니다");
                e.preventDefault();
                return;
            }
        }


        /* 주소 유효성 검사 */
        const addr0 = address[0].value.trim().length == 0; 
        const addr1 = address[1].value.trim().length == 0; 
        const addr2 = address[2].value.trim().length == 0; 

        const result = addr0 && addr1 && addr2; // 아무것도 입력하지 않은 상태 

        if( !result ) {
            alert("주소를 모두 입력해주세요"); 
            e.preventDefault(); 
        }

    });
}; 

/* 회원 탈퇴시 */

const resignForm = document.getElementById('resignForm');

if(resignForm != null) {

    resignForm.addEventListener('submit', e => {
    
        const memberPw = document.querySelector("#memberPw"); 
        const agree = document.querySelector("#agree"); 

        if(memberPw.value.trim().length == 0) {
            alert("비밀번호를 입력해주세요"); 
            e.preventDefault(); 
            return; 
        }
        
        if(!agree.checked) { // 체크 안됐을 때
            alert("약관에 동의해주세요");
            e.preventDefault();
            return;
        }

        if(!confirm("정말 탈퇴하시겠습니까?")){
            alert("취소되었습니다"); 
            e.preventDefault(); 
            return; 
        }

    });
}


/* 모달 */

const openBtn = document.getElementById('openBtn');
const closeBtn = document.getElementById('closeBtn');
const modal = document.getElementById('modal');

openBtn.addEventListener('click', e => {
    e.preventDefault(); 
    modal.classList.remove('hidden');
});

closeBtn.addEventListener('click', e => {
    e.preventDefault(); 
    modal.classList.add('hidden');
});













    