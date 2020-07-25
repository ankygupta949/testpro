package App;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

/**
 * The type Date utils.
 */
public class MBSDateUtils extends org.apache.commons.lang.time.DateUtils {


    /**
     * The constant DATE_FORMAT.
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    /**
     * The constant DATE_PATTERN_FOR_REST_API.
     */
    public static final String DATE_PATTERN_FOR_REST_API = "yyyy-MM-dd HH:mm:ss";
    /**
     * The constant DATE_FORMAT_DD_MMM_YYYY.
     */
    public static final String DATE_FORMAT_DD_MMM_YYYY = "dd-MMM-yyyy";
    /**
     * The constant DATE_FORMAT_MM_DD_YYYY.
     */
    public static final String DATE_FORMAT_MM_DD_YYYY = "MM/dd/yyyy";
    /**
     * The constant DATE_FORMAT_DD_MMM_YYYY_HH_MM_SS.
     */
    public static final String DATE_FORMAT_DD_MMM_YYYY_HH_MM_SS = "dd-MMM-yyyy HH:mm:ss";
    /**
     * The constant DATE_FORMAT_YY_MM_DD.
     */
    public static final String DATE_FORMAT_YY_MM_DD = "yyMMdd";
    /**
     * The constant DATE_FORMAT_MMM_YYYY.
     */
    public static final String DATE_FORMAT_MMM_YYYY = "MMM yyyy";
    /**
     * The constant ACCEPTABLE_DATE_PATTERN_FOR_REST_API.
     */
    protected static final String[] ACCEPTABLE_DATE_PATTERN_FOR_REST_API = new String[]{DATE_PATTERN_FOR_REST_API};
    /**
     * The constant MRR_REPORT_DATE_FORMAT.
     */
    protected static final String[] MRR_REPORT_DATE_FORMAT = {
            "MMM yyyy",
            "MMM-yyyy",
            "MMM/yyyy",
            "yy-MMM",
            "dd-MM-yyyy", "dd/MM/yyyy",
            "dd-MMM-yyyy", "dd/MMM/yyyy"};
    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER = LogManager.getLogger(MBSDateUtils.class);
    /**
     * The constant defaultTimeZone.
     */
    private static TimeZone defaultTimeZone = null;

    static {
        defaultTimeZone = TimeZone.getTimeZone("EST");
    }

    /**
     * Gets default time zone.
     *
     * @return the default time zone
     */
    public static TimeZone getDefaultTimeZone() {
        return defaultTimeZone;
    }

    /**
     * Gets day of week in string.
     *
     * @param date the date
     * @return the day of week in string
     */
    public static String getDayOfWeekInString(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
    }

    /**
     * Parse date date.
     *
     * @param str           the str
     * @param parsePatterns the parse patterns
     * @return the date
     * @throws ParseException the parse exception
     */
    public static Date parseDate(String str, String[] parsePatterns)
    throws ParseException {
        return org.apache.commons.lang.time.DateUtils.parseDate(str, parsePatterns);
    }

    /**
     * Clear time date.
     *
     * @param date the date
     * @return the date
     */
    public static Date clearTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * Clear time by start day date.
     *
     * @param date the date
     * @return the date
     */
    public static Date clearTimeByStartDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * Gets end of day.
     *
     * @param date the date
     * @return the end of day
     */
    public static Date getEndOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * Gets years difference.
     *
     * @param date1 the date 1
     * @param date2 the date 2
     * @return the years difference
     */
    public static Integer getYearsDifference(Date date1, Date date2) {
        if (Objects.isNull(date1) || Objects.isNull(date2) || date2.getTime() < date1.getTime()) {
            return null;
        }
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(date1);
        calendar2.setTime(date2);
        return calendar2.get(Calendar.YEAR) - calendar1.get(Calendar.YEAR);
    }

