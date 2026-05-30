package com.solvd.smarthome.persistence.impl;

import com.solvd.smarthome.domain.Device;
import com.solvd.smarthome.persistence.ConnectionPool;
import com.solvd.smarthome.persistence.DeviceRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeviceRepositoryImpl implements DeviceRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Device device) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO devices (name, model, is_online, installed_at, room_id) VALUES (?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, device.getName());
            ps.setString(2, device.getModel());
            ps.setBoolean(3, device.isOnline());
            ps.setTimestamp(4, device.getInstalledAt() != null ? Timestamp.valueOf(device.getInstalledAt()) : null);
            ps.setLong(5, device.getRoomId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
                device.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create device", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void update(Device device) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(
                "UPDATE devices SET name=?, model=?, is_online=?, installed_at=?, room_id=? WHERE id=?")) {
            ps.setString(1, device.getName());
            ps.setString(2, device.getModel());
            ps.setBoolean(3, device.isOnline());
            ps.setTimestamp(4, device.getInstalledAt() != null ? Timestamp.valueOf(device.getInstalledAt()) : null);
            ps.setLong(5, device.getRoomId());
            ps.setLong(6, device.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update device", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(
                "DELETE FROM devices WHERE id=?")) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete device", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Device findById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM devices WHERE id=?")) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapDevice(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find device", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return null;
    }

    @Override
    public List<Device> findAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Device> devices = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM devices")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                devices.add(mapDevice(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find devices", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return devices;
    }

    @Override
    public List<Device> findByRoomId(Long roomId) {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Device> devices = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM devices WHERE room_id=?")) {
            ps.setLong(1, roomId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                devices.add(mapDevice(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find devices by room", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return devices;
    }

    private Device mapDevice(ResultSet rs) throws SQLException {
        Device device = new Device();
        device.setId(rs.getLong("id"));
        device.setName(rs.getString("name"));
        device.setModel(rs.getString("model"));
        device.setOnline(rs.getBoolean("is_online"));
        Timestamp installedAt = rs.getTimestamp("installed_at");
        if (installedAt != null) {
            device.setInstalledAt(installedAt.toLocalDateTime());
        }
        device.setRoomId(rs.getLong("room_id"));
        return device;
    }
}