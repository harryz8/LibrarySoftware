public class BorrowRecord extends Status {
    Customer customer;
    Book book;
    Date date;
    int loanLength;
    public BorrowRecord(Customer customer, Book book, Date date, int loanLength) {
        super("Borrow Record");
        this.customer = customer;
        this.book = book;
        this.date = date;
        this.loanLength = loanLength;
    }
    public Date getLoanEndDate() {
        Date endDate = date.copy();
        endDate.addDays(loanLength);
        return endDate;
    }
    public String toString() {
        return book.toString()+"\n"+customer.toString()+"\n"+date.toString();
    }
}