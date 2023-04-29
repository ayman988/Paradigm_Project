    
	window.addEventListener('DOMContentLoaded', () => {
    document.getElementById("sudoku").onclick=function(){OpenSudoku()}	
    document.getElementById("Tic-Tac-Toe").onclick=function(){OpenTicTacToe()}	
    document.getElementById("eight-queen").onclick=function(){OpenEightQueen()}	
    document.getElementById("connect-4").onclick=function(){OpenConnectFour()}	
    document.getElementById("checkers").onclick=function(){OpenCheckers()}	
    document.getElementById("chess").onclick=function(){OpenChess()}	
    
    })
    function OpenSudoku(){
        window.open('./sudoku/sudoku.html','_blank')
    }
    function OpenTicTacToe(){
        window.open('./Tic-Tac-Toe/Tic-Tac-Toe.html','_blank')
    }
    function OpenEightQueen(){
        window.open('./eight-queen/eight-queen.html','_blank')
    }
    function OpenConnectFour(){
        window.open('./connect-4/connect-4.html','_blank')
    
    }
    function OpenCheckers(){
        window.open('./checkers/checkers.html','_blank')
    }
    function OpenChess(){
        window.open('./chess/chess.html','_blank')
    }
    
    
    export class GameEngine {
        
        board
        constructor() {
            if (this.constructor === GameEngine) {
              throw new Error("Abstract classes can't be instantiated");
            }
        }
        draw() {
            throw new Error("Method 'draw()' must be implemented.");
        }
        
        control() {
            throw new Error("Method 'control()' must be implemented.");
        }
    
    }  
    
    export function play(game,s){
        game.control(s);
        // game.draw();
    }





