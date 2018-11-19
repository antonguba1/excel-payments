import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelWriter
{

    public static void createExcelFile() throws IOException, ParseException
    {
        String[] columns = new String[ConsoleReader.getNumberOfMonths() * 2 + 1];
        columns[0] = "Name";

        for (int i = 1, j = 1; i < 2 * ConsoleReader.getNumberOfMonths(); i += 2, j++)
        {
            columns[i] = "Payment " + j + " due";
            columns[i + 1] = "Payment " + j + " done";
        }
        for (String s : columns)
            System.out.println(s);

        Workbook workbook = new XSSFWorkbook();
        CreationHelper createHelper = workbook.getCreationHelper();
        Sheet sheet = workbook.createSheet("Members");


        // Create a F   ont for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());

        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Create a Row
        Row headerRow = sheet.createRow(0);

        // Create cells
        for (int i = 0; i < columns.length; i++)
        {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        // Create Cell Style for formatting Date
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

        // Create Other rows and cells with employees data
        int rowNum = 1;
        for (User user : ConsoleReader.getUserList())
        {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0)
                    .setCellValue(user.getName());

            for (int i = 1, j = 0; i < 2 * ConsoleReader.getNumberOfMonths(); i += 2, j++)
            {
                row.createCell(i)
                        .setCellValue(DateConverter.toString(user.getPaymentList().get(j).getDueDate()));

                row.createCell(i + 1)
                        .setCellValue(dataForTest().get(j));
            }

        }

        // Resize all columns to fit the content size
        for (int i = 0; i < columns.length; i++)
        {
            sheet.autoSizeColumn(i);
        }

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("D:\\Projects\\new.xlsx");
        workbook.write(fileOut);
        fileOut.close();

        // Closing the workbook
        workbook.close();

    }

    public static List<String> dataForTest() throws ParseException
    {
        List<String> list = new ArrayList<>();
        list.add("23/08/2018");
        list.add("23/09/2018");
        list.add("23/10/2018");
        for (int i = 0; i < 2 * ConsoleReader.getNumberOfMonths(); i++)
            list.add("");

        return list;
    }

}
