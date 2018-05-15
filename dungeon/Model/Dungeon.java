package dungeon.Model;

import javafx.scene.control.CheckBox;

public class Dungeon {
	private int id ;
	private String playername; 
	private String playertask;
	private String playergearscore; //cp
	private String playerrole; //abiliieties
	private String playerclass; //type 
	private String playerguild;
	private CheckBox select;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	public Dungeon(int id, String playername, String playertask, String playergearscore, String playerrole,
			String playerclass, String playerguild) {
		super();
		this.id = id;
		this.playername = playername;
		this.playertask = playertask;
		this.playergearscore = playergearscore;
		this.playerrole = playerrole;
		this.playerclass = playerclass;
		this.playerguild = playerguild;
		this.select = new CheckBox();
	}
	public String getPlayerguild() {
		return playerguild;
	}
	public void setPlayerguild(String playerguild) {
		this.playerguild = playerguild;
	}
	public CheckBox getSelect() {
		return select;
	}
	public void setSelect(CheckBox select) {
		this.select = select;
	}
	/**
	 * @return the playername
	 */
	public String getPlayername() {
		return playername;
	}
	/**
	 * @param playername the playername to set
	 */
	public void setPlayername(String playername) {
		this.playername = playername;
	}
	/**
	 * @return the playertask
	 */
	public String getPlayertask() {
		return playertask;
	}
	/**
	 * @param playertask the playertask to set
	 */
	public void setPlayertask(String playertask) {
		this.playertask = playertask;
	}
	/**
	 * @return the playergearscore
	 */
	public String getPlayergearscore() {
		return playergearscore;
	}
	/**
	 * @param playergearscore the playergearscore to set
	 */
	public void setPlayergearscore(String playergearscore) {
		this.playergearscore = playergearscore;
	}
	/**
	 * @return the playerrole
	 */
	public String getPlayerrole() {
		return playerrole;
	}
	/**
	 * @param playerrole the playerrole to set
	 */
	public void setPlayerrole(String playerrole) {
		this.playerrole = playerrole;
	}
	/**
	 * @return the playerclass
	 */
	public String getPlayerclass() {
		return playerclass;
	}
	/**
	 * @param playerclass the playerclass to set
	 */
	public void setPlayerclass(String playerclass) {
		this.playerclass = playerclass;
	}
	/**
	 * @return the playerguild
	 */
	public String getPlayerGuild() {
		return playerguild;
	}
	/**
	 * @param playerguild the playerguild to set
	 */
	public void setPlayerGuild(String playerguild) {
		this.playerguild = playerguild;
	}


}