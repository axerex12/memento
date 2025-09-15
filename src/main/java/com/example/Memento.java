package com.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Memento implements IMemento {
    private final int[] options;
    private final boolean isSelected;
    private final LocalDateTime timeSaved;

    public Memento(int[] options, boolean isSelected) {
        this.options = options.clone(); // clone to avoid reference issues
        this.isSelected = isSelected;
        this.timeSaved = LocalDateTime.now();
    }

    public int[] getOptions() {
        return options.clone(); // return clone for safety
    }

    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public LocalDateTime getTimeSaved() {
        return timeSaved;
    }

    @Override
    public String getDescription() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return "Time: " + timeSaved.format(formatter) +
               " | Options: [" + options[0] + ", " + options[1] + ", " + options[2] + "]" +
               " | Checkbox: " + isSelected;
    }
}

