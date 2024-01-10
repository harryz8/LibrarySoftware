import java.util.ArrayList;
import java.io.*;
public class Database {
    String databasePath;
    public int LOANLENGTH = 7;
    ArrayList<Customer> assignedCustomers = new ArrayList<>();
    ArrayList<BorrowRecord> booksOut = new ArrayList<>();
    ArrayList<LibraryRecord> booksInLibrary = new ArrayList<>();
    public Database(String folderPath) {
        databasePath = folderPath;
        //https://stackoverflow.com/questions/3634853/how-to-create-a-directory-in-java
        File databaseDir = new File(databasePath);
        if (databaseDir.exists()) {
            importIn(databasePath);
        }
        else {
            databaseDir.mkdirs();
        }
    }
    public Book newBook(int bookID, String title, String author, int bookcaseID, int shelfID) {
        Book book = new Book(bookID, title, author);
        LibraryRecord newBookStatus = new LibraryRecord(book, bookcaseID, shelfID);
        book.setStatus(newBookStatus);
        booksInLibrary.add(newBookStatus);
        return book;
    }
    public Customer newCustomer(String firstName, String surname, int IDNumber) {
        Customer customer = new Customer(firstName, surname, IDNumber);
        assignedCustomers.add(customer);
        return customer;
    }
    public BorrowRecord borrowBook(LibraryRecord bookRecord, Customer customer, Date date) {
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
    public LibraryRecord returnBook(Customer customer, BorrowRecord curBorrowRecord, int bookcaseID, int shelfID) {
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
    public LibraryRecord bookInLibraryQuery(int bookID) {
        for (LibraryRecord bookRecord : booksInLibrary) {
            if (bookRecord.getBook().getID() == bookID) {
                return bookRecord;
            }
        }
        System.out.println("This book is not available / does not exist.");
        return null;
    }
    public Customer customerQuery(int customerID) {
        for (Customer each : assignedCustomers) {
            if (each.getID() == customerID) {
                return each;
            }
        }
        System.out.println("This customer does not exist.");
        return null;
    }
    public BorrowRecord bookLoanTableQuery(int bookID, Date date) {
        for (BorrowRecord bookRecord : booksOut) {
            if (bookRecord.getBook().getID() == bookID && bookRecord.getDate().equals(date)) {
                return bookRecord;
            }
        }
        System.out.println("This book is not loaned / does not exist.");
        return null;
    }
    public void export() {
        String assignedCustomersText = arrayListToString(assignedCustomers);
        String booksInLibraryText = arrayListToString(booksInLibrary);
        String booksOutText = arrayListToString(booksOut);
        try {
            Open customersFile = new Open(databasePath + Open.getSeparator() + "customers.zld", 'w');
            customersFile.write(assignedCustomersText);
            customersFile.close();
        }
        catch (IOException e) {
            System.out.println("An exception occurred when saving the customers file: "+e.getMessage());
        }
        try {
            Open libraryBooksFile = new Open(databasePath + Open.getSeparator() + "recordsOfBooksInLibrary.zld", 'w');
            libraryBooksFile.write(booksInLibraryText);
            libraryBooksFile.close();
        }
        catch (IOException e) {
            System.out.println("An exception occurred when saving the recordsOfBooksInLibrary file: "+e.getMessage());
        }
        try {
            Open loanedBooksFile = new Open(databasePath + Open.getSeparator() + "recordsOfLoanedBooks.zld", 'w');
            loanedBooksFile.write(booksOutText);
            loanedBooksFile.close();
        }
        catch (IOException e) {
            System.out.println("An exception occurred when saving the recordsOfLoanedBooks file: "+e.getMessage());
        }
    }
    public void importIn(String itsDatabaseFolderPath) {
        try {
            Open customersFile = new Open(itsDatabaseFolderPath + Open.getSeparator() + "customers.zld", 'r');
            String line = customersFile.readLine();
            while (line != null) {
                String[] lineArray = line.split(",");
                Customer lineCustomer = new Customer(lineArray[1], lineArray[2], Integer.parseInt(lineArray[0]));
                assignedCustomers.add(lineCustomer);
                line = customersFile.readLine();
            }
            customersFile.close();
        }
        catch (IOException e) {
            System.out.println("An exception occurred when reading the customers file: "+e.getMessage());
        }
        try {
            Open libraryBooksFile = new Open(itsDatabaseFolderPath + Open.getSeparator() + "recordsOfBooksInLibrary.zld", 'r');
            String line = libraryBooksFile.readLine();
            while (line != null) {
                String[] lineArray = line.split("¦");
                String[] bookArray = lineArray[1].split(",");
                String[] locationArray = lineArray[2].split(",");
                Book book = new Book(Integer.parseInt(bookArray[0]), bookArray[1], bookArray[2]);
                LibraryRecord libraryRecord = new LibraryRecord(book, Integer.parseInt(locationArray[0]), Integer.parseInt(locationArray[1]));
                booksInLibrary.add(libraryRecord);
                book.setStatus(libraryRecord);
                line = libraryBooksFile.readLine();
            }
            libraryBooksFile.close();
        }
        catch (IOException e) {
            System.out.println("An exception occurred when reading the recordsOfBooksInLibrary file: "+e.getMessage());
        }
        try {
            Open loanedBooksFile = new Open(itsDatabaseFolderPath + Open.getSeparator() + "recordsOfLoanedBooks.zld", 'r');
            String line = loanedBooksFile.readLine();
            while (line != null) {
                String[] lineArray = line.split("¦");
                String[] bookArray = lineArray[1].split(",");
                String[] customerArray = lineArray[2].split(",");
                String[] dateArray = lineArray[3].split("/");
                Date date = new Date(Integer.parseInt(dateArray[0]), Integer.parseInt(dateArray[1]), Integer.parseInt(dateArray[2]));
                Book book = new Book(Integer.parseInt(bookArray[0]), bookArray[1], bookArray[2]);
                Customer newCustomer = new Customer(customerArray[1], customerArray[2], Integer.parseInt(customerArray[0]));
                for (Customer eachCustomer : assignedCustomers) {
                    if (eachCustomer.equals(newCustomer)) {
                        BorrowRecord loanRecord = new BorrowRecord(eachCustomer, book, date, LOANLENGTH);
                        eachCustomer.addLoanedBook(loanRecord);
                        book.setStatus(loanRecord);
                    }
                }
                line = loanedBooksFile.readLine();
            }
            loanedBooksFile.close();
        }
        catch (IOException e) {
            System.out.println("An exception occurred when reading the recordsOfLoanedBooks file: "+e.getMessage());
        }
    }
}
