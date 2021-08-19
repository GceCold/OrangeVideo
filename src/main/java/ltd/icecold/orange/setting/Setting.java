package ltd.icecold.orange.setting;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import ltd.icecold.orange.OrangeVideo;
import ltd.icecold.orange.bean.GuiButtonBean;
import ltd.icecold.orange.bean.GuiImageBean;
import ltd.icecold.orange.bean.GuiTextBean;
import ltd.icecold.orange.bean.StageBean;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.List;
import java.util.Map;

public abstract class Setting {
    public static final Map<String, GuiButtonBean> button = Maps.newConcurrentMap();
    public static final Map<String, GuiImageBean> image = Maps.newConcurrentMap();
    public static final Map<String, GuiTextBean> text = Maps.newConcurrentMap();
    public static final Map<String, StageBean> stage = Maps.newConcurrentMap();

    public String component;
    public List<File> configurationFiles = Lists.newArrayList();
    public Map<String, YamlConfiguration> configuration = Maps.newHashMap();

    public Setting(String component) {
        this.component = component;
        writeSetting();
        readSetting();
    }

    public void readSetting() {
        configurationFiles = getFiles(OrangeVideo.getInstance().getDataFolder() + "/" + this.component);
        //System.out.println(this.component + "Number of configuration files：" + configurationFiles.size());
        for (File file : configurationFiles) {
            configuration.put(file.getName().substring(0, file.getName().lastIndexOf(".")), YamlConfiguration.loadConfiguration(file));
        }
    }

    protected void writeSetting() {
        if (!new File(OrangeVideo.getInstance().getDataFolder(), this.component + "/example.yml").exists())
            OrangeVideo.getInstance().saveResource(this.component + "/example.yml", false);
    }

    protected List<File> getFiles(String path) {
        File pathFile = new File(path);
        List<File> result = Lists.newArrayList();
        File[] files = pathFile.listFiles();
        if (files == null) return Lists.newArrayList();
        for (File file : files) {
            if (!file.isFile()) {
                result.addAll(getFiles(file.getAbsolutePath()));
            }
            result.add(file);
        }
        return result;
    }
}
