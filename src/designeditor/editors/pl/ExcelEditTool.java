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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ExcelEditTool extends Application {

	// ダミーデータ
	private final ObservableList<EditArea> editAreaData = FXCollections.observableArrayList(
			new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "1", "2", "3", "4", "5"),
			new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""),
			new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""),
			new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""),
			new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""),
			new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""),
			new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""));

	// ダミーデータ
	private final ObservableList<AreaType> areaTypeData = FXCollections.observableArrayList(
			new AreaType("1", "呼出パラメータ定義", "呼出・入金情報"), new AreaType("2", "呼出パラメータ定義", "呼出・残高情報"),
			new AreaType("3", "呼出パラメータ定義", "呼出・処理情報"), new AreaType("4", "作業領域定義", "作業・作業領域"),
			new AreaType("5", "作業領域定義", "作業・単項目チェック")

	);

	// ダミーデータ
	private final ObservableList<AreaName> areaNameData = FXCollections.observableArrayList(
			new AreaName("1", "作業・作業領域", "店舗コード"), new AreaName("2", "作業・作業領域", "口座番号"),
			new AreaName("3", "作業・作業領域", "計算残高"), new AreaName("4", "作業・作業領域", "結果コード"),
			new AreaName("5", "作業・作業領域", "結果詳細")

	);

	public static void main(String[] args) {
		launch(args);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage stage) throws Exception {

		stage.setTitle("メソッド設計エディター");

		

		final HBox hbox1 = new HBox();
		final Label label1 = new Label("メソッド名:");
		label1.setFont(new Font("Arial", 16));
		final TextField text1 = new TextField();
		hbox1.getChildren().addAll(label1,text1);
		
		final HBox hbox2 = new HBox();
		final Label label2 = new Label("パラメータ:");
		label2.setFont(new Font("Arial", 16));
		final TextField text2 = new TextField();
		hbox2.getChildren().addAll(label2,text2);
		
		final HBox hbox3 = new HBox();
		final Label label3 = new Label("戻り値:");
		label3.setFont(new Font("Arial", 16));		
		final TextField text3 = new TextField();
		hbox3.getChildren().addAll(label3,text3);
		
		final Label label = new Label("処理詳細エリア");
		label.setFont(new Font("Arial", 20));
		
		TableView<EditArea> table = new TableView<>();
		table.setEditable(true);

		// 番号列設定
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

		MenuItem[] items1 = new MenuItem[] { new MenuItem(ConstantManager.ADD_EMPTY_BLOCK),
				new MenuItem(ConstantManager.REMOVE_EMPTY_BLOCK) };
		MenuItem[] items2 = new MenuItem[] { new MenuItem(ConstantManager.ADD_RETURN_BLOCK),
				new MenuItem(ConstantManager.ADD_BREAK_BLOCK), new MenuItem(ConstantManager.ADD_CONTINUE_BLOCK) };

		Menu addControlBlock = new Menu(ConstantManager.ADD_CONTROY_BLOCK);
		addControlBlock.getItems().add(new MenuItem(ConstantManager.ADD_SELECT_BLOCK));
		addControlBlock.getItems().add(new MenuItem(ConstantManager.ADD_FOREACH_BLOCK));
		addControlBlock.getItems().add(new MenuItem(ConstantManager.ADD_THROW_BLOCK));

		Menu addCalculusBlock = new Menu(ConstantManager.ADD_CALCULUS_BLOCK);
		addCalculusBlock.getItems().add(new MenuItem(ConstantManager.ADD_DEFINE_VAR_BLOCK));
		addCalculusBlock.getItems().add(new MenuItem(ConstantManager.ADD_FORMULA_BLOCK));
		addCalculusBlock.getItems().add(new MenuItem(ConstantManager.ADD_CALL_BLOCK));

		logicContext.getItems().addAll(items1);
		logicContext.getItems().addAll(addControlBlock);
		logicContext.getItems().addAll(addCalculusBlock);
		logicContext.getItems().addAll(items2);

		//TODO debug 用
//		TableColumn<EditArea, String> tag = new TableColumn<>("tag");
//		tag.setMinWidth(40);
//		tag.setCellValueFactory(new PropertyValueFactory<EditArea, String>("tag"));
//
//		TableColumn<EditArea, String> step = new TableColumn<>("step");
//		step.setMinWidth(40);
//		step.setCellValueFactory(new PropertyValueFactory<EditArea, String>("step"));

		TableColumn<EditArea, String> logicOneCol = new TableColumn<>("");
		logicOneCol.setMinWidth(40);
		logicOneCol.setCellValueFactory(new PropertyValueFactory<EditArea, String>("logicOne"));

		TableColumn<EditArea, String> logicTwoCol = new TableColumn<>("");
		logicTwoCol.setMinWidth(40);
		logicTwoCol.setCellValueFactory(new PropertyValueFactory<EditArea, String>("logicTwo"));

		TableColumn<EditArea, String> logicThreeCol = new TableColumn<>("");
		logicThreeCol.setMinWidth(40);
		logicThreeCol.setCellValueFactory(new PropertyValueFactory<EditArea, String>("logicThree"));

		TableColumn<EditArea, String> sencondNameCol = new TableColumn<>("処理詳細内容");
		sencondNameCol.setMinWidth(300);
		sencondNameCol.setCellValueFactory(new PropertyValueFactory<EditArea, String>("editArea"));

		sencondNameCol.setOnEditStart((CellEditEvent<EditArea, String> t) -> {
			openDialog(stage, t);
		});

		TableColumn<EditArea, String> commentCol = new TableColumn<>("コメント");
		commentCol.setMinWidth(300);
		commentCol.setCellValueFactory(new PropertyValueFactory<EditArea, String>("comment"));

		//TODO　debug用
//		table.getColumns().addAll(numberCol, tag, step, logicOneCol, logicTwoCol, logicThreeCol, sencondNameCol,
//				commentCol);
		
		table.getColumns().addAll(numberCol, logicOneCol, logicTwoCol, logicThreeCol, sencondNameCol,
				commentCol);

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
		vbox.getChildren().addAll(hbox1,hbox2,hbox3,label, table);
		VBox.setVgrow(table, Priority.ALWAYS);

		stage.setScene(new Scene(vbox));
		stage.show();

	}

	/*
	 * 制御Brock追加
	 */
	private void addControlBlockLogic(Stage stage, Menu logicMenu, int index) {
		for (int i = 0; i < logicMenu.getItems().size(); i++) {
			MenuItem subMenu = logicMenu.getItems().get(i);
			if (ConstantManager.ADD_SELECT_BLOCK.equals(subMenu.getText())) {
				subMenu.setOnAction(ex -> {
					int idx = index;
					for (int j = index; j >= 0; j--) {
						if (ConstantManager.BLOCK_END_TAG.equals(editAreaData.get(j).getTag())) {
							break;
						} else if (ConstantManager.BLOCK_START_TAG.equals(editAreaData.get(j).getTag())) {
							idx = j;
							break;
						}
					}
					openDialog1(stage, idx, index);
				});
			} else if (ConstantManager.ADD_FOREACH_BLOCK.equals(subMenu.getText())) {
				subMenu.setOnAction(ex -> {
					int idx = index;
					for (int j = index; j >= 0; j--) {
						if (ConstantManager.BLOCK_END_TAG.equals(editAreaData.get(j).getTag())) {
							break;
						} else if (ConstantManager.BLOCK_START_TAG.equals(editAreaData.get(j).getTag())) {
							idx = j;
							break;
						}
					}
					openDialog2(stage, idx,index);
				});
			} else if (ConstantManager.ADD_THROW_BLOCK.equals(subMenu.getText())) {
				subMenu.setOnAction(ex -> {
					int idx = index;
					for (int j = index; j >= 0; j--) {
						if (ConstantManager.BLOCK_END_TAG.equals(editAreaData.get(j).getTag())) {
							break;
						} else if (ConstantManager.BLOCK_START_TAG.equals(editAreaData.get(j).getTag())) {
							idx = j;
							break;
						}
					}
					openDialog3(stage, idx,index);
				});
			}
		}
	}

	/*
	 * 計算Brock追加
	 */
	private void addCalculusBlockLogic(Stage stage, Menu logicMenu, int index) {
		for (int i = 0; i < logicMenu.getItems().size(); i++) {
			MenuItem subMenu = logicMenu.getItems().get(i);
			if (ConstantManager.ADD_DEFINE_VAR_BLOCK.equals(subMenu.getText())) {
				subMenu.setOnAction(ex -> {
					openDialog4(stage, index);
				});
			} else if (ConstantManager.ADD_FORMULA_BLOCK.equals(subMenu.getText())) {
				subMenu.setOnAction(ex -> {
					openDialog5(stage, index);
				});
			} else if (ConstantManager.ADD_CALL_BLOCK.equals(subMenu.getText())) {
				subMenu.setOnAction(ex -> {
					openDialog6(stage, index);
				});
			}
		}

	}

	/*
	 * メニュー機能
	 */
	private void menuLogic(ContextMenu logicContext, int index) {
		for (int i = 0; i < logicContext.getItems().size(); i++) {
			MenuItem subMenu = logicContext.getItems().get(i);
			if (ConstantManager.ADD_EMPTY_BLOCK.equals(subMenu.getText())) {
				subMenu.setOnAction(ex -> {
					String flg = ConstantManager.BLOCK_STEP_ZERO;
					if (index > 0) {
						EditArea beforeEdit = editAreaData.get(index - 1);
						flg = beforeEdit.getStep();
					}
					EditArea edit = new EditArea("", flg, "", "", "", "", "");
					editAreaData.add(index, edit);
				});
			} else if (ConstantManager.REMOVE_EMPTY_BLOCK.equals(subMenu.getText())) {
				subMenu.setOnAction(ex -> {
					EditArea edit = editAreaData.get(index);
					ObservableList<EditArea> ifLogicData = FXCollections.observableArrayList();
					if (ConstantManager.BLOCK_STEP_ZERO.equals(edit.getStep())) {
						ifLogicData.add(edit);
					} else if (ConstantManager.BLOCK_STEP_ONE.equals(edit.getStep())) {
						if (ConstantManager.BLOCK_START_TAG.equals(edit.getTag())) {
							for (int j = index; j < editAreaData.size(); j++) {
								ifLogicData.add(editAreaData.get(j));
								if (ConstantManager.BLOCK_END_TAG.equals(editAreaData.get(j).getTag())
										&& ConstantManager.BLOCK_STEP_ONE.equals(editAreaData.get(j).getStep())) {
									break;
								}
							}
						} else if (ConstantManager.BLOCK_END_TAG.equals(edit.getTag())) {
							for (int j = index - 1; j >= 0; j--) {
								ifLogicData.add(editAreaData.get(j));
								if (ConstantManager.BLOCK_START_TAG.equals(editAreaData.get(j).getTag())
										&& ConstantManager.BLOCK_STEP_ONE.equals(editAreaData.get(j).getStep())) {
									break;
								}
							}
						} else {
							for (int j = index; j < editAreaData.size(); j++) {
								ifLogicData.add(editAreaData.get(j));
								if (ConstantManager.BLOCK_END_TAG.equals(editAreaData.get(j).getTag())
										&& ConstantManager.BLOCK_STEP_ONE.equals(editAreaData.get(j).getStep())) {
									break;
								}
							}
							for (int j = index - 1; j >= 0; j--) {
								ifLogicData.add(editAreaData.get(j));
								if (ConstantManager.BLOCK_START_TAG.equals(editAreaData.get(j).getTag())
										&& ConstantManager.BLOCK_STEP_ONE.equals(editAreaData.get(j).getStep())) {
									break;
								}
							}
						}

					} else if (ConstantManager.BLOCK_STEP_TWO.equals(edit.getStep())) {
						if (ConstantManager.BLOCK_START_TAG.equals(edit.getTag())) {
							for (int j = index; j < editAreaData.size(); j++) {
								ifLogicData.add(editAreaData.get(j));
								if (ConstantManager.BLOCK_END_TAG.equals(editAreaData.get(j).getTag())
										&& ConstantManager.BLOCK_STEP_TWO.equals(editAreaData.get(j).getStep())) {
									break;
								}
							}
						} else if (ConstantManager.BLOCK_END_TAG.equals(edit.getTag())) {
							for (int j = index - 1; j >= 0; j--) {
								ifLogicData.add(editAreaData.get(j));
								if (ConstantManager.BLOCK_START_TAG.equals(editAreaData.get(j).getTag())
										&& ConstantManager.BLOCK_STEP_TWO.equals(editAreaData.get(j).getStep())) {
									break;
								}
							}
						} else {
							for (int j = index; j < editAreaData.size(); j++) {
								ifLogicData.add(editAreaData.get(j));
								if (ConstantManager.BLOCK_END_TAG.equals(editAreaData.get(j).getTag())
										&& ConstantManager.BLOCK_STEP_TWO.equals(editAreaData.get(j).getStep())) {
									break;
								}
							}
							for (int j = index - 1; j >= 0; j--) {
								ifLogicData.add(editAreaData.get(j));
								if (ConstantManager.BLOCK_START_TAG.equals(editAreaData.get(j).getTag())
										&& ConstantManager.BLOCK_STEP_TWO.equals(editAreaData.get(j).getStep())) {
									break;
								}
							}
						}
					} else if (ConstantManager.BLOCK_STEP_THREE.equals(edit.getStep())) {
						if (ConstantManager.BLOCK_START_TAG.equals(edit.getTag())) {
							for (int j = index; j < editAreaData.size(); j++) {
								ifLogicData.add(editAreaData.get(j));
								if (ConstantManager.BLOCK_END_TAG.equals(editAreaData.get(j).getTag())
										&& ConstantManager.BLOCK_STEP_THREE.equals(editAreaData.get(j).getStep())) {
									break;
								}
							}
						} else if (ConstantManager.BLOCK_END_TAG.equals(edit.getTag())) {
							for (int j = index - 1; j >= 0; j--) {
								ifLogicData.add(editAreaData.get(j));
								if (ConstantManager.BLOCK_START_TAG.equals(editAreaData.get(j).getTag())
										&& ConstantManager.BLOCK_STEP_THREE.equals(editAreaData.get(j).getStep())) {
									break;
								}
							}
						} else {
							for (int j = index; j < editAreaData.size(); j++) {
								ifLogicData.add(editAreaData.get(j));
								if (ConstantManager.BLOCK_END_TAG.equals(editAreaData.get(j).getTag())
										&& ConstantManager.BLOCK_STEP_THREE.equals(editAreaData.get(j).getStep())) {
									break;
								}
							}
							for (int j = index - 1; j >= 0; j--) {
								ifLogicData.add(editAreaData.get(j));
								if (ConstantManager.BLOCK_START_TAG.equals(editAreaData.get(j).getTag())
										&& ConstantManager.BLOCK_STEP_THREE.equals(editAreaData.get(j).getStep())) {
									break;
								}
							}
						}
					}
					editAreaData.removeAll(ifLogicData);

				});
			} else if (ConstantManager.ADD_RETURN_BLOCK.equals(subMenu.getText())) {
				subMenu.setOnAction(ex -> {
					String flg = ConstantManager.BLOCK_STEP_ZERO;
					if (index > 0) {
						EditArea beforeEdit = editAreaData.get(index - 1);
						flg = beforeEdit.getStep();
					}
					EditArea edit = new EditArea("", flg, "", "", "", "RETURN", "");
					editAreaData.add(index, edit);
				});
			} else if (ConstantManager.ADD_BREAK_BLOCK.equals(subMenu.getText())) {
				subMenu.setOnAction(ex -> {
					String flg = ConstantManager.BLOCK_STEP_ZERO;
					if (index > 0) {
						EditArea beforeEdit = editAreaData.get(index - 1);
						flg = beforeEdit.getStep();
					}
					EditArea edit = new EditArea("", flg, "", "", "", "BREAK", "");
					editAreaData.add(index, edit);
				});
			} else if (ConstantManager.ADD_CONTINUE_BLOCK.equals(subMenu.getText())) {
				subMenu.setOnAction(ex -> {
					String flg = ConstantManager.BLOCK_STEP_ZERO;
					if (index > 0) {
						EditArea beforeEdit = editAreaData.get(index - 1);
						flg = beforeEdit.getStep();
					}
					EditArea edit = new EditArea("", flg, "", "", "", "CONTINUE", "");
					editAreaData.add(index, edit);
				});
			}
		}
	}

	private String openDialog1(Stage stage, int idx, int index) {
		Stage newStage = new Stage();
		newStage.initModality(Modality.APPLICATION_MODAL);
		newStage.initOwner(stage);

		newStage.setTitle("項目選択");

		Label label1 = new Label("項目1を入力してください:");
		label1.setMaxSize(200, 20);
		TextField text1 = new TextField();
		text1.setMaxSize(200, 20);
		Label label2 = new Label("項目2を入力してください:");
		label2.setMaxSize(200, 20);
		TextField text2 = new TextField();
		text2.setMaxSize(200, 20);

		Button btn = new Button();
		btn.setText("登録");
		btn.setOnAction(e -> {
			EditArea edit = editAreaData.get(idx);
			System.out.println(idx);
			System.out.println(edit.getStep());
			ObservableList<EditArea> ifLogicData = FXCollections.observableArrayList();
			if (ConstantManager.BLOCK_STEP_ONE.equals(edit.getStep())) {
				ifLogicData.addAll(
						new EditArea(ConstantManager.BLOCK_START_TAG, ConstantManager.BLOCK_STEP_TWO, "", "IF", "",
								text1.getText(), ""),
						new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""),
						new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""),
						new EditArea("", ConstantManager.BLOCK_STEP_TWO, "", "ELSE", "", "", ""),
						new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""),
						new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""), new EditArea(
								ConstantManager.BLOCK_END_TAG, ConstantManager.BLOCK_STEP_TWO, "", "END", "", "", ""));
			} else if (ConstantManager.BLOCK_STEP_TWO.equals(edit.getStep())) {
				ifLogicData.addAll(
						new EditArea(ConstantManager.BLOCK_START_TAG, ConstantManager.BLOCK_STEP_THREE, "", "", "IF",
								text1.getText(), ""),
						new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""),
						new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""),
						new EditArea("", ConstantManager.BLOCK_STEP_THREE, "", "", "ELSE", "", ""),
						new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""),
						new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""),
						new EditArea(ConstantManager.BLOCK_END_TAG, ConstantManager.BLOCK_STEP_THREE, "", "", "END", "",
								""));
			} else {
				ifLogicData.addAll(
						new EditArea(ConstantManager.BLOCK_START_TAG, ConstantManager.BLOCK_STEP_ONE, "IF", "", "",
								text1.getText(), ""),
						new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""),
						new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""),
						new EditArea("", ConstantManager.BLOCK_STEP_ONE, "ELSE", "", "", "", ""),
						new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""),
						new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""), new EditArea(
								ConstantManager.BLOCK_END_TAG, ConstantManager.BLOCK_STEP_ONE, "END", "", "", "", ""));
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

	private String openDialog2(Stage stage, int idx,int index) {
		Stage newStage = new Stage();
		newStage.initModality(Modality.APPLICATION_MODAL);
		newStage.initOwner(stage);

		newStage.setTitle("項目選択");

		Label label1 = new Label("条件を入力してください:");
		label1.setMaxSize(200, 20);
		TextField text1 = new TextField();
		text1.setMaxSize(200, 20);

		Button btn = new Button();
		btn.setText("登録");
		btn.setOnAction(e -> {
			EditArea edit = editAreaData.get(idx);
			ObservableList<EditArea> ifLogicData = FXCollections.observableArrayList();
			if (ConstantManager.BLOCK_STEP_ONE.equals(edit.getStep())) {
				ifLogicData.addAll(
						new EditArea(ConstantManager.BLOCK_START_TAG, ConstantManager.BLOCK_STEP_TWO, "", "FOR", "",
								text1.getText(), ""),
						new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""),
						new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""),
						new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""), new EditArea(
								ConstantManager.BLOCK_END_TAG, ConstantManager.BLOCK_STEP_TWO, "", "END", "", "", ""));
			} else if (ConstantManager.BLOCK_STEP_TWO.equals(edit.getStep())) {
				ifLogicData.addAll(
						new EditArea(ConstantManager.BLOCK_START_TAG, ConstantManager.BLOCK_STEP_THREE, "", "", "FOR",
								text1.getText(), ""),
						new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""),
						new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""),
						new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""),
						new EditArea(ConstantManager.BLOCK_END_TAG, ConstantManager.BLOCK_STEP_THREE, "", "", "END", "",
								""));
			} else {
				ifLogicData.addAll(
						new EditArea(ConstantManager.BLOCK_START_TAG, ConstantManager.BLOCK_STEP_ONE, "FOR", "", "",
								text1.getText(), ""),
						new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""),
						new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""),
						new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""), new EditArea(
								ConstantManager.BLOCK_END_TAG, ConstantManager.BLOCK_STEP_ONE, "END", "", "", "", ""));
			}

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

	private String openDialog3(Stage stage, int idx,int index) {
		Stage newStage = new Stage();
		newStage.initModality(Modality.APPLICATION_MODAL);
		newStage.initOwner(stage);

		newStage.setTitle("項目選択");

		Label label1 = new Label("対象リスト項目を入力してください:");
		label1.setMaxSize(200, 20);
		TextField text1 = new TextField();
		text1.setMaxSize(200, 20);

		Button btn = new Button();
		btn.setText("登録");
		btn.setOnAction(e -> {

			EditArea edit = editAreaData.get(idx);
			ObservableList<EditArea> ifLogicData = FXCollections.observableArrayList();
			if (ConstantManager.BLOCK_STEP_ONE.equals(edit.getStep())) {
				ifLogicData.addAll(
						new EditArea(ConstantManager.BLOCK_START_TAG, ConstantManager.BLOCK_STEP_TWO, "", "TRY", "",
								text1.getText(), ""),
						new EditArea("", ConstantManager.BLOCK_STEP_TWO, "", "CATCH", "", "", ""),
						new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""),
						new EditArea("", ConstantManager.BLOCK_STEP_TWO, "", "FINALLY", "", "", ""), new EditArea(
								ConstantManager.BLOCK_END_TAG, ConstantManager.BLOCK_STEP_TWO, "", "END", "", "", ""));
			} else if (ConstantManager.BLOCK_STEP_TWO.equals(edit.getStep())) {
				ifLogicData.addAll(
						new EditArea(ConstantManager.BLOCK_START_TAG, ConstantManager.BLOCK_STEP_THREE, "", "", "TRY",
								text1.getText(), ""),
						new EditArea("", ConstantManager.BLOCK_STEP_THREE, "", "", "CATCH", "", ""),
						new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""),
						new EditArea("", ConstantManager.BLOCK_STEP_THREE, "", "", "FINALLY", "", ""),
						new EditArea(ConstantManager.BLOCK_END_TAG, ConstantManager.BLOCK_STEP_THREE, "", "", "END", "",
								""));
			} else {
				ifLogicData.addAll(
						new EditArea(ConstantManager.BLOCK_START_TAG, ConstantManager.BLOCK_STEP_ONE, "TRY", "", "",
								text1.getText(), ""),
						new EditArea("", ConstantManager.BLOCK_STEP_ONE, "CATCH", "", "", "", ""),
						new EditArea("", ConstantManager.BLOCK_STEP_ZERO, "", "", "", "", ""),
						new EditArea("", ConstantManager.BLOCK_STEP_ONE, "FINALLY", "", "", "", ""), new EditArea(
								ConstantManager.BLOCK_END_TAG, ConstantManager.BLOCK_STEP_ONE, "END", "", "", "", ""));
			}
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

		newStage.setTitle("項目選択");

		Label label1 = new Label("対象リスト項目を入力してください:");
		label1.setMaxSize(200, 20);
		TextField text1 = new TextField();
		text1.setMaxSize(200, 20);

		Button btn = new Button();
		btn.setText("登録");
		btn.setOnAction(e -> {
			String flg = ConstantManager.BLOCK_STEP_ZERO;

			EditArea edit = new EditArea("", flg, "", "", "", text1.getText(), "");
			editAreaData.add(index, edit);
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

		newStage.setTitle("項目選択");

		Label label1 = new Label("対象リスト項目を入力してください:");
		label1.setMaxSize(200, 20);
		TextField text1 = new TextField();
		text1.setMaxSize(200, 20);

		Button btn = new Button();
		btn.setText("登録");
		btn.setOnAction(e -> {
			String flg = ConstantManager.BLOCK_STEP_ZERO;
			EditArea edit = new EditArea("", flg, "", "", "", text1.getText(), "");
			editAreaData.add(index, edit);
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

		newStage.setTitle("項目選択");

		Label label1 = new Label("対象リスト項目を入力してください:");
		label1.setMaxSize(200, 20);
		TextField text1 = new TextField();
		text1.setMaxSize(200, 20);

		Button btn = new Button();
		btn.setText("登録");
		btn.setOnAction(e -> {
			String flg = ConstantManager.BLOCK_STEP_ZERO;

			EditArea edit = new EditArea("", flg, "", "", "", text1.getText(), "");
			editAreaData.add(index, edit);
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

		newStage.setTitle("項目選択");
		TableView<AreaType> table1 = new TableView<>();

		TableColumn<AreaType, String> col11 = new TableColumn<>("No");
		col11.setMinWidth(20);
		col11.setCellValueFactory(new PropertyValueFactory<AreaType, String>("No"));
		TableColumn<AreaType, String> col12 = new TableColumn<>("領域種別");
		col12.setMinWidth(200);
		col12.setCellValueFactory(new PropertyValueFactory<AreaType, String>("areaType"));
		TableColumn<AreaType, String> col13 = new TableColumn<>("領域名称");
		col13.setMinWidth(200);
		col13.setCellValueFactory(new PropertyValueFactory<AreaType, String>("areaName"));
		table1.getColumns().addAll(col11, col12, col13);
		table1.setItems(areaTypeData);

		TableView<AreaName> table2 = new TableView<>();
		TableColumn<AreaName, String> col21 = new TableColumn<>("No");
		col21.setMinWidth(20);
		col21.setCellValueFactory(new PropertyValueFactory<AreaName, String>("No"));
		TableColumn<AreaName, String> col22 = new TableColumn<>("領域名称");
		col22.setMinWidth(200);
		col22.setCellValueFactory(new PropertyValueFactory<AreaName, String>("areaName"));
		TableColumn<AreaName, String> col23 = new TableColumn<>("項目名");
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
