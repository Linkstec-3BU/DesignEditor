package designeditor.editors.menu;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.actions.ActionGroup;

import designeditor.editors.bean.EditArea;
import designeditor.editors.constant.ConstantManager;
import designeditor.editors.dialog.AddCalculusDialog;
import designeditor.editors.dialog.AddForeachDialog;
import designeditor.editors.dialog.AddSelectDialog;
import designeditor.editors.dialog.AddThrowDialog;


public class RightMenuManager extends ActionGroup {
	private TableViewer tableViewer;
	private List<EditArea> editAreaList;
	private Shell shell;

	/**
	 * 右メニュ-作成
	 * @author daizhu
	 * 
	 */
	public void fillContextMenu() {
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

	public RightMenuManager(TableViewer tableViewer,List<EditArea> editAreaList,Shell shell) {
		this.tableViewer = tableViewer;
		this.editAreaList = editAreaList;
		this.shell = shell;
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
			EditArea edit = new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", "");
			editAreaList.add(index,edit);
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
					editAreaList.removeAll(ifLogicData);
					tableViewer.refresh();
				}
			}
		}
	}

	/**
	 * 条件分岐Brock追加(if..else if)文)
	 * @author daizhu
	 * 
	 */
	private final class AddSelectBlockAction extends Action {
		public AddSelectBlockAction() {
			setText(ConstantManager.ADD_SELECT_BLOCK);
		}

		public void run() {
			String step = getNewRowStep();
			AddSelectDialog c = new AddSelectDialog(
					shell, tableViewer,step,editAreaList);
			c.open();
		}
	}

	/**
	 * 繰り返しBrock追加(for-each文) 
	 * @author daizhu
	 * 
	 */
	private final class AddForeachBlockAction extends Action {
		public AddForeachBlockAction() {
			setText(ConstantManager.ADD_FOREACH_BLOCK);
		}

		public void run() {
			String step = getNewRowStep();
			AddForeachDialog c = new AddForeachDialog(shell,
					tableViewer,step,editAreaList);
			c.open();
		}
	}

	/**
	 * 例外処理Brock追加(try..catch..finally文) 
	 * @author daizhu
	 * 
	 */
	private final class AddThrowBlockAction extends Action {
		public AddThrowBlockAction() {
			setText(ConstantManager.ADD_THROW_BLOCK);
		}

		public void run() {
			String step = getNewRowStep();
			AddThrowDialog c = new AddThrowDialog(shell,
					tableViewer,step,editAreaList);
			c.open();
		}
	}

	/**
	 * 変数宣言Brock追加 
	 * @author daizhu
	 *
	 */
	private final class AddDefineVarBlockAction extends Action {
		public AddDefineVarBlockAction() {
			setText(ConstantManager.ADD_DEFINE_VAR_BLOCK);
		}

		public void run() {
			AddCalculusDialog c = new AddCalculusDialog(shell,
					tableViewer,editAreaList);
			c.open();
		}
	}

	/**
	 * 計算式Brock追加 
	 * @author daizhu
	 */
	private final class AddFormulaBlockAction extends Action {
		public AddFormulaBlockAction() {
			setText(ConstantManager.ADD_FORMULA_BLOCK);
		}

		public void run() {
			AddCalculusDialog c = new AddCalculusDialog(shell,
					tableViewer,editAreaList);
			c.open();
		}
	}

	/**
	 * 呼出Brock追加
	 * @author daizhu
	 */
	private final class AddCallBlockAction extends Action {
		public AddCallBlockAction() {
			setText(ConstantManager.ADD_CALL_BLOCK);
		}

		public void run() {
			AddCalculusDialog c = new AddCalculusDialog(shell,
					tableViewer,editAreaList);
			c.open();
		}
	}

	/**
	 * Return Brock追加
	 * @author daizhu
	 *
	 */
	private final class AddReturnBlockAction extends Action {
		public AddReturnBlockAction() {
			setText(ConstantManager.ADD_RETURN_BLOCK);
		}

		public void run() {
			Table table = tableViewer.getTable();
			int index = table.getSelectionIndex() >= 0 ? table.getSelectionIndex() : 0;
			EditArea edit = new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "return", "");
			editAreaList.add(index,edit);
			tableViewer.refresh();
		}
	}

	/**
	 * Break Brock追加
	 * @author daizhu
	 */
	private final class AddBreakBlockAction extends Action {
		public AddBreakBlockAction() {
			setText(ConstantManager.ADD_BREAK_BLOCK);
		}

		public void run() {
			Table table = tableViewer.getTable();
			int index = table.getSelectionIndex() >= 0 ? table.getSelectionIndex() : 0;
			EditArea edit = new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "break", "");
			editAreaList.add(index,edit);
			tableViewer.refresh();
		}
	}

	/**
	 * Continue Brock追加
	 * @author daizhu
	 * 
	 */
	private final class AddContinueBlockAction extends Action {
		public AddContinueBlockAction() {
			setText(ConstantManager.ADD_CONTINUE_BLOCK);
		}

		public void run() {
			Table table = tableViewer.getTable();
			int index = table.getSelectionIndex() >= 0 ? table.getSelectionIndex() : 0;
			EditArea edit = new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "continue", "");
			editAreaList.add(index,edit);
			tableViewer.refresh();
		}
	}

	/**
	 * 新規追加行のstepを取得する
	 * @author daizhu
	 * 
	 */
	private String getNewRowStep() {
		Table table = tableViewer.getTable();
		int index = table.getSelectionIndex();
		String step = ConstantManager.BLOCK_STEP_ONE;
		for (int j = index-1; j >= 0; j--) {
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
