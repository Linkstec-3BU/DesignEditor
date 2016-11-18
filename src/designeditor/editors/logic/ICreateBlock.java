package designeditor.editors.logic;

import java.util.List;

import designeditor.editors.bean.MethodDesign;

public interface ICreateBlock {
	
	void CreateLevel1Block(List<MethodDesign>  methodDesignList, int index);
	
	void CreateLevel2Block(List<MethodDesign>  methodDesignList, int index);
	
	void CreateLevel3Block(List<MethodDesign>  methodDesignList, int index);
}
