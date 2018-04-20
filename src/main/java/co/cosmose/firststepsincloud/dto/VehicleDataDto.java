package co.cosmose.firststepsincloud.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VehicleDataDto {

    private String run;
    private String line;

    private double lastLat;
    private double lastLon;

    private double currentLat;
    private double currentLon;

    private String type;

}
