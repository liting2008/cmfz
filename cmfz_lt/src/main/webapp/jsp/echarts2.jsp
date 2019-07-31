<%@ page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <!-- 引入 ECharts 文件 -->
    <script src="${pageContext.request.contextPath}/echarts/echarts.js"></script>
    <script src="${pageContext.request.contextPath}/boot/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript">
        $(function () {
            var nums = [];
            $.ajax({
                url: "${pageContext.request.contextPath}/user/queryMonth",
                type: "get",
                datatype: "json",
                success: function (data) {
                    // 基于准备好的dom，初始化echarts实例
                    var myChart = echarts.init(document.getElementById('main'));
                    for (var i = 0; i < data.length; i++) {
                        nums.push(data[i]); //这里直接把值赋给nums数组就行了。不是data[i].name;
                    }
                    // 指定图表的配置项和数据
                    var option = {
                        title: {
                            text: '每个月注册用户数量'
                        },
                        tooltip: {},
                        legend: {
                            data: ['人数']
                        },
                        xAxis: {
                            data: ["2019-01", "2019-03", "2019-05", "2019-07"]
                        },
                        yAxis: {},
                        series: [{
                            name: '人数',
                            type: 'bar',
                            data: nums
                        }]
                    };
                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option);
                }
            })
        })
    </script>
</head>
<body>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM容器 -->
<div id="main" style="width: 600px;height:400px;"></div>
</body>
</html>