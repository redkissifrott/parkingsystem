package com.parkit.parkingsystem.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.integration.config.DataBaseTestConfig;
import com.parkit.parkingsystem.integration.service.DataBasePrepareService;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;

class TicketDAOTest {
	private static DataBaseTestConfig dataBaseTestConfig = new DataBaseTestConfig();
	// private static ParkingSpotDAO parkingSpotDAO;
	private static TicketDAO ticketDAO;
	private static DataBasePrepareService dataBasePrepareService;
	private Ticket ticket;

	@BeforeAll
	private static void setUp() throws Exception {
		// parkingSpotDAO = new ParkingSpotDAO();
		// parkingSpotDAO.dataBaseConfig = dataBaseTestConfig;
		ticketDAO = new TicketDAO();
		ticketDAO.dataBaseConfig = dataBaseTestConfig;
		dataBasePrepareService = new DataBasePrepareService();
	}

	@BeforeEach
	private void setUpPerTest() throws Exception {
		dataBasePrepareService.clearDataBaseEntries();
		ticket = new Ticket();
		ticket.setInTime(new Date(System.currentTimeMillis() - (60 * 60 * 1000)));
		ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR, false);
		ticket.setParkingSpot(parkingSpot);
		ticket.setId(1);
		ticket.setVehicleRegNumber("ABCDEF");
		ticket.setPrice(7);
		ticket.setRecurrence(false);
	}

	@AfterAll
	private static void tearDown() {

	}

	@Test
	public void saveAndGetTicketTest() {
		ticketDAO.saveTicket(ticket);
		Ticket ticketOut = ticketDAO.getTicket("ABCDEF");

		assertEquals(1, ticketOut.getId());
		assertEquals(7, ticketOut.getPrice());
		assertEquals(ticketOut.getRecurrence(), false);

	}

	
	@Test public void updateTicketTest() {
		ticketDAO.saveTicket(ticket);
		ticket.setPrice(9);
		ticket.setOutTime(new Date(System.currentTimeMillis() - (60 * 60 * 1000)));
		ticketDAO.updateTicket(ticket);
		Ticket ticketOut = ticketDAO.getTicket("ABCDEF");
		
		assertEquals(9, ticketOut.getPrice());
	}

}
