-- ============================================
-- UPDATE statements (10)
-- ============================================

-- 1. Update owner email
UPDATE mydb.owners SET email = 'john.new@example.com' WHERE id = 1;

-- 2. Update owner phone
UPDATE mydb.owners SET phone = '+9999999999' WHERE id = 2;

-- 3. Update smart home address
UPDATE mydb.smart_homes SET address = '999 New Street' WHERE id = 1;

-- 4. Update room area
UPDATE mydb.rooms SET area_sq_meters = 30.0 WHERE id = 1;

-- 5. Update device online status
UPDATE mydb.devices SET is_online = 1 WHERE id = 4;

-- 6. Update thermostat target temperature
UPDATE mydb.thermostats SET target_temperature = 25.0 WHERE id = 1;

-- 7. Update smart lock access code
UPDATE mydb.smart_locks SET access_code = '9999' WHERE id = 1;

-- 8. Update smart light brightness
UPDATE mydb.smart_lights SET brightness = 100 WHERE id = 1;

-- 9. Update energy meter current usage
UPDATE mydb.energy_meters SET current_usage_kw = 3.5 WHERE id = 1;

-- 10. Update schedule active status
UPDATE mydb.schedules SET is_active = 0 WHERE id = 1;

-- ============================================
-- DELETE statements (10)
-- ============================================

-- 1. Delete schedule by id
DELETE FROM mydb.schedules WHERE id = 2;

-- 2. Delete device log by id
DELETE FROM mydb.device_logs WHERE id = 4;

-- 3. Delete energy meter by id
DELETE FROM mydb.energy_meters WHERE id = 2;

-- 4. Delete camera by id
DELETE FROM mydb.cameras WHERE id = 2;

-- 5. Delete smart light by id
DELETE FROM mydb.smart_lights WHERE id = 2;

-- 6. Delete smart lock by id
DELETE FROM mydb.smart_locks WHERE id = 2;

-- 7. Delete thermostat by id
DELETE FROM mydb.thermostats WHERE id = 2;

-- 8. Delete device log where message contains 'Light'
DELETE FROM mydb.device_logs WHERE message LIKE '%Light%';

-- 9. Delete rooms with area less than 10
DELETE FROM mydb.rooms WHERE area_sq_meters < 10;

-- 10. Delete offline devices
DELETE FROM mydb.devices WHERE is_online = 0;

-- ============================================
-- 1 big JOIN statement (all tables)
-- ============================================

SELECT
    o.first_name, o.last_name,
    sh.address,
    r.name AS room_name,
    d.name AS device_name, d.model,
    t.target_temperature,
    sl.is_locked,
    c.resolution,
    sli.brightness, sli.color,
    em.current_usage_kw,
    dl.message,
    sc.turn_on_time, sc.turn_off_time
FROM mydb.owners o
         JOIN mydb.smart_homes sh ON sh.owner_id = o.id
         JOIN mydb.rooms r ON r.smart_home_id = sh.id
         JOIN mydb.devices d ON d.room_id = r.id
         LEFT JOIN mydb.thermostats t ON t.device_id = d.id
         LEFT JOIN mydb.smart_locks sl ON sl.device_id = d.id
         LEFT JOIN mydb.cameras c ON c.device_id = d.id
         LEFT JOIN mydb.smart_lights sli ON sli.device_id = d.id
         LEFT JOIN mydb.energy_meters em ON em.device_id = d.id
         LEFT JOIN mydb.device_logs dl ON dl.device_id = d.id
         LEFT JOIN mydb.schedules sc ON sc.device_id = d.id;

-- ============================================
-- JOINs (5)
-- ============================================

-- 1. INNER JOIN: devices with their rooms
SELECT d.name, d.model, r.name AS room
FROM mydb.devices d
         INNER JOIN mydb.rooms r ON d.room_id = r.id;

-- 2. LEFT JOIN: all rooms with their devices (including rooms without devices)
SELECT r.name AS room, d.name AS device
FROM mydb.rooms r
         LEFT JOIN mydb.devices d ON d.room_id = r.id;

-- 3. RIGHT JOIN: all devices with their rooms
SELECT r.name AS room, d.name AS device
FROM mydb.rooms r
         RIGHT JOIN mydb.devices d ON d.room_id = r.id;

-- 4. LEFT JOIN: owners with their smart homes
SELECT o.first_name, o.last_name, sh.address
FROM mydb.owners o
         LEFT JOIN mydb.smart_homes sh ON sh.owner_id = o.id;

-- 5. INNER JOIN: devices with their logs
SELECT d.name, dl.message, dl.timestamp
FROM mydb.devices d
         INNER JOIN mydb.device_logs dl ON dl.device_id = d.id;

-- ============================================
-- Aggregate functions with GROUP BY (without HAVING) (7)
-- ============================================

-- 1. Count devices per room
SELECT r.name, COUNT(d.id) AS device_count
FROM mydb.rooms r
         LEFT JOIN mydb.devices d ON d.room_id = r.id
