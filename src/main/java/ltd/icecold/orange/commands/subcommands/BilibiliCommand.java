package ltd.icecold.orange.commands.subcommands;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import ltd.icecold.orange.commands.SubCommand;
import ltd.icecold.orange.network.PacketHandle;
import ltd.icecold.orange.network.PacketType;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Locale;
import java.util.Map;

public class BilibiliCommand implements SubCommand {
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage("§7[OrangeVideo] >§c请在游戏中使用本指令");
            return;
        }
        if (args.length != 2){
            sender.sendMessage("§7[OrangeVideo] >§c请输入正确格式指令§6/video bilibili [bv号]");
            return;
        }
        String bv = args[1];
        if (!bv.toLowerCase().startsWith("bv")){
            sender.sendMessage("§7[OrangeVideo] >§c请输入正确格式的bv号");
            return;
        }
        Player player = (Player) sender;
        Map<String,Object> data = Maps.newHashMap();
        data.put("type", PacketType.VIDEO_BILIBILI);
        data.put("data", args[1]);
        PacketHandle.send(player,new Gson().toJson(data));
    }
}
