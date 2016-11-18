package designeditor.editors.provider;



import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import designeditor.editors.bean.MethodDesign;

/**
 * 各セルに表示内容設定用
 * @author daizhu
 */
public class MethodTableViewerLabelProvider implements ITableLabelProvider{
    @Override
    public Image getColumnImage(Object element, int columnIndex) {
        return null;
    }

    /**
     * 各セルにどの内容が表示することを設定する
     * @param element 行対象
     * @param columnIndex 列番号
     * @return 表示内容
     */
    @Override
    public String getColumnText(Object element, int columnIndex) {
        MethodDesign edit = (MethodDesign) element;
        
        if(columnIndex == 1){
            return edit.getLevel1Display();
        }
        
        if(columnIndex == 2){
            return edit.getLevel2Display();
        }
        
        if(columnIndex == 3){
            return edit.getLevel3Display();
        }
        
        if(columnIndex == 4){
            return edit.getDetailDisplay();
        }
        
        if(columnIndex == 5){
            return edit.getComment();
        }
//        
//        if(columnIndex == 6){
//            return edit.getBlockUniqueId();
//        }
//        
//        if(columnIndex == 7){
//            return edit.getParentBlockUniqueId();
//        }
//        
//        if(columnIndex == 8){
//            return edit.getNextBlockUniqueId();
//        }
//        
//        if(columnIndex == 9){
//            return edit.getBlockLevel();
//        }
//        
//        if(columnIndex == 10){
//            return edit.getBlockType();
//        }
        return "";
    }
    
    @Override
    public void addListener(ILabelProviderListener listener) {
        
    }
    
    @Override
    public void dispose() {
        
    }
    
    @Override
    public boolean isLabelProperty(Object element, String property) {
        return false;
    }
    
    @Override
    public void removeListener(ILabelProviderListener listener) {
        
    }
}
