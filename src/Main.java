public class Main {
    static final int LOANLENGTH = 7;
    public static void main(String[] args) {

    }
    public static Book newBook(int bookID, String title, String author, int bookcaseID, int shelfID) {
        Book book = new Book(bookID, title, author);
        LibraryRecord newBookStatus = new LibraryRecord(book, bookcaseID, shelfID);
        book.setStatus(newBookStatus);
        return book;
    }
    public static Customer newCustomer(String firstName, String surname, int IDNumber) {
        return new Customer(firstName, surname, IDNumber);
    }
    public static Status borrowBook(Book book, Customer customer, Date date) {
        BorrowRecord newLoan = new BorrowRecord(customer, book, date, LOANLENGTH);
        book.setStatus(newLoan);
        customer.addLoanedBook(newLoan);
        return newLoan;
    }
}
