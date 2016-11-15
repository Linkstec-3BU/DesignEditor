package designeditor.editors.provider;



import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import designeditor.editors.bean.EditArea;

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
        EditArea edit = (EditArea) element;
        
        if(columnIndex == 1){
            return edit.getLogicOne();
        }
        
        if(columnIndex == 2){
            return edit.getLogicTwo();
        }
        
        if(columnIndex == 3){
            return edit.getLogicThree();
        }
        
        if(columnIndex == 4){
            return edit.getEditArea();
        }
        if(columnIndex == 5){
            return edit.getComment();
        }
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
