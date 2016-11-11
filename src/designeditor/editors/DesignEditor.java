package designeditor.editors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.MultiPageEditorPart;

import designeditor.editors.bean.EditArea;
import designeditor.editors.constant.ConstantManager;
import designeditor.editors.dialog.RowEditDialog;
import designeditor.editors.menu.RightMenuManager;
import designeditor.editors.provider.TableViewerContentProvider;
import designeditor.editors.provider.TableViewerLabelProvider;

public class DesignEditor extends MultiPageEditorPart implements IResourceChangeListener {
	/**
	 * Creates a multi-page editor example.
	 */
	public DesignEditor() {
		super();
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
	}

	/**
	 * Creates page 1 of the multi-page editor,
	 */
	void createPage1() {
		
		Composite composite = new Composite(getContainer(), SWT.NONE);
		FillLayout layout = new FillLayout();
		composite.setLayout(layout);
		

		TableViewer tableView = new TableViewer(composite);
		Table table1 = tableView.getTable();
		table1.setHeaderVisible(true);// 设置标头
		table1.setLinesVisible(true);// 显示表格线
		TableLayout tLayout = new TableLayout();// 专用于表格的布局
		table1.setLayout(tLayout);
		
		/**
		 * 第三步:建立TableViewer中的列
		 */
		tLayout.addColumnData(new ColumnWeightData(50));

		TableColumn column = new TableColumn(table1, SWT.NONE);
		column.setText("番号");
		column.setWidth(50);

		tLayout.addColumnData(new ColumnWeightData(50));
		TableColumn column1 = new TableColumn(table1, SWT.NONE);
		column1.setText("");
		column.setWidth(50);

		tLayout.addColumnData(new ColumnWeightData(50));
		TableColumn column2 = new TableColumn(table1, SWT.NONE);
		column2.setText("");
		column.setWidth(50);

		tLayout.addColumnData(new ColumnWeightData(50));
		TableColumn column3 = new TableColumn(table1, SWT.NONE);
		column3.setText("");
		column.setWidth(50);

		tLayout.addColumnData(new ColumnWeightData(500));
		TableColumn column4 = new TableColumn(table1, SWT.NONE);
		column4.setText("処理詳細");
		column.setWidth(500);

		tLayout.addColumnData(new ColumnWeightData(500));
		TableColumn column5 = new TableColumn(table1, SWT.NONE);
		column5.setText("コメント");
		column.setWidth(500);

		tableView.setContentProvider(new TableViewerContentProvider());
		
		tableView.setLabelProvider(new TableViewerLabelProvider());

		List<EditArea> editAreaData = new ArrayList<EditArea>();
		editAreaData.add(new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "1", "2", "3", "4", "5"));
		tableView.setInput(editAreaData);

		tableView.addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event) {
				
				RowEditDialog c = new RowEditDialog(PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getShell(),tableView);
				c.open();

			}
		});

		RightMenuManager actionGroup = new RightMenuManager(tableView);
		
		actionGroup.fillContextMenu();

		int index = addPage(composite);
		setPageText(index, "Properties");
	}

	/**
	 * Creates the pages of the multi-page editor.
	 */
	protected void createPages() {
		createPage1();
	}

	/**
	 * The <code>MultiPageEditorPart</code> implementation of this
	 * <code>IWorkbenchPart</code> method disposes all nested editors.
	 * Subclasses may extend.
	 */
	public void dispose() {
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
		super.dispose();
	}

	/**
	 * Saves the multi-page editor's document.
	 */
	public void doSave(IProgressMonitor monitor) {
		getEditor(0).doSave(monitor);
	}

	/**
	 * Saves the multi-page editor's document as another file. Also updates the
	 * text for page 0's tab, and updates this multi-page editor's input to
	 * correspond to the nested editor's.
	 */
	public void doSaveAs() {
		IEditorPart editor = getEditor(0);
		editor.doSaveAs();
		setPageText(0, editor.getTitle());
		setInput(editor.getEditorInput());
	}

	/*
	 * (non-Javadoc) Method declared on IEditorPart
	 */
	public void gotoMarker(IMarker marker) {
		setActivePage(0);
		IDE.gotoMarker(getEditor(0), marker);
	}

	/**
	 * The <code>MultiPageEditorExample</code> implementation of this method
	 * checks that the input is an instance of <code>IFileEditorInput</code>.
	 */
	public void init(IEditorSite site, IEditorInput editorInput) throws PartInitException {
		if (!(editorInput instanceof IFileEditorInput))
			throw new PartInitException("Invalid Input: Must be IFileEditorInput");
		super.init(site, editorInput);
	}

	/*
	 * (non-Javadoc) Method declared on IEditorPart.
	 */
	public boolean isSaveAsAllowed() {
		return true;
	}

	@Override
	public void resourceChanged(IResourceChangeEvent arg0) {
		// TODO Auto-generated method stub

	}

}