package designeditor.editors.models;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the method_block database table.
 * 
 */
@Entity
@Table(name="method_block")
@NamedQuery(name="TMethodBlock.findAll", query="SELECT t FROM TMethodBlock t")
public class TMethodBlock implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="block_unique_id")
	private BigInteger blockUniqueId;

	@Column(name="after_block_unique_id")
	private BigInteger afterBlockUniqueId;

	@Column(name="before_block_unique_id")
	private BigInteger beforeBlockUniqueId;

	@Column(name="block_level")
	private String blockLevel;

	@Column(name="block_type")
	private String blockType;

	private String comment;

	@Column(name="detail_display")
	private String detailDisplay;

	@Column(name="father_block_unique_id")
	private BigInteger fatherBlockUniqueId;

	@Column(name="level1_display")
	private String level1Display;

	@Column(name="level2_display")
	private String level2Display;

	@Column(name="level3_display")
	private String level3Display;

	@Column(name="method_unique_id")
	private String methodUniqueId;

	public TMethodBlock() {
	}

	public BigInteger getBlockUniqueId() {
		return this.blockUniqueId;
	}

	public void setBlockUniqueId(BigInteger blockUniqueId) {
		this.blockUniqueId = blockUniqueId;
	}

	public BigInteger getAfterBlockUniqueId() {
		return this.afterBlockUniqueId;
	}

	public void setAfterBlockUniqueId(BigInteger afterBlockUniqueId) {
		this.afterBlockUniqueId = afterBlockUniqueId;
	}

	public BigInteger getBeforeBlockUniqueId() {
		return this.beforeBlockUniqueId;
	}

	public void setBeforeBlockUniqueId(BigInteger beforeBlockUniqueId) {
		this.beforeBlockUniqueId = beforeBlockUniqueId;
	}

	public String getBlockLevel() {
		return this.blockLevel;
	}

	public void setBlockLevel(String blockLevel) {
		this.blockLevel = blockLevel;
	}

	public String getBlockType() {
		return this.blockType;
	}

	public void setBlockType(String blockType) {
		this.blockType = blockType;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getDetailDisplay() {
		return this.detailDisplay;
	}

	public void setDetailDisplay(String detailDisplay) {
		this.detailDisplay = detailDisplay;
	}

	public BigInteger getFatherBlockUniqueId() {
		return this.fatherBlockUniqueId;
	}

	public void setFatherBlockUniqueId(BigInteger fatherBlockUniqueId) {
		this.fatherBlockUniqueId = fatherBlockUniqueId;
	}

	public String getLevel1Display() {
		return this.level1Display;
	}

	public void setLevel1Display(String level1Display) {
		this.level1Display = level1Display;
	}

	public String getLevel2Display() {
		return this.level2Display;
	}

	public void setLevel2Display(String level2Display) {
		this.level2Display = level2Display;
	}

	public String getLevel3Display() {
		return this.level3Display;
	}

	public void setLevel3Display(String level3Display) {
		this.level3Display = level3Display;
	}

	public String getMethodUniqueId() {
		return this.methodUniqueId;
	}

	public void setMethodUniqueId(String methodUniqueId) {
		this.methodUniqueId = methodUniqueId;
	}

}