package com.example;

import java.util.ArrayDeque;
import java.util.Deque;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.control.ListCell;

public class Controller {
    private final Model model;
    private final Gui gui;

    private final Deque<IMemento> undoStack = new ArrayDeque<>();
    private final Deque<IMemento> redoStack = new ArrayDeque<>();

    public Controller(Gui gui) {
        this.gui = gui;
        this.model = new Model();
        undoStack.push(model.createMemento()); // initial state
    }

    public void setOption(int optionNumber, int choice) {
        redoStack.clear(); // clear redo on new change
        undoStack.push(model.createMemento());
        model.setOption(optionNumber, choice);
    }

    public void setIsSelected(boolean isSelected) {
        redoStack.clear(); // clear redo on new change
        undoStack.push(model.createMemento());
        model.setIsSelected(isSelected);
    }

    public int getOption(int optionNumber) {
        return model.getOption(optionNumber);
    }

    public boolean getIsSelected() {
        return model.getIsSelected();
    }

    public void undo() {
        if (undoStack.size() > 1) { // keep at least initial state
            redoStack.push(undoStack.pop());
            IMemento previousState = undoStack.peek();
            model.restoreState(previousState);
            gui.updateGui();
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            IMemento redoState = redoStack.pop();
            undoStack.push(redoState);
            model.restoreState(redoState);
            gui.updateGui();
        }
    }

    public void showHistoryWindow() {
        Stage historyStage = new Stage();
        ListView<IMemento> listView = new ListView<>();
        listView.getItems().addAll(undoStack);

        listView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(IMemento item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getDescription());
            }
        });

        listView.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                model.restoreState(newSel);
                gui.updateGui();
            }
        });

        Scene scene = new Scene(new VBox(listView), 400, 300);
        historyStage.setScene(scene);
        historyStage.setTitle("History of States");
        historyStage.show();
    }
}
