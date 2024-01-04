public class Main {
    public static void main(String[] args) {
        
    }
    public static Book newBook(int bookID, String title, String author, int bookcaseID, int shelfID) {
        LibraryRecord newBookStatus = new LibraryRecord();
        Book book = new Book(bookID, title, author, newBookStatus);
        newBookStatus.writeRecord(book, bookcaseID, shelfID);
        return book;
    }
}
