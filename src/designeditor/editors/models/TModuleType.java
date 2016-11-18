package designeditor.editors.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the module_type database table.
 * 
 */
@Entity
@Table(name="module_type")
@NamedQuery(name="TModuleType.findAll", query="SELECT t FROM TModuleType t")
public class TModuleType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="module_type")
	private String moduleType;

	private String comment;

	@Column(name="module_type_name")
	private String moduleTypeName;

	public TModuleType() {
	}

	public String getModuleType() {
		return this.moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getModuleTypeName() {
		return this.moduleTypeName;
	}

	public void setModuleTypeName(String moduleTypeName) {
		this.moduleTypeName = moduleTypeName;
	}

}