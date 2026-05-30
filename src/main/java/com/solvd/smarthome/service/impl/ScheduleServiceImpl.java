package com.solvd.smarthome.service.impl;

import com.solvd.smarthome.domain.Schedule;
import com.solvd.smarthome.persistence.ScheduleRepository;
import com.solvd.smarthome.persistence.impl.ScheduleRepositoryImpl;
import com.solvd.smarthome.service.ScheduleService;

import java.util.List;

public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository = new ScheduleRepositoryImpl();

    @Override
    public Schedule create(Schedule schedule) {
        scheduleRepository.create(schedule);
        return schedule;
    }

    @Override
    public Schedule update(Schedule schedule) {
        scheduleRepository.update(schedule);
        return schedule;
    }

    @Override
    public void delete(Long id) {
        scheduleRepository.delete(id);
    }

    @Override
    public Schedule findById(Long id) {
        return scheduleRepository.findById(id);
    }

    @Override
    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    @Override
    public List<Schedule> findByDeviceId(Long deviceId) {
        return scheduleRepository.findByDeviceId(deviceId);
    }
}
