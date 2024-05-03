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

window.addEventListener("scroll", () => {
    for (let x of showBox) showTit(x);
});






