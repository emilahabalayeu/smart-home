-- owners
INSERT INTO mydb.owners (first_name, last_name, email, phone, birth_date) VALUES ('John', 'Doe', 'john@example.com', '+1234567890', '1990-01-15');
INSERT INTO mydb.owners (first_name, last_name, email, phone, birth_date) VALUES ('Mary', 'Smith', 'mary@example.com', '+0987654321', '1985-05-20');

-- smart_homes
INSERT INTO mydb.smart_homes (address, owner_id) VALUES ('123 Main St', 1);
INSERT INTO mydb.smart_homes (address, owner_id) VALUES ('456 Oak Ave', 2);

-- rooms
INSERT INTO mydb.rooms (name, area_sq_meters, smart_home_id) VALUES ('Living Room', 25.5, 1);
INSERT INTO mydb.rooms (name, area_sq_meters, smart_home_id) VALUES ('Bedroom', 18.0, 1);
INSERT INTO mydb.rooms (name, area_sq_meters, smart_home_id) VALUES ('Kitchen', 15.0, 2);
INSERT INTO mydb.rooms (name, area_sq_meters, smart_home_id) VALUES ('Bathroom', 8.0, 2);

-- devices
INSERT INTO mydb.devices (name, model, is_online, installed_at, room_id) VALUES ('Nest Thermostat', 'T3', 1, '2024-01-10 10:00:00', 1);
INSERT INTO mydb.devices (name, model, is_online, installed_at, room_id) VALUES ('Philips Hue', 'Hue v2', 1, '2024-01-11 11:00:00', 1);
INSERT INTO mydb.devices (name, model, is_online, installed_at, room_id) VALUES ('Yale Lock', 'YRD256', 1, '2024-01-12 12:00:00', 2);
INSERT INTO mydb.devices (name, model, is_online, installed_at, room_id) VALUES ('Arlo Camera', 'Pro4', 0, '2024-01-13 13:00:00', 2);
INSERT INTO mydb.devices (name, model, is_online, installed_at, room_id) VALUES ('Sense Meter', 'SE1', 1, '2024-01-14 14:00:00', 3);
INSERT INTO mydb.devices (name, model, is_online, installed_at, room_id) VALUES ('Samsung Light', 'SL100', 0, '2024-01-15 15:00:00', 3);

-- thermostats
INSERT INTO mydb.thermostats (target_temperature, current_temperature, device_id) VALUES (22.0, 20.5, 1);
INSERT INTO mydb.thermostats (target_temperature, current_temperature, device_id) VALUES (24.0, 23.0, 2);

-- smart_lights
INSERT INTO mydb.smart_lights (brightness, color, device_id) VALUES (80, 'white', 2);
INSERT INTO mydb.smart_lights (brightness, color, device_id) VALUES (50, 'warm', 6);

-- smart_locks
INSERT INTO mydb.smart_locks (is_locked, access_code, device_id) VALUES (1, '1234', 3);
INSERT INTO mydb.smart_locks (is_locked, access_code, device_id) VALUES (0, '5678', 4);

-- cameras
INSERT INTO mydb.cameras (resolution, is_recording, device_id) VALUES (4, 1, 4);
INSERT INTO mydb.cameras (resolution, is_recording, device_id) VALUES (8, 0, 5);

-- energy_meters
INSERT INTO mydb.energy_meters (current_usage_kw, total_usage_kwh, device_id) VALUES (1.5, 150.0, 5);
INSERT INTO mydb.energy_meters (current_usage_kw, total_usage_kwh, device_id) VALUES (2.0, 200.0, 6);

-- device_logs
INSERT INTO mydb.device_logs (message, timestamp, device_id) VALUES ('Device started', '2024-01-10 10:01:00', 1);
INSERT INTO mydb.device_logs (message, timestamp, device_id) VALUES ('Temperature changed', '2024-01-10 11:00:00', 1);
INSERT INTO mydb.device_logs (message, timestamp, device_id) VALUES ('Light turned on', '2024-01-11 08:00:00', 2);
INSERT INTO mydb.device_logs (message, timestamp, device_id) VALUES ('Lock engaged', '2024-01-12 22:00:00', 3);

-- schedules
INSERT INTO mydb.schedules (turn_on_time, turn_off_time, start_date, is_active, device_id) VALUES ('07:00:00', '23:00:00', '2024-01-10', 1, 2);
INSERT INTO mydb.schedules (turn_on_time, turn_off_time, start_date, is_active, device_id) VALUES ('06:00:00', '22:00:00', '2024-01-11', 1, 6);