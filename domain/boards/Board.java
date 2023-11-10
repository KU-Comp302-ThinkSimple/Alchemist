package domain.boards;

public abstract class Board {
    int size;
    String name;

    public Board(int size, String name) {
        this.size = size;
        this.name = name;
    }

    public Board() {

    }
}
