export class DateHelper {

    readonly fullName: String;
    readonly abbreviatedName: String;
    readonly firstLetter: String;

    private constructor(fullName: String, abbreviatedName: String) {
        this.fullName = fullName;
        this.abbreviatedName = abbreviatedName;
        this.firstLetter = fullName[0];
    }

    static readonly WEEKDAYS = new Array(new DateHelper("Sunday", "Sun"), new DateHelper("Monday", "Mon"), new DateHelper("Tuesday", "Tue"),
        new DateHelper("Wednesday", "Wed"), new DateHelper("Thursday", "Thur"), new DateHelper("Friday", "Fri"), new DateHelper("Saturday", "Sat"));

    static readonly MONTHS = new Array(new DateHelper("January", "Jan"), new DateHelper("Feburary", "Feb"), new DateHelper("March", "Mar"), new DateHelper("April", "Apr"), new DateHelper("May", "May"),
        new DateHelper("June", "June"), new DateHelper("July", "July"), new DateHelper("August", "Aug"), new DateHelper("September", "Sep"), new DateHelper("October", "Oct"),
        new DateHelper("November", "Nov"), new DateHelper("December", "Dec"))

    static setDateToMidnight(date: Date) {
        date.setMilliseconds(0);
        date.setMinutes(0);
        date.setHours(0);
        date.setSeconds(0);
        return date;
    }

    static convertToUTC(date: Date) {
        return date.setTime(date.getTime() + (date.getTimezoneOffset() * 60 * 1000));
    }

    static buildDatesBetweenDates(date1: Date, date2: Date) {
        var timeBetweenDates = Math.abs(date1.getTime() - date2.getTime());
        var daysBetweenDates = Math.floor(timeBetweenDates / (24 * 3600 * 1000))
        var dates = new Array();
        var date = new Date(date1);
        for (var i = 0; i < daysBetweenDates + 1; i++) {
            dates.push(new Date(date.getFullYear(), date.getMonth(), date.getDate()))
            date.setHours(24);
        }
        return dates;
    }

    static findSundayInWeekByDate(date) {
        var day = new Date(date.getTime());
        var dayNum = day.getDay();

        if (dayNum !== 0) {
            day.setHours(-24 * dayNum);
        }
        return day;
    }

    static buildWeekFromDate(day: Date) {
        var date = new Date(day);
        var week = new Array();
        for (var i = 0; i < 7; i++) {
            week.push(new Date(date.getFullYear(), date.getMonth(), date.getDate()));
            date.setHours(24);
        }
        return week;
    }

}
