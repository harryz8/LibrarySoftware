public class LibraryRecord extends Status{
    int bookcaseID;
    int shelfID;
    Book book;
    public LibraryRecord(Book book, int bookcaseID, int shelfID) {
        super("Library Record");
        this.book = book;
        this.bookcaseID = bookcaseID;
        this.shelfID = shelfID;
    }
    public String toString() {
        return this.getType()+"¦"+this.getBook().toString()+"¦"+Integer.toString(bookcaseID)+","+Integer.toString(shelfID);
    }

    public Book getBook() {
        return book;
    }
}
