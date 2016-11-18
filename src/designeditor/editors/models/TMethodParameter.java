package designeditor.editors.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the method_parameter database table.
 * 
 */
@Entity
@Table(name="method_parameter")
@NamedQuery(name="TMethodParameter.findAll", query="SELECT t FROM TMethodParameter t")
public class TMethodParameter implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TMethodParameterPK id;

	private String comment;

	@Column(name="parameter_name")
	private String parameterName;

	@Column(name="paramter_type")
	private String paramterType;

	public TMethodParameter() {
	}

	public TMethodParameterPK getId() {
		return this.id;
	}

	public void setId(TMethodParameterPK id) {
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