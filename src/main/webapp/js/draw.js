let centerX = 225;  //в этом мест x=0 с точки зрения математических координат
let centerY = 225;  //в этом мест y=0 с точки зрения математических координат
let R = 200;
let DEFAULT_R_ = 2;
let canvas = document.getElementById("canvas");
let context = canvas.getContext("2d");
context.font = "12px Verdana";

function drawPoint(x, y, delta = 2) {
    context.rect(x - delta / 2, y - delta / 2, delta, delta);
}

function get_r_() {
    let input_r = document.getElementsByClassName("input_r")[0];
    if (isNaN(+input_r.value) || +input_r.value < 2 || +input_r.value > 5) {
        return DEFAULT_R_;
    }
    return +input_r.value;
}

function drawPointForJSF(mathX, mathY, color = "red", delta = 4) {
    let r_ = get_r_();

    let x = mathX * R / r_ + centerX;
    let y = centerY - mathY * R / r_;

    context.beginPath();
    drawPoint(x, y, delta);
    context.strokeStyle = color;
    context.fillStyle = color;
    context.fill();
    context.stroke();
}

function drawTextWithDeltaX(text, x, y, delta = 4) {
    //смещение по оси х для надписей на оси y
    context.fillText(text, x + delta, y);
    drawPoint(x, y);
}

function drawTextWithDeltaY(text, x, y, delta = 4) {
    //смещение по оси y для надписей на оси x
    context.fillText(text, x, y - delta);
    drawPoint(x, y);
}

function drawArrow(x, y, arrowDelta, direction) {
    context.moveTo(x, y);
    if (direction === "right")
        context.lineTo(x - arrowDelta, y - arrowDelta);
    else
        context.lineTo(x - arrowDelta, y + arrowDelta);

    context.moveTo(x, y);
    if (direction === "right")
        context.lineTo(x - arrowDelta, y + arrowDelta);
    else
        context.lineTo(x + arrowDelta, y + arrowDelta);
}

function drawAxes(radius, delta) {
    let arrowDelta = 4;
    context.beginPath();

    drawPoint(centerX, centerY, 4);

    context.moveTo(centerX - radius - delta, centerY);
    context.lineTo(centerX + radius + delta, centerY); //OX

    drawArrow(centerX + radius + delta, centerY, arrowDelta, "right");
    context.fillText("X", centerX + radius + delta, centerY);

    context.moveTo(centerX, centerY + radius + delta);
    context.lineTo(centerX, centerY - radius - delta); //OY
    drawArrow(centerX, centerY - radius - delta, arrowDelta, "up");
    context.fillText("Y", centerX, centerY - radius - delta);

    //OX
    drawTextWithDeltaY("-R", centerX - radius, centerY);
    drawTextWithDeltaY("-R/2", centerX - radius / 2, centerY);
    drawTextWithDeltaY("R/2", centerX + radius / 2, centerY);
    drawTextWithDeltaY("R", centerX + radius, centerY);

    //OY
    drawTextWithDeltaX("-R", centerX, centerY + radius);
    drawTextWithDeltaX("-R/2", centerX, centerY + radius / 2);
    drawTextWithDeltaX("R/2", centerX, centerY - radius / 2);
    drawTextWithDeltaX("R", centerX, centerY - radius);

    context.stroke();
}

function drawSecondQuarter(radius) {
    //четверть окружности по радиусу
    context.moveTo(centerX, centerY);  //(0;0)

    context.arc(centerX, centerY, radius,
        Math.PI, Math.PI * 3 / 2,
        false);
    context.fill();
}

function drawThirdQuarter(height, width) {
    //прямоугольник по сторонам
    //сторона отрицательная => направление влево или вниз по математическим осям
    //сторона положительная => направление вправо или вверх по математическим осям
    context.moveTo(centerX, centerY);  //(0;0)

    context.lineTo(centerX + width, centerY); //(width;0)
    context.lineTo(centerX + width, centerY - height); //(width;height)
    context.lineTo(centerX, centerY - height); //(0;height)
    context.lineTo(centerX, centerY); //(0;0)
}

function drawFourthQuarter(height, width) {
    //прямоугольный треугольник по катетам
    //катеты могут быть отрицательными
    context.moveTo(centerX, centerY);  //(0;0)

    context.lineTo(centerX + width, centerY); //(width;0)
    context.lineTo(centerX, centerY - height);  //(0;height)
    context.lineTo(centerX, centerY);  //(0;0)
}

function drawPlot() {
    context.beginPath();

    drawSecondQuarter(R / 2);
    drawThirdQuarter(-R, -R);
    drawFourthQuarter(-R / 2, R / 2);

    context.closePath();
    context.strokeStyle = "blue";
    context.fillStyle = "blue";
    context.fill();
    context.stroke();
    context.strokeStyle = "black";
    context.fillStyle = "black";
    drawAxes(R, 14);
}

function updateCanvas(event) {
    context.clearRect(0, 0, canvas.width, canvas.height);  //очистка канваса
    drawPlot();

    for (let tr of document.querySelectorAll("#dataTable tr")) {
        let children = tr.children;
        // tagName возвращает название тега в верхнем регистре
        if (children[0].tagName.toLowerCase() === "td" && children[0].innerText !== "" && children[0].innerText !== "") {
            let x = children[0].innerText;
            let y = children[1].innerText;
            drawPointForJSF(x, y,
                (children[3].innerText === "true" ? "white" : "red")
            );
        }
    }
}

updateCanvas();

canvas.onclick = (event) => {
    let x = event.pageX - event.target.offsetLeft;
    let y = event.pageY - event.target.offsetTop;

    let mathX = x - centerX;
    let mathY = centerY - y;

    let r_ = get_r_();

    let x_ = mathX / R * r_;
    let y_ = mathY / R * r_;

    addAttempt(
        [
            {name: "x", value: x_.toString()},
            {name: "y", value: y_.toString()},
            {name: "r", value: r_.toString()}
        ]
    )

}
