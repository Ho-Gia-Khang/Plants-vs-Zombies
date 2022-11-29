package Zombies;

public class BucketHeadZombie extends Zombies {
    public BucketHeadZombie(){
        super.health = 120;
        super.zombieDamage = 12;
        super.zombieSpeed = 0.3f;
    }

    @Override
    public int getColumn() {
        int c = 9;
        for (int i = 8; i >= 1; i--) {
            if (coorX <= column[i] - 18 && coorX > column[i - 1] - 18) {
                c = i;
                break;
            } else if (coorX <= column[0] - 18) {
                c = 0;
            }
        }
        return c;
    }

    @Override
    public int getColumnEat() {
        int c = 9;
        for(int i=8;i>=0;i--){
            if(coorX<=column[i]-14 && coorX>column[i]-78){
                c=i;
                break;
            }
        }
        return c;
    }
}
