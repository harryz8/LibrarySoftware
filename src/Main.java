import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        boolean gate = true;
        Database libraryDatabase = new Database("ColwichLibraryDatabase");
        Scanner input = new Scanner(System.in);
        while (gate) {
            System.out.print("Choose an option: \n 1. Add a book to the library\n 2. Add a customer to the library\n 3. Process a book loan\n 4. Process a book return\n 5. Output Database to File\n 6. Exit\nChoose an option by typing its number: ");
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
                    System.out.println("Enter loan date's day number: ");
                    int day2 = input.nextInt();
                    input.nextLine();
                    System.out.println("Enter loan date's month number: ");
                    int month2 = input.nextInt();
                    input.nextLine();
                    System.out.println("Enter loan date's year: ");
                    int year2 = input.nextInt();
                    input.nextLine();
                    System.out.println("Enter bookcase ID: ");
                    int bkcaseID2 = input.nextInt();
                    input.nextLine();
                    System.out.println("Enter shelf ID: ");
                    int shlfID2 = input.nextInt();
                    input.nextLine();
                    BorrowRecord bookRecord2 = libraryDatabase.bookLoanTableQuery(bkID2, new Date(day2, month2, year2));
                    Customer customer2 = libraryDatabase.customerQuery(cID3);
                    if (bookRecord2 != null && customer2 != null) {
                        libraryDatabase.returnBook(customer2, bookRecord2, bkcaseID2, shlfID2);
                    }
                    break;
                case 5:
                    libraryDatabase.export();
                    break;
                default:
                    gate = false;
                    break;
            }
        }
        input.close();
    }

}
