package ltd.icecold.orange.commands.subcommands;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import ltd.icecold.orange.commands.SubCommand;
import ltd.icecold.orange.network.PacketHandle;
import ltd.icecold.orange.network.PacketType;
import ltd.icecold.orange.setting.Setting;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

public class SendCommand implements SubCommand {
    @Override
    public void execute(CommandSender sender, String[] args) {
        // video send [player] [stage] [seek]
        if (sender.isOp()) {
            sender.sendMessage("§7[OrangeVideo] >§cYou don't have permission");
            return;
        }
        if (args.length != 4) {
            sender.sendMessage("§7[OrangeVideo] >§cPlease enter the correct format command §6/video send [player] [stage] [Video progress (unit s)]");
            return;
        }
        if (Bukkit.getPlayer(args[1]) == null) {
            sender.sendMessage("§7[OrangeVideo] >§cTarget player is not online");
            return;
        }
        if (!Setting.stage.containsKey(args[2])) {
            sender.sendMessage("§7[OrangeVideo] >§cThe script was not found");
            return;
        }
        if (!StringUtils.isNumeric(args[3])) {
            sender.sendMessage("§7[OrangeVideo] >§cPlease enter the correct progress");
            return;
        }
        if (Integer.parseInt(args[3]) < 0) {
            sender.sendMessage("§7[OrangeVideo] >§cPlease enter the correct progress");
            return;
        }
        Player player = Bukkit.getPlayer(args[1]);
        Map<String, Object> data = Maps.newHashMap();
        data.put("type", PacketType.STAGE_PLAY);
        data.put("data", Setting.stage.get(args[2]));
        data.put("seek", args[3]);
        PacketHandle.send(player, new Gson().toJson(data));
    }
}
