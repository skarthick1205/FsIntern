package com.example.pagination;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExcelGenerator {

    public void writeUsersToExcel(List<User> users, Workbook workbook, int startRow) {
        Sheet sheet = workbook.getSheet("Users");
        if (sheet == null) sheet = workbook.createSheet("Users");

        int rowIdx = startRow;
        for (User user : users) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(user.getId());
            row.createCell(1).setCellValue(user.getName());
            row.createCell(2).setCellValue(user.getEmail());
        }
    }
}
