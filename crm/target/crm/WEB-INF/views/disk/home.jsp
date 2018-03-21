<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>凯盛软件CRM-首页</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <%@ include file="../include/css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/uploader/webuploader.css">
    <style>
        tr{
            height: 50px;
            line-height: 50px;
        }
        .table>tbody>tr>td{
            vertical-align: middle;
        }
        .file_icon {
            font-size: 30px;
        }
        .table>tbody>tr:hover{
            cursor: pointer;
        }
        
        .webuploader-container {
            display: inline-block;
        }
        .webuploader-pick {
            padding: 6.2px 15px;
            overflow: visible;
            font-size: 12px;
            line-height:1.5;
            font-weight: 400;
        }
    </style>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">
 	<%@ include file="../include/header.jsp"%>
   <jsp:include page="../include/sider.jsp">
   		<jsp:param value="disk" name="menu"/>
   	</jsp:include>
    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">${disk.name }</h3>

                    <div class="box-tools pull-right">
                    	<c:if test="${not empty disk}">
	                        <a href="/disk/home?pid=${disk.pid}" class="btn btn-default btn-sm"><i class="fa fa-arrow-left"></i> 返回上一级</a>
                    	</c:if>
                    	
                    	<c:if test="${disk.type != 'file' }">
	                    	<div id="picker"><i class="fa fa-upload"></i> 上传文件</div>
	                        <button id="addFolderBtn" class="btn btn-success btn-sm"><i class="fa fa-plus"></i> 新建文件夹</button>
                    	
                    	</c:if>
                    </div>
                </div>
                <div class="box-body no-padding">

                    <table class="table table-hover">
                    
                    <c:choose>
                    		<c:when test="${disk.type == 'file' }">
                    			<tr>
                    				<td colspan="4">
                    					<c:choose>
                    						<c:when test="${disk.name.endsWith('.pdf') or disk.name.endsWith('.jpg') or disk.name.endsWith('.gif') or disk.name.endsWith('.jpeg')}">
				                    			<a href="/disk/download?id=${disk.id}" target="_blank" class="btn btn-default btn-sm"><i class="fa fa-search"></i> 预览</a>
				                    			<a href="/disk/download?id=${disk.id}&fileName=${disk.name}" class="btn btn-info btn-sm"><i class="fa fa-download"></i> 下载</a>          
                    						</c:when>
                    						<c:otherwise>
				                    			<a href="/disk/download?id=${disk.id}&fileName=${disk.name}" class="btn btn-info btn-sm"><i class="fa fa-download"></i> 下载</a>          
                    						</c:otherwise>
                    					
                    					</c:choose>
                    				</td>
                    			</tr>
                    		</c:when>
                    
                    	<c:otherwise>
                    	<c:if test="${empty diskList }">
                    		<tr>
                    			<td colspan="5">暂无内容</td>
                    		</tr>
                    	</c:if>
                    
                    	<c:forEach items="${diskList}" var="disk">
	                        <tr  rel="${disk.id }" >
	                        	<c:choose>
	                        		<c:when test="${disk.type == 'dir' }">
			                            <td width="50" class="file_icon tr"><i class="fa fa-folder-o"></i></td>
	                        		</c:when>
	                        		
	                        		<c:otherwise>
			                            <td width="50" class="file_icon tr"><i class="fa fa-file-o"></i></td>
	                        		</c:otherwise>
	                        	</c:choose>
	                        	
	                            <td class="tr">${disk.name}</td>  <%-- HH:mm:ss --%>
	                            <td class="tr"><fmt:formatDate value="${disk.updateTime}" pattern="yyyy年MM月dd日 "/></td>
	                            <td class="tr" width="100">${disk.fileSize}</td>
	                            <td  width="150">
	                                <div class="btn-group">
	                                    <button type="button" class="btn btn-default dropdown-toggle" id="toggle"data-toggle="dropdown">
	                                        <i class="fa fa-ellipsis-h"></i>
	                                    </button>
	                                    <ul class="dropdown-menu">
		                                    <c:choose>
				                        		<c:when test="${disk.type == 'dir' }">
						                            <li><a href="/disk/home?pid=${disk.id}">打开</a></li>
				                        		</c:when>
				                        		<c:otherwise>
						                           <li><a href="/disk/download?id=${disk.id}&fileName=${disk.name}">下载</a></li>
				                        		</c:otherwise>
				                        	</c:choose>
	                                        
	                                        <li ><a href="javascript:;" class="rename" rel="${disk.id}">重命名</a></li>
	                                        <li><a href="javascript:;" class="delete" rel="${disk.id}">删除</a></li>
	                                    </ul>
	                                </div>
	                            </td>
	                        </tr>
                    	</c:forEach>
                   	</c:otherwise>
               	</c:choose>
               </table>
              </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <!-- 底部 -->
 <%@ include file="../include/footer.jsp"%>
