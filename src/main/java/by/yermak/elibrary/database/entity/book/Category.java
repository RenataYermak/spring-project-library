package by.yermak.elibrary.database.entity.book;

public enum Category {
    HORROR, FICTION, NOVEL, SCIENCE, DETECTIVE;

    public String getCategory() {
        return name();
    }
}