const tbody = document.getElementById("membershipBoard");
const popup = document.querySelector(".popup-layer");

function selectMember() {
    tbody.innerHTML = "";
    
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
                            member.memberName,
                            member.enrollDate];

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
                button.innerText = "수정";
                button.classList.add("managerButton");
                tr.append(buttonTd);
                buttonTd.append(button);
                tbody.append(tr);

                button.addEventListener("click", () => {

                    popup.style.display = 'flex';
                    var memberNo = document.querySelector("#memberNo");
                    var name = document.querySelector("#name");
                    var email = document.querySelector("#email");
                    var phone = document.querySelector("#phone");
                    var address = document.querySelector("#address");
                    var del = document.querySelector("#del");
                    var authority = document.querySelector("#authority");

                    popup.style.display = 'flex';

                    memberNo.value = member.memberNo;
                    name.value = member.memberName;
                    email.value = member.email;
                    phone.value = member.phone;
                    address.value = member.address;
                    del.value = member.memberDelFl;
                    authority.value = member.authority;

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


const updateButton = document.querySelector("#updateButton");



updateButton.addEventListener("click", () => {
    var memberNo = document.querySelector("#memberNo");
    var name = document.querySelector("#name");
    var email = document.querySelector("#email");
    var phone = document.querySelector("#phone");
    var address = document.querySelector("#address");
    var del = document.querySelector("#del");
    var authority = document.querySelector("#authority");


    if(name.value.trim().length == 0 ||
    email.value.trim().length == 0 ||
    phone.value.trim().length == 0 ||
    address.value.trim().length == 0 ||
    del.value.trim().length == 0 ||
    authority.value.trim().length == 0) {
        alert("모든 칸을 작성해주세요");
    } else {
        obj = {
            "memberNo" : memberNo.value,
            "memberName" : name.value,
            "email" : email.value,
            "phone" : phone.value,
            "address" : address.value,
            "memberDelFl" : del.value,
            "authority" : authority.value
    
        }
    
        fetch("/manager/update", {
            method : "PUT",
            headers : {"Content-Type" : "application/json"},
            body : JSON.stringify(obj)
        })
        .then(resp => resp.text())
        .then(result => {
    
            if(result > 0) {
                alert("수정 성공!");
                popup.style.display = 'none';
                selectMember();
            } else {
                alert("수정 실패");
            }
    
        })

    }

});

