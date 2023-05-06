import { GameEngine } from "../script.js";


export class Eight_Queens extends GameEngine{

    
    

    constructor(){

        super()

    }

    Initialize(){

        let board=[
            [0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0],
        ];

        return [board,true,"non"];

    }

    control(gameState,s){
        let board = gameState[0];

        //to delete element
        if(s.length==4 && s[0]=='D'){

            let r=s[2].charCodeAt(0)-'A'.charCodeAt(0)
            let c=s[3]-1

            if(r>'H'.charCodeAt(0)-'A'.charCodeAt(0) || r<'A'.charCodeAt(0)-'A'.charCodeAt(0) || c<0 || c>7){
                return [board,false,"non"];
            }
            else{
                console.log(r)
                console.log(c)
                board[r][c] = 0;
                return [board,true,"non"];
            }
        }

        if(s.length!=2){
            return [board,false,"non"];
        }
        let r=s[0].charCodeAt(0)-'A'.charCodeAt(0)
        let c=s[1]-1
        if(this.check(board,r,c)){
          board[r][c]=1
          return [board,true,"non"];
        }
        else{
            return [board,false,"non"];
        }
      }

      check(board,r,c){
        if(board[r][c]==1){
            return false
        }
        if(r>'H'.charCodeAt(0)-'A'.charCodeAt(0) || r<'A'.charCodeAt(0)-'A'.charCodeAt(0) || c<0 || c>7){
            return false;
        }
        for(let i=0;r+i<8;i++){
            if(board[r+i][c]==1){
                return false
            }
        }
        for(let i=0;r-i>=0;i++){
            if(board[r-i][c]==1){
                return false
            }
        }
        for(let i=0;i+c<8;i++){
            if(board[r][c+i]==1){
                return false
            }
        }
        for(let i=0;c-i>=0;i++){
            if(board[r][c-i]==1){
                return false
            }
        }
        let i=0;
        let j=0;
       while(r+i<8&&c+j<8){
        if(board[r+i][c+j]==1){
            return false
        }
        i=i+1;
        j=j+1;
       }
       i=0;j=0;
       while(r-i>=0&&c+j<8){
        if(board[r-i][c+j]==1){
            return false
        }
        i=i+1;j=j+1;
       }
       i=0;j=0;
       while(r+i<8&&c-j>=0){
        if(board[r+i][c-j]==1){
            return false
        }
        i=i+1;j=j+1;
       }
       i=0;j=0;
       while(r-i>=0&&c-j>=0){
        if(board[r-i][c-j]==1){
            return false
        }
        i=i+1;j=j+1;
       }
       return true
    }

    draw(board){
        for(let i=0;i<8;i++){
            for(let j=0;j<8;j++){
                let D=document.getElementById((String.fromCharCode('A'.charCodeAt(0)+i).concat(j+1)))
                if(board[i][j]==1){
                    D.innerHTML="♛"
                }
                else{
                    D.innerHTML=" "
                }
            }
        }
    }
    

}

const game = new Eight_Queens();
