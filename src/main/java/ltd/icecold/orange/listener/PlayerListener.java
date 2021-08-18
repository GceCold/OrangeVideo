package ltd.icecold.orange.listener;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import ltd.icecold.orange.OrangeVideo;
import ltd.icecold.orange.network.ModMessage;
import ltd.icecold.orange.network.PacketHandle;
import ltd.icecold.orange.network.PacketType;
import ltd.icecold.orange.setting.Setting;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class PlayerListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        try {
            Class<? extends CommandSender> senderClass = player.getClass();
            Method addChannel = senderClass.getDeclaredMethod("addChannel", String.class);
            addChannel.setAccessible(true);
            addChannel.invoke(player, OrangeVideo.CHANNEL);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Bukkit.getScheduler().runTaskLaterAsynchronously(OrangeVideo.getInstance(),()->{
            Gson gson = new Gson();
            Map<String, Object> buttonInitData = Maps.newHashMap();
            buttonInitData.put("type", PacketType.JOIN_PACKET_BUTTON_INIT);
            buttonInitData.put("data", Setting.button);
            PacketHandle.send(player,gson.toJson(buttonInitData));
            Map<String, Object> imageInitData = Maps.newHashMap();
            imageInitData.put("type", PacketType.JOIN_PACKET_IMAGE_INIT);
            imageInitData.put("data", Setting.image);
            PacketHandle.send(player,gson.toJson(imageInitData));
            Map<String, Object> textInitData = Maps.newHashMap();
            textInitData.put("type", PacketType.JOIN_PACKET_TEXT_INIT);
            textInitData.put("data", Setting.text);
            PacketHandle.send(player,gson.toJson(textInitData));
        },100L);
    }

    @EventHandler
    public void onPlayerDamaged(EntityDamageEvent event){
        if (ModMessage.watchingStage.containsKey(event.getEntity().getUniqueId()) && OrangeVideo.getInstance().getConfig().getBoolean("playerInvincible")) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlayerDamaged(EntityDamageByEntityEvent event){
        LivingEntity defenseEntity = (event.getEntity() instanceof LivingEntity && !(event.getEntity() instanceof ArmorStand)) ? (LivingEntity) event.getEntity() : null;
        LivingEntity attackEntity = null;

        if (event.getDamager() instanceof Projectile && ((Projectile) event.getDamager()).getShooter() instanceof LivingEntity) {
            attackEntity = (LivingEntity) ((Projectile) event.getDamager()).getShooter();
        } else if (event.getDamager() instanceof LivingEntity) {
            attackEntity = (LivingEntity) event.getDamager();
        }

        if (defenseEntity == null || attackEntity == null) return;

        if (ModMessage.watchingStage.containsKey(defenseEntity.getUniqueId()) && OrangeVideo.getInstance().getConfig().getBoolean("playerInvincible")) {
            attackEntity.sendMessage(OrangeVideo.getInstance().getConfig().getString("message"));
        }

    }
}
