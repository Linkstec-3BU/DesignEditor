package designeditor.editors.pl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CreateThrowBlock implements CreateBlock {

	@Override
	public ObservableList<EditArea> CreateStepOneBlock(String jyoken) {
		ObservableList<EditArea> ifLogicData = FXCollections.observableArrayList();
		ifLogicData.addAll(
				new EditArea(ConstantManager.BLOCK_START_TAG, ConstantManager.BLOCK_STEP_ONE, "TRY", "", "",
						jyoken, ""),
				new EditArea("", ConstantManager.BLOCK_STEP_ONE, "CATCH", "", "", "", ""),
				new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""),
				new EditArea("", ConstantManager.BLOCK_STEP_ONE, "FINALLY", "", "", "", ""), new EditArea(
						ConstantManager.BLOCK_END_TAG, ConstantManager.BLOCK_STEP_ONE, "END", "", "", "", ""));
		return ifLogicData;
	}

	@Override
	public ObservableList<EditArea> CreateStepTwoBlock(String jyoken) {
		ObservableList<EditArea> ifLogicData = FXCollections.observableArrayList();
		ifLogicData.addAll(
				new EditArea(ConstantManager.BLOCK_START_TAG, ConstantManager.BLOCK_STEP_TWO, "", "TRY", "",
						jyoken, ""),
				new EditArea("", ConstantManager.BLOCK_STEP_TWO, "", "CATCH", "", "", ""),
				new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""),
				new EditArea("", ConstantManager.BLOCK_STEP_TWO, "", "FINALLY", "", "", ""), new EditArea(
						ConstantManager.BLOCK_END_TAG, ConstantManager.BLOCK_STEP_TWO, "", "END", "", "", ""));
		return ifLogicData;
	}

	@Override
	public ObservableList<EditArea> CreateStepThreeBlock(String jyoken) {
		ObservableList<EditArea> ifLogicData = FXCollections.observableArrayList();
		ifLogicData.addAll(
				new EditArea(ConstantManager.BLOCK_START_TAG, ConstantManager.BLOCK_STEP_THREE, "", "", "TRY",
						jyoken, ""),
				new EditArea("", ConstantManager.BLOCK_STEP_THREE, "", "", "CATCH", "", ""),
				new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""),
				new EditArea("", ConstantManager.BLOCK_STEP_THREE, "", "", "FINALLY", "", ""),
				new EditArea(ConstantManager.BLOCK_END_TAG, ConstantManager.BLOCK_STEP_THREE, "", "", "END", "",
						""));

		return ifLogicData;
	}

}
