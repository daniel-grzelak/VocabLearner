package com.daniel.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.sqlite.SQLiteConfig;

import com.daniel.classes.User;
import com.daniel.classes.WordsAll;


public class Database {

	private static String DRIVER = "org.sqlite.JDBC";
	private static String DB = "jdbc:sqlite:Database.db";

	private static Connection conn;
	private static Statement stat; 
	
	public static void connect() {
		try {
			Class.forName(DRIVER);
			SQLiteConfig conf = new SQLiteConfig();
			conf.enforceForeignKeys(true);
			conn = DriverManager.getConnection(DB, conf.toProperties());
			stat = conn.createStatement();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void createTables() {
		
				try {
					String createTable = "create table if not exists Words"
							+ "(id integer primary key autoincrement,"
							+ "wordPL varchar(50) not null,"
							+ "wordENG varchar(50) not null,"
							+ "user varchar(50) not null,"
							+ "level varchar(10) not null default '0000000000',"
							+ "interval integer not null,"
							+ "date date);";

					String createTableUser = "create table if not exists User (id integer primary key autoincrement, "
							+ "username varchar(50) not null," + " password varchar(50) not null, "
							+ "role varchar(50) not null);";

					stat.execute(createTable);
					stat.execute(createTableUser);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	

	public static void insertWords(WordsAll w, User u) {

		try {
			String insertWords = "insert into Words (wordPl, wordEng, user, level, interval, date) values (?,?,?,?,?,?);";
			
			PreparedStatement ps = conn.prepareStatement(insertWords);
			ps.setString(1, w.getWordPl());
			ps.setString(2, w.getWordEng());
			ps.setString(3, u.getUsername());
			ps.setString(4, w.getLevel());
			ps.setInt(5, w.getInterval());
			ps.setTime(6, w.getDate());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void insertWordsEdit(String wordPl, String wordEng, String username, int interval) {

		try {
			String insertWords = "insert into Words (wordPl, wordEng, user, interval) values (?,?,?,?);";
			
			PreparedStatement ps = conn.prepareStatement(insertWords);
			ps.setString(1, wordPl);
			ps.setString(2, wordEng);
			ps.setString(3, username);
			ps.setInt(4, interval);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void insertUser(User u) {
		
		String insertUser = "insert into User (username, password, role) values (?,?,?);";
		
		try {
			PreparedStatement ps = conn.prepareStatement(insertUser);
			ps.setString(1, u.getUsername());
			ps.setString(2, User.encrypter(u.getPassword()));
			ps.setString(3, u.getRole());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static boolean userMatcher(String login, String password) {
		boolean ifUserExists = false;
		try {

			String selectUser = "select * from User where username like ? and password like ?;";
			PreparedStatement ps = conn.prepareStatement(selectUser);
			ps.setString(1, login);
			ps.setString(2, User.encrypter(password));
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				ifUserExists = true;
			}
			return ifUserExists;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ifUserExists;
		}

	}
	
	public static String selectRole(String username, String password) {
		try {

			String selectRole = "select role from User where username like ? and password like ?;";
			PreparedStatement ps = conn.prepareStatement(selectRole);
			ps.setString(1, username);
			ps.setString(2, User.encrypter(password));
			ResultSet rs = ps.executeQuery();
			
			String role = "";
			if (rs.next()) {
				role = rs.getString("role");
			}
			return role;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "user";
		}
	}
	
	public static void editWords(String wordPl, String wordEng, Integer id, int interval) {

		PreparedStatement ps;
		try {
			String updateWords = "UPDATE Words SET " + "wordPL = ?, wordENG = ?, interval = ? WHERE id = ?";
			ps = conn.prepareStatement(updateWords);
			ps.setString(1, wordPl);
			ps.setString(2, wordEng);
			ps.setInt(3, interval);
			ps.setInt(4, id);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static List<WordsAll> selectWordsAdmin() {

		try {
			List<WordsAll> words = new ArrayList<>();
			
			String select = "Select id, wordPL, wordENG, user, interval FROM Words;";
			ResultSet rs = stat.executeQuery(select);
			while(rs.next()) {
				words.add(new WordsAll(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
			}
			return words;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<WordsAll> selectWordsUser(String username) {

		try {
			List<WordsAll> words = new ArrayList<>();
			
			String select = "Select id, wordPL, wordENG, interval FROM Words WHERE user = ? or user = 'admin';";
			PreparedStatement ps = conn.prepareStatement(select);
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				words.add(new WordsAll(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
			}
			return words;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void deleteWords(int id) {
		
		PreparedStatement ps;
		try {
			String deleteWords = "DELETE FROM Words WHERE id = ?";
			ps = conn.prepareStatement(deleteWords);
			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//-------------------------------FREE MODE-----------------------------
	
	public static List<WordsAll> selectWordsFree(String username, int limit) {

		try {
			List<WordsAll> words = new ArrayList<>();
			
			String select = "Select id, wordPL, wordENG, interval FROM Words WHERE user = ? or user = 'admin' ORDER BY RANDOM() LIMIT ?;";
			PreparedStatement ps = conn.prepareStatement(select);
			ps.setString(1, username);
			ps.setInt(2, limit);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				words.add(new WordsAll(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
			}
			return words;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//---------------------------------INTELLIGENT MODE--------------------------
	
	public static List<WordsAll> selectWordsIntelligent(String username, int limit) {

		try {
			List<WordsAll> words = new ArrayList<>();
			
			String select = "Select id, wordPL, wordENG, interval, level FROM Words WHERE user = ? or user = 'admin' ORDER BY RANDOM() LIMIT ?;";
			PreparedStatement ps = conn.prepareStatement(select);
			ps.setString(1, username);
			ps.setInt(2, limit);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				words.add(new WordsAll(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5)));
			}
			return words;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void updateWordsLevel(List<WordsAll> list) {

		for (int i = 0; i < list.size(); i++) {
			PreparedStatement ps;
			try {
				String updateUser = "UPDATE Words SET " + "level = ? " + "WHERE id = ?";
				ps = conn.prepareStatement(updateUser);
				ps.setString(1, list.get(i).getLevel());
				ps.setInt(2, list.get(0).getId());
				ps.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


	}
	
	//--------------------------REVISION MODE-------------------
	
	public static List<WordsAll> selectWordsRevision(String username, int limit) {

		try {
			List<WordsAll> words = new ArrayList<>();
			
			String select = "Select id, wordPL, wordENG, interval, level FROM Words WHERE user = ? or user = 'admin' ORDER BY RANDOM() LIMIT ?;";
			PreparedStatement ps = conn.prepareStatement(select);
			ps.setString(1, username);
			ps.setInt(2, limit);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				words.add(new WordsAll(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5)));
			}
			return words;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
