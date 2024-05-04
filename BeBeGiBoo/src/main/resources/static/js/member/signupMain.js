/* *************MAIN************** */

/* 체크박스 하나만 선택하기 */

const checkboxesMain = document.querySelectorAll('.selectOne');

checkboxesMain.forEach(checkbox => {
    checkbox.addEventListener('change', function() {
        checkboxesMain.forEach(cb => {
            if (cb !== this) {
                cb.checked = false;
            }
        });
    });
}); 


/* 제출하기 버튼 클릭시 이동 */ 
const submitBtn = document.getElementById('submitBtn');
var donatorCheckbox = document.getElementById('donator');
var acceptorCheckbox = document.getElementById('acceptor');


/* value값 확인 용 */

function handleCheckboxChange() {
    
    console.log('Donator Checkbox: ' + (donatorCheckbox.checked ? donatorCheckbox.value : 'not checked'));
    console.log('Acceptor Checkbox: ' + (acceptorCheckbox.checked ? acceptorCheckbox.value : 'not checked'));
}

donatorCheckbox.addEventListener('change', handleCheckboxChange);
acceptorCheckbox.addEventListener('change', handleCheckboxChange);


/* 제출하기 버튼 클릭시 */
submitBtn.addEventListener('click', e => {

    e.preventDefault(); 

    if( !(donatorCheckbox.checked || acceptorCheckbox.checked)){
  
        alert('기부자 또는 피기부자 중 최소 한 가지를 선택해야 합니다.');
        return; 
    } 
    // 선택된 체크박스 값 확인
    var donatorChecked = donatorCheckbox.checked;
    var acceptorChecked = acceptorCheckbox.checked;

    // 폼 엘리먼트 생성
    var form = document.createElement('form');
    form.setAttribute('method', 'post');
    form.setAttribute('action', '/member/signup/signupTerm');

    // authority 값을 전달하는 hidden input 추가
    if (donatorChecked) {
        var donatorInput = document.createElement('input');
        donatorInput.setAttribute('type', 'hidden');
        donatorInput.setAttribute('name', 'authority');
        donatorInput.setAttribute('value', '1');
        form.appendChild(donatorInput);
    } else if (acceptorChecked) {
        var acceptorInput = document.createElement('input');
        acceptorInput.setAttribute('type', 'hidden');
        acceptorInput.setAttribute('name', 'authority');
        acceptorInput.setAttribute('value', '2');
        form.appendChild(acceptorInput);
    }

    // 폼을 문서에 추가하고 제출
    document.body.appendChild(form);
    form.submit();
});

