import { GameEngine } from "../script.js";
import { play } from "../script.js"


export class Sudoku extends GameEngine{

    board=[
        [5,3,0,0,7,0,0,0,0],
        [6,0,0,1,9,5,0,0,0],
        [0,9,8,0,0,0,0,6,0],
        [8,0,0,0,6,0,0,0,3],
        [4,0,0,8,0,3,0,0,1],
        [7,0,0,0,2,0,0,0,6],
        [0,6,0,0,0,0,2,8,0],
        [0,0,0,4,1,9,0,0,5],
        [0,0,0,0,8,0,0,7,9]
    ];

    Base=[
        [5,3,0,0,7,0,0,0,0],
        [6,0,0,1,9,5,0,0,0],
        [0,9,8,0,0,0,0,6,0],
        [8,0,0,0,6,0,0,0,3],
        [4,0,0,8,0,3,0,0,1],
        [7,0,0,0,2,0,0,0,6],
        [0,6,0,0,0,0,2,8,0],
        [0,0,0,4,1,9,0,0,5],
        [0,0,0,0,8,0,0,7,9]
    ];

    constructor(){

        super();

        //we need a function to initialize the sudoko game here
        this.board=[
            [2,3,0,0,7,0,0,0,0],
            [6,0,0,1,9,5,0,0,0],
            [0,9,8,0,0,0,0,6,0],
            [8,0,0,0,6,0,0,0,3],
            [4,0,0,8,0,3,0,0,1],
            [7,0,0,0,2,0,0,0,6],
            [0,6,0,0,0,0,2,8,0],
            [0,0,0,4,1,9,0,0,5],
            [0,0,0,0,8,0,0,7,9]
        ];

        this.Base=[
            [2,3,0,0,7,0,0,0,0],
            [6,0,0,1,9,5,0,0,0],
            [0,9,8,0,0,0,0,6,0],
            [8,0,0,0,6,0,0,0,3],
            [4,0,0,8,0,3,0,0,1],
            [7,0,0,0,2,0,0,0,6],
            [0,6,0,0,0,0,2,8,0],
            [0,0,0,4,1,9,0,0,5],
            [0,0,0,0,8,0,0,7,9]
        ];
        this.draw();
    }

    control(s){
        if(s.length!=4||s.indexOf(" ")!=2){
          document.getElementById("outMessage").innerHTML="Not Valid Input!"
          return
      }
        let input=s.split(" ")
        let r=input[0][0].charCodeAt(0)-'A'.charCodeAt(0)
        let c=input[0][1]-1
        let x=input[1]
        if(this.check(r,c,x)){
          document.getElementById('outMessage').innerHTML=""
          this.board[r][c]=x
          this.draw()
        }
        else{
          document.getElementById('outMessage').innerHTML="already exist in a row, column or box"
        }
      }

      check(r,c,x){
        if(x<0||x>9){
            return false
        }
        if(this.Base[r][c]!=0){
            return false
        }
         for(let i=0;i<9;i++){
            if(i==c){
                continue
            }
            if(this.board[r][i]==x){
                return false
            }
         }
         for(let i=0;i<9;i++){
            if(i==r){
                continue
            }
            if(this.board[i][c]==x){
                return false
            }
         }
         let sr=Math.floor(r/3)*3
         let sc=Math.floor(c/3)*3
         for(let i=0;i<3;i++){
            for(let j=0;j<3;j++){
                if(sr+i==r&&sc+j==c){
                    continue
                }
                if(this.board[sr+i][sc+j]==x){
                    return false
                }
            }
         }
         return true
    }

    draw(){
        for(let i=0;i<9;i++){
            for(let j=0;j<9;j++){
                let D=document.getElementById((String.fromCharCode('A'.charCodeAt(0)+i).concat(j+1)))
                if(this.Base[i][j]!=0){
                    D.innerHTML=this.Base[i][j]
                    D.style.color="black"
                }
                else if(this.board[i][j]==0){
                    D.innerHTML=" "
                }
                
                else{
                    D.innerHTML=this.board[i][j]
                    D.style.color="blue"
                }
            }
        }
    }
}

const game = new Sudoku();

window.addEventListener('DOMContentLoaded', () => {
    document.getElementById('enter').onclick=function(){
        play(game,document.getElementById("input").value)
        window.addEventListener("keypress",function(event){
            if (event.key === "Enter"){
                play(game,document.getElementById('input').value)
            }
        })
    }
})

