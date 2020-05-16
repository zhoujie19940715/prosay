<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.prosayedu.com/prosay/core" prefix="page" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title></title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        <link rel="stylesheet" href="${basePath}/static/content/ui/global/bootstrap/css/bootstrap.min.css">
        <link href="${basePath}/static/content/ui/global/font-awesome/css/font-awesome.css" rel="stylesheet" />
        <link rel="stylesheet" href="${basePath}/static/content/adminlte/dist/css/AdminLTE.css">
        <link rel="stylesheet" href="${basePath}/static/content/adminlte/dist/css/skins/_all-skins.min.css">
        <link href="${basePath}/static/content/min/css/supershopui.common.min.css" rel="stylesheet" />
        <link href="${basePath}/static/content/plugins/bootstrap-table/bootstrap-table.css" rel="stylesheet" />
         <link href="${basePath}/static/content/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css" />
    <!--全局通用框架样式 end-->
        </head>
<body>
    <section class="content-header">
        <h1>
             会员信息列表
            <small>会员信息的查询与管理</small>
        </h1>
    </section>
    <section class="content">
        <div class="row">
                <div class="col-md-12">
                    <!-- BEGIN SAMPLE TABLE PORTLET-->
                    <div class="box-body" style="padding-bottom:0px;">
                        <div class="panel panel-default">
                            <div class="panel-heading">查询条件</div>
                            <div class="panel-body">
                                <form id="formSearch" class="form-horizontal">
                                	<input type="hidden" name="currentPage" id="currentPage"/>
                                    <div class="form-group" style="margin-top:15px">
                                        <label class="control-label col-sm-1" for="vip_name">会员昵称</label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control" name="vip_name" value="${page.pd.vip_name}" id="vip_name">
                                        </div>
                                        <label class="control-label col-sm-1" for="txt_search_statu">最终登录时间</label>
                                        <div class="col-sm-2">
                                             <div class="input-group date form_datetime input-large">
                                                    <input type="text" name="vip_starttime" value="${page.pd.vip_starttime }"  size="16" readonly class="form-control">
                                                    <span class="input-group-btn">
                                                        <button class="btn default date-set" type="button">
                                                            <i class="fa fa-calendar"></i>
                                                        </button>
                                                    </span>
                                                </div>
                                        </div>
                                        <div class="col-sm-2">
                                        	 <div class="input-group date form_datetime input-large">
                                                    <input type="text" name="vip_endtime" value="${page.pd.vip_endtime }"  size="16" readonly class="form-control">
                                                    <span class="input-group-btn">
                                                        <button class="btn default date-set" type="button">
                                                            <i class="fa fa-calendar"></i>
                                                        </button>
                                                    </span>
                                                </div>
                                        </div>
                                        <div class="col-sm-3" style="text-align:left;">
                                            <button type="button" style="margin-left:50px" id="btn_query" class="btn btn-primary">查询</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>

                        <div id="toolbar" class="btn-group">
                            <button id="btn_delete" type="button" class="btn btn-default">
                                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
                            </button>
                        </div>
                        <div class="table-scrollable  fixed-table-container">
                            <table class="table-striped table-hover table" id="table">
                                <thead>
                                    <tr>
                                        <th data-checkbox="true"><input id="checkAll" type="checkbox"/></th>
                                        <th>会员id</th>
                                        <th>会员昵称</th>
                                        <th>会员帐号</th>
                                        <th>会员密码</th>
                                        <th>性别</th>
                                        <th>真实姓名</th>
                                        <th>联系方式</th>
                                        <th>出生日期</th>
                                        <th>会员等级</th>
                                        <th>会员积分</th>
                                        <th>会员邮箱</th>
                                        <th>会员注册时间</th>
                                        <th>最后登录IP</th>
                                        <th>最后登录时间</th>
                                    </tr>
                                </thead>
                                <c:forEach items="${page.data}" var="vip">
                                <tr>
                                	<td><input type="checkbox" name="checkId" value="${vip.vip_id}"/></td>
                                	<td>${vip.vip_id}</td>
                                	<td>${vip.vip_name}</td>
                                	<td>${vip.vip_account}</td>
                                	<td>${vip.vip_password}</td>
                                	<td>${vip.vip_sex}</td>
                                	<td>${vip.vip_realname}</td>
                                	<td>${vip.vip_tel}</td>
                                	<td>${vip.vip_birthday}</td>
                                	<td>${vip.vip_level}</td>
                                	<td>${vip.vip_score}</td>
                                	<td>${vip.vip_email}</td>
                                	<td>${vip.vip_regtime}</td>
                                	<td>${vip.vip_loginip}</td>
                                	<td>${vip.vip_logintime}</td>
                                	<td>
                                	<a href="javascript:edit(${vip.vip_id});" class="btn btn-icon-only purple"><i class="fa fa-edit"></i></a>
                                	<a href="javascript:del(${vip.vip_id});" class="btn btn-icon-only red"> <i class="fa fa-times"></i></a>
                                	</td>
                                </tr>
                                </c:forEach>
                                <tr>
                                	<td colspan=7 align=right><page:page pageSize="${page.pageSize}" total="${page.total }" currentPage="${page.currentPage}" formId="formSearch"/></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
        </div>
    </section>
