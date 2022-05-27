var date = new Date();
if (date.getDate() % 2 === 0) {
    $("#logo-img").prop("src", "img/logo.png");
} else {
    $("#logo-img").prop("src", "img/earth.png");
}

var slideIndex = 0;
showSlides();

//轮播图控制
function showSlides() {
    var i;
    var slides = document.getElementsByClassName("mySlides");
    var dots = document.getElementsByClassName("dot");
    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }
    slideIndex++;
    if (slideIndex > slides.length) {
        slideIndex = 1;
    }
    for (i = 0; i < dots.length; i++) {
        dots[i].className = dots[i].className.replace(" active", "");
    }
    slides[slideIndex - 1].style.display = "block";
    dots[slideIndex - 1].className += " active";
    setTimeout(showSlides, 2000); // 切换时间为 2 秒
}

//显示通知弹窗
function showNotice(noticeID) {
    // 清空默认内容
    $("#notice-title").text('');
    $("#notice-content").text('');
    $("#release-Time").text("发布于：");
    // 异步请求
    $.ajax({
        //几个参数需要注意一下
        type: "GET",   //方法类型
        dataType: "json",   //预期服务器返回的数据类型
        url: "/notice/" + noticeID,   //查找公告的url请求
        success: function (result) {    //result是服务器返回的封装数据
            console.log(result);//打印服务端返回的数据(调试用)
            document.getElementsByClassName("notice-modal")[0].style.display = "block";
            if (result.code === 200) {
                $("#notice-title").text(result.data["title"]);
                $("#notice-content").text(result.data["content"]);
                $("#release-Time").text("发布于：" + result.data["releaseTime"]);
                $("#notice-img").prop("src",result.data["imageAddress"]);
            }
        },
        error: function () {
            alert("查看公告失败，请重新尝试！");
        }
    });
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


// 退出登录
// $(document).ready(function () {
//     $("#logout").click(function () {
//         var isLogout = window.confirm("确定要退出登录吗？");
//         if (isLogout) {
//             $.ajax({
//                 //几个参数需要注意一下
//                 type: "GET",   //方法类型
//                 dataType: "json",   //预期服务器返回的数据类型
//                 url: "/executeLogout",    //执行登录的url请求
//                 success: function (result) {    //result是服务器返回的封装数据
//                     console.log(result);//打印服务端返回的数据(调试用)
//                     if (result.code === 200) {
//                         alert(result.msg + "\n" + result.data);
//                         // 登录请求验证成功后跳转首页面
//                         window.location.href = "/login"
//                     }
//                 },
//                 error: function () {
//                     alert("退出失败，请重试！")
//                 }
//             });
//         } else {
//             console.log("取消退出登录");//打印服务端返回的数据(调试用)
//         }
//         // $("#div1").load("demo_test.txt", function (responseTxt, statusTxt, xhr) {
//         //     if (statusTxt == "success")
//         //         alert("外部内容加载成功!");
//         //     if (statusTxt == "error")
//         //         alert("Error: " + xhr.status + ": " + xhr.statusText);
//         // });
//     });
// });