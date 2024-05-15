const horizontal = document.querySelector("#horizontal");
let i = 1;

function selectProduct() {
    horizontal.innerHTML = "";

    fetch("/acceptor/selectproductList")
    .then(resp => resp.text())
    .then(result => {

        const productList = JSON.parse(result);
        

        if(productList == null) {
            productList.innerText = "기부물품이 존재하지 않습니다.";
        } else {
            productList.forEach( (product) => {

                
                let arr = [ product.recordDate,
                    product.productName];

                    const infoBox = document.createElement("div");
                    infoBox.classList.add("img-wrapper");
                    infoBox.classList.add("slower");
                    infoBox.classList.add("vertical");


                    const productInfo = document.createElement("div");
                    productInfo.classList.add("productInfo");

                    const donateDate = document.createElement("div");
                    donateDate.innerText = "기부날짜";
                    donateDate.classList.add("donateDate");
                    productInfo.append(donateDate);

                    for(let key of arr){
                    const div = document.createElement("div");
                    div.style.textAlign = "center";
                    div.style.fontSize = "20px";
                    div.style.fontWeight = "bold";
                    div.style.marginBottom = "150px";
                    div.innerText = key;
                    productInfo.append(div);
                    productInfo.style.backgroundImage = `url("/images/layette-${i}.jpg")`;
                    productInfo.style.backgroundSize = "cover";
                    if(i == 10) {
                        i = 1;
                    }else {
                        i ++;
                    }
                }

                

                const button = document.createElement("button");
                button.classList.add("acceptorButton");
                button.innerText = "신청하기";
                button.style.backgroundColor = "rgba(0,0,0,0)";

                productInfo.append(button);

                infoBox.append(productInfo);
                horizontal.append(infoBox);

                button.addEventListener("click", () => {
                    fetch("/acceptor/insertWish", {
                        method : "PUT",
                        headers : {"Content-Type" : "application/json"},
                        body : JSON.stringify(product.recordNo)
                    })
                    .then(resp => resp.text())
                    .then(result => {

                        const wish = JSON.parse(result);

                        if(wish > 0) {
                            alert("신청이 완료되었습니다.");

                            horizontal.innerHTML = "";

                            fetch("/acceptor/selectproductList")
                            .then(resp => resp.text())
                            .then(result => {
                        
                                const productList = JSON.parse(result);
                                
                        
                                if(productList == null) {
                                    productList.innerText = "기부물품이 존재하지 않습니다.";
                                } else {
                                    productList.forEach( (product) => {
                        
                                        
                                        let arr = [ product.recordDate,
                                            product.productName];
                        
                                            const infoBox = document.createElement("div");
                                            infoBox.classList.add("img-wrapper");
                                            infoBox.classList.add("slower");
                                            infoBox.classList.add("vertical");
                        
                        
                                            const productInfo = document.createElement("div");
                                            productInfo.classList.add("productInfo");
                        
                                            const donateDate = document.createElement("div");
                                            donateDate.innerText = "기부날짜";
                                            donateDate.classList.add("donateDate");
                                            productInfo.append(donateDate);
                                            for(let key of arr){
                                            const div = document.createElement("div");
                                            div.style.textAlign = "center";
                                            div.style.fontSize = "20px";
                                            div.style.fontWeight = "bold";
                                            div.style.marginBottom = "150px";
                                            div.innerText = key;
                                            productInfo.append(div);
                                            productInfo.style.backgroundImage = `url("/images/layette-${i}.jpg")`;
                                            productInfo.style.backgroundSize = "cover";
                                        }
                        
                                        
                        
                                        const button = document.createElement("button");
                                        button.classList.add("acceptorButton");
                                        button.innerText = "신청하기";
                        
                                        productInfo.append(button);
                        
                                        infoBox.append(productInfo);
                                        horizontal.append(infoBox);
                                    
                                    });
                                }
                            });
                        } else {
                            alert("이미 신청한 기부물품입니다");
                        }
                    });
                });
            });
        }
    });
}

selectProduct();