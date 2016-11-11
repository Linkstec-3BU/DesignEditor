package designeditor.editors;

import java.io.StringWriter;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FontDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.MultiPageEditorPart;

import designeditor.editors.constant.ConstantManager;

public class DesignEditor extends MultiPageEditorPart implements IResourceChangeListener {

	/** The text editor used in page 0. */
	private TextEditor editor;

	/** The font chosen in page 1. */
	private Font font;

	/** The text widget used in page 2. */
	private StyledText text;

	/**
	 * Creates a multi-page editor example.
	 */
	public DesignEditor() {
		super();
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
	}

	/**
	 * Creates page 1 of the multi-page editor, which allows you to change the
	 * font used in page 2.
	 */
	void createPage1() {

		Composite composite = new Composite(getContainer(), SWT.NONE);
		GridLayout layout = new GridLayout();
		composite.setLayout(layout);
		layout.numColumns = 2;

		Table table = new Table(composite, SWT.MULTI | SWT.FULL_SELECTION | SWT.CHECK);
		table.setHeaderVisible(true);// ヘッダー
		table.setLinesVisible(true);// 線表示

		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setText("番号");
		tableColumn.setWidth(40);

		tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(40);

		tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(40);

		tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setText("処理詳細");
		tableColumn.setWidth(300);

		tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setText("備考");
		tableColumn.setWidth(300);

		TableItem item = null;
		item = new TableItem(table, SWT.NONE);
		item.setText(new String[] { "1", "2", "3", "4", "5", "6" });
		item = new TableItem(table, SWT.NONE);
		item.setText(new String[] { "1", "2", "3", "4", "5", "6" });
		item = new TableItem(table, SWT.NONE);
		item.setText(new String[] { "1", "2", "3", "4", "5", "6" });
		item = new TableItem(table, SWT.NONE);
		item.setText(new String[] { "1", "2", "3", "4", "5", "6" });
		item = new TableItem(table, SWT.NONE);
		item.setText(new String[] { "1", "2", "3", "4", "5", "6" });
		item = new TableItem(table, SWT.NONE);
		item.setText(new String[] { "1", "2", "3", "4", "5", "6" });

		table.addListener(SWT.MouseDown, new Listener() {
			@Override
			public void handleEvent(Event event) {
				if (event.button == 3) {
					Menu menu = new Menu(table);
					table.setMenu(menu);

					MenuItem addEmptyItem = new MenuItem(menu, SWT.PUSH);
					addEmptyItem.setText(ConstantManager.ADD_EMPTY_BLOCK);

					addEmptyItem.addListener(SWT.Selection, new Listener() {
						public void handleEvent(Event event) {
							TableItem item1 = (TableItem) table.getSelection()[0];
							Table parent = item1.getParent();
							if (parent != null) {
								TableItem item = null;
								item = new TableItem(table, SWT.NONE);
								item.setText(new String[] { "1", "2", "3", "4", "5", "6" });
							}
						}
					});

					MenuItem removeEmptyItem = new MenuItem(menu, SWT.PUSH);
					removeEmptyItem.setText(ConstantManager.REMOVE_EMPTY_BLOCK);

					removeEmptyItem.addListener(SWT.Selection, new Listener() {
						public void handleEvent(Event event) {
							TableItem item1 = (TableItem) table.getSelection()[0];
							Table parent = item1.getParent();
							if (parent != null) {
								table.remove(table.getSelectionIndices());
							}
						}
					});

					MenuItem controlMenuItem = new MenuItem(menu, SWT.CASCADE);
					controlMenuItem.setText(ConstantManager.ADD_CONTROY_BLOCK);

					Menu controlMenu = new Menu(controlMenuItem);
					controlMenuItem.setMenu(controlMenu);

					MenuItem addSelectBlockItem = new MenuItem(controlMenu, SWT.PUSH);
					addSelectBlockItem.setText(ConstantManager.ADD_SELECT_BLOCK);

					MenuItem addForeachBlockItem = new MenuItem(controlMenu, SWT.PUSH);
					addForeachBlockItem.setText(ConstantManager.ADD_FOREACH_BLOCK);

					MenuItem addThrowBlockItem = new MenuItem(controlMenu, SWT.PUSH);
					addThrowBlockItem.setText(ConstantManager.ADD_THROW_BLOCK);

					MenuItem calculusMenuItem = new MenuItem(menu, SWT.CASCADE);
					calculusMenuItem.setText(ConstantManager.ADD_CALCULUS_BLOCK);

					Menu calculusMenu = new Menu(calculusMenuItem);
					calculusMenuItem.setMenu(calculusMenu);

					MenuItem defineVarBlockItem = new MenuItem(calculusMenu, SWT.PUSH);
					defineVarBlockItem.setText(ConstantManager.ADD_DEFINE_VAR_BLOCK);

					MenuItem addFormulaBlockItem = new MenuItem(calculusMenu, SWT.PUSH);
					addFormulaBlockItem.setText(ConstantManager.ADD_DEFINE_VAR_BLOCK);

					MenuItem addCallBlockItem = new MenuItem(calculusMenu, SWT.PUSH);
					addCallBlockItem.setText(ConstantManager.ADD_DEFINE_VAR_BLOCK);

					MenuItem addReturnBlockItem = new MenuItem(menu, SWT.PUSH);
					addReturnBlockItem.setText(ConstantManager.ADD_RETURN_BLOCK);

					MenuItem addBreakBlockItem = new MenuItem(menu, SWT.PUSH);
					addBreakBlockItem.setText(ConstantManager.ADD_RETURN_BLOCK);

					MenuItem addContinueBlockItem = new MenuItem(menu, SWT.PUSH);
					addContinueBlockItem.setText(ConstantManager.ADD_RETURN_BLOCK);

					// item.addListener(SWT.Selection, new Listener() {
					// public void handleEvent(Event event) {
					// TableItem item1 = (TableItem) table
					// .getSelection()[0];
					// Table parent = item1.getParent();
					// if (parent != null) {
					// table.remove(table
					// .getSelectionIndices());
					// }
					// }
					// });
				}
			}

		});

		int index = addPage(composite);
		setPageText(index, "Properties");
	}

