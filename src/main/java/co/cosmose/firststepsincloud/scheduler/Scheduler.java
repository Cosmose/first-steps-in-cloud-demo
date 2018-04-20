package co.cosmose.firststepsincloud.scheduler;

import co.cosmose.firststepsincloud.service.VehicleDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Scheduler {

    private final VehicleDataService vehicleDataService;

    @Scheduled(fixedRate = 10000L)
    public void refreshData() {
        vehicleDataService.refreshData();
    }
}
