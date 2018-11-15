import java.util.Date;
import java.util.List;

public class ReportGenerator
{
    public static void createReport(List<User> users)
    {
        System.out.println(String.format("=====Report %s=====", DateConverter.toString(new Date())));
    }
}
