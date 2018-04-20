package co.cosmose.firststepsincloud.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LosAngelesVehiclesResponseDto {

    private List<LosAngelesVehicleDataDto> items;

}
