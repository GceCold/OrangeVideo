package ltd.icecold.orange.bean;

public class GuiImageBean {
    private String image;
    private int x;
    private int y;
    private int width;
    private int high;
    private int xshow;
    private int yshow;
    private GuiTextBean hovertext;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public int getXshow() {
        return xshow;
    }

    public void setXshow(int xshow) {
        this.xshow = xshow;
    }

    public int getYshow() {
        return yshow;
    }

    public void setYshow(int yshow) {
        this.yshow = yshow;
    }

    public GuiTextBean getHovertext() {
        return hovertext;
    }

    public void setHovertext(GuiTextBean hovertext) {
        this.hovertext = hovertext;
    }
}
