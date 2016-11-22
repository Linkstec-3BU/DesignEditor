package designeditor.editors.blocks;

import java.util.List;

import designeditor.editors.bean.MethodDesign;

public interface ICreateBlock {
	
	void CreateLevel1Block(List<MethodDesign>  methodDesignList, int index,String methodUniqueId);
	
	void CreateLevel2Block(List<MethodDesign>  methodDesignList, int index,String methodUniqueId);
	
	void CreateLevel3Block(List<MethodDesign>  methodDesignList, int index,String methodUniqueId);
}
