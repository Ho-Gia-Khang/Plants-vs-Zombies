package Helpz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Audio{
    private static Clip[] clip = new Clip[20];
    private static Timer timer; //set timer

    static{
        try{
            // create clip reference
            for(int i=0;i<20;i++){
                clip[i] = AudioSystem.getClip();
            }
            // open audioInputStream to the clip
            clip[0].open(AudioSystem.getAudioInputStream(Audio.class.getResource(("..data/sfx/Menu.wav"))));
            clip[1].open(AudioSystem.getAudioInputStream(Audio.class.getResource(("..data/sfx/Background.wav"))));
            clip[2].open(AudioSystem.getAudioInputStream(Audio.class.getResource(("..data/sfx/Win.wav"))));
            clip[3].open(AudioSystem.getAudioInputStream(Audio.class.getResource(("..data/sfx/Lose.wav"))));
            clip[4].open(AudioSystem.getAudioInputStream(Audio.class.getResource(("..data/sfx/Zombies_coming.wav"))));
            clip[5].open(AudioSystem.getAudioInputStream(Audio.class.getResource(("..data/sfx/Seedlift.wav"))));
            clip[6].open(AudioSystem.getAudioInputStream(Audio.class.getResource(("..data/sfx/Plant.wav"))));
            clip[7].open(AudioSystem.getAudioInputStream(Audio.class.getResource(("..data/sfx/Eat.wav"))));
            clip[8].open(AudioSystem.getAudioInputStream(Audio.class.getResource(("..data/sfx/Buzzer.wav"))));
            clip[9].open(AudioSystem.getAudioInputStream(Audio.class.getResource(("..data/sfx/Evillaugh.wav"))));
            clip[10].open(AudioSystem.getAudioInputStream(Audio.class.getResource(("..data/sfx/Shovel.wav"))));
            clip[11].open(AudioSystem.getAudioInputStream(Audio.class.getResource(("..data/sfx/Remove.wav"))));
            clip[12].open(AudioSystem.getAudioInputStream(Audio.class.getResource(("..data/sfx/Wave.wav"))));
            clip[13].open(AudioSystem.getAudioInputStream(Audio.class.getResource(("..data/sfx/Siren.wav"))));
            clip[14].open(AudioSystem.getAudioInputStream(Audio.class.getResource(("..data/sfx/Groan_brains1.wav"))));
            clip[15].open(AudioSystem.getAudioInputStream(Audio.class.getResource(("..data/sfx/Groan_brains2.wav"))));
            clip[16].open(AudioSystem.getAudioInputStream(Audio.class.getResource(("..data/sfx/Groan_brains3.wav"))));
            clip[17].open(AudioSystem.getAudioInputStream(Audio.class.getResource(("..data/sfx/Groan1.wav"))));
            clip[18].open(AudioSystem.getAudioInputStream(Audio.class.getResource(("..data/sfx/Groan2.wav"))));
            clip[19].open(AudioSystem.getAudioInputStream(Audio.class.getResource(("..data/sfx/Groan3.wav"))));
        }catch(Exception ex)  {
            ex.printStackTrace();
        }

        //play zombies coming after 12 seconds
        timer = new Timer(12000, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                clip[4].setMicrosecondPosition(0);
                clip[4].start();
                timer.stop();
            }
        });
    }

    public static void menu(){
        clip[0].start();
        clip[0].loop(Clip.LOOP_CONTINUOUSLY);
    }
    public static void evillaugh(){
        clip[0].stop();
        clip[0]=null;
        clip[9].start();
    }

    public static void begin(){
        clip[9]=null;
        clip[2].stop();
        clip[3].stop();

        clip[1].start();
        clip[1].loop(Clip.LOOP_CONTINUOUSLY);
        timer.start();
    }

    public static void win(){
        clip[1].stop();
        clip[1].setMicrosecondPosition(0);

        clip[2].setMicrosecondPosition(0);
        clip[2].start();
    }

    public static void lose(){
        clip[1].stop();
        clip[1].setMicrosecondPosition(0);

        clip[3].setMicrosecondPosition(0);
        clip[3].start();
    }

    public static void seedlift(){
        clip[5].setMicrosecondPosition(0);
        clip[5].start();
    }
    public static void plant(){
        clip[6].setMicrosecondPosition(0);
        clip[6].start();
    }
    public static void shovel(){
        clip[10].setMicrosecondPosition(0);
        clip[10].start();
    }
    public static void remove(){
        clip[11].setMicrosecondPosition(0);
        clip[11].start();
    }

    public static void eat(){
        clip[7].setMicrosecondPosition(0);
        clip[7].start();
    }
    public static boolean isEating(){
        return clip[7].isActive();
    }

    public static void buzzer(){
        clip[8].setMicrosecondPosition(0);
        clip[8].start();
    }

    public static void wave(){
        clip[12].setMicrosecondPosition(0);
        clip[12].start();
    }
    public static void siren(){
        clip[13].setMicrosecondPosition(0);
        clip[13].start();
    }

    public static void brain1(){
        clip[14].setMicrosecondPosition(0);
        clip[14].start();
    }
    public static void brain2(){
        clip[15].setMicrosecondPosition(0);
        clip[15].start();
    }
    public static void brain3(){
        clip[16].setMicrosecondPosition(0);
        clip[16].start();
    }
    public static void groan1(){
        clip[17].setMicrosecondPosition(0);
        clip[17].start();
    }
    public static void groan2(){
        clip[18].setMicrosecondPosition(0);
        clip[18].start();
    }
    public static void groan3(){
        clip[19].setMicrosecondPosition(0);
        clip[19].start();
    }
}
