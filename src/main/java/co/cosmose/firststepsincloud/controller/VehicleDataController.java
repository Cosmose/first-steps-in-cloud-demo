package co.cosmose.firststepsincloud.controller;

import co.cosmose.firststepsincloud.dto.VehicleDataDto;
import co.cosmose.firststepsincloud.service.VehicleDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class VehicleDataController {

    private final VehicleDataService vehicleDataService;

    @GetMapping("/v1/vehicles")
    public List<VehicleDataDto> getCurrentVehicleData(
            @RequestParam(value = "lines", required = false) List<String> lines) {
        return vehicleDataService.getCurrentVehicleData(lines);
    }
}
