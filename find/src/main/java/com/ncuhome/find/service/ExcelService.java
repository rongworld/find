package com.ncuhome.find.service;


import com.ncuhome.find.respository.Lost;
import com.ncuhome.find.respository.LostRepository;
import com.ncuhome.find.respository.LostStaticRepository;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;


@Service
public class ExcelService {
    private LostRepository lostRepository = LostStaticRepository.lostRepository;
    public HSSFWorkbook createBook(Long dateStart,Long dateEnd){
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFCellStyle style = hssfWorkbook.createCellStyle();
        HSSFFont font = hssfWorkbook.createFont();
        font.setBold(true);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setFont(font);
        HSSFSheet sheet = hssfWorkbook.createSheet("失物信息表");
        sheet.setColumnWidth((short)3,(short)4000);
        sheet.setColumnWidth((short)4,(short)4500);
        sheet.setColumnWidth((short)5, (short)4500);
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell;
        cell = row.createCell(0);
        cell.setCellValue("序号");
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue("姓名");
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue("卡类型");
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue("卡号");
        cell.setCellStyle(style);
        cell = row.createCell(4);
        cell.setCellValue("添加时间");
        cell.setCellStyle(style);
        cell = row.createCell(5);
        cell.setCellValue("认领时间");
        cell.setCellStyle(style);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        List<Lost> lostList =  lostRepository.findByDateBetween(dateStart,dateEnd);
        if(lostList == null){
            return  null;
        }
        int size = lostList.size();
        for(int i=1;i<=size;i++){
            HSSFRow hssfRow = sheet.createRow(i);
            for(int j=0;j<6;j++){
                cell = hssfRow.createCell(j);
                switch (j){
                    case 0:
                        cell.setCellValue(i);
                        break;
                    case 1:
                        cell.setCellValue((lostList.get(i-1)).getName());
                        break;
                    case 2:
                        switch ((lostList.get(i-1)).getCardType()){
                            case "0":
                                cell.setCellValue("校园卡");
                                break;
                            case "1":
                                cell.setCellValue("身份证");
                                break;
                            case "2":
                                cell.setCellValue("建行卡");
                                break;
                        }
                        break;
                    case 3:
                        cell.setCellValue((lostList.get(i-1)).getCardNumber());
                        break;
                    case 4:
                        cell.setCellValue(simpleDateFormat.format((lostList.get(i-1)).getDate()));
                        break;
                    case 5:
                        if(lostList.get(i-1).getClaimDate() == null){
                            cell.setCellValue("未认领");
                        }else{
                            cell.setCellValue(simpleDateFormat.format(lostList.get(i-1).getClaimDate()));
                        }
                        break;
                }
            }
        }
        return hssfWorkbook;
    }
}
