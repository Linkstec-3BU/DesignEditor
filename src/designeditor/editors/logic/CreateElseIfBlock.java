package designeditor.editors.logic;

import java.util.List;

import designeditor.editors.bean.MethodDesign;
import designeditor.editors.constant.ConstantManager;
import designeditor.util.MethodDesignUtil;

public class CreateElseIfBlock implements ICreateBlock {

	@Override
	public void CreateLevel1Block(List<MethodDesign> methodDesignList, int index) {
		MethodDesignUtil.addCommonBlock2(methodDesignList, index, ConstantManager.BLOCK_TYPE_IF);
		MethodDesign newMethodDesign = methodDesignList.get(index);

		newMethodDesign.setLevel1Display(ConstantManager.DISPLAY_SELECT_ELSE_IF);

		methodDesignList.set(index, newMethodDesign);

		MethodDesignUtil.addSubBlock(methodDesignList, index);
		MethodDesignUtil.addCommonBlock(methodDesignList, index + 1, ConstantManager.BLOCK_TYPE_NORMAL);

	}

	@Override
	public void CreateLevel2Block(List<MethodDesign> methodDesignList, int index) {
		MethodDesignUtil.addCommonBlock2(methodDesignList, index, ConstantManager.BLOCK_TYPE_IF);
		MethodDesign newMethodDesign = methodDesignList.get(index);

		newMethodDesign.setLevel2Display(ConstantManager.DISPLAY_SELECT_ELSE_IF);

		methodDesignList.set(index, newMethodDesign);

		MethodDesignUtil.addSubBlock(methodDesignList, index);
		MethodDesignUtil.addCommonBlock(methodDesignList, index + 1, ConstantManager.BLOCK_TYPE_NORMAL);

	}

	@Override
	public void CreateLevel3Block(List<MethodDesign> methodDesignList, int index) {
		MethodDesignUtil.addCommonBlock2(methodDesignList, index, ConstantManager.BLOCK_TYPE_IF);
		MethodDesign newMethodDesign = methodDesignList.get(index);

		newMethodDesign.setLevel3Display(ConstantManager.DISPLAY_SELECT_ELSE_IF);

		methodDesignList.set(index, newMethodDesign);

		MethodDesignUtil.addSubBlock(methodDesignList, index);
		MethodDesignUtil.addCommonBlock(methodDesignList, index + 1, ConstantManager.BLOCK_TYPE_NORMAL);

	}

}
