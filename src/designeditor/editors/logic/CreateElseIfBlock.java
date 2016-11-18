package designeditor.editors.logic;

import java.util.List;

import designeditor.editors.bean.MethodDesign;
import designeditor.editors.constant.ConstantManager;
import designeditor.util.MethodDesignUtil;

public class CreateElseIfBlock implements ICreateBlock {

	@Override
	public void CreateLevel1Block(List<MethodDesign> methodDesignList, int index) {
		MethodDesignUtil.addCommonBlock(methodDesignList, index);
		MethodDesign newMethodDesign = methodDesignList.get(index);
		newMethodDesign.setBlockType(ConstantManager.BLOCK_TYPE_IF);

		newMethodDesign.setLevel1Display(ConstantManager.DISPLAY_SELECT_ELSE_IF);

		methodDesignList.set(index, newMethodDesign);

		MethodDesignUtil.addSubBlock(methodDesignList, index);
		MethodDesignUtil.addCommonBlock(methodDesignList, index + 1);

	}

	@Override
	public void CreateLevel2Block(List<MethodDesign> methodDesignList, int index) {
		MethodDesignUtil.addCommonBlock(methodDesignList, index);
		MethodDesign newMethodDesign = methodDesignList.get(index);
		newMethodDesign.setBlockType(ConstantManager.BLOCK_TYPE_IF);

		newMethodDesign.setLevel2Display(ConstantManager.DISPLAY_SELECT_ELSE_IF);

		methodDesignList.set(index, newMethodDesign);

		MethodDesignUtil.addSubBlock(methodDesignList, index);
		MethodDesignUtil.addCommonBlock(methodDesignList, index + 1);

	}

	@Override
	public void CreateLevel3Block(List<MethodDesign> methodDesignList, int index) {
		MethodDesignUtil.addCommonBlock(methodDesignList, index);
		MethodDesign newMethodDesign = methodDesignList.get(index);
		newMethodDesign.setBlockType(ConstantManager.BLOCK_TYPE_IF);

		newMethodDesign.setLevel3Display(ConstantManager.DISPLAY_SELECT_ELSE_IF);

		methodDesignList.set(index, newMethodDesign);

		MethodDesignUtil.addSubBlock(methodDesignList, index);
		MethodDesignUtil.addCommonBlock(methodDesignList, index + 1);

	}

}
