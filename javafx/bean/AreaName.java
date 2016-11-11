package designeditor.editors.pl.bean;

import javafx.beans.property.SimpleStringProperty;

public class AreaName {
	private final SimpleStringProperty no;
	private final SimpleStringProperty name;
	private final SimpleStringProperty areaName;

	public AreaName(String lno, String lareaName, String lname) {
		this.no = new SimpleStringProperty(lno);
		this.areaName = new SimpleStringProperty(lareaName);
		this.name = new SimpleStringProperty(lname);
	}

	public String getAreaName() {
		return areaName.get();
	}

	public String getName() {
		return name.get();
	}

	public String getNo() {
		return no.get();
	}

	public void setAreaName(String lareaName) {
		areaName.set(lareaName);
	}

	public void setName(String lname) {
		name.set(lname);
	}

	public void setNo(String lno) {
		no.set(lno);
	}

}