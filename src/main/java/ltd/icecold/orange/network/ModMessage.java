package ltd.icecold.orange.network;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ltd.icecold.orange.OrangeVideo;
import ltd.icecold.orange.api.StageAPI;
import ltd.icecold.orange.bean.GuiButtonBean;
import ltd.icecold.orange.bean.StageBean;
import ltd.icecold.orange.event.StageButtonActionEvent;
import ltd.icecold.orange.event.StageStartPlayEvent;
import ltd.icecold.orange.event.StageStopPlayEvent;
import ltd.icecold.orange.setting.Setting;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.util.Map;
import java.util.UUID;

public class ModMessage implements PluginMessageListener {
    public static Map<UUID, StageBean> watchingStage = Maps.newHashMap();

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        String read = PacketHandle.read(message);
        if (!validate(read)) return;

        JsonObject asJsonObject = new JsonParser().parse(read).getAsJsonObject();
        String type = asJsonObject.get("type").getAsString();
        Gson gson = new Gson();
        if (PacketType.STAGE_BUTTON_ACTION.equals(type)) {
            StageBean stage = gson.fromJson(asJsonObject.get("stage"), StageBean.class);
            GuiButtonBean button = gson.fromJson(asJsonObject.get("data"), GuiButtonBean.class);
            for (String command : button.getCommands()) {
                if (button.isAsop()) {
                    boolean isOp = player.isOp();
                    try {
                        player.setOp(true);
                        OrangeVideo.getInstance().getServer().dispatchCommand(player, command.replace("%player%", player.getName()).replace("/", ""));
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        player.setOp(isOp);
                    }
                } else {
                    OrangeVideo.getInstance().getServer().dispatchCommand(player, command.replace("%player%", player.getName()).replace("/", ""));
                }
            }
            if (button.getStage() != null && !"".equals(button.getStage())) {
                String[] split = button.getStage().split("\\[");
                if (split.length != 2) {
                    throw new IllegalArgumentException("Error Button Stage Setting.");
                }
                if (!Setting.stage.containsKey(split[0])) {
                    throw new NullPointerException("Could not find stage name.");
                }
                String seek = split[1].replace("]", "");
                if (!StringUtils.isNumeric(seek)) {
                    throw new IllegalArgumentException("Error Button Stage Setting.");
                }
                StageBean willPlayStage = Setting.stage.get(split[0]);
                StageAPI.startNewStage(player, willPlayStage, Integer.parseInt(seek));
            }
            Bukkit.getServer().getPluginManager().callEvent(new StageButtonActionEvent(player, stage, button));
        }
        if (PacketType.STAGE_START_EVENT.equals(type)) {
            StageBean stage = gson.fromJson(asJsonObject.get("data"), StageBean.class);
            watchingStage.put(player.getUniqueId(), stage);
            Bukkit.getServer().getPluginManager().callEvent(new StageStartPlayEvent(player, stage));
        }
        if (PacketType.STAGE_STOP_EVENT.equals(type)) {
            StageBean stage = gson.fromJson(asJsonObject.get("data"), StageBean.class);
            watchingStage.remove(player.getUniqueId());
            Bukkit.getServer().getPluginManager().callEvent(new StageStopPlayEvent(player, stage));
        }
    }

    private static boolean validate(String json) {
        JsonElement jsonElement;
        try {
            jsonElement = new JsonParser().parse(json);
        } catch (Exception e) {
            return false;
        }
        if (jsonElement == null) {
            return false;
        }
        return jsonElement.isJsonObject();
    }

}
