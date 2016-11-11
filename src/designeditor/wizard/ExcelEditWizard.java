package designeditor.wizard;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;

import designeditor.wizard.page.ExcelEditWizardPage;



public class ExcelEditWizard extends Wizard implements INewWizard {

	private ExcelEditWizardPage page;

	private IStructuredSelection selection;

	private IWorkbench workbench;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean performFinish() {

		IFile file = this.page.createNewFile();

		if (file == null) {
			return false;
		}

		IWorkbenchPage page = this.workbench.getActiveWorkbenchWindow().getActivePage();

		try {
			IDE.openEditor(page, file, true);
		} catch (PartInitException e) {

			e.printStackTrace();
		}

		return true;
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
		this.workbench = workbench;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addPages() {
		this.page = new ExcelEditWizardPage(this.selection);
		this.addPage(this.page);

	}
}
