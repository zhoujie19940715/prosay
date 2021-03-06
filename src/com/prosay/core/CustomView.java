package com.prosay.core;

/**
 * @author Jame
 * @ClassName: CustomView
 * @Description: 视图模型
 * @date 2017年12月18日 下午10:00:50
 */
public class CustomView {
    private ReturnContent content;
    private ReturnType returnType;

    public CustomView(ReturnContent content) {
        this.content = content;
        if (content.getUrl() == null) {
            this.returnType = ReturnType.JSON;
        } else {
            this.returnType = ReturnType.FORWORD;
        }
    }

    public CustomView(String viewName) {
        content = new ReturnContent(viewName);
        returnType = ReturnType.FORWORD;
    }

    public ReturnContent getContent() {
        return content;
    }

    public void setContent(ReturnContent content) {
        this.content = content;
    }

    public ReturnType getReturnType() {
        return returnType;
    }

    public void setReturnType(ReturnType returnType) {
        this.returnType = returnType;
    }

    public CustomView(ReturnContent content, ReturnType returnType) {
        this.content = content;
        this.returnType = returnType;
    }
}
