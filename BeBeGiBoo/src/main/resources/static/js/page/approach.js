const button1 = document.querySelector("#button1");
const button2 = document.querySelector("#button2");
const button3 = document.querySelector("#button3");
const button4 = document.querySelector("#button4");


button1.addEventListener("click", () => {
    window.scrollTo({top:0, behavior: 'smooth'});
});

button2.addEventListener("click", () => {
    window.scrollTo({top:500, behavior: 'smooth'});
});

button3.addEventListener("click", () => {
    window.scrollTo({top:1300, behavior: 'smooth'});
});

button4.addEventListener("click", () => {
    window.scrollTo({top:3000, behavior: 'smooth'});
});

button5.addEventListener("click", () => {
    window.scrollTo({top:3800, behavior: 'smooth'});
});