package ltd.icecold.orange.event;

import ltd.icecold.orange.bean.GuiButtonBean;
import ltd.icecold.orange.bean.StageBean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class StageButtonActionEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private StageBean stage;
    private GuiButtonBean button;
    private Player player;

    public StageButtonActionEvent(Player player, StageBean stage, GuiButtonBean button) {
        this.stage = stage;
        this.button = button;
        this.player = player;
    }

    public StageBean getStage() {
        return stage;
    }

    public GuiButtonBean getButton() {
        return button;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
