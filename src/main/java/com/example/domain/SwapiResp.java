
package com.example.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class SwapiResp {

    @JsonProperty("count")
    private String count;

    @JsonProperty("next")
    private String next;

    @JsonProperty("previous")
    private String previous;

    @JsonProperty("results")
    private List<Starship> starships;
}