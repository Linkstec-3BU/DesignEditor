package designeditor.editors.bean;

import designeditor.util.StringUtil;

public class MethodParameter {
	// プロジェクト名
	private String projectId;
	// パケッジ名
	private String packageId;
	// モジュール物理名
	private String moduleId;
	private String methodId;

	private String parameterId;
	private String parameterName;
	private String paramterType;

	private String comment;
	
	public MethodParameter(){
		this.parameterId = "";
		this.parameterName = "";
		this.paramterType = "";
		this.comment = "";
	}

	public String getParameterId() {
		return parameterId;
	}

	public void setParameterId(String parameterId) {
		this.parameterId = parameterId;
	}

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public String getParamterType() {
		return paramterType;
	}

	public void setParamterType(String paramterType) {
		this.paramterType = paramterType;
	}

	public String getComment() {
		return StringUtil.NullToEmpty(comment);
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getMethodId() {
		return methodId;
	}

	public void setMethodId(String methodId) {
		this.methodId = methodId;
	}

}
