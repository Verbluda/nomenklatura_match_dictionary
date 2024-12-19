package com.example.nomenklatura_match_dictionary.scheduler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@EnableScheduling
public class DumpScheduler {

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Value("${spring.datasource.username}")
    private String dbUser;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Value("${backup.folder}")
    private String backupFolder;

    @Value("${local.backup.folder}")
    private String localBackupFolder;


    @Scheduled(fixedRate = 1_800_000)
    private void getDBDump() {
        try {

            String containerName = getContainerNameByPort(getDatabasePortFromUrl(datasourceUrl));
            if (containerName == null) {
                throw new RuntimeException("Container not found for port: " + getDatabasePortFromUrl(datasourceUrl));
            }

            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String backupFileName = "backup_" + timestamp + ".sql";
            String containerBackupPath = backupFolder + backupFileName;
            String localBackupPath = localBackupFolder + backupFileName;

            File localBackupDir = new File(localBackupFolder);
            if (!localBackupDir.exists()) {
                localBackupDir.mkdirs();
            }

            String dumpCommand = String.format(
                    "docker exec -e PGPASSWORD=%s %s pg_dump -U %s -F c -b -v -f %s %s",
                    dbPassword, containerName, dbUser, containerBackupPath, getDatabaseNameFromUrl(datasourceUrl));

            executeCommand(dumpCommand);

            String copyCommand = String.format(
                    "docker cp %s:%s %s",
                    containerName, containerBackupPath, localBackupPath);

            executeCommand(copyCommand);

            System.out.println("Backup completed successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getContainerNameByPort(int port) throws Exception {
        String command = String.format("docker ps --filter \"publish=%d\" --format \"{{.Names}}\"", port);

        ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", command);
        Process process = processBuilder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String containerName = reader.readLine();

        // Ожидание завершения процесса
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Failed to get container name for port: " + port);
        }

        return containerName;
    }

    private void executeCommand(String command) throws Exception {
        String[] cmd = {"cmd.exe", "/c", command};

        ProcessBuilder processBuilder = new ProcessBuilder(cmd);

        Process process = processBuilder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        while ((line = errorReader.readLine()) != null) {
            System.err.println(line);
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Command failed with exit code: " + exitCode);
        }
    }
    private String getDatabaseNameFromUrl(String url) {
        String[] parts = url.split("/");
        return parts[parts.length - 1];
    }

    private int getDatabasePortFromUrl(String url) {
        String[] parts1 = url.split("/");
        String[] parts2 = parts1[parts1.length - 2].split(":");
        return Integer.parseInt(parts2[parts2.length - 1]);
    }
}
