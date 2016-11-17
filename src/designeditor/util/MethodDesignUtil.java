package designeditor.util;

import java.util.List;

import designeditor.editors.bean.MethodDesign;
import designeditor.editors.constant.ConstantManager;

public class MethodDesignUtil {

	public static void initBlock(List<MethodDesign> methodDesignList) {
		MethodDesign startMethodDesign = new MethodDesign(ConstantManager.START_NODE, "", "",
				ConstantManager.BLOCK_TYPE_NORMAL, ConstantManager.BLOCK_LEVEL_ONE);
		MethodDesign endMethodDesign = new MethodDesign(ConstantManager.END_NODE, "", "",
				ConstantManager.BLOCK_TYPE_NORMAL, ConstantManager.BLOCK_LEVEL_ONE);
		methodDesignList.add(startMethodDesign);
		methodDesignList.add(endMethodDesign);
	}

	/**
	 * 共通Blockを追加する
	 * 
	 * @param methodDesignList
	 * @param index
	 * @return
	 */
	public static void addCommonBlock(List<MethodDesign> methodDesignList, int index) {
		MethodDesign beforeMethodDesign = methodDesignList.get(index - 1);
		MethodDesign afterMethodDesign = methodDesignList.get(index);
		String uniqueId = StringUtil.GetUniqueId();
		MethodDesign newMethodDesign = new MethodDesign(uniqueId, afterMethodDesign.getParentBlockUniqueId(),
				afterMethodDesign.getBlockUniqueId(), "", afterMethodDesign.getBlockLevel());
		beforeMethodDesign.setNextBlockUniqueId(uniqueId);
		methodDesignList.set(index - 1, beforeMethodDesign);
		methodDesignList.add(index, newMethodDesign);
	}

	/**
	 * 共通Blockを追加する
	 * 
	 * @param methodDesignList
	 * @param index
	 * @return
	 */
	public static void addSubBlock(List<MethodDesign> methodDesignList, int index) {
		MethodDesign parentMethodDesign = methodDesignList.get(index);
		String uniqueId = StringUtil.GetUniqueId();
		MethodDesign newMethodDesign = new MethodDesign(uniqueId, parentMethodDesign.getBlockUniqueId(),
				parentMethodDesign.getNextBlockUniqueId(), parentMethodDesign.getBlockType(),
				getNextLevel(parentMethodDesign.getBlockLevel()));
		parentMethodDesign.setNextBlockUniqueId(uniqueId);
		methodDesignList.set(index, parentMethodDesign);
		methodDesignList.add(index + 1, newMethodDesign);
	}

	private static String getNextLevel(String level) {
		if (ConstantManager.BLOCK_LEVEL_ONE.equals(level)) {
			return ConstantManager.BLOCK_LEVEL_TWO;
		} else if (ConstantManager.BLOCK_LEVEL_TWO.equals(level)) {
			return ConstantManager.BLOCK_LEVEL_THREE;
		} else if (ConstantManager.BLOCK_LEVEL_THREE.equals(level)) {
			return ConstantManager.BLOCK_LEVEL_FOUR;
		}
		return ConstantManager.BLOCK_LEVEL_ONE;
	}
}
