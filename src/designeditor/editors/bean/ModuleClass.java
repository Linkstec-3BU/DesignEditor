package designeditor.editors.bean;

import java.util.List;

import designeditor.util.StringUtil;

public class ModuleClass {
	// プロジェクト名
	private String project_id;
	// パケッジ名
	private String package_id;
	//モジュール物理名
	private String module_id;
	//モジュール論理名
	private String module_id_name;
	//モジュールタイプ
	private String module_type;
	//コメント
	private String comment;
	
	private List<ModuleMethod> moduleMethod;
	
	public List<ModuleMethod> getModuleMethod() {
		return moduleMethod;
	}
	public void setModuleMethod(List<ModuleMethod> moduleMethod) {
		this.moduleMethod = moduleMethod;
	}
	public String getProject_id() {
		return StringUtil.NullToEmpty(project_id);
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	public String getPackage_id() {
		return StringUtil.NullToEmpty(package_id);
	}
	public void setPackage_id(String package_id) {
		this.package_id = package_id;
	}
	public String getModule_id() {
		return StringUtil.NullToEmpty(module_id);
	}
	public void setModule_id(String module_id) {
		this.module_id = module_id;
	}
	public String getModule_id_name() {
		return StringUtil.NullToEmpty(module_id_name);
	}
	public void setModule_id_name(String module_id_name) {
		this.module_id_name = module_id_name;
	}
	public String getModule_type() {
		return StringUtil.NullToEmpty(module_type);
	}
	public void setModule_type(String module_type) {
		this.module_type = module_type;
	}
	public String getComment() {
		return StringUtil.NullToEmpty(comment);
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}
