package com.example.nomenklatura_match_dictionary.service.impl;

import com.example.nomenklatura_match_dictionary.model.Match;
import com.example.nomenklatura_match_dictionary.repository.MatchRepository;
import com.example.nomenklatura_match_dictionary.service.DictionaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DictionaryServiceImpl implements DictionaryService {

    private final MatchRepository matchRepository;

    @Override
    public void getMatches(Model model, String keyword) {
        List<Match> listMatches = listAll(keyword);
        model.addAttribute("listMatches", listMatches);
        model.addAttribute("keyword", keyword);

        // Проверка на наличие соответствий
        boolean noMatchesFound = listMatches.isEmpty();
        model.addAttribute("noMatchesFound", noMatchesFound);
    }

    private List<Match> listAll(String keyword) {
        if (keyword != null) {
            return matchRepository.search(keyword);
        }
        return matchRepository.findAll();
    }

    @Override
    public void showNewMatchForm(Model model) {
        Match match = new Match();
        model.addAttribute("matches", match);
    }

    @Override
    public ResponseEntity<Object> saveMatch(Match match) {
        matchRepository.save(match);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Match> editMatchById(Long id) {
        Match match = matchRepository.findById(id).get();
        if (match == null) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
        return ResponseEntity.ok(match);
    }

    @Override
    public void deleteMatch(Long id) {
        matchRepository.deleteById(id);
    }

    @Override
    public List<String> getNomenklaturaOptions(List<String> wordList) {
        List<String> nomenklaturaOptions = new ArrayList<>();
        for(String word : wordList) {
            nomenklaturaOptions.addAll(matchRepository.findNomenklaturaOptions(word));
        }
        return nomenklaturaOptions;
    }
}
