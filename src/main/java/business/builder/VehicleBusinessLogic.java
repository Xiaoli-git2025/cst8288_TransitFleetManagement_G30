package business.builder;
/*
 Student Name: Xiaoli He, Shan Cai, Yanqi Huang
 Project Name: Tranisit Fleet Management
 Section: CST8288 Section 024
 Due Date: Aug 08, 2025
*/
import dao.VehicleDAO;
import model.VehicleDTO;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

/**
 * VehicleService handles business logic for vehicle operations,
 * combining DAO access with design patterns (Builder and Simple Factory)
 * @author Xiaoli He, Shan Cai, Yanqi Huang
 * @since July 20,2025
 */
public class VehicleBusinessLogic {

    private final VehicleDAO vehicleDAO;

    public VehicleBusinessLogic() {
        this.vehicleDAO = new VehicleDAO();
    }

    /**
     * Creates a new vehicle using the Builder pattern and persists it
     * @param type Vehicle type (Diesel, Electronic, Hybrid)
     * @param vehicleNumber Vehicle identification number
     * @param consumptionRate Fuel consumption rate
     * @param maxPassenger Maximum passenger capacity
     * @param routeId Associated route ID
     * @param capacity Vehicle capacity
     * @return true if creation was successful
     */
    public boolean createVehicle(String type, String vehicleNumber, BigDecimal consumptionRate,
                               int maxPassenger, int routeId, int capacity) {
        // Use Simple Factory to get appropriate builder
        CarBuilder builder = CarDirector.createCarBuilder(type);
        if (builder == null) {
            return false;
        }
        // Build the car using Builder pattern
        Car car = builder
                .withVehicleNumber(vehicleNumber)
                .withConsumptionRate(consumptionRate)
                .withMaxPassenger(maxPassenger)
                .withRouteId(routeId)
                .withCapacity(capacity)
                .build();
        // Convert to DTO and persist
        VehicleDTO vehicleDTO = convertToDTO(car);
        return vehicleDAO.add(vehicleDTO);
    }

    /**
     * Updates an existing vehicle
     * @param vehicleId ID of vehicle to update
     * @param updatedCar Updated car details
     * @return true if update was successful
     */
    public boolean updateVehicle(int vehicleId, Car updatedCar) {
        VehicleDTO existingVehicle = vehicleDAO.getById(vehicleId);
        if (existingVehicle == null) {
            return false;
        }
        // Update the existing DTO with new values
        VehicleDTO updatedDTO = convertToDTO(updatedCar);
        updatedDTO.setVehicleId(vehicleId); // Ensure we're updating the correct record
        
        return vehicleDAO.update(updatedDTO);
    }

    /**
     * Retrieves all vehicles
     * @return List of all vehicles
     * @throws SQLException if database error occurs
     */
    public List<VehicleDTO> getAllVehicles() throws SQLException {
        return vehicleDAO.getAll();
    }

    /**
     * Retrieves a vehicle by ID
     * @param vehicleId ID of vehicle to retrieve
     * @return VehicleDTO or null if not found
     */
    public VehicleDTO getVehicleById(int vehicleId) {
        return vehicleDAO.getById(vehicleId);
    }

    /**
     * Deletes a vehicle
     * @param vehicleId ID of vehicle to delete
     * @return true if deletion was successful
     */
    public boolean deleteVehicle(int vehicleId) {
        return vehicleDAO.delete(vehicleId);
    }

    /**
     * Converts a Car object to VehicleDTO
     * @param car Car object to convert
     * @return Converted VehicleDTO
     */
    private VehicleDTO convertToDTO(Car car) {
        VehicleDTO dto = new VehicleDTO();
        dto.setVehicleNumber(car.getVehicleNumber());
        dto.setConsumptionRate(car.getConsumptionRate());
        dto.setMaxPassenger(car.getMaxPassenger());
        dto.setFuelType(car.getFuelType());
        dto.setRouteId(car.getRouteId());
        dto.setCapacity(car.getCapacity());
        return dto;
    }

    /**
     * Converts a VehicleDTO to Car object using Builder pattern
     * @param dto VehicleDTO to convert
     * @return Built Car object
     */
    public Car convertToCar(VehicleDTO dto) {
        return new CarBuilder()
                .withVehicleNumber(dto.getVehicleNumber())
                .withConsumptionRate(dto.getConsumptionRate())
                .withMaxPassenger(dto.getMaxPassenger())
                .withFuelType(dto.getFuelType())
                .withRouteId(dto.getRouteId())
                .withCapacity(dto.getCapacity())
                .build();
    }
}