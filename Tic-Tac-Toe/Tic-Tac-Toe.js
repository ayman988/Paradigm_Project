import { GameEngine } from "../script.js";
import { play } from "../script.js"


export class Tic_Tac_Toe extends GameEngine {
        

    turn='X'

    board=[
        ['s','s','s'],
        ['s','s','s'],
        ['s','s','s']
    ];


    constructor(){
        super();
        this.turn ='X'

        this.board=[
            ['s','s','s'],
            ['s','s','s'],
            ['s','s','s']
        ];

        this.draw();
        
    }

    
    
    AlterTurn(){
        if(this.turn=='X'){
            this.turn='O'
            document.getElementById("Turn").innerHTML="O's Turn"
        }
        else{
            this.turn='X'
            document.getElementById("Turn").innerHTML="X's Turn"
        }
    }

    check(r,c){
        if(r<0||r>2||c<0||c>2){
            return false
        }
        console.log("hi")
        console.log(this.board)
        console.log(this.board[0])
        if(this.board[r][c]=='X' || this.board[r][c]=='O'){
            return false
        }
        this.board[r][c] = this.turn
    
        return true
    }

    draw(){
        for(let i=0;i<3;i++){
            for(let j=0;j<3;j++){
                let D=document.getElementById((String.fromCharCode('A'.charCodeAt(0)+i).concat(j+1)))
                if(this.board[i][j]=='X'){
                    D.innerHTML='X'
                    D.style.color="green"
                }
                else if(this.board[i][j]=='O'){
                    D.innerHTML='O'
                    D.style.color="purple";
                }
            }
        }
    }

    control(s){
        if(s.length!=2){
            document.getElementById("outMessage").innerHTML="Not Valid!"
            return
        }
        var r=s[0].charCodeAt(0)-'A'.charCodeAt(0)
        var c=s[1]-1
        if(this.check(r,c,this.turn,this.board)){
            document.getElementById('outMessage').innerHTML=""
            this.board[r][c]=this.turn  
            this.AlterTurn() 
            this.draw();
        }
        else{
            document.getElementById('outMessage').innerHTML="Not Valid!"
        }
    }

}

const game = new Tic_Tac_Toe();

window.addEventListener('DOMContentLoaded', () => {   
    // add event on button enter and call the method (play) with the user input-->example:B2
    document.getElementById('enter').onclick=function(){
        play(game,document.getElementById('input').value)
        }
    }
)



