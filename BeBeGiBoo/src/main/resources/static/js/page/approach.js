const container1 = document.querySelector("#container1");
const container2 = document.querySelector("#container2");
const container3 = document.querySelector("#container3");
const container4 = document.querySelector("#container4");


container1.addEventListener("click", () => {

    window.scrollTo({top:2300, behavior: 'smooth'});
});

container2.addEventListener("click", () => {

    window.scrollTo({top:2900, behavior: 'smooth'});
});

container3.addEventListener("click", () => {

    window.scrollTo({top:3600, behavior: 'smooth'});
});

container4.addEventListener("click", () => {

    window.scrollTo({top:4700, behavior: 'smooth'});
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