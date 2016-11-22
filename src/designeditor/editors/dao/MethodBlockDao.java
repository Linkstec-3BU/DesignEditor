package designeditor.editors.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import designeditor.editors.bean.MethodDesign;
import designeditor.editors.models.TMethodBlock;
import designeditor.util.DbUtil;
import designeditor.util.StringUtil;

public class MethodBlockDao {
	public List<TMethodBlock> selectByFk(String methodUniqueId) {
		List<TMethodBlock> methodBlockList = new ArrayList<TMethodBlock>();
		String sql = "SELECT t FROM TMethodBlock t where t.methodUniqueId = ?1";
		String parameters[] = { methodUniqueId };
		methodBlockList = (List<TMethodBlock>) DbUtil.selectByOthers(sql, parameters);
		return methodBlockList;
	}
	
	public TMethodBlock selectByAfterBlockUniqueId(String methodUniqueId,BigInteger afterBlockUniqueId) {
		List<TMethodBlock> methodBlockList = new ArrayList<TMethodBlock>();
		String sql = "SELECT t FROM TMethodBlock t where t.methodUniqueId = ?1 and t.blockUniqueId = ?2";
		Object parameters[] = {methodUniqueId, afterBlockUniqueId };
		methodBlockList = (List<TMethodBlock>) DbUtil.selectByOthers(sql, parameters);
		return methodBlockList.get(0);
	}

	public MethodDesign modelToBean(TMethodBlock methodBlock) {
		MethodDesign methodDesign = new MethodDesign(methodBlock.getBlockUniqueId().toString(), "", "",
				methodBlock.getBlockLevel(), methodBlock.getMethodUniqueId());
		if (methodBlock.getFatherBlockUniqueId() == null) {
			methodDesign.setFatherBlockUniqueId(null);
		} else {
			methodDesign.setFatherBlockUniqueId(methodBlock.getFatherBlockUniqueId().toString());
		}
		if (methodBlock.getAfterBlockUniqueId() == null) {
			methodDesign.setAfterBlockUniqueId(null);
		} else {
			methodDesign.setAfterBlockUniqueId(methodBlock.getAfterBlockUniqueId().toString());
		}
		methodDesign.setBlockType(methodBlock.getBlockType());
		methodDesign.setComment(methodBlock.getComment());
		methodDesign.setDetailDisplay(methodBlock.getDetailDisplay());
		methodDesign.setLevel1Display(methodBlock.getLevel1Display());
		methodDesign.setLevel2Display(methodBlock.getLevel2Display());
		methodDesign.setLevel3Display(methodBlock.getLevel3Display());
		return methodDesign;
	}

	public List<MethodDesign> modelToBean(List<TMethodBlock> methodBlockList) {
		List<MethodDesign> methodDesignList = new ArrayList<MethodDesign>();
		for (TMethodBlock methodBlock : methodBlockList) {
			MethodDesign methodDesign = modelToBean(methodBlock);
			methodDesignList.add(methodDesign);
		}

		return methodDesignList;
	}

	public TMethodBlock beanToModel(MethodDesign methodDesign) {
		TMethodBlock methodBlock = new TMethodBlock();
		if (StringUtil.NotNullAndEmpty(methodDesign.getAfterBlockUniqueId())) {
			methodBlock.setAfterBlockUniqueId(new BigInteger(methodDesign.getAfterBlockUniqueId()));
		} else {
			methodBlock.setAfterBlockUniqueId(null);
		}
		methodBlock.setBlockUniqueId(new BigInteger(methodDesign.getBlockUniqueId()));
		if (StringUtil.NotNullAndEmpty(methodDesign.getFatherBlockUniqueId())) {
			methodBlock.setFatherBlockUniqueId(new BigInteger(methodDesign.getFatherBlockUniqueId()));
		} else {
			methodBlock.setFatherBlockUniqueId(null);
		}
		methodBlock.setBlockType(methodDesign.getBlockType());
		methodBlock.setComment(methodDesign.getComment());
		methodBlock.setDetailDisplay(methodDesign.getDetailDisplay());
		methodBlock.setLevel1Display(methodDesign.getLevel1Display());
		methodBlock.setLevel2Display(methodDesign.getLevel2Display());
		methodBlock.setLevel3Display(methodDesign.getLevel3Display());
		methodBlock.setMethodUniqueId(methodDesign.getMethodUniqueId());
		methodBlock.setBlockLevel(methodDesign.getBlockLevel());
		return methodBlock;
	}
}
