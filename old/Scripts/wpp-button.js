function wppShow() {
    var date = new Date();
    var hour = date.getHours();
    var minute = date.getMinutes();
    document.getElementById("wpp-chat-time").innerText = hour + ":" + minute;
    $(".wpp-area").css("display", "flex");
    $(".wpp-area").animate({
        top: '-330px',
        opacity: '100%'
    });
};

function wppHide() {
    $(".wpp-area").animate({
        top: "-300px",
        opacity: "0%",
    });
    $(".wpp-area").hide(200);
}
