import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter
{
    private static final String dateFormat = "dd/MM/yyyy";

    public static Date toDate(String string) throws ParseException
    {
        DateFormat format = new SimpleDateFormat(dateFormat);
        try
        {
            Date date = format.parse(string);
            return date;
        } catch (ParseException e)
        {
            return null;
        }
    }

    public static String toString(Date date)
    {
        DateFormat format = new SimpleDateFormat(dateFormat);
        String str = format.format(date);
        return str;
    }


}
