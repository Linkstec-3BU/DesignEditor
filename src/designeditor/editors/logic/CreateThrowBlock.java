package designeditor.editors.logic;

import java.util.ArrayList;
import java.util.List;

import designeditor.editors.bean.EditArea;
import designeditor.editors.constant.ConstantManager;

public class CreateThrowBlock implements ICreateBlock {

	@Override
	public List<EditArea> CreateStepOneBlock(String jyoken) {
		List<EditArea> ifLogicData = new ArrayList<EditArea>();
		ifLogicData.add(new EditArea(ConstantManager.BLOCK_START_TAG, ConstantManager.BLOCK_STEP_ONE, "TRY", "", "",
				jyoken, ""));
		ifLogicData.add(new EditArea("", ConstantManager.BLOCK_STEP_ONE, "CATCH", "", "", "", ""));
		ifLogicData.add(new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""));
		ifLogicData.add(new EditArea("", ConstantManager.BLOCK_STEP_ONE, "FINALLY", "", "", "", ""));
		ifLogicData.add(
				new EditArea(ConstantManager.BLOCK_END_TAG, ConstantManager.BLOCK_STEP_ONE, "END", "", "", "", ""));
		return ifLogicData;
	}

	@Override
	public List<EditArea> CreateStepTwoBlock(String jyoken) {
		List<EditArea> ifLogicData = new ArrayList<EditArea>();
		ifLogicData.add(new EditArea(ConstantManager.BLOCK_START_TAG, ConstantManager.BLOCK_STEP_TWO, "", "TRY", "",
				jyoken, ""));
		ifLogicData.add(new EditArea("", ConstantManager.BLOCK_STEP_TWO, "", "CATCH", "", "", ""));
		ifLogicData.add(new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""));
		ifLogicData.add(new EditArea("", ConstantManager.BLOCK_STEP_TWO, "", "FINALLY", "", "", ""));
		ifLogicData.add(
				new EditArea(ConstantManager.BLOCK_END_TAG, ConstantManager.BLOCK_STEP_TWO, "", "END", "", "", ""));
		return ifLogicData;
	}

	@Override
	public List<EditArea> CreateStepThreeBlock(String jyoken) {
		List<EditArea> ifLogicData = new ArrayList<EditArea>();
		ifLogicData.add(new EditArea(ConstantManager.BLOCK_START_TAG, ConstantManager.BLOCK_STEP_THREE, "", "", "TRY",
				jyoken, ""));
		ifLogicData.add(new EditArea("", ConstantManager.BLOCK_STEP_THREE, "", "", "CATCH", "", ""));
		ifLogicData.add(new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""));
		ifLogicData.add(new EditArea("", ConstantManager.BLOCK_STEP_THREE, "", "", "FINALLY", "", ""));
		ifLogicData.add(
				new EditArea(ConstantManager.BLOCK_END_TAG, ConstantManager.BLOCK_STEP_THREE, "", "", "END", "", ""));

		return ifLogicData;
	}

}
