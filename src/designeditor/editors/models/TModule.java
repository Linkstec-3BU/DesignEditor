package designeditor.editors.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the module database table.
 * 
 */
@Entity
@Table(name="module")
@NamedQuery(name="TModule.findAll", query="SELECT t FROM TModule t")
public class TModule implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TModulePK id;

	private String comment;

	@Column(name="module_id_name")
	private String moduleIdName;

	@Column(name="module_type")
	private String moduleType;

	public TModule() {
	}

	public TModulePK getId() {
		return this.id;
	}

	public void setId(TModulePK id) {
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