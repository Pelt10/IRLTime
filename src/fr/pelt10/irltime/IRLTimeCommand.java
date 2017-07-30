package fr.pelt10.irltime;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class IRLTimeCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		IRLTime.setEnable(!IRLTime.getEnable());
		sender.sendMessage("Le temps IRL est d�sormais " + IRLTime.getEnable());
		return true;
	}

}
