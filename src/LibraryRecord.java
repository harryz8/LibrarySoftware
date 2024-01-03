public class LibraryRecord extends Status{
    int bookcaseID;
    int shelfID;
    Book book;
    public LibraryRecord() {
        super("Library Record");
    }
    public void writeRecord(Book book, int bookcaseID, int shelfID) {
        this.book = book;
        this.bookcaseID = bookcaseID;
        this.shelfID = shelfID;
    }
    public String toString() {
        return book.toString()+"\n"+Integer.toString(bookcaseID)+","+Integer.toString(shelfID);
    }
}