    /**
     * Gets months difference.
     *
     * @param date1 the date 1
     * @param date2 the date 2
     * @return the months difference
     */
    public static Integer getMonthsDifference(Date date1, Date date2) {
        if (Objects.isNull(date1) || Objects.isNull(date2) || date2.getTime() < date1.getTime()) {
            return null;
        }
        Long timeDifference = date2.getTime() - date1.getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeDifference);
        return calendar.get(Calendar.MONTH);
    }

    /**
     * Gets start date of month.
     *
     * @param year  the year
     * @param month the month
     * @return the start date of month
     */
    public static Date getStartDateOfMonth(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * Gets end date of month.
     *
     * @param date the date
     * @return the end date of month
     */
    public static Date getEndDateOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int lastDate = calendar.getActualMaximum(Calendar.DATE);
        calendar.set(Calendar.DATE, lastDate);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * Gets start date of month.
     *
     * @param month the month
     * @return the start date of month
     */
    public static Date getStartDateOfMonth(Date month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(month);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * Gets month from date.
     *
     * @param date the date
     * @return the month from date
     */
    public static int getMonthFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH);
    }

    /**
     * Gets year from date.
     *
     * @param date the date
     * @return the year from date
     */
    public static int getYearFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * Gets formatted date.
     *
     * @param requiredFormat the required format
     * @param date           the date
     * @return the formatted date
     */
    public static String getFormattedDate(String requiredFormat, Date date) {
        SimpleDateFormat format = new SimpleDateFormat(requiredFormat);
        return format.format(date);
    }

    /**
     * Convert string format to date date.
     *
     * @param requiredFormat the required format
     * @param date           the date
     * @return the date
     */
    public static Date convertStringFormatToDate(String requiredFormat, String date) {
        SimpleDateFormat format = new SimpleDateFormat(requiredFormat);
        Date formattedDate = null;
        try {
            formattedDate = format.parse(date);
        } catch (ParseException e) {
            LOGGER.error("Exception occurs", e);
        }
        return formattedDate;
    }

    /**
     * Generate current date for specific day
     *
     * @param day day
     * @return date day of current date
     */
    public static Date getDayOfCurrentDate(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * Gets date by day.
     *
     * @param day  day
     * @param date date
     * @return date date by day
     */
    public static Date getDateByDay(int day, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * Gets start date of the day month.
     *
     * @param date     date
     * @param startDay startDay
     * @return date start date of the day month
     */
    public static Date getStartDateOfTheDayMonth(Date date, int startDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, startDay);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * Gets end date of the day month.
     *
     * @param date     date
     * @param startDay startDay
     * @param endDay   endDay
     * @return date end date of the day month
     */
    public static Date getEndDateOfTheDayMonth(Date date, int startDay, int endDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (startDay > endDay) {
            calendar.add(Calendar.MONTH, 1);
        }
        calendar.set(Calendar.DAY_OF_MONTH, endDay);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * Gets last day of the day month.
     *
     * @param date date
     * @return int last day of the day month
     */
    public static int getLastDayOfTheDayMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DATE);
    }

    /**
     * @param date  date
     * @param hours hours
     * @return date
     */
    public static Date increaseHours(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);

        return calendar.getTime();
    }

    /**
     * Increase date by day date.
     *
     * @param date the date
     * @param day  the day
     * @return the date
     */
    public static Date increaseDateByDay(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, day + calendar.get(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * @param date date
     * @return number of day in week
     */
    public static int getDayOfWeekByDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * @param date date
     * @return number of day in month
     */
    public static int getDayOfMonthByDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Convert string format to date date.
     *
     * @param requiredFormat the required format
     * @param date           the date
     * @return the date
     */
    public static String convertDateToStringFormat(String requiredFormat, Date date) {
        SimpleDateFormat format = new SimpleDateFormat(requiredFormat);
        return format.format(date);
    }

    /**
     * @param date1 date1
     * @param date2 date2
     * @return boolean
     */
    public static boolean isSameMonthAndYear(Date date1, Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
               && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH);
    }

    /**
     * @return date
     */
    public static Date fetchApprovePcrDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        if (dayOfWeek == 1) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        } else if (dayOfWeek == 7) {
            calendar.add(Calendar.DAY_OF_MONTH, 2);
        }
        return calendar.getTime();
    }

    /**
     * Generate current date for specific day
     *
     * @param day day
     * @return date day of current date
     */
    public static Date getDayOfNextMonth(int day,Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if(calendar.get(Calendar.DAY_OF_MONTH) > 4){
            calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH)+1);
        }
        calendar.set(Calendar.DAY_OF_MONTH, day);

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

}
