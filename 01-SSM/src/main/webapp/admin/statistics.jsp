<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>设备管理统计分析</title>
    <script src="https://unpkg.com/echarts@5.4.2/dist/echarts.min.js"></script>
<style>
body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 20px;
    background-color: #f4f4f4;
}
.chart-row {
    display: flex;
    justify-content: space-between;
    margin-bottom: 20px;
}
.chart-container {
    width: 48%;
    height: 400px;
    background-color: white;
    border-radius: 8px;
    box-shadow: 0 4px 6px rgba(0,0,0,0.1);
    padding: 15px;
}
.chart-title {
    text-align: center;
    font-weight: bold;
    margin-bottom: 10px;
}
</style>
</head>
<body>
<div class="chart-row">
    <div class="chart-container">
        <div class="chart-title">设备数量统计</div>
        <div id="facilityCountChart" style="width:100%;height:350px;"></div>
    </div>
    <div class="chart-container">
        <div class="chart-title">设备状态分布</div>
        <div id="statusDistributionChart" style="width:100%;height:350px;"></div>
    </div>
</div>

<div class="chart-row">
    <div class="chart-container">
        <div class="chart-title">设备新增趋势</div>
        <div id="monthlyAddChart" style="width:100%;height:350px;"></div>
    </div>
    <div class="chart-container">
        <div class="chart-title">设备报废趋势</div>
        <div id="monthlyDeleteChart" style="width:100%;height:350px;"></div>
    </div>
</div>

<div class="chart-row">
    <div class="chart-container">
        <div class="chart-title">设备维修趋势</div>
        <div id="monthlyRepairChart" style="width:100%;height:350px;"></div>
    </div>
    <div class="chart-container">
        <div class="chart-title">新增设备名字分布</div>
        <div id="facilityTypeChart" style="width:100%;height:350px;"></div>
    </div>
</div>

<script>
fetch('/StatisticsServlet')
.then(response => response.json())
.then(data => {
    // 设备数量统计
    const facilityCountChart = echarts.init(document.getElementById('facilityCountChart'));
    facilityCountChart.setOption({
        tooltip: { trigger: 'axis' },
        xAxis: {
            type: 'category',
            data: ['新增设备', '报废设备', '维修设备']
        },
        yAxis: { type: 'value' },
        series: [{
            type: 'bar',
            itemStyle: {
                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                    {offset: 0, color: '#83bff6'},
                    {offset: 1, color: '#188df0'}
                ])
            },
            data: [
                data.addFacilityCount,
                data.deleteFacilityCount,
                data.repairsCount
            ]
        }]
    });

    // 状态分布饼图
    const statusDistributionChart = echarts.init(document.getElementById('statusDistributionChart'));
    statusDistributionChart.setOption({
        tooltip: { trigger: 'item' },
        series: [{
            type: 'pie',
            radius: ['40%', '70%'],
            avoidLabelOverlap: false,
            itemStyle: {
                borderRadius: 10,
                borderColor: '#fff',
                borderWidth: 2
            },
            data: Object.entries(data.facilityStatusDistribution)
                .map(([name, value]) => {
                    let displayName;
                    switch (name) {
                        case '1': displayName = '申请中'; break;
                        case '2': displayName = '申请成功'; break;
                        case '3': displayName = '已处理'; break;
                        default: displayName = name;
                    }
                    return { name: displayName, value };
                })
        }]
    });

    // 月度新增趋势
    const monthlyAddChart = echarts.init(document.getElementById('monthlyAddChart'));
    monthlyAddChart.setOption({
        tooltip: { trigger: 'axis' },
        xAxis: {
            type: 'category',
            data: data.monthlyAddFacilityTrend.map(item => item.month)
        },
        yAxis: { type: 'value' },
        series: [{
            type: 'line',
            smooth: true,
            data: data.monthlyAddFacilityTrend.map(item => item.count)
        }]
    });

    // 月度报废趋势
    const monthlyDeleteChart = echarts.init(document.getElementById('monthlyDeleteChart'));
    monthlyDeleteChart.setOption({
        tooltip: { trigger: 'axis' },
        xAxis: {
            type: 'category',
            data: data.monthlyDeleteFacilityTrend.map(item => item.month)
        },
        yAxis: { type: 'value' },
        series: [{
            type: 'line',
            smooth: true,
            data: data.monthlyDeleteFacilityTrend.map(item => item.count)
        }]
    });

    // 月度维修趋势
    const monthlyRepairChart = echarts.init(document.getElementById('monthlyRepairChart'));
    monthlyRepairChart.setOption({
        tooltip: { trigger: 'axis' },
        xAxis: {
            type: 'category',
            data: data.monthlyRepairsTrend.map(item => item.month)
        },
        yAxis: { type: 'value' },
        series: [{
            type: 'line',
            smooth: true,
            data: data.monthlyRepairsTrend.map(item => item.count)
        }]
    });

    // 设备类型分布
    const facilityTypeChart = echarts.init(document.getElementById('facilityTypeChart'));
    facilityTypeChart.setOption({
        tooltip: { trigger: 'item' },
        series: [{
            type: 'pie',
            radius: ['40%', '70%'],
            avoidLabelOverlap: false,
            itemStyle: {
                borderRadius: 10,
                borderColor: '#fff',
                borderWidth: 2
            },
            data: Object.entries(data.facilityTypeDistribution)
                .filter(([key, value]) => key !== '<prototype>')
                .map(([key, value]) => ({ name: key, value }))
        }]
    });
});
</script>
</body>
</html>
