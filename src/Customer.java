public class Customer {
    String firstName;
    String surname;
    int ID;
    public Customer(String firstName, String surname, int IDNum) {
        this.firstName = firstName;
        this.surname = surname;
        this.ID = IDNum;
    }
    @Override
    public String toString(){
        return Integer.toString(ID)+","+firstName+","+surname;
    }
}
