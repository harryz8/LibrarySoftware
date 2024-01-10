public class Date {
    int day;
    int month;
    int year;
    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }
    @Override
    public String toString(){
        return day+"/"+month+"/"+year;
    }
    public Date copy() {
        return new Date(day, month, year);
    }
    public void addDays(int numOfDays) {
        int daysInFebruary = 28;
        if (year%4 == 0 && (year%400==0 || year%100!=0)) {
            daysInFebruary = 29;
        }
        int newDay = day+numOfDays;
        int daysInCurrentMonth;
        if (month == 2) {
            daysInCurrentMonth = daysInFebruary;
        } else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            daysInCurrentMonth = 31;
        }
        else {
            daysInCurrentMonth = 30;
        }
        while (newDay>daysInCurrentMonth) {
            newDay = newDay - daysInCurrentMonth;
            month++;
            if (month > 12){
                year++;
                month=1;
            }
            if (month == 2) {
                daysInCurrentMonth = daysInFebruary;
            } else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                daysInCurrentMonth = 31;
            }
            else {
                daysInCurrentMonth = 30;
            }
        }
        day = newDay;
    }
    @Override
    public boolean equals(Object date) {
        boolean result;
        if (date == this) {
            result = true;
        }
        else if (date == null) {
            result = false;
        }
        else if (date.getClass() != this.getClass()) {
            result = false;
        }
        Date date2 = (Date) date;
        if (date2.toString().equals(this.toString())) {
            result = true;
        }
        else {
            result = false;
        }
        return result;
    }
}
