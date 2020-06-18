package dataLogic;

enum Mark
{
    X("X"),
    O("O");

    String markStringFormat;

    Mark(String markStringFormat)
    {
        this.markStringFormat = markStringFormat;
    }

    static Mark getMark(String mark) throws Exception {
        if(mark.equals("X"))
        {
            return X;
        }
        else if(mark.equals("O"))
        {
            return O;
        }

        throw new Exception("Invalid Mark");
    }


}
