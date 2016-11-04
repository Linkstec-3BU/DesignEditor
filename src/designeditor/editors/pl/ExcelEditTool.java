package designeditor.editors.pl;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ExcelEditTool extends Application {

	private static final String ADD_EMPTY_BLOCK = "��Brock�ǉ�";
	private static final String REMOVE_EMPTY_BLOCK = "Brock�폜";
	private static final String ADD_CONTROY_BLOCK = "����Brock�ǉ�";
	private static final String ADD_SELECT_BLOCK = "��������Brock�ǉ�(if..else if)��)";
	private static final String ADD_FOREACH_BLOCK = "�J��Ԃ�Brock�ǉ�(for-each��)";
	private static final String ADD_THROW_BLOCK = "��O����Brock�ǉ�(try..catch..finally��)";
	private static final String ADD_CALCULUS_BLOCK = "���ZBrock�ǉ�";
	private static final String ADD_DEFINE_VAR_BLOCK = "�ϐ��錾Brock�ǉ�";
	private static final String ADD_FORMULA_BLOCK = "�v�Z��Brock�ǉ�";
	private static final String ADD_CALL_BLOCK = "�ďoBrock�ǉ�";
	private static final String ADD_RETURN_BLOCK = "Return Brock�ǉ�";
	private static final String ADD_BREAK_BLOCK = "Break Brock�ǉ�";
	private static final String ADD_CONTINUE_BLOCK = "Continue Brock�ǉ�";

	private final ObservableList<EditArea> editAreaData = FXCollections.observableArrayList(
			new EditArea("", "1", "2", "3", "4", "5"), new EditArea("", "", "", "", "", ""),
			new EditArea("", "", "", "", "", ""), new EditArea("", "", "", "", "", ""),
			new EditArea("", "", "", "", "", ""), new EditArea("", "", "", "", "", ""),
			new EditArea("", "", "", "", "", ""));

	private final ObservableList<AreaType> areaTypeData = FXCollections.observableArrayList(
			new AreaType("1", "�ďo�p�����[�^��`", "�ďo�E�������"), new AreaType("2", "�ďo�p�����[�^��`", "�ďo�E�c�����"),
			new AreaType("3", "�ďo�p�����[�^��`", "�ďo�E�������"), new AreaType("4", "��Ɨ̈��`", "��ƁE��Ɨ̈�"),
			new AreaType("5", "��Ɨ̈��`", "��ƁE�P���ڃ`�F�b�N")

	);
	private final ObservableList<AreaName> areaNameData = FXCollections.observableArrayList(
			new AreaName("1", "��ƁE��Ɨ̈�", "�X�܃R�[�h"), new AreaName("2", "��ƁE��Ɨ̈�", "�����ԍ�"),
			new AreaName("3", "��ƁE��Ɨ̈�", "�v�Z�c��"), new AreaName("4", "��ƁE��Ɨ̈�", "���ʃR�[�h"),
			new AreaName("5", "��ƁE��Ɨ̈�", "���ʏڍ�")

	);

	public static void main(String[] args) {
		launch(args);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage stage) throws Exception {

		stage.setTitle("Table View Sample");

		final Label label = new Label("Address Book");
		label.setFont(new Font("Arial", 20));

		final Label actionTaken = new Label();
		TableView<EditArea> table = new TableView<>();
		table.setEditable(true);

		// �ԍ���ݒ�
		TableColumn<EditArea, EditArea> numberCol = new TableColumn<>("No");
		numberCol.setMinWidth(20);
		numberCol.setCellValueFactory(new Callback<CellDataFeatures<EditArea, EditArea>, ObservableValue<EditArea>>() {
			@SuppressWarnings("rawtypes")
			@Override
			public ObservableValue<EditArea> call(CellDataFeatures<EditArea, EditArea> p) {
				return new ReadOnlyObjectWrapper(p.getValue());
			}
		});

		numberCol.setCellFactory(new Callback<TableColumn<EditArea, EditArea>, TableCell<EditArea, EditArea>>() {
			@Override
			public TableCell<EditArea, EditArea> call(TableColumn<EditArea, EditArea> param) {
				return new TableCell<EditArea, EditArea>() {
					@Override
					protected void updateItem(EditArea item, boolean empty) {
						super.updateItem(item, empty);

						if (this.getTableRow() != null && item != null) {
							setText(this.getTableRow().getIndex() + 1 + "");
						} else {
							setText("");
						}
					}
				};
			}
		});
		numberCol.setSortable(false);

		ContextMenu logicContext = new ContextMenu();

		MenuItem[] items1 = new MenuItem[] { new MenuItem(ADD_EMPTY_BLOCK), new MenuItem(REMOVE_EMPTY_BLOCK) };
		MenuItem[] items2 = new MenuItem[] { new MenuItem(ADD_RETURN_BLOCK), new MenuItem(ADD_BREAK_BLOCK),
				new MenuItem(ADD_CONTINUE_BLOCK) };

		Menu addControlBlock = new Menu(ADD_CONTROY_BLOCK);
		addControlBlock.getItems().add(new MenuItem(ADD_SELECT_BLOCK));
		addControlBlock.getItems().add(new MenuItem(ADD_FOREACH_BLOCK));
		addControlBlock.getItems().add(new MenuItem(ADD_THROW_BLOCK));

		Menu addCalculusBlock = new Menu(ADD_CALCULUS_BLOCK);
		addCalculusBlock.getItems().add(new MenuItem(ADD_DEFINE_VAR_BLOCK));
		addCalculusBlock.getItems().add(new MenuItem(ADD_FORMULA_BLOCK));
		addCalculusBlock.getItems().add(new MenuItem(ADD_CALL_BLOCK));

		logicContext.getItems().addAll(items1);
		logicContext.getItems().addAll(addControlBlock);
		logicContext.getItems().addAll(addCalculusBlock);
		logicContext.getItems().addAll(items2);

		TableColumn<EditArea, String> logicOneCol = new TableColumn<>("");
		logicOneCol.setMinWidth(30);
		logicOneCol.setCellValueFactory(new PropertyValueFactory<EditArea, String>("logicOne"));

		TableColumn<EditArea, String> logicTwnCol = new TableColumn<>("");
		logicTwnCol.setMinWidth(30);
		logicTwnCol.setCellValueFactory(new PropertyValueFactory<EditArea, String>("logicTwn"));

		TableColumn<EditArea, String> logicThreeCol = new TableColumn<>("");
		logicThreeCol.setMinWidth(30);
		logicThreeCol.setCellValueFactory(new PropertyValueFactory<EditArea, String>("logicThree"));

		TableColumn<EditArea, String> sencondNameCol = new TableColumn<>("���W�b�N�������e");
		sencondNameCol.setMinWidth(300);
		sencondNameCol.setCellValueFactory(new PropertyValueFactory<EditArea, String>("editArea"));

		sencondNameCol.setOnEditStart((CellEditEvent<EditArea, String> t) -> {
			openDialog(stage, t);
		});

		TableColumn<EditArea, String> commentCol = new TableColumn<>("�R�����g");
		commentCol.setMinWidth(300);
		commentCol.setCellValueFactory(new PropertyValueFactory<EditArea, String>("comment"));

		table.getColumns().addAll(numberCol, logicOneCol, logicTwnCol, logicThreeCol, sencondNameCol, commentCol);

		table.setItems(editAreaData);

		table.setRowFactory(new Callback<TableView<EditArea>, TableRow<EditArea>>() {
			@Override
			public TableRow<EditArea> call(TableView<EditArea> param) {
				TableRow<EditArea> row = new TableRow<>();
				row.setOnMousePressed(e -> {
					if (e.isSecondaryButtonDown()) {
						logicContext.show(row, e.getScreenX(), e.getScreenY());
						addControlBlockLogic(stage, addControlBlock, row.getIndex());
						addCalculusBlockLogic(stage, addCalculusBlock, row.getIndex());
						menuLogic(logicContext, row.getIndex());
					}
				});
				return row;
			}
		});

		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 10, 10, 10));
		vbox.getChildren().addAll(label, table, actionTaken);
		VBox.setVgrow(table, Priority.ALWAYS);

		stage.setScene(new Scene(vbox));
		stage.show();

	}

	private void addControlBlockLogic(Stage stage, Menu logicMenu, int index) {
		for (int i = 0; i < logicMenu.getItems().size(); i++) {
			MenuItem subMenu = logicMenu.getItems().get(i);
			if (ADD_SELECT_BLOCK.equals(subMenu.getText())) {
				subMenu.setOnAction(ex -> {
					openDialog1(stage, index);
				});
			} else if (ADD_FOREACH_BLOCK.equals(subMenu.getText())) {
				subMenu.setOnAction(ex -> {
					openDialog2(stage, index);
				});
			} else if (ADD_THROW_BLOCK.equals(subMenu.getText())) {
				subMenu.setOnAction(ex -> {
					openDialog3(stage, index);
				});
			}
		}
	}

	private void addCalculusBlockLogic(Stage stage, Menu logicMenu, int index) {
		for (int i = 0; i < logicMenu.getItems().size(); i++) {
			MenuItem subMenu = logicMenu.getItems().get(i);
			if (ADD_DEFINE_VAR_BLOCK.equals(subMenu.getText())) {
				subMenu.setOnAction(ex -> {
					openDialog4(stage, index);
				});
			} else if (ADD_FORMULA_BLOCK.equals(subMenu.getText())) {
				subMenu.setOnAction(ex -> {
					openDialog5(stage, index);
				});
			} else if (ADD_CALL_BLOCK.equals(subMenu.getText())) {
				subMenu.setOnAction(ex -> {
					openDialog6(stage, index);
				});
			}
		}

	}

	private void menuLogic(ContextMenu logicContext, int index) {
		for (int i = 0; i < logicContext.getItems().size(); i++) {
			MenuItem subMenu = logicContext.getItems().get(i);
			if (ADD_EMPTY_BLOCK.equals(subMenu.getText())) {
				subMenu.setOnAction(ex -> {
					String flg = "";
					if (index > 0) {
						EditArea beforeEdit = editAreaData.get(index - 1);
						flg = beforeEdit.getFlg();
					}
					EditArea edit = new EditArea(flg, "", "", "", "", "");
					editAreaData.add(index, edit);
				});
			} else if (REMOVE_EMPTY_BLOCK.equals(subMenu.getText())) {
				subMenu.setOnAction(ex -> {
					editAreaData.remove(index);
				});
			} else if (ADD_RETURN_BLOCK.equals(subMenu.getText())) {
				subMenu.setOnAction(ex -> {
					String flg = "";
					if (index > 0) {
						EditArea beforeEdit = editAreaData.get(index - 1);
						flg = beforeEdit.getFlg();
					}
					EditArea edit = new EditArea(flg, "", "", "", "", "");
					editAreaData.add(index, edit);
				});
			} else if (ADD_BREAK_BLOCK.equals(subMenu.getText())) {
				subMenu.setOnAction(ex -> {
					String flg = "";
					if (index > 0) {
						EditArea beforeEdit = editAreaData.get(index - 1);
						flg = beforeEdit.getFlg();
					}
					EditArea edit = new EditArea(flg, "", "", "", "", "");
					editAreaData.add(index, edit);
				});
			} else if (ADD_CONTINUE_BLOCK.equals(subMenu.getText())) {
				subMenu.setOnAction(ex -> {
					String flg = "";
					if (index > 0) {
						EditArea beforeEdit = editAreaData.get(index - 1);
						flg = beforeEdit.getFlg();
					}
					EditArea edit = new EditArea(flg, "", "", "", "", "");
					editAreaData.add(index, edit);
				});
			}
		}
	}

	private String openDialog1(Stage stage, int index) {
		Stage newStage = new Stage();
		newStage.initModality(Modality.APPLICATION_MODAL);
		newStage.initOwner(stage);

		newStage.setTitle("���ڑI��");

		Label label1 = new Label("����1����͂��Ă�������:");
		label1.setMaxSize(200, 20);
		TextField text1 = new TextField();
		text1.setMaxSize(200, 20);
		Label label2 = new Label("����2����͂��Ă�������:");
		label2.setMaxSize(200, 20);
		TextField text2 = new TextField();
		text2.setMaxSize(200, 20);

		Button btn = new Button();
		btn.setText("�o�^");
		btn.setOnAction(e -> {
			EditArea edit = editAreaData.get(index);
			String flg = edit.getFlg();
			ObservableList<EditArea> ifLogicData = FXCollections.observableArrayList();
			if (flg == "IF") {
				ifLogicData.addAll(new EditArea("IF2", "", "IF", "", text1.getText(), ""),
						new EditArea("IF2", "", "", "", "", ""), new EditArea("IF2", "", "", "", "", ""),
						new EditArea("IF2", "", "ELSE", "", "", ""), new EditArea("IF2", "", "", "", "", ""),
						new EditArea("IF2", "", "", "", "", ""), new EditArea("IF2", "", "END", "", "", ""));
			} else if (flg == "IF2") {
				ifLogicData.addAll(new EditArea("IF3", "", "", "IF", text1.getText(), ""),
						new EditArea("IF3", "", "", "", "", ""), new EditArea("IF3", "", "", "", "", ""),
						new EditArea("IF3", "", "", "ELSE", "", ""), new EditArea("IF3", "", "", "", "", ""),
						new EditArea("IF3", "", "", "", "", ""), new EditArea("IF3", "", "", "END", "", ""));
			} else {
				ifLogicData.addAll(new EditArea("IF", "IF", "", "", text1.getText(), ""),
						new EditArea("IF", "", "", "", "", ""), new EditArea("IF", "", "", "", "", ""),
						new EditArea("IF", "ELSE", "", "", "", ""), new EditArea("IF", "", "", "", "", ""),
						new EditArea("IF", "", "", "", "", ""), new EditArea("IF", "END", "", "", "", ""));
			}

			editAreaData.addAll(index, ifLogicData);
			newStage.close();
		});

		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 10, 10, 10));

		vbox.getChildren().addAll(label1, text1, label2, text2, btn);

		newStage.setScene(new Scene(vbox));
		newStage.show();

		return "";
	}

	private String openDialog2(Stage stage, int index) {
		Stage newStage = new Stage();
		newStage.initModality(Modality.APPLICATION_MODAL);
		newStage.initOwner(stage);

		newStage.setTitle("���ڑI��");

		Label label1 = new Label("��������͂��Ă�������:");
		label1.setMaxSize(200, 20);
		TextField text1 = new TextField();
		text1.setMaxSize(200, 20);

		Button btn = new Button();
		btn.setText("�o�^");
		btn.setOnAction(e -> {
			ObservableList<EditArea> ifLogicData = FXCollections.observableArrayList(
					new EditArea("FOR", "FOR", "", "", text1.getText(), ""), new EditArea("FOR", "", "", "", "", ""),
					new EditArea("FOR", "", "", "", "", ""), new EditArea("FOR", "", "", "", "", ""),
					new EditArea("FOR", "END", "", "", "", ""));

			editAreaData.addAll(index, ifLogicData);
			newStage.close();
		});

		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 10, 10, 10));

		vbox.getChildren().addAll(label1, text1, btn);

		newStage.setScene(new Scene(vbox));
		newStage.show();

		return "";
	}

	private String openDialog3(Stage stage, int index) {
		Stage newStage = new Stage();
		newStage.initModality(Modality.APPLICATION_MODAL);
		newStage.initOwner(stage);

		newStage.setTitle("���ڑI��");

		Label label1 = new Label("�Ώۃ��X�g���ڂ���͂��Ă�������:");
		label1.setMaxSize(200, 20);
		TextField text1 = new TextField();
		text1.setMaxSize(200, 20);

		Button btn = new Button();
		btn.setText("�o�^");
		btn.setOnAction(e -> {
			ObservableList<EditArea> ifLogicData = FXCollections.observableArrayList(
					new EditArea("FOR", "TRY", "", "", text1.getText(), ""), new EditArea("FOR", "", "", "", "", ""),
					new EditArea("FOR", "CATCH", "", "", "", ""), new EditArea("FOR", "", "", "", "", ""),
					new EditArea("FOR", "FINALLY", "", "", "", ""), new EditArea("FOR", "", "", "", "", ""),
					new EditArea("FOR", "END", "", "", "", ""));
			editAreaData.addAll(index, ifLogicData);
			newStage.close();
		});

		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 10, 10, 10));

		vbox.getChildren().addAll(label1, text1, btn);

		newStage.setScene(new Scene(vbox));
		newStage.show();

		return "";
	}

	private String openDialog4(Stage stage, int index) {
		Stage newStage = new Stage();
		newStage.initModality(Modality.APPLICATION_MODAL);
		newStage.initOwner(stage);

		newStage.setTitle("���ڑI��");

		Label label1 = new Label("�Ώۃ��X�g���ڂ���͂��Ă�������:");
		label1.setMaxSize(200, 20);
		TextField text1 = new TextField();
		text1.setMaxSize(200, 20);

		Button btn = new Button();
		btn.setText("�o�^");
		btn.setOnAction(e -> {
			ObservableList<EditArea> ifLogicData = FXCollections.observableArrayList(
					new EditArea("FOR", "TRY", "", "", text1.getText(), ""), new EditArea("FOR", "", "", "", "", ""),
					new EditArea("FOR", "CATCH", "", "", "", ""), new EditArea("FOR", "", "", "", "", ""),
					new EditArea("FOR", "FINALLY", "", "", "", ""), new EditArea("FOR", "", "", "", "", ""),
					new EditArea("FOR", "END", "", "", "", ""));
			editAreaData.addAll(index, ifLogicData);
			newStage.close();
		});

		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 10, 10, 10));

		vbox.getChildren().addAll(label1, text1, btn);

		newStage.setScene(new Scene(vbox));
		newStage.show();

		return "";
	}

	private String openDialog5(Stage stage, int index) {
		Stage newStage = new Stage();
		newStage.initModality(Modality.APPLICATION_MODAL);
		newStage.initOwner(stage);

		newStage.setTitle("���ڑI��");

		Label label1 = new Label("�Ώۃ��X�g���ڂ���͂��Ă�������:");
		label1.setMaxSize(200, 20);
		TextField text1 = new TextField();
		text1.setMaxSize(200, 20);

		Button btn = new Button();
		btn.setText("�o�^");
		btn.setOnAction(e -> {
			ObservableList<EditArea> ifLogicData = FXCollections.observableArrayList(
					new EditArea("FOR", "TRY", "", "", text1.getText(), ""), new EditArea("FOR", "", "", "", "", ""),
					new EditArea("FOR", "CATCH", "", "", "", ""), new EditArea("FOR", "", "", "", "", ""),
					new EditArea("FOR", "FINALLY", "", "", "", ""), new EditArea("FOR", "", "", "", "", ""),
					new EditArea("FOR", "END", "", "", "", ""));
			editAreaData.addAll(index, ifLogicData);
			newStage.close();
		});

		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 10, 10, 10));

		vbox.getChildren().addAll(label1, text1, btn);

		newStage.setScene(new Scene(vbox));
		newStage.show();

		return "";
	}

	private String openDialog6(Stage stage, int index) {
		Stage newStage = new Stage();
		newStage.initModality(Modality.APPLICATION_MODAL);
		newStage.initOwner(stage);

		newStage.setTitle("���ڑI��");

		Label label1 = new Label("�Ώۃ��X�g���ڂ���͂��Ă�������:");
		label1.setMaxSize(200, 20);
		TextField text1 = new TextField();
		text1.setMaxSize(200, 20);

		Button btn = new Button();
		btn.setText("�o�^");
		btn.setOnAction(e -> {
			ObservableList<EditArea> ifLogicData = FXCollections.observableArrayList(
					new EditArea("FOR", "TRY", "", "", text1.getText(), ""), new EditArea("FOR", "", "", "", "", ""),
					new EditArea("FOR", "CATCH", "", "", "", ""), new EditArea("FOR", "", "", "", "", ""),
					new EditArea("FOR", "FINALLY", "", "", "", ""), new EditArea("FOR", "", "", "", "", ""),
					new EditArea("FOR", "END", "", "", "", ""));
			editAreaData.addAll(index, ifLogicData);
			newStage.close();
		});

		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 10, 10, 10));

		vbox.getChildren().addAll(label1, text1, btn);

		newStage.setScene(new Scene(vbox));
		newStage.show();

		return "";
	}

	@SuppressWarnings("unchecked")
	private String openDialog(Stage stage, CellEditEvent<EditArea, String> t) {
		Stage newStage = new Stage();
		newStage.initModality(Modality.APPLICATION_MODAL);
		newStage.initOwner(stage);

		newStage.setTitle("���ڑI��");
		TableView<AreaType> table1 = new TableView<>();

		TableColumn<AreaType, String> col11 = new TableColumn<>("No");
		col11.setMinWidth(20);
		col11.setCellValueFactory(new PropertyValueFactory<AreaType, String>("No"));
		TableColumn<AreaType, String> col12 = new TableColumn<>("�̈���");
		col12.setMinWidth(200);
		col12.setCellValueFactory(new PropertyValueFactory<AreaType, String>("areaType"));
		TableColumn<AreaType, String> col13 = new TableColumn<>("�̈於��");
		col13.setMinWidth(200);
		col13.setCellValueFactory(new PropertyValueFactory<AreaType, String>("areaName"));
		table1.getColumns().addAll(col11, col12, col13);
		table1.setItems(areaTypeData);

		TableView<AreaName> table2 = new TableView<>();
		TableColumn<AreaName, String> col21 = new TableColumn<>("No");
		col21.setMinWidth(20);
		col21.setCellValueFactory(new PropertyValueFactory<AreaName, String>("No"));
		TableColumn<AreaName, String> col22 = new TableColumn<>("�̈於��");
		col22.setMinWidth(200);
		col22.setCellValueFactory(new PropertyValueFactory<AreaName, String>("areaName"));
		TableColumn<AreaName, String> col23 = new TableColumn<>("���ږ�");
		col23.setMinWidth(200);
		col23.setCellValueFactory(new PropertyValueFactory<AreaName, String>("name"));
		table2.getColumns().addAll(col21, col22, col23);
		table2.setItems(areaNameData);

		table2.setRowFactory(new Callback<TableView<AreaName>, TableRow<AreaName>>() {
			@Override
			public TableRow<AreaName> call(TableView<AreaName> param) {
				TableRow<AreaName> row = new TableRow<>();
				row.setOnMouseClicked(e -> {
					t.getTableView().getItems().get(t.getTablePosition().getRow())
							.setEditArea(table2.getColumns().get(2).getCellData(row.getIndex()).toString());
					t.getTableView().refresh();
					newStage.close();
				});

				return row;
			}
		});

		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 10, 10, 10));

		vbox.getChildren().addAll(table1, table2);

		newStage.setScene(new Scene(vbox));
		newStage.show();

		return "";
	}
}
