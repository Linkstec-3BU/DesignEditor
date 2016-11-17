//package designeditor.editors.logic;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import designeditor.editors.bean.MethodDesign;
//import designeditor.editors.constant.ConstantManager;
//
//public class CreateThrowBlock implements ICreateBlock {
//
//	@Override
//	public List<MethodDesign> CreateStepOneBlock(String jyoken) {
//		List<MethodDesign> ifLogicData = new ArrayList<MethodDesign>();
//		ifLogicData.add(new MethodDesign(ConstantManager.BLOCK_START_TAG, ConstantManager.BLOCK_STEP_ONE, "TRY", "", "",
//				jyoken, ""));
//		ifLogicData.add(new MethodDesign("", ConstantManager.BLOCK_STEP_ONE, "CATCH", "", "", "", ""));
//		ifLogicData.add(new MethodDesign("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""));
//		ifLogicData.add(new MethodDesign("", ConstantManager.BLOCK_STEP_ONE, "FINALLY", "", "", "", ""));
//		ifLogicData.add(
//				new MethodDesign(ConstantManager.BLOCK_END_TAG, ConstantManager.BLOCK_STEP_ONE, "END", "", "", "", ""));
//		return ifLogicData;
//	}
//
//	@Override
//	public List<MethodDesign> CreateStepTwoBlock(String jyoken) {
//		List<MethodDesign> ifLogicData = new ArrayList<MethodDesign>();
//		ifLogicData.add(new MethodDesign(ConstantManager.BLOCK_START_TAG, ConstantManager.BLOCK_STEP_TWO, "", "TRY", "",
//				jyoken, ""));
//		ifLogicData.add(new MethodDesign("", ConstantManager.BLOCK_STEP_TWO, "", "CATCH", "", "", ""));
//		ifLogicData.add(new MethodDesign("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""));
//		ifLogicData.add(new MethodDesign("", ConstantManager.BLOCK_STEP_TWO, "", "FINALLY", "", "", ""));
//		ifLogicData.add(
//				new MethodDesign(ConstantManager.BLOCK_END_TAG, ConstantManager.BLOCK_STEP_TWO, "", "END", "", "", ""));
//		return ifLogicData;
//	}
//
//	@Override
//	public List<MethodDesign> CreateStepThreeBlock(String jyoken) {
//		List<MethodDesign> ifLogicData = new ArrayList<MethodDesign>();
//		ifLogicData.add(new MethodDesign(ConstantManager.BLOCK_START_TAG, ConstantManager.BLOCK_STEP_THREE, "", "", "TRY",
//				jyoken, ""));
//		ifLogicData.add(new MethodDesign("", ConstantManager.BLOCK_STEP_THREE, "", "", "CATCH", "", ""));
//		ifLogicData.add(new MethodDesign("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""));
//		ifLogicData.add(new MethodDesign("", ConstantManager.BLOCK_STEP_THREE, "", "", "FINALLY", "", ""));
//		ifLogicData.add(
//				new MethodDesign(ConstantManager.BLOCK_END_TAG, ConstantManager.BLOCK_STEP_THREE, "", "", "END", "", ""));
//
//		return ifLogicData;
//	}
//
//	@Override
//	public List<MethodDesign> CreateLevel1Block(String jyoken) {
//		// TODO Auto-generated method stub
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
