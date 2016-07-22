package sk.tsystems.mines.minesweeper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;

/**
 * Player times.
 */
public class BestTimes implements Iterable<BestTimes.PlayerTime> {
	/** List of best player times. */
	private List<PlayerTime> playerTimes = new ArrayList<PlayerTime>();

	/**
	 * Returns an iterator over a set of best times.
	 * 
	 * @return an iterator
	 */
	public Iterator<PlayerTime> iterator() {
		return playerTimes.iterator();
	}

	/**
	 * Adds player time to best times.
	 * 
	 * @param name
	 *            name to the player
	 * @param time
	 *            player time in seconds
	 */
	public void addPlayerTime(String name, int time) {
		PlayerTime player = new PlayerTime(name, time);
		
			insertToDB(player);
			
	}

	public void reset() {
		playerTimes.removeAll(playerTimes);
	}

	private void insertToDB(PlayerTime playerTime){
	    try {
//            Class.forName(DatabaseSetting.DRIVER_CLASS);
            Connection connection = DriverManager.getConnection(DatabaseSetting.URL,
                    DatabaseSetting.USER, DatabaseSetting.PASSWORD);
            // }catch(Exception e){System.out.println("Exception occured during saving high score to database: " + e.getMessage());}
 
 
            Statement stm = connection.createStatement();
            try {
                stm.executeUpdate(DatabaseSetting.QUERY_CREATE_BEST_TIMES);
            } catch (Exception e) {
                System.err.println("Exception occured during saving high score to database: " + e.getMessage());
            }
            stm.close();
 
            PreparedStatement pstm = connection.prepareStatement(DatabaseSetting.QUERY_ADD_BEST_TIME);
            pstm.setString(1, playerTime.getName());
            pstm.setInt(2, playerTime.getTime());
           // pstm.setString(3, playerTime.getLevel());
            pstm.execute();
            pstm.close();
            connection.close();
        } catch (Exception e) {
            System.err.println("Exception occured during saving high score to database: " + e.getMessage());
        }
	}

	private void selectFromDB() {
//		try {
//			Class.forName(DatabaseSetting.DRIVER_CLASS);
//		} catch (ClassNotFoundException e1) {
//			e1.printStackTrace();
//		}
		try {
			Connection connection = DriverManager.getConnection(DatabaseSetting.URL,
					DatabaseSetting.USER, DatabaseSetting.PASSWORD);
			Statement stm = connection.createStatement();
			ResultSet rs = stm
					.executeQuery(DatabaseSetting.QUERY_SELECT_BEST_TIMES);
			playerTimes.clear();
			while (rs.next()) {
				PlayerTime pt = new PlayerTime(rs.getString(1), rs.getInt(2));
				playerTimes.add(pt);
			}
			Collections.sort(playerTimes);
			stm.close();
			connection.close();
		} catch (SQLException e) {
			System.err
					.println("Exception occured during loading high score to database: "
							+ e.getMessage());

		}
	}

	/**
	 * Returns a string representation of the object.
	 * 
	 * @return a string representation of the object
	 */
	public String toString() {
		selectFromDB();
		Formatter f = new Formatter();
		int index = 1;
		for (PlayerTime playerTime : playerTimes) {
			f.format("%d. %s  %d\t \n", index, playerTime.getName(),
					playerTime.getTime());
			index++;
		}

		return f.toString();
	}

	/**
	 * Player time.
	 */
	public static class PlayerTime implements Comparable<PlayerTime> {
		/** Player name. */
		private final String name;

		public String getName() {
			return name;
		}

		public int getTime() {
			return time;
		}

		/** Playing time in seconds. */
		private final int time;

		/**
		 * Constructor.
		 * 
		 * @param name
		 *            player name
		 * @param time
		 *            playing game time in seconds
		 */
		public PlayerTime(String name, int time) {
			this.name = name;
			this.time = time;
		}

		@Override
		public int compareTo(PlayerTime o) {
			if (getTime() > o.getTime()) {
				return -1;
			} else if (getTime() < o.getTime()) {
				return 1;
			}
			return 0;
		}
	}
}