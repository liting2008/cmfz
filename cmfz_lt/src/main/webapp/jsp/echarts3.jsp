<%@page contentType="text/html; UTF-8" isELIgnored="false" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <!-- 引入 ECharts 文件 -->
    <script src="../echarts/echarts.js"></script>
    <script src="../boot/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="https://cdn-hangzhou.goeasy.io/goeasy.js"></script>
    <script src="https://www.echartsjs.com/gallery/vendors/echarts/map/js/china.js?_v_=1553896255267"></script>




    <script>
        $(function () {
            var goEasy = new GoEasy({
                appkey: 'BC-5a129bc32c42435db40f84691bea58d5'
            })

            goEasy.subscribe({
                channel:'demo_channel',
                onMessage: function(message){
                    var data = JSON.parse(message.content);
                   showAll(data)
                }
            });

            $.ajax({
                url:"${pageContext.request.contextPath}/user/queryCount",
                type:"get",
                datatype:"json",
                success:function (data) {
                    showAll(data)
                }
            })

        })

        function showAll(data) {
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));
            var myChart1 = echarts.init(document.getElementById('area'));

            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: 'ECharts 入门示例'
                },
                tooltip: {},
                legend: {
                    data: ['用户注册月份统计']
                },
                xAxis: {
                    data: data.month
                },
                yAxis: {},
                series: [{
                    name: '注册人数',
                    type: 'bar',
                    data: data.count
                }]
            };

            //大公鸡
            areaOption = {
                title : {
                    text: 'iphone销量',
                    subtext: '纯属虚构',
                    left: 'center'
                },
                tooltip : {
                    trigger: 'item'
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data:['男','女']
                },
                visualMap: {
                    min: 0,
                    max: 2500,
                    left: 'left',
                    top: 'bottom',
                    text:['高','低'],           // 文本，默认为数值文本
                    calculable : true
                },
                toolbox: {
                    show: true,
                    orient : 'vertical',
                    left: 'right',
                    top: 'center',
                    feature : {
                        mark : {show: true},
                        dataView : {show: true, readOnly: false},
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                series : [
                    {
                        name: '男',
                        type: 'map',
                        mapType: 'china',
                        roam: false,
                        label: {
                            normal: {
                                show: false
                            },
                            emphasis: {
                                show: true
                            }
                        },
                        data: data.man
                    },
                    {
                        name: '女',
                        type: 'map',
                        mapType: 'china',
                        label: {
                            normal: {
                                show: false
                            },
                            emphasis: {
                                show: true
                            }
                        },
                        data: data.women
                    },

                ]
            };



            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
            myChart1.setOption(areaOption);
        }
    </script>
</head>


<body>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 600px;height:400px;"></div>
<div id="area" style="width: 600px;height:400px;"></div>
</body>
</html>