package fr.pelt10.irltime;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Calendar;

public class IRLTime extends JavaPlugin {
    private int taskID;

    @Override
    public void onEnable() {
        enableScheduler();

        getCommand("irltime").setExecutor((sender, command, label, args) -> {
            if (taskID == -1) {
                enableScheduler();
            } else {
                disableScheduler();
            }
            sender.sendMessage("IRLTime is now  " + (taskID == -1 ? "disable" : "enable"));
            return true;
        });

        super.onEnable();
    }

    private void disableScheduler() {
        getServer().getScheduler().cancelTask(taskID);
        taskID = -1;
    }

    private void enableScheduler() {
        taskID = getServer().getScheduler().runTaskTimer(this, () -> {
            Calendar calendar = Calendar.getInstance();
            int time = calendar.get(Calendar.HOUR_OF_DAY) * 3600 + calendar.get(Calendar.MINUTE) * 60 + calendar.get(Calendar.SECOND);

            long tick = time * 24000 / 86400;
            tick = (tick - 6000 < 0) ? 18000 - tick : tick - 6000;
            for (World world : Bukkit.getWorlds()) {
                world.setTime(tick);
            }
        }, 0, 20).getTaskId();
    }
}
