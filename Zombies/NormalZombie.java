package Zombies;

public class NormalZombie extends Zombies {

    public NormalZombie(){
        super.health = 40;
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
        for(int i=8;i>=0;i--){
            if(coorX<=column[i]+4 && coorX>column[i]-60){
                c=i;
                break;
            }
        }
        return c;
    }
}
