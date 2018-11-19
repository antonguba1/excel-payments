import java.text.ParseException;
import java.util.*;

public class ConsoleReader
{
    private static Scanner scanner = new Scanner(System.in);
    private static int numberOfMonths;
    private static List<User> userList = new ArrayList<>();
    private static String path = "D:\\Projects\\new.xlsx";

    public static void run() throws Exception
    {
        System.out.println("Choose 1 - Create a file for new group\n Choose 2 - Generate report");
        int option = scanner.nextInt();
        switch (option)
        {
            case 1:
            {
                readUsersFromConsole();
                ExcelWriter.createExcelFile();
                break;
            }
            case 2:
                ExcelReader.read(path);
                ReportGenerator.createReport();
                break;
            default:
                System.out.println("Unknown option");
        }
    }

    public static List<User> readUsersFromConsole() throws ParseException
    {
        System.out.println("How many people is assigned to this group?: ");
        int numberOfMembers = scanner.nextInt();
        String name, dueDate;
        System.out.println("How long the course takes?:");
        numberOfMonths = scanner.nextInt();

        for (int i = 0; i < numberOfMembers; i++)
        {
            scanner.nextLine();
            System.out.println("User's fullname: ");
            name = scanner.nextLine();
            System.out.println("User's dueDate: ");
            dueDate = scanner.nextLine();
            Date dueDate1 = DateConverter.toDate(dueDate);

            System.out.println("in name is" + name);
            List<Payment> paymentList = new ArrayList<>();
            paymentList.add(new Payment(dueDate1, null));

            for (int j = 1; j < numberOfMonths; j++)
            {
                dueDate1 = addMonths(dueDate1, 1);
                paymentList.add(new Payment(dueDate1, null));

            }
            for (Payment payment : paymentList)
                System.out.println(payment.getDueDate());

            userList.add(new User(name, paymentList));
        }

        return userList;
    }


    public static Date addMonths(Date date, int numberOfMonths)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MONTH, (cal.get(Calendar.MONTH) + numberOfMonths));
        Date date2 = cal.getTime();
        return date2;
    }

    public static int getNumberOfMonths()
    {
        return numberOfMonths;
    }

    public static List<User> getUserList()
    {
        return userList;
    }
}
