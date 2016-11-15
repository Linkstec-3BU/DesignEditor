package designeditor.editors.dialog;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import designeditor.editors.bean.ModuleClass;
import designeditor.editors.bean.ModuleMethod;

public class ClassDefineDialog extends Dialog {
	protected Object result;
	protected Shell shell;
	private ModuleClass moduleClass;

	public static void main(String[] args) {

		ModuleClass moduleClass = new ModuleClass();

		Display display = new Display();
		Shell shell = new Shell();
		shell.setSize(650, 400);
		shell.setText("クラス定義editor");

		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.horizontalSpacing = 10;
		shell.setLayout(gridLayout);

		// 左側エリア設定
		Composite leftComposite = new Composite(shell, SWT.BORDER);
		GridLayout leftGridLayout = new GridLayout(2, false);
		leftGridLayout.horizontalSpacing = 20;
		leftGridLayout.verticalSpacing = 20;
		leftComposite.setLayout(leftGridLayout);
		GridData leftGridData = new GridData(300, 300);
		leftComposite.setLayoutData(leftGridData);
		GridData textGridData = new GridData(120, 20);
		
		Label projectLabel = new Label(leftComposite, SWT.NONE);
		projectLabel.setText("プロジェクトID:");
		Text projectText = new Text(leftComposite, SWT.NONE);
		projectText.setLayoutData(textGridData);
		projectText.setText(moduleClass.getProject_id());

		Label packageLabel = new Label(leftComposite, SWT.NONE);
		packageLabel.setText("package名:");
		Text packageText = new Text(leftComposite, SWT.NONE);
		packageText.setLayoutData(textGridData);
		packageText.setText(moduleClass.getPackage_id());

		Label modulLabel1 = new Label(leftComposite, SWT.NONE);
		modulLabel1.setText("モジュールの物理名:");
		Text modulText1 = new Text(leftComposite, SWT.NONE);
		modulText1.setLayoutData(textGridData);
		modulText1.setText(moduleClass.getModule_id());

		Label modulLabel2 = new Label(leftComposite, SWT.NONE);
		modulLabel2.setText("モジュールの論理名:");
		Text modulText2 = new Text(leftComposite, SWT.NONE);
		modulText2.setLayoutData(textGridData);
		modulText2.setText(moduleClass.getModule_id_name());

		Label commentLabel = new Label(leftComposite, SWT.NONE);
		commentLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 2, 1));
		commentLabel.setText("備考:");

		Text commentText = new Text(leftComposite, SWT.WRAP);
		GridData commentGd = new GridData(SWT.FILL, SWT.FILL, false, false, 2, 1);
		commentGd.heightHint = 80;
		commentText.setLayoutData(commentGd);
		commentText.setText(moduleClass.getComment());

		// 右側エリア設定
		Composite rightComposite = new Composite(shell, SWT.BORDER);

		GridLayout rightGridLayout = new GridLayout(1, false);
		rightComposite.setLayout(rightGridLayout);

		GridData rightGridData = new GridData(300, 300);
		rightComposite.setLayoutData(rightGridData);

		if (moduleClass.getModuleMethod() != null) {
			for (int i = 0; i < moduleClass.getModuleMethod().size(); i++) {
				ModuleMethod moduleMethod = moduleClass.getModuleMethod().get(i);
				Composite rightChildComposite = new Composite(rightComposite, SWT.BORDER);

				GridLayout rightChildGridLayout = new GridLayout(3, false);
				rightChildComposite.setLayout(rightChildGridLayout);

				GridData rightChildGridData = new GridData(280, 60);
				rightChildComposite.setLayoutData(rightChildGridData);

				Label methodLabel = new Label(rightChildComposite, SWT.NONE);
				methodLabel.setText("メソッド名");
				moduleMethod.setMethod_id(methodLabel.getText());

				Button btn1 = new Button(rightChildComposite, SWT.PUSH);
				btn1.setText("定義");
				btn1.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						MethodDefineDialog c = new MethodDefineDialog(shell, moduleMethod);
						c.open();
						methodLabel.setText(moduleMethod.getMethod_id());
					}
				});

				Button btn2 = new Button(rightChildComposite, SWT.PUSH);
				btn2.setText("設計");

				btn2.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						MethodDesignDialog c = new MethodDesignDialog(shell, moduleMethod);
						c.open();
					}
				});
			}
		}
		List<ModuleMethod> moduleMethodList = new ArrayList<ModuleMethod>();
		for (int i = 0; i < 10; i++) {
			ModuleMethod moduleMethod = new ModuleMethod();
			Composite rightChildComposite = new Composite(rightComposite, SWT.BORDER);

			GridLayout rightChildGridLayout = new GridLayout(3, false);
			rightChildComposite.setLayout(rightChildGridLayout);

			GridData rightChildGridData = new GridData(280, 60);
			rightChildComposite.setLayoutData(rightChildGridData);

			Label methodLabel = new Label(rightChildComposite, SWT.NONE);
			methodLabel.setText("メソッド名");
			moduleMethod.setMethod_id(methodLabel.getText());

			Button btn1 = new Button(rightChildComposite, SWT.PUSH);
			btn1.setText("定義");
			btn1.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					MethodDefineDialog c = new MethodDefineDialog(shell, moduleMethod);
					c.open();
					methodLabel.setText(moduleMethod.getMethod_id());
				}
			});

			Button btn2 = new Button(rightChildComposite, SWT.PUSH);
			btn2.setText("設計");

			btn2.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					MethodDesignDialog c = new MethodDesignDialog(shell, moduleMethod);
					c.open();
				}
			});

			rightChildComposite.setVisible(false);
			moduleMethodList.add(moduleMethod);
			moduleClass.setModuleMethod(moduleMethodList);
		}

		Composite bottomComposite = new Composite(shell, SWT.BORDER);

		GridLayout bottomGridLayout = new GridLayout(3, false);
		bottomComposite.setLayout(bottomGridLayout);
		GridData bottomGridData = new GridData(615, 40);
		bottomGridData.horizontalSpan = 2;
		bottomComposite.setLayoutData(bottomGridData);

		Button saveBtn = new Button(bottomComposite, SWT.PUSH | SWT.CENTER);
		saveBtn.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, true, false));
		saveBtn.setText("保存");
		saveBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}

		});

		Button canclBtn = new Button(bottomComposite, SWT.PUSH | SWT.CENTER);
		canclBtn.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, true, false));
		canclBtn.setText("取消");
		canclBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				shell.dispose();
			}

		});

		Button addMethodBtn = new Button(bottomComposite, SWT.PUSH | SWT.CENTER);
		addMethodBtn.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, true, false));
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

		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();

	}

	public ClassDefineDialog(Shell parent, ModuleClass moduleClass) {
		super(parent, SWT.NONE);
		this.moduleClass = moduleClass;
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
		shell.setSize(650, 400);
		shell.setText("クラス定義editor");

		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.horizontalSpacing = 10;
		shell.setLayout(gridLayout);

		// 左側エリア設定
		Composite leftComposite = new Composite(shell, SWT.BORDER);
		GridLayout leftGridLayout = new GridLayout(2, false);
		leftGridLayout.horizontalSpacing = 20;
		leftGridLayout.verticalSpacing = 20;
		leftComposite.setLayout(leftGridLayout);
		GridData leftGridData = new GridData(300, 300);
		leftComposite.setLayoutData(leftGridData);

		Label projectLabel = new Label(leftComposite, SWT.NONE);
		projectLabel.setText("プロジェクトID:");
		Text projectText = new Text(leftComposite, SWT.NONE);
		projectText.setText(moduleClass.getProject_id());

		Label packageLabel = new Label(leftComposite, SWT.NONE);
		packageLabel.setText("package名:");
		Text packageText = new Text(leftComposite, SWT.NONE);
		packageText.setText(moduleClass.getPackage_id());

		Label modulLabel1 = new Label(leftComposite, SWT.NONE);
		modulLabel1.setText("モジュールの物理名:");
		Text modulText1 = new Text(leftComposite, SWT.NONE);
		modulText1.setText(moduleClass.getModule_id());

		Label modulLabel2 = new Label(leftComposite, SWT.NONE);
		modulLabel2.setText("モジュールの論理名:");
		Text modulText2 = new Text(leftComposite, SWT.NONE);
		modulText2.setText(moduleClass.getModule_id_name());

		Label commentLabel = new Label(leftComposite, SWT.NONE);
		commentLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 2, 1));
		commentLabel.setText("備考:");

		Text commentText = new Text(leftComposite, SWT.WRAP);
		GridData commentGd = new GridData(SWT.FILL, SWT.FILL, false, false, 2, 1);
		commentGd.heightHint = 80;
		commentText.setLayoutData(commentGd);
		commentText.setText(moduleClass.getComment());

		// 右側エリア設定
		Composite rightComposite = new Composite(shell, SWT.BORDER);

		GridLayout rightGridLayout = new GridLayout(1, false);
		rightComposite.setLayout(rightGridLayout);

		GridData rightGridData = new GridData(300, 300);
		rightComposite.setLayoutData(rightGridData);

		Composite rightChildComposite = new Composite(rightComposite, SWT.BORDER);

		GridLayout rightChildGridLayout = new GridLayout(3, false);
		rightChildComposite.setLayout(rightChildGridLayout);

		GridData rightChildGridData = new GridData(280, 60);
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

			ModuleMethod moduleMethod = new ModuleMethod();
			Button btn11 = new Button(rightChildComposite1, SWT.PUSH);
			btn11.setText("定義");
			btn11.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					MethodDefineDialog c = new MethodDefineDialog(shell, moduleMethod);
					System.out.println(c.open());
				}
			});

			Button btn21 = new Button(rightChildComposite1, SWT.PUSH);
			btn21.setText("設計");
			rightChildComposite1.setVisible(false);
		}

		Composite bottomComposite = new Composite(shell, SWT.BORDER);

		GridLayout bottomGridLayout = new GridLayout(3, false);
		bottomComposite.setLayout(bottomGridLayout);
		GridData bottomGridData = new GridData(615, 40);
		bottomGridData.horizontalSpan = 2;
		bottomComposite.setLayoutData(bottomGridData);

		Button saveBtn = new Button(bottomComposite, SWT.PUSH | SWT.CENTER);
		saveBtn.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, true, false));
		saveBtn.setText("保存");

		Button canclBtn = new Button(bottomComposite, SWT.PUSH | SWT.CENTER);
		canclBtn.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, true, false));
		canclBtn.setText("取消");

		Button addMethodBtn = new Button(bottomComposite, SWT.PUSH | SWT.CENTER);
		addMethodBtn.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, true, false));
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
	}
}