(function () {
    $.ajax({
        type: "GET",
        dataType: "json",   //预期服务器返回的数据类型
        url: "/checkLogin",
        success: function (result) {
            if (result.code === 200) {
                console.log("登陆成功\n" + result.data)
            } else if (result.code === 500) {
                alert("当前用户未登录");
                // 跳转登录页面
                window.location.href = "/login"
            }
        },
        error: function () {
            alert("登录核验异常！");
        }
    });
})()