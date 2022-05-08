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

// 退出登录
$(document).ready(function () {
    $("#logout").click(function () {
        var isLogout = window.confirm("确定要退出登录吗？");
        if (isLogout) {
            $.ajax({
                //几个参数需要注意一下
                type: "GET",   //方法类型
                dataType: "json",   //预期服务器返回的数据类型
                url: "/executeLogout",    //执行登录的url请求
                success: function (result) {    //result是服务器返回的封装数据
                    console.log(result);//打印服务端返回的数据(调试用)
                    if (result.code === 200) {
                        alert(result.msg + "\n" + result.data);
                        // 登录请求验证成功后跳转首页面
                        window.location.href = "/login"
                    }
                }
            });
        }else {
            console.log("取消退出登录");//打印服务端返回的数据(调试用)
        }
        // $("#div1").load("demo_test.txt", function (responseTxt, statusTxt, xhr) {
        //     if (statusTxt == "success")
        //         alert("外部内容加载成功!");
        //     if (statusTxt == "error")
        //         alert("Error: " + xhr.status + ": " + xhr.statusText);
        // });
    });
});