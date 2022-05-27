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

// 显示编辑框
function openEdit() {
    // 清空默认内容
    $("form").reset();
    modal.style.display = "block";
}

// 单个添加
function addOne() {
    $.ajax({
        //几个参数需要注意一下
        type: "POST",   //方法类型
        dataType: "json",   //预期服务器返回的数据类型
        url: "/notice/",   //查找公告的url请求
        data: $("form").serialize(),
        success: function (result) {    //result是服务器返回的封装数据
            // console.log(result);//打印服务端返回的数据(调试用)
            modal2.style.display = "block";
            if (result.code === 200) {
                $("#edit-title").text(result.data["title"]);
                $("#edit-content").text(result.data["content"]);
                modal.style.display = "block";
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