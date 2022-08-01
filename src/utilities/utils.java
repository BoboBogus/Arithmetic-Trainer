package utilities;

import settings.settings;

public class utils {
    public static int HeightPercent(long percent) {
        int num = (int) (settings.PRACTICE_FRAME_HEIGHT * (percent * 0.01));
        return num;
    }

    public static int MainHeightPercent(long percent) {
        int num = (int) (settings.MAIN_FRAME_HEIGHT * (percent * 0.01));
        return num;
    }

    public static int MainWidthPercent(long percent) {
        int num = (int) (settings.MAIN_FRAME_WIDTH * (percent * 0.01));
        return num;
    }
}
