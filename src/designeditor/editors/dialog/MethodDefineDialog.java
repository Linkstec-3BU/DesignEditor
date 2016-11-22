package designeditor.editors.dialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import designeditor.editors.bean.MethodParameter;
import designeditor.editors.bean.ModuleMethod;
import designeditor.util.StringUtil;

public class MethodDefineDialog extends Dialog {
	protected Object result;
	protected Shell shell;
	private ModuleMethod moduleMethod;

	public MethodDefineDialog(Shell parent, ModuleMethod moduleMethod) {
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
		shell = new Shell(getParent(), SWT.SHELL_TRIM);
		shell.setSize(650, 400);
		shell.setText("メソッド定義editor");

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
		
		Label methodNameLabel1 = new Label(leftComposite, SWT.NONE);
		methodNameLabel1.setText("メソッドの物理名:");
		Text methodNameText1 = new Text(leftComposite, SWT.NONE);
		methodNameText1.setLayoutData(textGridData);
		methodNameText1.setText(moduleMethod.getMethodId());
		if (StringUtil.NotNullAndEmpty(moduleMethod.getMethodId())) {
			methodNameText1.setEnabled(false);
		} 

		Label methodNameLabel2 = new Label(leftComposite, SWT.NONE);
		methodNameLabel2.setText("メソッドの論理名:");
		Text methodNameText2 = new Text(leftComposite, SWT.NONE);
		methodNameText2.setLayoutData(textGridData);
		methodNameText2.setText(moduleMethod.getMethodIdName());
		

		Label methodReturnLabel = new Label(leftComposite, SWT.NONE);
		methodReturnLabel.setText("メソッドの戻り値タイプ:");
		Text methodReturnText = new Text(leftComposite, SWT.NONE);
		methodReturnText.setLayoutData(textGridData);
		methodReturnText.setText(moduleMethod.getMethodReturnType());

		Label methodThrowLabel = new Label(leftComposite, SWT.NONE);
		methodThrowLabel.setText("メソッドの異常タイプ:");
		Text methodThrowText = new Text(leftComposite, SWT.NONE);
		methodThrowText.setLayoutData(textGridData);
		methodThrowText.setText(moduleMethod.getMethodThrows1());

		Label commentLabel = new Label(leftComposite, SWT.NONE);
		commentLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 2, 1));
		commentLabel.setText("備考:");

		Text commentText = new Text(leftComposite, SWT.WRAP);
		GridData commentGd = new GridData(SWT.FILL, SWT.FILL, false, false, 2, 1);
		commentGd.heightHint = 80;
		commentText.setLayoutData(commentGd);
		commentText.setText(moduleMethod.getComment());

		// 右側エリア設定
		Composite rightComposite = new Composite(shell, SWT.BORDER);

		GridLayout rightGridLayout = new GridLayout(1, false);
		rightComposite.setLayout(rightGridLayout);

		GridData rightGridData = new GridData(300, 300);
		rightComposite.setLayoutData(rightGridData);
		List<MethodParameter> methodParameterList = new ArrayList<MethodParameter>();
		if (moduleMethod.getMethodParameter() != null) {
			for (int i = 0; i < moduleMethod.getMethodParameter().size(); i++) {
				MethodParameter methodParameter = moduleMethod.getMethodParameter().get(i);
				
				Composite rightChildComposite = new Composite(rightComposite, SWT.BORDER);

				GridLayout rightChildGridLayout = new GridLayout(4, false);
				rightChildComposite.setLayout(rightChildGridLayout);

				GridData rightChildGridData = new GridData(280, 30);
				rightChildComposite.setLayoutData(rightChildGridData);
				
				Label parameterTypeLabel = new Label(rightChildComposite, SWT.NONE);
				parameterTypeLabel.setLayoutData(new GridData(70,30));
				parameterTypeLabel.setText(methodParameter.getParamterType());
				
				Label parameterLabel = new Label(rightChildComposite, SWT.NONE);
				parameterLabel.setLayoutData(new GridData(70,30));
				parameterLabel.setText(methodParameter.getParameterId());
				
				Button editBtn = new Button(rightChildComposite,SWT.PUSH);
				editBtn.setText("編集");
				editBtn.setLayoutData(new GridData(70,30));
				editBtn.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						MethodParameter methodParameter = new MethodParameter();
						ParameterDefineDialog dialog = new ParameterDefineDialog(shell, methodParameter);
						dialog.open();
						parameterTypeLabel.setText(methodParameter.getParamterType());
						parameterLabel.setText(methodParameter.getParameterId());						
					}
				});
				
				Button removeBtn = new Button(rightChildComposite, SWT.PUSH);
				removeBtn.setText("削除");
				removeBtn.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						rightChildComposite.dispose();
						rightComposite.layout();
						methodParameterList.remove(methodParameter);
					}
				});

				methodParameterList.add(methodParameter);
			}
		}
		for (int i = 0; i < 100; i++) {
			MethodParameter methodParameter = new MethodParameter();
			Composite rightChildComposite = new Composite(rightComposite, SWT.BORDER);

			GridLayout rightChildGridLayout = new GridLayout(4, false);
			rightChildComposite.setLayout(rightChildGridLayout);

			GridData rightChildGridData = new GridData(280, 30);
			rightChildComposite.setLayoutData(rightChildGridData);

			Label parameterTypeLabel = new Label(rightChildComposite, SWT.NONE);
			parameterTypeLabel.setLayoutData(new GridData(70,30));
			parameterTypeLabel.setText(methodParameter.getParamterType());
			
			Label parameterLabel = new Label(rightChildComposite, SWT.NONE);
			parameterLabel.setLayoutData(new GridData(70,30));
			parameterLabel.setText(methodParameter.getParameterId());
			
			Button editBtn = new Button(rightChildComposite,SWT.PUSH);
			editBtn.setText("編集");
			editBtn.setLayoutData(new GridData(70,30));
			editBtn.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					MethodParameter methodParameter = new MethodParameter();
					ParameterDefineDialog dialog = new ParameterDefineDialog(shell, methodParameter);
					dialog.open();
					parameterTypeLabel.setText(methodParameter.getParamterType());
					parameterLabel.setText(methodParameter.getParameterId());	
					methodParameterList.add(methodParameter);
					moduleMethod.setMethodParameter(methodParameterList);
				}
			});
			
			Button removeBtn = new Button(rightChildComposite, SWT.PUSH);
			removeBtn.setText("削除");
			removeBtn.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					rightChildComposite.dispose();
					rightComposite.layout();
					methodParameterList.remove(methodParameter);
				}
			});
			
			rightChildComposite.setVisible(false);
		}
		Composite bottomComposite = new Composite(shell, SWT.BORDER);

		GridLayout bottomGridLayout = new GridLayout(5, false);
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
				moduleMethod.setMethodId(methodNameText1.getText());
				moduleMethod.setMethodIdName(methodNameText2.getText());
				moduleMethod.setMethodReturnType(methodReturnText.getText());
				moduleMethod.setMethodThrows1(methodThrowText.getText());
				moduleMethod.setComment(commentText.getText());	
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				moduleMethod.setMethodUniqueId(methodNameText1.getText() + sdf.format(new Date()));
				shell.dispose();
			}

		});

		Button canclBtn = new Button(bottomComposite, SWT.PUSH | SWT.CENTER);
		canclBtn.setText("取消");
		canclBtn.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, true, false));
		canclBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});

		Button addParamterBtn = new Button(bottomComposite, SWT.PUSH | SWT.CENTER);
		addParamterBtn.setText("Add paramter");
		addParamterBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Control rightControl[] = rightComposite.getChildren();
				
				
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
		addParamterBtn.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, true, false));
		Button addThrowBtn = new Button(bottomComposite, SWT.PUSH | SWT.CENTER);
		addThrowBtn.setText("Add Throw");
		addThrowBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

			}
		});
		addThrowBtn.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, true, false));

		Button tmpBtn = new Button(bottomComposite, SWT.PUSH | SWT.CENTER);
		tmpBtn.setText("tmp btn");
		tmpBtn.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, true, false));
		tmpBtn.setVisible(false);
	}
}