<script src="${basePath}/static/content/ui/global/jQuery/jquery.min.js"></script>
<script src="${basePath}/static/content/ui/global/bootstrap/js/bootstrap.min.js"></script>
<script src="${basePath}/static/content/min/js/supershopui.common.js"></script>
<script src="${basePath}/static/content/plugins/bootstrap-table/bootstrap-table.js"></script>
<script src="${basePath}/static/content/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
<script src="${basePath}/static/content/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="${basePath}/static/content/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${basePath}/prosay/common.js" type="text/javascript"></script>
</body>
<script type="text/javascript">
function edit(id){
 	 $.fn.modalOpen({
          id: "vipForm",
          title: '会员信息修改',
          url: 'vipEdit.do?vip_id='+id,
          width: "750px",
          height: "550px",
          callBack: function (iframeId) {
              var obj = document.getElementById(iframeId);
              obj.contentWindow.AcceptClick();
          }
      });
 }
function del(id){
  $.fn.modalConfirm("确定删除吗？",function(result){
	   if(result){
		   $.ajax({
				type:"post",
				url:"delVip.do",
				data:{"vip_id":id},
				success:function(result){
					var strs = result.split("|");
					if(result.indexOf("error")!=-1){
						$.fn.modalAlert(strs[1],"error");
					}else{
						$.fn.modalAlert(strs[1],"success",function(index){
							top.layer.closeAll();
							$("#btn_query").click();
							return true;
						});
					}
				}
			});
	   }
  });
}
$("#btn_delete").click(function(){
	 	var ids = "";
	   //遍历获取所有的已经选择id
	 $("input[name=checkId]").each(function(){
		 var tmp = $(this);
		 if(tmp.prop("checked")){
			ids+=tmp.val()+",";	
		 }
		
	});
  console.log(ids+":"+ids.length);
  if(ids.length<=0){
	   $.fn.modalAlert("没有选择任何会员！","warning");
	   return ;
  }
  ids = ids.substring(0,ids.length-1);
	  $.fn.modalConfirm("确定删除已选吗？",function(result){
	   if(result){
		   $.ajax({
				type:"post",
				url:"batchDelVip.do",
				data:{"ids":ids},
				success:function(result){
					var strs = result.split("|");
					if(result.indexOf("error")!=-1){
						$.fn.modalAlert(strs[1],"error");
					}else{
						$.fn.modalAlert(strs[1],"success",function(index){
							top.layer.closeAll();
							$("#btn_query").click();
							return true;
						});
					}
				}
			});
	   }
  });
});
</script>

</html>