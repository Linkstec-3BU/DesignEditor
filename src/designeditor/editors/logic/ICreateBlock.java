package designeditor.editors.logic;

import java.util.List;

import designeditor.editors.bean.MethodDesign;

public interface ICreateBlock {
	
	List<MethodDesign> CreateLevel1Block(String jyoken);
	
	List<MethodDesign> CreateLevel2Block(String jyoken);
	
	List<MethodDesign> CreateLevel3Block(String jyoken);
}
