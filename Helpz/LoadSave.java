package Helpz;

import java.io.*;
import java.util.logging.Logger;

public class LoadSave {
    public static String LEVEL_NUMBER = "1";
    //public static String[][] LEVEL_CONTENT = {{"ConeHeadZombie"}, {"ConeHeadZombie", "ConeHeadZombie"}};
    //public static String[] LEVEL_CONTENT = {"NormalZombie","ConeHeadZombie","PoleVaultingZombie","MetalBucketZombie","FootballZombie"};
    //public static String[] LEVEL_CONTENT = {"PoleVaultingZombie","PoleVaultingZombie","PoleVaultingZombie","FootballZombie","FootballZombie","FootballZombie"};

    //public static int[][][] LEVEL_VALUE = {{{0, 99}}, {{0, 49}, {50, 99}}};
    public LoadSave() {
        try {
            File f = new File("LEVEL_CONTENT.vbhv");

            if (!f.exists()) {
                BufferedWriter bwr = new BufferedWriter(new FileWriter(f));
                bwr.write("1");
                bwr.close();
                LEVEL_NUMBER = "1";
            } else {
                BufferedReader br = new BufferedReader(new FileReader(f));
                LEVEL_NUMBER = br.readLine();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    public static void write(String lvl) {
        File f = new File("LEVEL_CONTENT.vbhv");
        try {
            BufferedWriter bwr = new BufferedWriter(new FileWriter(f));
            bwr.write(lvl);
            bwr.close();
            LEVEL_NUMBER = lvl;
        } catch (IOException e) {
            Logger.getLogger(LoadSave.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }

    }
}
