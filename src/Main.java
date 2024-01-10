import java.util.ArrayList;

public class Main {
    static final int LOANLENGTH = 7;
    static ArrayList<Customer> assignedCustomers = new ArrayList<>();
    static ArrayList<BorrowRecord> booksOut = new ArrayList<>();
    static ArrayList<LibraryRecord> booksInLibrary = new ArrayList<>();
    public static void main(String[] args) {
        Book book = newBook(1,"The Hobbit", "JRR Tolkien", 1, 1);
        
    }
    public static Book newBook(int bookID, String title, String author, int bookcaseID, int shelfID) {
        Book book = new Book(bookID, title, author);
        LibraryRecord newBookStatus = new LibraryRecord(book, bookcaseID, shelfID);
        book.setStatus(newBookStatus);
        booksInLibrary.add(newBookStatus);
        return book;
    }
    public static Customer newCustomer(String firstName, String surname, int IDNumber) {
        Customer customer = new Customer(firstName, surname, IDNumber);
        assignedCustomers.add(customer);
        return customer;
    }
    public static BorrowRecord borrowBook(LibraryRecord bookRecord, Customer customer, Date date) {
        Book book = bookRecord.getBook();
        BorrowRecord newLoan = new BorrowRecord(customer, book, date, LOANLENGTH);
        book.setStatus(newLoan);
        customer.addLoanedBook(newLoan);
        for (int i=0; i<booksInLibrary.size(); i++) {
            if (booksInLibrary.get(i).getBook() == bookRecord.getBook()) {
                booksInLibrary.remove(i);
                break;
            }
        }
        booksOut.add(newLoan);
        return newLoan;
    }
    public static LibraryRecord returnBook(Customer customer, BorrowRecord curBorrowRecord, int bookcaseID, int shelfID) {
        LibraryRecord returnToBookshelf = new LibraryRecord(curBorrowRecord.getBook(), bookcaseID, shelfID);
        customer.removeLoanedBook(curBorrowRecord, returnToBookshelf);
        for (int i=0; i<booksOut.size(); i++) {
            if (booksOut.get(i).getBook() == curBorrowRecord.getBook()) {
                booksOut.remove(i);
                break;
            }
        }
        booksInLibrary.add(returnToBookshelf);
        return returnToBookshelf;
    }
}
