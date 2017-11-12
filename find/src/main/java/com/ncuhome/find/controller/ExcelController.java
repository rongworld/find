package com.ncuhome.find.controller;

import com.ncuhome.find.annotation.LoginOnly;
import com.ncuhome.find.service.ExcelService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ExcelController {
    @GetMapping(value = "/download")
    @LoginOnly
    public void down(@RequestParam(value = "dateStart", required = false, defaultValue = "0") String datestart, @RequestParam(value = "dateEnd", required = false, defaultValue = "0") String dateEnd, HttpServletResponse response)
            throws IOException, ServletException {
        HSSFWorkbook hssfWorkbook = new ExcelService().createBook(Long.parseLong(datestart), Long.parseLong(dateEnd));
        if (hssfWorkbook.getSheet("失物信息表").getLastRowNum() > 1) {
            response.setHeader("content-Type", "application/vnd.ms-excel");
            // 下载文件的默认名称
            response.setHeader("Content-Disposition", "attachment;filename=Lost.xls");

            hssfWorkbook.write(response.getOutputStream());
        } else {
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("没有查到记录！");
        }

    }
}
