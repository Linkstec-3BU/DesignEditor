package designeditor.editors.pl;

import javafx.beans.property.SimpleStringProperty;

public class EditArea {

	private final SimpleStringProperty tag;
	private final SimpleStringProperty step;
	private final SimpleStringProperty logicOne;
	private final SimpleStringProperty logicTwo;
	private final SimpleStringProperty logicThree;
	private final SimpleStringProperty editArea;
	private final SimpleStringProperty comment;

	public EditArea(String tag,String step,String logicOne, String logicTwo, String logicThree,
			String editArea, String comment) {
		this.tag = new SimpleStringProperty(tag);
		this.step = new SimpleStringProperty(step);
		this.logicOne = new SimpleStringProperty(logicOne);
		this.logicTwo = new SimpleStringProperty(logicTwo);
		this.logicThree = new SimpleStringProperty(logicThree);
		this.editArea = new SimpleStringProperty(editArea);
		this.comment = new SimpleStringProperty(comment);
	}

	public String getTag() {
		return tag.get();
	}
	
	public String getStep() {
		return step.get();
	}
	
	public String getComment() {
		return comment.get();
	}

	public String getEditArea() {
		return editArea.get();
	}

	public String getLogicOne() {
		return logicOne.get();
	}

	public String getLogicThree() {
		return logicThree.get();
	}

	public String getLogicTwo() {
		return logicTwo.get();
	}

	public void setTag(String tag) {
		this.tag.set(tag);
	}
	
	public void setStep(String step) {
		this.step.set(step);
	}
	
	public void setComment(String comment) {
		this.comment.set(comment);
	}

	public void setEditArea(String editArea) {
		this.editArea.set(editArea);
	}

	public void setLogicOne(String logicOne) {
		this.logicOne.set(logicOne);
	}

	public void setLogicThree(String logicThree) {
		this.logicThree.set(logicThree);
	}

	public void setLogicTwo(String logicTwo) {
		this.logicTwo.set(logicTwo);
	}
}
