const tbody = document.getElementById("donationThingsBoard");
const donationThings = document.getElementById("donationThings");
const donationThingsBox = document.getElementById("donationThingsBox");
const popup = document.querySelector(".popup-layer");
const donatorName = document.querySelector("#donatorName");
const donateThings = document.querySelector("#donateThings");
const acceptorBox = document.querySelector("#acceptorBox");
const viewTitle = document.querySelector("#viewTitle");
const detailDiv = document.createElement("div");


function selectMember() {
    tbody.innerHTML = "";
    detailDiv.innerHTML = "";
    
    fetch("/manager/selectMemberList")
    .then(resp => resp.text())
    .then(result => {

        const memberList = JSON.parse(result);
        

        if(memberList == null) {
            memberList.innerText = "회원이 존재하지 않습니다.";
        } else {
            memberList.forEach( (member) => {
                let arr = [member.memberNo,
                            member.memberId,
                            ];

                const tr = document.createElement("tr");
                tr.classList.add("shadow");
                for(let key of arr){
                    const td = document.createElement("td");
                    td.innerText = key;
                    tr.append(td);
                    tr.classList.add("text");
                }
                const buttonTd = document.createElement("td");
                const button = document.createElement("button");
                button.innerText = "기부내역 보러가기>>";
                button.classList.add("managerButton");
                tr.append(buttonTd);
                buttonTd.append(button);
                tbody.append(tr);


                button.addEventListener("click", () => {
                    viewTitle.innerText = member.memberId;

                    donationThings.innerHTML = "";

                    tbody.style.transform = "translateX(-320px)";
                    donationThingsBox.style.transform = "translateX(-320px)";
                    donationThingsBox.style.visibility = 'visible';
                    donationThingsBox.style.position = "sticky";

                    fetch("/manager/selectDonationThings", {
                        method : "POST",
                        headers : {"Content-Type" : "application/json"},
                        body : JSON.stringify(member.memberNo)
                    })
                    .then(resp => resp.text())
                    .then(result => {
                        const donationThingsList = JSON.parse(result);


                        if(donationThingsList.length == 0) {
                            donationThings.innerHTML = "<p>기부한 물품이 존재하지 않습니다.</p>";
                        }

                        donationThingsList.forEach( (product) => {
                            if(product.acceptorNo == 0) {

                                product.acceptorName = "피기부자 없음";

                            }

                            let arr = [product.recordNo,
                                product.recordDate,
                                product.acceptorName];

                            console.log(product.recordNo, product.recordDate, product.acceptorName);

                            const div = document.createElement("div");
                            div.classList.add("duration");

                            const tr = document.createElement("tr");
                            tr.classList.add("shadow");
                            tr.style.border = "1px gray solid";
                            for(let key of arr){
                                const td = document.createElement("td");
                                td.innerText = key;
                                tr.append(td);
                                tr.classList.add("text");
                            }

                            donationThings.append(tr);

                            div.append(tr);
                            tr.style.cursor = "pointer";
                            donationThings.append(div);

                            tr.addEventListener("click", () => {
                                div.append(detailDiv);
                                detailDiv.innerHTML = "";

                                fetch("/manager/selectDonationDetailThings", {
                                    method : "POST",
                                    headers : {"Content-Type" : "application/json"},
                                    body : JSON.stringify(product.recordNo)
                                })
                                .then(resp => resp.text())
                                .then(result => {
                                    const donationDetailThingsList = JSON.parse(result);

                                    donationDetailThingsList.forEach( (detailProduct) => {
            
                                        let arr = [detailProduct.donatorName,
                                            detailProduct.phone,
                                            detailProduct.address,
                                            detailProduct.productName];
            
                                        console.log(arr);

                                        const extr = document.createElement("tr");
                                        extr.classList.add("text");
                                        extr.style.fontSize = "12px";
                                        extr.style.color = "rgb(0,149,250)";
                                        extr.style.marginTop = "10px";
                                        const nametd = document.createElement("td");
                                        const phonetd = document.createElement("td");
                                        const addresstd = document.createElement("td");
                                        const producttd = document.createElement("td");

                                        nametd.innerText = "배송자";
                                        phonetd.innerText = "전화번호";
                                        addresstd.innerText = "출발지";
                                        producttd.innerText = "기부물품";

                                        extr.append(nametd);
                                        extr.append(phonetd);
                                        extr.append(addresstd);
                                        extr.append(producttd);
                                        detailDiv.append(extr);
                                        detailDiv.style.border = "1px gray solid";

                                        const tr = document.createElement("tr");
                                        for(let key of arr){
                                            const td = document.createElement("td");
                                            td.innerText = key;
                                            tr.append(td);
                                            tr.classList.add("text");
                                            detailDiv.append(tr);
                                        }
                                        tr.style.marginBottom = "10px";                                        
                                        if(detailProduct.acceptorNo == 0) {
                                            const acceptorButton = document.createElement("button");
                                            acceptorButton.innerText = "피기부자 지정";
                                            acceptorButton.classList.add("acceptorButton");
                                            detailDiv.append(acceptorButton);


                                            acceptorButton.addEventListener("click", () => {
                                                acceptorBox.innerHTML = "";
                                                popup.style.display = 'flex';
                                                donatorName.innerText = detailProduct.donatorName;
                                                donateThings.innerText = detailProduct.productName;
    
                                                fetch("/manager/selectAcceptor", {
                                                    method : "POST",
                                                    headers : {"Content-Type" : "application/json"},
                                                    body : JSON.stringify(product.recordNo)
                                                })
                                                .then(resp => resp.text())
                                                .then(result => {
                                                    const acceptorList = JSON.parse(result);

                                                    if(acceptorList.length == 0) {
                                                        acceptorBox.style.textAlign = "center";
                                                        acceptorBox.style.fontWeight = "bold";
                                                        acceptorBox.innerText = "신청자 없음";
                                                    }
                            
                                                    console.log(acceptorList);
                
                                                    acceptorList.forEach( (acceptor) => {
                            
                                                        let arr = [acceptor.memberId,
                                                                acceptor.memberName,
                                                                acceptor.email,
                                                                acceptor.phone,
                                                                acceptor.address,
                                                        ];
                            
                                                        console.log(arr);
    
                                                        const tr = document.createElement("tr");
                                                        tr.style.margin = "10px";
                                                        for(let key of arr){
                                                            const td = document.createElement("td");
                                                            td.innerText = key;
                                                            tr.append(td);
                                                            tr.classList.add("text");
                                                        }
                                                        const donateButton = document.createElement("button");
                                                        donateButton.innerText = "기부";
                                                        donateButton.classList.add("donateButton");
                                                        tr.append(donateButton);
                                                        acceptorBox.append(tr);
    
                                                        donateButton.addEventListener("click", () => {
                                                            connectObj = {
                                                                "recordNo" : product.recordNo,
                                                                "acceptorNo" : acceptor.memberNo
                                                            }
        
                                                            fetch("/manager/connectDonate", {
                                                                method : "PUT",
                                                                headers : {"Content-Type" : "application/json"},
                                                                body : JSON.stringify(connectObj)
                                                            })
                                                            .then(resp => resp.text())
                                                            .then(result => {
                                                                
                                                                if(result > 0) {
                                                                    alert("기부연결 성공!");
                                                                    popup.style.display = 'none';
                                                                    


                                                                    /************************** */
                                                                    
                                                                    location.reload();
                                                                } else {
                                                                    alert("기부연결 실패");
                                                                }
                                                            
                                                            });
    
                                                        });
    
    
                                                    });
                                                });
                                            });
                                        }


                                    });
                                });
                            });

                        });
                    });

                });
            });
        }
    });
    
};


selectMember();


const popupClose = document.querySelector(".popup-close");

popupClose.addEventListener("click", () => {

    if(confirm("취소하시겠습니까?")) {
        popup.style.display = 'none';
    }
});


