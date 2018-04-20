package co.cosmose.firststepsincloud.client;

import co.cosmose.firststepsincloud.client.dto.LosAngelesVehiclesResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "losAngelesApiClient", url = "${api.losAngeles.root}")
public interface LosAngelesMetroApiClient {


    @GetMapping("/agencies/lametro/vehicles")
    LosAngelesVehiclesResponseDto getMetroVehicles();

    @GetMapping("/agencies/lametro-rail/vehicles")
    LosAngelesVehiclesResponseDto getMetroRailVehicles();

}
