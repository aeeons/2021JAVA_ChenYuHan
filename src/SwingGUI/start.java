package SwingGUI;

public class start {
    public indexFrame d = new indexFrame();

    public static void main(String[] args) {
        loginFrame a = new loginFrame();
        a.Init();
        registerFrameStaff b = new registerFrameStaff();
        b.Init();
        registerFrameBoss c = new registerFrameBoss();
        c.Init();
        indexFrame d = new indexFrame();
        d.Init();
    }

}
