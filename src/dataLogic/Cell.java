package dataLogic;

class Cell {
    int xPosition;
    int yPosition;
    Mark mark;

    Cell(int xPosition, int yPosition, Mark mark)
    {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.mark = mark;
    }
    Mark getMark(){
        return mark;
    }
}


