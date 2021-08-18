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
     * 播放剧情
     * @param player 玩家
     * @param stage 剧本
     * @param seek 视频开始进度 单位：秒
     */
    public static void startNewStage(Player player, StageBean stage,Integer seek){
        Map<String,Object> data = Maps.newHashMap();
        data.put("type", PacketType.STAGE_PLAY);
        data.put("data", stage);
        data.put("seek",seek);
        PacketHandle.send(player,new Gson().toJson(data));
    }

    /**
     * 向指定玩家发送播放b站视频指令
     * @param player 玩家
     * @param bv bv号
     */
    public static void playBilibili(Player player, String bv){
        Map<String,Object> data = Maps.newHashMap();
        data.put("type", PacketType.VIDEO_BILIBILI);
        data.put("data", bv);
        PacketHandle.send(player,new Gson().toJson(data));
    }

    /**
     * 添加新按钮
     * @param name 名称 在stage的设置中使用的名称
     * @param button 按钮设置
     */
    public static void addNewButton(String name, GuiButtonBean button){
        Setting.button.put(name,button);
    }

    /**
     * 添加新图片
     * @param name 名称 在stage的设置中使用的名称
     * @param image 图片设置
     */
    public static void addNewImage(String name, GuiImageBean image){
        Setting.image.put(name,image);
    }

    /**
     * 添加新文字
     * @param name 名称 在stage的设置中使用的名称
     * @param text 文本信息设置
     */
    public static void addNewText(String name, GuiTextBean text){
        Setting.text.put(name,text);
    }

    /**
     * 获取已有按钮设置
     * @param name 按钮名称
     * @return 按钮设置
     */
    public static GuiButtonBean getButton(String name){
        return Setting.button.get(name);
    }

    /**
     * 获取已有图片设置
     * @param name 图片名称
     * @return 图片设置
     */
    public static GuiImageBean getImage(String name){
        return Setting.image.get(name);
    }

    /**
     * 获取已有文字设置
     * @param name 文字名称
     * @return 文字设置
     */
    public static GuiTextBean getText(String name){
        return Setting.text.get(name);
    }

    /**
     * 玩家是否在观看视频
     * @param player 玩家
     * @return 是否正在观看
     */
    public static boolean isWatching(Player player){
        return ModMessage.watchingStage.containsKey(player.getUniqueId());
    }

    /**
     * 玩家正在观看的剧情
     * @param player 玩家
     * @return 剧情设置
     */
    public static StageBean playerWatching(Player player){
        return ModMessage.watchingStage.get(player.getUniqueId());
    }
}
