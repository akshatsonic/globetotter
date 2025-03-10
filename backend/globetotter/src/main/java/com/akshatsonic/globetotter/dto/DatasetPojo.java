package com.akshatsonic.globetotter.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.util.List;

@Data
public class DatasetPojo {
    String city;
    String country;
    @JsonAlias("trivia")
    List<Trivia> trivias;
    @JsonAlias("clues")
    List<Clue> clues;
    @JsonAlias("fun_fact")
    List<Fact> funFacts;

    private class Trivia {
        String trivia;
    }

    private class Clue {
        String clue;
    }

    private class Fact {
        String fact;
    }
}
