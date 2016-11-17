//package designeditor.editors.dialog;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.eclipse.jface.viewers.TableViewer;
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.events.SelectionAdapter;
//import org.eclipse.swt.events.SelectionEvent;
//import org.eclipse.swt.events.SelectionListener;
//import org.eclipse.swt.layout.GridLayout;
//import org.eclipse.swt.widgets.Button;
//import org.eclipse.swt.widgets.Control;
//import org.eclipse.swt.widgets.Dialog;
//import org.eclipse.swt.widgets.Display;
//import org.eclipse.swt.widgets.Shell;
//import org.eclipse.swt.widgets.Table;
//import org.eclipse.swt.widgets.Text;
//
//import designeditor.editors.bean.MethodDesign;
//import designeditor.editors.constant.ConstantManager;
//import designeditor.editors.logic.CreateSelectBlock;
//import designeditor.util.MethodDesignUtil;
//
//public class AddSelectDialog extends Dialog {
//	protected Object result;
//	protected Shell shell;
//	private TableViewer tableViewer;
//	private String step;
//
//	private List<MethodDesign> methodDesignList;
//
//	public AddSelectDialog(Shell parent, TableViewer tableViewer,String step,List<MethodDesign> methodDesignList) {
//		super(parent, SWT.NONE);
//		this.step = step;
//		this.tableViewer = tableViewer;
//		this.methodDesignList = methodDesignList;
//	}
//
//	public Object open() {
//		createContents();
//		shell.open();
//		shell.layout();
//		Display display = getParent().getDisplay();
//		while (!shell.isDisposed()) {
//			if (!display.readAndDispatch())
//				display.sleep();
//		}
//		return result;
//	}
//
//	protected void createContents() {
//		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
//		shell.setSize(312, 212);
//		shell.setText("SWT Dialog");
//		shell.setLayout(new GridLayout(1, false));
//		Text text = new Text(shell, SWT.BORDER);
//		text.setEditable(true);
//		text.setSize(200, 30);
//		
//		Button addJyokenBtn = new Button(shell,SWT.PUSH);
//		addJyokenBtn.setText("条件追加");
//		addJyokenBtn.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				Text text1 = new Text(shell, SWT.BORDER);
//				text1.setEditable(true);
//				text1.setSize(200, 30);
//			}
//		});
//		
//		
//		final Button button = new Button(shell, SWT.NONE);
//		button.setText("確定");
//		
//		
//		button.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				Table table = tableViewer.getTable();
//				int index = table.getSelectionIndex();
//				
//				MethodDesignUtil.updateBlockUniqueIdAndLevel(methodDesignList, index);
//				MethodDesign newMethodDesign = methodDesignList.get(index);
//				
//				if (index > 0) {
//					MethodDesign beforeMethodDesign = methodDesignList.get(index-1);
//					MethodDesign afterMethodDesign = methodDesignList.get(index);
//					Control[] control = shell.getChildren();
//					MethodDesign newMethodDesign = new MethodDesign();
//					methodDesignList.add(index, newMethodDesign);
//					for (Control ctl : control) {
//						if (ctl instanceof Text) {
//							
//						}
//					}
//					MethodDesign newMethodDesign = new MethodDesign();
//					
//				}
//				
//				CreateSelectBlock block = new CreateSelectBlock();
//				List<MethodDesign> editlist = new ArrayList<MethodDesign>();
//				if (ConstantManager.BLOCK_STEP_ONE.equals(step)) {
//					editlist = block.CreateLevel1Block(text.getText());
//				} else if (ConstantManager.BLOCK_STEP_TWO.equals(step)) {
//					editlist = block.CreateLevel2Block(text.getText());
//				} else {
//					editlist = block.CreateLevel3Block(text.getText());
//				}
//				int index = table.getSelectionIndex() >= 0 ? table.getSelectionIndex() : 0;
//				methodDesignList.addAll(index, editlist);
//				tableViewer.refresh();
//				shell.close();
//			}
//		});
//	}
//}