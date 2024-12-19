package com.example.nomenklatura_match_dictionary.service;

import com.example.nomenklatura_match_dictionary.model.Match;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import java.util.List;

public interface DictionaryService {

    void getMatches(Model model, String keyword);
    void showNewMatchForm(Model model);
    ResponseEntity<Object> saveMatch(Match match);
    ResponseEntity<Match> editMatchById(Long id);
    void deleteMatch(Long id);
    List<String> getNomenklaturaOptions(List<String> wordList);
}
