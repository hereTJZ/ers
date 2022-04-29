//显示通知弹窗
function showNotice(className) {
    document.getElementsByClassName(className)[0].style.display = "block";
}

// 在用户点击按钮，关闭弹窗
var noticeModal = document.getElementsByClassName("notice-modal")[0];
var spanIndex = document.querySelector(".close-notice-modal");
spanIndex.onclick = function () {
    noticeModal.style.display = "none";
};

// 在用户点击其他地方时，关闭弹窗
window.onclick = function (event) {
    if (event.target == noticeModal) {
        noticeModal.style.display = "none";
    }
};
