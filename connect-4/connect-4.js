import { GameEngine } from "../script.js";



export class Connect_4 extends GameEngine{


    constructor(){

        super();

    }

    Initialize(){
        let board=[ 
            ["s","s","s","s","s","s","s"],
            ["s","s","s","s","s","s","s"],
            ["s","s","s","s","s","s","s"],
            ["s","s","s","s","s","s","s"],
            ["s","s","s","s","s","s","s"],
            ["s","s","s","s","s","s","s"]
        ]
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

            
            
            for(let i=5; i>=0; i--){
                if(board[i][s-1]=="s"){
                    board[i][s-1] = turn;


                    if(turn=="red"){
                        turn="green"
                    }
                    else{
                        turn="red"
                    }
        
                    return[board,true,turn];

                }
            }

            return[board,false,turn];
            
        }
        
        else{
            return[board,false,turn];
        }
    }

    check(board,s){
        if(s<1||s>7||board[s-1]==-1){
            return false
        }
        return true
    }

    draw(board){

        for(let i=0; i<=5; i++){
            for(let j=0; j<=6; j++){
                if(board[i][j]=="red"){
                    document.getElementById((i).toString().concat((j+1).toString())).style.backgroundColor="red"
                }
                else if(board[i][j]=="green"){
                    document.getElementById((i).toString().concat((j+1).toString())).style.backgroundColor="green"
                }
            }
        }


        // if(this.turn=="red"){
        //     document.getElementById((this.board[s-1]).toString().concat(s.toString())).style.backgroundColor="red"
        // }
        // else{
        //     document.getElementById(this.board[s-1].toString().concat(s.toString())).style.backgroundColor="green"
        // }
    }


}

const game = new Connect_4();
