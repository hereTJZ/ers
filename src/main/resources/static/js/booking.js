// 获取弹窗
var modal = document.getElementById("myModal");

// 打开弹窗的按钮对象
var btn = document.querySelector(".myBtn");

// 获取 <span> 元素，用于关闭弹窗
var span = document.querySelector(".close");

// 点击按钮打开弹窗
btn.onclick = function () {
    modal.style.display = "block";
};

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

var date = new Date();
if (date.getDate() % 2 === 0) {
    $("#logo-img").prop("src", "img/logo.png");
} else {
    $("#logo-img").prop("src", "img/earth.png");
}

// -----------------------------------------------------------------------------------------------------------------------------------

// 周历时间
const weekdays = ["周日", "周一", "周二", "周三", "周四", "周五", "周六"];

//数字格式转换形如1->01
function formatstr(value) {
    return value < 10 ? "0" + value : value;
}

// 根据 日期 和 weekNum 获取一周中周num的日期数，weekNum对应周日数为1~7
function getDateInOneWeek(date, weekNum) {
    // 返回 1970 年 1 月 1 日至当前时刻的毫秒数
    var nowTime = date.getTime();
    // 获得一周中的天数 (0 ~ 6)。其中0为周日，1~6为对应周数
    var weekday = date.getDay() === 0 ? 7 : date.getDay();

    var oneDayLong = 24 * 60 * 60 * 1000; // 一天的毫秒数
    var targetTime = nowTime + (weekNum - weekday) * oneDayLong;
    return new Date(targetTime).getDate();
}

// 根据 日期 获取一周中每周的日期数列表，列表的0~6中值对应周1~7的日期
function getOneWeekDateList(date) {
    // 返回 1970 年 1 月 1 日至当前时刻的毫秒数
    var nowTime = date.getTime();
    // 获得一周中的天数 (0 ~ 6)。其中0为周日，1~6为对应周日数
    var weekday = date.getDay() === 0 ? 7 : date.getDay();

    var oneDayLong = 24 * 60 * 60 * 1000; // 一天的毫秒数
    var MondayTime = nowTime + (1 - weekday) * oneDayLong;
    var TuesdayTime = nowTime + (2 - weekday) * oneDayLong;
    var WednesdayTime = nowTime + (3 - weekday) * oneDayLong;
    var ThursdayTime = nowTime + (4 - weekday) * oneDayLong;
    var FridayTime = nowTime + (5 - weekday) * oneDayLong;
    var SaturdayTime = nowTime + (6 - weekday) * oneDayLong;
    var SundayTime = nowTime + (7 - weekday) * oneDayLong;

    var oneWeekDateList = [];
    oneWeekDateList[0] = new Date(MondayTime).getDate();
    oneWeekDateList[1] = new Date(TuesdayTime).getDate();
    oneWeekDateList[2] = new Date(WednesdayTime).getDate();
    oneWeekDateList[3] = new Date(ThursdayTime).getDate();
    oneWeekDateList[4] = new Date(FridayTime).getDate();
    oneWeekDateList[5] = new Date(SaturdayTime).getDate();
    oneWeekDateList[6] = new Date(SundayTime).getDate();

    return oneWeekDateList;
}

// 判断两周是否为同一周
function isThisWeek(date1, date2) {
    if (date1.getFullYear() === date2.getFullYear() && date1.getMonth() === date2.getMonth() && getDateInOneWeek(date1, 1) === getDateInOneWeek(date2, 1)) {
        return true;
    } else {
        return false;
    }
}

// 更新时间线位置函数
function refreshTimeLine(date) {
    // var date = new Date();
    var todayMinutes = date.getHours() * 60 + date.getMinutes(); // 今日总分钟数
    $(".line-wrap .line-3").css({ top: 42 + (todayMinutes / 1440) * 600 + "px" }); /* 42 + 50X，范围42~642*/
    $("#line-3-time").html("&nbsp;" + formatstr(date.getHours()) + ":" + formatstr(date.getMinutes()));
}

