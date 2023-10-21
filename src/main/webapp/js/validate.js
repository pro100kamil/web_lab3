function validateY() {
    return getErrorY() === "";
}

function validateR() {
    return getErrorR() === "";
}

function handlerY() {
    document.getElementsByClassName("error-message")[0].textContent = getErrorY();
}

function handlerR() {
    document.getElementsByClassName("error-message")[0].textContent = getErrorR();
}

function getErrorY() {
    //возвращает строковую ошибку или пустую строку при отсутствии ошибок
    let input_y = document.getElementsByClassName("input_y")[0];
    let y = input_y.value;

    if (y === "") {
        return "Y не должен быть пустым";
    }
    if (isNaN(y)) {
        return "Y должен быть числом";
    }
    if (String(y).length > 7) {
        return "Y должен быть числом, состоящим из менее 8 символов (<= 7)";
    }
    if (-5 > y || y > 5) {
        return "Y на промежутке [-5;5]";
    }
    return "";
}


function getErrorR() {
    //возвращает строковую ошибку или пустую строку при отсутствии ошибок
    let input_r = document.getElementsByClassName("input_r")[0];
    let r = input_r.value;

    if (r === "") {
        return "R не должен быть пустым";
    }
    if (isNaN(r)) {
        return "R должен быть числом";
    }
    if (String(r).length > 7) {
        return "R должен быть числом, состоящим из менее 8 символов (<= 7)";
    }
    if (2 > r || r > 5) {
        return "R на промежутке [2;5]";
    }
    return "";
}

function handleSubmit(event) {
    let status = event.status; // Can be 'begin', 'complete' and 'success'.
    console.log("handle submit, status: ", status);
    if (status === 'success') {  //чтобы при отправке формы отрисовка была только после ajax
        console.log("status is success")
        updateCanvas();
    }
    if (status === 'begin') {
        handlerY();
        if (!validateY()) return true;
        handlerR();
        return validateR();
    }
}


function handleClear(event) {
    let status = event.status; // Can be 'begin', 'complete' and 'success'.
    if (status === 'success') {  //чтобы при отправке формы отрисовка была только после ajax
        console.log("status is success")
        updateCanvas();
    }
}

console.log("validate");
