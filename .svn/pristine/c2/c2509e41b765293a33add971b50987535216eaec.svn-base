package com.prosay.tag;

import java.io.IOException;

import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * 自定义的分页标签
 * @author jame
 *
 */
public class PageTag extends BodyTagSupport{
	private int currentPage;//当前页
	private int pageSize;//每页的总体数
	private int total;//总记录数
	private String formId;//当分页是查询表单时需要传递他的id
	private final static String FIRSTPAGE  = "<a href=\"javascript:void(0)\" class='btn btn-default btn-sm ' onclick=\"doPage(1)\">首页</a> ";
	private final static String PREPAGE  = "<a href=\"javascript:void(0)\" class='btn btn-default btn-sm ' onclick=\"doPage({PAGE})\">上一页</a> ";
	private final static String NEXTPAGE  = "<a href=\"javascript:void(0)\" class='btn btn-default btn-sm ' onclick=\"doPage({PAGE})\">下一页</a> ";
	private final static String LASTPAGE  = "<a href=\"javascript:void(0)\" class='btn btn-default btn-sm ' onclick=\"doPage({PAGE})\">尾页</a> ";
	private final static String TURNPAGE  = "<input type='text' id='turn'/><a class='btn btn-default btn-sm ' href=\"javascript:void(0)\" onclick=\"doPage(-1)\">跳转</a> ";
	public int doStartTag(){
		StringBuilder html = new StringBuilder();
		int totalPage = total/pageSize;
		if(total%pageSize!=0){
			totalPage++;
		}
		html.append("当前第"+currentPage+"页,共"+totalPage+"页    ");
		if(totalPage>1){//如果列表只有一页不需要出现分页的内容
			if(1==currentPage){
				html.append(NEXTPAGE.replace("{PAGE}",""+(currentPage+1)));
				html.append(LASTPAGE.replace("{PAGE}",""+totalPage));				
			}else if(currentPage==totalPage){
				html.append(FIRSTPAGE);
				html.append(PREPAGE.replace("{PAGE}",""+(currentPage-1)));
				
			}else{
				html.append(FIRSTPAGE);
				html.append(PREPAGE.replace("{PAGE}",""+(currentPage-1)));
				html.append(NEXTPAGE.replace("{PAGE}",""+(currentPage+1)));
				html.append(LASTPAGE.replace("{PAGE}",""+totalPage));		
			}
			html.append(TURNPAGE);
		}
		html.append("<script type='text/javascript'>");
		html.append("function doPage(pageIndex){");
		html.append("if(pageIndex==-1){");
		html.append("var turn = document.getElementById('turn').value;");
		html.append("try{");
		html.append("pageIndex = parseInt(turn);");
		html.append("if(0>pageIndex||pageIndex>"+totalPage+"){");
		html.append("alert('页数超出范围！');");
		html.append("return;");
		html.append("}");
		html.append("}catch(e){");
		html.append("alert('输入的跳转页数不合法！');");
		html.append("return ;");
		html.append("}");
		html.append("}");
		html.append("var url = window.location.href;");
		html.append("var form = document.getElementById('"+formId+"');");
		html.append("if(form){");
		//约定要实现分页的查询表单需要增加一个隐藏域currentPage
		html.append("document.getElementById('currentPage').value = pageIndex;");
		html.append("form.submit();");
		html.append("}else{");
		html.append("if(url.indexOf('currentPage')!=-1){");
		html.append("url = url.replace(/currentPage=[0-9]+/,'currentPage='+pageIndex);");
		html.append("}else if(url.indexOf('?')!=-1){");
		html.append("url = url +='&currentPage='+pageIndex;");
		html.append("}else{");
		html.append("url+='?currentPage='+pageIndex;");
		html.append("}");
		html.append("window.location.href=url;");
		html.append("}");
		html.append("}");
		html.append("</script>");
		try {
			this.pageContext.getOut().println(html.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.SKIP_BODY;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getFormId() {
		return formId;
	}
	public void setFormId(String formId) {
		this.formId = formId;
	}
	
}
