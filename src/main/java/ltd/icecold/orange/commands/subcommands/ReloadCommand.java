package ltd.icecold.orange.commands.subcommands;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import ltd.icecold.orange.OrangeVideo;
import ltd.icecold.orange.commands.SubCommand;
import ltd.icecold.orange.network.PacketHandle;
import ltd.icecold.orange.network.PacketType;
import ltd.icecold.orange.setting.*;
import ltd.icecold.orange.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.Map;

public class ReloadCommand implements SubCommand {
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender.isOp()) {
            Setting.button.clear();
            Setting.text.clear();
            Setting.image.clear();
            Setting.stage.clear();
            new TextSetting();
            new ImageSetting();
            new ButtonSetting();
            new StageSetting();
            OrangeVideo.getInstance().reloadConfig();
            Bukkit.getScheduler().runTaskAsynchronously(OrangeVideo.getInstance(), () -> {
                Gson gson = new Gson();
                Map<String, Object> data = Maps.newHashMap();
                data.put("type", PacketType.JOIN_PACKET_BUTTON_INIT);
                data.put("data", Setting.button);
                String buttonData = gson.toJson(data);
                data.put("type", PacketType.JOIN_PACKET_IMAGE_INIT);
                data.put("data", Setting.image);
                String imageData = gson.toJson(data);
                data.put("type", PacketType.JOIN_PACKET_TEXT_INIT);
                data.put("data", Setting.text);
                String textData = gson.toJson(data);

                Utils.getOnlinePlayers().forEach((player -> {
                    PacketHandle.send(player, buttonData);
                    PacketHandle.send(player, imageData);
                    PacketHandle.send(player, textData);
                }));
            });
            sender.sendMessage("[OrangeVideo] >§6Reload success");
        } else {
            sender.sendMessage("[OrangeVideo] >§cYou don't have permission!");
        }
    }
}
