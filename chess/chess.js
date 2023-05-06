import { GameEngine } from "../script.js";



export class Chess extends GameEngine{
    

    constructor(){
        
        super();

        
    }


    Initialize(){


        let turn=0
        let board=[
            ['R','H','B','Q','K','B','H','R'],
            ['P','P','P','P','P','P','P','P'],
            ['s','s','s','s','s','s','s','s'],
            ['s','s','s','s','s','s','s','s'],
            ['s','s','s','s','s','s','s','s'],
            ['s','s','s','s','s','s','s','s'],
            ['p','p','p','p','p','p','p','p'],
            ['r','h','b','q','k','b','h','r'],
        ];

        return [board,true,turn];
    }

    draw(board){

        let M=new Map();

        M.set('R','♜');
        M.set('H','♞');
        M.set('B','♝');
        M.set('Q','♛');
        M.set('K','♚');
        M.set('P','♟');
        M.set('r','♖');
        M.set('h','♘'); 
        M.set('b','♗');
        M.set('k','♔');
        M.set('q','♕');
        M.set('p','♙');
        M.set('s',' ');

        for(let i=0;i<8;i++){
            for(let j=0;j<8;j++){
                document.getElementById(String.fromCharCode('A'.charCodeAt(0) + i).concat((j+1).toString())).innerHTML=M.get(board[i][j]);
            }
        }
    }

//calulate the board row and column
control(gameState,s){
    let board = gameState[0];
    let turn = gameState[2];
    if(s.length!=5){
        return [board,false,turn];
    }
    let input=s.split(" ")
    let r1=input[0][0].charCodeAt(0)-'A'.charCodeAt(0)
    let c1=input[0][1]-1
    let r2=input[1][0].charCodeAt(0)-'A'.charCodeAt(0)
    let c2=input[1][1]-1
    if(this.check(board,turn,r1,c1,r2,c2)){
        board[r2][c2]=board[r1][c1]
        board[r1][c1]='s'
        if(turn==0){
            turn=1
        }
        else{
            turn=0
        }
        return [board,true,turn];
    }
    else{
        return [board,false,turn]

    }
}


check(board,turn,r1,c1,r2,c2){

    let Black=new Set(['R','H','B','Q','K','P'])
    let White=new Set(['r','h','b','q','k','p'])

    if(r1<0||r1>7||c1<0||c1>7|r2<0||r2>7|c2<0||c2>7){
        return false
    }
    if(turn==0&&!White.has(board[r1][c1])||turn==1&&!Black.has(board[r1][c1])){
        return false
    }
    if(turn==0&&White.has(board[r2][c2])||turn==1&&Black.has(board[r2][c2])){
        return false
    }
    if(board[r1][c1]=='R'||board[r1][c1]=='r'){
        if(Math.abs(r1-r2)==0||Math.abs(c1-c2)==0){
            if(r1>r2){
                for(let i=1;i+r2<r1;i++){
                    if(board[i+r2][c1]!='s'){
                        return false
                    }
                }
            }
            else if(r1<r2){
                for(let i=1;i+r1<r2;i++){
                    if(board[i+r1][c1]!='s'){
                        return false
                    }
                }
            }
            else if(c1>c2){
                for(let i=1;i+c2<c1;i++){
                    if(board[r1][c2+i]!='s'){
                        return false
                    }
                }
            }
            else{
                for(let i=1;i+c1<c2;i++){
                    if(board[r1][c1+i]!='s'){
                        return false
                    }
                }
            }
               return false
        }
    }
        if(board[r1][c1]=='H'||board[r1][c1]=='h'){
                if(Math.abs(r1-r2)==1&&Math.abs(c1-c2)==2||Math.abs(r1-r2)==2&&Math.abs(c1-c2)==1){
                    return true
                }
                return false
        }
        if(board[r1][c1]=='B'||board[r1][c1]=='b'){
            if(Math.abs(r1-r2)==Math.abs(c1-c2)&&Math.abs(r1-r2)!=0){
                if(r1<r2){
                    if(c1<c2){
                        for(let i=1;i+r1<r2;i++){
                            if(board[i+r1][i+c1]!='s'){
                                console.log("2")
                            }
                        }
                    }
                    if(c2<c1){
                        for(let i=1;i+r1<r2;i++){
                            if(board[i+r1][c1-i]!='s'){
                                return false
                            }
                        }
                    }
                }
                if(r2<r1){
    
                  if(c1<c2){
                      for(let i=1;r1-i>r2;i++){
                        if(board[r1-i][c1+i]!='s'){
                            console.log("4")
                        }
                      }
                    }
                    if(c2<c1){
                        for(let i=1;r1-i>r2;i++){
                            if(board[r1-i][c1-i]!='s'){
                                return false
                            } 
                        }
                    }
                }
                return true
            }
            return false
        }
        if(board[r1][c1]=='K'||board[r1][c1]=='k'){
            if(Math.abs(r1-r2)==1||Math.abs(c1-c2)==1){
                return true
            }
            return false
        }
        if(board[r1][c1]=='P'){
            if(r2-r1==1&&c1==c2&&board[r2][c2]=='s'){
                return true
            }
            if(r1==1&&r2==3&&c1==c2&&board[r1+1][c1]=='s'&&board[r2][c2]=='s'){
                return true
            }
            if(Math.abs(c1-c2)==1&&r2-r1==1&&White.has(board[r2][c2])){
                return true
            }
            return false
        }
        if(board[r1][c1]=='p'){
            if(r1-r2==1&&c1==c2&&board[r2][c2]=='s'){
                return true
            }
            if(r1==6&&r2==4&&c1==c2&&board[r2+1][c1]=='s'&&board[r2][c2]=='s'){
                return true
            }
            if(Math.abs(c1-c2)==1&&r1-r2==1&&Black.has(board[r2][c2])){
                return true
            }
            return false
        }
        if(board[r1][c1]=='Q'||board[r1][c1]=='q'){
            if(Math.abs(r1-r2)==Math.abs(c1-c2)&&Math.abs(r1-r2)!=0){
                if(r1<r2){
                    if(c1<c2){
                        for(let i=1;i+r1<r2;i++){
                            if(board[i+r1][i+c1]!='s'){
                                return false
                            }
                        }
                    }
                    if(c2<c1){
                        for(let i=1;i+r1<r2;i++){
                            if(board[i+r1][c1-i]!='s'){
                                return false
                            }
                        }
                    }
                }
                if(r2<r1){
    
                  if(c1<c2){
                      for(let i=1;r1-i>r2;i++){
                        if(board[r1-i][c1+i]!='s'){
                            return false
                        }
                      }
                    }
                    if(c2<c1){
                        for(let i=1;r1-i>r2;i++){
                            if(board[r1-i][c1-i]!='s'){
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
                        if(board[i+r2][c1]!='s'){
                            return false
                        }
                    }
                }
                else if(r1<r2){
                    for(let i=1;i+r1<r2;i++){
                        if(board[i+r1][c1]!='s'){
                            return false
                        }
                    }
                }
                else if(c1>c2){
                    for(let i=1;i+c2<c1;i++){
                        if(board[r1][c2+i]!='s'){
                            return false
                        }
                    }
                }
                else{
                    for(let i=1;i+c1<c2;i++){
                        if(board[r1][c1+i]!='s'){
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
