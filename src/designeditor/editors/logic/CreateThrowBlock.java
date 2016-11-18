package designeditor.editors.logic;

import java.util.List;

import designeditor.editors.bean.MethodDesign;
import designeditor.editors.constant.ConstantManager;
import designeditor.util.MethodDesignUtil;

public class CreateThrowBlock implements ICreateBlock {

	@Override
	public void CreateLevel1Block(List<MethodDesign> methodDesignList, int index) {
		MethodDesignUtil.addCommonBlock(methodDesignList, index);
		MethodDesign newMethodDesign = methodDesignList.get(index);
		newMethodDesign.setBlockType(ConstantManager.BLOCK_TYPE_IF);
		newMethodDesign.setLevel1Display(ConstantManager.DISPLAY_END);
		methodDesignList.set(index, newMethodDesign);
		
		MethodDesignUtil.addCommonBlock(methodDesignList, index);
		newMethodDesign = methodDesignList.get(index);
		newMethodDesign.setBlockType(ConstantManager.BLOCK_TYPE_THROW);

		newMethodDesign.setLevel1Display("TRY");

		methodDesignList.set(index, newMethodDesign);

		MethodDesignUtil.addSubBlock(methodDesignList, index);
		MethodDesignUtil.addCommonBlock(methodDesignList, index + 1);

		MethodDesignUtil.addCommonBlock(methodDesignList, index + 3);
		newMethodDesign = methodDesignList.get(index + 3);
		newMethodDesign.setBlockType(ConstantManager.BLOCK_TYPE_THROW);

		newMethodDesign.setLevel1Display("CATCH");

		methodDesignList.set(index + 3, newMethodDesign);

		MethodDesignUtil.addSubBlock(methodDesignList, index + 3);
		MethodDesignUtil.addCommonBlock(methodDesignList, index + 4);

		MethodDesignUtil.addCommonBlock(methodDesignList, index + 6);
		newMethodDesign = methodDesignList.get(index + 6);
		newMethodDesign.setBlockType(ConstantManager.BLOCK_TYPE_THROW);

		newMethodDesign.setLevel1Display("THROW");

		methodDesignList.set(index + 6, newMethodDesign);

		MethodDesignUtil.addSubBlock(methodDesignList, index + 6);
		MethodDesignUtil.addCommonBlock(methodDesignList, index + 7);

	}

	@Override
	public void CreateLevel2Block(List<MethodDesign> methodDesignList, int index) {
		MethodDesignUtil.addCommonBlock(methodDesignList, index);
		MethodDesign newMethodDesign = methodDesignList.get(index);
		newMethodDesign.setBlockType(ConstantManager.BLOCK_TYPE_IF);
		newMethodDesign.setLevel2Display(ConstantManager.DISPLAY_END);
		methodDesignList.set(index, newMethodDesign);
		
		MethodDesignUtil.addCommonBlock(methodDesignList, index);
		newMethodDesign = methodDesignList.get(index);
		newMethodDesign.setBlockType(ConstantManager.BLOCK_TYPE_THROW);
		newMethodDesign.setLevel2Display("TRY");
		methodDesignList.set(index, newMethodDesign);

		MethodDesignUtil.addSubBlock(methodDesignList, index);
		MethodDesignUtil.addCommonBlock(methodDesignList, index + 1);

		MethodDesignUtil.addCommonBlock(methodDesignList, index + 3);
		newMethodDesign = methodDesignList.get(index + 3);
		newMethodDesign.setBlockType(ConstantManager.BLOCK_TYPE_THROW);

		newMethodDesign.setLevel2Display("CATCH");

		methodDesignList.set(index + 3, newMethodDesign);

		MethodDesignUtil.addSubBlock(methodDesignList, index + 3);
		MethodDesignUtil.addCommonBlock(methodDesignList, index + 4);

		MethodDesignUtil.addCommonBlock(methodDesignList, index + 6);
		newMethodDesign = methodDesignList.get(index + 6);
		newMethodDesign.setBlockType(ConstantManager.BLOCK_TYPE_THROW);

		newMethodDesign.setLevel2Display("THROW");

		methodDesignList.set(index + 6, newMethodDesign);

		MethodDesignUtil.addSubBlock(methodDesignList, index + 6);
		MethodDesignUtil.addCommonBlock(methodDesignList, index + 7);

	}

	@Override
	public void CreateLevel3Block(List<MethodDesign> methodDesignList, int index) {
		MethodDesignUtil.addCommonBlock(methodDesignList, index);
		MethodDesign newMethodDesign = methodDesignList.get(index);
		newMethodDesign.setBlockType(ConstantManager.BLOCK_TYPE_IF);
		newMethodDesign.setLevel3Display(ConstantManager.DISPLAY_END);
		methodDesignList.set(index, newMethodDesign);
		
		MethodDesignUtil.addCommonBlock(methodDesignList, index);
		newMethodDesign = methodDesignList.get(index);
		newMethodDesign.setBlockType(ConstantManager.BLOCK_TYPE_THROW);
		newMethodDesign.setLevel3Display("TRY");
		methodDesignList.set(index, newMethodDesign);

		MethodDesignUtil.addSubBlock(methodDesignList, index);
		MethodDesignUtil.addCommonBlock(methodDesignList, index + 1);

		MethodDesignUtil.addCommonBlock(methodDesignList, index + 3);
		newMethodDesign = methodDesignList.get(index + 3);
		newMethodDesign.setBlockType(ConstantManager.BLOCK_TYPE_THROW);
		newMethodDesign.setLevel3Display("CATCH");
		methodDesignList.set(index + 3, newMethodDesign);

		MethodDesignUtil.addSubBlock(methodDesignList, index + 3);
		MethodDesignUtil.addCommonBlock(methodDesignList, index + 4);

		MethodDesignUtil.addCommonBlock(methodDesignList, index + 6);
		newMethodDesign = methodDesignList.get(index + 6);
		newMethodDesign.setBlockType(ConstantManager.BLOCK_TYPE_THROW);
		newMethodDesign.setLevel3Display("THROW");
		methodDesignList.set(index + 6, newMethodDesign);

		MethodDesignUtil.addSubBlock(methodDesignList, index + 6);
		MethodDesignUtil.addCommonBlock(methodDesignList, index + 7);

	}

}
