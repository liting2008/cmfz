<%@ page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <!-- 引入 ECharts 文件 -->
    <script src="${pageContext.request.contextPath}/echarts/echarts.js"></script>
    <script src="${pageContext.request.contextPath}/boot/js/jquery-3.3.1.min.js"></script>
    <%-- goeasy   --%>
    <script type="text/javascript" src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
    <%--  翻示例的js中China.js的url路径复制  --%>
    <script src="https://www.echartsjs.com/gallery/vendors/echarts/map/js/china.js?_v_=1553896255267"></script>
    <script type="text/javascript">
        $(function () {
            var goEasy = new GoEasy({
                appkey: '您的app key'
            });
            goEasy.subscribe({
                channel:'demo_channel',
                onMessage: function(message){
                    alert('收到：'+message.content);
                }
            });
            $.ajax({
                url:"${pageContext.request.contextPath}/user/queryGender",
                datatype:"json",
                type:"get",
                success:function (data) {
                    // 基于准备好的dom，初始化echarts实例
                    var myChart = echarts.init(document.getElementById('main'));

                    // 指定图表的配置项和数据
                    var option = {
                        title : {
                            text: '男女分布图',
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
                            max: 15,
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
                                data:[
                                    {name: '北京',value: Math.round(Math.random()*1000)},
                                    {name: '天津',value: Math.round(Math.random()*1000)},
                                    {name: '上海',value: Math.round(Math.random()*1000)},
                                    {name: '重庆',value: Math.round(Math.random()*1000)},
                                    {name: '河北',value: Math.round(Math.random()*1000)},
                                    {name: '河南',value: Math.round(Math.random()*1000)},
                                    {name: '云南',value: Math.round(Math.random()*1000)},
                                    {name: '辽宁',value: Math.round(Math.random()*1000)},
                                    {name: '黑龙江',value: Math.round(Math.random()*1000)},
                                    {name: '湖南',value: Math.round(Math.random()*1000)},
                                    {name: '安徽',value: Math.round(Math.random()*1000)},
                                    {name: '山东',value: Math.round(Math.random()*1000)},
                                    {name: '新疆',value: Math.round(Math.random()*1000)},
                                    {name: '江苏',value: Math.round(Math.random()*1000)},
                                    {name: '浙江',value: Math.round(Math.random()*1000)},
                                    {name: '江西',value: Math.round(Math.random()*1000)},
                                    {name: '湖北',value: Math.round(Math.random()*1000)},
                                    {name: '广西',value: Math.round(Math.random()*1000)},
                                    {name: '甘肃',value: Math.round(Math.random()*1000)},
                                    {name: '山西',value: Math.round(Math.random()*1000)},
                                    {name: '内蒙古',value: Math.round(Math.random()*1000)},
                                    {name: '陕西',value: Math.round(Math.random()*1000)},
                                    {name: '吉林',value: Math.round(Math.random()*1000)},
                                    {name: '福建',value: Math.round(Math.random()*1000)},
                                    {name: '贵州',value: Math.round(Math.random()*1000)},
                                    {name: '广东',value: Math.round(Math.random()*1000)},
                                    {name: '青海',value: Math.round(Math.random()*1000)},
                                    {name: '西藏',value: Math.round(Math.random()*1000)},
                                    {name: '四川',value: Math.round(Math.random()*1000)},
                                    {name: '宁夏',value: Math.round(Math.random()*1000)},
                                    {name: '海南',value: Math.round(Math.random()*1000)},
                                    {name: '台湾',value: Math.round(Math.random()*1000)},
                                    {name: '香港',value: Math.round(Math.random()*1000)},
                                    {name: '澳门',value: Math.round(Math.random()*1000)}
                                ]
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
                                data:[
                                    {name: '北京',value: Math.round(Math.random()*1000)},
                                    {name: '天津',value: Math.round(Math.random()*1000)},
                                    {name: '上海',value: Math.round(Math.random()*1000)},
                                    {name: '重庆',value: Math.round(Math.random()*1000)},
                                    {name: '河北',value: Math.round(Math.random()*1000)},
                                    {name: '安徽',value: Math.round(Math.random()*1000)},
                                    {name: '新疆',value: Math.round(Math.random()*1000)},
                                    {name: '浙江',value: Math.round(Math.random()*1000)},
                                    {name: '江西',value: Math.round(Math.random()*1000)},
                                    {name: '山西',value: Math.round(Math.random()*1000)},
                                    {name: '内蒙古',value: Math.round(Math.random()*1000)},
                                    {name: '吉林',value: Math.round(Math.random()*1000)},
                                    {name: '福建',value: Math.round(Math.random()*1000)},
                                    {name: '广东',value: Math.round(Math.random()*1000)},
                                    {name: '西藏',value: Math.round(Math.random()*1000)},
                                    {name: '四川',value: Math.round(Math.random()*1000)},
                                    {name: '宁夏',value: Math.round(Math.random()*1000)},
                                    {name: '香港',value: Math.round(Math.random()*1000)},
                                    {name: '澳门',value: Math.round(Math.random()*1000)}
                                ]
                            }
                        ]
                    };

                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option);
                }
            })
        })
    </script>
</head>
<body>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 600px;height:400px;"></div>
</body>
</html>