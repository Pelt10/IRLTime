package fr.pelt10.irltime;

import java.util.ArrayList;

import org.bukkit.plugin.java.JavaPlugin;

public class IRLTime extends JavaPlugin {
	static boolean enable;
	@Override
	public void onEnable() {
		enable = true;
		getServer().getScheduler().runTaskTimer(this, new TimeRunnable(), 0, 20);
		new DCommand("irlTime", "/irlTime", "Enable/Disable IRLTime", "IrlTime.admin", new ArrayList<String>(), new IRLTimeCommand(), this);
		super.onEnable();
	}
	
	public static boolean getEnable(){
		return enable;
	}
	
	public static void setEnable(boolean enable) {
		IRLTime.enable = enable;
	}
}
