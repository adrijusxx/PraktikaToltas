package dungeon.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import dungeon.View.Dashboard;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;






public class DungeonDao {
	public void addPlayer(Dungeon dungeon) throws MySQLIntegrityConstraintViolationException
	{
		String sql = "INSERT INTO `dungeon`"
				+ "(`playertask`, `playergearscore`, `playerrole`, `playerclass`,"
				+ " `playerguild`)"
				+ " VALUES (?, ?, ?, ?, ?)";
		try {
		Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/praktinis", "root", "");
		PreparedStatement add = myConn.prepareStatement(sql);
		add.setString(1,dungeon.getPlayertask());
		add.setString(2,dungeon.getPlayergearscore());
		add.setString(3,dungeon.getPlayerrole());
		add.setString(4,dungeon.getPlayerclass());
		add.setString(5,dungeon.getPlayerGuild());
		
		add.execute();
		add.close();
		}catch (MySQLIntegrityConstraintViolationException e) {
			throw new MySQLIntegrityConstraintViolationException ();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void showPlayer(ObservableList<Dungeon> data, User user ) {
		String query = "";
		if(user.getUserlevel() == Dashboard.ADMIN_LEVEL){
			 query = "SELECT * FROM dungeon";	
		}else{
			String username = user.getUsername();
			 query = "SELECT * FROM dungeon WHERE playername LIKE '"+ username +"'";	
		}
		try {
		Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/praktinis", "root", "");
		PreparedStatement add = myConn.prepareStatement(query);
		ResultSet rs = add.executeQuery();
		while(rs.next()) {
			data.add(new Dungeon(
					rs.getInt("id"),
					rs.getString("playername"),
					rs.getString("playertask"),
					rs.getString("playergearscore"),
					rs.getString("playerrole"),
					rs.getString("playerclass"),
					rs.getString("playerguild")
					));
		}
		
		}catch(Exception exc){
			exc.printStackTrace();
		
		}
	}
	
	
	public void updatePlayer(Dungeon dungeon, User user)
	{
		try {
		Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/praktinis", "root", "");
		if(user.getUsername().equals(dungeon.getPlayername())){
			
		}
		PreparedStatement upd = myConn.prepareStatement("UPDATE dungeon SET playertask = ?, playergearscore = ?,playerrole = ?,playerclass = ?,playerguild = ? WHERE id = ?");
		
		upd.setString(1,dungeon.getPlayertask());
		upd.setString(2,dungeon.getPlayergearscore());
		upd.setString(3,dungeon.getPlayerrole());
		upd.setString(4,dungeon.getPlayerclass());
		upd.setString(5,dungeon.getPlayerGuild());
		upd.setInt(6,dungeon.getId());
		upd.executeUpdate();
		upd.close();
		}catch(Exception exc){
			exc.printStackTrace();
		
		}
	}
	
	public void assignPlayer(Dungeon dungeon, User user)
	{
		try {
		Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/praktinis", "root", "");
		if(user.getUsername().equals(dungeon.getPlayername())){
			
		}
		PreparedStatement upd = myConn.prepareStatement("UPDATE dungeon SET playername = ? WHERE id = ?");
		
		upd.setString(1,dungeon.getPlayername());
		upd.setInt(2,dungeon.getId());
		upd.executeUpdate();
		upd.close();
		}catch(Exception exc){
			exc.printStackTrace();
		
		}
	}
	
	public void deletePlayer(int id) //istrinti zaideja is listo pagal ID
	{
		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/praktinis", "root", "");
			PreparedStatement del = 
					myConn.prepareStatement("delete FROM dungeon WHERE id = ?");
			del.setInt(1, id);
			del.executeUpdate();
			}catch(Exception exc){
				exc.printStackTrace();
			
			}
	}
	

	
/*	public ObservableList<Dungeon> searchPlayerByName(String pavadinimas, User user){
		String sql = "";		
		if (pavadinimas.isEmpty() && user.getUserlevel() == 9) {
			sql = "Select * FROM dungeon";
		}				
		else if (pavadinimas.isEmpty()) {
			sql = "Select * FROM dungeon WHERE playername ='"+user.getUsername()+"'";
		}
		else if(!pavadinimas.isEmpty() && user.getUserlevel() == 9){
			sql = "Select * FROM dungeon WHERE playertask LIKE '%" + pavadinimas + "%'";	
		} 
		else { // pavadinimas ivestas, ieskoma paprasto vartotojo dungeonas
			sql = "Select * FROM dungeon WHERE playertask LIKE '%" + pavadinimas + "%' AND playername ='"+user.getUsername()+"'";	
		}		
		ObservableList<Dungeon>data = FXCollections.observableArrayList();
		try {
			
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/praktinis", "root", "");
			PreparedStatement pavad = myConn.prepareStatement(sql);
			
			ResultSet rs = pavad.executeQuery();	
				while(rs.next()){
					data.add(new Dungeon(
							rs.getInt("id"),
							rs.getString("playername"),
							rs.getString("playertask"),
							rs.getString("playergearscore"),
							rs.getString("playerrole"),
							rs.getString("playerclass"),
							rs.getDouble("playerbosscountexpected"),
							rs.getDouble("playerbosscount"),
							rs.getString("playerguild")
					));	       		         		         			
				}	
		}catch(Exception exc){
			exc.printStackTrace();	
		}
			return data;
	}*/
	
}
