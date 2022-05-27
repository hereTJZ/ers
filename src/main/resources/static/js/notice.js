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
                $("#notice-img").prop("src", result.data["imageAddress"]);
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
    // 清空默认内容
    $("#edit-title").text('');
    $("#edit-content").text('');
    $.ajax({
        //几个参数需要注意一下
        type: "GET",   //方法类型
        dataType: "json",   //预期服务器返回的数据类型
        url: "/notice/" + noticeID,   //查找公告的url请求
        success: function (result) {    //result是服务器返回的封装数据
            // console.log(result);//打印服务端返回的数据(调试用)
            modal2.style.display = "block";
            if (result.code === 200) {
                $("#edit-title").text(result.data["title"]);
                $("#edit-content").text(result.data["content"]);
            }
            if (result.code === 500) {
                alert(result.data);
            }
        },
        error: function () {
            alert("获取公告失败，请重新尝试！");
        }
    });
}

var upload = document.querySelector('#upload-file');

// 发布公告
$(document).ready(function () {
    $("#publish-notice").click(function () {
        // Ajax上传文件，则需要使用 FormData 对象来作为数据
        var formdata = new FormData();
        formdata.append("title", $("#edit-title").val());
        formdata.append("content", $("#edit-content").val());
        formdata.append("picture", $("#upload-file")[0].files[0]);

        $.ajax({
            //几个参数需要注意一下
            async: false,
            type: "POST",   //方法类型
            dataType: "json",   //预期服务器返回的数据类型
            url: "/addNotice",    //执行登录的url请求
            data: formdata,  // 参数放在了请求体中
            cache: false,
            contentType: false,  //发送给服务器的数据类型，对应dataType，false表示不要去设置Content-Type请求头
            processData: false,  //不要将发送的数据处理为字符串，默认true，后端接收到的都是字符串
            success: function (result) {    //result是服务器返回的封装数据
                console.log(result);//打印服务端返回的数据(调试用)
                if (result.code === 200) {
                    alert(result.msg + "\n" + result.data);
                    // 登录请求验证成功后跳转首页面
                    window.location.href = "/notice"
                }
                if (result.code === 400) {
                    alert(result.msg + "\n" + result.data);
                }
                if (result.code === 401) {
                    alert("保存图片异常！！");
                    console.log(result.data)
                }
                if (result.code === 500) {
                    alert(result.msg + "\n" + result.data);
                    window.location.href = "/login"
                }
            },
            error: function () {
                alert("发布失败，请重试！")
            }
        });
    })
})

var preview = document.querySelector('#preview');
upload.addEventListener('change', updateImageDisplay);

// 图片预览
function updateImageDisplay() {
    while (preview.firstChild) {
        preview.removeChild(preview.firstChild);
    }

    var curFiles = upload.files;
    if (curFiles.length === 0) {
        var para = document.createElement('p');
        para.textContent = '当前未选择图片';
        preview.appendChild(para);
    } else {
        // $("#preview ol").style.display = "block";
        var list = document.createElement('ol');
        preview.appendChild(list);
        for (var i = 0; i < curFiles.length; i++) {
            var listItem = document.createElement('li');
            var para = document.createElement('p');
            if (validFileType(curFiles[i])) {
                para.textContent = '图片大小：' + returnFileSize(curFiles[i].size) + '.';
                var image = document.createElement('img');
                image.src = window.URL.createObjectURL(curFiles[i]);

                listItem.appendChild(image);
                listItem.appendChild(para);
            } else {
                para.textContent = '请选择正确的图片类型！';
                listItem.appendChild(para);
            }
            list.appendChild(listItem);
        }
    }
}

var fileTypes = [
    'image/jpeg',
    'image/pjpeg',
    'image/png',
    'image/gif'
]

// 合法图片类型
function validFileType(file) {
    for (var i = 0; i < fileTypes.length; i++) {
        if (file.type === fileTypes[i]) {
            return true;
        }
    }
    return false;
}

// 图片大小
function returnFileSize(number) {
    if (number < 1024) {
        return number + 'bytes';
    } else if (number > 1024 && number < 1048576) {
        return (number / 1024).toFixed(1) + 'KB';
    } else if (number > 1048576) {
        return (number / 1048576).toFixed(1) + 'MB';
    }
}

// 删除公告
function deleteNotice(noticeID) {
    $.ajax({
        url: "/deleteNotice",
        data: {"id": noticeID},
        success: function (result) {    //result是服务器返回的封装数据
            console.log(result);//打印服务端返回的数据(调试用)
            if (result.code === 200) {
                alert(result.msg + "\n" + result.data);
                // 登录请求验证成功后跳转首页面
                window.location.reload();
            }
            if (result.code === 400) {
                alert(result.msg + "\n" + result.data);
            }
            if (result.code === 500) {
                alert(result.msg + "\n" + result.data);
                window.location.href = "/login"
            }
        },
        error: function () {
            alert("删除失败，请重试！")
        }
    })
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

// 页面跳转
function changePage(pageNum) {
    var beginTime = $("#start-time").val()
    var endTime = $("#end-time").val()
    var content = $("#search-content").val()
    window.location.href = '/notice?pageNum=' + pageNum + '&pageSize=' + 10 + '&startTime=' + beginTime + '&endTime=' + endTime + '&content=' + content
}

// 跳转页面
$(document).ready(function () {
    $(".news-page-go").click(function () {
        var page = $("#page-input").val()
        changePage(page)
    })
});

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