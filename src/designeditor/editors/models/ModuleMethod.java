package designeditor.editors.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the module_method database table.
 * 
 */
@Entity
@Table(name="module_method")
@NamedQuery(name="ModuleMethod.findAll", query="SELECT m FROM ModuleMethod m")
public class ModuleMethod implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ModuleMethodPK id;

	private String comment;

	@Column(name="method_id_name")
	private String methodIdName;

	@Column(name="method_return_type")
	private String methodReturnType;

	@Column(name="method_throws_1")
	private String methodThrows1;

	@Column(name="method_unique_id")
	private String methodUniqueId;

	public ModuleMethod() {
	}

	public ModuleMethodPK getId() {
		return this.id;
	}

	public void setId(ModuleMethodPK id) {
		this.id = id;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getMethodIdName() {
		return this.methodIdName;
	}

	public void setMethodIdName(String methodIdName) {
		this.methodIdName = methodIdName;
	}

	public String getMethodReturnType() {
		return this.methodReturnType;
	}

	public void setMethodReturnType(String methodReturnType) {
		this.methodReturnType = methodReturnType;
	}

	public String getMethodThrows1() {
		return this.methodThrows1;
	}

	public void setMethodThrows1(String methodThrows1) {
		this.methodThrows1 = methodThrows1;
	}

	public String getMethodUniqueId() {
		return this.methodUniqueId;
	}

	public void setMethodUniqueId(String methodUniqueId) {
		this.methodUniqueId = methodUniqueId;
	}

}