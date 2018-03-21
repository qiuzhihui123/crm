<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>凯盛软件CRM-首页</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	<%@ include file="../include/css.jsp" %>	
		
    <style>
        .td_title {
            font-weight: bold;
        }
    </style>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@ include file="../include/header.jsp" %>

    <!-- =============================================== -->
	<jsp:include page="../include/sider.jsp">
  		<jsp:param value="customerspub" name="customer"/>
  </jsp:include> 
    
    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">

            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">客户资料</h3>
                    <div class="box-tools">
						<a class="btn btn-primary btn-sm" href="/customer/public/list"><i class="fa fa-arrow-left"></i> 返回列表</a>
                        <a class="btn bg-purple btn-sm" href="/customer/public/edit?custId=${cust.id }"><i class="fa fa-pencil"></i> 编辑</a>
                        <button class="btn bg-maroon btn-sm" id="pubBtn"><i class="fa fa-recycle"></i> 认领</button>
                        <button class="btn btn-danger btn-sm"id="delBtn"><i class="fa fa-trash-o"></i> 删除</button>
                    </div>
                </div>
                <div class="box-body no-padding">
                    <table class="table">
                        <tr>
                            <td class="td_title">姓名</td>
                            <td>${cust.custName}</td>
                            <td class="td_title">职位</td>
                            <td>${cust.jobTitle}</td>
                            <td class="td_title">联系电话</td>
                            <td>${cust.mobile}</td>
                        </tr>
                        <tr>
                            <td class="td_title">所属行业</td>
                            <td>${cust.trade}</td>
                            <td class="td_title">客户来源</td>
                            <td>${cust.source}</td>
                            <td class="td_title">级别</td>
                            <td>${cust.level}</td>
                        </tr>
                        <tr>
                            <td class="td_title">地址</td>
                            <td colspan="5">${cust.address}</td>
                        </tr>
                        <tr>
                            <td class="td_title">备注</td>
                            <td colspan="5">${cust.mark}</td>
                        </tr>
                    </table>
                </div>
                <div class="box-footer">
                    <span style="color: #ccc" class="pull-right">创建日期：<fmt:formatDate value="${cust.createTime}" pattern="yyyy年MM月dd日"/> &nbsp;&nbsp;&nbsp;&nbsp;
                        最后修改日期：<fmt:formatDate value="${cust.updateTime}" pattern="yyyy年MM月dd日" /></span>
                </div>
            </div>

            <div class="row">
                <div class="col-md-8">
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title">跟进记录</h3>
                        </div>
                        <div class="box-body">

                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title">日程安排</h3>
                        </div>
                        <div class="box-body">

                        </div>
                    </div>
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title">相关资料</h3>
                        </div>
                        <div class="box-body">

                        </div>
                    </div>
                </div>
            </div>

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <!-- 底部 -->
    
    <!-- 转交给其他员工的选择模态框 -->
	<div class="modal fade" id="chooseStaff" tabindex="-1" role="dialog">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title">转交客户</h4>
	      </div>
	      <div class="modal-body">
	       		
	       		<select id="staffList" class="form-control" style="width:100%">
		       		<c:forEach items="${staffList}" var="staff">
		       			<c:if test="${staff.id != 1 and staff.id != cust.staffId}">
			       			<option value="${staff.id}">${staff.staffname} (${staff.mobile})</option>
			       		</c:if>
		       		</c:forEach>
	       		</select>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	        <button type="button" class="btn btn-primary" id="saveBtn">保存</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
    
<%@ include file="../include/footer.jsp" %>
<!-- ./wrapper -->

<%@ include file="../include/js.jsp" %>

	<script>
	 	$(function(){
	 		var custId = ${cust.id};
	 		
	 		$("#delBtn").click(function(){
	 			layer.confirm("确定要删除该公海客户么？",function(){
	 				layer.close();
					location.href="/customer/public/del?custId=" + custId;
	 			});
	 		});
	 		
	 		
	 		/* $("#transBtn").click(function(){
				$("#chooseStaff").modal({
					backdrop : 'static',
					show : true
				})
			}); */
			
			
		
	 		$("#pubBtn").click(function(){
	 			layer.confirm("确定要认领该客户吗?",function(){
					layer.close();
					location.href="/customer/public/my?custId=" + custId;					
				});
	 			
	 		});
	 	})
	</script>

</body>
</html>
