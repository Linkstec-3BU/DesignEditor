package designeditor.wizard.page;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

public class ExcelEditWizardPage extends WizardNewFileCreationPage {

	private static final String EXTENSION = ".de";

	public ExcelEditWizardPage(IStructuredSelection selection) {
		super("新規作成", selection);

		this.setTitle("メソッド設計エディター");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);

		this.setFileName("newfile");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean validatePage() {
		boolean valid = super.validatePage();
		if (valid) {
			String fileName = this.getFileName();
			if (fileName.indexOf(".") != -1 && !fileName.endsWith(EXTENSION)) {
				this.setErrorMessage("拡張子が「.mdet」を設定してください");
				valid = false;
			}
		}
		if (valid) {
			String fileName = this.getFileName();
			if (fileName.indexOf(".") == -1) {
				fileName = fileName + EXTENSION;
			}
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			IWorkspaceRoot root = workspace.getRoot();

			IPath containerPath = this.getContainerFullPath();
			IPath newFilePath = containerPath.append(fileName);

			if (root.getFile(newFilePath).exists()) {
				this.setErrorMessage("'" + fileName + "' " + "すでに存在しました");
				valid = false;
			}
		}

		if (valid) {
			this.setMessage("メソッド設計エディターファイルを新規作成");
		}

		return valid;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IFile createNewFile() {
		String fileName = this.getFileName();
		if (fileName.indexOf(".") == -1) {
			this.setFileName(fileName + EXTENSION);
		}

		return super.createNewFile();
	}

}