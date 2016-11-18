package designeditor.editors.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the module database table.
 * 
 */
@Entity
@NamedQuery(name="Module.findAll", query="SELECT m FROM Module m")
public class Module implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ModulePK id;

	private String comment;

	@Column(name="module_id_name")
	private String moduleIdName;

	@Column(name="module_type")
	private String moduleType;

	public Module() {
	}

	public ModulePK getId() {
		return this.id;
	}

	public void setId(ModulePK id) {
		this.id = id;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getModuleIdName() {
		return this.moduleIdName;
	}

	public void setModuleIdName(String moduleIdName) {
		this.moduleIdName = moduleIdName;
	}

	public String getModuleType() {
		return this.moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

}