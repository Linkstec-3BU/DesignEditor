package designeditor.editors.bean;

import java.util.ArrayList;
import java.util.List;

import designeditor.util.StringUtil;

public class ModuleMethod {
	
	private String methodUniqueId;
	// プロジェクト名
	private String projectId;
	// パケッジ名
	private String packageId;
	// モジュール物理名
	private String moduleId;
	private String methodId;
	private String methodIdName;
	private String methodReturnType;
	private String methodThrows1;
	private String comment;
	private List<MethodParameter> methodParameter;
	private List<MethodDesign> methodDesignList;

	public ModuleMethod() {
		this.methodId = "";
		this.methodIdName = "";
		this.methodReturnType = "";
		this.methodThrows1 = "";
		this.comment = "";
		this.methodParameter = new ArrayList<MethodParameter>();
		this.methodDesignList = new ArrayList<MethodDesign>();
	}
	
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

	public String getMethodId() {
		return methodId;
	}

	public void setMethodId(String methodId) {
		this.methodId = methodId;
	}

	public String getMethodIdName() {
		return methodIdName;
	}

	public void setMethodIdName(String methodIdName) {
		this.methodIdName = methodIdName;
	}

	public String getMethodReturnType() {
		return methodReturnType;
	}

	public void setMethodReturnType(String methodReturnType) {
		this.methodReturnType = methodReturnType;
	}

	public String getMethodThrows1() {
		return methodThrows1;
	}

	public void setMethodThrows1(String methodThrows1) {
		this.methodThrows1 = methodThrows1;
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

	public String getMethodUniqueId() {
		return methodUniqueId;
	}

	public void setMethodUniqueId(String methodUniqueId) {
		this.methodUniqueId = methodUniqueId;
	}
	
	
}
