<%@ page import="java.util.List"
%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.tnt.fund.analyse.service.entity.FundCode" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
%>
<%
    List dwjzListWithArray = (List) request.getAttribute("dwjzListWithArray");
    Integer maxCC = (Integer) request.getAttribute("maxCC");
    String title = (String) request.getAttribute("title");
    FundCode fundCode = (FundCode) request.getAttribute("fundCode");
    System.out.println("========== title: " + title + ", fundCode:" + fundCode);

    String categoryStr = "[";
    for (int i = 0; i < maxCC; i++) {
        categoryStr += "\'" + i + "\' ";
        if (i != maxCC - 1) {
            categoryStr += ",";
        }
    }
    categoryStr += "]";

    String week1Str = "["; // [7.0, 6.9, 9.5, 14.5, 18.4, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
    String week2Str = "[", week3Str = "[", week4Str = "[", week5Str = "[";
    ArrayList<Double> week1Array = new ArrayList<Double>();
    ArrayList<Double> week2Array = new ArrayList<Double>();
    ArrayList<Double> week3Array = new ArrayList<Double>();
    ArrayList<Double> week4Array = new ArrayList<Double>();
    ArrayList<Double> week5Array = new ArrayList<Double>();
    for (int i = 0; i < dwjzListWithArray.size(); i++) {
        Double[] dwzjArray = (Double[]) dwjzListWithArray.get(i);

        for (int j = 0; j <= 4; j++) {
            String val = "null";
            if (dwzjArray[j] != null) {
                val = Double.toString(dwzjArray[j]);
            }

            switch (j) {
                case 0:
                    week1Str += val;
                    if (dwzjArray[j] != null) {
                        week1Array.add(dwzjArray[j]);
                    }
                    if (i != dwjzListWithArray.size() - 1) {
                        week1Str += ",";
                    }
                    break;
                case 1:
                    week2Str += val;
                    if (dwzjArray[j] != null) {
                        week2Array.add(dwzjArray[j]);
                    }
                    if (i != dwjzListWithArray.size() - 1) {
                        week2Str += ",";
                    }
                    break;
                case 2:
                    week3Str += val;
                    if (dwzjArray[j] != null) {
                        week3Array.add(dwzjArray[j]);
                    }
                    if (i != dwjzListWithArray.size() - 1) {
                        week3Str += ",";
                    }
                    break;
                case 3:
                    week4Str += val;
                    if (dwzjArray[j] != null) {
                        week4Array.add(dwzjArray[j]);
                    }
                    if (i != dwjzListWithArray.size() - 1) {
                        week4Str += ",";
                    }
                    break;
                case 4:
                    week5Str += val;
                    if (dwzjArray[j] != null) {
                        week5Array.add(dwzjArray[j]);
                    }
                    if (i != dwjzListWithArray.size() - 1) {
                        week5Str += ",";
                    }
                    break;
                default:
                    break;
            }

        }
    }
    week1Str += "]";
    week2Str += "]";
    week3Str += "]";
    week4Str += "]";
    week5Str += "]";

    Double minWeekAvg = 0.0;
    Double week1Avg = 0.0;
    for (int i = 0; i < week1Array.size(); i++) {
        week1Avg += week1Array.get(i);
    }
    week1Avg = week1Avg / week1Array.size() ;
    minWeekAvg = week1Avg;
    System.out.println("week1Avg: " + week1Avg + ", week1Array: " + week1Array);


    Double week2Avg = 0.0;
    for (int i = 0; i < week2Array.size(); i++) {
        week2Avg += week2Array.get(i);
    }
    week2Avg = week2Avg / week2Array.size();
    minWeekAvg = Math.min(week2Avg, minWeekAvg);
    System.out.println("week2Avg: " + week2Avg + ", week2Array: " + week2Array);


    Double week3Avg = 0.0;
    for (int i = 0; i < week3Array.size(); i++) {
        week3Avg += week3Array.get(i);
    }
    week3Avg = week3Avg / week3Array.size();
    minWeekAvg = Math.min(week3Avg, minWeekAvg);
    System.out.println("week3Avg: " + week3Avg + ", week3Array: " + week3Array);


    Double week4Avg = 0.0;
    for (int i = 0; i < week4Array.size(); i++) {
        week4Avg += week4Array.get(i);
    }
    week4Avg = week4Avg / week4Array.size();
    minWeekAvg = Math.min(week4Avg, minWeekAvg);
    System.out.println("week4Avg: " + week4Avg + ", week4Array: " + week4Array);


    Double week5Avg = 0.0;
    for (int i = 0; i < week5Array.size(); i++) {
        week5Avg += week5Array.get(i);
    }
    week5Avg = week5Avg / week5Array.size();
    minWeekAvg = Math.min(week5Avg, minWeekAvg);
    System.out.println("week5Avg: " + week5Avg + ", week5Array: " + week5Array);

    minWeekAvg = minWeekAvg - 0.01;

    String barChartValueStr = "[" + week1Avg+ ", " + week2Avg  + ", " + week3Avg  + ", " + week4Avg + ", " + week5Avg + "]"; // [107, 31, 635, 203, 2]
    System.out.println("---barChartValueStr: " + barChartValueStr);
