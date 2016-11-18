package designeditor.editors.bean;

public class MethodDesign {
	private String blockUniqueId;
	private String parentBlockUniqueId;
	private String nextBlockUniqueId;
	private String blockType;
	private String blockLevel;
	private String level1Display;
	private String level2Display;
	private String level3Display;
	private String detailDisplay;
	private String comment;

	public MethodDesign(String blockUniqueId, String parentBlockUniqueId, String nextBlockUniqueId, String blockLevel) {
		this.blockUniqueId = blockUniqueId;
		this.parentBlockUniqueId = parentBlockUniqueId;
		this.nextBlockUniqueId = nextBlockUniqueId;
		this.blockType = "";
		this.blockLevel = blockLevel;
		this.level1Display = "";
		this.level2Display = "";
		this.level3Display = "";
		this.detailDisplay = "";
		this.comment = "";
	}

	public String getBlockUniqueId() {
		return blockUniqueId;
	}

	public void setBlockUniqueId(String blockUniqueId) {
		this.blockUniqueId = blockUniqueId;
	}

	public String getParentBlockUniqueId() {
		return parentBlockUniqueId;
	}

	public void setParentBlockUniqueId(String parentBlockUniqueId) {
		this.parentBlockUniqueId = parentBlockUniqueId;
	}

	public String getNextBlockUniqueId() {
		return nextBlockUniqueId;
	}

	public void setNextBlockUniqueId(String nextBlockUniqueId) {
		this.nextBlockUniqueId = nextBlockUniqueId;
	}

	public String getBlockType() {
		return blockType;
	}

	public void setBlockType(String blockType) {
		this.blockType = blockType;
	}

	public String getBlockLevel() {
		return blockLevel;
	}

	public void setBlockLevel(String blockLevel) {
		this.blockLevel = blockLevel;
	}

	public String getLevel1Display() {
		return level1Display;
	}

	public void setLevel1Display(String level1Display) {
		this.level1Display = level1Display;
	}

	public String getLevel2Display() {
		return level2Display;
	}

	public void setLevel2Display(String level2Display) {
		this.level2Display = level2Display;
	}

	public String getLevel3Display() {
		return level3Display;
	}

	public void setLevel3Display(String level3Display) {
		this.level3Display = level3Display;
	}

	public String getDetailDisplay() {
		return detailDisplay;
	}

	public void setDetailDisplay(String detailDisplay) {
		this.detailDisplay = detailDisplay;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}