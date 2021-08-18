package ltd.icecold.orange.commands.subcommands;

import ltd.icecold.orange.commands.SubCommand;
import org.bukkit.command.CommandSender;

public class HelpCommand implements SubCommand {
    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage("§7[OrangeVideo] >§6/video stage [name] [视频进度(单位s)]     §b开始一个剧本");
        sender.sendMessage("§7[OrangeVideo] >§6/video bilibili [bv号]     §b开始播放b站视频(暂不支持番剧)");
        sender.sendMessage("§7[OrangeVideo] >§6/video reload     §b重载插件配置文件");
    }
}
