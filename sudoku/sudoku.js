import { GameEngine } from "../script.js";


export class Sudoku extends GameEngine{

    

    constructor(){

        super();

    }

    Initialize(){


        let base = this.generateSudokuBoard();

        let board=[
            [0,0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0,0]
        ];

        for(let i=0; i<9; i++){
            for(let j=0; j<9; j++){
                if(base[i][j]!=0){
                    board[i][j] = 11;
                }
                else{
                    board[i][j] = 0;
                }
            }
        }

        for(let i=0;i<9;i++){
            for(let j=0;j<9;j++){
                let D=document.getElementById((String.fromCharCode('A'.charCodeAt(0)+i).concat(j+1)))
                if(base[i][j]!=0){
                    D.innerHTML=base[i][j]
                    D.style.color="black"
                }
            }
        }


        return [board,true,"non",base]
    }

     generateSudokuBoard() {
        const board = [];
        const size = 9;
      
        // Create an empty board
        for (let i = 0; i < size; i++) {
          board.push(new Array(size).fill(0));
        }
      
        // Fill the board with valid numbers
        this.fillBoard(board, 0, 0);


        for (let k = 0; k < 25; k++) {
            let i = Math.floor(Math.random() * 8);
            let j = Math.floor(Math.random() * 8);

            board[i][j] = 0;
        }
        return board;
      }
      
       fillBoard(board, row, col) {
        const size = 9;
        let nextRow = row;
        let nextCol = col + 1;
        if (nextCol >= size) {
          nextRow++;
          nextCol = 0;
        }
        if (row >= size) {
          return true; // We've filled the entire board!
        }
      
        const shuffledValues = this.shuffle([1, 2, 3, 4, 5, 6, 7, 8, 9]);
      
        for (const value of shuffledValues) {
          if (this.isValid(board, row, col, value)) {
            board[row][col] = value;
            if (this.fillBoard(board, nextRow, nextCol)) {
              return true;
            }
            board[row][col] = 0; // Backtrack
          }
        }
      
        return false; // No valid number found
      }
      
       isValid(board, row, col, value) {
        // Check the row and column
        for (let i = 0; i < 9; i++) {
          if (board[row][i] === value || board[i][col] === value) {
            return false;
          }
        }
      
        // Check the 3x3 box
        const boxRow = Math.floor(row / 3) * 3;
        const boxCol = Math.floor(col / 3) * 3;
        for (let i = boxRow; i < boxRow + 3; i++) {
          for (let j = boxCol; j < boxCol + 3; j++) {
            if (board[i][j] === value) {
              return false;
            }
          }
        }
      
        return true;
      }
      
       shuffle(array) {
        for (let i = array.length - 1; i > 0; i--) {
          const j = Math.floor(Math.random() * (i + 1));
          [array[i], array[j]] = [array[j], array[i]];
        }
        return array;
      }
      


    

    // generateSudokuBoard() {
    //     const board = [];
      
    //     // Create an empty 9x9 board
    //     for (let i = 0; i < 9; i++) {
    //       board[i] = [];
    //       for (let j = 0; j < 9; j++) {
    //         board[i][j] = 0;
    //       }
    //     }
      
    //     // Fill the board with random numbers
    //     for (let k = 0; k < 40; k++) {
    //         let i = Math.floor(Math.random() * 8);
    //         let j = Math.floor(Math.random() * 8);
    //         let num = 0;
    //         while (num === 0 || !this.isValidMove(board, i, j, num)) {
    //           num = Math.floor(Math.random() * 9) + 1;
    //         }
    //         board[i][j] = num;
          
    //     }
      
    //     return board;
    // }

    // isValidMove(board, row, col, num) {
    //     // Check row
    //     for (let i = 0; i < 9; i++) {
    //       if (board[row][i] === num) {
    //         return false;
    //       }
    //     }
      
    //     // Check column
    //     for (let i = 0; i < 9; i++) {
    //       if (board[i][col] === num) {
    //         return false;
    //       }
    //     }
      
    //     // Check box
    //     const boxRow = Math.floor(row / 3) * 3;
    //     const boxCol = Math.floor(col / 3) * 3;
    //     for (let i = boxRow; i < boxRow + 3; i++) {
    //       for (let j = boxCol; j < boxCol + 3; j++) {
    //         if (board[i][j] === num) {
    //           return false;
    //         }
    //       }
    //     }
      
    //     return true;
    // }

    control(gameState,s){

        let board = gameState[0];
        let base = gameState[3];


        if(s.length!=4||s.indexOf(" ")!=2){
          return [board,false,"non",base]
      }
        let input=s.split(" ")
        let r=input[0][0].charCodeAt(0)-'A'.charCodeAt(0)
        let c=input[0][1]-1
        let x=input[1]
        if(this.check(board,base,r,c,x)){
          board[r][c]=x
          return [board,true,"non",base];
        }
        else{
          return [board,false,"non",base];
        }
      }

      check(board,base,r,c,x){
        if(x<0||x>9){
            return false
        }
        if(base[r][c]!=0){
            return false
        }
         for(let i=0;i<9;i++){
            if(i==c){
                continue
            }
            if(base[r][i]==x){
                return false
            }
         }
         for(let i=0;i<9;i++){
            if(i==r){
                continue
            }
            if(base[i][c]==x){
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
                if(base[sr+i][sc+j]==x){
                    return false
                }
            }
         }
         return true
    }

    draw(board){

        for(let i=0;i<9;i++){
            for(let j=0;j<9;j++){
                let D=document.getElementById((String.fromCharCode('A'.charCodeAt(0)+i).concat(j+1)))
                if(board[i][j]==11){
                    continue;
                }
                else if(board[i][j]==0){
                    D.innerHTML=" "
                }
                
                else{
                    D.innerHTML=board[i][j]
                    D.style.color="blue"
                }
            }
        }
    }
}

const game = new Sudoku();


