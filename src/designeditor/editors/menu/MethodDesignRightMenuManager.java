package designeditor.editors.menu;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.actions.ActionGroup;

import designeditor.editors.bean.MethodDesign;
import designeditor.editors.constant.ConstantManager;
import designeditor.editors.dialog.AddCalculusDialog;
import designeditor.util.MethodDesignUtil;

public class MethodDesignRightMenuManager extends ActionGroup {
	private TableViewer tableViewer;
	private List<MethodDesign> methodDesignList;
	private Shell shell;

	public MethodDesignRightMenuManager(TableViewer tableViewer, List<MethodDesign> methodDesignList, Shell shell) {
		this.tableViewer = tableViewer;
		this.methodDesignList = methodDesignList;
		this.shell = shell;
	}

	/**
	 * 右メニュ-作成
	 * 
	 * @author daizhu
	 * 
	 */
	public void fillContextMenu() {
		MenuManager menuManager = new MenuManager();
		menuManager.add(new AddEmptyBlockAction());
		menuManager.add(new RemoveEmptyBlockAction());

		MenuManager controlMenu = new MenuManager(ConstantManager.ADD_CONTROY_BLOCK, null);
		controlMenu.add(new AddSelectBlockAction());
		controlMenu.add(new AddSelectElseIfBlockAction());
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
	 * 空Brock追加用
	 * 
	 * @author daizhu
	 * 
	 */
	private final class AddEmptyBlockAction extends Action {
		public AddEmptyBlockAction() {
			setText(ConstantManager.ADD_EMPTY_BLOCK);
		}

		public void run() {
			Table table = tableViewer.getTable();
			int index = table.getSelectionIndex();

			if (index < 0) {
				MessageDialog.openInformation(null, null, "レコードを選択してください");
				return;
			} else {
				MethodDesignUtil.addCommonBlock(methodDesignList, index);
				MethodDesign newMethodDesign = methodDesignList.get(index);
				newMethodDesign.setBlockType(ConstantManager.BLOCK_TYPE_NORMAL);
				methodDesignList.set(index, newMethodDesign);
			}

			tableViewer.refresh();
		}
	}

	/**
	 * Brock削除
	 * 
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
			MethodDesign methodDesign = methodDesignList.get(index);

			List<MethodDesign> newMethodDesignList = new ArrayList<MethodDesign>();

			if (index < 0) {
				MessageDialog.openInformation(null, null, "レコードを選択してください");
			} else {
				methodDesignList.removeAll(newMethodDesignList);
				tableViewer.refresh();
			}
		}
	}

	/**
	 * 条件分岐Brock追加(if..else)文)
	 * 
	 * @author daizhu
	 * 
	 */
	private final class AddSelectBlockAction extends Action {
		public AddSelectBlockAction() {
			setText(ConstantManager.ADD_SELECT_BLOCK);
		}

		public void run() {
			Table table = tableViewer.getTable();
			int index = table.getSelectionIndex();

			if (index < 0) {
				MessageDialog.openInformation(null, null, "レコードを選択してください");
				return;
			} else {
				MethodDesignUtil.addCommonBlock(methodDesignList, index);
				MethodDesign newMethodDesign = methodDesignList.get(index);
				newMethodDesign.setBlockType(ConstantManager.BLOCK_TYPE_IF);
				if (ConstantManager.BLOCK_LEVEL_ONE.equals(newMethodDesign.getBlockLevel())) {
					newMethodDesign.setLevel1Display("IF");
				} else if (ConstantManager.BLOCK_LEVEL_TWO.equals(newMethodDesign.getBlockLevel())) {
					newMethodDesign.setLevel2Display("IF");
				} else if (ConstantManager.BLOCK_LEVEL_THREE.equals(newMethodDesign.getBlockLevel())) {
					newMethodDesign.setLevel3Display("IF");
				}
				methodDesignList.set(index, newMethodDesign);

				MethodDesignUtil.addSubBlock(methodDesignList, index);
				MethodDesignUtil.addCommonBlock(methodDesignList, index + 1);

				MethodDesignUtil.addCommonBlock(methodDesignList, index + 3);
				newMethodDesign = methodDesignList.get(index + 3);
				newMethodDesign.setBlockType(ConstantManager.BLOCK_TYPE_IF);
				
				if (ConstantManager.BLOCK_LEVEL_ONE.equals(newMethodDesign.getBlockLevel())) {
					newMethodDesign.setLevel1Display("ELSE");
				} else if (ConstantManager.BLOCK_LEVEL_TWO.equals(newMethodDesign.getBlockLevel())) {
					newMethodDesign.setLevel2Display("ELSE");
				} else if (ConstantManager.BLOCK_LEVEL_THREE.equals(newMethodDesign.getBlockLevel())) {
					newMethodDesign.setLevel3Display("ELSE");
				}
				
				methodDesignList.set(index + 3, newMethodDesign);

				MethodDesignUtil.addSubBlock(methodDesignList, index + 3);
				MethodDesignUtil.addCommonBlock(methodDesignList, index + 4);
			}

			tableViewer.refresh();

		}
	}

	/**
	 * 条件分岐Brock追加(else if)文)
	 * 
	 * @author daizhu
	 * 
	 */
	private final class AddSelectElseIfBlockAction extends Action {
		public AddSelectElseIfBlockAction() {
			setText(ConstantManager.ADD_SELECT_ELSE_IF_BLOCK);
		}

		public void run() {
			Table table = tableViewer.getTable();
			int index = table.getSelectionIndex();

			if (index < 0) {
				MessageDialog.openInformation(null, null, "レコードを選択してください");
				return;
			} else {
				MethodDesignUtil.addCommonBlock(methodDesignList, index);
				MethodDesign newMethodDesign = methodDesignList.get(index);
				newMethodDesign.setBlockType(ConstantManager.BLOCK_TYPE_IF);
				
				if (ConstantManager.BLOCK_LEVEL_ONE.equals(newMethodDesign.getBlockLevel())) {
					newMethodDesign.setLevel1Display("ELSE IF");
				} else if (ConstantManager.BLOCK_LEVEL_TWO.equals(newMethodDesign.getBlockLevel())) {
					newMethodDesign.setLevel2Display("ELSE IF");
				} else if (ConstantManager.BLOCK_LEVEL_THREE.equals(newMethodDesign.getBlockLevel())) {
					newMethodDesign.setLevel3Display("ELSE IF");
				}
				methodDesignList.set(index, newMethodDesign);

				MethodDesignUtil.addSubBlock(methodDesignList, index);
				MethodDesignUtil.addCommonBlock(methodDesignList, index + 1);
			}

			tableViewer.refresh();
		}
	}

	/**
	 * 繰り返しBrock追加(for-each文)
	 * 
	 * @author daizhu
	 * 
	 */
	private final class AddForeachBlockAction extends Action {
		public AddForeachBlockAction() {
			setText(ConstantManager.ADD_FOREACH_BLOCK);
		}

		public void run() {
			Table table = tableViewer.getTable();
			int index = table.getSelectionIndex();

			if (index < 0) {
				MessageDialog.openInformation(null, null, "レコードを選択してください");
				return;
			} else {
				MethodDesignUtil.addCommonBlock(methodDesignList, index);
				MethodDesign newMethodDesign = methodDesignList.get(index);
				newMethodDesign.setBlockType(ConstantManager.BLOCK_TYPE_LOOP);
				if (ConstantManager.BLOCK_LEVEL_ONE.equals(newMethodDesign.getBlockLevel())) {
					newMethodDesign.setLevel1Display("FOR");
				} else if (ConstantManager.BLOCK_LEVEL_TWO.equals(newMethodDesign.getBlockLevel())) {
					newMethodDesign.setLevel2Display("FOR");
				} else if (ConstantManager.BLOCK_LEVEL_THREE.equals(newMethodDesign.getBlockLevel())) {
					newMethodDesign.setLevel3Display("FOR");
				}
				methodDesignList.set(index, newMethodDesign);

				MethodDesignUtil.addSubBlock(methodDesignList, index);
				MethodDesignUtil.addCommonBlock(methodDesignList, index + 1);
			}

			tableViewer.refresh();
		}
	}

	/**
	 * 例外処理Brock追加(try..catch..finally文)
	 * 
	 * @author daizhu
	 * 
	 */
	private final class AddThrowBlockAction extends Action {
		public AddThrowBlockAction() {
			setText(ConstantManager.ADD_THROW_BLOCK);
		}

		public void run() {
			Table table = tableViewer.getTable();
			int index = table.getSelectionIndex();

			if (index < 0) {
				MessageDialog.openInformation(null, null, "レコードを選択してください");
				return;
			} else {
				MethodDesignUtil.addCommonBlock(methodDesignList, index);
				MethodDesign newMethodDesign = methodDesignList.get(index);
				newMethodDesign.setBlockType(ConstantManager.BLOCK_TYPE_THROW);
				
				if (ConstantManager.BLOCK_LEVEL_ONE.equals(newMethodDesign.getBlockLevel())) {
					newMethodDesign.setLevel1Display("TRY");
				} else if (ConstantManager.BLOCK_LEVEL_TWO.equals(newMethodDesign.getBlockLevel())) {
					newMethodDesign.setLevel2Display("TRY");
				} else if (ConstantManager.BLOCK_LEVEL_THREE.equals(newMethodDesign.getBlockLevel())) {
					newMethodDesign.setLevel3Display("TRY");
				}
				methodDesignList.set(index, newMethodDesign);

				MethodDesignUtil.addSubBlock(methodDesignList, index);
				MethodDesignUtil.addCommonBlock(methodDesignList, index + 1);

				MethodDesignUtil.addCommonBlock(methodDesignList, index + 3);
				newMethodDesign = methodDesignList.get(index + 3);
				newMethodDesign.setBlockType(ConstantManager.BLOCK_TYPE_THROW);
				
				if (ConstantManager.BLOCK_LEVEL_ONE.equals(newMethodDesign.getBlockLevel())) {
					newMethodDesign.setLevel1Display("CATCH");
				} else if (ConstantManager.BLOCK_LEVEL_TWO.equals(newMethodDesign.getBlockLevel())) {
					newMethodDesign.setLevel2Display("CATCH");
				} else if (ConstantManager.BLOCK_LEVEL_THREE.equals(newMethodDesign.getBlockLevel())) {
					newMethodDesign.setLevel3Display("CATCH");
				}
				methodDesignList.set(index + 3, newMethodDesign);

				MethodDesignUtil.addSubBlock(methodDesignList, index + 3);
				MethodDesignUtil.addCommonBlock(methodDesignList, index + 4);

				MethodDesignUtil.addCommonBlock(methodDesignList, index + 6);
				newMethodDesign = methodDesignList.get(index + 6);
				newMethodDesign.setBlockType(ConstantManager.BLOCK_TYPE_THROW);
				
				if (ConstantManager.BLOCK_LEVEL_ONE.equals(newMethodDesign.getBlockLevel())) {
					newMethodDesign.setLevel1Display("THROW");
				} else if (ConstantManager.BLOCK_LEVEL_TWO.equals(newMethodDesign.getBlockLevel())) {
					newMethodDesign.setLevel2Display("THROW");
				} else if (ConstantManager.BLOCK_LEVEL_THREE.equals(newMethodDesign.getBlockLevel())) {
					newMethodDesign.setLevel3Display("THROW");
				}
				methodDesignList.set(index + 6, newMethodDesign);

				MethodDesignUtil.addSubBlock(methodDesignList, index + 6);
				MethodDesignUtil.addCommonBlock(methodDesignList, index + 7);
			}

			tableViewer.refresh();
		}
	}

	/**
	 * 変数宣言Brock追加
	 * 
	 * @author daizhu
	 *
	 */
	private final class AddDefineVarBlockAction extends Action {
		public AddDefineVarBlockAction() {
			setText(ConstantManager.ADD_DEFINE_VAR_BLOCK);
		}

		public void run() {

			AddCalculusDialog c = new AddCalculusDialog(shell, tableViewer, methodDesignList);
			c.open();
		}
	}

	/**
	 * 計算式Brock追加
	 * 
	 * @author daizhu
	 */
	private final class AddFormulaBlockAction extends Action {
		public AddFormulaBlockAction() {
			setText(ConstantManager.ADD_FORMULA_BLOCK);
		}

		public void run() {
			AddCalculusDialog c = new AddCalculusDialog(shell, tableViewer, methodDesignList);
			c.open();
		}
	}

	/**
	 * 呼出Brock追加
	 * 
	 * @author daizhu
	 */
	private final class AddCallBlockAction extends Action {
		public AddCallBlockAction() {
			setText(ConstantManager.ADD_CALL_BLOCK);
		}

		public void run() {
			AddCalculusDialog c = new AddCalculusDialog(shell, tableViewer, methodDesignList);
			c.open();
		}
	}

	/**
	 * Return Brock追加
	 * 
	 * @author daizhu
	 *
	 */
	private final class AddReturnBlockAction extends Action {
		public AddReturnBlockAction() {
			setText(ConstantManager.ADD_RETURN_BLOCK);
		}

		public void run() {
			Table table = tableViewer.getTable();
			int index = table.getSelectionIndex();

			if (index < 0) {
				MessageDialog.openInformation(null, null, "レコードを選択してください");
				return;
			} else {
				MethodDesignUtil.addCommonBlock(methodDesignList, index);
				MethodDesign newMethodDesign = methodDesignList.get(index);
				newMethodDesign.setBlockType(ConstantManager.BLOCK_TYPE_NORMAL);
				newMethodDesign.setDetailDisplay("return");
				methodDesignList.set(index, newMethodDesign);
			}

			tableViewer.refresh();
		}
	}

	/**
	 * Break Brock追加
	 * 
	 * @author daizhu
	 */
	private final class AddBreakBlockAction extends Action {
		public AddBreakBlockAction() {
			setText(ConstantManager.ADD_BREAK_BLOCK);
		}

		public void run() {
			Table table = tableViewer.getTable();
			int index = table.getSelectionIndex();

			if (index < 0) {
				MessageDialog.openInformation(null, null, "レコードを選択してください");
				return;
			} else {
				MethodDesignUtil.addCommonBlock(methodDesignList, index);
				MethodDesign newMethodDesign = methodDesignList.get(index);
				newMethodDesign.setBlockType(ConstantManager.BLOCK_TYPE_NORMAL);
				newMethodDesign.setDetailDisplay("break");
				methodDesignList.set(index, newMethodDesign);
			}

			tableViewer.refresh();
		}
	}

	/**
	 * Continue Brock追加
	 * 
	 * @author daizhu
	 * 
	 */
	private final class AddContinueBlockAction extends Action {
		public AddContinueBlockAction() {
			setText(ConstantManager.ADD_CONTINUE_BLOCK);
		}

		public void run() {

			Table table = tableViewer.getTable();
			int index = table.getSelectionIndex();

			if (index < 0) {
				MessageDialog.openInformation(null, null, "レコードを選択してください");
				return;
			} else {
				MethodDesignUtil.addCommonBlock(methodDesignList, index);
				MethodDesign newMethodDesign = methodDesignList.get(index);
				newMethodDesign.setBlockType(ConstantManager.BLOCK_TYPE_NORMAL);
				newMethodDesign.setDetailDisplay("continue");
				methodDesignList.set(index, newMethodDesign);
			}

			tableViewer.refresh();

		}
	}
}