GROUP BY r.id, r.name;

-- 2. Average thermostat target temperature per home
SELECT sh.address, AVG(t.target_temperature) AS avg_temp
FROM mydb.smart_homes sh
         JOIN mydb.rooms r ON r.smart_home_id = sh.id
         JOIN mydb.devices d ON d.room_id = r.id
         JOIN mydb.thermostats t ON t.device_id = d.id
GROUP BY sh.id, sh.address;

-- 3. Total energy usage per home
SELECT sh.address, SUM(em.total_usage_kwh) AS total_kwh
FROM mydb.smart_homes sh
         JOIN mydb.rooms r ON r.smart_home_id = sh.id
         JOIN mydb.devices d ON d.room_id = r.id
         JOIN mydb.energy_meters em ON em.device_id = d.id
GROUP BY sh.id, sh.address;

-- 4. Count logs per device
SELECT d.name, COUNT(dl.id) AS log_count
FROM mydb.devices d
         LEFT JOIN mydb.device_logs dl ON dl.device_id = d.id
GROUP BY d.id, d.name;

-- 5. Max brightness per home
SELECT sh.address, MAX(sli.brightness) AS max_brightness
FROM mydb.smart_homes sh
         JOIN mydb.rooms r ON r.smart_home_id = sh.id
         JOIN mydb.devices d ON d.room_id = r.id
         JOIN mydb.smart_lights sli ON sli.device_id = d.id
GROUP BY sh.id, sh.address;

-- 6. Count devices per owner
SELECT o.first_name, o.last_name, COUNT(d.id) AS device_count
FROM mydb.owners o
         JOIN mydb.smart_homes sh ON sh.owner_id = o.id
         JOIN mydb.rooms r ON r.smart_home_id = sh.id
         JOIN mydb.devices d ON d.room_id = r.id
GROUP BY o.id, o.first_name, o.last_name;

-- 7. Min and max room area per home
SELECT sh.address, MIN(r.area_sq_meters) AS min_area, MAX(r.area_sq_meters) AS max_area
FROM mydb.smart_homes sh
         JOIN mydb.rooms r ON r.smart_home_id = sh.id
GROUP BY sh.id, sh.address;

-- ============================================
-- Aggregate functions with GROUP BY and HAVING (7)
-- ============================================

-- 1. Rooms with more than 1 device
SELECT r.name, COUNT(d.id) AS device_count
FROM mydb.rooms r
         LEFT JOIN mydb.devices d ON d.room_id = r.id
GROUP BY r.id, r.name
HAVING COUNT(d.id) > 1;

-- 2. Homes with average thermostat temperature above 22
SELECT sh.address, AVG(t.target_temperature) AS avg_temp
FROM mydb.smart_homes sh
         JOIN mydb.rooms r ON r.smart_home_id = sh.id
         JOIN mydb.devices d ON d.room_id = r.id
         JOIN mydb.thermostats t ON t.device_id = d.id
GROUP BY sh.id, sh.address
HAVING AVG(t.target_temperature) > 22;

-- 3. Devices with more than 1 log
SELECT d.name, COUNT(dl.id) AS log_count
FROM mydb.devices d
         LEFT JOIN mydb.device_logs dl ON dl.device_id = d.id
GROUP BY d.id, d.name
HAVING COUNT(dl.id) > 1;

-- 4. Homes with total energy usage above 100 kwh
SELECT sh.address, SUM(em.total_usage_kwh) AS total_kwh
FROM mydb.smart_homes sh
         JOIN mydb.rooms r ON r.smart_home_id = sh.id
         JOIN mydb.devices d ON d.room_id = r.id
         JOIN mydb.energy_meters em ON em.device_id = d.id
GROUP BY sh.id, sh.address
HAVING SUM(em.total_usage_kwh) > 100;

-- 5. Owners with more than 1 device
SELECT o.first_name, o.last_name, COUNT(d.id) AS device_count
FROM mydb.owners o
         JOIN mydb.smart_homes sh ON sh.owner_id = o.id
         JOIN mydb.rooms r ON r.smart_home_id = sh.id
         JOIN mydb.devices d ON d.room_id = r.id
GROUP BY o.id, o.first_name, o.last_name
HAVING COUNT(d.id) > 1;

-- 6. Homes with more than 1 room
SELECT sh.address, COUNT(r.id) AS room_count
FROM mydb.smart_homes sh
         JOIN mydb.rooms r ON r.smart_home_id = sh.id
GROUP BY sh.id, sh.address
HAVING COUNT(r.id) > 1;

-- 7. Rooms with average device installation year after 2023
SELECT r.name, COUNT(d.id) AS device_count
FROM mydb.rooms r
         JOIN mydb.devices d ON d.room_id = r.id
GROUP BY r.id, r.name
HAVING COUNT(d.id) >= 1;