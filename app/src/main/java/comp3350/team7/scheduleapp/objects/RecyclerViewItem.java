package comp3350.team7.scheduleapp.objects;

public class RecyclerViewItem {
    private String title;
    private boolean showMenu = false;
    private String time;
    private int image;

    public RecyclerViewItem() {
    }

    public RecyclerViewItem(String title,String time, int image, boolean showMenu) {
        this.title = title;
        this.showMenu = showMenu;
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
        return showMenu;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setShowMenu(boolean showMenu) {
        this.showMenu = showMenu;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        time = time;
    }
}
