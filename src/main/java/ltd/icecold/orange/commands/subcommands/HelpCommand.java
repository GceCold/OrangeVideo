package ltd.icecold.orange.commands.subcommands;

import ltd.icecold.orange.commands.SubCommand;
import org.bukkit.command.CommandSender;

public class HelpCommand implements SubCommand {
    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage("§7[OrangeVideo] >§6/video stage [name] [Video progress (unit s)]     §bStart a script");
        sender.sendMessage("§7[OrangeVideo] >§6/video bilibili [bv number]     §bStart playing video of station b (Does not support Fanju)");
        sender.sendMessage("§7[OrangeVideo] >§6/video reload     §bReload the plugin configuration file");
    }
}
