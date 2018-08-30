<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>登入 - layuiAdmin</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
<link rel="stylesheet" href="${basePath}resource/login/admin.css" media="all">
<link rel="stylesheet" href="${basePath}resource/layui/css/layui.css" media="all">
<link rel="stylesheet" href="${basePath}resource/login/login.css" media="all">
</head>
<body>
				<p>layui 官方出品的单页面后台管理模aaaa板系统</p>
	<div class="layadmin-user-login layadmin-user-display-show" id="LAY-user-login" style="display: none;">
		<div class="layadmin-user-login-main">
			<div class="layadmin-user-login-box layadmin-user-login-header">
				<h2>layuiAdmin</h2>
			</div>
			<div >
				<div class="layui-form-item">
					<label class="layadmin-user-login-icon layui-icon layui-icon-username"></label> 
						<input type="text" id="username" name="username" id="LAY-user-login-username" lay-verify="required" placeholder="用户名" class="layui-input">
				</div>
				<div class="layui-form-item">
					<label class="layadmin-user-login-icon layui-icon layui-icon-password"></label> 
						<input type="text" id="password" name="password" lay-verify="required" placeholder="密码" class="layui-input">
				</div>
				<div class="layui-form-item">
					<button class="layui-btn layui-btn-fluid" id="submit">登 入</button>
				</div>
			</div>
		</div>
	</div>
	<script src="${basePath}resource/layui/layui.all.js"></script>
	<script>
		//一般直接写在一个js文件中
		layui.use([ 'layer', 'form' ], function() {
			var layer = layui.layer, form = layui.form;
			 var $ = layui.jquery;
			 debugger
			 $("#submit").on("click",function(){
			 	debugger
			 	 $.ajax({
			        	url:"${basePath}/checkLogin?loginNo="+$("#username").val()+"&password="+$("#password").val(),
			        	type:"post",
			        	dataType:"json",
			        	beforeSend:function(){
			        		layer.msg('开始登录，请注意后台控制台。');
			        	},
			        	success:function(result){
				        	layer.close(load);
				    		if(result && result.status != 200){
				    			layer.msg(result.message,function(){});
				    			$('.password').val('');
				    			return;
				    		}else{
				    			layer.msg('登录成功！');
				    			setTimeout(function(){
				    				//登录返回
					    			window.location.href= result.back_url || "${basePath}/";
				    			},1000)
				    		}
			        	},
			        	error:function(e){
			        		console.log(e,e.message);
			        		layer.msg('请看后台Java控制台，是否报错，确定已经配置数据库和Redis',new Function());
			        	}
			        });
				 window.location.href="checkLogin";
			 })
		});
	</script>
</body>
</html>