package co.cosmose.firststepsincloud.service;

import co.cosmose.firststepsincloud.client.LosAngelesMetroApiClient;
import co.cosmose.firststepsincloud.client.dto.LosAngelesVehicleDataDto;
import co.cosmose.firststepsincloud.domain.VehicleLocation;
import co.cosmose.firststepsincloud.dto.VehicleDataDto;
import co.cosmose.firststepsincloud.repository.VehicleLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static co.cosmose.firststepsincloud.domain.VehicleLocation.Type.METRO;
import static co.cosmose.firststepsincloud.domain.VehicleLocation.Type.RAIL;
import static java.util.stream.Collectors.toList;

@Service
public class VehicleDataServiceImpl implements VehicleDataService {

    @Autowired
    private LosAngelesMetroApiClient losAngelesMetroApiClient;

    @Autowired
    private VehicleLocationRepository vehicleLocationRepository;

    @Override
    @Transactional
    public List<VehicleDataDto> getCurrentVehicleData(List<String> lines) {
        final List<VehicleLocation> locations = findVehicleLocations(lines);

        final Map<String, List<VehicleLocation>> map = locations.stream()
                .collect(Collectors.groupingBy(
                        VehicleLocation::getRun,
                        Collectors.mapping(Function.identity(), Collectors.toList())
                ));

        final List<VehicleDataDto> result = new ArrayList<>();

        map.values().forEach((list) -> {
            if (list.size() >= 2) {

                final VehicleLocation startLocation = list.get(0); // first
                final VehicleLocation endLocation = list.get(list.size() - 1); // last

                result.add(VehicleDataDto.builder()
                        .currentLat(endLocation.getLat())
                        .currentLon(endLocation.getLon())
                        .lastLat(startLocation.getLat())
                        .lastLon(startLocation.getLon())
                        .run(endLocation.getRun())
                        .line(endLocation.getLine())
                        .type(startLocation.getType().toString())
                        .build());
            }
        });
        return result;
    }

    private List<VehicleLocation> findVehicleLocations(List<String> lines) {
        final Sort sortByStatusDesc = new Sort(Sort.Direction.DESC, "status");
        return CollectionUtils.isEmpty(lines)
                ? vehicleLocationRepository.findAll(sortByStatusDesc)
                : vehicleLocationRepository.findByLineIn(lines, sortByStatusDesc);
    }

    @Override
    @Transactional
    public void refreshData() {
        vehicleLocationRepository.removeNextToLatest();
        vehicleLocationRepository.changeLatestToNextToLatest();

        final List<VehicleLocation> metro = losAngelesMetroApiClient.getMetroVehicles()
                .getItems().stream()
                .map(convertToVehicleLocation(METRO))
                .collect(toList());
        vehicleLocationRepository.saveAll(metro);

        final List<VehicleLocation> rail = losAngelesMetroApiClient.getMetroRailVehicles()
                .getItems().stream()
                .map(convertToVehicleLocation(RAIL))
                .collect(toList());
        vehicleLocationRepository.saveAll(rail);
    }

    private Function<LosAngelesVehicleDataDto, VehicleLocation> convertToVehicleLocation(VehicleLocation.Type type) {
        return vehicleData -> VehicleLocation.builder()
                .run(vehicleData.getId())
                .line(vehicleData.getRouteId())
                .lat(vehicleData.getLatitude())
                .lon(vehicleData.getLongitude())
                .status(VehicleLocation.Status.LATEST)
                .type(type)
                .build();
    }
}
