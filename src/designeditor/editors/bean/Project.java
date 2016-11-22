package designeditor.editors.bean;

public class Project {
	// プロジェクト名
	private String projectId;
	// プロジェクト名
	private String projectIdName;

	// コメント
	private String comment;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectIdName() {
		return projectIdName;
	}

	public void setProjectIdName(String projectIdName) {
		this.projectIdName = projectIdName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
