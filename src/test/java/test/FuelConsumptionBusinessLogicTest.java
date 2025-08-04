package test;

import business.FuelConsumptionBusinessLogic;
import model.FuelConsumptionDTO;
import model.VehicleDTO;
import org.junit.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class FuelConsumptionBusinessLogicTest {

    private static FuelConsumptionBusinessLogic logic;
    private static int testFuelId;
    private static int testVehicleId = 1; // 🔁 Replace with a real existing vehicle_id

    @BeforeClass
    public static void setUpClass() {
        logic = new FuelConsumptionBusinessLogic();
    }

    @AfterClass
    public static void tearDownClass() {
        if (testFuelId > 0) {
            logic.deleteFuelConsumptionById(testFuelId); // Clean up test data
        }
    }

    @Test
    public void testLogFuelConsumption() throws SQLException {
        FuelConsumptionDTO fuel = new FuelConsumptionDTO();
        fuel.setVehicleId(testVehicleId);
        fuel.setDate(new Date(System.currentTimeMillis()));
        fuel.setMilesTraveled(new BigDecimal("123.45"));
        fuel.setUnitPrice(new BigDecimal("5.67"));
        fuel.setCost(new BigDecimal("700.89"));

        boolean result = logic.LogFuelConsumption(fuel);
        assertTrue("Fuel consumption should be logged", result);

        // Find and store inserted fuel record
        List<FuelConsumptionDTO> records = logic.getFuelConsumptionsByVehicleId(testVehicleId);
        for (FuelConsumptionDTO fc : records) {
            if (fc.getMilesTraveled().compareTo(new BigDecimal("123.45")) == 0) {
                testFuelId = fc.getFcId();
                break;
            }
        }
        assertTrue("Logged fuel consumption should exist in DB", testFuelId > 0);
    }

    @Test
    public void testGetFuelConsumptionById() throws SQLException {
        if (testFuelId == 0) {
            testLogFuelConsumption(); // Ensure we have a testFuelId
        }
        FuelConsumptionDTO fuel = logic.getFuelConsumptionById(testFuelId);
        assertNotNull("Should retrieve fuel consumption by ID", fuel);
        assertEquals(testFuelId, fuel.getFcId());
    }

    @Test
    public void testUpdateFuelConsumption() throws SQLException {
        if (testFuelId == 0) {
            testLogFuelConsumption();
        }

        FuelConsumptionDTO fuel = logic.getFuelConsumptionById(testFuelId);
        fuel.setMilesTraveled(new BigDecimal("200.00"));
        boolean updated = logic.UpdateFuelConsumption(fuel);
        assertTrue("Fuel consumption should be updated", updated);

        FuelConsumptionDTO updatedFuel = logic.getFuelConsumptionById(testFuelId);
        assertEquals(new BigDecimal("200.00"), updatedFuel.getMilesTraveled());
    }

    @Test
    public void testGetFuelConsumptionsByVehicleId() {
        List<FuelConsumptionDTO> records = null;
        try {
            records = logic.getFuelConsumptionsByVehicleId(testVehicleId);
        } catch (Exception e) {
            fail("SQLException thrown: " + e.getMessage());
        }

        assertNotNull("Fuel consumption list should not be null", records);
        for (FuelConsumptionDTO record : records) {
            assertEquals(testVehicleId, record.getVehicleId());
        }
    }


    @Test
    public void testDeleteFuelConsumptionById() throws SQLException {
        // Add a record to delete
        FuelConsumptionDTO fuel = new FuelConsumptionDTO();
        fuel.setVehicleId(testVehicleId);
        fuel.setDate(new Date(System.currentTimeMillis()));
        fuel.setMilesTraveled(new BigDecimal("10.00"));
        fuel.setUnitPrice(new BigDecimal("2.00"));
        fuel.setCost(new BigDecimal("20.00"));
        logic.LogFuelConsumption(fuel);

        int tempFuelId = -1;
        for (FuelConsumptionDTO fc : logic.getFuelConsumptionsByVehicleId(testVehicleId)) {
            if (fc.getMilesTraveled().compareTo(new BigDecimal("10.00")) == 0) {
                tempFuelId = fc.getFcId();
                break;
            }
        }

        assertTrue("Temp fuel record should exist", tempFuelId > 0);
        boolean deleted = logic.deleteFuelConsumptionById(tempFuelId);
        assertTrue("Fuel consumption should be deleted", deleted);
    }
}
