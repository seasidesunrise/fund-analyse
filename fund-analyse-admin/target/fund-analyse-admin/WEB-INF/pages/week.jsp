<%@ page import="java.util.List"
%>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
%>
<%
    List dwjzListWithArray = (List) request.getAttribute("dwjzListWithArray");
    Integer maxCC = (Integer) request.getAttribute("maxCC");

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
                    if (i != dwjzListWithArray.size() - 1) {
                        week1Str += ",";
                    }
                    break;
                case 1:
                    week2Str += val;
                    if (i != dwjzListWithArray.size() - 1) {
                        week2Str += ",";
                    }
                    break;
                case 2:
                    week3Str += val;
                    if (i != dwjzListWithArray.size() - 1) {
                        week3Str += ",";
                    }
                    break;
                case 3:
                    week4Str += val;
                    if (i != dwjzListWithArray.size() - 1) {
                        week4Str += ",";
                    }
                    break;
                case 4:
                    week5Str += val;
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
%>

<html>
<script type="text/javascript" src="/fund-analyse-admin/js/jquery-1.11.3.min.js"></script>

<script src="/fund-analyse-admin/js/highcharts.js"></script>

<body>

<div id="container" style="width:100%; height:800px;"></div>

</body>
</html>
<script>
    $(function () {
        $('#container').highcharts({
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