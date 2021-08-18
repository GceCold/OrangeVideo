package ltd.icecold.orange.bean;

import com.google.common.collect.Maps;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.List;
import java.util.Map;

public class GuiButtonBean {
    private int id;
    private String name;
    private String color;
    private String url;
    private String url2;
    private int x;
    private int y;
    private int width;
    private int high;
    private List<String> commands;
    private boolean asop;
    private boolean close;
    private String stage;
    private String click_url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl2() {
        return url2;
    }

    public void setUrl2(String url2) {
        this.url2 = url2;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public List<String> getCommands() {
        return commands;
    }

    public void setCommands(List<String> commands) {
        this.commands = commands;
    }

    public boolean isAsop() {
        return asop;
    }

    public void setAsop(boolean asop) {
        this.asop = asop;
    }

    public boolean isClose() {
        return close;
    }

    public void setClose(boolean close) {
        this.close = close;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String to) {
        this.stage = to;
    }

    public String getClick_url() {
        return click_url;
    }

    public void setClick_url(String click_url) {
        this.click_url = click_url;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "GuiButtonBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", url='" + url + '\'' +
                ", url2='" + url2 + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", high=" + high +
                ", commands=" + commands +
                ", asop=" + asop +
                ", close=" + close +
                ", stage='" + stage + '\'' +
                ", click_url='" + click_url + '\'' +
                '}';
    }
}
