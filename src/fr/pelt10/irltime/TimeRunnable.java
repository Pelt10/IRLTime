package fr.pelt10.irltime;

import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.World;

public class TimeRunnable implements Runnable {
	
	@Override
	public void run() {
		if(!IRLTime.getEnable())
			return;
		Date d = new Date();
		int time = d.getHours() * 3600 + d.getMinutes() * 60 + d.getSeconds();
		
		long tick = time*24000/86400;
		if(tick - 6000 < 0)
			tick = 24000 - (6000-tick);
		else tick -= 6000;
		for (World w : Bukkit.getWorlds()) {
			w.setTime(tick);
		}
	}

}
