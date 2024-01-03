public abstract class Status {
    String type;
    public Status(String type) {
        this.type = type;
    }
    public abstract void setStatus();
    public abstract String getStatus();
}
