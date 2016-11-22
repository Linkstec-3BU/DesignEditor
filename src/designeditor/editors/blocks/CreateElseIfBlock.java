package designeditor.editors.blocks;

import java.util.List;

import designeditor.editors.bean.MethodDesign;
import designeditor.editors.constant.ConstantManager;
import designeditor.util.MethodDesignUtil;

public class CreateElseIfBlock implements ICreateBlock {

	@Override
	public void CreateLevel1Block(List<MethodDesign> methodDesignList, int index,String methodUniqueId) {
		MethodDesignUtil.addCommonBlock2(methodDesignList, index, ConstantManager.BLOCK_TYPE_IF,methodUniqueId);
		MethodDesign newMethodDesign = methodDesignList.get(index);

		newMethodDesign.setLevel1Display(ConstantManager.DISPLAY_SELECT_ELSE_IF);

		methodDesignList.set(index, newMethodDesign);

		MethodDesignUtil.addSubBlock(methodDesignList, index,methodUniqueId);
		MethodDesignUtil.addCommonBlock(methodDesignList, index + 1, ConstantManager.BLOCK_TYPE_NORMAL,methodUniqueId);

	}

	@Override
	public void CreateLevel2Block(List<MethodDesign> methodDesignList, int index,String methodUniqueId) {
		MethodDesignUtil.addCommonBlock2(methodDesignList, index, ConstantManager.BLOCK_TYPE_IF,methodUniqueId);
		MethodDesign newMethodDesign = methodDesignList.get(index);

		newMethodDesign.setLevel2Display(ConstantManager.DISPLAY_SELECT_ELSE_IF);

		methodDesignList.set(index, newMethodDesign);

		MethodDesignUtil.addSubBlock(methodDesignList, index,methodUniqueId);
		MethodDesignUtil.addCommonBlock(methodDesignList, index + 1, ConstantManager.BLOCK_TYPE_NORMAL,methodUniqueId);

	}

	@Override
	public void CreateLevel3Block(List<MethodDesign> methodDesignList, int index,String methodUniqueId) {
		MethodDesignUtil.addCommonBlock2(methodDesignList, index, ConstantManager.BLOCK_TYPE_IF,methodUniqueId);
		MethodDesign newMethodDesign = methodDesignList.get(index);

		newMethodDesign.setLevel3Display(ConstantManager.DISPLAY_SELECT_ELSE_IF);

		methodDesignList.set(index, newMethodDesign);

		MethodDesignUtil.addSubBlock(methodDesignList, index,methodUniqueId);
		MethodDesignUtil.addCommonBlock(methodDesignList, index + 1, ConstantManager.BLOCK_TYPE_NORMAL,methodUniqueId);

	}

}
