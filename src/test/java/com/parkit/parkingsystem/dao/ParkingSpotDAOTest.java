package com.parkit.parkingsystem.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.integration.config.DataBaseTestConfig;
import com.parkit.parkingsystem.integration.service.DataBasePrepareService;
import com.parkit.parkingsystem.model.ParkingSpot;

class ParkingSpotDAOTest {

	private static DataBaseTestConfig dataBaseTestConfig = new DataBaseTestConfig();
	private static DataBasePrepareService dataBasePrepareService;
	private static ParkingSpotDAO parkingSpotDAO;

	@BeforeAll
	private static void setUp() throws Exception {
		parkingSpotDAO = new ParkingSpotDAO();
		parkingSpotDAO.dataBaseConfig = dataBaseTestConfig;
		dataBasePrepareService = new DataBasePrepareService();
	}

	@BeforeEach
	private void setUpPerTest() throws Exception {
		dataBasePrepareService.clearDataBaseEntries();
	}

	@AfterAll
	private static void tearDown() {

	}

	@Test
	void getNextAvailableSlotTest() {
		int parkingSpotNumber = parkingSpotDAO.getNextAvailableSlot(ParkingType.CAR);
		assertEquals(1, parkingSpotNumber);
	}

	@Test
	void updateParkingTest() {
		ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR, false);
		Boolean isAvailableUpdate = parkingSpotDAO.updateParking(parkingSpot);
		assertEquals(true, isAvailableUpdate);
	}

}
