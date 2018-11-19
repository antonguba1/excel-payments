


import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelReader
{
    private static List<User> userList = new ArrayList<>();


    public static void read(String path) throws Exception
    {
        FileInputStream file = new FileInputStream(new File(path));

        //Create Workbook instance holding reference to .xlsx file
        XSSFWorkbook workbook = new XSSFWorkbook(file);

        //Get first/desired sheet from the workbook
        XSSFSheet sheet = workbook.getSheetAt(0);
        FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
        Row header = sheet.getRow(0);
        checkName(header);

        for (int i = 1; i < header.getLastCellNum(); i++)
        {
            checkPayment(header, i);
        }


        for (int i = 1; i <= sheet.getLastRowNum(); i++)
        {
            Row row = sheet.getRow(i);

            User user = new User();
            user.setName(row.getCell(0).getStringCellValue());



            for (int j = 1; j <+ row.getLastCellNum(); j += 2)
            {
                Payment payment = new Payment();
                String due = row.getCell(j).getStringCellValue();
                String done = row.getCell(j + 1).getStringCellValue();
                payment.setDueDate(DateConverter.toDate(due));
                payment.setActualDate(DateConverter.toDate(done));
                user.addPayment(payment);
            }

            userList.add(user);
        }


    }


    private static void checkName(Row header) throws Exception
    {
        if (!header.getCell(0).getStringCellValue().toLowerCase().trim().equals("name"))
        {
            throw new Exception("First header must be name!");
        }
    }

    private static void checkPayment(Row header, int colNumber) throws Exception
    {
        String cell = header.getCell(colNumber).getStringCellValue().toLowerCase().trim();
        String[] cellValues = cell.split(" ");
        if (cellValues.length != 3)
            throw new Exception("Header must be in format payment # due/done");

        if (colNumber % 2 == 1)
        {
            if (!cellValues[2].equals("due"))
                throw new Exception("Header must be in format payment # due");
        } else
        {
            if (!cellValues[2].equals("done"))
                throw new Exception("Header must be in format payment # done");
        }
    }

    public static List<User> getUserList()
    {
        return userList;
    }
}