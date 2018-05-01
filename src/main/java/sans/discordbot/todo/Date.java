package sans.discordbot.todo;

public class Date {
    
    private final int month;
    private final int day;
    private final int year; 
    private final int hour;
    private final int minute; 
    
    public Date (int month, int day, int year, int hour, int minute) {
        this.month = month;
        this.day = day;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
    }
    
    public Date (String date) throws IndexOutOfBoundsException { // 11/2/2017 23:59
        this.month  = Integer.parseInt(date.substring(0                      , date.indexOf    ("/")).trim());
        this.day    = Integer.parseInt(date.substring(date.indexOf    ("/")+1, date.lastIndexOf("/")).trim());
        this.year   = Integer.parseInt(date.substring(date.lastIndexOf("/")+1, date.indexOf    (" ")).trim());
        this.hour   = Integer.parseInt(date.substring(date.indexOf    (" ")+1, date.indexOf    (":")).trim());
        this.minute = Integer.parseInt(date.substring(date.indexOf    (":")+1, date.length()        ).trim());
    }
    
    public String toDisplayString() {
        return String.format("%02d", this.getMonth()) + "/"
             + String.format("%02d", this.getDay()) /*+ "/"
             + String.format("%04d", this.getYear())*/+ " "
             + String.format("%02d", this.getHour())  + ":"
             + String.format("%02d", this.getMinute());
    }
    
    @Override
    public String toString() {
        return String.format("%02d", this.getMonth()) + "/"
             + String.format("%02d", this.getDay())   + "/"
             + String.format("%04d", this.getYear())  + " "
             + String.format("%02d", this.getHour())  + ":"
             + String.format("%02d", this.getMinute());
    }
    
    public int getMonth() {
        return this.month;
    }
    
    public int getDay() {
        return this.day;
    }
    
    public int getYear() {
        return this.year; 
    }
    
    public int getHour() {
        return this.hour;
    }
    
    public int getMinute() {
        return this.minute;
    }
    
    public boolean isBefore(Date date) {
        boolean b = false;
        if (this.year < date.getYear()) b = true;
        else if (this.year == date.getYear()) {
            if (this.month < date.getMonth()) b = true;
            else if (this.month == date.getMonth()) {
                if (this.day < date.getDay()) b = true;
                else if (this.day == date.getDay()) {
                    if (this.hour < date.getHour()) b = true;
                    else if (this.hour == date.getHour()) {
                        if (this.minute < date.getMinute()) b = true;
                    }
                }
            }
        }
        return b;
    }
    
    
}
