import java.util.Scanner;
import com.Database;
import com.library.*;
import com.toolkit.*;
public class Main {
    public static void main(String[] args) {
        boolean gate = true;
        Database libraryDatabase = new Database("defaultDatabase", 7);
        Scanner input = new Scanner(System.in);
        while (gate) {
            System.out.println();
            System.out.print("Choose an option: \n 1. Add a book to the library\n 2. Add a customer to the library\n 3. Process a book loan\n 4. Process a book return\n 5. View customer's current loans\n 6. Exit\nChoose an option by typing its number: ");
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
                    libraryDatabase.newBook(bkID, title, author, bkcaseID, shlfID);
                    break;
                case 2:
                    System.out.println("Enter customer first name: ");
                    String fname = input.nextLine();
                    System.out.println("Enter customer surname: ");
                    String sname = input.nextLine();
                    System.out.println("Enter customer ID: ");
                    int cID = input.nextInt();
                    input.nextLine();
                    libraryDatabase.newCustomer(fname, sname, cID);
                    break;
                case 3:
                    System.out.println("Enter book ID: ");
                    int bkID1 = input.nextInt();
                    input.nextLine();
                    System.out.println("Enter customer ID: ");
                    int cID2 = input.nextInt();
                    input.nextLine();
                    System.out.println("Enter today's day number: ");
                    int day = input.nextInt();
                    input.nextLine();
                    System.out.println("Enter today's month number: ");
                    int month = input.nextInt();
                    input.nextLine();
                    System.out.println("Enter the year: ");
                    int year = input.nextInt();
                    input.nextLine();
                    LibraryRecord bookRecord = libraryDatabase.bookInLibraryQuery(bkID1);
                    Customer customer1 = libraryDatabase.customerQuery(cID2);
                    if (bookRecord != null && customer1 != null) {
                        libraryDatabase.borrowBook(libraryDatabase.bookInLibraryQuery(bkID1), customer1, new Date(day, month, year));
                    }
                    break;
                case 4:
                    System.out.println("Enter book ID: ");
                    int bkID2 = input.nextInt();
                    input.nextLine();
                    System.out.println("Enter customer ID: ");
                    int cID3 = input.nextInt();
                    input.nextLine();
                    System.out.println("Enter today's day number: ");
                    int day2 = input.nextInt();
                    input.nextLine();
                    System.out.println("Enter today's month number: ");
                    int month2 = input.nextInt();
                    input.nextLine();
                    System.out.println("Enter the year: ");
                    int year2 = input.nextInt();
                    input.nextLine();
                    System.out.println("Enter bookcase ID: ");
                    int bkcaseID2 = input.nextInt();
                    input.nextLine();
                    System.out.println("Enter shelf ID: ");
                    int shlfID2 = input.nextInt();
                    input.nextLine();
                    Date todaysDate = new Date(day2, month2, year2);
                    Customer customer2 = libraryDatabase.customerQuery(cID3);
                    if (customer2 != null) {
                        BorrowRecord bookRecord2 = libraryDatabase.bookLoanTableQuery(bkID2, customer2);
                        Date borrowDate = bookRecord2.getDate();
                        Date loanEndDate = borrowDate.copy();
                        loanEndDate.addDays(bookRecord2.getLoanLength());
                        if (bookRecord2 != null) {
                            libraryDatabase.returnBook(customer2, bookRecord2, bkcaseID2, shlfID2);
                            if (loanEndDate.compareTo(todaysDate)<0) {
                                System.out.println("You have incurred a penalty fee of £50 for returning this book late.");
                            }
                        }
                    }
                    break;
                case 5:
                    System.out.println("Enter customer ID: ");
                    int cID4 = input.nextInt();
                    input.nextLine();
                    Customer customer3 = libraryDatabase.customerQuery(cID4);
                    for (int i=0; i<customer3.getLoanedBooksList().size(); i++) {
                        Date date = customer3.getLoanedBooksList().get(i).getDate();
                        Date endDate = date.copy();
                        endDate.addDays(customer3.getLoanedBooksList().get(i).getLoanLength());
                        Book book = customer3.getLoanedBooksList().get(i).getBook();
                        System.out.println(Integer.toString(book.getID())+" - "+book.getTitle()+" - "+book.getAuthor()+" - Loan start: "+date.toString()+" - Loan end: "+endDate.toString());
                    }
                    break;
                default:
                    libraryDatabase.export();
                    gate = false;
                    break;
            }
        }
        input.close();
    }

}
