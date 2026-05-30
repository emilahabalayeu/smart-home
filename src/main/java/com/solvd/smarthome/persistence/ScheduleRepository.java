package com.solvd.smarthome.persistence;

import com.solvd.smarthome.domain.Schedule;
import java.util.List;

public interface ScheduleRepository {
    void create(Schedule schedule);
    void update(Schedule schedule);
    void delete(Long id);
    Schedule findById(Long id);
    List<Schedule> findAll();
    List<Schedule> findByDeviceId(Long deviceId);
}
