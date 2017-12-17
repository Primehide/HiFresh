package be.kdg.hiFresh.domain;

public class Week {
    private int year;
    private int intWeek;

    public Week(int year, int intWeek){
        this.year = year;
        this.intWeek = intWeek;
    }

    public int getYear() {
        return year;
    }

    public int getIntWeek() {
        return intWeek;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setIntWeek(int intWeek) {
        this.intWeek = intWeek;
    }
}
