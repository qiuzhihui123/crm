<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <title>销售机会统计</title>
    <%@ include file="../include/css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">
    <%@include file="../include/header.jsp"%>
    <!-- =============================================== -->

    <jsp:include page="../include/sider.jsp">
        <jsp:param name="menu" value="charts_saleprocess_count"/>
    </jsp:include>
    <!-- 右侧内容部分 -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">

            <div class="box">
                <div class="box-header with-border">
                </div>
                <div class="box-body">
                    <div id="process_count" style="height: 300px;width: 80%"></div>
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
<script src="/static/plugins/echarts/echarts.min.js"></script>
<script>
    $(function () {
    	
    	var process_count = $("#process_count")[0];
    	var myCharts = echarts.init(process_count);
    	
    	option = {
    		    title: {
    		        text: '进度统计',
    		    },
    		    
    		    tooltip: {
    		        trigger: 'item',
    		        formatter: "{a} <br/>{b} : {c}%"
    		    },
    		    
    		    toolbox: {
    		        feature: {
    		            saveAsImage: {}
    		        }
    		    },
    		    legend: {
    		        /* data: ['展现','点击','访问','咨询','订单'] */
    		    },
    		    calculable: true,
    		    series: [
    		        {
    		            name:'进度统计',
    		            type:'funnel',
    		            left: '10%',
    		            top: 50,
    		            //x2: 80,
    		            bottom: 60,
    		            width: '80%',
    		            // height: {totalHeight} - y - y2,
    		            min: 0,
    		            max: 100,
    		            minSize: '0%',
    		            maxSize: '100%',
    		            sort: 'descending',
    		            gap: 2,
    		            label: {
    		                normal: {
    		                    show: true,
    		                    position: 'inside'
    		                },
    		                emphasis: {
    		                    textStyle: {
    		                        fontSize: 20
    		                    }
    		                }
    		            },
    		            labelLine: {
    		                normal: {
    		                    length: 10,
    		                    lineStyle: {
    		                        width: 1,
    		                        type: 'solid'
    		                    }
    		                }
    		            },
    		            itemStyle: {
    		                normal: {
    		                    borderColor: '#fff',
    		                    borderWidth: 1
    		                }
    		            },
    		            /* data: [
    		                {value: 60, name: '访问'},
    		                {value: 40, name: '咨询'},
    		                {value: 20, name: '订单'},
    		                {value: 80, name: '点击'},
    		                {value: 100, name: '展现'}
    		            ] */
    		        }
    		    ]
    		};

    	myCharts.setOption(option);
    	
    	/* myCharts.setOption({
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
        
    	}); */
    	
    	$.get("/charts/process/count",function(res){
    		if(res.state == 0){
    			var processCountArray = [];
    			var processArray = [];
    			var length = res.data.length;
    			
    			var counts = res.data[length-1].counts;
    			
    			
    			for(var i = 0; i < length-1; i++){
    				var obj = res.data[i];
    				
    				var process = obj.process;
    				var count = obj.count;
    				
    				processArray.push(process);
    				
    				var processCount = {value:count,name:process};
    				
    				processCountArray.push(processCount);
    				
    			}
    			
    			myCharts.setOption({
    				title:{
    					text: '进度统计',
    					subtext:'总数'+ counts
    				},
    				
    				series:[{
    					max: counts,
    					data: processCountArray
    				}],
    				
    				legend: {
        		        data: processArray
        		    },
        		    
        		    
    				
    	    	}); 
    		}
    	});
    	
    	
       
    });
</script>

</body>
</html>