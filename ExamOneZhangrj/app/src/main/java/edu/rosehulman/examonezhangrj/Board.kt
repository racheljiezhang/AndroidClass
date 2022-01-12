package edu.rosehulman.examonezhangrj

class Board {
    private var checkPen = false;
    private var matx = Array(9) { Array(9) { Mark.NONE} }

    enum class Mark {
        MARKED,
        NONE
    }

    fun checker(y: Int, x: Int) {
        if(checkPen) {
            matx[y][x] = Mark.MARKED
        }
    }


    fun remove(){
        matx = Array(9) { Array(9) { Mark.NONE} }
        checkPen = false
    }

    fun toString(coor: Coordinate) : String{
        var temp = ""
        for(i in 0..8){
            for(j in 0..8){
                if(coor.y == i && coor.x == j){
                    if(checkPen){
                        temp += "D"
                    }
                    else{
                        temp += "U"
                    }
                }
                else if(this.matx[i][j] == Mark.MARKED){
                    temp += "X"
                }
                else if(this.matx[i][j] == Mark.NONE){
                    temp+= "."
                }
            }
            temp += "\n"
        }
        return temp
    }

    fun reversePen(){
        this.checkPen = !this.checkPen
    }
}