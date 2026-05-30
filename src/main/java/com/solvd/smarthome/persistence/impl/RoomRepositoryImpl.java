package com.solvd.smarthome.persistence.impl;

import com.solvd.smarthome.domain.Room;
import com.solvd.smarthome.persistence.ConnectionPool;
import com.solvd.smarthome.persistence.RoomRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomRepositoryImpl implements RoomRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Room room) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO rooms (name, area_sq_meters, smart_home_id) VALUES (?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, room.getName());
            ps.setDouble(2, room.getAreaSqMeters());
            ps.setLong(3, room.getSmartHomeId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
                room.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create room", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void update(Room room) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(
                "UPDATE rooms SET name=?, area_sq_meters=?, smart_home_id=? WHERE id=?")) {
            ps.setString(1, room.getName());
            ps.setDouble(2, room.getAreaSqMeters());
            ps.setLong(3, room.getSmartHomeId());
            ps.setLong(4, room.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update room", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(
                "DELETE FROM rooms WHERE id=?")) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete room", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Room findById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM rooms WHERE id=?")) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapRoom(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find room", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return null;
    }

    @Override
    public List<Room> findAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Room> rooms = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM rooms")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                rooms.add(mapRoom(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find rooms", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return rooms;
    }

    private Room mapRoom(ResultSet rs) throws SQLException {
        Room room = new Room();
        room.setId(rs.getLong("id"));
        room.setName(rs.getString("name"));
        room.setAreaSqMeters(rs.getDouble("area_sq_meters"));
        room.setSmartHomeId(rs.getLong("smart_home_id"));
        return room;
    }
}