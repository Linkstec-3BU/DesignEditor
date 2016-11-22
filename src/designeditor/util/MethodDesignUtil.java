package designeditor.util;

import java.util.List;

import designeditor.editors.bean.MethodDesign;
import designeditor.editors.constant.ConstantManager;

public class MethodDesignUtil {

	public static void initBlock(List<MethodDesign> methodDesignList) {
		MethodDesign startMethodDesign = new MethodDesign(ConstantManager.START_NODE, "", "",
				 ConstantManager.BLOCK_LEVEL_ONE);
		startMethodDesign.setBlockType(ConstantManager.BLOCK_TYPE_NORMAL);
		MethodDesign endMethodDesign = new MethodDesign(ConstantManager.END_NODE, "", "",
				 ConstantManager.BLOCK_LEVEL_ONE);
		endMethodDesign.setBlockType(ConstantManager.BLOCK_TYPE_NORMAL);
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
	public static void addCommonBlock(List<MethodDesign> methodDesignList, int index,String blockType) {
		MethodDesign beforeMethodDesign = methodDesignList.get(index - 1);
		MethodDesign afterMethodDesign = methodDesignList.get(index);
		MethodDesign methodDesign = compareLevel(beforeMethodDesign,afterMethodDesign);
		String uniqueId = StringUtil.GetUniqueId();
		MethodDesign newMethodDesign = new MethodDesign(uniqueId, methodDesign.getFatherBlockUniqueId(),
				afterMethodDesign.getBlockUniqueId(), methodDesign.getBlockLevel());
		newMethodDesign.setBlockType(blockType);
		beforeMethodDesign.setAfterBlockUniqueId(uniqueId);
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
	public static void addCommonBlock2(List<MethodDesign> methodDesignList, int index,String blockType) {
		MethodDesign beforeMethodDesign = methodDesignList.get(index - 1);
		MethodDesign afterMethodDesign = methodDesignList.get(index);
		String uniqueId = StringUtil.GetUniqueId();
		MethodDesign newMethodDesign = new MethodDesign(uniqueId, afterMethodDesign.getFatherBlockUniqueId(),
				afterMethodDesign.getBlockUniqueId(), afterMethodDesign.getBlockLevel());
		newMethodDesign.setBlockType(blockType);
		beforeMethodDesign.setAfterBlockUniqueId(uniqueId);
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
				parentMethodDesign.getAfterBlockUniqueId(), 
				getNextLevel(parentMethodDesign.getBlockLevel()));
		newMethodDesign.setBlockType(ConstantManager.BLOCK_TYPE_NORMAL);
		parentMethodDesign.setAfterBlockUniqueId(uniqueId);
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
	
	public static MethodDesign compareLevel(MethodDesign beforeMethodDesign,MethodDesign afterMethodDesign) {
		String beforeLevel = beforeMethodDesign.getBlockLevel();
		String afterLevel = afterMethodDesign.getBlockLevel();
		if (beforeLevel.compareTo(afterLevel) > 0) {
			return beforeMethodDesign;
		} else if (beforeLevel.compareTo(afterLevel) <= 0) {
			return afterMethodDesign;
		}
		return null;
	}
}
