package ltd.icecold.orange.setting;

import com.google.common.collect.Lists;
import ltd.icecold.orange.bean.GuiImageBean;
import ltd.icecold.orange.bean.GuiTextBean;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.List;

public class TextSetting extends Setting{
    public TextSetting() {
        super("text");
    }

    @Override
    public void readSetting() {
        super.readSetting();
        for (String name:this.configuration.keySet()){
            YamlConfiguration yamlConfiguration = this.configuration.get(name);
            GuiTextBean guiTextBean = new GuiTextBean();
            guiTextBean.setX(yamlConfiguration.getInt("x"));
            guiTextBean.setY(yamlConfiguration.getInt("y"));
            guiTextBean.setScale((float) yamlConfiguration.getDouble("scale"));
            List<String> textList = Lists.newArrayList();
            yamlConfiguration.getStringList("text").forEach((text)->{
                textList.add(text.replace("&","§"));
            });
            guiTextBean.setText(textList);
            text.put(name,guiTextBean);
        }
    }
}
