import { GameEngine } from "../script.js";
import { play } from "../script.js"

export class Checkers extends GameEngine{
    // 0-->white,1-->black
    // The initial board 
    board=[
        [-1,1,-1,1,-1,1,-1,1],
        [1,-1,1,-1,1,-1,1,-1],
        [-1,1,-1,1,-1,1,-1,1],
        [-1,-1,-1,-1,-1,-1,-1,-1],
        [-1,-1,-1,-1,-1,-1,-1,-1],
        [0,-1,0,-1,0,-1,0,-1],
        [-1,0,-1,0,-1,0,-1,0],
        [0,-1,0,-1,0,-1,0,-1],
    ];
    turn=1
    musteat=false

    constructor(){
        super();
        this.board = [
            [-1,1,-1,1,-1,1,-1,1],
            [1,-1,1,-1,1,-1,1,-1],
            [-1,1,-1,1,-1,1,-1,1],
            [-1,-1,-1,-1,-1,-1,-1,-1],
            [-1,-1,-1,-1,-1,-1,-1,-1],
            [0,-1,0,-1,0,-1,0,-1],
            [-1,0,-1,0,-1,0,-1,0],
            [0,-1,0,-1,0,-1,0,-1],
        ];

        this.turn = 1;
        this.draw();
        this.musteat=false

    }


    draw(){

    for(i=0;i<8;i++){
        for(j=0;j<8;j++){
            D=document.getElementById(String.fromCharCode('A'.charCodeAt(0) + i).concat((j+1).toString()))
            if(board[i][j]==0){
                D.style.backgroundColor="white"
                D.style.borderColor="white"
            }
            if(board[i][j]==1){
                D.style.backgroundColor="black"
                D.style.borderColor="white"
            }  
            if(board[i][j]==-1&&(i+j)%2==1){
                
                D.style.backgroundColor="black"
                D.style.borderColor="black"
               
            } 
            
        }
    }
    

}
//calculate the board row and column from the input
//example F5 E4
function control(){
    s=document.getElementById("input").value
    if(s.length!=5){
        document.getElementById("outMessage").innerHTML="Not Valid!"
        return
    }
    input=s.split(" ")
    r1=input[0][0].charCodeAt(0)-'A'.charCodeAt(0)
    c1=input[0][1]-1
    r2=input[1][0].charCodeAt(0)-'A'.charCodeAt(0)
    c2=input[1][1]-1
    
    if(check(r1,c1,r2,c2)){
        document.getElementById("outMessage").innerHTML=""
        if(turn==0){
            turn=1 
            board[r1][c1]=-1
            board[r2][c2]=0
            if(r1-r2==2&&Math.abs(c1-c2)==2&&board[r1-1][(c1+c2)/2]==1){
                board[r1-1][(c1+c2)/2]=-1
            }
        }
        else{
            turn=0
           
            board[r1][c1]=-1
            board[r2][c2]=1
            if(r2-r1==2&&Math.abs(c1-c2)==2&&board[r1+1][(c1+c2)/2]==0){
                board[r1+1][(c1+c2)/2]=-1
            }
        }
      
        draw()
     
    }
    else{
        document.getElementById("outMessage").innerHTML="Not Valid!"
    }


}
//check if the move is valid
function check(r1,c1,r2,c2){
    if(r1<0||r1>7||c1<0||c1>7|r2<0||r2>7|c2<0||c2>7||turn==0&&board[r1][c1]!=0||turn==1&&board[r1][c1]!=1||board[r2][c2]!=-1){
        return false
    }
    if(turn==0&&r1-r2==1&&Math.abs(c1-c2)==1||turn==0&&r1-r2==2&&Math.abs(c1-c2)==2&&board[r1-1][(c1+c2)/2]==1){
        return true
    }
    if(turn==1&&r2-r1==1&&Math.abs(c1-c2)==1||turn==1&&r2-r1==2&&Math.abs(c1-c2)==2&&board[r1+1][(c1+c2)/2]==0){
        return true
    }
    return false
}


}


const game = new Checkers();
window.addEventListener('DOMContentLoaded', () => {
    document.getElementById('enter').onclick=function(){play(game,document.getElementById("input").value)}
})  
