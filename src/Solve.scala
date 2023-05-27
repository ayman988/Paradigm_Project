import java.awt.*
import java.util.concurrent.locks.Condition
import javax.swing.*
import scala.collection.mutable.{ArrayStack, ListBuffer}
import scala.util.Random
import scala.collection.immutable._
import java.io._
import scala.io.Source
import org.jpl7._
import scala.util.Using



def solveSudoku(fileWriter: PrintWriter, board:Array[Array[Int]],base:Array[Array[Int]], i: Int):Array[Any]= {
  File("result.txt").delete()
  fileStart(fileWriter, i)
  rowStart(fileWriter, i)
  writeOneCell(fileWriter, board(i / 9)(i % 9))
  rowFormat(fileWriter, i % 9 == 8)
  rowEnd(fileWriter, i % 9 == 8 && i != 80)
  fileEnd(fileWriter, i == 80)
  i match
    case 80 => closeFile(fileWriter,board,base)
    case _ => solveSudoku(fileWriter, board,base,i + 1)
}
def closeFile(fileWriter: PrintWriter,board:Array[Array[Int]],base:Array[Array[Int]]):Array[Any] = {
  fileWriter.write("sudoku(X),maplist(labeling([ff]),X),tell('result.txt'),write(X),told.")
  fileWriter.close()
  new Query("consult('board.pl')").oneSolution()
  new Query("solve").oneSolution()
  checkExist(board,base)
}
def checkExist(board:Array[Array[Int]],base:Array[Array[Int]]):Array[Any] = File("result.txt").exists() match {
  case true => copyResult(fileToString("result.txt"), 0, 0, board,base)
  case false => Array(board,base, false)
}
def fileToString(s: String): String = {
  Using(Source.fromFile(s)) { source => source.mkString }.get
}
def fileToList(s: String): String = {
  Using(Source.fromFile(s)) { source => source.getLines().map(line => line + "\n").mkString }.get
}
def copyResult(s: String, c: Int, i: Int, board: Array[Array[Int]],base:Array[Array[Int]]):Array[Any] = i match {
  case 81 => Array(board,base, true)
  case _ => checkCharacter(s, c, i, board,base)
}
def checkCharacter(s: String, c: Int, i: Int, board: Array[Array[Int]],base:Array[Array[Int]]): Array[Any] = s.charAt(c) match {
  case '[' => copyResult(s, c + 1, i, board,base)
  case ']' => copyResult(s, c + 1, i, board,base)
  case ',' => copyResult(s, c + 1, i, board,base)
  case _ => addResult(s, c, i, board,base)
}
def addResult(s: String, c: Int, i: Int, board: Array[Array[Int]],base:Array[Array[Int]]):Array[Any] = {
  board(i / 9)(i % 9) = s.charAt(c).toInt - '0'.toInt
  copyResult(s, c + 1, i + 1, board,base)
}
def fileStart(fileWriter: PrintWriter, i: Int): Unit = i match {
  case 0 => fileWriter.write(fileToList("sudoku.txt") + "[")
  case _ =>
}
def writeOneCell(fileWriter: PrintWriter, x: Int): Unit = x match {
  case 0 => fileWriter.write("_")
  case _ => fileWriter.write(x.toString)
}
def rowFormat(fileWriter: PrintWriter, condition: Boolean): Unit = condition match {
  case true => fileWriter.write("]")
  case false => fileWriter.write(",")
}
def rowStart(fileWriter: PrintWriter, i: Int): Unit = i % 9 match {
  case 0 => fileWriter.write("[")
  case _ =>
}
def rowEnd(fileWriter: PrintWriter, condition: Boolean): Unit = condition match {
  case true => fileWriter.write(",\n")
  case false =>
}
def fileEnd(fileWriter: PrintWriter, condition: Boolean): Unit = condition match {
  case true => fileWriter.write("],\n")
  case false =>
}


def solveQueens(file: PrintWriter,board:Array[Array[Int]]): Array[Any] = {
  File("result.txt").delete()
  file.write(fileToList("queens.txt") + "[")
  searchColumns(0, board, file)
  file.write("n_queens(X),labeling([ff],X),tell('result.txt'),write(X),told.")
  file.close()
  new Query("consult('board.pl')").oneSolution()
  new Query("solve").oneSolution()
  checkExist(board)
}
def checkExist(board:Array[Array[Int]]): Array[Any] = File("result.txt").exists() match {
  case true => copyToBoard(fileToString("result.txt"), 0, 0, board)
  case false => Array(board, null, false)
}
def searchColumns(c: Int, board: Array[Array[Int]], file: PrintWriter): Unit = c match {
  case 8 =>
  case _ => searchBoard(c, board, file)
}
def searchCell(c: Int, i: Int, board: Array[Array[Int]], condition: Boolean): Int = condition match {
  case false => i + 1
  case true => searchCell(c, i + 1, board, i < 7 && board(i + 1)(c) == 0)
}
def writeCell(file: PrintWriter, x: Int): Unit = x match {
  case 9 => file.write("_")
  case _ => file.write(x.toString)
}
def searchBoard(c: Int, board: Array[Array[Int]], file: PrintWriter): Unit = {
  writeCell(file, searchCell(c, 0, board, board(0)(c) == 0))
  checkEnd(c, file)
  searchColumns(c + 1, board, file)
}
def checkEnd(c: Int, file: PrintWriter): Unit = c match {
  case 7 => file.write("]," + "\n")
  case _ => file.write(",")
}

def copyToBoard(s: String, c: Int, i: Int, board: Array[Array[Int]]): Array[Any] = i match {
  case 8 => Array(board, null, true)
  case _ => writeOneCell(s, c, i, board)
}
def writeOneCell(s: String, c: Int, i: Int, board: Array[Array[Int]]): Array[Any] = s.charAt(c) match {
  case '[' => copyToBoard(s, c + 1, i, board)
  case ']' => copyToBoard(s, c + 1, i, board)
  case ',' => copyToBoard(s, c + 1, i, board)
  case _ => writeToBoard(s, c, i, board)
}
def writeToBoard(s: String, c: Int, i: Int, board: Array[Array[Int]]): Array[Any] = {
  board(s.charAt(c).toInt - '0'.toInt - 1)(i) = 1
  copyToBoard(s, c + 1, i + 1, board)
}
def checkSolvable(input1:Any,input2:Any):Boolean={
  input2 == null && !checkFullQueens(0, 0, input1.asInstanceOf[Array[Array[Int]]]) ||
    input2.isInstanceOf[Array[Array[Int]]] && !checkFullSudoku(0, input1.asInstanceOf[Array[Array[Int]]])
}
def checkFullSudoku(i: Int, board: Array[Array[Int]]): Boolean = i match {
  case 81 => true
  case _ => board(i / 9)(i % 9) != 0 && checkFullSudoku(i + 1, board)
}

def checkFullQueens(sum: Int, i: Int, board: Array[Array[Int]]): Boolean = i match {
  case 64 => sum == 8
  case _ => checkFullQueens(sum + board(i / 8)(i % 8), i + 1, board)
}
def initQuery(file: PrintWriter):Unit={
  file.write(fileToList("libraries.txt"))
  file.close()
  new Query("consult('board.pl')").oneSolution()


}