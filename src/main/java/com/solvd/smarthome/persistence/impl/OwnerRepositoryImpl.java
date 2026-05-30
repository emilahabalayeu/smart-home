package com.solvd.smarthome.persistence.impl;

import com.solvd.smarthome.domain.Owner;
import com.solvd.smarthome.persistence.ConnectionPool;
import com.solvd.smarthome.persistence.OwnerRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OwnerRepositoryImpl implements OwnerRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Owner owner) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO owners (first_name, last_name, email, phone, birth_date) VALUES (?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, owner.getFirstName());
            ps.setString(2, owner.getLastName());
            ps.setString(3, owner.getEmail());
            ps.setString(4, owner.getPhone());
            ps.setDate(5, owner.getBirthDate() != null ? Date.valueOf(owner.getBirthDate()) : null);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
                owner.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create owner", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void update(Owner owner) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(
                "UPDATE owners SET first_name=?, last_name=?, email=?, phone=?, birth_date=? WHERE id=?")) {
            ps.setString(1, owner.getFirstName());
            ps.setString(2, owner.getLastName());
            ps.setString(3, owner.getEmail());
            ps.setString(4, owner.getPhone());
            ps.setDate(5, owner.getBirthDate() != null ? Date.valueOf(owner.getBirthDate()) : null);
            ps.setLong(6, owner.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update owner", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(
                "DELETE FROM owners WHERE id=?")) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete owner", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Owner findById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM owners WHERE id=?")) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapOwner(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find owner", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return null;
    }

    @Override
    public List<Owner> findAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Owner> owners = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM owners")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                owners.add(mapOwner(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find owners", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return owners;
    }

    private Owner mapOwner(ResultSet rs) throws SQLException {
        Owner owner = new Owner();
        owner.setId(rs.getLong("id"));
        owner.setFirstName(rs.getString("first_name"));
        owner.setLastName(rs.getString("last_name"));
        owner.setEmail(rs.getString("email"));
        owner.setPhone(rs.getString("phone"));
        Date birthDate = rs.getDate("birth_date");
        if (birthDate != null) {
            owner.setBirthDate(birthDate.toLocalDate());
        }
        return owner;
    }
}