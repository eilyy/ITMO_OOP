package system.databases;

import java.util.Calendar;
import java.util.Stack;

public class TimeDB {
    private static TimeDB instance;
    private static Stack<Long> timeStack = new Stack<>();

    private static long dateNow = -1;
    private static Calendar date = Calendar.getInstance();

    public static TimeDB getInstance() {
        if(instance == null)
            instance = new TimeDB();
        return instance;
    }

    public void nextDay() {
        date.add(Calendar.DATE, 1);
        dateNow = (long)date.get(Calendar.YEAR) * 100000000 + (long)(date.get(Calendar.MONTH) + 1) * 1000000 + (long)date.get(Calendar.DAY_OF_MONTH) * 10000;
    }

    public void setDateNow(int year, int month, int day) {
        dateNow = (long)year * 100000000 + (long)month * 1000000 + (long)day * 10000;
        month--;
        date.set(year, month, day);
    }

    public int getDateNow() throws Exception {
        if(dateNow == -1)
            throw new Exception("Set date first");
        return date.get(Calendar.YEAR) * 10000 + (date.get(Calendar.MONTH) + 1) * 100 + date.get(Calendar.DAY_OF_MONTH);
    }

    public Calendar getDate() {
        return date;
    }

    public long newTime(int hour, int min) throws Exception {
        if(dateNow == -1)
            throw new Exception("Set date first");
        long time = dateNow + (long)hour * 100 + (long)min;
        if(!timeStack.isEmpty() && time < timeStack.firstElement())
            throw new Exception("Time cannot go back");
        timeStack.push(time);
        return time;
    }

    public long makeTimeStamp(int year, int month, int day, int hour, int min) {
        return (long)year * 100000000 + (long)month * 1000000 + (long)day * 10000 + (long)hour * 100 + (long)min;
    }
}
