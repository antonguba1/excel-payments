import java.util.Date;
import java.util.List;

public class ReportGenerator
{
    public static void createReport()
    {
        Date date = new Date();
        System.out.println(String.format("=====Report %s=====", DateConverter.toString(date)));


        for (User user : ExcelReader.getUserList())
        {
            System.out.println("***************************");
            System.out.println(user.getName());
            Date beginningDate = user.getPaymentList().get(0).getDueDate();
            System.out.println("Date of the beginning of the course: " + DateConverter.toString(beginningDate));
            for (int i = 0; i < user.getPaymentList().size(); i++)
            {
                Payment p = user.getPaymentList().get(i);

                if (p.getDueDate().compareTo(date) <= 0)
                {
                    if (DateConverter.toString(p.getActualDate()) == "")
                        System.out.println(String.format("Payment %s not done ", i + 1));
                    else
                        System.out.println(String.format("Payment %s done ", i + 1)+
                                DateConverter.toString(p.getActualDate()));
                }


            }
            System.out.println("***************************");
        }
    }
}
