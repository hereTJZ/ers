// 获取 <span> 元素，用于关闭弹窗
var close2 = document.querySelector(".close2");

// 点击 <span> (x), 关闭弹窗
close2.onclick = function () {
    modal2.style.display = "none";
};

// 获取查看弹窗
var modal = document.getElementById("myModal");
// 获取编辑弹窗
var modal2 = document.getElementById("myModal2");

// 获取 <span> 元素，用于关闭弹窗
var span = document.querySelector(".close");

// 点击 <span> (x), 关闭弹窗
span.onclick = function () {
    modal.style.display = "none";
};

// 在用户点击其他地方时，关闭弹窗
window.onclick = function (event) {
    if (event.target == modal || event.target == modal2) {
        modal.style.display = "none";
        modal2.style.display = "none";
    }
};

var date = new Date();
if (date.getDate() % 2 === 0) {
    $("#logo-img").prop("src", "img/logo.png");
} else {
    $("#logo-img").prop("src", "img/earth.png");
}


// 新增公告
function addNotice() {
    // 清空默认内容
    $("#edit-title").text('');
    $("#edit-content").text('');
    modal2.style.display = "block";
}

// 查看公告
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
            // console.log(result);//打印服务端返回的数据(调试用)
            modal.style.display = "block";
            if (result.code === 200) {
                $("#notice-title").text(result.data["title"]);
                $("#notice-content").text(result.data["content"]);
                $("#release-Time").text("发布于：" + result.data["releaseTime"]);
            }
            if (result.code === 500) {
                alert(result.data);
            }
        },
        error: function () {
            alert("查看公告失败，请重新尝试！");
        }
    });
}

// 修改公告
function editNotice(noticeID) {
    $(document).ready(function () {
        $("#publish-notice").click(function () {
            $.ajax({
                //几个参数需要注意一下
                type: "POST",   //方法类型
                dataType: "json",   //预期服务器返回的数据类型
                url: "/addNotice",    //执行登录的url请求
                date: {
                    title: $("#edit-title").val(),
                    content: $("#edit-content").val(),
                    picture:
                },
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
        })
    })
}

// 删除公告
function deleteNotice(noticeID) {

}

//模糊搜索
$(document).ready(function () {
    $("#newsSearch").click(function () {
        var beginTime = $("#start-time").val()
        var endTime = $("#end-time").val()
        var content = $("#search-content").val()

        //alert('/news?startTime=' + beginTime + '&endTime=' + endTime + '&content=' + content)
        window.location.href = '/notice?startTime=' + beginTime + '&endTime=' + endTime + '&content=' + content
    })
});

function upPage(pageNum) {
    var beginTime = $("#start-time").val()
    var endTime = $("#end-time").val()
    var content = $("#search-content").val()
    window.location.href = '/notice?pageNum=' + pageNum + '&pageSize=' + 10 + '&startTime=' + beginTime + '&endTime=' + endTime + '&content=' + content
}

function nextPage(pageNum) {
    var beginTime = $("#start-time").val()
    var endTime = $("#end-time").val()
    var content = $("#search-content").val()
    window.location.href = '/notice?pageNum=' + pageNum + '&pageSize=' + 10 + '&startTime=' + beginTime + '&endTime=' + endTime + '&content=' + content
}

// 跳转页面
$(document).ready(function () {
    $(".news-page-go").click(function () {
        var beginTime = $("#start-time").val()
        var endTime = $("#end-time").val()
        var content = $("#search-content").val()
        var page = $("#page-input").val()

        //alert('/news?pageNum=' + page + '&pageSize=' + 10 + '&startTime=' + beginTime + '&endTime=' + endTime + '&content=' + content)
        window.location.href = '/notice?pageNum=' + page + '&pageSize=' + 10 + '&startTime=' + beginTime + '&endTime=' + endTime + '&content=' + content
    })
});

// 打开弹窗的按钮对象
var btn2 = document.querySelector(".addBtn");
var btn001 = document.querySelector(".addBtn1");
var btn002 = document.querySelector(".addBtn2");


var btn01 = document.querySelector(".myBtn1");
var btn02 = document.querySelector(".myBtn2");


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
    });
});