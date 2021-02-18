package comp3350.team7.scheduleapp.objects;

public class RecyclerViewItem {
    private String title;
    private boolean showOption = false;
    private String time;
    private int image;

    public RecyclerViewItem() {
    }

    public RecyclerViewItem(String title,String time, int image, boolean showOption) {
        this.title = title;
        this.showOption = showOption;
        this.image = image;
        this.time = time;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
    public String getTitle() {
        return title;
    }

    public boolean isShowMenu() {
        return showOption;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setShowMenu(boolean showOption) {
        this.showOption = showOption;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        time = time;
    }
}
