package ltd.icecold.orange.setting;

import com.google.common.collect.Maps;
import ltd.icecold.orange.bean.GuiButtonBean;
import ltd.icecold.orange.bean.GuiImageBean;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.Map;

public class ButtonSetting extends Setting{


    public ButtonSetting() {
        super("button");
    }

    @Override
    public void readSetting() {
        super.readSetting();
        for (String name:this.configuration.keySet()){
            YamlConfiguration yamlConfiguration = this.configuration.get(name);
            GuiButtonBean guiButtonBean = new GuiButtonBean();
            guiButtonBean.setId(yamlConfiguration.getInt("id"));
            guiButtonBean.setX(yamlConfiguration.getInt("x"));
            guiButtonBean.setY(yamlConfiguration.getInt("y"));
            guiButtonBean.setWidth(yamlConfiguration.getInt("width"));
            guiButtonBean.setHigh(yamlConfiguration.getInt("high"));
            guiButtonBean.setName(yamlConfiguration.getString("name"));
            guiButtonBean.setColor(yamlConfiguration.getString("color"));
            guiButtonBean.setUrl(yamlConfiguration.getString("url"));
            guiButtonBean.setUrl2(yamlConfiguration.getString("url2"));
            guiButtonBean.setCommands(yamlConfiguration.getStringList("commands"));
            guiButtonBean.setAsop(yamlConfiguration.getBoolean("asop"));
            guiButtonBean.setClose(yamlConfiguration.getBoolean("close"));
            guiButtonBean.setStage(yamlConfiguration.getString("stage"));
            guiButtonBean.setClick_url(yamlConfiguration.getString("click_url"));
            button.put(name,guiButtonBean);
        }
    }
}
