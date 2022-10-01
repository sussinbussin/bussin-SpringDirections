package com.bussin.SpringDirections.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DistanceResult implements Serializable {
    private String origin;
    private String destination;
    private Long distance;
    private Long duration;
}
