package edu.rosehulman.linearlightsout

class GameModel {
    var buttonChecked: BooleanArray
    var round: Int

    constructor(){
        buttonChecked = BooleanArray(7)
        for(k in 0..6){
            val random = (0..1).random()
            buttonChecked[k] = random == 1
        }
        round = 0;
    }

    fun checkChecked(i: Int): Boolean {
        return buttonChecked[i]
    }

    fun pressButton(i: Int): Boolean {
        round += 1
        buttonChecked[i] = !buttonChecked[i]
        if(i > 0){
            buttonChecked[i-1] = !buttonChecked[i-1]
        }
        if(i < 6){
            buttonChecked[i+1] = !buttonChecked[i+1]
        }
        return checkWin()
    }

    fun checkWin(): Boolean {
        if(buttonChecked[0] == true){
            for(k in 1..6){
                if(buttonChecked[k] == false){
                    return false;
                }
            }
        }
        else{
            for(k in 1..6){
                if(buttonChecked[k] == true){
                    return false;
                }
            }
        }
        return true
    }

    override fun toString(): String {
        var s = ""
        for (k in buttonChecked) {
            s += if (k) "1" else "0"
        }
        return s
    }
}