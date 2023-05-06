import { GameEngine } from "../script.js";
import { play } from "../script.js"



export class Connect_4 extends GameEngine{


    constructor(){

        super();

    }

    Initialize(){
        let board=[5,5,5,5,5,5,5]
        let turn="red"
        let state = true;
        return [board,state,turn];
    }

    control(gameState,s){
        let board = gameState[0];
        let turn = gameState[2];
        if(s.length!=1){
            return
        }
        if(this.check(board,s)){

            if(turn=="red"){
                turn="green"
            }
            else{
                turn="red"
            }
            board[s-1] = (board[s-1]-1)
            return[board,true,turn];
            
        }
        
        else{
            return[board,false,turn];
        }
    }

    check(board,s){
        if(s<1||s>7||this.board[s-1]==-1){
            return false
        }
        return true
    }

    draw(gameState){
        let board = gameState[0];
        let state = gameState[1];
        let turn = gameState[2];
        let s=document.getElementById("input").value
        if(state == false){
            document.getElementById('outMessage').innerHTML="Not Valid!";
        }
        else{
            document.getElementById('outMessage').innerHTML="";
        }
        if(this.turn=="red"){
            document.getElementById((this.board[s-1]).toString().concat(s.toString())).style.backgroundColor="red"
        }
        else{
            document.getElementById(this.board[s-1].toString().concat(s.toString())).style.backgroundColor="green"
        }
    }


}

const game = new Connect_4();
