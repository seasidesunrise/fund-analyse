<%@ page import="java.util.List"
%>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
%>
<%
    ArrayList dwjzListWithArray = (ArrayList) request.getAttribute("dwjzListWithArray");
    Integer maxCC = (Integer) request.getAttribute("maxCC");
    String names = "['周一', '周二', '周三', '周四', '周五']";

    String week1 = "[", week2 = "[", week3 = "[", week4 = "[", week5 = "[";
    for (int i = 0; i < dwjzListWithArray.size(); i++) {
        Double[] dwzjArray = (Double[]) dwjzListWithArray.get(i);
        for (int j = 0; j < 4; j++) {
            String val = "null";
            if (dwzjArray[j] != null) {
                val = Double.toString(dwzjArray[j]);
            }
            switch (j) {
                case 0:
                    week1 += "[" + i + ", " + val + "]"; // [1, 29.54]
                    if (i != dwjzListWithArray.size() - 1) {
                        week1 += ",";
                    }
                    break;
                case 1:
                    week2 += "[" + i + ", " + val + "]"; // [1, 29.54]
                    if (i != dwjzListWithArray.size() - 1) {
                        week2 += ",";
                    }
                    break;
                case 2:
                    week3 += "[" + i + ", " + val + "]"; // [1, 29.54]
                    if (i != dwjzListWithArray.size() - 1) {
                        week3 += ",";
                    }
                    break;
                case 3:
                    week4 += "[" + i + ", " + val + "]"; // [1, 29.54]
                    if (i != dwjzListWithArray.size() - 1) {
                        week4 += ",";
                    }
                    break;
                case 4:
                    week5 += "[" + i + ", " + val + "]"; // [1, 29.54]
                    if (i != dwjzListWithArray.size() - 1) {
                        week5 += ",";
                    }
                    break;
                default:
                    break;
            }
        }
    }
    week1 += "]";
    week2 += "]";
    week3 += "]";
    week4 += "]";
    week5 += "]";
%>
<html>
<script type="text/javascript" src="/fund-analyse-admin/js/jquery-1.11.3.min.js"></script>
<script src="/fund-analyse-admin/js/highstock.js"></script>
<script src="/fund-analyse-admin/js//modules/exporting.js"></script>

<body>

<div id="container" style="width:100%; height:600px;"></div>

</body>
</html>
<script>
    $(function () {
        var seriesOptions = [],
                seriesCounter = 0,
                names = <%=names%>;

        function createChart() {

            $('#container').highcharts('StockChart', {
                rangeSelector: {
                    selected: 4
                },

                yAxis: {
                    labels: {
                        formatter: function () {
                            return (this.value > 0 ? ' + ' : '') + this.value + '%';
                        }
                    },
                    plotLines: [{
                        value: 0,
                        width: 2,
                        color: 'silver'
                    }]
                },

//                plotOptions: {
//                    series: {
//                        compare: 'value',
//                        showInNavigator: true
//                    }
//                },

                tooltip: {
                    pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.change}%)<br/>',
                    valueDecimals: 5,
                    split: true
                },

                series: [{
                    name: '周一',
                    data: <%=week1%>
                }, {
                    name: '周二',
                    data: <%=week2%>
                }, {
                    name: '周三',
                    data: <%=week3%>
                }, {
                    name: '周四',
                    data: <%=week4%>
                }, {
                    name: '周五',
                    data: <%=week5%>
                }]
            });
        }

        createChart();

//        $.each(names, function (i, name) {
//
//            $.getJSON('https://www.highcharts.com/samples/data/jsonp.php?filename=' + name.toLowerCase() + '-c.json&callback=?', function (data) {
//
//                seriesOptions[i] = {
//                    name: name,
//                    data: data
//                };
//
//                // As we're loading the data asynchronously, we don't know what order it will arrive. So
//                // we keep a counter and create the chart when all the data is loaded.
//                seriesCounter += 1;
//
//                if (seriesCounter === names.length) {
//                    createChart();
//                }
//            });
//        });
    });

</script>