package Main;

import javax.swing.JFrame;

import Inputs.*;

import Scenes.*;

public class Game extends JFrame implements Runnable {

    private Thread gameThread;

    private final static int FPS = 120;
    private final static int UPS = 60;

    private MyMouseListener myMouseListener;
    private KeyBoardListener keyboardListener;

    //imported classes
    private GameScreen screen;
    private Render render;
    private titleScreen titleScreen;
    private Playing playing;
    private Settings settings;
    
    public Game(){

        initClasses();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(screen);

        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    private void initInput(){
        myMouseListener = new MyMouseListener(this);
        addMouseListener(myMouseListener);
        addMouseMotionListener(myMouseListener);

        keyboardListener = new KeyBoardListener();
        addKeyListener(keyboardListener);

        requestFocus();
    }

    private void start(){
        gameThread = new Thread(this) {};
        gameThread.start();
    }

    private void updateGame(){
    }

    @Override // the game loop
    public void run() {

        long lastTimeCheck = System.currentTimeMillis();
        long now;

        int frames = 0;
        long lastTimeFPS = System.nanoTime();
        double timePerFrame = 1000000000.0/FPS;

        int updates = 0;
        long lastTimeUPS = System.nanoTime();
        double timePerUpdate = 1000000000.0/UPS;

        while(true){
            now = System.nanoTime();
            //call the repaint method once every 1/120 second - rendering
            if(now - lastTimeFPS >= timePerFrame){
                lastTimeFPS = now;
                frames++;
                repaint();
            }

            //call the updateGame method once every 1/60 second - adjust the UPS
            if(now - lastTimeUPS >= timePerUpdate){
                lastTimeUPS = now;
                updates++;
                updateGame();
            }

            // display the current status
            if(System.currentTimeMillis() - lastTimeCheck >= 1000){
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                lastTimeCheck = System.currentTimeMillis();
                frames = 0;
                updates = 0;
            }
        }
    }

    private void initClasses(){
        screen = new GameScreen(this);
        render = new Render(this);
        titleScreen = new titleScreen(this);
        playing = new Playing(this);
        settings = new Settings(this);
    }

    //getters
    public Render getRender(){
        return render; 
    }

    public titleScreen getTitleScreen(){
        return titleScreen;
    }

    public Playing getPlaying(){
        return playing;
    }

    public Settings getSettings(){
        return settings;
    }

    public static void main(String[] args){
        Game game = new Game();
        game.initInput();
        game.start();
    }

}
