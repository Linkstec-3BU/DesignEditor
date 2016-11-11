package designeditor.editors.menu;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionGroup;

import designeditor.editors.bean.EditArea;
import designeditor.editors.constant.ConstantManager;
import designeditor.editors.dialog.AddCalculusDialog;
import designeditor.editors.dialog.AddForeachDialog;
import designeditor.editors.dialog.AddThrowDialog;
import designeditor.editors.dialog.SelectJyokenDialog;

//继承ActionGroup
public class RightMenuManager extends ActionGroup {
	private TableViewer tableViewer;

	/**
	 * 鼠标右键有菜单,首先要 生成菜单Menu,并将两个Action传入
	 */
	public void fillContextMenu() {// I开头的一般是接口的意思.
		// 加入两个Action对象到菜单管理器中
		MenuManager menuManager = new MenuManager();
		menuManager.add(new AddEmptyBlockAction());
		menuManager.add(new RemoveEmptyBlockAction());

		MenuManager controlMenu = new MenuManager(ConstantManager.ADD_CONTROY_BLOCK, null);
		controlMenu.add(new AddSelectBlockAction());
		controlMenu.add(new AddForeachBlockAction());
		controlMenu.add(new AddThrowBlockAction());
		menuManager.add(controlMenu);

		MenuManager calculusMenu = new MenuManager(ConstantManager.ADD_CONTROY_BLOCK, null);
		calculusMenu.add(new AddDefineVarBlockAction());
		calculusMenu.add(new AddFormulaBlockAction());
		calculusMenu.add(new AddCallBlockAction());
		menuManager.add(calculusMenu);

		menuManager.add(new AddReturnBlockAction());
		menuManager.add(new AddBreakBlockAction());
		menuManager.add(new AddContinueBlockAction());

		Table table = tableViewer.getTable();
		Menu menu = menuManager.createContextMenu(table);
		table.setMenu(menu);

	}

	/**
	 * 用来接受TableViewer对象的构造函数。 因为在Action会要使用到TableViewer对象 所以一定要把TableViewer传进来。
	 */
	public RightMenuManager(TableViewer tableViewer) {
		this.tableViewer = tableViewer;
	}

	private final class AddEmptyBlockAction extends Action {
		public AddEmptyBlockAction() {
			setText(ConstantManager.ADD_EMPTY_BLOCK);
		}

		public void run() {
			Table table = tableViewer.getTable();
			EditArea edit = new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", "");
			tableViewer.insert(edit, table.getSelectionIndex());
		}
	}

	/**
	 * 
	 */
	private class RemoveEmptyBlockAction extends Action {
		public RemoveEmptyBlockAction() {
			setText(ConstantManager.REMOVE_EMPTY_BLOCK);
		}

		/**
		 * 继承自Action的方法,动作代码写在此方法中.
		 */
		public void run() {
			Table table = tableViewer.getTable();
			IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
			EditArea edit = (EditArea) (selection.getFirstElement());
			int index = table.getSelectionIndex();

			List<EditArea> ifLogicData = new ArrayList<EditArea>();
			if (ConstantManager.BLOCK_STEP_ZERO.equals(edit.getStep())) {
				ifLogicData.add(edit);
			} else {
				for (int j = index; j < table.getItemCount(); j++) {
					EditArea item = (EditArea) table.getItem(j).getData();
					ifLogicData.add(item);
					if (ConstantManager.BLOCK_END_TAG.equals(item.getTag()) && edit.getStep().equals(item.getStep())) {
						break;
					}
				}
				for (int j = index; j >= 0; j--) {
					EditArea item = (EditArea) table.getItem(j).getData();
					ifLogicData.add(item);
					if (ConstantManager.BLOCK_START_TAG.equals(item.getTag())
							&& edit.getStep().equals(item.getStep())) {
						break;
					}
				}

			}

			if (edit == null) {
				MessageDialog.openInformation(null, null, "レコードを選択してください");
			} else {
				for (int i = 0; i < ifLogicData.size(); i++) {
					tableViewer.remove(ifLogicData.get(i));
				}
			}
		}
	}

