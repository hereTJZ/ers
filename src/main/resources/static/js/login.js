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

// 发送短信
function send_SMS() {
    alert("短信已发送，请注意查收😄")
}

// 执行登录
$(document).ready(function () {
    $("#login").click(function () {
        $.ajax({
            //几个参数需要注意一下
            type: "POST",   //方法类型
            dataType: "json",   //预期服务器返回的数据类型
            url: "/executeLogin",    //执行登录的url请求
            data: $("form").serialize(),    //序列化表单元素集为字符串以便提交
            success: function (result) {    //result是服务器返回的封装数据
                console.log(result);//打印服务端返回的数据(调试用)
                if (result.code === 200) {
                    alert(result.msg + "\nID:" + result.data["id"] + "\n" + result.data["realName"]);
                    // 登录请求验证成功后跳转首页面
                    window.location.href="/home"
                }else if (result.code === 400){
                    // 当前已登录，防止重登陆
                    alert(result.msg + "\n" + result.data);
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