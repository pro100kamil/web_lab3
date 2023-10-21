function handleClick(event) {
    if (event.status !== "success") return;  //чтобы функция не вызывалась три раза
    let p_input_x = document.getElementsByClassName("p_input_x")[0];
    for (let btn of p_input_x.children) {
        btn.classList.remove("active");
    }
    event.source.classList.add("active");
}
