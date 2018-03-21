<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <title>顾客等级统计</title>
    <%@ include file="../include/css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">
    <%@include file="../include/header.jsp"%>
    <!-- =============================================== -->

    <jsp:include page="../include/sider.jsp">
        <jsp:param name="menu" value="charts_customer_count"/>
    </jsp:include>
    <!-- 右侧内容部分 -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">

            <div class="box">
                <div class="box-header with-border">
                </div>
                <div class="box-body">
                    <div id="customer_count" style="height: 300px;width: 80%"></div>
                </div>
            </div>

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <%@ include file="../include/footer.jsp"%>

</div>
<!-- ./wrapper -->

<%@include file="../include/js.jsp"%>
<script src="/static/plugins/echarts/echarts.common.min.js"></script>
<script>
    $(function () {
    	
    	var customer_count = $("#customer_count")[0];
    	var myCharts = echarts.init(customer_count);
    	myCharts.setOption({
    		title: {
                text: '用户统计',
                left:'center'
            },
            
            legend: {
                data:['等级'],
                left:'right'
            
            },
            tooltip:{
            	
            },
            xAxis: {
                data: []
            },
            yAxis: {
            	name:'数量'
            },
            series: [{
                name: '等级',
                type: 'bar',
                data: []
            }]
        
    	});
    	
    	$.get("/charts/level/count",function(data){
    		if(data.state == 0){
    			var levelArray = [];
    			var countArray = [];
    			
    			for(var i = 0; i < data.data.length; i++){
    				var obj = data.data[i];
    				levelArray.push(obj.level);
    				countArray.push(obj.count);
    			}
    			
    			myCharts.setOption({
    	    		xAxis: {
    	                data: levelArray
    	            },
    	            series: [{
    	                
    	                data: countArray
    	            }]
    	    	});
    		}
    	});
    	
    	
       
    });
</script>

</body>
</html>