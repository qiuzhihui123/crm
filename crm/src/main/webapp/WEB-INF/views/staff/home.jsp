<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>欢迎来到主页</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	
	<style>
		#space{
			margin-right:320px;
		}
		
		#video{
			margin-left:190px;
			margin-bottom:50px;
		}
	</style>
	
	<%@ include file="../include/css.jsp" %>
	<link href="/static/plugins/video/css/video-js.min.css" rel="stylesheet" >
		
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

  <!-- 顶部导航栏部分 -->
  <%@ include file="../include/header.jsp" %>
  <!-- =============================================== -->

  <!-- 左侧菜单栏 -->
  <%@ include file="../include/sider.jsp" %>

  <!-- =============================================== -->

  <!-- 右侧内容部分 -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1 style="display:inline">
        Blank page
        <small>it all starts here</small>
      </h1>
      <span id="space"></span>
      
      
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li><a href="#">Examples</a></li>
        <li class="active">Blank page</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">

      <!-- Default box -->
      <div class="box">
        <div class="box-header with-border">
          <h3 class="box-title">欢迎来到CRM系统</h3>

          <div class="box-tools pull-right">
            <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip" title="Collapse">
              <i class="fa fa-minus"></i></button>
            <button type="button" class="btn btn-box-tool" data-widget="remove" data-toggle="tooltip" title="Remove">
              <i class="fa fa-times"></i></button>
          </div>
        </div>
        <div class="box-body">
        	
        </div>
        
       	<!-- 视频播放 -->
        
        <video id="video" class="video-js vjs-default-skin vjs-big-play-centered" controls preload="none" width="640" height="264"
		      
		      data-setup="{}">
		    <source src="/static/video/test.mp4" type='video/mp4' />
		</video>	
        
        <!-- 视频播放底部 -->
        
        <!-- /.box-body -->
        <div class="box-footer">
          	
        </div>
        	
			
        <!-- /.box-footer-->
      </div>
      <!-- /.box -->

    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  <!-- 底部 -->
 <%@ include file="../include/footer.jsp" %>
<!-- ./wrapper -->

<%@ include file="../include/js.jsp" %>
<script src="/static/plugins/video/js/video.min.js"></script>

<script type="text/javascript">
    var myPlayer = videojs('video');
    videojs("video").ready(function(){
        var myPlayer = this;
        myPlayer.play();
    });
    
    var myPlayer = videojs("video");
    
    myPlayer.height(400)
    
</script>

</body>
</html>
