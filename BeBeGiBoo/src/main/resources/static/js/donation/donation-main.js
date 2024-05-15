const slides = document.querySelectorAll(".donation-container");
const donationBoxUp14 = document.querySelectorAll(".donation-box-up14");
const donationBoxUnder14 = document.querySelectorAll(".donation-box-under14");
const title = document.querySelector("#donation-title");
const things = document.getElementsByName("count-thing");
const thingsContent = document.getElementsByName("content-thing");
const thingsType = document.getElementsByName("thing-type");
const thingsValue = document.getElementsByName("count-thing-value");

const startButton = document.querySelector("button");




titleObjUnder14 = ["신청자 나이 선택",
            "14세 미만 법정대리인(보호자) 동의",
            "유아용품 종류 선택",
            "유아용품 이름 입력",
            "박스수량 선택",
            "배송정보 입력",
            "결제수단 선택"];

titleObjUp14 = ["신청자 나이 선택",
            "유아용품 종류 선택",
            "유아용품 이름 입력",
            "박스수량 선택",
            "배송정보 입력",
            "결제수단 선택"];

/* 나이선택칸 */
const under14 = document.querySelector("#option1");
const up14 = document.querySelector("#option2");

const parentsAgree = document.querySelector("#parentsAgree");




/* 기부하기 버튼 눌렀을 때 */
startButton.addEventListener("click", () => {
    slides[0].style.display = 'none';
    slides[1].style.display = 'flex';
});

const previousButton = document.querySelector("#previous");
const nextButton = document.querySelector("#next");
const submitButton = document.querySelector("#submit");





/* 이전버튼 눌렀을 때 */
previousButton.addEventListener("click", e=>{
    nextButton.style.display = 'flex';
    submitButton.style.display = 'none';

    if(document.querySelector("#option1").checked){
        for(let i = 0; i < donationBoxUnder14.length; i++){

            if(i == 0) {
                e.preventDefault();
            } else {

                if(donationBoxUnder14[i].style.display != 'none'){
                    donationBoxUnder14[i].style.display = 'none';
                    donationBoxUnder14[i-1].style.display = 'flex';
                    title.innerText = titleObjUnder14[i-1];
                    break;
                }

            }
        }
    } else if(document.querySelector("#option2").checked){
        for(let i = 0; i < donationBoxUp14.length; i++){

            if(i == 0) {
                e.preventDefault();
            } else {
                if(donationBoxUp14[i].style.display != 'none'){
                    donationBoxUp14[i].style.display = 'none';
                    donationBoxUp14[i-1].style.display = 'flex';
                    title.innerText = titleObjUp14[i-1];
                    break;
                }

            }
            
        }
    }
});


document.querySelector("#oneCheck").checked = true;


