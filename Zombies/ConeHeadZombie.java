package Zombies;

public class ConeHeadZombie extends Zombies {
    public ConeHeadZombie(){
        super.health = 60;
        super.zombieDamage = 12;
        super.zombieSpeed = 0.3f;
    }

    @Override
    public int getColumn() {
        int c = 9;
        for(int i=8;i>=1;i--){
            if(coorX<=column[i] && coorX>column[i-1]){
                c=i;
                break ;
            }else if(coorX<=column[0]){
                c=0;
            }
        }
        return c;
    }

    @Override
    public int getColumnEat() {
        int c = 9;
        for(int i=8;i>=1;i--){
            if(coorX<=column[i] && coorX>column[i-1]){
                c=i;
                break ;
            }else if(coorX<=column[0]){
                c=0;
            }
        }
        return c;
    }
}
