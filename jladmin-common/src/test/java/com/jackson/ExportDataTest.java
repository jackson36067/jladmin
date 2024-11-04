package com.jackson;

import com.jackson.entity.Student;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ExportDataTest {
    public static void main(String[] args) throws IOException {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("姓名");
        strings.add("年龄");
        // 创建excel文件
        XSSFWorkbook excel = new XSSFWorkbook();
        // 创建sheet1页面
        XSSFSheet sheet = excel.createSheet("sheet1");
        // 创建行, 0表示第一行
        XSSFRow row1 = sheet.createRow(0);
        int descriptionIndex = 0;
        for (String dataDescription : strings) {
            // 把字段描述写上
            row1.createCell(descriptionIndex).setCellValue(dataDescription);
            descriptionIndex++;
        }
        ArrayList<Student> student = new ArrayList<>();
        Student jackson = new Student("jackson", "18");
        Student lt = new Student("lt", "18");
        XSSFRow row2 = sheet.createRow(1);
        row2.createCell(0).setCellValue(jackson.getName());
        row2.createCell(1).setCellValue(jackson.getAge());
        XSSFRow row3 = sheet.createRow(2);
        row3.createCell(0).setCellValue(lt.getName());
        row3.createCell(1).setCellValue(lt.getAge());
        FileOutputStream fos = new FileOutputStream(new File("D:\\userInfo.xlsx"));
        excel.write(fos);
        fos.close();
        excel.close();
    }
}
