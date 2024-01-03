public abstract class Status {
    String type;
    public Status(String type) {
        this.type = type;
    }
    @Override
    public abstract String toString();
    public String getType() {
        return type;
    }
}
