package ltd.icecold.orange.commands;

import com.google.common.collect.Maps;
import ltd.icecold.orange.commands.subcommands.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Map;

public class CommandHandle implements CommandExecutor {
    private static final Map<String, SubCommand> subCommandMap = Maps.newHashMap();
    private static CommandHandle instance;

    public CommandHandle() {
        registerCommand("stage", new StageCommand());
        registerCommand("bilibili", new BilibiliCommand());
        registerCommand("reload", new ReloadCommand());
        registerCommand("help", new HelpCommand());
        registerCommand("send", new SendCommand());
        instance = this;
        //System.out.println(Arrays.toString(subCommandMap.keySet().toArray()));
    }

    private static void registerCommand(String subCommandName, SubCommand subCommand) {
        if (subCommandMap.containsKey(subCommandName)) {
            Bukkit.getConsoleSender().sendMessage("§6[§eOrangeGui§6] §fRepeat registration instruction!");
        }
        subCommandMap.put(subCommandName, subCommand);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            subCommandMap.get("help").execute(sender, args);
            return true;
        }
        if (!subCommandMap.containsKey(args[0])) {
            sender.sendMessage("§6[§eOrangeVideo§6] §cUnknown instruction!");
            return true;
        }

        SubCommand subCommand = subCommandMap.get(args[0]);
        subCommand.execute(sender, args);
        return true;
    }

    public static CommandHandle getInstance() {
        return instance;
    }
}
