@echo off

start "" java -jar C:\Users\Lucy\IdeaProjects\nomenklatura_match_dictionary\target\nomenklatura_match_dictionary-0.0.1-SNAPSHOT.jar

timeout /t 10 /nobreak >nul

start "" http://localhost:9990