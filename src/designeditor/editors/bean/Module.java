package designeditor.editors.bean;

import java.util.ArrayList;
import java.util.List;

import designeditor.util.StringUtil;

public class Module {
	// プロジェクト名
	private String projectId;
	// パケッジ名
	private String packageId;
	//モジュール物理名
	private String moduleId;
	//モジュール論理名
	private String moduleIdName;
	//モジュールタイプ
	private String moduleType;
	//コメント
	private String comment;
	
	private List<ModuleMethod> moduleMethod;
	
	public Module() {
		this.moduleId = "";
		this.moduleIdName = "";
		this.moduleType = "";
		this.comment = "";
		this.moduleMethod = new ArrayList<ModuleMethod>();
	}
	
	public Module(String projectId,String packageId,String moduleId, String moduleIdName, String moduleType,
			String comment, List<ModuleMethod> moduleMethod) {
		this.projectId = projectId;
		this.packageId = packageId;
		this.moduleId = moduleId;
		this.moduleIdName = moduleIdName;
		this.moduleType = moduleType;
		this.comment = comment;
		this.moduleMethod = moduleMethod;
	}
	
	
	public List<ModuleMethod> getModuleMethod() {
		return moduleMethod;
	}
	public void setModuleMethod(List<ModuleMethod> moduleMethod) {
		this.moduleMethod = moduleMethod;
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

	public String getModuleIdName() {
		return moduleIdName;
	}

	public void setModuleIdName(String moduleIdName) {
		this.moduleIdName = moduleIdName;
	}

	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

	public String getComment() {
		return StringUtil.NullToEmpty(comment);
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}
