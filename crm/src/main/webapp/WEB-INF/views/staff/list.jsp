<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>凯盛软件CRM-首页</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
 	<%@ include file="../include/css.jsp"%> 
 	<link rel="stylesheet" href="/static/plugins/tree/css/metroStyle/metroStyle.css" />
</head>
<body class="hold-transit ion skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

 <%@ include file="../include/header.jsp"%> 
<%--  <%@ include file="../include/sider.jsp"%>  --%>
<jsp:include page="../include/sider.jsp">
	<jsp:param value="stafflist" name="staff"/>
</jsp:include>
 
  <!-- =============================================== -->

  <!-- 右侧内容部分 -->
  <div class="content-wrapper">

    <!-- Main content -->
    <section class="content">

      <div class="row">
        <div class="col-md-2">
            <div class="box">
              <div class="box-body">
                <button id="addDepartment"class="btn btn-default">添加部门</button>
                <ul id="ztree" class="ztree"></ul>
              </div>
            </div>
        </div>
        <div class="col-md-10">
            <!-- Default box -->
            <div class="box">
              <div class="box-header with-border">
                <h3 class="box-title">员工管理</h3>
                <div class="box-tools pull-right">
                  <button type="button" id="addStaff" class="btn btn-box-tool"  title="Collapse">
                    <i class="fa fa-plus"></i> 添加员工</button>
                </div>
              </div>
              <div class="box-body">
                <table class="table">
                  <thead>
                    <tr>
                      <th>姓名</th>
                      <th>部门</th>
                      <th>手机</th>
                      
                    </tr>
                  </thead>
				  <tbody id="body">
            
				  </tbody>
                </table>
                
                <ul class="pagination pull-right" id="pagination"></ul>
                
              </div>
            </div>
            <!-- /.box -->
        </div>
      </div>

    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  
   <!-- 模态框，点击添加员工弹出 -->
  <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title" id="myModalLabel">新增员工</h4>
        </div>
        <div class="modal-body">
        	<form id="addStaffForm">
                     <div class="form-group">
                         <label>姓名</label>
                         <input type="text" class="form-control" name="staffName" id="staffName">
                     </div>
                     <div class="form-group">
                         <label>手机号码</label>
                         <input type="text" class="form-control" name="mobile" id="mobile">
                     </div>
                     <div class="form-group">
                         <label>密码(默认000000)</label>
                         <input type="password" class="form-control" name="password" value="000000">
                     </div>
                     <div class="form-group">
                         <label>所属部门</label>
                         <div id="checkboxList">
                         </div>
                     </div>
                 </form>
        </div>
        <div class="modal-footer">
          <button type="button" id ="backModal" ""class="btn btn-default" data-dismiss="modal">取消</button>
          <button type="button" class="btn btn-primary" id="saveBtn">保存</button>
        </div>
      </div>
    </div>
  </div>
  

  <!-- 底部 -->
  <%@ include file="../include/footer.jsp"%> 

</div>
<!-- ./wrapper -->

