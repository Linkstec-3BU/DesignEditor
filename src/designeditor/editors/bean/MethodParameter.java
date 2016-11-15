package designeditor.editors.bean;

import designeditor.util.StringUtil;

public class MethodParameter {
	private String parameter_id;
	private String parameter_name;
	private String paramter_type;
	private String comment;
	public String getParameter_id() {
		return StringUtil.NullToEmpty(parameter_id);
	}
	public void setParameter_id(String parameter_id) {
		this.parameter_id = parameter_id;
	}
	public String getParameter_name() {
		return StringUtil.NullToEmpty(parameter_name);
	}
	public void setParameter_name(String parameter_name) {
		this.parameter_name = parameter_name;
	}
	public String getParamter_type() {
		return StringUtil.NullToEmpty(paramter_type);
	}
	public void setParamter_type(String paramter_type) {
		this.paramter_type = paramter_type;
	}
	public String getComment() {
		return StringUtil.NullToEmpty(comment);
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
