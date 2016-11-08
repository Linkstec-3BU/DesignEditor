package designeditor.editors.pl;

import designeditor.editors.pl.bean.EditArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CreateSelectBlock implements CreateBlock {
	
	@Override
	public ObservableList<EditArea> CreateStepOneBlock(String jyoken) {
		ObservableList<EditArea> ifLogicData = FXCollections.observableArrayList(); 
		ifLogicData.addAll(
				new EditArea(ConstantManager.BLOCK_START_TAG, ConstantManager.BLOCK_STEP_ONE, "IF", "", "",
						jyoken, ""),
				new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""),
				new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""),
				new EditArea("", ConstantManager.BLOCK_STEP_ONE, "ELSE", "", "", "", ""),
				new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""),
				new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""), new EditArea(
						ConstantManager.BLOCK_END_TAG, ConstantManager.BLOCK_STEP_ONE, "END", "", "", "", ""));
		return ifLogicData;
	}

	@Override
	public ObservableList<EditArea> CreateStepTwoBlock(String jyoken) {
		ObservableList<EditArea> ifLogicData = FXCollections.observableArrayList(); 
		ifLogicData.addAll(
				new EditArea(ConstantManager.BLOCK_START_TAG, ConstantManager.BLOCK_STEP_TWO, "", "IF", "",
						jyoken, ""),
				new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""),
				new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""),
				new EditArea("", ConstantManager.BLOCK_STEP_TWO, "", "ELSE", "", "", ""),
				new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""),
				new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""), new EditArea(
						ConstantManager.BLOCK_END_TAG, ConstantManager.BLOCK_STEP_TWO, "", "END", "", "", ""));
		return ifLogicData;
	}

	@Override
	public ObservableList<EditArea> CreateStepThreeBlock(String jyoken) {
		ObservableList<EditArea> ifLogicData = FXCollections.observableArrayList(); 
		ifLogicData.addAll(
				new EditArea(ConstantManager.BLOCK_START_TAG, ConstantManager.BLOCK_STEP_THREE, "", "", "IF",
						jyoken, ""),
				new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""),
				new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""),
				new EditArea("", ConstantManager.BLOCK_STEP_THREE, "", "", "ELSE", "", ""),
				new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""),
				new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""),
				new EditArea(ConstantManager.BLOCK_END_TAG, ConstantManager.BLOCK_STEP_THREE, "", "", "END", "",
						""));
		return ifLogicData;
	}

}
