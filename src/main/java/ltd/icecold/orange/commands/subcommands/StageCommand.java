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

public class StageCommand implements SubCommand {
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage("§7[OrangeVideo] >§c请在游戏中使用本指令");
            return;
        }
        if (args.length != 3){
            sender.sendMessage("§7[OrangeVideo] >§c请输入正确格式指令§6/video stage [name] [视频进度(单位s)]");
            return;
        }
        if (!Setting.stage.containsKey(args[1])){
            sender.sendMessage("§7[OrangeVideo] >§c未找到该剧本");
            return;
        }
        if (!StringUtils.isNumeric(args[2])){
            sender.sendMessage("§7[OrangeVideo] >§c请输入正确的进度");
            return;
        }
        if (Integer.parseInt(args[2])<0){
            sender.sendMessage("§7[OrangeVideo] >§c请输入正确的进度");
            return;
        }
        Player player = (Player) sender;
        Map<String,Object> data = Maps.newHashMap();
        data.put("type", PacketType.STAGE_PLAY);
        data.put("data", Setting.stage.get(args[1]));
        data.put("seek", args[2]);
        PacketHandle.send(player,new Gson().toJson(data));
    }
}
