package ltd.icecold.orange.setting;

import ltd.icecold.orange.bean.GuiImageBean;
import org.bukkit.configuration.file.YamlConfiguration;

public class ImageSetting extends Setting{

    public ImageSetting() {
        super("image");
    }

    @Override
    public void readSetting() {
        super.readSetting();
        for (String name:this.configuration.keySet()){
            YamlConfiguration yamlConfiguration = this.configuration.get(name);
            GuiImageBean guiImageBean = new GuiImageBean();
            guiImageBean.setImage(yamlConfiguration.getString("image"));
            guiImageBean.setX(yamlConfiguration.getInt("x"));
            guiImageBean.setY(yamlConfiguration.getInt("y"));
            guiImageBean.setWidth(yamlConfiguration.getInt("width"));
            guiImageBean.setHigh(yamlConfiguration.getInt("high"));
            guiImageBean.setXshow(yamlConfiguration.getInt("xshow"));
            guiImageBean.setYshow(yamlConfiguration.getInt("yshow"));
            guiImageBean.setHovertext(text.get(yamlConfiguration.getString("hovertext")));
            image.put(name,guiImageBean);
        }
    }
}
