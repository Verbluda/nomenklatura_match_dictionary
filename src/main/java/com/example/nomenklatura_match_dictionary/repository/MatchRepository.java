package com.example.nomenklatura_match_dictionary.repository;

import com.example.nomenklatura_match_dictionary.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {

    @Query("SELECT m FROM Match m WHERE m.rowName LIKE :searchTerm")
    List<Match> search(String searchTerm);
    @Query("SELECT m.nomenklaturaName FROM Match m WHERE m.nomenklaturaName LIKE CONCAT('%',:word,'%')")
    List<String> findNomenklaturaOptions(String word);
}
