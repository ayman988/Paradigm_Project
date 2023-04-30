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

        for(let i=0;i<8;i++){
            for(let j=0;j<8;j++){
                let D=document.getElementById(String.fromCharCode('A'.charCodeAt(0) + i).concat((j+1).toString()))
                if(this.board[i][j]==0){
                    D.style.backgroundColor="white"
                    D.style.borderColor="white"
                }
                if(this.board[i][j]==1){
                    D.style.backgroundColor="black"
                    D.style.borderColor="white"
                }  
                if(this.board[i][j]==-1&&(i+j)%2==1){
                    
                    D.style.backgroundColor="black"
                    D.style.borderColor="black"
                   
                } 
                
            }
        }
        
    
    }

    control(s){
        if(s.length!=5||s.indexOf(" ")!=2){
            document.getElementById("outMessage").innerHTML="Not Valid!"
            return
        }
        let input=s.split(" ")
        let r1=input[0][0].charCodeAt(0)-'A'.charCodeAt(0)
        let c1=input[0][1]-1
        let r2=input[1][0].charCodeAt(0)-'A'.charCodeAt(0)
        let c2=input[1][1]-1
        this.MustEat()
        if(this.check(r1,c1,r2,c2)){
            document.getElementById("outMessage").innerHTML=""
            if(this.turn==0){
              //  this.turn=1
                this.board[r1][c1]=-1
                this.board[r2][c2]=0
                if(r1-r2==2&&Math.abs(c1-c2)==2&&this.board[r1-1][(c1+c2)/2]==1){
                    this.board[r1-1][(c1+c2)/2]=-1
                    this.MustEat()
                }
            }
            else{
               // this.turn=0
                this.board[r1][c1]=-1
                this.board[r2][c2]=1
                if(r2-r1==2&&Math.abs(c1-c2)==2&&this.board[r1+1][(c1+c2)/2]==0){
                    this.board[r1+1][(c1+c2)/2]=-1
                    this.MustEat()
                }
            }

            if(this.musteat==false){
                this.turn=Math.abs(this.turn-1)
                if(this.turn==0){
                    document.getElementById("Turn").innerHTML="White's Turn"
                }
                else{
                    document.getElementById("Turn").innerHTML="Black's Turn"
                }
            }
          
            this.draw()
         
        }
        else{
            document.getElementById("outMessage").innerHTML="Not Valid!"
        }    
    }

    check(r1,c1,r2,c2){
        if(r1<0||r1>7||c1<0||c1>7|r2<0||r2>7|c2<0||c2>7||this.turn==0&&this.board[r1][c1]!=0||this.turn==1&&this.board[r1][c1]!=1||this.board[r2][c2]!=-1){
            return false
        }
        if(this.turn==0&&r1-r2==1&&Math.abs(c1-c2)==1&&this.musteat==false||this.turn==0&&r1-r2==2&&Math.abs(c1-c2)==2&&this.board[r1-1][(c1+c2)/2]==1){
            return true
        }
        if(this.turn==1&&r2-r1==1&&Math.abs(c1-c2)==1&&this.musteat==false||this.turn==1&&r2-r1==2&&Math.abs(c1-c2)==2&&this.board[r1+1][(c1+c2)/2]==0){
            return true
        }
        return false
    }
    MustEat(){
        for(let i=0;i<8;i++){
            for(let j=0;j<8;j++){
                if(this.board[i][j]==this.turn){
                    if(this.turn==0&&i-2>-1&&j-2>-1&&this.board[i-1][j-1]==1&&this.board[i-2][j-2]==-1||this.turn==0&&i-2>-1&&j+2<8&&this.board[i-1][j+1]==1&&this.board[i-2][j+2]==-1||
                        this.turn==1&&i+2<8&&j-2>-1&&this.board[i+1][j-1]==0&&this.board[i+2][j-2]==-1||this.turn==1&&i+2<8&&j+2<8&&this.board[i+1][j+1]==0&&this.board[i+2][j+2]==-1){
                        this.musteat=true
                        return true
                       }
                }
            }
        }
        this.musteat=false
        return false
    }

}


const game = new Checkers();
window.addEventListener('DOMContentLoaded', () => {
    document.getElementById('enter').onclick=function(){play(game,document.getElementById("input").value)}
    window.addEventListener("keypress",function(event){
        if (event.key === "Enter"){
            play(game,document.getElementById('input').value)
        }
    })
})  
