package designeditor.editors.logic;

import java.util.List;

import designeditor.editors.bean.EditArea;

public interface ICreateBlock {
	
	List<EditArea> CreateStepOneBlock(String jyoken);
	
	List<EditArea> CreateStepTwoBlock(String jyoken);
	
	List<EditArea> CreateStepThreeBlock(String jyoken);
}
