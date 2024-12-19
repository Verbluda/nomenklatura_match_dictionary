package com.example.nomenklatura_match_dictionary.controller;

import com.example.nomenklatura_match_dictionary.model.Match;
import com.example.nomenklatura_match_dictionary.service.DictionaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class DictionaryController {

    private final DictionaryService dictionaryService;

    @RequestMapping("/")
    public String getMovies(Model model, @Param("keyword") String keyword) {
        dictionaryService.getMatches(model, keyword);
        return "matches";
    }

    @RequestMapping("/new")
    public String showNewMatchForm(Model model) {
        dictionaryService.showNewMatchForm(model);
        return "new_match";
    }

    @GetMapping("/getNomenklaturaOptions")
    @ResponseBody
    public List<String> getNomenklaturaOptions(@RequestParam String words) {
        List<String> wordList = Arrays.asList(words.split(","));
        return dictionaryService.getNomenklaturaOptions(wordList);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<Object> saveMatch(@RequestBody Match match) {
        return dictionaryService.saveMatch(match);
    }

    @GetMapping("/edit/{id}")
    @ResponseBody
    public ResponseEntity<Match> editMatchById(@PathVariable Long id) {
        return dictionaryService.editMatchById(id);
    }

    @RequestMapping("/delete/{id}")
    public String deleteMatch(@PathVariable(name = "id") Long id) {
        dictionaryService.deleteMatch(id);
        return "redirect:/";
    }

}
