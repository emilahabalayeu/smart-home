package com.solvd.smarthome.persistence.impl;

import com.solvd.smarthome.domain.SmartHome;
import com.solvd.smarthome.persistence.ConnectionPool;
import com.solvd.smarthome.persistence.SmartHomeRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SmartHomeRepositoryImpl implements SmartHomeRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String FIND_ALL_WITH_DETAILS_QUERY =
            "SELECT sh.id, sh.address, sh.owner_id, " +
                    "o.first_name, o.last_name, o.email, " +
                    "r.id as room_id, r.name as room_name, " +
                    "d.id as device_id, d.name as device_name, d.model, d.is_online, " +
                    "dl.id as log_id, dl.message, " +
                    "sc.id as schedule_id, sc.is_active " +
                    "FROM smart_homes sh " +
                    "JOIN owners o ON sh.owner_id = o.id " +
                    "LEFT JOIN rooms r ON r.smart_home_id = sh.id " +
                    "LEFT JOIN devices d ON d.room_id = r.id " +
                    "LEFT JOIN device_logs dl ON dl.device_id = d.id " +
                    "LEFT JOIN schedules sc ON sc.device_id = d.id";

    @Override
    public void create(SmartHome smartHome) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO smart_homes (address, owner_id) VALUES (?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, smartHome.getAddress());
            ps.setLong(2, smartHome.getOwnerId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
                smartHome.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create smart home", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void update(SmartHome smartHome) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(
                "UPDATE smart_homes SET address=?, owner_id=? WHERE id=?")) {
            ps.setString(1, smartHome.getAddress());
            ps.setLong(2, smartHome.getOwnerId());
            ps.setLong(3, smartHome.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update smart home", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(
                "DELETE FROM smart_homes WHERE id=?")) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete smart home", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public SmartHome findById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM smart_homes WHERE id=?")) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapSmartHome(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find smart home", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return null;
    }

    @Override
    public List<SmartHome> findAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        List<SmartHome> smartHomes = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM smart_homes")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                smartHomes.add(mapSmartHome(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find smart homes", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return smartHomes;
    }

    @Override
    public List<SmartHome> findByOwnerId(Long ownerId) {
        Connection connection = CONNECTION_POOL.getConnection();
        List<SmartHome> smartHomes = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM smart_homes WHERE owner_id=?")) {
            ps.setLong(1, ownerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                smartHomes.add(mapSmartHome(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find smart homes by owner", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return smartHomes;
    }

    @Override
    public List<SmartHome> findAllWithDetails() {
        Connection connection = CONNECTION_POOL.getConnection();
        List<SmartHome> smartHomes = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(FIND_ALL_WITH_DETAILS_QUERY)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                smartHomes.add(mapSmartHome(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find smart homes with details", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return smartHomes;
    }

    private SmartHome mapSmartHome(ResultSet rs) throws SQLException {
        SmartHome smartHome = new SmartHome();
        smartHome.setId(rs.getLong("id"));
        smartHome.setAddress(rs.getString("address"));
        smartHome.setOwnerId(rs.getLong("owner_id"));
        return smartHome;
    }
}