package ltd.icecold.orange.utils;

import com.google.common.collect.Lists;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.List;

public class Utils {
    public static boolean validate(String json) {
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

    public static List<Player> getOnlinePlayers() {
        List<Player> players = Lists.newArrayList();
        List<World> worlds = Lists.newArrayList();
        worlds.addAll(Bukkit.getWorlds());
        for (int i = 0; i < worlds.size(); i++) {
            if (!worlds.get(i).getPlayers().isEmpty()) {
                players.addAll(worlds.get(i).getPlayers());
            }
        }
        return players;
    }
}
