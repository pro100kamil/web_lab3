function handleClick(event) {
    if (event.type !== "click") return;
    let p_input_x = document.getElementsByClassName("p_input_x")[0];
    for (let btn of p_input_x.children) {
        btn.classList.remove("active");
    }
    event.target.classList.add("active");
}


