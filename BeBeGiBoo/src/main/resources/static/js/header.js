const hamburger = document.querySelector("#hamburger");
const navContainer = document.querySelector("#nav-container");
const navContainerNull = document.querySelector("#nav-container-null");


hamburger.addEventListener("click", () => {

    if(navContainer.style.visibility != "visible") {
        navContainer.style.visibility = "visible";
        navContainer.classList.add("act-down");
    } else {
        navContainer.style.visibility = "hidden";
        document.querySelector("#hover1").style.visibility = 'hidden';
        document.querySelector("#hover2").style.visibility = 'hidden';
        navContainer.classList.remove("act-down");
    }

});
hamburger.addEventListener("click", () => {

    if(navContainerNull.style.visibility != "visible") {
        navContainerNull.style.visibility = "visible";
        navContainerNull.classList.add("act-down");
    } else {
        navContainerNull.style.visibility = "hidden";
        navContainerNull.classList.remove("act-down");
    }

});



document.querySelector("#myPage").addEventListener("mouseover", () => {
    document.querySelector("#hover1").style.visibility = 'visible';
    document.querySelector("#hover2").style.visibility = 'visible';
});

document.querySelector(".noHover").addEventListener("mouseover", () => {
    document.querySelector("#hover1").style.visibility = 'hidden';
    document.querySelector("#hover2").style.visibility = 'hidden';
});

