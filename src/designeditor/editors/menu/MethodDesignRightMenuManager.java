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
import designeditor.editors.logic.CreateElseIfBlock;
import designeditor.editors.logic.CreateForeachBlock;
import designeditor.editors.logic.CreateSelectBlock;
import designeditor.editors.logic.CreateThrowBlock;
import designeditor.editors.logic.ICreateBlock;
import designeditor.util.MethodDesignUtil;

public class MethodDesignRightMenuManager extends ActionGroup {
	private TableViewer tableViewer;
	private List<MethodDesign> methodDesignList;
	private Shell shell;
	private ICreateBlock block;

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
		// controlMenu.add(new AddSelectElseIfBlockAction());
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

			if (index <= 0) {
				MessageDialog.openInformation(null, null, "開始行以外のレコードを選択してください");
				return;
			} else {
				MethodDesignUtil.addCommonBlock(methodDesignList, index, ConstantManager.BLOCK_TYPE_NORMAL);
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

			if (index <= 0) {
				MessageDialog.openInformation(null, null, "開始行以外のレコードを選択してください");
			} else if (index == methodDesignList.size() - 1) {
				MessageDialog.openInformation(null, null, "最終行を削除できません");
			} else {
				MethodDesign methodDesign = methodDesignList.get(index);
				List<MethodDesign> newMethodDesignList = new ArrayList<MethodDesign>();

				String parentBlockUniqueId = methodDesign.getFatherBlockUniqueId();
				int afterIndex = 0;
				boolean startTag = false;
				if (ConstantManager.BLOCK_TYPE_NORMAL.equals(methodDesign.getBlockType())) {
					newMethodDesignList.add(methodDesign);
				} else {
					for (int i = 1; i < methodDesignList.size(); i++) {
						MethodDesign nextMethodDesign = methodDesignList.get(i);
						if (!startTag && !nextMethodDesign.getFatherBlockUniqueId()
								.equals(methodDesign.getFatherBlockUniqueId())) {
							continue;
						}
						startTag = true;
						newMethodDesignList.add(nextMethodDesign);
						afterIndex = i + 1;
						if (ConstantManager.BLOCK_LEVEL_ONE.equals(methodDesign.getBlockLevel())) {
							if (parentBlockUniqueId.equals(nextMethodDesign.getFatherBlockUniqueId())
									&& ConstantManager.DISPLAY_END.equals(nextMethodDesign.getLevel1Display())) {
								break;
							}
						} else if (ConstantManager.BLOCK_LEVEL_TWO.equals(methodDesign.getBlockLevel())) {
							if (parentBlockUniqueId.equals(nextMethodDesign.getFatherBlockUniqueId())
									&& ConstantManager.DISPLAY_END.equals(nextMethodDesign.getLevel2Display())) {
								break;
							}
						} else if (ConstantManager.BLOCK_LEVEL_THREE.equals(methodDesign.getBlockLevel())) {
							if (parentBlockUniqueId.equals(nextMethodDesign.getFatherBlockUniqueId())
									&& ConstantManager.DISPLAY_END.equals(nextMethodDesign.getLevel3Display())) {
								break;
							}
						}
					}
				}
				MethodDesign beforeMethodDesign = methodDesignList.get(index - 1);
				MethodDesign afterMethodDesign = methodDesignList.get(afterIndex);
				beforeMethodDesign.setAfterBlockUniqueId(afterMethodDesign.getBlockUniqueId());

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

			if (index <= 0) {
				MessageDialog.openInformation(null, null, "開始行以外のレコードを選択してください");
				return;
			} else {

				MethodDesign newMethodDesign = methodDesignList.get(index);
				if (!ConstantManager.DISPLAY_SELECT_ELSE.equals(newMethodDesign.getLevel1Display())
						&& !ConstantManager.DISPLAY_SELECT_ELSE.equals(newMethodDesign.getLevel2Display())
						&& !ConstantManager.DISPLAY_SELECT_ELSE.equals(newMethodDesign.getLevel3Display())) {
					block = new CreateSelectBlock();
					MethodDesign beforeMethodDesign = methodDesignList.get(index-1);
					MethodDesign methodDesign = MethodDesignUtil.compareLevel(beforeMethodDesign, newMethodDesign);
					if (ConstantManager.BLOCK_LEVEL_ONE.equals(methodDesign.getBlockLevel())) {
						block.CreateLevel1Block(methodDesignList, index);
					} else if (ConstantManager.BLOCK_LEVEL_TWO.equals(methodDesign.getBlockLevel())) {
						block.CreateLevel2Block(methodDesignList, index);
					} else if (ConstantManager.BLOCK_LEVEL_THREE.equals(methodDesign.getBlockLevel())) {
						block.CreateLevel3Block(methodDesignList, index);
					}
				} else {
					block = new CreateElseIfBlock();
					if (ConstantManager.BLOCK_LEVEL_ONE.equals(newMethodDesign.getBlockLevel())) {
						block.CreateLevel1Block(methodDesignList, index);
					} else if (ConstantManager.BLOCK_LEVEL_TWO.equals(newMethodDesign.getBlockLevel())) {
						block.CreateLevel2Block(methodDesignList, index);
					} else if (ConstantManager.BLOCK_LEVEL_THREE.equals(newMethodDesign.getBlockLevel())) {
						block.CreateLevel3Block(methodDesignList, index);
					}
				}
			}

			tableViewer.refresh();

		}
	}

