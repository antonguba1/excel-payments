
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class AA {
    public static void main(String[] args) throws Exception {

        FileInputStream file = new FileInputStream(new File("C:\\Users\\Jakub\\Desktop/Report.xlsx"));

        //Create Workbook instance holding reference to .xlsx file
        XSSFWorkbook workbook = new XSSFWorkbook(file);

        //Get first/desired sheet from the workbook
        XSSFSheet sheet = workbook.getSheetAt(0);

        FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
        Row header = sheet.getRow(0);
        checkName(header);


        for (int i=1; i< header.getLastCellNum();i++) {
            checkPayment(header,i);
        }


        for (Row row : sheet) {
            for (Cell cell : row) {
                switch (cell.getCellTypeEnum()) {
                    case NUMERIC:
                        System.out.print(cell.getNumericCellValue() + "(Integer)\t");
                        break;
                    case STRING:
                        System.out.print(cell.getStringCellValue() + "(String)\t");
                        break;
                }
            }
            System.out.println();

        }

    }

    private static void checkName(Row header) throws Exception {
        if (!header.getCell(0).getStringCellValue().toLowerCase().trim().equals("name")) {
            throw new Exception("First header must be name!");
        }
    }

    private static void checkPayment(Row header, int colNumber) throws Exception {
        String cell = header.getCell(colNumber).getStringCellValue().toLowerCase().trim();
        String[] cellValues = cell.split(" ");
        if (cellValues.length != 3)
            throw new Exception("Header must be in format payment # due/done");

        if (colNumber % 2 == 1) {
            if (!cellValues[2].equals("due"))
                throw new Exception("Header must be in format payment # due");
        }
        else {
            if (!cellValues[2].equals("done"))
                throw new Exception("Header must be in format payment # done");
        }
    }

}