//package designeditor.editors.logic;
//
//import java.util.List;
//
//import designeditor.editors.bean.MethodDesign;
//import designeditor.editors.constant.ConstantManager;
//
//public class CreateSelectBlock implements ICreateBlock {
//
//	@Override
//	public List<MethodDesign> CreateLevel1Block(String jyoken) {
//		MethodDesign newMethodDesign = new MethodDesign();
//		newMethodDesign.setAfterBlockUniqueId(ConstantManager.END_NODE);
//		newMethodDesign.setBeforeBlockUniqueId(ConstantManager.START_NODE);
//		newMethodDesign.setBlockLevel(ConstantManager.BLOCK_LEVEL_ONE);
//		newMethodDesign.setBlockType(ConstantManager.BLOCK_TYPE_NORMAL);
//		newMethodDesign.setBlockUniqueId(ConstantManager.START_NODE);
//		newMethodDesign.setComment("");
//		newMethodDesign.setDetailDisplay("");
//		newMethodDesign.setLevel1Display("");
//		newMethodDesign.setLevel2Display("");
//		newMethodDesign.setLevel3Display("");
//		return null;
//	}
//
//	@Override
//	public List<MethodDesign> CreateLevel2Block(String jyoken) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<MethodDesign> CreateLevel3Block(String jyoken) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}
