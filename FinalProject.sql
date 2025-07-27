DROP DATABASE IF EXISTS Transitsystem;
CREATE DATABASE Transitsystem;
USE Transitsystem;

-- Route table 
CREATE TABLE Route (
    route_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    route_number INT unique,
    description TEXT
);
INSERT INTO Route(route_number, description) VALUES
(999, 'All'),
(200, 'Route description 1'),
(201, 'Route description 2'),
(202, 'Route description 3');

-- Station table
CREATE TABLE Station (
    station_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    station_name VARCHAR(100)
);
INSERT INTO Station(station_name) VALUES
('Station_1'),
('Station_2'),
('Station_3');

-- Alert table
CREATE TABLE Alert (
    alert_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    alert_type VARCHAR(100),
    alert_description TEXT
);
INSERT INTO Alert(alert_type, alert_description) VALUES
('Type_1', 'Alert description_1'),
('Type_2', 'Alert description_2'),
('Type_3', 'Alert description_3');

-- Users table
CREATE TABLE Users (
    user_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(100),
    email VARCHAR(100),
    password VARCHAR(100),
    user_type VARCHAR(50),
    route_id INT,
	FOREIGN KEY (route_id) REFERENCES Route(route_id)
);
INSERT INTO Users(user_name, email, password, user_type, route_id) VALUES
('admin', 'admin@gmail.com', 'admin', 'admin', 1),
('operator_he', 'ohe@gmail.com', 'he', 'operator', 3),
('operator_huang', 'ohuang@gmail.com', 'huang', 'operator', 2),
('manager_huang', 'mhuang@gmail.com', 'huang', 'manager', 1),
('manager_he', 'mhe@gmail.com', 'he', 'manager', 1),
('operator_cai', 'ocai@gmail.com', 'cai', 'operator', 4),
('manager_cai', 'mcai@gmail.com', 'cai', 'manager', 1);

-- RouteSchedule table 
CREATE TABLE RouteSchedule (
    schedule_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    route_id INT,
    station_id INT,
    schedule_number INT,
    schedule_arrive_time TIME,
    schedule_depart_time TIME,
    FOREIGN KEY (route_id) REFERENCES Route(route_id),
    FOREIGN KEY (station_id) REFERENCES Station(station_id)
);
INSERT INTO RouteSchedule(route_id, station_id, schedule_number, schedule_arrive_time, schedule_depart_time) VALUES
(2, 1, 10, '23:33:58', '17:57:10'),
(3, 2, 11, '05:05:08', '21:09:43'),
(4, 3, 12, '17:03:42', '10:18:53');

-- StationTime table 
CREATE TABLE StationTime (
    time_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    log_date DATE,
    arrive_time TIME,
    depart_time TIME,
    note TEXT,
    schedule_id INT,
    user_id INT,
    FOREIGN KEY (schedule_id) REFERENCES RouteSchedule(schedule_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);
INSERT INTO StationTime(log_date, arrive_time, depart_time, note, schedule_id, user_id) VALUES
('2025-07-15', '09:38:35', '09:50:36', 'all good', 1, 1),
('2025-07-15', '08:04:56', '08:57:48', 'so so', 2, 2),
('2025-07-15', '02:03:59', '02:46:26', 'okay', 3, 3);

-- Vehicle table
CREATE TABLE Vehicle (
    vehicle_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    vehicle_number VARCHAR(50),
    consumption_rate DECIMAL(5,2),
    max_passenger INT,
    fuel_type VARCHAR(50),
    route_id INT,
    capacity INT,
    FOREIGN KEY (route_id) REFERENCES Route(route_id)
);
INSERT INTO Vehicle(vehicle_number, consumption_rate, max_passenger, fuel_type, route_id, capacity) VALUES
('V101', 11.13, 34, 'Electric', 2, 49),
('V102', 10.3, 49, 'Diesel', 3, 52),
('V103', 7.1, 46, 'Hybrid', 4, 70);

-- FuelConsumption table
CREATE TABLE FuelConsumption (
    fc_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    vehicle_id INT,
    date DATE,
    miles_traveled DECIMAL(10,2),
    unit_price DECIMAL(6,2),
    FOREIGN KEY (vehicle_id) REFERENCES Vehicle(vehicle_id)
);
INSERT INTO FuelConsumption(vehicle_id, date, miles_traveled, unit_price) VALUES
(1, '2025-03-24', 296.49, 2.01),
(2, '2025-03-07', 220.00, 2.15),
(3, '2025-04-04', 183.56, 2.41);

-- VehicleComponent table
CREATE TABLE VehicleComponent (
    component_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    component_name VARCHAR(100),
    vehicle_id INT,
    used_hour INT,
    threshold_hour INT,
    FOREIGN KEY (vehicle_id) REFERENCES Vehicle(vehicle_id)
);
INSERT INTO VehicleComponent(component_name, vehicle_id, used_hour, threshold_hour) VALUES
('Component_1', 1, 177, 500),
('Component_2', 2, 356, 450),
('Component_3', 3, 247, 500);

-- MaintenanceAlert table
CREATE TABLE MaintenanceAlert (
    maintenance_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    alert_id INT,
    component_id INT,
    alert_date DATE,
    reporter_id INT,
    resolved BOOLEAN,
    FOREIGN KEY (alert_id) REFERENCES Alert(alert_id),
    FOREIGN KEY (component_id) REFERENCES VehicleComponent(component_id),
    FOREIGN KEY (reporter_id) REFERENCES Users(user_id)
);
INSERT INTO MaintenanceAlert(alert_id, component_id, alert_date, reporter_id, resolved) VALUES
(1, 1, '2025-07-02', 2, 0),
(2, 2, '2025-06-02', 2, 1),
(3, 3, '2025-06-28', 2, 0);

-- MaintenanceSchedule table
CREATE TABLE MaintenanceSchedule (
    schedule_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    maintenance_id INT,
    schedule_date DATE,
    note TEXT,
    maintenance_cost DECIMAL(10,2),
    FOREIGN KEY (maintenance_id) REFERENCES MaintenanceAlert(maintenance_id)
);
INSERT INTO MaintenanceSchedule(maintenance_id, schedule_date, note, maintenance_cost) VALUES
(1, '2025-07-03', 'need tool', 727.62),
(2, '2025-07-29', 'need oil change', 91.34),
(3, '2025-08-01', 'wait for parts', 264.60);