	/**
	 * 
	 */
	private final class AddSelectBlockAction extends Action {
		public AddSelectBlockAction() {
			setText(ConstantManager.ADD_SELECT_BLOCK);
		}

		public void run() {
			String step = getNewRowStep();
			SelectJyokenDialog c = new SelectJyokenDialog(
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), tableViewer,step);
			c.open();
		}
	}

	/**
	 * 
	 */
	private final class AddForeachBlockAction extends Action {
		public AddForeachBlockAction() {
			setText(ConstantManager.ADD_FOREACH_BLOCK);
		}

		public void run() {
			String step = getNewRowStep();
			AddForeachDialog c = new AddForeachDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
					tableViewer,step);
			c.open();
		}
	}

	/**
	 * 
	 */
	private final class AddThrowBlockAction extends Action {
		public AddThrowBlockAction() {
			setText(ConstantManager.ADD_THROW_BLOCK);
		}

		public void run() {
			String step = getNewRowStep();
			AddThrowDialog c = new AddThrowDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
					tableViewer,step);
			c.open();
		}
	}

	private final class AddDefineVarBlockAction extends Action {
		public AddDefineVarBlockAction() {
			setText(ConstantManager.ADD_DEFINE_VAR_BLOCK);
		}

		public void run() {
			AddCalculusDialog c = new AddCalculusDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
					tableViewer);
			c.open();
		}
	}

	/**
	 * 
	 */
	private final class AddFormulaBlockAction extends Action {
		public AddFormulaBlockAction() {
			setText(ConstantManager.ADD_FORMULA_BLOCK);
		}

		public void run() {
			AddCalculusDialog c = new AddCalculusDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
					tableViewer);
			c.open();
		}
	}

	/**
	 * 
	 */
	private final class AddCallBlockAction extends Action {
		public AddCallBlockAction() {
			setText(ConstantManager.ADD_CALL_BLOCK);
		}

		public void run() {
			AddCalculusDialog c = new AddCalculusDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
					tableViewer);
			c.open();
		}
	}

	private final class AddReturnBlockAction extends Action {
		public AddReturnBlockAction() {
			setText(ConstantManager.ADD_RETURN_BLOCK);
		}

		public void run() {
			Table table = tableViewer.getTable();
			EditArea edit = new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "return", "");
			tableViewer.insert(edit, table.getSelectionIndex());
		}
	}

	/**
	 * 
	 */
	private final class AddBreakBlockAction extends Action {
		public AddBreakBlockAction() {
			setText(ConstantManager.ADD_BREAK_BLOCK);
		}

		public void run() {
			Table table = tableViewer.getTable();
			EditArea edit = new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "break", "");
			tableViewer.insert(edit, table.getSelectionIndex());
		}
	}

	/**
	 * 
	 */
	private final class AddContinueBlockAction extends Action {
		public AddContinueBlockAction() {
			setText(ConstantManager.ADD_CONTINUE_BLOCK);
		}

		public void run() {
			Table table = tableViewer.getTable();
			EditArea edit = new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "continue", "");
			tableViewer.insert(edit, table.getSelectionIndex());
		}
	}

	private String getNewRowStep() {
		Table table = tableViewer.getTable();
		int index = table.getSelectionIndex();
		String step = ConstantManager.BLOCK_STEP_ONE;
		for (int j = index; j >= 0; j--) {
			EditArea item = (EditArea) table.getItem(j).getData();
			if (ConstantManager.BLOCK_END_TAG.equals(item.getTag())) {
				step = item.getStep();
				break;
			} else if (ConstantManager.BLOCK_START_TAG.equals(item.getTag())) {
				if (ConstantManager.BLOCK_STEP_ONE.equals(item.getStep())) {
					step = ConstantManager.BLOCK_STEP_TWO;
				} else if (ConstantManager.BLOCK_STEP_TWO.equals(item.getStep())) {
					step = ConstantManager.BLOCK_STEP_THREE;
				}
				break;
			}
		}
		return step;
	}

}
