package com.solvd.smarthome.service;

import com.solvd.smarthome.domain.Schedule;
import java.util.List;

public interface ScheduleService {
    Schedule create(Schedule schedule);
    Schedule update(Schedule schedule);
    void delete(Long id);
    Schedule findById(Long id);
    List<Schedule> findAll();
    List<Schedule> findByDeviceId(Long deviceId);
}
