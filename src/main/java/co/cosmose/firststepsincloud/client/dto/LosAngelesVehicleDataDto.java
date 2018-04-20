package co.cosmose.firststepsincloud.client.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LosAngelesVehicleDataDto {

    private String id;
    @JsonProperty("run_id")
    private String runId;
    @JsonProperty("route_id")
    private String routeId;
    private double longitude;
    private double latitude;

}
