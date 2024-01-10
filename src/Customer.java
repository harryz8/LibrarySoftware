import java.util.ArrayList;

public class Customer {
    String firstName;
    String surname;
    int ID;
    ArrayList<BorrowRecord> loanedBooks = new ArrayList<>();
    public Customer(String firstName, String surname, int IDNum) {
        this.firstName = firstName;
        this.surname = surname;
        this.ID = IDNum;
    }
    @Override
    public String toString(){
        return Integer.toString(ID)+","+firstName+","+surname;
    }
    public void addLoanedBook(BorrowRecord record) {
        loanedBooks.add(record);
    }
    public Book removeLoanedBook(BorrowRecord oldRecord, LibraryRecord newRecord) {
        Book book;
        for (int i = 0; i<loanedBooks.size(); i++) {
            if (loanedBooks.get(i).equals(oldRecord)) {
                book = loanedBooks.get(i).getBook();
                book.setStatus(newRecord);
                loanedBooks.remove(i);
                return book;
            }
        }
        return null;
    }
    public int getID() {
        return ID;
    }
    @Override
    public boolean equals(Object customer) {
        boolean result;
        if (customer == this) {
            result = true;
        }
        else if (customer == null) {
            result = false;
        }
        else if (customer.getClass() != this.getClass()) {
            result = false;
        }
        Customer customer2 = (Customer) customer;
        if (customer2.getID() == this.getID() && customer2.firstName.equals(this.firstName) && customer2.surname.equals(this.surname)) {
            result = true;
        }
        else {
            result = false;
        }
        return result;
    }
}
