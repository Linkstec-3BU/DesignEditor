package designeditor.editors.menu;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.actions.ActionGroup;

import designeditor.editors.bean.Module;
import designeditor.editors.bean.ModuleMethod;
import designeditor.editors.constant.ConstantManager;


public class ClassDesignRightMenuManager extends ActionGroup {
	private TableViewer tableViewer;
	private List<Module> moduleList;
		
	public ClassDesignRightMenuManager(TableViewer tableViewer,List<Module> moduleList) {
		this.tableViewer = tableViewer;
		this.moduleList = moduleList;
	}
	
	/**
	 * 右メニュ-作成
	 * @author daizhu
	 * 
	 */
	public void fillContextMenu() {
		MenuManager menuManager = new MenuManager();
		menuManager.add(new AddEmptyBlockAction());
		menuManager.add(new RemoveEmptyBlockAction());

		Table table = tableViewer.getTable();
		Menu menu = menuManager.createContextMenu(table);
		table.setMenu(menu);
	}	

	/**
	 * 空Brock追加用
	 * @author daizhu
	 * 
	 */
	private final class AddEmptyBlockAction extends Action {
		public AddEmptyBlockAction() {
			setText(ConstantManager.ADD_EMPTY_BLOCK);
		}

		public void run() {
			Table table = tableViewer.getTable();
			int index = table.getSelectionIndex() >= 0 ? table.getSelectionIndex() : 0;
			Module edit = new Module("", "", "", "", "", "", new ArrayList<ModuleMethod>());
			moduleList.add(index,edit);
			tableViewer.refresh();
		}
	}

	/**
	 * Brock削除
	 * @author daizhu
	 * 
	 */
	private class RemoveEmptyBlockAction extends Action {
		public RemoveEmptyBlockAction() {
			setText(ConstantManager.REMOVE_EMPTY_BLOCK);
		}

		public void run() {
			Table table = tableViewer.getTable();
			int index = table.getSelectionIndex();
			
			if (index < 0) {
				MessageDialog.openInformation(null, null, "レコードを選択してください");
			} else {
				
					moduleList.remove(index);
					tableViewer.refresh();
				
			}
		}
	}
}
