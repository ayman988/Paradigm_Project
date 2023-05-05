import { GameEngine } from "../script.js";
import { play } from "../script.js"



export class Connect_4 extends GameEngine{
    board=[5,5,5,5,5,5,5]
    turn="red"

    constructor(){

        super();
        this.board = [5,5,5,5,5,5,5]
        this.turn = "red"  
    }

    control(s){
        if(s.length!=1){
            document.getElementById("outMessage").innerHTML="Not Valid!"
            return
        }
        if(this.check(s)){
            document.getElementById("outMessage").innerHTML=""
            this.draw()
            if(this.turn=="red"){
                this.turn="green"
                document.getElementById("Turn").innerHTML="Green's Turn"
               
            }
            else{
                this.turn="red"
                document.getElementById("Turn").innerHTML="Red's Turn"
     
            }
            this.board[s-1] = (this.board[s-1]-1)
            
        }
        
        else{
            document.getElementById("outMessage").innerHTML="Full !"
        }
    }

    check(s){
        if(s<1||s>7||this.board[s-1]==-1){
            return false
        }
        return true
    }

    draw(){
        let s=document.getElementById("input").value
        if(this.turn=="red"){
            document.getElementById((this.board[s-1]).toString().concat(s.toString())).style.backgroundColor="red"
        }
        else{
            document.getElementById(this.board[s-1].toString().concat(s.toString())).style.backgroundColor="green"
        }
    }


}

const game = new Connect_4();
window.addEventListener('DOMContentLoaded', () => {
    document.getElementById('enter').onclick=function(){play(game,document.getElementById("input").value)}
})  