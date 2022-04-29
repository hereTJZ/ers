// 地图控制
var center = new TMap.LatLng(31.771519, 117.205399);
var centerHeight = new TMap.LatLng(31.771519, 117.205399, 60);

// 初始化地图
var map = new TMap.Map("container", {
    zoom: 15, // 设置地图缩放
    center: new TMap.LatLng(31.771519, 117.205399), // 设置地图中心点坐标，
    pitch: 0, // 俯仰度
    rotation: 0, // 旋转角度
});

// 获取缩放控件实例，设置缩放值可见
control = map.getControl(TMap.constants.DEFAULT_CONTROL_ID.ZOOM);
control.setNumVisible(true);

function setCenter() {
    map.setCenter(new TMap.LatLng(31.771519, 117.205399)); //坐标为默认值
}

// MultiMarker文档地址：https://lbs.qq.com/webApi/javascriptGL/glDoc/glDocMarker
var marker = new TMap.MultiMarker({
    map: map,
    styles: {
        // 点标记样式
        marker: new TMap.MarkerStyle({
            width: 20, // 样式宽
            height: 30, // 样式高
            anchor: { x: 10, y: 30 }, // 描点位置
        }),
    },
    geometries: [
        // 点标记数据数组
        {
            // 标记位置(纬度，经度，高度)
            position: center,
            id: "marker",
        },
    ],
});

document.querySelector("button.btn1").onclick = function () {
    var data = marker.getGeometryById("marker");
    Object.assign(data, {
        position: centerHeight,
    });
    marker.updateGeometries([data]);
    map.easeTo(
        {
            pitch: 80,
            zoom: 17,
            rotation: -30, // 旋转角度
        },
        600
    );
};
document.querySelector("button.btn2").onclick = function () {
    var data = marker.getGeometryById("marker");
    Object.assign(data, {
        position: center,
    });
    marker.updateGeometries([data]);
    map.easeTo(
        {
            pitch: 0,
            zoom: 16,
            rotation: 0, // 旋转角度
        },
        600
    );
};
