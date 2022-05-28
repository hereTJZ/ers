// 获取弹窗
var modal = document.getElementById("myModal");

// 获取 <span> 元素，用于关闭弹窗
var span = document.querySelector(".close");

// 点击 <span> (x), 关闭弹窗
span.onclick = function () {
    modal.style.display = "none";
};

// 在用户点击其他地方时，关闭弹窗
window.onclick = function (event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
};

// 添加用户
function openEdit() {
    // 清空默认内容
    document.getElementById("myForm").reset();
    $("#add-user").attr("style", "display:inline-block;");
    $("#refresh-user").attr("style", "display:none;");
    $("#id-input").attr("style", "display:none;");
    modal.style.display = "block";
}

// 单个添加
function addUser() {
    $.ajax({
        //几个参数需要注意一下
        type: "POST",   //方法类型
        dataType: "json",   //预期服务器返回的数据类型
        url: "/addUser",    //执行的url请求
        //提交至服务器的数据
        data: $("form").serialize(), //请求参数放在请求头中
        success: function (result) {    //result是服务器返回的封装数据
            console.log(result);//打印服务端返回的数据(调试用)
            if (result.code === 200) {
                alert(result.msg + "\n" + "添加用户成功😄");
                window.location.reload();
            }
            if (result.code === 400) {
                // 输入信息为空提示信息
                alert(result.msg + "\n" + result.data);
            }
            if (result.code === 500) {
                // 输入信息错误提示信息
                alert(result.msg + "\n" + result.data);
            }
        },
        // 登录请求失败
        error: function () {
            alert("添加用户失败！");
        }
    });
}

// 编辑用户-获取原用户信息
function getUser(userId) {
    // 清空默认内容
    document.getElementById("myForm").reset();
    $("#add-user").attr("style", "display:none;");
    $("#refresh-user").attr("style", "display:inline-block;");
    $.ajax({
        //几个参数需要注意一下
        type: "GET",   //方法类型
        dataType: "json",   //预期服务器返回的数据类型
        url: "/getUserById",   //查找公告的url请求
        data: "id=" + userId,
        success: function (result) {    //result是服务器返回的封装数据
            // console.log(result);//打印服务端返回的数据(调试用)
            modal.style.display = "block";
            if (result.code === 200) {
                $("#id").val(result.data["id"]);
                $("#realName").val(result.data["realName"]);
                $("#phone").val(result.data["phone"]);
                $("#email").val(result.data["email"]);
                $("#male").prop("checked", true);
                if (result.data["gender"] === "女") {
                    $("#male").prop("checked", false);
                    $("#female").prop("checked", true);
                }
                // 教师
                if (result.data["role"] === 2) {
                    $("#school").val(result.data["school"]);
                    $("#faculty").val(result.data["faculty"]);
                    $("#subject").val(result.data["subject"]);
                    $("#teacher").prop("checked", true);
                }
                // 学生
                if ((result.data["role"]) === 3) {
                    $("#school").val(result.data["school"]);
                    $("#faculty").val(result.data["faculty"]);
                    $("#professional").val(result.data["professional"]);
                    $("#grade").val(result.data["grade"]);
                    $("#classNum").val(result.data["classNum"]);
                    $("#student").prop("checked", true);
                }
                // 社会人员
                if ((result.data["role"]) === 4) {
                    $("#social").prop("checked", true);
                }
            }

            if (result.code === 500) {
                alert(result.data);
            }
        },
        error: function () {
            alert("获取用户信息失败，请重新尝试！");
        }
    });
}

// 更新用户
function saveUser() {
    $.ajax({
        //几个参数需要注意一下
        type: "POST",   //方法类型
        dataType: "json",   //预期服务器返回的数据类型
        url: "/resetUserInfo",    //执行的url请求
        //提交至服务器的数据
        data: $("form").serialize(), //请求参数放在请求头中
        success: function (result) {    //result是服务器返回的封装数据
            // console.log(result);//打印服务端返回的数据(调试用)
            if (result.code === 200) {
                alert(result.msg + "\n" + "修改用户信息成功😄");
                window.location.reload();
            }
            if (result.code === 400) {
                // 输入信息为空提示信息
                alert(result.msg + "\n" + result.data);
            }
            if (result.code === 500) {
                // 输入信息错误提示信息
                alert(result.msg + "\n" + result.data);
            }
        },
        // 登录请求失败
        error: function () {
            alert("修改用户信息失败！");
        }
    });
}

// 删除用户
function deleteUser(userId) {
    if (window.confirm("确认要删除该用户吗？")) {
        $.ajax({
            //几个参数需要注意一下
            type: "GET",   //方法类型
            dataType: "json",   //预期服务器返回的数据类型
            url: "/deleteUserById",   //查找公告的url请求
            data: "id=" + userId,
            success: function (result) {    //result是服务器返回的封装数据
                // console.log(result);//打印服务端返回的数据(调试用)
                if (result.code === 200) {
                    alert(result.msg + "\n" + result.data);
                    window.location.reload();
                } else {
                    alert(result.msg + "\n" + result.data);
                }
            },
            error: function () {
                alert("删除失败，请重新尝试！");
            }
        });
    }
}

// 全选
function selectAll(listSize) {
    var selectCount = $(":checkbox:checked").length;

    if (selectCount < listSize) {
        $("input:checkbox").each(function () {
            $(this).attr("checked", true);
        });
    } else {
        $('input:checkbox').each(function () {
            $(this).attr('checked', false);
        });
    }
}


//模糊搜索
$(document).ready(function () {
    $("#newsSearch").click(function () {
        var content = $("#search-content").val()
        var role = $("#newsSearch").attr("data-role")
        //alert('/news?startTime=' + beginTime + '&endTime=' + endTime + '&content=' + content)
        window.location.href = '/user?content=' + content + '&role=' + role
    })
});

// 页面跳转
function changePage(pageNum) {
    var content = $("#search-content").val()
    var role = $("#newsSearch").attr("data-role")
    window.location.href = '/user?content=' + content + '&role=' + role + '&pageNum=' + pageNum + '&pageSize=' + 12
}

// 跳转页面
$(document).ready(function () {
    $(".news-page-go").click(function () {
        var page = $("#page-input").val()
        changePage(page)
    })
});