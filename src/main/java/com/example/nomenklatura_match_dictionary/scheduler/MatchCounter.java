package com.example.nomenklatura_match_dictionary.scheduler;

import com.example.nomenklatura_match_dictionary.model.Match;
import com.example.nomenklatura_match_dictionary.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@EnableScheduling
@RequiredArgsConstructor
public class MatchCounter {

    @Value("${supplier.folder}")
    private String supplierFolder;

    private final MatchRepository matchRepository;

    @Scheduled(fixedRate = 1_800_000)
    private void countMatches() {
        Map<String, Integer> successfulMatches = new HashMap<>();
        try {
            List<File> files = Files.list(Paths.get(supplierFolder))
                    .map(Path::toFile)
                    .filter(File::isFile)
                    .toList();

            for (File file : files) {
//                try(FileInputStream fileInputStream = new FileInputStream(file) {
//                    Workbook workbook = new XSSFWorkbook(fileInputStream)) {
//
//                    Sheet sheet = workbook.getSheetAt(0);
//                    int rowNumber = 1;
//
//                    for (ApartmentEntity apart : apartments) {
//                        Row row = sheet.createRow(rowNumber++);
//                        row.createCell(0).setCellValue(getFullAddress(apart));
//                        row.createCell(1).setCellValue(apart.getPricePerDay());
//                        row.createCell(2).setCellValue(LocalDateTime.now());
//                    }
//
//                    FileOutputStream fileOutputStream = new FileOutputStream(file);
//                    workbook.write(fileOutputStream);
//                    fileOutputStream.flush();
//                    fileOutputStream.close();
//                } catch (FileNotFoundException e) {
//                    throw new RuntimeException("Файл для выгрузки отчета не найден");
//                } catch (IOException e) {
//                    throw new RuntimeException("Проблема с выгрузкой отчета");
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
