package designeditor.editors.dialog;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

import designeditor.editors.bean.EditArea;
import designeditor.editors.constant.ConstantManager;
import designeditor.editors.logic.CreateForeachBlock;

public class AddCalculusDialog extends Dialog {
	protected Object result;
	protected Shell shell;
	private TableViewer tableViewer;

	public AddCalculusDialog(Shell parent, TableViewer tableViewer) {
		super(parent, SWT.NONE);
		this.tableViewer = tableViewer;
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
		shell.setSize(312, 212);
		shell.setText("SWT Dialog");
		Text text = new Text(shell, SWT.BORDER);
		text.setBounds(100, 35, 20, 18);
		text.setEditable(true);

		final Button button = new Button(shell, SWT.NONE);
		button.setText("button");
		button.setBounds(127, 74, 44, 23);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Table table = tableViewer.getTable();
				int index = table.getSelectionIndex();
				EditArea edit = new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", text.getText(), "");
				tableViewer.insert(edit, index);

				// tableViewer.refresh();
				shell.close();
			}
		});
	}
}