	/**
	 * Creates page 2 of the multi-page editor, which shows the sorted text.
	 */
	void createPage2() {
		Composite composite = new Composite(getContainer(), SWT.NONE);
		FillLayout layout = new FillLayout();
		composite.setLayout(layout);
		text = new StyledText(composite, SWT.H_SCROLL | SWT.V_SCROLL);
		text.setEditable(false);

		int index = addPage(composite);
		setPageText(index, "Preview");
	}

	/**
	 * Creates the pages of the multi-page editor.
	 */
	protected void createPages() {
		createPage1();
		createPage2();
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

	/**
	 * Calculates the contents of page 2 when the it is activated.
	 */
	protected void pageChange(int newPageIndex) {
		super.pageChange(newPageIndex);
		if (newPageIndex == 2) {
			sortWords();
		}
	}

	/**
	 * Closes all project files on project close.
	 */
	public void resourceChanged(final IResourceChangeEvent event) {
		if (event.getType() == IResourceChangeEvent.PRE_CLOSE) {
			Display.getDefault().asyncExec(new Runnable() {
				public void run() {
					IWorkbenchPage[] pages = getSite().getWorkbenchWindow().getPages();
					for (int i = 0; i < pages.length; i++) {
						if (((FileEditorInput) editor.getEditorInput()).getFile().getProject()
								.equals(event.getResource())) {
							IEditorPart editorPart = pages[i].findEditor(editor.getEditorInput());
							pages[i].closeEditor(editorPart, true);
						}
					}
				}
			});
		}
	}

	/**
	 * Sets the font related data to be applied to the text in page 2.
	 */
	void setFont() {
		FontDialog fontDialog = new FontDialog(getSite().getShell());
		fontDialog.setFontList(text.getFont().getFontData());
		FontData fontData = fontDialog.open();
		if (fontData != null) {
			if (font != null)
				font.dispose();
			font = new Font(text.getDisplay(), fontData);
			text.setFont(font);
		}
	}

	/**
	 * Sorts the words in page 0, and shows them in page 2.
	 */
	void sortWords() {

		String editorText = editor.getDocumentProvider().getDocument(editor.getEditorInput()).get();

		StringTokenizer tokenizer = new StringTokenizer(editorText, " \t\n\r\f!@#\u0024%^&*()-_=+`~[]{};:'\",.<>/?|\\");
		ArrayList editorWords = new ArrayList();
		while (tokenizer.hasMoreTokens()) {
			editorWords.add(tokenizer.nextToken());
		}

		Collections.sort(editorWords, Collator.getInstance());
		StringWriter displayText = new StringWriter();
		for (int i = 0; i < editorWords.size(); i++) {
			displayText.write(((String) editorWords.get(i)));
			displayText.write(System.getProperty("line.separator"));
		}
		text.setText(displayText.toString());
	}
}