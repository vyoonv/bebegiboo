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


/* 이미지 미리보기 */
const previewList = document.querySelectorAll(".preview");
const inputImageList = document.querySelectorAll(".inputImage");
const deleteImageList = document.querySelectorAll(".delete-image");

const backupInputList = new Array(inputImageList.length); 

const changeImageFn = (inputImage, order) => {
    const maxSize = 1024 * 1024 * 10; 
    const file = inputImage.files[0]; 

    if(file == undefined){
        const temp = backupInputList[order].cloneNode(true); 

        inputImage.after(temp);
        inputImage.remove(); 
        inputImage = temp; 
        
        inputImage.addEventListener('change', e => {
            changeImageFn(e.target, order); 
        })

        return;
    }

    // 선택된 파일 크기가 최대크기 초과시 
    if(file.size > maxSize){
        alert("10MB 이하 이미지를 선택해주세요"); 

        if(backupInputList[order] == undefined || backupInputList[order].value == '') {
            inputImage.value = ""; 
            return; 
        }

        const temp = backupInputList[order].cloneNode(true); 

        inputImage.after(temp); 
        inputImage.remove(); 
        inputImage = temp; 

        inputImage.addEventListener("change", e => {
            changeImageFn(e.target, order); 
        })

        return; 
    }

    // 선택된 이미지 미리보기 
    const reader = new FileReader(); 

    reader.readAsDataURL(file); 

    reader.addEventListener("load", e=> {
        const url = e.target.result; 

        previewList[order].src = url; 

        backupInputList[order] = inputImage.cloneNode(true); 
    }); 
}

for(let i=0; i<inputImageList.length; i++){
    inputImageList[i].addEventListener("change", e => {
        changeImageFn(e.target, i); 
    })

    deleteImageList[i].addEventListener("click", ()=> {
        previewList[i].src = "";
        inputImageList[i].value = ""; 
        backupInputList[i].value = ""; 
    });
}




