package designeditor.editors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.MultiPageEditorPart;

import designeditor.editors.dialog.AddCalculusDialog;
import designeditor.editors.dialog.MethodDefineDialog;

public class DesignEditor extends MultiPageEditorPart implements IResourceChangeListener {
	private Composite composite;

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
		composite = new Composite(getContainer(), SWT.BORDER);
		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.horizontalSpacing = 10;
		composite.setLayout(gridLayout);		

		// 左側エリア設定
		Composite leftComposite = new Composite(composite, SWT.BORDER);
		GridLayout leftGridLayout = new GridLayout(2, false);
		leftGridLayout.horizontalSpacing = 20;
		leftGridLayout.verticalSpacing = 20;
		leftComposite.setLayout(leftGridLayout);
		GridData leftGridData = new GridData(400, 600);
		leftComposite.setLayoutData(leftGridData);

		Label projectLabel = new Label(leftComposite, SWT.NONE);
		projectLabel.setText("プロジェクトID:");
		Text projectText = new Text(leftComposite, SWT.NONE);
		projectText.setText("");

		Label packageLabel = new Label(leftComposite, SWT.NONE);
		packageLabel.setText("package名:");
		Text packageText = new Text(leftComposite, SWT.NONE);
		packageText.setText("");

		Label modulLabel1 = new Label(leftComposite, SWT.NONE);
		modulLabel1.setText("モジュールの物理名:");
		Text modulText1 = new Text(leftComposite, SWT.NONE);
		modulText1.setText("");

		Label modulLabel2 = new Label(leftComposite, SWT.NONE);
		modulLabel2.setText("モジュールの論理名:");
		Text modulText2 = new Text(leftComposite, SWT.NONE);
		modulText2.setText("");

		Label commentLabel = new Label(leftComposite, SWT.NONE);
		commentLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 2, 1));
		commentLabel.setText("備考:");

		Text commentText = new Text(leftComposite, SWT.WRAP);
		commentText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 2, 1));
		commentText.setText("");

		// 右側エリア設定
		Composite rightComposite = new Composite(composite, SWT.BORDER);

		GridLayout rightGridLayout = new GridLayout(1, false);
		rightComposite.setLayout(rightGridLayout);

		GridData rightGridData = new GridData(400, 600);
		rightComposite.setLayoutData(rightGridData);

		Composite rightChildComposite = new Composite(rightComposite, SWT.BORDER);
		
		GridLayout rightChildGridLayout = new GridLayout(3, false);
		rightChildComposite.setLayout(rightChildGridLayout);

		GridData rightChildGridData = new GridData(400, 80);
		rightChildComposite.setLayoutData(rightChildGridData);
		
		Label methodLabel = new Label(rightChildComposite, SWT.NONE);
		methodLabel.setText("method1");

		Button btn1 = new Button(rightChildComposite, SWT.PUSH);
		btn1.setText("定義");

		Button btn2 = new Button(rightChildComposite, SWT.PUSH);
		btn2.setText("設計");

		for (int i = 0; i < 10; i++) {
			Composite rightChildComposite1 = new Composite(rightComposite, SWT.BORDER);
			
			GridLayout rightChildGridLayout1 = new GridLayout(3, false);
			rightChildComposite1.setLayout(rightChildGridLayout1);

			GridData rightChildGridData1 = new GridData(400, 80);
			rightChildComposite1.setLayoutData(rightChildGridData1);
			
			Label methodLabel1 = new Label(rightChildComposite1, SWT.NONE);
			methodLabel1.setText("method1");
			

			Button btn11 = new Button(rightChildComposite1, SWT.PUSH);
			btn11.setText("定義");
			btn11.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
//					MethodDefineDialog c = new MethodDefineDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
//					c.open();
				}
			});

			Button btn21 = new Button(rightChildComposite1, SWT.PUSH);
			btn21.setText("設計");
			rightChildComposite1.setVisible(false);
		}

		Composite bottomComposite = new Composite(composite, SWT.BORDER);

		GridLayout bottomGridLayout = new GridLayout(3, false);
		bottomComposite.setLayout(bottomGridLayout);
		GridData bottomGridData = new GridData(810, 80);
		bottomGridData.horizontalSpan = 2;
		bottomComposite.setLayoutData(bottomGridData);

		Button saveBtn = new Button(bottomComposite, SWT.PUSH | SWT.CENTER);
		saveBtn.setText("保存");

		Button canclBtn = new Button(bottomComposite, SWT.PUSH | SWT.CENTER);
		canclBtn.setText("取消");

		Button addMethodBtn = new Button(bottomComposite, SWT.PUSH | SWT.CENTER);
		addMethodBtn.setText("Add method");
		addMethodBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Control rightControl[] = rightComposite.getChildren();
				System.out.println(rightControl.length);
				boolean labelFlg = false;
				for (Control ctl : rightControl) {
					if (ctl.getVisible()) {
						continue;
					}
					if (ctl instanceof Composite && !labelFlg) {
						ctl.setVisible(true);
						labelFlg = true;
						continue;
					}
				}
			}
		});

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