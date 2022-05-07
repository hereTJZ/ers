$(document).ready(function () {
    $("#logout").click(function () {
        $.ajax({
            //几个参数需要注意一下
            type: "GET",   //方法类型
            dataType: "json",   //预期服务器返回的数据类型
            url: "/executeLogout",    //执行登录的url请求
            data: sessionStorage,
            success: function (result) {    //result是服务器返回的封装数据
                console.log(result);//打印服务端返回的数据(调试用)
                if (result.code === 200) {
                    alert(result.msg + "\n" + result.data["id"] + "\n" + result.data["realName"]);
                    // 登录请求验证成功后跳转首页面
                    window.location.href="/home"
                }else if (result.code === 500){
                    // 登录请求验证失败后提示信息
                    alert(result.msg + "\n" + result.data);
                }
            },
            // 登录请求失败
            error: function () {
                alert("登录请求异常！");
            }
        });
        // $("#div1").load("demo_test.txt", function (responseTxt, statusTxt, xhr) {
        //     if (statusTxt == "success")
        //         alert("外部内容加载成功!");
        //     if (statusTxt == "error")
        //         alert("Error: " + xhr.status + ": " + xhr.statusText);
        // });
    });
});