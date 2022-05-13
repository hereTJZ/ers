(function () {
    $.ajax({
        type: "GET",
        dataType: "json",   //预期服务器返回的数据类型
        url: "/checkLogin",
        success: function (result) {
            if (result.code === 200) {
                console.log("登陆成功\n" + result.data)
            } else if (result.code === 500) {
                alert("当前用户未登录！");
                // 跳转登录页面
                window.location.href = "/login"
            }
        },
        error: function () {
            alert("登录核验异常！");
        }
    });
})()

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
                },
                error: function () {
                    alert("退出失败，请重试！")
                }
            });
        } else {
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