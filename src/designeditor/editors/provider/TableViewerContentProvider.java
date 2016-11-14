package designeditor.editors.provider;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * setInputのパラメータを転換用
 * @author daizhu
 */
public class TableViewerContentProvider implements IStructuredContentProvider {

    @Override
    public void dispose() {
        
    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        
    }
    
    
    @Override
    public Object[] getElements(Object inputElement) {
        if(inputElement instanceof List){
            return ((List)inputElement).toArray();
        }else{
            return new Object[0];
        }
    }
}