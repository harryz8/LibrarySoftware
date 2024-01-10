import java.lang.Math;
public class Date implements Comparable<Date> {
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
        int daysInCurrentMonth = getDaysInMonth(month, year);
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
    public int getDaysInMonth(int curMonth, int curYear) {
        int daysInFebruary = 28;
        if (curYear%4 == 0 && (curYear%400==0 || curYear%100!=0)) {
            daysInFebruary = 29;
        }
        int daysInCurrentMonth;
        if (curMonth == 2) {
            daysInCurrentMonth = daysInFebruary;
        } else if (curMonth == 1 || curMonth == 3 || curMonth == 5 || curMonth == 7 || curMonth == 8 || curMonth == 10 || curMonth == 12) {
            daysInCurrentMonth = 31;
        }
        else {
            daysInCurrentMonth = 30;
        }
        return daysInCurrentMonth;
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

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int compareTo(Date otherDate) {
        int difference = 0;
        int otherDay = otherDate.getDay();
        int otherMonth = otherDate.getMonth();
        int otherYear = otherDate.getYear();
        int thisDay = this.getDay();
        int thisMonth = this.getMonth();
        int thisYear = this.getYear();
        while (otherYear > thisYear) {
            otherMonth += 12;
            otherYear--;
        }
        while (otherYear < thisYear) {
            thisMonth += 12;
            thisYear--;
        }
        while (otherMonth > thisMonth) {
            int curMonth = otherMonth / 12;
            if (curMonth == 0) {
                curMonth = 12;
            }
            otherDay += getDaysInMonth(curMonth, otherYear);
            otherMonth--;
        }
        while (otherMonth < thisMonth) {
            int curMonth = thisMonth / 12;
            if (curMonth == 0) {
                curMonth = 12;
            }
            thisDay += getDaysInMonth(curMonth, thisYear);
            thisMonth--;
        }
        difference = Math.max(thisDay, otherDay) - Math.min(thisDay, otherDay);
        return difference;
    }
}
