package com.parkit.parkingsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.parkit.parkingsystem.config.DataBaseConfig;
import com.parkit.parkingsystem.constants.DBConstants;
import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.model.ParkingSpot;

/**
 * data access objects for parking spots
 *
 */
public class ParkingSpotDAO {
	private static final Logger logger = LogManager.getLogger("ParkingSpotDAO");

	public DataBaseConfig dataBaseConfig = new DataBaseConfig();

	/**
	 * if there is parking spot available in the parking data base, return the next
	 * parking spot number
	 * 
	 * @param parkingType car or bike
	 * @return parking spot number
	 */
	public int getNextAvailableSlot(ParkingType parkingType) {
		Connection con = null;
		int result = -1;
		try {
			con = dataBaseConfig.getConnection();
			PreparedStatement ps = con.prepareStatement(DBConstants.GET_NEXT_PARKING_SPOT);
			try {
				ps.setString(1, parkingType.toString());
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
				result = rs.getInt(1);
				}
				dataBaseConfig.closeResultSet(rs);
		} finally {
				dataBaseConfig.closePreparedStatement(ps);
			}
		} catch (Exception ex) {
			logger.error("Error fetching next available slot", ex);
		} finally {
			dataBaseConfig.closeConnection(con);
		}
		return result;
	}

	/**
	 * update the availability for that parking spot
	 * 
	 * @param parkingSpot
	 * @return
	 */
	public boolean updateParking(ParkingSpot parkingSpot) {
		Connection con = null;
		try {
			con = dataBaseConfig.getConnection();
			PreparedStatement ps = con.prepareStatement(DBConstants.UPDATE_PARKING_SPOT);
			try {
				ps.setBoolean(1, parkingSpot.isAvailable());
				ps.setInt(2, parkingSpot.getId());
				int updateRowCount = ps.executeUpdate();
				return (updateRowCount == 1);
			} finally {
				if (ps != null) {
				dataBaseConfig.closePreparedStatement(ps);
				}
			}
		} catch (Exception ex) {
			logger.error("Error updating parking info", ex);
			return false;
		} finally {
			dataBaseConfig.closeConnection(con);
		}
	}

}
