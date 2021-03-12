package com.parkit.parkingsystem.util;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * manage user's input
 *
 */
public class InputReaderUtil {

	private static Scanner scan = new Scanner(System.in, StandardCharsets.UTF_8.name());
	private static final Logger logger = LogManager.getLogger("InputReaderUtil");

	/**
	 * manage user's input for selection in initial menu
	 * 
	 * @return user's chosen number
	 */
	public int readSelection() {
		try {
			int input = Integer.parseInt(scan.nextLine());
			return input;
		} catch (Exception e) {
			logger.error("Error while reading user input from Shell", e);
			System.out.println("Error reading input. Please enter valid number for proceeding further");
			return -1;
		}
	}

	/**
	 * manage user's input for vehicle number
	 * 
	 * @return inputed vehicle number
	 * @throws Exception Exception e if user's input string is not valid
	 */
	public String readVehicleRegistrationNumber() throws Exception {
		try {
			String vehicleRegNumber = scan.nextLine();
			if (vehicleRegNumber == null || vehicleRegNumber.trim().length() == 0) {
				throw new IllegalArgumentException("Invalid input provided");
			}
			return vehicleRegNumber;
		} catch (Exception e) {
			logger.error("Error while reading user input from Shell", e);
			System.out.println("Error reading input. Please enter a valid string for vehicle registration number");
			throw e;
		}
	}

}
