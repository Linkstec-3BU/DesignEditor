package designeditor.editors.dialog;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import designeditor.editors.bean.MethodDesign;
import designeditor.editors.bean.ModuleMethod;
import designeditor.editors.menu.MethodDesignRightMenuManager;
import designeditor.editors.provider.MethodTableViewerLabelProvider;
import designeditor.editors.provider.RowNumberLabelProvider;
import designeditor.editors.provider.TableViewerContentProvider;
import designeditor.util.MethodDesignUtil;

public class MethodDesignDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private ModuleMethod moduleMethod;
	private List<MethodDesign> methodDesignList;

//	public static void main(String args[]) {
//		Display display = new Display();
//		Shell shell = new Shell();
//		shell.setSize(800, 600);
//		shell.setText("メソッド設計editor");
//
//		GridLayout layout = new GridLayout(6, false);
//		shell.setLayout(layout);
//
//		Label methodLabel = new Label(shell, SWT.NONE);
//		methodLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
//		methodLabel.setText("メソッド名");
//
//		Text methodText = new Text(shell, SWT.BORDER);
//		methodText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
//
//		Label paramterLabel = new Label(shell, SWT.NONE);
//		paramterLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
//		paramterLabel.setText("パラメータ");
//
//		Text paramterText = new Text(shell, SWT.BORDER);
//		paramterText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
//
//		Label returnLabel = new Label(shell, SWT.NONE);
//		returnLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
//		returnLabel.setText("戻り値");
//
//		Text returnText = new Text(shell, SWT.BORDER);
//		returnText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
//
//		Button btn = new Button(shell, SWT.PUSH);
//		btn.setText("ソース生成");
//		btn.setSize(50, 20);
//
//		Composite tableComposite = new Composite(shell, SWT.NONE);
//
//		TableViewer tableView = new TableViewer(tableComposite, SWT.FULL_SELECTION);
//		Table table = tableView.getTable();
//		tableComposite.setLayout(new FillLayout());
//		GridData gridData = new GridData();
//		gridData.horizontalSpan = 6;
//		gridData.horizontalAlignment = GridData.FILL;
//		gridData.grabExcessHorizontalSpace = true;
//		gridData.widthHint = 780;
//		gridData.heightHint = 500;
//		tableComposite.setLayoutData(gridData);
//
//		table.setHeaderVisible(true);
//		table.setLinesVisible(true);
//
//		/**
//		 * 
//		 */
//		TableViewerColumn numberColumn = new TableViewerColumn(tableView, SWT.RIGHT);
//		numberColumn.getColumn().setText("番号");
//		numberColumn.getColumn().setWidth(45);
//
//		TableColumn logicOneColumn = new TableColumn(table, SWT.NONE);
//		logicOneColumn.setText("");
//		logicOneColumn.setWidth(45);
//
//		TableColumn logicTwoColumn = new TableColumn(table, SWT.NONE);
//		logicTwoColumn.setText("");
//		logicTwoColumn.setWidth(45);
//
//		TableColumn logicthreeColumn = new TableColumn(table, SWT.NONE);
//		logicthreeColumn.setText("");
//		logicthreeColumn.setWidth(45);
//
//		TableColumn editAreaColumn = new TableColumn(table, SWT.NONE);
//		editAreaColumn.setText("処理詳細");
//		editAreaColumn.setWidth(300);
//
//		TableColumn commentColumn = new TableColumn(table, SWT.NONE);
//		commentColumn.setText("コメント");
//		commentColumn.setWidth(300);
//
//		tableView.setContentProvider(new TableViewerContentProvider());
//
//		tableView.setLabelProvider(new TableViewerLabelProvider());
//
//		numberColumn.setLabelProvider(new RowNumberLabelProvider());
//
//		List<EditArea> editAreaData = new ArrayList<EditArea>();
//		editAreaData.add(new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "1", "2", "3", "4", "5"));
//		tableView.setInput(editAreaData);
//
//		tableView.addDoubleClickListener(new IDoubleClickListener() {
//			@Override
//			public void doubleClick(DoubleClickEvent event) {
//				RowEditDialog c = new RowEditDialog(shell, tableView, editAreaData);
//				c.open();
//			}
//		});
//
//		RightMenuManager rightMenuManager = new RightMenuManager(tableView, editAreaData, shell);
//		rightMenuManager.fillContextMenu();
//
//		shell.open();
//		while (!shell.isDisposed()) {
//			if (!display.readAndDispatch())
//				display.sleep();
//		}
//		display.dispose();
//	}

	public MethodDesignDialog(Shell parent, ModuleMethod moduleMethod) {
		super(parent, SWT.NONE);
		this.moduleMethod = moduleMethod;
	}

	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		return result;
	}

	protected void createContents() {
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shell.setSize(800, 600);
		shell.setText("メソッド設計editor");

		GridLayout layout = new GridLayout(6, false);
		shell.setLayout(layout);

		Label methodLabel = new Label(shell, SWT.NONE);
		methodLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		methodLabel.setText("メソッド名");

		Text methodText = new Text(shell, SWT.BORDER);
		methodText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		methodText.setText(moduleMethod.getMethodId());

		Label paramterLabel = new Label(shell, SWT.NONE);
		paramterLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		paramterLabel.setText("パラメータ");

		Text paramterText = new Text(shell, SWT.BORDER);
		paramterText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		if (moduleMethod.getMethodParameter() == null || moduleMethod.getMethodParameter().isEmpty()) {
			paramterText.setText("");
		} else {
			paramterText.setText(moduleMethod.getMethodParameter().get(0).getParameterId());
		}

		Label returnLabel = new Label(shell, SWT.NONE);
		returnLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		returnLabel.setText("戻り値タイプ");

		Text returnText = new Text(shell, SWT.BORDER);
		returnText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		returnText.setText(moduleMethod.getMethodReturnType());

		Composite tableComposite = new Composite(shell, SWT.NONE);

		TableViewer tableView = new TableViewer(tableComposite, SWT.FULL_SELECTION);
		Table table = tableView.getTable();
		tableComposite.setLayout(new FillLayout());
		GridData gridData = new GridData();
		gridData.horizontalSpan = 6;
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.widthHint = 780;
		gridData.heightHint = 500;
		tableComposite.setLayoutData(gridData);

		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		/**
		 * 
		 */
		TableViewerColumn numberColumn = new TableViewerColumn(tableView, SWT.RIGHT);
		numberColumn.getColumn().setText("番号");
		numberColumn.getColumn().setWidth(45);
		
		TableColumn logicOneColumn = new TableColumn(table, SWT.NONE);
		logicOneColumn.setText("");
		logicOneColumn.setWidth(45);

		TableColumn logicTwoColumn = new TableColumn(table, SWT.NONE);
		logicTwoColumn.setText("");
		logicTwoColumn.setWidth(45);

		TableColumn logicthreeColumn = new TableColumn(table, SWT.NONE);
		logicthreeColumn.setText("");
		logicthreeColumn.setWidth(45);

		TableColumn editAreaColumn = new TableColumn(table, SWT.NONE);
		editAreaColumn.setText("処理詳細");
		editAreaColumn.setWidth(300);

		TableColumn commentColumn = new TableColumn(table, SWT.NONE);
		commentColumn.setText("コメント");
		commentColumn.setWidth(300);		
//		
//		TableColumn logicOneColumn1 = new TableColumn(table, SWT.NONE);
//		logicOneColumn1.setText("");
//		logicOneColumn1.setWidth(45);
//		
//		TableColumn logicOneColumn2 = new TableColumn(table, SWT.NONE);
//		logicOneColumn2.setText("");
//		logicOneColumn2.setWidth(45);
//		
//		TableColumn logicOneColumn3 = new TableColumn(table, SWT.NONE);
//		logicOneColumn3.setText("");
//		logicOneColumn3.setWidth(45);
//		
//		TableColumn logicOneColumn4 = new TableColumn(table, SWT.NONE);
//		logicOneColumn4.setText("");
//		logicOneColumn4.setWidth(45);
//		
//		TableColumn logicOneColumn5 = new TableColumn(table, SWT.NONE);
//		logicOneColumn5.setText("");
//		logicOneColumn5.setWidth(45);

		tableView.setContentProvider(new TableViewerContentProvider());

		tableView.setLabelProvider(new MethodTableViewerLabelProvider());

		numberColumn.setLabelProvider(new RowNumberLabelProvider());

		methodDesignList = new ArrayList<MethodDesign>();
		
		if (moduleMethod.getMethodDesignList() == null) {
			MethodDesignUtil.initBlock(methodDesignList);
		} else {
			methodDesignList = moduleMethod.getMethodDesignList();
		}
		tableView.setInput(methodDesignList);

		tableView.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
				RowEditDialog c = new RowEditDialog(shell, tableView, methodDesignList);
				c.open();
			}
		});

		MethodDesignRightMenuManager rightMenuManager = new MethodDesignRightMenuManager(tableView, methodDesignList, shell);
		rightMenuManager.fillContextMenu();
		
		Button saveBtn = new Button(shell, SWT.PUSH);
		saveBtn.setText("保存");
		saveBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				moduleMethod.setMethodDesignList(methodDesignList);
				shell.dispose();
			}
		});
	}

}