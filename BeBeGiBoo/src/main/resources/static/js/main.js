

document.addEventListener("DOMContentLoaded", function () {
    const options = {
      root: null,
      rootMargin: "0px",
      threshold: 0.4
    };
  
    // IMAGE ANIMATION
  
    let revealCallback = (entries) => {
      entries.forEach((entry) => {
        let container = entry.target;
  
        if (entry.isIntersecting) {
          container.classList.add("animating");
          return;
        }
  
        if (entry.boundingClientRect.top > 0) {
          container.classList.remove("animating");
        }
      });
    };
  
    let revealObserver = new IntersectionObserver(revealCallback, options);
  
    document.querySelectorAll(".reveal").forEach((reveal) => {
      revealObserver.observe(reveal);
    });
  
    // TEXT ANIMATION
  
    let fadeupCallback = (entries) => {
      entries.forEach((entry) => {
        let container = entry.target;
        container.classList.add("not-fading-up");
  
        if (entry.isIntersecting) {
          container.classList.add("fading-up");
          return;
        }
  
        if (entry.boundingClientRect.top > 0) {
          container.classList.remove("fading-up");
        }
      });
    };
  
    let fadeupObserver = new IntersectionObserver(fadeupCallback, options);
  
    document.querySelectorAll(".fadeup").forEach((fadeup) => {
      fadeupObserver.observe(fadeup);
    });
  });
  




document.querySelector("#top-button").addEventListener("click", () => {

    window.scrollTo({top:0, behavior: 'smooth'});
});





const showBox = document.querySelectorAll(".showBox");
const screenH = window.innerHeight/3*2;
const retVal = ele => ele.getBoundingClientRect().top;

const showTit = x => {
    let xval = retVal(x);
    if (xval < screenH && xval > 0) {
        x.classList.add("on");
    }
};

window.addEventListener("scroll", () => {
    for (let x of showBox) showTit(x);
});

/* FAQ 게시판 이동 */
document.getElementById('faqBoardBtn').addEventListener('click', ()=> {
  location.href = '/faqBoard/faqBoard';
}); 

/* 후기 인증 게시판 이동 */
document.getElementById('reviewBtn').addEventListener('click', ()=> {
  location.href = '/review';
});





//*******팝업 쿠키 설정********* */

const popupList= document.querySelectorAll(".popup");



function closePopup(num) {
  const cookieCheckBoxList = document.querySelectorAll(".cookieCheckBox");
  if(cookieCheckBoxList[num].checked){

    checkPopup(num);
  }else{//그냥 X 누른 경우

    popupList[num].style.display = "none";
  }
}


//체크박스 클릭하고 x누른 경우
function checkPopup(num) { 
  var cookieCheck = getCookie(num);



  if (cookieCheck == null){
    //쿠키 설정하기
    setCookie(num);
    popupList[num].style.display = "none";
  }else{
    return;
  }

}

function getCookie(num) {
  const name = "modalClose"+num;
  const value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');

  return value?value[2]:null;
}
//하루동안 안보이기 체크하는 경우
//쿠키 설정하기
function setCookie(num) {
  let date = new Date(Date.now() + 86400e3);
  date = date.toUTCString();
  let name = "modalClose"+num;
  document.cookie = `${name}=T; expires=${date}`;

  console.log(document.cookie);
}


function openPopup(){
  //쿠키가 있으면 팝업이 안열리고
  
  //팝업 개수 만큼 돌면서 확인하기
  for(let i =0;i<popupList.length;i++){
	
    if(getCookie(i)==null){
      popupList[i].style.display = "block";
    }else{//쿠키가 없으면 팝업이 열리도록
        popupList[i].style.display = "none";
    }
  
  }


}


document.addEventListener("DOMContentLoaded", () => {
  openPopup();
})



const donateThings = document.querySelector("#donateThingss");
let i = 1;


/* 기부물품 출력칸 */
if(donateThings != null) {
  function donateThingsFuntion() {
    donateThings.innerHTML = "";
  
      fetch("/acceptor/selectproductList")
      .then(resp => resp.json())
      .then(productList => {
  
        console.log(productList);
  
          //const productList = JSON.parse(result);
          
  
          if(productList == null) {
              productList.innerText = "기부물품이 존재하지 않습니다.";
          } else {
              productList.forEach( (product) => {
  
                  
                  let arr = [ product.productName];
  
  
                      const productInfo = document.createElement("div");
                      productInfo.classList.add("productInfoo");
  
                      for(let key of arr){
                      const div = document.createElement("div");
                      div.style.textAlign = "center";
                      div.style.fontSize = "15px";
                      div.style.fontWeight = "bold";
                      div.innerText = key;
                      productInfo.append(div);
                      donateThings.append(productInfo);
                  };
                });
              };
        });
  };
  
  donateThingsFuntion();
  
}


const boardTbody = document.querySelector("#boardTbody");

function selectBoard() {
  boardTbody.innerHTML = "";

  fetch("/review/boardList")
  .then(resp => resp.json())
  .then(boardList => {


  if(boardList == null) {
    boardList.innerText = "게시글이 존재하지 않습니다.";
  } else {
    boardList.forEach( (board) => {


          let arr = [ board.boardNo,
              board.boardTitle,
              board.boardWriteDate,
              board.memberId,
              board.readCount]

              const tr = document.createElement("tr");

              for(let key of arr){
                const td = document.createElement("td");
                td.innerText = key;
                tr.append(td);
              }
              boardTbody.append(tr);
      });
    }


  });
}

selectBoard();