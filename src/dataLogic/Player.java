package dataLogic;

import java.util.List;
import java.util.Scanner;

class Player
{
    String name;
    Mark mark;

    Player(String name, Mark mark) {
        this.name = name;
        this.mark = mark;
    }
    String getCurrentPlayerMark(){
        return mark.markStringFormat;
    }
}
