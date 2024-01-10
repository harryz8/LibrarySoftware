package com.library;
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
        return this.getType()+"¦"+this.getBook().toString()+"¦"+this.getCustomer().toString()+"¦"+this.getDate().toString();
    }

    public Customer getCustomer() {
        return customer;
    }

    public Date getDate() {
        return date;
    }

    public Book getBook() {
        return book;
    }

    public int getLoanLength() {
        return loanLength;
    }

    @Override
    public boolean equals(Object record) {
        boolean result;
        if (record == this) {
            result = true;
        }
        else if (record == null) {
            result = false;
        }
        else if (record.getClass() != this.getClass()) {
            result = false;
        }
        BorrowRecord record2 = (BorrowRecord) record;
        if (record2.getBook().equals(this.getBook()) && record2.getCustomer().equals(this.getCustomer()) && record2.getDate().equals(this.getDate()) && record2.getLoanLength() == this.getLoanLength()) {
            result = true;
        }
        else {
            result = false;
        }
        return result;
    }
}