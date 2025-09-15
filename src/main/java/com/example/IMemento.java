package com.example;

import java.time.LocalDateTime;

public interface IMemento {
    LocalDateTime getTimeSaved();
    String getDescription();
}
