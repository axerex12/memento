package command_pattern;


class MoveUpCommand implements Command {
    private final PixelGrid grid;
    public MoveUpCommand(PixelGrid grid) { this.grid = grid; }
    public void execute() { grid.move(-1, 0); }
}

class MoveDownCommand implements Command {
    private final PixelGrid grid;
    public MoveDownCommand(PixelGrid grid) { this.grid = grid; }
    public void execute() { grid.move(1, 0); }
}

class MoveLeftCommand implements Command {
    private final PixelGrid grid;
    public MoveLeftCommand(PixelGrid grid) { this.grid = grid; }
    public void execute() { grid.move(0, -1); }
}

class MoveRightCommand implements Command {
    private final PixelGrid grid;
    public MoveRightCommand(PixelGrid grid) { this.grid = grid; }
    public void execute() { grid.move(0, 1); }
}

class TogglePixelCommand implements Command {
    private final PixelGrid grid;
    public TogglePixelCommand(PixelGrid grid) { this.grid = grid; }
    public void execute() { grid.toggle(); }
}

class PrintPixelCommand implements Command {
    private final PixelGrid grid;
    public PrintPixelCommand(PixelGrid grid) { this.grid = grid; }
    public void execute() { grid.print(); }
}