<%@ include file="../include/js.jsp"%> 
<script src="/static/plugins/tree/js/jquery.ztree.all.min.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
<script src="/static/plugins/jQuery/jquery.twbsPagination.js"></script>
<script>
  $(function(){
	 
	  //事件委托编辑staff
	   $(document).delegate(".edit","click",function(){
						
			var staffId = $(this).attr("rel");
			
			location.href = "/staff/edit?id=" + staffId;
					
		}); 
	  
	  //事件委托，删除staff
	  $(document).delegate(".del","click",function(){
	  		
	  		var staffId = $(this).attr("rel");
	  		
	  		layer.confirm('您确定要删除本员工么？', function(){
	  		
	  		$.ajax({
				url : '/staff/del',
				type : 'post',
				data : {
					'id' : staffId
				},
				success : function(data) {
					if (data.state == 0) {
						layer.alert("删除成功!");
						pageLoad(deptId);
					}
				}
			});
	  			
	  		});
	  		
	  	});
	  
	  /*
	   		以下是点击部门显示该部门的staff相关
	   */
	  var deptId = null;
	   /*初始化分页工具默认为20页  */
	  var $pagination = $("#pagination");
	  var defaultOpts = {totalPages : 20};
  	  $pagination.twbsPagination(defaultOpts);
	  
  	  pageLoad(deptId);/*执行加载页面的函数，初始deptId 是null在后台判断如果是null就返回所有  */
	  
	  
  	  function pageLoad(deptId){
  		  $.ajax({
  			  url:"/staff/list.json",
  			  type:'get',
  			  data:{
  				 "deptId":deptId 
  			  },
  		  	  success: function(data){
  		  		  var page = data.data;
  		  		  
  		  		  var totalPages = page.totalPage;
  		  		
  		  		  var currentPage = $pagination.twbsPagination('getCurrentPage');
  		  		  currentPage = (currentPage > totalPages ? totalPages : currentPage);
  		  		
  		  		  if(totalPages == 0){
		  			  totalPages = 1;
		  			  currentPage = 1;
		  		  }
  		  		  
  		  		$pagination.twbsPagination('destroy');
  		  		
  		  		$pagination.twbsPagination($.extend({},defaultOpts,{
  		  			startPage:currentPage,
  		  			totalPages:totalPages,
  		  			visiblePages:3,
  		  			first:'首页',
  		  			last:'末页',
  		  			prev:'上一页',
  		  			next:'下一页',
  		  			onPageClick:function(event,page){
  		  				load(deptId,page);
  		  			}
  		  			
  		  		}));/*分页插件传入自己设置显示方式的对象  */
  		  		
  		  	  }	/* ajax发起异步参数对象中的成功回调函数 */ 
  		 
  		  });/*ajax请求尾部  */
  	  }
  	  
   	  function load(deptId,p){
  		$.ajax({
	        url:"/staff/list.json",
	        type:"get",
	        data:{
	        	"deptId":deptId,
	        	"p":p
	        },
	        success: function (data) {
	        	var page = data.data;
	        	
	        	$("#body").html("");
	            for(var i = 0; i < page.items.length; i++) {
	            	var staff = page.items[i];
	            	if(staff){
	            		
	            	var html = "<tr> <td>" + staff.staffname+"</td> <td>" + staff.deptName + "</td> <td>"+staff.mobile+"</td> <td> <a href='javascript:;' class='del' rel='" + staff.id + "'>删除</a><a href='javascript:;' class='edit' rel='" + staff.id + "'>编辑</a></td></tr>";
	            	$("#body").append(html);
	            	}
	            }
	        }
	    });
  	  }
  	  
  	  /* 
  	  	以下是模态框相关
  	  */
	  $("#addStaff").click(function(){
		  
		 $.get("/dept/list").done(function(data){
			 for(var i = 0; i < data.length; i++) {
	    			var dept = data[i];
	    			if(dept.pId == 1) {
		    			var html = '<label class="checkbox-inline"><input type="checkbox" name="deptId" value="'+ dept.id +'">'+dept.deptName+'</label>'
		    			$("#checkboxList").append(html);
	    			}
	    		}
			 
			  $('#myModal').modal({
		            backdrop:'static',
		            keyboard: false,
		        });
		  
			  $('#myModal').on('hidden.bs.modal', function (e) {

				  $("#checkboxList").empty();
			    });
			  
			  /* $("#backModal").click(function(){
				  $("#checkboxList").empty();
			  }); */
			 
			  
			  
		 }).fail(function(){
			 layer.msg('系统异常');
		 });	
		  
	  });
	  
	  $("#saveBtn").click(function(){
		  
		  $("#addStaffForm").validate({
			  errorClass:'text-danger',
			  errorElement:'span',
			  rules:{
				 staffName: {required:true},
				 mobile: {required:true},
				 password: {required:true},
				 deptId: {required:true}
			  },
			  
			  messages :{
			  	 staffName : {required:"请填写员工姓名"},
				 mobile : {required:"请填写员工电话"},
				 password : {required:"请填写员工密码"},
				 deptId : {required:"请填写员工部门"}
			  },
			  
			  submitHandler:function(){
				  $.ajax({
					  url:'/satff/add',
					  type:'post',
					  data:$("#addStaffForm").serialize(),
					   beforeSend:function(){
							$("#saveBtn").text("保存中...").attr('disabled','disabled');
						}, 
					  success:function(data){
						  if(data.state == '0'){
							  layer.alert("保存成功");
							  $('#myModal').modal('hide');
							  
							  $("#staffName").val("");
							  $("#mobile").val("");
							  $("#checkboxList").empty();
							  
							  pageLoad(data.data[0]); 
						  }
						  if(data.state == '40'){
							  layer.alert("系统繁忙，请骚后添加");
							  $("#staffName").val("");
							  $("#mobile").val("");
							  $("#checkboxList").empty();
							  $('#myModal').modal('hide');	 
							  
						  }
					  },
					  error:function(){layer.alert("系统异常")},
					   
					  complete:function(){
						  $("#saveBtn").text("保存").removeAttr('disabled');
					  } 
				  });
			  }
			  
		  });
		  
		  $("#addStaffForm").submit();
	  });
	  
	  
	  /*
	  	以下是ztree相关
	  */
	  $("#addDepartment").click(function(){
		layer.prompt({title:'请输入部门名称'},function(text,index){
			layer.close(index);
			$.post("/dept/add",{'deptName':text}).done(function(data){
				
				if(data.state == '0'){
					
					layer.msg("添加成功");
					var treeObj  = $.fn.zTree.getZTreeObj("ztree");
					treeObj.reAsyncChildNodes(null,"refresh");
					
				}	
				if(data.state == '40'){
				}
				
			}).fail(function(){
				layer.alert('系统繁忙，骚后再试');
			});
				
			});
				
		  });
	  
	    var setting = {
				data: {
					simpleData: {
						enable: true
					},
					key:{
						name:'deptName',
					}
				},
				
				async:{
					enable:true,
					url:'/dept/list',
					type:'get',
					
					//用于对 Ajax 返回数据进行预处理的函数
					dataFilter : function(treeId,parentNode,responseData){
			    		if(responseData){
			    			for(var i = 0; i < responseData.length;i++){
			    				if(responseData[i].id == 1){
			    					responseData[i].open = true;
			    				}
			    			}
			    		}
			    		return responseData;
			    	}
				},
			
					//用于捕获节点被点击的事件回调函数
				 callback:{
					
					 //treeId 对应 zTree 的 treeId，便于用户操控
					 //treeNode  	被点击的节点 JSON 数据对象	
					 
					 onClick:function(event,treeId,treeNode){
						 /* alert(treeNode.id + treeNode.deptName + treeNode.pId) ; */
						 
						deptId = treeNode.id;
						 
						if(deptId == 1) {
							deptId = null;
						}
						
						pageLoad(deptId);
						
					}
				 }
			};
	    
    	 $.fn.zTree.init($("#ztree"), setting);
  });
</script>
</body>
</html>
    