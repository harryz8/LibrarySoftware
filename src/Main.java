import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static final int LOANLENGTH = 7;
    static ArrayList<Customer> assignedCustomers = new ArrayList<>();
    static ArrayList<BorrowRecord> booksOut = new ArrayList<>();
    static ArrayList<LibraryRecord> booksInLibrary = new ArrayList<>();
    public static void main(String[] args) {
        boolean gate = true;
        while (gate) {
            System.out.print("Choose an option: \n 1. Add a book to the library\n 2. Add a customer to the library\n 3. Process a book loan\n 4. Process a book return\n 5. Output Database\n 6. Exit\nChoose an option by typing its number: ");
            Scanner input = new Scanner(System.in);
            int option = input.nextInt();
            input.nextLine();
            switch (option) {
                case 1:
                    System.out.println("Enter book ID: ");
                    int bkID = input.nextInt();
                    input.nextLine();
                    System.out.println("Enter book title: ");
                    String title = input.nextLine();
                    System.out.println("Enter book author: ");
                    String author = input.nextLine();
                    System.out.println("Enter bookcase ID: ");
                    int bkcaseID = input.nextInt();
                    input.nextLine();
                    System.out.println("Enter shelf ID: ");
                    int shlfID = input.nextInt();
                    input.nextLine();
                    newBook(bkID, title, author, bkcaseID, shlfID);
                    break;
                case 2:
                    System.out.println("Enter customer first name: ");
                    String fname = input.nextLine();
                    System.out.println("Enter customer surname: ");
                    String sname = input.nextLine();
                    System.out.println("Enter customer ID: ");
                    int cID = input.nextInt();
                    input.nextLine();
                    newCustomer(fname, sname, cID);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    System.out.println("****|CUSTOMERS|****");
                    System.out.println(arrayListToString(assignedCustomers));
                    System.out.println("****|BOOKS IN LIBRARY|****");
                    System.out.println(arrayListToString(booksInLibrary));
                    System.out.println("****|BOOKS LOANED|****");
                    System.out.println(arrayListToString(booksOut));
                    break;
                default:
                    gate = false;
                    break;
            }
            input.close();
        }
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
}
