package ltd.icecold.orange.setting;

import com.google.gson.Gson;
import ltd.icecold.orange.OrangeVideo;
import ltd.icecold.orange.bean.StageBean;
import ltd.icecold.orange.utils.IOUtil;

import java.io.File;
import java.util.List;

public class StageSetting extends Setting {
    public StageSetting() {
        super("stage");
    }

    @Override
    public void readSetting() {
        List<File> files = getFiles(OrangeVideo.getInstance().getDataFolder() + "/" + this.component);
        //System.out.println(this.component + "Number of configuration files：" + configurationFiles.size());
        Gson gson = new Gson();
        files.forEach((file) -> {
            StageBean stageBean = gson.fromJson(IOUtil.readFile(file), StageBean.class);
            Setting.stage.put(stageBean.getName(), stageBean);
        });
    }

    @Override
    protected void writeSetting() {
        if (!new File(OrangeVideo.getInstance().getDataFolder(), this.component + "/example.json").exists())
            OrangeVideo.getInstance().saveResource(this.component + "/example.json", false);
    }
}
