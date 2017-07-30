package fr.pelt10.irltime;

import java.util.Arrays;
import java.util.Calendar;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class IRLTime extends JavaPlugin {
    private int taskID;

    @Override
    public void onEnable() {
	enableScheduler();

	new DCommand("irltime", "/irltime", "Enable/Disable IRLTime", "irltime.admin", Arrays.asList(),
		(sender, command, label, args) -> {
		    if(taskID == -1) {
			enableScheduler();
		    } else {
			disableScheduler();
		    }
		    sender.sendMessage("Le temps IRL est désormais " + (taskID==-1 ? "activé" : "désactivé"));
		    return true;
		}, this);

	super.onEnable();
    }

    private void disableScheduler() {
	getServer().getScheduler().cancelTask(taskID);
	taskID = -1;
    }

    private void enableScheduler() {
	taskID = getServer().getScheduler().runTaskTimer(this, () -> {
	    Calendar calendar = Calendar.getInstance();
	    int time = calendar.get(Calendar.HOUR_OF_DAY) * 3600 + calendar.getMaximum(Calendar.MINUTE) * 60
		    + Calendar.SECOND;

	    long tick = time * 24000 / 86400;
	    tick = (tick - 6000 < 0) ? 18000 - tick : tick - 6000;
	    for (World world : Bukkit.getWorlds()) {
		world.setTime(tick);
	    }
	}, 0, 20).getTaskId();
    }
}
