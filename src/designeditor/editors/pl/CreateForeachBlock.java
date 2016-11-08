package designeditor.editors.pl;

import designeditor.editors.pl.bean.EditArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CreateForeachBlock implements CreateBlock {

	@Override
	public ObservableList<EditArea> CreateStepOneBlock(String jyoken) {
		ObservableList<EditArea> ifLogicData = FXCollections.observableArrayList();
		ifLogicData.addAll(
				new EditArea(ConstantManager.BLOCK_START_TAG, ConstantManager.BLOCK_STEP_ONE, "FOR", "", "",
						jyoken, ""),
				new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""),
				new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""),
				new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""), new EditArea(
						ConstantManager.BLOCK_END_TAG, ConstantManager.BLOCK_STEP_ONE, "END", "", "", "", ""));
		return ifLogicData;
	}

	@Override
	public ObservableList<EditArea> CreateStepTwoBlock(String jyoken) {
		ObservableList<EditArea> ifLogicData = FXCollections.observableArrayList();
		ifLogicData.addAll(
				new EditArea(ConstantManager.BLOCK_START_TAG, ConstantManager.BLOCK_STEP_TWO, "", "FOR", "",
						jyoken, ""),
				new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""),
				new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""),
				new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""), new EditArea(
						ConstantManager.BLOCK_END_TAG, ConstantManager.BLOCK_STEP_TWO, "", "END", "", "", ""));
		return ifLogicData;
	}

	@Override
	public ObservableList<EditArea> CreateStepThreeBlock(String jyoken) {
		ObservableList<EditArea> ifLogicData = FXCollections.observableArrayList();
		ifLogicData.addAll(
				new EditArea(ConstantManager.BLOCK_START_TAG, ConstantManager.BLOCK_STEP_THREE, "", "", "FOR",
						jyoken, ""),
				new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""),
				new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""),
				new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""),
				new EditArea(ConstantManager.BLOCK_END_TAG, ConstantManager.BLOCK_STEP_THREE, "", "", "END", "",
						""));

		return ifLogicData;
	}

}
