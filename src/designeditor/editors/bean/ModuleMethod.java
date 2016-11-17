package designeditor.editors.bean;

import java.util.List;

import designeditor.util.StringUtil;

public class ModuleMethod {
	private String method_id;
	private String method_id_name;
	private String method_return_type;
	private String method_throws_1;
	private String comment;
	private List<MethodParameter> methodParameter;
	private List<MethodDesign> methodDesignList;
	
	public List<MethodDesign> getMethodDesignList() {
		return methodDesignList;
	}
	public void setMethodDesignList(List<MethodDesign> methodDesignList) {
		this.methodDesignList = methodDesignList;
	}
	public List<MethodParameter> getMethodParameter() {
		return methodParameter;
	}
	public void setMethodParameter(List<MethodParameter> methodParameter) {
		this.methodParameter = methodParameter;
	}
	public String getMethod_id() {
		return StringUtil.NullToEmpty(method_id);
	}
	public void setMethod_id(String method_id) {
		this.method_id = method_id;
	}
	public String getMethod_id_name() {
		return StringUtil.NullToEmpty(method_id_name);
	}
	public void setMethod_id_name(String method_id_name) {
		this.method_id_name = method_id_name;
	}
	public String getMethod_return_type() {
		return StringUtil.NullToEmpty(method_return_type);
	}
	public void setMethod_return_type(String method_return_type) {
		this.method_return_type = method_return_type;
	}
	public String getMethod_throws_1() {
		return StringUtil.NullToEmpty(method_throws_1);
	}
	public void setMethod_throws_1(String method_throws_1) {
		this.method_throws_1 = method_throws_1;
	}
	public String getComment() {
		return StringUtil.NullToEmpty(comment);
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
