package co.cosmose.firststepsincloud.service;

import co.cosmose.firststepsincloud.dto.VehicleDataDto;

import java.util.List;

public interface VehicleDataService {

    List<VehicleDataDto> getCurrentVehicleData(List<String> lines);

    void refreshData();
}
