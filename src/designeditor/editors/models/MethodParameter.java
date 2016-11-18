package designeditor.editors.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the method_parameter database table.
 * 
 */
@Entity
@Table(name="method_parameter")
@NamedQuery(name="MethodParameter.findAll", query="SELECT m FROM MethodParameter m")
public class MethodParameter implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MethodParameterPK id;

	private String comment;

	@Column(name="parameter_name")
	private String parameterName;

	@Column(name="paramter_type")
	private String paramterType;

	public MethodParameter() {
	}

	public MethodParameterPK getId() {
		return this.id;
	}

	public void setId(MethodParameterPK id) {
		this.id = id;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getParameterName() {
		return this.parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public String getParamterType() {
		return this.paramterType;
	}

	public void setParamterType(String paramterType) {
		this.paramterType = paramterType;
	}

}