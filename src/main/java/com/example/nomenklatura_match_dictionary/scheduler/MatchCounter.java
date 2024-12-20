package com.example.nomenklatura_match_dictionary.scheduler;

import com.example.nomenklatura_match_dictionary.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import static java.util.Objects.isNull;

@Service
@EnableScheduling
@RequiredArgsConstructor
public class MatchCounter {

    @Value("${supplier.folder}")
    private String supplierFolder;
    private final MatchRepository matchRepository;
    private final JdbcTemplate jdbcTemplate;

    @Scheduled(fixedRate = 10_800_000)
    private void countMatches() {
        List<File> files = null;
        try {
            files = Files.list(Paths.get(supplierFolder))
                    .map(Path::toFile)
                    .filter(File::isFile)
                    .filter(file -> file.getName().endsWith(".xlsx"))
                    .toList();

            for (File file : files) {
                try (FileInputStream fileInputStream = new FileInputStream(file.getAbsoluteFile());
                     Workbook workbook = new XSSFWorkbook(fileInputStream)) {

                    Sheet sheet = workbook.getSheetAt(0);
                    Iterator<Row> rowIterator = sheet.iterator();
                    int counter = 0;

                    while (rowIterator.hasNext()) {
                        Row row = rowIterator.next();
                        Cell cell = row.getCell(0);
                        if (!isNull(matchRepository.findByRowName(cell.getStringCellValue()))) {
                            counter++;
                        }
                    }
                    System.out.println("Processed " + counter + " positions in " + file.getName() + ".");
                    String query = "insert into counter (file_name, counter) values (?, ?)";
                    jdbcTemplate.update(query, file.getName(), counter);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
