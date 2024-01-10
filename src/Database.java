import java.util.ArrayList;

public class Database {
    public static int LOANLENGTH = 7;
    static ArrayList<Customer> assignedCustomers = new ArrayList<>();
    static ArrayList<BorrowRecord> booksOut = new ArrayList<>();
    static ArrayList<LibraryRecord> booksInLibrary = new ArrayList<>();
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
    public static String arrayListToString(ArrayList<?> list) {
        StringBuilder sb = new StringBuilder();
        for (Object each : list) {
            sb.append(each.toString()+"\n");
        }
        return sb.toString();
    }
    public static String arrayListToString(ArrayList<?> list, String sep) {
        StringBuilder sb = new StringBuilder();
        for (Object each : list) {
            sb.append(each.toString()+sep);
        }
        return sb.toString();
    }
    public static LibraryRecord bookInLibraryQuery(int bookID) {
        for (LibraryRecord bookRecord : booksInLibrary) {
            if (bookRecord.getBook().getID() == bookID) {
                return bookRecord;
            }
        }
        System.out.println("This book is not available / does not exist.");
        return null;
    }
    public static Customer customerQuery(int customerID) {
        for (Customer each : assignedCustomers) {
            if (each.getID() == customerID) {
                return each;
            }
        }
        System.out.println("This customer does not exist.");
        return null;
    }
    public static BorrowRecord bookLoanTableQuery(int bookID) {
        for (BorrowRecord bookRecord : booksOut) {
            if (bookRecord.getBook().getID() == bookID) {
                return bookRecord;
            }
        }
        System.out.println("This book is not loaned / does not exist.");
        return null;
    }
}
