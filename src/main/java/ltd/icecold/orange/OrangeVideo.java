package ltd.icecold.orange;

import ltd.icecold.orange.commands.CommandHandle;
import ltd.icecold.orange.listener.PlayerListener;
import ltd.icecold.orange.network.ModMessage;
import ltd.icecold.orange.setting.*;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.File;
import java.io.IOException;

public final class OrangeVideo extends JavaPlugin {

    private static OrangeVideo instance;
    public static final String CHANNEL = "OrangeVideo:message";

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getPluginCommand("video").setExecutor(new CommandHandle());
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, CHANNEL);
        Bukkit.getMessenger().registerIncomingPluginChannel(this, CHANNEL, new ModMessage());
        Bukkit.getPluginManager().registerEvents(new PlayerListener(),this);
        Bukkit.getConsoleSender().sendMessage("§a =============§6[OrangeVideo]§a=============");
        Bukkit.getConsoleSender().sendMessage("§7> §d插件版本:1.0.0");
        Metrics metrics = new Metrics(this, 12468);
        Bukkit.getConsoleSender().sendMessage("§7> §3正在加载配置文件");
        File file = new File(this.getDataFolder()+"/config.yml");
        if (!file.exists()){
            saveResource("config.yml",false);
        }
        new TextSetting();
        new ImageSetting();
        new ButtonSetting();
        new StageSetting();
        Bukkit.getConsoleSender().sendMessage("§7> §b已加载§6"+Setting.stage.size()+"§b个剧本");
        Bukkit.getConsoleSender().sendMessage("§7> §b已加载§6"+Setting.button.size()+"§b个按钮配置");
        Bukkit.getConsoleSender().sendMessage("§7> §b已加载§6"+Setting.image.size()+"§b个图片配置");
        Bukkit.getConsoleSender().sendMessage("§7> §b已加载§6"+Setting.text.size()+"§b个文字配置");
        getServer().getConsoleSender().sendMessage("§a =======================================");

        Bukkit.getScheduler().runTaskLaterAsynchronously(this, this::checkVersion,20L*5);
    }



    @Override
    public void onDisable() {
    }

    private void checkVersion()  {
        try {
            Connection.Response execute = Jsoup.connect("https://www.icecold.ltd/orange/video/version")
                    .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36 Edg/92.0.902.73")
                    .timeout(5 * 1000)
                    .ignoreContentType(true)
                    .method(Connection.Method.GET)
                    .execute();
            if (!execute.body().trim().equals("1.0.0")){
                Bukkit.getConsoleSender().sendMessage("§7[OrangeVideo] >§b检测到插件有新版本 §6"+ execute.body()+" §b可更新，请查看插件mcbbs原贴");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static OrangeVideo getInstance() {
        return instance;
    }
}