	// /**
	// * 条件分岐Brock追加(else if)文)
	// *
	// * @author daizhu
	// *
	// */
	// private final class AddSelectElseIfBlockAction extends Action {
	// public AddSelectElseIfBlockAction() {
	// setText(ConstantManager.ADD_SELECT_ELSE_IF_BLOCK);
	// }
	//
	// public void run() {
	// Table table = tableViewer.getTable();
	// int index = table.getSelectionIndex();
	//
	// if (index <= 0) {
	// MessageDialog.openInformation(null, null, "開始行以外のレコードを選択してください");
	// return;
	// } else {
	// block = new CreateElseIfBlock();
	// MethodDesign newMethodDesign = methodDesignList.get(index);
	//
	// if
	// (!ConstantManager.DISPLAY_SELECT_ELSE.equals(newMethodDesign.getLevel1Display())
	// &&
	// !ConstantManager.DISPLAY_SELECT_ELSE.equals(newMethodDesign.getLevel2Display())
	// &&
	// !ConstantManager.DISPLAY_SELECT_ELSE.equals(newMethodDesign.getLevel3Display()))
	// {
	// MessageDialog.openInformation(null, null, "ELSE以外の行が利用できません");
	// return;
	// }
	//
	// }
	//
	// tableViewer.refresh();
	// }
	// }

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

			if (index <= 0) {
				MessageDialog.openInformation(null, null, "開始行以外のレコードを選択してください");
				return;
			} else {
				block = new CreateForeachBlock();
				MethodDesign newMethodDesign = methodDesignList.get(index);
				MethodDesign beforeMethodDesign = methodDesignList.get(index-1);
				MethodDesign methodDesign = MethodDesignUtil.compareLevel(beforeMethodDesign, newMethodDesign);
				if (ConstantManager.BLOCK_LEVEL_ONE.equals(methodDesign.getBlockLevel())) {
					block.CreateLevel1Block(methodDesignList, index);
				} else if (ConstantManager.BLOCK_LEVEL_TWO.equals(methodDesign.getBlockLevel())) {
					block.CreateLevel2Block(methodDesignList, index);
				} else if (ConstantManager.BLOCK_LEVEL_THREE.equals(methodDesign.getBlockLevel())) {
					block.CreateLevel3Block(methodDesignList, index);
				}
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

			if (index <= 0) {
				MessageDialog.openInformation(null, null, "開始行以外のレコードを選択してください");
				return;
			} else {
				block = new CreateThrowBlock();
				MethodDesign newMethodDesign = methodDesignList.get(index);
				MethodDesign beforeMethodDesign = methodDesignList.get(index-1);
				MethodDesign methodDesign = MethodDesignUtil.compareLevel(beforeMethodDesign, newMethodDesign);
				if (ConstantManager.BLOCK_LEVEL_ONE.equals(methodDesign.getBlockLevel())) {
					block.CreateLevel1Block(methodDesignList, index);
				} else if (ConstantManager.BLOCK_LEVEL_TWO.equals(methodDesign.getBlockLevel())) {
					block.CreateLevel2Block(methodDesignList, index);
				} else if (ConstantManager.BLOCK_LEVEL_THREE.equals(methodDesign.getBlockLevel())) {
					block.CreateLevel3Block(methodDesignList, index);
				}
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
			Table table = tableViewer.getTable();
			int index = table.getSelectionIndex();

			if (index <= 0) {
				MessageDialog.openInformation(null, null, "開始行以外のレコードを選択してください");
				return;
			} else {
				AddCalculusDialog c = new AddCalculusDialog(shell, tableViewer, methodDesignList);
				c.open();
			}
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
			Table table = tableViewer.getTable();
			int index = table.getSelectionIndex();

			if (index <= 0) {
				MessageDialog.openInformation(null, null, "開始行以外のレコードを選択してください");
				return;
			} else {
				AddCalculusDialog c = new AddCalculusDialog(shell, tableViewer, methodDesignList);
				c.open();
			}
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
			Table table = tableViewer.getTable();
			int index = table.getSelectionIndex();

			if (index <= 0) {
				MessageDialog.openInformation(null, null, "開始行以外のレコードを選択してください");
				return;
			} else {
				AddCalculusDialog c = new AddCalculusDialog(shell, tableViewer, methodDesignList);
				c.open();
			}
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

			if (index <= 0) {
				MessageDialog.openInformation(null, null, "開始行以外のレコードを選択してください");
				return;
			} else {
				MethodDesignUtil.addCommonBlock(methodDesignList, index, ConstantManager.BLOCK_TYPE_NORMAL);
				MethodDesign newMethodDesign = methodDesignList.get(index);
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

			if (index <= 0) {
				MessageDialog.openInformation(null, null, "開始行以外のレコードを選択してください");
				return;
			} else {
				MethodDesignUtil.addCommonBlock(methodDesignList, index, ConstantManager.BLOCK_TYPE_NORMAL);
				MethodDesign newMethodDesign = methodDesignList.get(index);
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

			if (index <= 0) {
				MessageDialog.openInformation(null, null, "開始行以外のレコードを選択してください");
				return;
			} else {
				MethodDesignUtil.addCommonBlock(methodDesignList, index, ConstantManager.BLOCK_TYPE_NORMAL);
				MethodDesign newMethodDesign = methodDesignList.get(index);
				newMethodDesign.setDetailDisplay("continue");
				methodDesignList.set(index, newMethodDesign);
			}

			tableViewer.refresh();
		}
	}

}
