package ltd.icecold.orange.event;

import ltd.icecold.orange.bean.StageBean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class StageStopPlayEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final StageBean stage;
    private final Player player;

    public StageStopPlayEvent(Player player, StageBean stage) {
        this.stage = stage;
        this.player = player;
    }

    public StageBean getStage() {
        return stage;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
