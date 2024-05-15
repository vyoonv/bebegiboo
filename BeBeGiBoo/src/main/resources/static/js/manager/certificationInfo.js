/* 모달 */

const modal = document.getElementById('modal');

document.addEventListener('DOMContentLoaded', function() {
    var selectButtons = document.querySelectorAll('.select');

    if(selectButtons.length > 0){
        selectButtons.forEach(function(button){
            button.addEventListener('click', function(){

            var memberId = button.getAttribute('data-memberName');
            var certificationId = button.getAttribute('data-certificationId');
            var memberBirth = button.getAttribute('data-memberBirth');
            var phone = button.getAttribute('data-phone');
            var address = button.getAttribute('data-address');
            var memberNo = button.getAttribute('data-memberNo');
            console.log('Member No:', memberNo);

            var memberNoInput = document.getElementById('memberNo');
            memberNoInput.value = memberNo;

            var addressParts = address.split('^^^');
            
            // 모달에 정보 표시하기
            modal.querySelector('#name').value = memberId;
            modal.querySelector('#certificationId').value = certificationId;
            modal.querySelector('#birth').value = memberBirth;
            modal.querySelector('#phone').value = phone;
            modal.querySelector('#postcode').value = addressParts[0];
            modal.querySelector('#mainAddress').value = addressParts[1];
            modal.querySelector('#detailAddress').value = addressParts[2];
            
            modal.classList.remove('hidden');
                 
            })
        })
    }

    var closeButton = document.querySelector('.popup-close');

    closeButton.addEventListener('click', function() {
        modal.classList.add('hidden');
    });
});


/* 수정하기 */
document.addEventListener('DOMContentLoaded', function() {
    var updateButton = document.getElementById('updateButton');

    updateButton.addEventListener('click', function() {
        var nameInput = document.getElementById('name');
        var certificationIdInput = document.getElementById('certificationId');
        var birthInput = document.getElementById('birth');
        var phoneInput = document.getElementById('phone');
        var postcodeInput = document.getElementById('postcode');
        var mainAddressInput = document.getElementById('mainAddress');
        var detailAddressInput = document.getElementById('detailAddress');
        var memberNoInput = document.getElementById('memberNo');

        var memberName = nameInput.value;
        var certificationId = certificationIdInput.value;
        var memberBirth = birthInput.value;
        var phone = phoneInput.value;
        var postcode = postcodeInput.value;
        var mainAddress = mainAddressInput.value;
        var detailAddress = detailAddressInput.value;
        var memberNo = memberNoInput.value;

        var address = `${postcode}^^^${mainAddress}^^^${detailAddress}`;

        var data = {
            memberName: memberName,
            certificationId: certificationId,
            memberBirth: memberBirth,
            phone: phone,
            address: address,
            memberNo: memberNo
        };

        fetch("/manager/certificationUpdate", {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        })
        .then(resp => resp.text())
        .then(result => {
            if(result > 0) {
                alert("수정 성공!");
                modal.classList.add('hidden');
                location.href="/manager/certificationInfo";
            } else {
                alert("수정 실패");
            }
        })
        .catch(error => {
            console.error('에러메세지 : ', error);
        });
        
  
    });
});

