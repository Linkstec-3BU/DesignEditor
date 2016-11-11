package designeditor.editors.logic;

import java.util.ArrayList;
import java.util.List;

import designeditor.editors.bean.EditArea;
import designeditor.editors.constant.ConstantManager;
import javafx.collections.FXCollections;

public class CreateSelectBlock implements ICreateBlock {

	@Override
	public List<EditArea> CreateStepOneBlock(String jyoken) {
		List<EditArea> ifLogicData = new ArrayList<EditArea>();
		ifLogicData.add(new EditArea(ConstantManager.BLOCK_START_TAG, ConstantManager.BLOCK_STEP_ONE, "IF", "", "",
				jyoken, ""));
		ifLogicData.add(new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""));
		ifLogicData.add(new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""));
		ifLogicData.add(new EditArea("", ConstantManager.BLOCK_STEP_ONE, "ELSE", "", "", "", ""));
		ifLogicData.add(new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""));
		ifLogicData.add(new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""));
		ifLogicData.add(
				new EditArea(ConstantManager.BLOCK_END_TAG, ConstantManager.BLOCK_STEP_ONE, "END", "", "", "", ""));
		return ifLogicData;
	}

	@Override
	public List<EditArea> CreateStepTwoBlock(String jyoken) {
		List<EditArea> ifLogicData = new ArrayList<EditArea>();
		ifLogicData.add(new EditArea(ConstantManager.BLOCK_START_TAG, ConstantManager.BLOCK_STEP_TWO, "", "IF", "",
				jyoken, ""));
		ifLogicData.add(new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""));
		ifLogicData.add(new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""));
		ifLogicData.add(new EditArea("", ConstantManager.BLOCK_STEP_TWO, "", "ELSE", "", "", ""));
		ifLogicData.add(new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""));
		ifLogicData.add(new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""));
		ifLogicData.add(
				new EditArea(ConstantManager.BLOCK_END_TAG, ConstantManager.BLOCK_STEP_TWO, "", "END", "", "", ""));
		return ifLogicData;
	}

	@Override
	public List<EditArea> CreateStepThreeBlock(String jyoken) {
		List<EditArea> ifLogicData = new ArrayList<EditArea>();

		ifLogicData.add(new EditArea(ConstantManager.BLOCK_START_TAG, ConstantManager.BLOCK_STEP_THREE, "", "", "IF",
				jyoken, ""));
		ifLogicData.add(new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""));
		ifLogicData.add(new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""));
		ifLogicData.add(new EditArea("", ConstantManager.BLOCK_STEP_THREE, "", "", "ELSE", "", ""));
		ifLogicData.add(new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""));
		ifLogicData.add(new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""));
		ifLogicData.add(
				new EditArea(ConstantManager.BLOCK_END_TAG, ConstantManager.BLOCK_STEP_THREE, "", "", "END", "", ""));
		return ifLogicData;
	}

}