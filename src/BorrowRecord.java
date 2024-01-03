public class BorrowRecord extends Status {
    Customer customer;
    Book book;
    Date date;
    public BorrowRecord() {
        super("Borrow Record");
    }
    public void writeRecord(Customer customer, Book book, Date date) {
        this.customer = customer;
        this.book = book;
        this.date = date;
    }
    public String toString() {
        return book.toString()+"\n"+customer.toString()+"\n"+date.toString();
    }
}