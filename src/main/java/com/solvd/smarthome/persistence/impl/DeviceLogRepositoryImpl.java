package com.solvd.smarthome.persistence.impl;

import com.solvd.smarthome.domain.DeviceLog;
import com.solvd.smarthome.persistence.ConnectionPool;
import com.solvd.smarthome.persistence.DeviceLogRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeviceLogRepositoryImpl implements DeviceLogRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(DeviceLog deviceLog) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO device_logs (message, timestamp, device_id) VALUES (?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, deviceLog.getMessage());
            ps.setTimestamp(2, deviceLog.getTimestamp() != null ? Timestamp.valueOf(deviceLog.getTimestamp()) : null);
            ps.setLong(3, deviceLog.getDeviceId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
                deviceLog.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create device log", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void update(DeviceLog deviceLog) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(
                "UPDATE device_logs SET message=?, timestamp=?, device_id=? WHERE id=?")) {
            ps.setString(1, deviceLog.getMessage());
            ps.setTimestamp(2, deviceLog.getTimestamp() != null ? Timestamp.valueOf(deviceLog.getTimestamp()) : null);
            ps.setLong(3, deviceLog.getDeviceId());
            ps.setLong(4, deviceLog.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update device log", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(
                "DELETE FROM device_logs WHERE id=?")) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete device log", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public DeviceLog findById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM device_logs WHERE id=?")) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapDeviceLog(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find device log", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return null;
    }

    @Override
    public List<DeviceLog> findAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        List<DeviceLog> logs = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM device_logs")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                logs.add(mapDeviceLog(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find device logs", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return logs;
    }

    @Override
    public List<DeviceLog> findByDeviceId(Long deviceId) {
        Connection connection = CONNECTION_POOL.getConnection();
        List<DeviceLog> logs = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM device_logs WHERE device_id=?")) {
            ps.setLong(1, deviceId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                logs.add(mapDeviceLog(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find device logs by device", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return logs;
    }

    private DeviceLog mapDeviceLog(ResultSet rs) throws SQLException {
        DeviceLog log = new DeviceLog();
        log.setId(rs.getLong("id"));
        log.setMessage(rs.getString("message"));
        Timestamp timestamp = rs.getTimestamp("timestamp");
        if (timestamp != null) {
            log.setTimestamp(timestamp.toLocalDateTime());
        }
        log.setDeviceId(rs.getLong("device_id"));
        return log;
    }
}