import { GameEngine } from "../script.js";


export class Tic_Tac_Toe extends GameEngine {
        


    constructor(){
        super();        
    }

    Initialize(){

        let board=[
            ['s','s','s'],
            ['s','s','s'],
            ['s','s','s']
        ];
        let turn = 'X'
        let gameState = [board,true,turn]
        return gameState;
    }

    
    
    AlterTurn(turn){
        if(turn=='X'){
            turn='O'
        }
        else{
            turn='X'
        }
        return turn;
    }

    check(board,r,c){
        if(r<0||r>2||c<0||c>2){
            return false
        }
        if(board[r][c]=='X' || board[r][c]=='O'){
            return false
        }
        // board[r][c] = this.turn
        return true
    }

    draw(gameState){
        let board = gameState[0];

        for(let i=0;i<3;i++){
            for(let j=0;j<3;j++){
                let D=document.getElementById((String.fromCharCode('A'.charCodeAt(0)+i).concat(j+1)))
                if(board[i][j]=='X'){
                    D.innerHTML='X'
                    D.style.color="green"
                }
                else if(board[i][j]=='O'){
                    D.innerHTML='O';
                    D.style.color="purple";
                }
            }
        }

    }

    control(gameState,s){
        let board = gameState[0];
        let turn = gameState[2];
        if(s.length!=2){
            return [board,false,turn];
        }
        var r=s[0].charCodeAt(0)-'A'.charCodeAt(0)
        var c=s[1]-1
        if(this.check(board,r,c)){
            board[r][c] = turn  
            turn = this.AlterTurn(turn); 
            return [board,true,turn];
        }

        return [board,false,turn];
    }
}

const game = new Tic_Tac_Toe();