%>

<html>
<script type="text/javascript" src="/fund-analyse-admin/js/jquery-1.11.3.min.js"></script>
<script src="/fund-analyse-admin/js/highcharts.js"></script>

<body>
http://localhost:8080/fund-analyse-admin/fund/week.do?fcode=000961&last=12
<br/>
http://localhost:8080/fund-analyse-admin/fund/week.do?fcode=340007&last=12
<div id="containerTop" style="width:100%; height:300px;"></div>
<div id="container" style="width:100%; height:800px;"></div>

</body>
</html>

<script>
    $(function () {
        $('#containerTop').highcharts({
            chart: {
                type: 'bar'
            },
            title: {
                text: '<%=fundCode.getFundName()%>：平均值'
            },
            subtitle: {
                text: ''
            },
            xAxis: {
                categories: ['周一', '周二', '周三', '周四', '周五'],
                title: {
                    text: null
                }
            },
            yAxis: {
                min: <%=minWeekAvg%>,
                title: {
                    text: '净值均值',
                    align: 'high'
                },
                labels: {
                    overflow: 'justify'
                }
            },
            tooltip: {
                valueSuffix: ' '
            },
            plotOptions: {
                bar: {
                    dataLabels: {
                        enabled: true
                    }
                }
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'top',
                x: -300,
                y: 10,
                floating: true,
                borderWidth: 1,
                backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
                shadow: true
            },
            credits: {
                enabled: false
            },
            series: [{
                name: '',
                data: <%=barChartValueStr%>
            }
            ]
        });
    });

    $(function () {
        $('#container').highcharts({
            chart: {
                type: 'line'
            },
            title: {
                text: '<%=fundCode.getFundName()%>：基金每日净值走势'
            },
            subtitle: {
                text: ''
            },
            xAxis: {
                categories: <%=categoryStr%>
            },
            yAxis: {
                title: {
                    text: 'Temperature (°C)'
                }
            },
            plotOptions: {
                line: {
                    dataLabels: {
                        enabled: false
                    },
                    enableMouseTracking: true
                }
            },
            series: [{
                name: '周一',
                data: <%=week1Str%>
            }, {
                name: '周二',
                data: <%=week2Str%>
            }, {
                name: '周三',
                data: <%=week3Str%>
            }, {
                name: '周四',
                data: <%=week4Str%>
            }, {
                name: '周五',
                data: <%=week5Str%>
            }]
        });
    });

    $(function () {
        $('#container2').highcharts({
            chart: {
                type: 'line'
            },
            title: {
                text: '基金每日净值走势'
            },
            subtitle: {
                text: 'Source: WorldClimate.com'
            },
            xAxis: {
                categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
            },
            yAxis: {
                title: {
                    text: 'Temperature (°C)'
                }
            },
            plotOptions: {
                line: {
                    dataLabels: {
                        enabled: true
                    },
                    enableMouseTracking: false
                }
            },
            series: [{
                name: 'Tokyo',
                data: [7.0, 6.9, 9.5, 14.5, 18.4, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
            }, {
                name: 'London',
                data: [3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8]
            }]
        });
    });
</script>