</div>
<!-- ./wrapper -->

 <%@ include file="../include/js.jsp"%>
 <script  src="/static/plugins/uploader/webuploader.js"></script>
 <script>
 	$(function(){
 		
 		 $(".delete").click(function(){
 			var diskId = $(this).attr("rel");
 			
 			layer.confirm("确认要删除该文件夹吗",function(){
 				
 				$.post("/disk/delete",{'diskId' : diskId}).done(function(data){
 					
 					if(data.state == '0'){
 						var pid = data.data
 						layer.msg('删除成功');
 						location.href="/disk/home?pid="+pid;
 					}	
 					if(data.state == '40'){
 						layer.alert('系统繁忙，骚后再试');
 					}
 					
 				}).fail(function(){
 					layer.alert('系统繁忙，骚后再试');
 				});
 					
 				});
 					
 			  }); 
 		
 		$(".rename").click(function(){
 			var diskId = $(this).attr("rel");
 			
 			layer.prompt({title:'请输入修改名称'},function(text,index){
 				layer.close(index);
 				$.post("/disk/rename",{'diskName':text,'diskId' : diskId}).done(function(data){
 					
 					if(data.state == '0'){
 						layer.msg("修改成功");
 						history.go(0);
 					}	
 					if(data.state == '40'){
 						layer.alert('系统繁忙，骚后再试');
 					}
 					
 				}).fail(function(){
 					layer.alert('系统繁忙，骚后再试');
 				});
 					
 				});
 					
 			  });
 		
 		
 		//新建文件夹
 		var pid = "${requestScope.disk == null? '0' : requestScope.disk.id}";
 		$("#addFolderBtn").click(function(){
 			layer.prompt({title:"请输入文件夹名称:"},function(text, index){
 				layer.close(index);
 				$.post("/disk/new/folder",{"pid":pid, "name":text}).done(function(resp){
                    if(resp.state == '0') {
                        layer.msg("创建成功");
                        history.go(0);
                    } else {
                        layer.msg(resp.message);
                    }
                }).error(function(){
                    layer.msg("服务器异常");
                });
 			});
 		});
 		
 	 		//点击tr	
 			$(".tr").click(function(){
 	 			
 	 			var pid = $(this).parent().attr("rel");
 	 				
 	 			window.location.href = "/disk/home?pid=" + pid;
 	 			
 	 		});
 			
 			//上传
 			var uploader = WebUploader.create({
 	            pick:"#picker",
 	            swf:'/static/plugins/uploader/Uploader.swf',
 	            server:'/disk/upload', 
 	            auto: true, 
 	            fileVal:'file', 
 	            formData:{
 	                "pid":pid
 	            } 
 	        });
 		 
 			var loadIndex = -1;//用于关闭layer
 			
 			//开始
 			uploader.on('uploadStart',function (file) {
 	            loadIndex = layer.load(2); 
 	        });
 			
 			//成功
 	        uploader.on('uploadSuccess',function (file,resp) {
 	            if(resp.state == '0') {
 	                layer.msg("文件上传成功");
 	                history.go(0);
 	            }
 	        });
 			
 	     //失败
 	        uploader.on('uploadError',function (file) {
 	            layer.msg("上传失败，服务器异常");
 	        });
 	     
 	     //finally
 	        uploader.on('uploadComplete',function (file) {
 	            layer.close(loadIndex);
 	        });
 		
 	})
 
 </script>
</body>
</html>
