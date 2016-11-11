package designeditor.editors.pl.bean;

import javafx.beans.property.SimpleStringProperty;

public class AreaType {
	private final SimpleStringProperty no;
	private final SimpleStringProperty areaType;
	private final SimpleStringProperty areaName;

	public AreaType(String lno, String lareaType, String lareaName) {
		this.no = new SimpleStringProperty(lno);
		this.areaType = new SimpleStringProperty(lareaType);
		this.areaName = new SimpleStringProperty(lareaName);
	}

	public String getAreaName() {
		return areaName.get();
	}

	public String getAreaType() {
		return areaType.get();
	}

	public String getNo() {
		return no.get();
	}

	public void setAreaName(String lareaName) {
		areaName.set(lareaName);
	}

	public void setAreaType(String lareaType) {
		areaType.set(lareaType);
	}

	public void setNo(String lno) {
		no.set(lno);
	}

}