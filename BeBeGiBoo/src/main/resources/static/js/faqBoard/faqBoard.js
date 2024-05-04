function toggleAnswer(element) {
    var answerDiv = element.previousElementSibling;  // 'answer' div 참조
    if (answerDiv.style.display === 'none') {
        answerDiv.style.display = 'block';  // 답변 보이기
    } else {
        answerDiv.style.display = 'none';  // 답변 숨기기
    }
}

