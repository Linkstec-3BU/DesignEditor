package designeditor.editors.dialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import designeditor.editors.bean.MethodParameter;

public class ParameterDefineDialog extends Dialog {
	protected Object result;
	protected Shell shell;
	private MethodParameter methodParameter;
	private String[] JAVA_TYPES = { "String", "BigDecimal", "boolean", "Boolean",
			"int", "long", "Long", "float", "double", "Double", "Date", "Time", 
			"Timestamp", "Blob", "Clob", "Integer", "Array"};

//	public static void main(String[] args) {
//		Display display = new Display();
//		Shell shell = new Shell();
//		shell.setSize(350, 400);
//		shell.setText("パラメータ定義editor");
//
//		GridLayout gridLayout = new GridLayout(1, false);
//		gridLayout.horizontalSpacing = 10;
//		shell.setLayout(gridLayout);
//
//		// 上側エリア設定
//		Composite topComposite = new Composite(shell, SWT.BORDER);
//		GridLayout topGridLayout = new GridLayout(2, false);
//		topGridLayout.horizontalSpacing = 20;
//		topGridLayout.verticalSpacing = 20;
//		topComposite.setLayout(topGridLayout);
//		GridData leftGridData = new GridData(300, 300);
//		topComposite.setLayoutData(leftGridData);
//
//		Label parmeterNameLabel1 = new Label(topComposite, SWT.NONE);
//		parmeterNameLabel1.setText("パラメータの物理名:");
//		Text parmeterNameText1 = new Text(topComposite, SWT.NONE);
//		parmeterNameText1.setText("");
//
//		Label parmeterNameLabel2 = new Label(topComposite, SWT.NONE);
//		parmeterNameLabel2.setText("パラメータの論理名:");
//		Text parmeterNameText2 = new Text(topComposite, SWT.NONE);
//		parmeterNameText2.setText("");
//
//		Label parmeterLabel = new Label(topComposite, SWT.NONE);
//		parmeterLabel.setText("パラメータタイプ:");
//		Text parmeterText = new Text(topComposite, SWT.NONE);
//		parmeterText.setText("");
//
//		Label commentLabel = new Label(topComposite, SWT.NONE);
//		commentLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 2, 1));
//		commentLabel.setText("備考:");
//
//		Text commentText = new Text(topComposite, SWT.WRAP);
//		GridData commentGd = new GridData(SWT.FILL, SWT.FILL, false, false, 2, 1);
//		commentGd.heightHint = 80;
//		commentText.setLayoutData(commentGd);
//		commentText.setText("");
//		
//
//
//		Composite bottomComposite = new Composite(shell, SWT.BORDER);
//
//		GridLayout bottomGridLayout = new GridLayout(2, false);
//		bottomComposite.setLayout(bottomGridLayout);
//		GridData bottomGridData = new GridData(300, 40);
//		bottomGridData.horizontalSpan = 2;
//		bottomComposite.setLayoutData(bottomGridData);
//
//		Button saveBtn = new Button(bottomComposite, SWT.PUSH | SWT.CENTER);
//		saveBtn.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL , true,false));
//		saveBtn.setText("保存");
//
//		Button canclBtn = new Button(bottomComposite, SWT.PUSH | SWT.CENTER);
//		canclBtn.setText("取消");
//		canclBtn.setLayoutData(new GridData(SWT.LEFT, SWT.FILL , true,false));
//		
//		
//		 shell.open();
//		   while (!shell.isDisposed()) {
//		    if (!display.readAndDispatch())
//		     display.sleep();
//		   }
//		   display.dispose();
//		   
//	}
//	
	public ParameterDefineDialog(Shell parent,MethodParameter methodParameter) {
		super(parent, SWT.NONE);
		this.methodParameter = methodParameter;
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
		shell.setSize(350, 400);
		shell.setText("パラメータ定義editor");

		GridLayout gridLayout = new GridLayout(1, false);
		gridLayout.horizontalSpacing = 10;
		shell.setLayout(gridLayout);

		// 上側エリア設定
		Composite topComposite = new Composite(shell, SWT.BORDER);
		GridLayout topGridLayout = new GridLayout(2, false);
		topGridLayout.horizontalSpacing = 20;
		topGridLayout.verticalSpacing = 20;
		topComposite.setLayout(topGridLayout);
		GridData leftGridData = new GridData(300, 300);
		topComposite.setLayoutData(leftGridData);

		GridData textGridData = new GridData(120, 20);
		
		Label parmeterNameLabel1 = new Label(topComposite, SWT.NONE);
		parmeterNameLabel1.setText("パラメータの物理名:");
		Text parmeterNameText1 = new Text(topComposite, SWT.NONE);
		parmeterNameText1.setLayoutData(textGridData);
		parmeterNameText1.setText(methodParameter.getParameter_id());

		Label parmeterNameLabel2 = new Label(topComposite, SWT.NONE);
		parmeterNameLabel2.setText("パラメータの論理名:");
		Text parmeterNameText2 = new Text(topComposite, SWT.NONE);
		parmeterNameText2.setLayoutData(textGridData);
		parmeterNameText2.setText(methodParameter.getParameter_name());

		Label parmeterLabel = new Label(topComposite, SWT.NONE);
		parmeterLabel.setText("パラメータタイプ:");
		Combo parmeterCombo = new Combo(topComposite, SWT.DROP_DOWN);
		parmeterCombo.setLayoutData(textGridData);
		parmeterCombo.setText(methodParameter.getParamter_type());
		parmeterCombo.setItems(JAVA_TYPES);

		Label commentLabel = new Label(topComposite, SWT.NONE);
		commentLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 2, 1));
		commentLabel.setText("備考:");

		Text commentText = new Text(topComposite, SWT.WRAP);
		GridData commentGd = new GridData(SWT.FILL, SWT.FILL, false, false, 2, 1);
		commentGd.heightHint = 80;
		commentText.setLayoutData(commentGd);
		commentText.setText(methodParameter.getComment());

		Composite bottomComposite = new Composite(shell, SWT.BORDER);

		GridLayout bottomGridLayout = new GridLayout(2, false);
		bottomComposite.setLayout(bottomGridLayout);
		GridData bottomGridData = new GridData(300, 40);
		bottomGridData.horizontalSpan = 2;
		bottomComposite.setLayoutData(bottomGridData);

		Button saveBtn = new Button(bottomComposite, SWT.PUSH | SWT.CENTER);
		saveBtn.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL , true,false));
		saveBtn.setText("保存");
		saveBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				methodParameter.setParameter_id(parmeterNameText1.getText());
				methodParameter.setParameter_name(parmeterNameText2.getText());
				methodParameter.setParamter_type(parmeterCombo.getText());
				methodParameter.setComment(commentText.getText());
				shell.dispose();
			}
		});
		

		Button canclBtn = new Button(bottomComposite, SWT.PUSH | SWT.CENTER);
		canclBtn.setText("取消");
		canclBtn.setLayoutData(new GridData(SWT.LEFT, SWT.FILL , true,false));
		canclBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		
		Button tmpBtn = new Button(bottomComposite, SWT.PUSH | SWT.CENTER);
		tmpBtn.setText("tmp btn");
		tmpBtn.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL , true,false));
		tmpBtn.setVisible(false);
	}
}