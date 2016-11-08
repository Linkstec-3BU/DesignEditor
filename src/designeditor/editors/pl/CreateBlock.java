package designeditor.editors.pl;

import javafx.collections.ObservableList;

public interface CreateBlock {
	
	ObservableList<EditArea> CreateStepOneBlock(String jyoken);
	
	ObservableList<EditArea> CreateStepTwoBlock(String jyoken);
	
	ObservableList<EditArea> CreateStepThreeBlock(String jyoken);
}
