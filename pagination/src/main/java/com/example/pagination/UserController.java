package com.example.pagination;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired private ExcelGenerator excelGenerator;

    @GetMapping("/download")
    public void downloadAllUsers(HttpServletResponse response) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        int page = 0, size = 2;
        int startRow = 0;

        long total = userService.getTotalCount();
        int totalPages = (int) Math.ceil((double) total / size);

        while (page < totalPages) {
            List<User> users = userService.getUsers(page, size).getContent();
            excelGenerator.writeUsersToExcel(users, workbook, startRow);
            startRow += users.size();
            page++;
        }

        // Set headers for download
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=users.xlsx");

        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