// 刷新页面时间
function date_time(date) {
    var rightNow = new Date();
    // date.setFullYear(2022, 4, 9);
    // 获得当前的年份
    var year = date.getFullYear();
    // 获得当前月份
    var month = date.getMonth() + 1;
    // 获得当前的日数 (1 ~ 31)
    var day = date.getDate();
    // 获得一周中的天数 (0 ~ 6)。其中0为周日，1~6为对应周数
    var weekday = date.getDay();
    console.log("刷新时间：" + date);

    // 更新标题日期
    $("#year").text(formatstr(year));
    $("#month").text(formatstr(month));
    $("#today").text(formatstr(day));

    // 更新周历表头时间
    for (var i = 0; i < 7; i++) {
        document.querySelectorAll("th span")[i].innerHTML = formatstr(getDateInOneWeek(date, i + 1));
    }

    // 是否为当前周
    if (isThisWeek(rightNow, date)) {
        // 表头与日轴样式
        if (weekday !== 0) {
            // js方式设置css样式
            document.getElementsByTagName("th")[weekday - 1].style = "background: #0099FF; color: #fff;";
            // jQuery方式设置css样式
            $(".line-left").css({ left: 60 + 170 * (weekday - 1) + "px" }); /* 60 + 170X */
            $(".line-right").css({ left: 228 + 170 * (weekday - 1) + "px" }); /* 228 + 170X */
        } else {
            // 周日
            document.getElementsByTagName("th")[6].style = "background: #0099FF; color: #fff;";
            $(".line-left").css({ left: 60 + 170 * 6 + "px" }); /* 60 + 170X */
            $(".line-right").css({ left: 228 + 170 * 6 + "px" }); /* 228 + 170X */
        }

        // 时间线-需时刻更新
        refreshTimeLine(date);

        // 显示日轴与时间线
        $(".line-wrap").show();
        // $(".line-wrap").css("display", "block");
    } else {
        // 隐藏日轴与时间线
        $(".line-wrap").hide();
        // js方式复原表头css样式
        if (weekday !== 0) {
            document.getElementsByTagName("th")[weekday - 1].style = "background-color: #c8f8ff; color: #000000;";
        } else {
            // 周日
            document.getElementsByTagName("th")[6].style = "background-color: #c8f8ff; color: #000000;";
        }
    }
}

// 每次刷页面时执行函数，更新当前页面时间
var time = new Date(); /* 全局变量 */
date_time(time);
// 1秒更新一次
// 注意：myVar = setInterval(date_time(new Date()), 1000);
// 上面的写法错误，setInterval内的函数参数不能带参数（代参函数有返回值），只能采用如下匿名函数的方法
var myVar = setInterval(function () {
    date_time(new Date());
}, 1000);

// -----------------------------------------------------------------------------------------------------------------------------------

// 切换上一周
$(document).ready(function () {
    $("#last-week").click(function () {
        // 非本周及停止更新时间（静态）
        clearInterval(myVar);
        // time变为此刻的上一周
        time.setDate(time.getDate() - 7);
        date_time(time);

        // 还有一种情况，当切换上下周刚好回到本周时，要回归时间轴的更新（动态）
        if (isThisWeek(time, new Date())) {
            if (clickNum === 0) {
                myVar = setInterval(function () {
                    date_time(new Date());
                }, 1000);
            }
            clickNum++;
        } else {
            clickNum = 0;
        }
    });
});

// 切换下一周
$(document).ready(function () {
    $("#next-week").click(function () {
        // 非本周及停止更新时间（静态）
        clearInterval(myVar);
        // time变为此刻的下一周
        time.setDate(time.getDate() + 7);
        date_time(time);

        // 还有一种情况，当切换上下周刚好回到本周时，要回归时间轴的更新（动态）
        if (isThisWeek(time, new Date())) {
            if (clickNum === 0) {
                myVar = setInterval(function () {
                    date_time(new Date());
                }, 1000);
            }
            clickNum++;
        } else {
            clickNum = 0;
        }
    });
});

// 回到本周
var clickNum = 1;
$(document).ready(function () {
    $("#this-week").click(function () {
        // 回到本周重新开始更新时间（动态）
        time = new Date();
        date_time(time);

        // 这里要防止多次点击“回到本周”而导致setInterval多次重复执行
        if (clickNum === 0) {
            myVar = setInterval(function () {
                date_time(new Date());
            }, 1000);
        }
        clickNum++;
    });
});
