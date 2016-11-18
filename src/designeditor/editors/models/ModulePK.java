package designeditor.editors.models;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the module database table.
 * 
 */
@Embeddable
public class ModulePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="project_id")
	private String projectId;

	@Column(name="package_id")
	private String packageId;

	@Column(name="module_id")
	private String moduleId;

	public ModulePK() {
	}
	public String getProjectId() {
		return this.projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getPackageId() {
		return this.packageId;
	}
	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}
	public String getModuleId() {
		return this.moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ModulePK)) {
			return false;
		}
		ModulePK castOther = (ModulePK)other;
		return 
			this.projectId.equals(castOther.projectId)
			&& this.packageId.equals(castOther.packageId)
			&& this.moduleId.equals(castOther.moduleId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.projectId.hashCode();
		hash = hash * prime + this.packageId.hashCode();
		hash = hash * prime + this.moduleId.hashCode();
		
		return hash;
	}
}