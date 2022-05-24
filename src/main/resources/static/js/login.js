//  jQuery 函数位于 document ready 函数中，防止文档在完全加载（就绪）之前运行 jQuery 代码
$(document).ready(function () {
    $("#password")
        .focus(function () {
            // 密码框获得焦点，追加样式.password
            $("#owl").addClass("password");
        })
        .blur(function () {
            // 密码框失去焦点，移除样式.password
            $("#owl").removeClass("password");
        });
});

// 密码显示与隐藏
var password = document.getElementById('password');
var anniu = document.getElementById('conceal');
anniu.addEventListener('click', function () {
    if (password.type === 'password') {
        password.setAttribute('type', 'text');
        anniu.classList.add('xianshi');
        $("#owl").removeClass("password");
    } else {
        password.setAttribute('type', 'password');
        anniu.classList.remove('xianshi');
        $("#owl").addClass("password");
    }
})

// 纸飞机动画
let plane = document.getElementById("plane");
let deg = 0,
    ex = 0,
    ey = 0,
    vx = 0,
    vy = 0,
    count = 0;
window.addEventListener("mousemove", (e) => {
    ex = e.x - plane.offsetLeft - plane.clientWidth / 2;
    ey = e.y - plane.offsetTop - plane.clientHeight / 2;
    deg = (360 * Math.atan(ey / ex)) / (2 * Math.PI) + 45;
    if (ex < 0) {
        deg += 180;
    }
    count = 0;
});

function draw() {
    plane.style.transform = "rotate(" + deg + "deg)";
    if (count < 100) {
        vx += ex / 100;
        vy += ey / 100;
    }
    plane.style.left = vx + "px";
    plane.style.top = vy + "px";
    count++;
}

setInterval(draw, 1);


// 发送验证码
function send_SMS() {
    var phone = $("#account").val();
    var email = $("#email").val();
    $("#send_button").prop("disabled", true).text("发送中...")
    //禁用发送按钮函数
    function disable_button() {
        // 定义一个变量存储时间的数字
        var n = 60;
        // 让按钮被禁用
        // 替换按钮的文字内容
        $("#send_button")
            .prop("disabled", true)
            .text(n + "s 后重发")
            .css({
                color: "white",
                background: "linear-gradient(200deg, rgba(115, 175, 211, 0.6),rgba(150, 250, 196, 0.6))"
            });
        // 每隔 1s 更改倒计时内容
        // 通过定时器进行每隔 1s 减时间效果
        var timer = setInterval(() => {
            n--;
            // 文字内容发生变化
            // 定时器内部的this指向的默认为 window
            $("#send_button").text(n + "s 后重发")
            // 判断如果时间到了 0 ，就要停止定时器
            if (n <= 0) {
                clearInterval(timer)
                // 5s 结束后，需要让文字恢复 发送
                // 让按钮取消禁用
                $("#send_button")
                    .prop("disabled", false)
                    .text("重新发送")
                    .css({
                        color: "white",
                        background: "linear-gradient(200deg, #72afd3, #96fbc4)"
                    });
            }
        }, 1000)
    }
    //发送短信请求
    $.ajax({
        //几个参数需要注意一下
        type: "POST",   //方法类型
        dataType: "json",   //预期服务器返回的数据类型
        url: "/sendEmail",    //执行的url请求
        //提交至服务器的数据
        data: {
            "phone": phone,
            "email": email
        },
        success: function (result) {    //result是服务器返回的封装数据
            console.log(result);//打印服务端返回的数据(调试用)
            if (result.code === 200) {
                disable_button();
                alert("验证码已发送至邮箱，请注意查收😄");
            } else if (result.code === 400) {
                // 请求验证失败后提示信息
                $("#send_button").prop("disabled", false).text("发 送")
                alert(result.msg + "\n" + result.data);
            } else if (result.code === 500) {
                // 请求验证错误后提示信息
                $("#send_button").prop("disabled", false).text("发 送")
                alert(result.msg + "\n" + result.data);
            }
        },
        // 登录请求失败
        error: function () {
            $("#send_button").prop("disabled", false)
            alert("验证码发送异常！");
        }
    });
}

// 执行注册
$(document).ready(function () {
    $("#doRegister").click(function () {
        $("#doRegister").text("正在注册...");
        $.ajax({
            //几个参数需要注意一下
            type: "POST",   //方法类型
            dataType: "json",   //预期服务器返回的数据类型
            url: "/executeRegister",    //执行登录的url请求
            data: $("form").serialize(),    //序列化表单元素集为字符串以便提交
            success: function (result) {    //result是服务器返回的封装数据
                console.log(result);//打印服务端返回的数据(调试用)
                if (result.code === 200) {
                    alert(result.msg + "\n" + result.data);
                    // 注册请求成功后跳转登录页面
                    window.location.href = "/login"
                } else if (result.code === 400) {
                    // 输入错误
                    alert(result.msg + "\n" + result.data);
                } else if (result.code === 500) {
                    // 输入不合法
                    alert(result.msg + "\n" + result.data);
                }
            },
            // 登录请求失败
            error: function () {
                alert("注册请求异常！");
            }
        })
        $("#doRegister").text("注册");
        // $("#div1").load("demo_test.txt", function (responseTxt, statusTxt, xhr) {
        //     if (statusTxt == "success")
        //         alert("外部内容加载成功!");
        //     if (statusTxt == "error")
        //         alert("Error: " + xhr.status + ": " + xhr.statusText);
        // });
    });
});

// 执行登录
$(document).ready(function () {
    $("#login").click(function () {
        $("#login").text("正在登陆...");
        $.ajax({
            //几个参数需要注意一下
            type: "POST",   //方法类型
            dataType: "json",   //预期服务器返回的数据类型
            url: "/executeLogin",    //执行登录的url请求
            data: $("form").serialize(),    //序列化表单元素集为字符串以便提交
            success: function (result) {    //result是服务器返回的封装数据
                console.log(result);//打印服务端返回的数据(调试用)
                if (result.code === 200) {
                    alert(result.msg + "\nID:" +
                        result.data["id"] + "\n" +
                        ((result.data["role"] === 1) ? "管理员" :
                            (result.data["role"] === 2 ? "教师" :
                                (result.data["role"] === 3 ? "学生" : "社会人员"))) + "\n" +
                        result.data["realName"]);
                    // 登录请求验证成功后跳转首页面
                    window.location.href = "/home"
                } else if (result.code === 400) {
                    // 当前已登录，防止重登陆
                    $("#login").text("登陆");
                    alert(result.msg + "\n" + result.data);
                    window.location.href = "/home"
                } else if (result.code === 500) {
                    // 登录请求验证失败后提示信息
                    $("#login").text("登陆");
                    alert(result.msg + "\n" + result.data);
                }
            },
            // 登录请求失败
            error: function () {
                $("#login").text("登陆");
                alert("登录请求异常！");
            }
        })
        // $("#div1").load("demo_test.txt", function (responseTxt, statusTxt, xhr) {
        //     if (statusTxt == "success")
        //         alert("外部内容加载成功!");
        //     if (statusTxt == "error")
        //         alert("Error: " + xhr.status + ": " + xhr.statusText);
        // });
    });
});