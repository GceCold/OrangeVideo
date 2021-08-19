package ltd.icecold.orange.commands.subcommands;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import ltd.icecold.orange.commands.SubCommand;
import ltd.icecold.orange.network.PacketHandle;
import ltd.icecold.orange.network.PacketType;
import ltd.icecold.orange.setting.Setting;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

public class StageCommand implements SubCommand {
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§7[OrangeVideo] >§cPlease use this command in the game");
            return;
        }
        if (args.length != 3) {
            sender.sendMessage("§7[OrangeVideo] >§cPlease enter the correct format command §6/video stage [name] [Video progress (unit s)]");
            return;
        }
        if (!Setting.stage.containsKey(args[1])) {
            sender.sendMessage("§7[OrangeVideo] >§cThe script was not found");
            return;
        }
        if (!StringUtils.isNumeric(args[2])) {
            sender.sendMessage("§7[OrangeVideo] >§cPlease enter the correct progress");
            return;
        }
        if (Integer.parseInt(args[2]) < 0) {
            sender.sendMessage("§7[OrangeVideo] >§cPlease enter the correct progress");
            return;
        }
        Player player = (Player) sender;
        Map<String, Object> data = Maps.newHashMap();
        data.put("type", PacketType.STAGE_PLAY);
        data.put("data", Setting.stage.get(args[1]));
        data.put("seek", args[2]);
        PacketHandle.send(player, new Gson().toJson(data));
    }
}
