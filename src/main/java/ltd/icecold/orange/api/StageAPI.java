package ltd.icecold.orange.api;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import ltd.icecold.orange.bean.GuiButtonBean;
import ltd.icecold.orange.bean.GuiImageBean;
import ltd.icecold.orange.bean.GuiTextBean;
import ltd.icecold.orange.bean.StageBean;
import ltd.icecold.orange.network.ModMessage;
import ltd.icecold.orange.network.PacketHandle;
import ltd.icecold.orange.network.PacketType;
import ltd.icecold.orange.setting.Setting;
import org.bukkit.entity.Player;

import java.util.Map;

public class StageAPI {
    /**
     * Start new Stage
     *
     * @param player Player
     * @param stage  Script
     * @param seek   Video start progress unit: second
     */
    public static void startNewStage(Player player, StageBean stage, Integer seek) {
        Map<String, Object> data = Maps.newHashMap();
        data.put("type", PacketType.STAGE_PLAY);
        data.put("data", stage);
        data.put("seek", seek);
        PacketHandle.send(player, new Gson().toJson(data));
    }

    /**
     * Send the specified player a command to play the video of station b
     *
     * @param player Player
     * @param bv     bv number
     */
    public static void playBilibili(Player player, String bv) {
        Map<String, Object> data = Maps.newHashMap();
        data.put("type", PacketType.VIDEO_BILIBILI);
        data.put("data", bv);
        PacketHandle.send(player, new Gson().toJson(data));
    }

    /**
     * Add new button
     *
     * @param name   The name used in the stage settings
     * @param button Button settings
     */
    public static void addNewButton(String name, GuiButtonBean button) {
        Setting.button.put(name, button);
    }

    /**
     * Add new picture
     *
     * @param name  The name used in the stage settings
     * @param image Picture settings
     */
    public static void addNewImage(String name, GuiImageBean image) {
        Setting.image.put(name, image);
    }

    /**
     * Add new text
     *
     * @param name The name used in the stage settings
     * @param text Text settings
     */
    public static void addNewText(String name, GuiTextBean text) {
        Setting.text.put(name, text);
    }

    /**
     * Get existing button settings
     *
     * @param name Button name
     * @return Button settings
     */
    public static GuiButtonBean getButton(String name) {
        return Setting.button.get(name);
    }

    /**
     * Get existing picture settings
     *
     * @param name Picture name
     * @return Picture settings
     */
    public static GuiImageBean getImage(String name) {
        return Setting.image.get(name);
    }

    /**
     * Get existing text settings
     *
     * @param name Text name
     * @return Text settings
     */
    public static GuiTextBean getText(String name) {
        return Setting.text.get(name);
    }

    /**
     * Whether the player is watching the video
     *
     * @param player Player
     * @return watching = true
     */
    public static boolean isWatching(Player player) {
        return ModMessage.watchingStage.containsKey(player.getUniqueId());
    }

    /**
     * The plot the player is watching
     *
     * @param player Player
     * @return Plot setting
     */
    public static StageBean playerWatching(Player player) {
        return ModMessage.watchingStage.get(player.getUniqueId());
    }
}
