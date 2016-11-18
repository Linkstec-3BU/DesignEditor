package designeditor.editors.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the project database table.
 * 
 */
@Entity
@NamedQuery(name="Project.findAll", query="SELECT p FROM Project p")
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="project_id")
	private String projectId;

	private String comment;

	@Column(name="project_id_name")
	private String projectIdName;

	public Project() {
	}

	public String getProjectId() {
		return this.projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getProjectIdName() {
		return this.projectIdName;
	}

	public void setProjectIdName(String projectIdName) {
		this.projectIdName = projectIdName;
	}

}