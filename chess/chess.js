import { GameEngine } from "../script.js";
import { play } from "../script.js"



export class Chess extends GameEngine{

    turn=0
    board=[
        ['R','H','B','Q','K','B','H','R'],
        ['P','P','P','P','P','P','P','P'],
        ['s','s','s','s','s','s','s','s'],
        ['s','s','s','s','s','s','s','s'],
        ['s','s','s','s','s','s','s','s'],
        ['s','s','s','s','s','s','s','s'],
        ['p','p','p','p','p','p','p','p'],
        ['r','h','b','q','k','b','h','r'],
    ];
    M=new Map();
    Black=new Set(['R','H','B','Q','K','P'])
    White=new Set(['r','h','b','q','k','p'])

    constructor(){
        super();
        this.M.set('R','♜');
        this.M.set('H','♞');
        this.M.set('B','♝');
        this.M.set('Q','♛');
        this.M.set('K','♚');
        this.M.set('P','♟');
        this.M.set('r','♖');
        this.M.set('h','♘');
        this.M.set('b','♗');
        this.M.set('k','♔');
        this.M.set('q','♕');
        this.M.set('p','♙');
        this.M.set('s',' ');
        this.draw();
    }

    draw(){
        for(let i=0;i<8;i++){
            for(let j=0;j<8;j++){
                document.getElementById(String.fromCharCode('A'.charCodeAt(0) + i).concat((j+1).toString())).innerHTML=this.M.get(this.board[i][j])
            }
        }
    }

//calulate the board row and column
control(s){
    
    if(s.length!=5){
        document.getElementById("outMessage").innerHTML="Not Valid!"
        return
    }
    let input=s.split(" ")
    let r1=input[0][0].charCodeAt(0)-'A'.charCodeAt(0)
    let c1=input[0][1]-1
    let r2=input[1][0].charCodeAt(0)-'A'.charCodeAt(0)
    let c2=input[1][1]-1
    if(this.check(r1,c1,r2,c2)){
        document.getElementById("outMessage").innerHTML=""
        this.board[r2][c2]=this.board[r1][c1]
        this.board[r1][c1]='s'
        if(this.turn==0){
            this.turn=1
        }
        else{
            this.turn=0
        }
     this.draw()
    }
    else{
        document.getElementById("outMessage").innerHTML="Not Valid!"

    }
}
//check if the move is valid 
//example: G5 E5
check(r1,c1,r2,c2){
    document.getElementById("outMessage").innerHTML=""

    if(r1<0||r1>7||c1<0||c1>7|r2<0||r2>7|c2<0||c2>7){
        return false
    }
    if(this.turn==0&&!this.White.has(this.board[r1][c1])||this.turn==1&&!this.Black.has(this.board[r1][c1])){
        return false
    }
    if(this.turn==0&&this.White.has(this.board[r2][c2])||this.turn==1&&this.Black.has(this.board[r2][c2])){
        return false
    }
    if(this.board[r1][c1]=='R'||this.board[r1][c1]=='r'){
        if(Math.abs(r1-r2)==0||Math.abs(c1-c2)==0){
            if(r1>r2){
                for(let i=1;i+r2<r1;i++){
                    if(this.board[i+r2][c1]!='s'){
                        return false
                    }
                }
            }
            else if(r1<r2){
                for(let i=1;i+r1<r2;i++){
                    if(this.board[i+r1][c1]!='s'){
                        return false
                    }
                }
            }
            else if(c1>c2){
                for(let i=1;i+c2<c1;i++){
                    if(this.board[r1][c2+i]!='s'){
                        return false
                    }
                }
            }
            else{
                for(let i=1;i+c1<c2;i++){
                    if(this.board[r1][c1+i]!='s'){
                        return false
                    }
                }
            }
               return false
        }
    }
        if(this.board[r1][c1]=='H'||this.board[r1][c1]=='h'){
                if(Math.abs(r1-r2)==1&&Math.abs(c1-c2)==2||Math.abs(r1-r2)==2&&Math.abs(c1-c2)==1){
                    return true
                }
                return false
        }
        if(this.board[r1][c1]=='B'||this.board[r1][c1]=='b'){
            if(Math.abs(r1-r2)==Math.abs(c1-c2)&&Math.abs(r1-r2)!=0){
                if(r1<r2){
                    if(c1<c2){
                        for(let i=1;i+r1<r2;i++){
                            if(this.board[i+r1][i+c1]!='s'){
                                console.log("2")
                            }
                        }
                    }
                    if(c2<c1){
                        for(let i=1;i+r1<r2;i++){
                            if(this.board[i+r1][c1-i]!='s'){
                                return false
                            }
                        }
                    }
                }
                if(r2<r1){
    
                  if(c1<c2){
                      for(let i=1;r1-i>r2;i++){
                        if(this.board[r1-i][c1+i]!='s'){
                            console.log("4")
                        }
                      }
                    }
                    if(c2<c1){
                        for(let i=1;r1-i>r2;i++){
                            if(this.board[r1-i][c1-i]!='s'){
                                return false
                            } 
                        }
                    }
                }
                return true
            }
            return false
        }
        if(this.board[r1][c1]=='K'||this.board[r1][c1]=='k'){
            if(Math.abs(r1-r2)==1||Math.abs(c1-c2)==1){
                return true
            }
            return false
        }
        if(this.board[r1][c1]=='P'){
            if(r2-r1==1&&c1==c2&&this.board[r2][c2]=='s'){
                return true
            }
            if(r1==1&&r2==3&&c1==c2&&this.board[r1+1][c1]=='s'&&this.board[r2][c2]=='s'){
                return true
            }
            if(Math.abs(c1-c2)==1&&r2-r1==1&&this.White.has(this.board[r2][c2])){
                return true
            }
            return false
        }
        if(this.board[r1][c1]=='p'){
            if(r1-r2==1&&c1==c2&&this.board[r2][c2]=='s'){
                return true
            }
            if(r1==6&&r2==4&&c1==c2&&this.board[r2+1][c1]=='s'&&this.board[r2][c2]=='s'){
                return true
            }
            if(Math.abs(c1-c2)==1&&r1-r2==1&&this.Black.has(this.board[r2][c2])){
                return true
            }
            return false
        }
        if(this.board[r1][c1]=='Q'||this.board[r1][c1]=='q'){
            if(Math.abs(r1-r2)==Math.abs(c1-c2)&&Math.abs(r1-r2)!=0){
                if(r1<r2){
                    if(c1<c2){
                        for(let i=1;i+r1<r2;i++){
                            if(this.board[i+r1][i+c1]!='s'){
                                return false
                            }
                        }
                    }
                    if(c2<c1){
                        for(let i=1;i+r1<r2;i++){
                            if(this.board[i+r1][c1-i]!='s'){
                                return false
                            }
                        }
                    }
                }
                if(r2<r1){
    
                  if(c1<c2){
                      for(let i=1;r1-i>r2;i++){
                        if(this.board[r1-i][c1+i]!='s'){
                            return false
                        }
                      }
                    }
                    if(c2<c1){
                        for(let i=1;r1-i>r2;i++){
                            if(this.board[r1-i][c1-i]!='s'){
                                return false
                            }
                        }
                    }
                }
                return true
            }
            else if(Math.abs(r1-r2)==0||Math.abs(c1-c2)==0){
                if(r1>r2){
                    for(let i=1;i+r2<r1;i++){
                        if(this.board[i+r2][c1]!='s'){
                            return false
                        }
                    }
                }
                else if(r1<r2){
                    for(let i=1;i+r1<r2;i++){
                        if(this.board[i+r1][c1]!='s'){
                            return false
                        }
                    }
                }
                else if(c1>c2){
                    for(let i=1;i+c2<c1;i++){
                        if(this.board[r1][c2+i]!='s'){
                            return false
                        }
                    }
                }
                else{
                    for(let i=1;i+c1<c2;i++){
                        if(this.board[r1][c1+i]!='s'){
                            return false
                        }
                    }
                }
                return true
            }
            return true
        }
        else{
            return false
        }

    }


}

const game = new Chess();
window.addEventListener('DOMContentLoaded', () => {
    
    document.getElementById('enter').onclick=function(){
        play(game,document.getElementById("input").value);
    }
})  