/* 다음버튼 눌렀을 때 */
nextButton.addEventListener("click", e=>{
    const parentsName = document.querySelector("#parentsName");
    const parentsMiddleNo = document.querySelector("#parentsMiddleNo");
    const parentsLastNo = document.querySelector("#parentsLastNo");
    const thingInput = document.getElementsByName("content-thing-input");




    /* 배송정보 */
    const donatorName = document.querySelector("#name").value;
    const donatorPhone = document.querySelector("#phone").value;
    const donatorPhone2 = document.querySelector("#phone2").value;
    const postcode = document.querySelector("#postcode").value;
    const mainAddress = document.querySelector("#mainAddress").value;
    const detailAddress = document.querySelector("#detailAddress").value;
    const date = document.querySelector("#date").value;
    const memo = document.querySelector("#memo").value;


    var ddate = new Date();
    var year = ddate.getFullYear();
    var month = ("0" + (1 + ddate.getMonth())).slice(-2);
    var day = ("0" + ddate.getDate()).slice(-2);

    document.querySelector("#date").min = year + "-" + month + "-" + day;




    /* 나이선택칸 */
    if(!document.querySelector("#option1").checked && !document.querySelector("#option2").checked) {
        alert("나이 유형을 선택해주세요");
        e.preventDefault();
    };

    /* 체크 다 안되어있다면 alert */
    let checkAll = false;
    for(let i = 0; i < thingsType.length; i++) {
        if(thingsType[i].checked == true) {
            checkAll = true;
        };
    };


    /* 내용입력칸 */
    for(let i = 0; i < thingsType.length; i ++) {

        if(!thingsType[i].checked) {
            thingsContent[i].style.display = 'none';
            thingInput[i].value = "";
        } else {
            thingsContent[i].style.display = 'block';
        }
    }

    /* 박스수량 체크칸 */
    for(let i = 0; i < thingsType.length; i ++) {
        
        if(!thingsType[i].checked) {
            things[i].style.display = 'none';
            things[i].value = undefined;
            thingsValue[i].value = 0;
        } else if(thingsType[i].checked) {
            things[i].style.display = 'block';
        }
    }


    /* 가격칸 */
    let countValue = 0;
    for(let i = 0; i < thingsValue.length; i ++) {
        let pay = parseInt(thingsValue[i].value) * 5000;
        countValue += pay;
    }

    const total = document.querySelector("#total");
    total.innerText = countValue;




    /* 페이지 넘어가기 */


    if(document.querySelector("#option1").checked){
        if(checkAll == true) {
            
            for(let i = 0; i < donationBoxUnder14.length; i++){


                function pagenation() {
                    donationBoxUnder14[i].style.display = 'none';
                    donationBoxUnder14[i+1].style.display = 'flex';
                    title.innerText = titleObjUnder14[i+1];
                                        
                    if(i == 5) {
                        nextButton.style.display = 'none';
                        submitButton.style.display = 'flex';
                    }else {
                        nextButton.style.display = 'flex';
                        submitButton.style.display = 'none';
                    }
                }
                


                if(donationBoxUnder14[i].style.display != 'none'){

                    if(i == 1){
                        if(parentsName.value.trim().length == 0 || parentsMiddleNo.value.trim().length == 0 || parentsLastNo.value.trim().length == 0){
                            alert("입력을 모두 완료해주세요");
                        } else if(document.querySelector("#parentsAgree").checked == false) {
                            alert("동의사항에 체크해주세요");
                        } else if(kakaoClick == 0) {
                            alert("카카오 인증하기를 완료해주세요");
                        } else {
                            pagenation();
                            break;
                        }
                    } else if(i == 3) {
                        let modal = true;
                        for(let j = 0; j < thingsType.length; j ++) {

                            if(thingsType[j].checked) {

                                if(thingInput[j].value.trim().length == 0) {
                                    modal = false;
                                    alert("입력을 모두 완료해주세요");
                                    break;
                                }
                            }
                        }

                        if(modal == true) {
                            pagenation();
                            break;
                        }


                    }else if(i == 4) {
                        let modal = true;
                        for(let i = 0; i < thingsType.length; i ++) {
        
                            if(thingsType[i].checked) {
                                if(thingsValue[i].value == 0) {
                                    modal = false;
                                    alert("박스수량은 최소 1개 이상이어야 합니다");
                                    break;
                                }
                            }
                        }

                        if(modal == true) {
                            pagenation();
                            break;
                        }
                    } else if(i == 5) {

                        if(donatorName.trim().length == 0 || donatorPhone.trim().length == 0 || donatorPhone2.trim().length == 0 || postcode.trim().length == 0 || mainAddress.trim().length == 0 || detailAddress.trim().length == 0 || date.trim().length == 0 || memo.length == 0){
                            alert("입력을 모두 완료해주세요");
                        } else {
                            pagenation();
                            break;
                        }

                    
                    } else {
                        pagenation();
                        break;

                    }

                }
            }
        } else {
            alert("적어도 하나의 타입을 선택해 주세요")
        }
    } else if(document.querySelector("#option2").checked){
        if(checkAll == true) {
            for(let i = 0; i < donationBoxUp14.length; i++){

                function pagenation() {
                    if(donationBoxUp14[i].style.display != 'none'){
                        donationBoxUp14[i].style.display = 'none';
                        donationBoxUp14[i+1].style.display = 'flex';
                        title.innerText = titleObjUp14[i+1];
        
                        if(i == 4) {
                            nextButton.style.display = 'none';
                            submitButton.style.display = 'flex';
                        }else {
                            nextButton.style.display = 'flex';
                            submitButton.style.display = 'none';
                        }
                    }
                }


                if(donationBoxUp14[i].style.display != 'none'){

                    if(i == 2) {
                        let modal = true;
                        for(let j = 0; j < thingsType.length; j ++) {

                            if(thingsType[j].checked) {

                                if(thingInput[j].value.trim().length == 0) {
                                    modal = false;
                                    alert("입력을 모두 완료해주세요");
                                    break;
                                }
                            }
                        }

                        if(modal == true) {
                            pagenation();
                            break;
                        }


                    }else if(i == 3) {
                        let modal = true;
                        for(let i = 0; i < thingsType.length; i ++) {
        
                            if(thingsType[i].checked) {
                                if(thingsValue[i].value == 0) {
                                    modal = false;
                                    alert("박스수량은 최소 1개 이상이어야 합니다");
                                    break;
                                }
                            }
                        }

                        if(modal == true) {
                            pagenation();
                            break;
                        }
                    } else if(i == 4) {

                        if(donatorName.trim().length == 0 || donatorPhone.trim().length == 0 || donatorPhone2.trim().length == 0 || postcode.trim().length == 0 || mainAddress.trim().length == 0 || detailAddress.trim().length == 0 || date.trim().length == 0 || memo.length == 0){
                            alert("입력을 모두 완료해주세요");
                        } else {
                            pagenation();
                            break;
                        }

                    
                    } else {
                        pagenation();
                        break;

                    }

                }
                
                
            }
        } else {
            alert("적어도 하나의 타입을 선택해 주세요")
        }
    }


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


document.querySelector("#searchAddress").addEventListener("click", DaumPostcode);







submitButton.addEventListener("click", e => {

    if(document.querySelector("#paymentAgree").checked == false) {
        alert("동의사항에 체크해주세요");
    } else if(paymentClick == 0) {
        alert("결제하기 버튼을 눌러주세요");
    } else {
        var payment = document.querySelector("#payment");
        
        const obj = {
            "daily" : document.querySelector("#daily").value,
            "cloth" : document.querySelector("#cloth").value,
            "dish" : document.querySelector("#dish").value,
            "electronic" : document.querySelector("#electronic").value,
            "toy" : document.querySelector("#toy").value,
            "name" : document.querySelector("#name").value,
            "phone" : document.querySelector("#phone").value,
            "phone2" : document.querySelector("#phone2").value,
            "address" : document.querySelector("#postcode").value + " " + document.querySelector("#mainAddress").value + " " + document.querySelector("#detailAddress").value,
            "date" : document.querySelector("#date").value,
            "memo" : document.querySelector("#memo").value,
            "total" : document.querySelector("#total").innerText,
            "payment" :  payment.options[payment.selectedIndex].value,
            "dailyBox" : document.querySelector("#dailyBox").value,
            "clothBox" : document.querySelector("#clothBox").value,
            "dishBox" : document.querySelector("#dishBox").value,
            "electronicBox" : document.querySelector("#electronicBox").value,
            "toyBox" : document.querySelector("#toyBox").value
        }
    
        console.log(obj);
        
        fetch("/donation/complete", {
            method: "PUT",
            headers : {"Content-Type" : "application/json"},
            body : JSON.stringify(obj)
        })
        .then(resp => resp.text())
        .then(temp => {
            if(temp > 0) {
                alert("기부 신청이 완료되었습니다");
                location.href = "/donation/completePage";
            } else {
                alert("기부 신청이 완료되지 않았습니다");
                e.preventDefault();
            }
        });

    }


});






/* 카카오 인증하기 버튼 눌렀을 때 */

const kakao = document.querySelector("#kakao");
let kakaoClick = 0;

kakao.addEventListener("click", () => {

    if(document.querySelector("#parentsName").value.trim().length == 0 || document.querySelector("#parentsMiddleNo").value.trim().length == 0 || document.querySelector("#parentsLastNo").value.trim().length == 0){
        alert("입력을 모두 완료해주세요");
    } else if(document.querySelector("#parentsAgree").checked == false) {
        alert("동의사항에 체크해주세요");
    } else {
        kakao.style.backgroundColor = "#dbd02e";
        setTimeout(function () {
              alert("인증되었습니다");
            }, 3000);
        kakaoClick += 1;
        setTimeout(function () {
            kakao.style.backgroundColor = "#F7E600";
            }, 3000);

    }


});




/* 결제하기 버튼 눌렀을 때 */

const paymentButton = document.querySelector("#paymentButton");
let paymentClick = 0;

paymentButton.addEventListener("click", () => {
    if(document.querySelector("#paymentAgree").checked == false) {
        alert("동의사항에 체크해주세요");
    } else {
        setTimeout(function () {
              alert("결제가 완료되었습니다");
            }, 1500);
        paymentClick += 1;
    }
});