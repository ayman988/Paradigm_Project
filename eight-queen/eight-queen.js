import { GameEngine } from "../script.js";
import { play } from "../script.js"


export class Eight_Queens extends GameEngine{

    //The initial board is all empty
    //0-->empty,1-->queen
    board=[
        [0,0,0,0,0,0,0,0],
        [0,0,0,0,0,0,0,0],
        [0,0,0,0,0,0,0,0],
        [0,0,0,0,0,0,0,0],
        [0,0,0,0,0,0,0,0],
        [0,0,0,0,0,0,0,0],
        [0,0,0,0,0,0,0,0],
        [0,0,0,0,0,0,0,0],
    ];

    constructor(){

        super()
        this.board = [
            [0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0],
        ];
        this.draw();

    }

    control(s){
        if(s.length!=2){
          document.getElementById("outMessage").innerHTML="Not Valid Input !"
          return
        }
        let r=s[0].charCodeAt(0)-'A'.charCodeAt(0)
        let c=s[1]-1
        if(this.check(r,c)){
          document.getElementById("outMessage").innerHTML=""
          this.board[r][c]=1
          this.draw()
        }
        else{
          document.getElementById("outMessage").innerHTML="it will scare a queen!"
        }
      }

      check(r,c){
        if(this.board[r][c]==1){
            return false
        }
        if(r>'H'.charCodeAt(0)-'A'.charCodeAt(0) || r<'A'.charCodeAt(0)-'A'.charCodeAt(0) || c<0 || c>7){
            return false;
        }
        for(let i=0;r+i<8;i++){
            if(this.board[r+i][c]==1){
                return false
            }
        }
        for(let i=0;r-i>=0;i++){
            if(this.board[r-i][c]==1){
                return false
            }
        }
        for(let i=0;i+c<8;i++){
            if(this.board[r][c+i]==1){
                return false
            }
        }
        for(let i=0;c-i>=0;i++){
            if(this.board[r][c-i]==1){
                return false
            }
        }
        let i=0;
        let j=0;
       while(r+i<8&&c+j<8){
        if(this.board[r+i][c+j]==1){
            return false
        }
        i=i+1;
        j=j+1;
       }
       i=0;j=0;
       while(r-i>=0&&c+j<8){
        if(this.board[r-i][c+j]==1){
            return false
        }
        i=i+1;j=j+1;
       }
       i=0;j=0;
       while(r+i<8&&c-j>=0){
        if(this.board[r+i][c-j]==1){
            return false
        }
        i=i+1;j=j+1;
       }
       i=0;j=0;
       while(r-i>=0&&c-j>=0){
        if(this.board[r-i][c-j]==1){
            return false
        }
        i=i+1;j=j+1;
       }
       return true
    }

    draw(){
        for(let i=0;i<8;i++){
            for(let j=0;j<8;j++){
                let D=document.getElementById((String.fromCharCode('A'.charCodeAt(0)+i).concat(j+1)))
                if(this.board[i][j]==1){
                    D.innerHTML="♛"
                }
            }
        }
    }
    

}