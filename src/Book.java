public class Book {
    int ID;
    String title;
    String author;
    Status status;
    public Book(int ID, String title, String author, Status status) {
        //Book ID must be unique
        this.ID = ID;
        this.title = title;
        this.author = author;
        this.status = status;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getID() {
        return ID;
    }

    public Status getStatus() {
        return status;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }
    @Override
    public String toString() {
        return Integer.toString(ID)+" : "+getTitle()+" "+getAuthor();
    }
}
