package designeditor.editors;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
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

import designeditor.editors.bean.MethodDesign;
import designeditor.editors.bean.MethodParameter;
import designeditor.editors.bean.Module;
import designeditor.editors.bean.ModuleMethod;
import designeditor.editors.dao.MethodParameterDao;
import designeditor.editors.dao.ModuleDao;
import designeditor.editors.dao.ModuleMethodDao;
import designeditor.editors.dialog.ClassDefineDialog;
import designeditor.editors.menu.ClassDesignRightMenuManager;
import designeditor.editors.models.TMethodParameter;
import designeditor.editors.models.TModule;
import designeditor.editors.provider.ClassTableViewerLabelProvider;
import designeditor.editors.provider.RowNumberLabelProvider;
import designeditor.editors.provider.TableViewerContentProvider;
import designeditor.util.DbUtil;

public class DesignEditor extends MultiPageEditorPart implements IResourceChangeListener {
	private Composite composite;
	private List<Module> moduleList;
	private ModuleDao moduleDao;
	private ModuleMethodDao moduleMethodDao;
	private MethodParameterDao methodParameterDao;

	/**
	 * Creates a multi-page editor example.
	 */
	public DesignEditor() {
		super();
		moduleDao = new ModuleDao();
		moduleMethodDao = new ModuleMethodDao();
		methodParameterDao = new MethodParameterDao();
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
	}

	/**
	 * Creates page 1 of the multi-page editor,
	 */
	void createPage1() {
		EntityManager em = DbUtil.init();
		List<TModule> tModuleList = moduleDao.selectAll();

		if (tModuleList == null) {
			moduleList = new ArrayList<Module>();
		} else {
			moduleList = moduleDao.ModelToBean(tModuleList);
		}
		composite = new Composite(getContainer(), SWT.BORDER);
		GridLayout gridLayout = new GridLayout(1, false);
		gridLayout.horizontalSpacing = 10;
		composite.setLayout(gridLayout);

		Composite topComposite = new Composite(composite, SWT.BORDER);
		topComposite.setLayout(new GridLayout());
		topComposite.setLayoutData(new GridData(1180, 40));
		Button btn = new Button(topComposite, SWT.PUSH);
		btn.setText("保存");
		btn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				em.getTransaction().begin();
				try {
					for (int i = 0; i < moduleList.size(); i++) {
						Module module = moduleList.get(i);
						List<ModuleMethod> moduleMethodList = module.getModuleMethod();
						for (ModuleMethod moduleMethod : moduleMethodList) {
							moduleMethod.setProjectId(module.getProjectId());
							moduleMethod.setPackageId(module.getPackageId());
							moduleMethod.setModuleId(module.getModuleId());
							List<MethodDesign> methodDesignList = moduleMethod.getMethodDesignList();
							List<MethodParameter> methodParameterList = moduleMethod.getMethodParameter();
							for (MethodDesign methodDesign : methodDesignList) {
								// TODO BeanToModel
								em.joinTransaction();
								DbUtil.save(methodDesign);
							}
							for (MethodParameter methodParameter : methodParameterList) {
								methodParameter.setProjectId(moduleMethod.getProjectId());
								methodParameter.setPackageId(moduleMethod.getPackageId());
								methodParameter.setModuleId(moduleMethod.getModuleId());
								methodParameter.setMethodId(moduleMethod.getMethodId());
								TMethodParameter tMethodParameter = methodParameterDao.BeanToModel(methodParameter);
								em.joinTransaction();
								DbUtil.save(tMethodParameter);
							}
							em.joinTransaction();
							DbUtil.save(moduleMethodDao.BeanToModel(moduleMethod));
						}
						TModule tModule = moduleDao.BeanToModel(module);
						em.joinTransaction();
						DbUtil.save(tModule);
					}
					em.getTransaction().commit();
				} catch (Exception ex) {
					System.out.println(ex.getStackTrace());
					em.getTransaction().rollback();
				} finally {
					DbUtil.close();
				}

			}
		});
		btn.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, true, false));

		Composite bottomComposite = new Composite(composite, SWT.BORDER);

		TableViewer tableView = new TableViewer(bottomComposite, SWT.FULL_SELECTION);
		Table table = tableView.getTable();
		bottomComposite.setLayout(new FillLayout());
		GridData gridData = new GridData();
		gridData.horizontalSpan = 6;
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.widthHint = 1180;
		gridData.heightHint = 800;
		bottomComposite.setLayoutData(gridData);

		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableViewerColumn numberColumn = new TableViewerColumn(tableView, SWT.RIGHT);
		numberColumn.getColumn().setText("番号");
		numberColumn.getColumn().setWidth(60);

		TableColumn logicOneColumn = new TableColumn(table, SWT.NONE);
		logicOneColumn.setText("プロジェクト");
		logicOneColumn.setWidth(180);

		TableColumn logicTwoColumn = new TableColumn(table, SWT.NONE);
		logicTwoColumn.setText("パッケジ");
		logicTwoColumn.setWidth(180);

		TableColumn logicthreeColumn = new TableColumn(table, SWT.NONE);
		logicthreeColumn.setText("モジュール名");
		logicthreeColumn.setWidth(180);

		tableView.setContentProvider(new TableViewerContentProvider());

		tableView.setLabelProvider(new ClassTableViewerLabelProvider());

		numberColumn.setLabelProvider(new RowNumberLabelProvider());

		tableView.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
				Table table = tableView.getTable();
				int index = table.getSelectionIndex();
				Module moduleData = moduleList.get(index);
				ClassDefineDialog c = new ClassDefineDialog(
						PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), moduleData);
				c.open();
				moduleList.set(index, moduleData);
				tableView.refresh();
			}
		});

		tableView.setInput(moduleList);

		ClassDesignRightMenuManager rightMenuManager = new ClassDesignRightMenuManager(tableView, moduleList);
		rightMenuManager.fillContextMenu();

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