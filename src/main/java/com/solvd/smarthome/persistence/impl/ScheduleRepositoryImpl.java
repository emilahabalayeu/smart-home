package com.solvd.smarthome.persistence.impl;

import com.solvd.smarthome.domain.Schedule;
import com.solvd.smarthome.persistence.ConnectionPool;
import com.solvd.smarthome.persistence.ScheduleRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScheduleRepositoryImpl implements ScheduleRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Schedule schedule) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO schedules (turn_on_time, turn_off_time, start_date, is_active, device_id) VALUES (?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
            ps.setTime(1, Time.valueOf(schedule.getTurnOnTime()));
            ps.setTime(2, Time.valueOf(schedule.getTurnOffTime()));
            ps.setDate(3, Date.valueOf(schedule.getStartDate()));
            ps.setBoolean(4, schedule.isActive());
            ps.setLong(5, schedule.getDeviceId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
                schedule.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create schedule", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void update(Schedule schedule) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(
                "UPDATE schedules SET turn_on_time=?, turn_off_time=?, start_date=?, is_active=?, device_id=? WHERE id=?")) {
            ps.setTime(1, Time.valueOf(schedule.getTurnOnTime()));
            ps.setTime(2, Time.valueOf(schedule.getTurnOffTime()));
            ps.setDate(3, Date.valueOf(schedule.getStartDate()));
            ps.setBoolean(4, schedule.isActive());
            ps.setLong(5, schedule.getDeviceId());
            ps.setLong(6, schedule.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update schedule", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(
                "DELETE FROM schedules WHERE id=?")) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete schedule", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Schedule findById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM schedules WHERE id=?")) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapSchedule(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find schedule", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return null;
    }

    @Override
    public List<Schedule> findAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Schedule> schedules = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM schedules")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                schedules.add(mapSchedule(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find schedules", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return schedules;
    }

    @Override
    public List<Schedule> findByDeviceId(Long deviceId) {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Schedule> schedules = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM schedules WHERE device_id=?")) {
            ps.setLong(1, deviceId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                schedules.add(mapSchedule(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find schedules by device", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return schedules;
    }

    private Schedule mapSchedule(ResultSet rs) throws SQLException {
        Schedule schedule = new Schedule();
        schedule.setId(rs.getLong("id"));
        schedule.setTurnOnTime(rs.getTime("turn_on_time").toLocalTime());
        schedule.setTurnOffTime(rs.getTime("turn_off_time").toLocalTime());
        schedule.setStartDate(rs.getDate("start_date").toLocalDate());
        schedule.setActive(rs.getBoolean("is_active"));
        schedule.setDeviceId(rs.getLong("device_id"));
        return schedule;
    }
}
