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
  def Tic_Tac_Toe_control(st: String, input: Array[Any]): Array[Any] = {

    def start(st: String): Array[Any] = st match {
      case null => Array(Array(Array('s', 's', 's'),
        Array('s', 's', 's'),
        Array('s', 's', 's')), 0, true)
      case _ => checkLength(st.length)
    }

    def checkLength(L: Int): Array[Any] = L match {
      case 2 => checkBounds(st.charAt(0).toInt - 'A'.toInt, st.charAt(1).toInt - '0'.toInt - 1,input(0).asInstanceOf[Array[Array[Char]]])
      case _ => Array(input(0).asInstanceOf[Array[Array[Char]]], input(1).asInstanceOf[Int], false)
    }

    def checkBounds(r: Int, c: Int,board:Array[Array[Char]]): Array[Any] = {
      return bounds(r,c,r < 0 || r > 2 || c < 0 || c > 2 || board(r)(c) != 's')
    }
    def bounds(r: Int, c: Int,B:Boolean): Array[Any] = B match{
      case true => Array(input(0).asInstanceOf[Array[Array[Char]]], input(1).asInstanceOf[Int], false)
      case false => Assignment(r,c,input(0).asInstanceOf[Array[Array[Char]]],input(1).asInstanceOf[Int])
    }
    def Assignment(r: Int, c: Int,board:Array[Array[Char]],turn:Int):Array[Any]={
      update(r,c,turn,board)
      return  Array(board,(turn-1).abs,true)
    }
    def update(r:Int,c:Int,turn:Int,board:Array[Array[Char]]):Unit= turn match{
      case 0 =>  board(r)(c) = 'x'
      case 1 =>  board(r)(c) = 'o'
    }
    return start(st)
  }
  def Tic_Tac_Toe_draw(input: Array[Any]): Any = {

def END(frame:JFrame):Any={
  frame.dispose()
  return null
}
def start(L:Int):Any= L match{
  case 4 => END(input(0).asInstanceOf[JFrame])
  case 1 =>createFrame()
  case _ =>preUpdate(input(0).asInstanceOf[JFrame],input(1).asInstanceOf[Array[Array[Char]]])
}
 def createFrame():Any={
   var frame = new JFrame()
   frame.setBounds(15, 0, 1500, 800)
   frame.getContentPane().setLayout(null)
   val A = new JLabel("A")
   val B = new JLabel("B")
   val C = new JLabel("C")
   A.setBounds(350, 130, 100, 100)
   A.setFont(new Font("Tahoma", Font.ROMAN_BASELINE, 50))
   A.setForeground(Color.ORANGE)
   B.setBounds(350, 330, 100, 100)
   B.setFont(new Font("Tahoma", Font.ROMAN_BASELINE, 50))
   B.setForeground(Color.ORANGE)
   C.setBounds(350, 530, 100, 100)
   C.setFont(new Font("Tahoma", Font.ROMAN_BASELINE, 50))
   C.setForeground(Color.ORANGE)
   frame.getContentPane().add(A)
   frame.getContentPane().add(B)
   frame.getContentPane().add(C)
   val N1 = new JLabel("1")
   val N2 = new JLabel("2")
   val N3 = new JLabel("3")
   N1.setBounds(530, 0, 100, 100)
   N1.setFont(new Font("Tahoma", Font.ROMAN_BASELINE, 50))
   N1.setForeground(Color.ORANGE)
   frame.getContentPane().add(N1)
   N2.setBounds(764, 0, 100, 100)
   N2.setFont(new Font("Tahoma", Font.ROMAN_BASELINE, 50))
   N2.setForeground(Color.ORANGE)
   frame.getContentPane().add(N2)
   N3.setBounds(998, 0, 100, 100)
   N3.setFont(new Font("Tahoma", Font.ROMAN_BASELINE, 50))
   N3.setForeground(Color.ORANGE)
   frame.getContentPane().add(N3)
   update(frame,input(0).asInstanceOf[Array[Array[Char]]])
 }
 def update(frame: JFrame, board: Array[Array[Char]]):Any={
   addPanel(frame,board)
 }
 def preUpdate(frame:JFrame ,board: Array[Array[Char]]):Any={
   frame.remove(frame.getContentPane().getComponentAt(415, 90))
   frame.repaint()
   update(frame,board)
 }
 def addPanel(frame: JFrame,board: Array[Array[Char]]):Any={
   frame.getContentPane().add(new JPanel() {
     this.setBounds(415, 90, 703, 601)
     this.setLayout(null)
     this.repaint()

     override def paint(g: Graphics) = {
       loop(1,9,this,board,draw,draw(this,board,0))
       super.paint(g)
       g.drawRect(0, 0, 702, 600)
       g.drawLine(234, 0, 234, 600)
       g.drawLine(468, 0, 468, 600)
       g.drawLine(0, 200, 702, 200)
       g.drawLine(0, 400, 702, 400)
     }
   })
   frame.setVisible(true)
   frame.repaint()
   return frame
 }
  def loop(i:Int,n:Int,panel:JPanel,board:Array[Array[Char]],f:(panel:JPanel,board:Array[Array[Char]],j:Int)=>Unit,u:Unit):Unit= i==n match{
    case true =>
    case false => loop(i+1,n,panel,board,f,f(panel,board,i))
  }
  def draw(panel:JPanel,board:Array[Array[Char]],i:Int):Unit= board(i/3)(i%3) match{
    case 'o' => addLabel(panel,"O",i,Color.BLUE)
    case 'x' => addLabel(panel,"X",i,Color.magenta)
    case _ =>
  }
  def addLabel(panel:JPanel,s:String,i:Int,color:Color):Unit={
    val l = new JLabel(s)
    l.setBounds((i%3) * 234 + 50, (i/3) * 200, 234, 200)
    l.setFont(new Font("Tahoma", Font.ROMAN_BASELINE, 200))
    l.setForeground(color)
    panel.add(l)
  }
  start(input.length)
  }

  def sudoku_control(st: String, input: Array[Any]): Array[Any] = {
  def copy(r:Int,c:Int,i:Int,board:Array[Array[Int]],base:Array[Array[Int]],f:Unit):Unit= i ==81 match{
    case true =>
    case false =>copy(r,c,i+1,board,base,insert(r+i/9,c+i%9,base(r+i/9)(c+i%9),board))
  }
  def createRandomBoard(board:Array[Array[Int]],base:Array[Array[Int]]):Array[Any]={
    initBoard(0,0,board,0,false,unit())
    initBoard(0,0,base,0,false,unit())
    initRandomSquares(base)
    CSP(3,base)
    removeRandomCells(base,Random.shuffle(creatList(Seq(),0)),0)
    copy(0,0,0,board,base,unit())
    Array(board,base,true)
  }
  def insert(r:Int,c:Int,x:Int,board:Array[Array[Int]]):Unit={
  board(r)(c)=x
  }
 def initBoard(r:Int,c:Int,board:Array[Array[Int]],direction:Int,condition:Boolean,f:Unit):Boolean= direction match{
   case 0 => (!condition)&&initBoard(r,c+1,board,1,r==9||c+1==9,insert(r,c,0,board))&&initBoard(r+1,c,board,2,r+1==9||c==9,insert(r,c,0,board))&&initBoard(r+1,c+1,board,0,r+1==9||c+1==9,insert(r,c,0,board))
   case 1 => (!condition)&&initBoard(r,c+1,board,1,r==9||c+1==9,insert(r,c,0,board))
   case 2 => (!condition)&&initBoard(r+1,c,board,2,r+1==9||c==9,insert(r,c,0,board))

 }
 def unit():Unit={}
 def initRandomSquares(board:Array[Array[Int]]):Unit={
   randomSquare(0,0,0,board,Random.shuffle(Seq(1,2,3,4,5,6,7,8,9)),false,unit())
   randomSquare(3,3,0,board,Random.shuffle(Seq(1,2,3,4,5,6,7,8,9)),false,unit())
   randomSquare(6,6,0,board,Random.shuffle(Seq(1,2,3,4,5,6,7,8,9)),false,unit())
 }
 def randomSquare(r:Int,c:Int,i:Int,board:Array[Array[Int]],L:Seq[Int],condition:Boolean,f:Unit):Unit= condition match{
   case true =>
   case false =>  randomSquare(r,c,i+1,board,L,i==8,insert(r+(i/3),c+(i%3),L(i),board))
 }
 def removeRandomCells(board: Array[Array[Int]],L:Seq[Int],i:Int):Unit= i==40 match{
   case false => f5(board,L(i),L,i)
   case true =>
 }
 def f5(board: Array[Array[Int]],n:Int,L:Seq[Int],i:Int):Unit={
   insert(n/9,n%9,0,board)
   removeRandomCells(board, L, i+1)
 }
 def creatList(L:Seq[Int],i:Int): Seq[Int]= i==81 match{
   case true => L
   case false => creatList(L:+i,i+1)
 }
 def CSP(i:Int,board:Array[Array[Int]]):Unit= {
   trial(1)
   def trial(j:Int):Unit= j match{
     case 10 =>
     case _ => f(i,j,board,trial)
     }
 }
  def f(i:Int,j:Int,board:Array[Array[Int]],trial:(j:Int)=>Unit):Unit={
    f1(i,j,board)
    trial(j+1)
  }
def f1(i:Int,n:Int,board:Array[Array[Int]]):Unit= check(i / 9, i % 9, n, board) match{
  case true => f2(i,n,board)
  case false =>f4(i,board)
}
def f2(i:Int,n:Int,board:Array[Array[Int]]) :Unit={
  insert(i / 9, i % 9, n, board)
  f3(i,n,board,i < 77 && board(8)(5) == 0)
}
def f3(i:Int,n:Int,board:Array[Array[Int]],condition:Boolean) :Unit= condition match{
  case true => nextEmpty(i+1,board,board((i+1)/9)((i+1)%9)!=0)
  case false =>
}
def f4(i:Int,board:Array[Array[Int]]):Unit= board(8)(5) match{
  case 0 => insert(i / 9, i % 9, 0, board)
  case _ =>
}
def nextEmpty(i:Int,board:Array[Array[Int]],condition:Boolean):Unit= condition match{
  case false => CSP(i,board)
  case true => nextEmpty(i+1,board,board((i+1)/9)((i+1)%9)!=0)
}

  def start(st:String):Array[Any]= st match{
    case null => createRandomBoard(Array.ofDim[Int](9, 9),Array.ofDim[Int](9, 9))
    case _ => checkLength(st.length() != 4 || st.indexOf(" ") != 2,input(0).asInstanceOf[Array[Array[Int]]],input(1).asInstanceOf[Array[Array[Int]]])
  }
    def checkLength(B:Boolean,board:Array[Array[Int]],base:Array[Array[Int]]):Array[Any]= B match{
      case true => Array(board,base, false)
      case false => checkBounds(st.charAt(0).toInt - 'A'.toInt,st.charAt(1).toInt - '0'.toInt - 1,st.charAt(3).toInt - '0'.toInt,board,base)
    }
   def checkBounds(r:Int,c:Int,x:Int,board:Array[Array[Int]],base:Array[Array[Int]]):Array[Any]={
     bounds(r,c,x,board,base,r < 0 || r > 8 || c < 0 || c > 8 || x < 0 || x > 9 || base(r)(c) != 0)
   }
   def bounds(r:Int,c:Int,x:Int,board:Array[Array[Int]],base:Array[Array[Int]],B:Boolean):Array[Any]= B match{
     case true => Array(board,base, false)
     case false => valid(r,c,x,board,base)
   }
  def valid(r: Int, c: Int, x: Int,board: Array[Array[Int]],base: Array[Array[Int]]):Array[Any]= check(r,c,x,board) match {
    case true => Assignment(r,c,x,board,base)
    case false => Array(board,base, false)
  }
def Assignment(r: Int, c: Int, x: Int, board: Array[Array[Int]], base: Array[Array[Int]]):Array[Any]={
  board(r)(c) = x
  Array(board, base, true)
}
    def checkRow(r: Int, i: Int, x: Int, condition: Boolean,board: Array[Array[Int]]): Boolean = condition match {
      case false => board(r)(i) != x && checkRow(r, i + 1, x, i == 8,board)
      case true => true
    }

    def checkColumn(c: Int, i: Int, x: Int, condition: Boolean,board: Array[Array[Int]]): Boolean = condition match {
      case false => board(i)(c) != x && checkColumn(c, i + 1, x, i == 8,board)
      case true => true
    }

    def checkBox(direction: Int, r: Int, c: Int, i: Int, j: Int, x: Int, condition: Boolean,board: Array[Array[Int]]): Boolean = direction match {
      case 0 => !condition || board(r + i)(c + j) != x && checkBox(1, r, c, i, j + 1, x, j < 2,board) && checkBox(2, r, c, i + 1, j, x, i < 2,board) && checkBox(0, r, c, i + 1, j + 1, x, i < 2 && j < 2,board)
      case 1 => !condition || board(r + i)(c + j) != x && checkBox(1, r, c, i, j + 1, x, j < 2,board)
      case 2 => !condition || board(r + i)(c + j) != x && checkBox(2, r, c, i + 1, j, x, i < 2,board)
    }

    def check(r: Int, c: Int, x: Int,board: Array[Array[Int]]): Boolean = x match{
      case 0 => true
      case _ =>  checkRow(r, 0, x, false,board) && checkColumn(c, 0, x, false,board) && checkBox(0, (r / 3) * 3, (c / 3) * 3, 0, 0, x, true,board)
    }
    start(st)
  }

  def sudoku_draw(input: Array[Any]): Any = {
    def END(frame: JFrame): Any = {
      frame.dispose()
      return null
    }
    def start(L:Int):Any= L match{
      case 4 => END(input(0).asInstanceOf[JFrame])
      case 1=> createFrame(new JFrame())
      case _ => preUpdate(input(0).asInstanceOf[JFrame])
    }
    def createFrame(frame:JFrame): Any ={
      frame.setBounds(15, 0, 1500, 800)
      frame.getContentPane().setLayout(null)
      drawLetters(1)

      def drawLetters(i: Int): Unit = {
        val l = new JLabel(((64 + i).toChar).toString())
        l.setBounds(530, 225 + 30 * i, 20, 20)
        l.setFont(new Font("Tahoma", Font.ROMAN_BASELINE, 20))
        l.setForeground(Color.ORANGE)
        frame.getContentPane().add(l)
        i == 9 match
          case true =>
          case false => drawLetters(i + 1)
      }
      drawNumbers(1)
      def drawNumbers(i: Int): Unit = {
        val l = new JLabel(((i + '0').toChar).toString())
        l.setBounds(530 + i * 30, 230, 20, 20)
        l.setFont(new Font("Tahoma", Font.ROMAN_BASELINE, 20))
        l.setForeground(Color.ORANGE)
        frame.getContentPane().add(l)
        i == 9 match
          case true =>
          case false => drawNumbers(i + 1)
      }
      frame.setVisible(true)
      update(frame,input(0).asInstanceOf[Array[Array[Int]]],input(0).asInstanceOf[Array[Array[Int]]])
    }
    def preUpdate(frame:JFrame):Any={
      frame.remove(frame.getContentPane().getComponentAt(550, 250))
      frame.repaint()
      update(frame,input(1).asInstanceOf[Array[Array[Int]]],input(2).asInstanceOf[Array[Array[Int]]])
    }
    def update(frame:JFrame,board:Array[Array[Int]],base:Array[Array[Int]]):Any={
      frame.getContentPane().add(new JPanel() {
        this.setBounds(550, 250, 271, 271)
        this.setLayout(null)
        this.setOpaque(true)
        this.repaint()
        draw(0)

        def draw(i: Int): Unit = i == 81 match {
          case true =>
          case false => chooseLabel(i)
        }

        def chooseLabel(i: Int): Unit = board(i / 9)(i % 9) match {
          case 0 => addLabel(i, Color.BLACK, "")
          case _ => checkBase(i: Int)
        }

        def checkBase(i: Int): Unit = base(i / 9)(i % 9) match {
          case 0 => addLabel(i, Color.BLUE, " " + board(i / 9)(i % 9).toString)
          case _ => addLabel(i, Color.BLACK, " " + board(i / 9)(i % 9).toString)
        }

        def addLabel(i: Int, color: Color, s: String): Unit = {
          val l = new JLabel(s)
          l.setBounds((i % 9) * 30, (i / 9) * 30, 30, 30)
          l.setFont(new Font("Tahoma", Font.ROMAN_BASELINE, 25))
          l.setForeground(color)
          l.setBackground(Color.WHITE)
          l.setOpaque(true)
          this.add(l)
          draw(i + 1)
        }

        override def paint(g: Graphics) = {
          super.paint(g)
          g.setColor(Color.BLACK)
          g.drawRect(0, 0, 270, 270)
          drawVerticalLines(1)

          def drawVerticalLines(i: Int): Unit = {
            g.setColor(new Color(0, 0, 0, 70))
            g.drawLine(i * 30, 0, i * 30, 270)
            i == 8 match
              case true =>
              case false => drawVerticalLines(i + 1)
          }

          drawHorizontalLines(1)

          def drawHorizontalLines(i: Int): Unit = {
            g.setColor(new Color(0, 0, 0, 70))
            g.drawLine(0, i * 30, 270, i * 30)
            i == 8 match
              case true =>
              case false => drawHorizontalLines(i + 1)
          }

          g.setColor(Color.BLACK)
          g.drawLine(90, 0, 90, 270)
          g.drawLine(180, 0, 180, 270)
          g.drawLine(0, 90, 270, 90)
          g.drawLine(0, 180, 270, 180)
        }
      })
      return frame
    }
     start(input.length)
  }

  def eight_queen_control(st: String, input: Array[Any]): Array[Any] = {

    def start(st:String):Array[Any]= st match{
      case null =>Array(Array(Array(0, 0, 0, 0, 0, 0, 0, 0),
        Array(0, 0, 0, 0, 0, 0, 0, 0),
        Array(0, 0, 0, 0, 0, 0, 0, 0),
        Array(0, 0, 0, 0, 0, 0, 0, 0),
        Array(0, 0, 0, 0, 0, 0, 0, 0),
        Array(0, 0, 0, 0, 0, 0, 0, 0),
        Array(0, 0, 0, 0, 0, 0, 0, 0),
        Array(0, 0, 0, 0, 0, 0, 0, 0),
      ),null,true)
      case _ => checkLength(st.length)
    }
    def checkLength(L: Int):Array[Any]= L match{
      case 2 => checkBounds(st.charAt(0).toInt - 'A'.toInt,st.charAt(1).toInt - '0'.toInt - 1)
      case _ => Array(input(0).asInstanceOf[Array[Array[Int]]],null,false)
    }
    def checkBounds(r:Int,c:Int):Array[Any]={
      return bounds(r < 0 || r > 7 || c < 0 || c > 7,r,c,input(0).asInstanceOf[Array[Array[Int]]])
    }
    def clear(r:Int,c:Int,board:Array[Array[Int]]):Array[Any]= board(r)(c) match{
      case 0 =>checkAttack(r,c,board)
      case _ =>Assignment(r,c,board,0)

    }
    def bounds(B:Boolean,r:Int,c:Int,board:Array[Array[Int]]):Array[Any]= B match{
      case true => Array(board,null,false)
      case false => clear(r,c,board)
    }
    def checkAttack(r:Int,c:Int,board:Array[Array[Int]]):Array[Any]={
      return checkDirections(r,c,checkRow(r,c,0,false,board)&&checkColumn(r,c,0,false,board)&&checkDiagonals(r,c,0,0,0,true,board),board)
    }
    def checkDirections(r:Int,c:Int,B: Boolean,board:Array[Array[Int]]):Array[Any]= B match{
      case false =>Array(board,null,false)
      case true => Assignment(r,c,board,1)
    }
    def Assignment(r:Int,c:Int,board:Array[Array[Int]],x:Int):Array[Any]={
      board(r)(c) = x
      return Array(board, null, true)
    }
    def checkRow(r:Int,c:Int,i: Int, condition: Boolean,board:Array[Array[Int]]): Boolean = condition match {
      case false => board(r)(i) == 0 && checkRow(r,c,i + 1, i == 7,board)
      case true => true
    }

    def checkColumn(r:Int,c:Int,i: Int, condition: Boolean,board:Array[Array[Int]]): Boolean = condition match {
      case false => board(i)(c) == 0 && checkColumn(r,c,i + 1, i == 7,board)
      case true => true
    }

    def checkDiagonals(r:Int,c:Int,direction: Int, i: Int, j: Int, condition: Boolean,board:Array[Array[Int]]): Boolean = direction match {
      case 0 => !condition || board(r + i)(c + j) == 0 && checkDiagonals(r,c,1, i - 1, j + 1, r + i - 1 >= 0 && c + j + 1 < 8,board) &&
        checkDiagonals(r,c,2, i - 1, j - 1, r + i - 1 >= 0 && c + j - 1 >= 0,board) && checkDiagonals(r,c,3, i + 1, j + 1, r + i + 1 < 8 && c + j + 1 < 8,board) &&
        checkDiagonals(r,c,4, i + 1, j - 1, r + i + 1 < 8 && c + j - 1 >= 0,board)
      case 1 => !condition || board(r + i)(c + j) == 0 && checkDiagonals(r,c,1, i - 1, j + 1, r + i - 1 >= 0 && c + j + 1 < 8,board)
      case 2 => !condition || board(r + i)(c + j) == 0 && checkDiagonals(r,c,2, i - 1, j - 1, r + i - 1 >= 0 && c + j - 1 >= 0,board)
      case 3 => !condition || board(r + i)(c + j) == 0 && checkDiagonals(r,c,3, i + 1, j + 1, r + i + 1 < 8 && c + j + 1 < 8,board)
      case 4 => !condition || board(r + i)(c + j) == 0 && checkDiagonals(r,c,4, i + 1, j - 1, r + i + 1 < 8 && c + j - 1 >= 0,board)
    }
    return start(st)
  }
  def eight_queen_draw(input:Array[Any]):Any={

    def END(frame: JFrame): Any = {
      frame.dispose()
      return null
    }
    def createFrame(frame:JFrame):Any={
      frame.setBounds(15, 0, 1500, 800)
      frame.getContentPane().setLayout(null)
      drawLetters(1)

      def drawLetters(i: Int): Unit = {
        val l = new JLabel(((64 + i).toChar).toString())
        l.setBounds(370, 20 + 75 * i, 75, 75)
        l.setFont(new Font("Tahoma", Font.ROMAN_BASELINE, 30))
        l.setForeground(Color.ORANGE)
        frame.getContentPane().add(l)
        i match
          case 8 =>
          case _ => drawLetters(i + 1)
      }

      drawNunmbers(1)
      def drawNunmbers(i: Int): Unit = {
        val l = new JLabel(((i + '0').toChar).toString())
        l.setBounds(375 + i * 75, 20, 75, 75)
        l.setFont(new Font("Tahoma", Font.ROMAN_BASELINE, 30))
        l.setForeground(Color.ORANGE)
        frame.getContentPane().add(l)
        i match
          case 8 =>
          case _ => drawNunmbers(i + 1)
      }
      update(frame,input(0).asInstanceOf[Array[Array[Int]]], new ImageIcon(("Black_Queen.png")))
    }
    def preUpdate(frame: JFrame):Any={
      frame.remove(frame.getContentPane().getComponentAt(415, 90))
      frame.repaint()
      update(frame,input(1).asInstanceOf[Array[Array[Int]]],new ImageIcon(("Black_Queen.png")))
    }
    def update(frame: JFrame,board:Array[Array[Int]],icon:ImageIcon):Any={
      frame.getContentPane().add(new JPanel() {
        this.setBounds(415, 90, 602, 602)
        this.setLayout(null)

        override def paint(g: Graphics) = {

          draw(0)

          def draw(i: Int): Unit = {
            ((i / 8) + (i % 8)) % 2 match
              case 0 => checkBoard(i, Color.WHITE)
              case _ => checkBoard(i, Color.magenta)

            i match
              case 63 =>
              case _ => draw(i + 1)
          }

          def checkBoard(i: Int, color: Color): Unit = board(i / 8)(i % 8) match {
            case 1 => addQueen(i, color)
            case _ => addEmptyLabel(i, color)
          }

          def addQueen(i: Int, color: Color): Unit = {
            val b = new JLabel("")
            b.setBounds((i % 8) * 75, (i / 8) * 75, 8, 75)
            b.setBackground(color)
            b.setForeground(Color.BLACK)
            b.setFont(new Font("Tahoma", Font.ROMAN_BASELINE, 50))
            b.setOpaque(true)
            this.add(b)
            val l = new JLabel("")
            l.setIcon(icon)
            l.setBounds((i % 8) * 75 + 8, (i / 8) * 75, 67, 75)
            l.setFont(new Font("Tahoma", Font.ROMAN_BASELINE, 30))
            l.setForeground(Color.BLACK)
            l.setBackground(color)
            l.setOpaque(true)
            this.add(l)
          }

          def addEmptyLabel(i: Int, color: Color): Unit = {
            val l = new JLabel("")
            l.setBounds((i % 8) * 75, (i / 8) * 75, 75, 75)
            l.setFont(new Font("Tahoma", Font.ROMAN_BASELINE, 50))
            l.setForeground(Color.BLACK)
            l.setBackground(color)
            l.setOpaque(true)
            this.add(l)
          }

          super.paint(g)
          g.drawRect(0, 0, 600, 600)
          drawVerticalLines(1)

          def drawVerticalLines(i: Int): Unit = {
            g.drawLine(i * 75, 0, i * 75, 600)
            i match
              case 7 =>
              case _ => drawVerticalLines(i + 1)
          }

          drawHorizontalLines(1)

          def drawHorizontalLines(i: Int): Unit = {
            g.drawLine(0, i * 75, 600, i * 75)
            i match
              case 7 =>
              case _ => drawHorizontalLines(i + 1)
          }
        }
      })
      frame.setVisible(true)
      return frame
    }
    def start(L:Int):Any= L match{
      case 4 =>END(input(0).asInstanceOf[JFrame])
      case 1=>createFrame(new JFrame())
      case _ =>preUpdate(input(0).asInstanceOf[JFrame])
    }
  start(input.length)
//    return frame
  }

  def connect_4_control(st: String, input: Array[Any]): Array[Any] ={

    def start(st:String):Array[Any]= st match{
      case null => Array(Array(Array(-1, -1, -1, -1, -1, -1, -1),
        Array(-1, -1, -1, -1, -1, -1, -1),
        Array(-1, -1, -1, -1, -1, -1, -1),
        Array(-1, -1, -1, -1, -1, -1, -1),
        Array(-1, -1, -1, -1, -1, -1, -1),
        Array(-1, -1, -1, -1, -1, -1, -1),
      ),0,true)
      case _ =>checkLength(st.length)
    }
    def checkLength(L:Int):Array[Any]=L match{
      case 1 => checkBounds(st.charAt(0).toInt - '0'.toInt - 1,input(0).asInstanceOf[Array[Array[Int]]])
      case _ => Array(input(0).asInstanceOf[Array[Array[Int]]],input(1).asInstanceOf[Int],false)
    }
    def checkBounds(c:Int,board:Array[Array[Int]]):Array[Any]={
      return bounds(c < 0 || c > 6 || board(0)(c) != -1,c)
    }
    def bounds(B:Boolean,c:Int):Array[Any]=B match{
      case true => Array(input(0).asInstanceOf[Array[Array[Int]]],input(1).asInstanceOf[Int],false)
      case false => Assignment(input(0).asInstanceOf[Array[Array[Int]]],input(1).asInstanceOf[Int],c)
    }
    def Assignment(board:Array[Array[Int]],turn:Int,c:Int):Array[Any]={
      insert(6,false,board,turn,c)
      return Array(board,(turn-1).abs,true)
    }
    def insert(index: Int, condition: Boolean,board:Array[Array[Int]],turn:Int,c:Int): Unit = condition match {
      case false => insert(index - 1, board(index - 1)(c) == -1,board, turn, c)
      case true => board(index)(c) = turn
    }
    return start(st)
  }
  def connect_4_draw(input:Array[Any]):Any={
     def createFrame(frame: JFrame):Any={
       frame.setBounds(15, 0, 1500, 800)
       frame.getContentPane().setLayout(null)
       drawNumbers(1)

       def drawNumbers(i: Int): Unit = {
         val l = new JLabel(((i + '0').toChar).toString())
         l.setBounds(360 + i * 100, 20, 100, 100)
         l.setFont(new Font("Tahoma", Font.ROMAN_BASELINE, 30))
         l.setForeground(Color.magenta)
         frame.getContentPane().add(l)
         i match
           case 7 =>
           case _ => drawNumbers(i + 1)
       }
       frame.setVisible(true)
       update(frame,input(0).asInstanceOf[Array[Array[Int]]])
     }

    def END(frame: JFrame): Any = {
      frame.dispose()
      return null
    }
    def preUpdate(frame:JFrame):Any={
      frame.remove(frame.getContentPane().getComponentAt(415, 90))
      frame.repaint()
      update(input(0).asInstanceOf[JFrame],input(1).asInstanceOf[Array[Array[Int]]])
    }
    def update(frame:JFrame,board:Array[Array[Int]]):Any={
      frame.getContentPane().add(new JPanel() {
        this.setBounds(415, 90, 702, 602)
        this.setLayout(null)
        this.repaint()

        override def paint(g: Graphics) = {
          super.paint(g)
          g.setColor(Color.BLUE)
          g.fillRoundRect(0, 0, 700, 600, 100, 100)
          draw(0)

          def draw(i: Int): Unit = board(i / 7)(i % 7) match {
            case 0 => f1(i)
            case 1 => f2(i)
            case _ => f3(i)
          }

          def f1(i: Int): Unit = {
            g.setColor(Color.YELLOW)
            g.fillOval((i % 7) * 100, (i / 7) * 100, 100, 100)
            i match
              case 41 =>
              case _ => draw(i + 1)
          }

          def f2(i: Int): Unit = {
            g.setColor(Color.RED)
            g.fillOval((i % 7) * 100, (i / 7) * 100, 100, 100)
            i match
              case 41 =>
              case _ => draw(i + 1)
          }

          def f3(i: Int): Unit = {
            g.setColor(this.getBackground())
            g.fillOval((i % 7) * 100, (i / 7) * 100, 100, 100)
            g.setColor(Color.BLACK)
            g.drawOval((i % 7) * 100, (i / 7) * 100, 100, 100)
            i match
              case 41 =>
              case _ => draw(i + 1)
          }

          g.setColor(Color.BLACK)
        }

      })
      return frame
    }
    def start(L: Int): Any = L match {
      case 4 => END(input(0).asInstanceOf[JFrame])
      case 1 => createFrame(new JFrame())
      case _ => preUpdate(input(0).asInstanceOf[JFrame])
    }
    start(input.length)
  }

  def chess_control(st: String, input: Array[Any]): Array[Any] ={
   def checkSpace(r1:Int,c1:Int,r2:Int,c2:Int,i :Int,j:Int,condition:Boolean,board:Array[Array[Char]]):Boolean= condition match{
     case false => board(r1)(c1)=='s'&&checkSpace(r1+i,c1+j,r2,c2,i,j,r1+i==r2&&c1+j==c2,board)
     case true => true
   }
   def checkDirection(N1:Int,N2:Int):Int= N2-N1 match{
     case  0=>0
     case _ =>(N2-N1)/((N2-N1).abs)
   }
    def Rook(r1: Int, c1: Int, r2: Int, c2: Int,board:Array[Array[Char]]): Boolean = {

      ((r1 - r2).abs == 0 || (c1 - c2).abs == 0)&&checkSpace(r1+checkDirection(r1,r2),c1+checkDirection(c1,c2),r2,c2,checkDirection(r1,r2),checkDirection(c1,c2),(r1-r2).abs==1||(c1-c2).abs==1,board)
    }

    def Horse(r1: Int, c1: Int, r2: Int, c2: Int,board:Array[Array[Char]]): Boolean = {
      (r1 - r2).abs == 1 && (c1 - c2).abs == 2 || (r1 - r2).abs == 2 && (c1 - c2).abs == 1
    }

    def Bishop(r1: Int, c1: Int, r2: Int, c2: Int,board:Array[Array[Char]]): Boolean = {


       ((r1 - r2).abs == (c1 - c2).abs && (r1 - r2).abs != 0)&&
        checkSpace(r1+checkDirection(r1,r2),c1+checkDirection(c1,c2),r2,c2,checkDirection(r1,r2),checkDirection(c1,c2),(r1-r2).abs==1||(c1-c2).abs==1,board)
    }

    def King(r1: Int, c1: Int, r2: Int, c2: Int,board:Array[Array[Char]]): Boolean = {

      (r1 - r2).abs == 1 || (c1 - c2).abs == 1
    }

    def BlackPawn(r1: Int, c1: Int, r2: Int, c2: Int,board:Array[Array[Char]]): Boolean = {

      (r2 - r1 == 1 && c1 == c2 && board(r2)(c2) == 's') || (r1 == 1 && r2 == 3 && c1 == c2 && board(r1 + 1)(c1) == 's' && board(r2)(c2) == 's') ||
       ((c1 - c2).abs == 1 && r2 - r1 == 1 && (board(r2)(c2)).isLower&&board(r2)(c2) != 's')
    }

    def WhitePawn(r1: Int, c1: Int, r2: Int, c2: Int,board:Array[Array[Char]]): Boolean = {

      (r1 - r2 == 1 && c1 == c2 && board(r2)(c2) == 's') || (r1 == 6 && r2 == 4 && c1 == c2 && board(r2 + 1)(c1) == 's' && board(r2)(c2) == 's') ||
       ((c1 - c2).abs == 1 && r1 - r2 == 1 && (board(r2)(c2)).isUpper)
    }

    def Queen(r1: Int, c1: Int, r2: Int, c2: Int,board:Array[Array[Char]]): Boolean = {

       Rook(r1,c1,r2,c2,board) || Bishop(r1, c1, r2, c2, board)
    }
    def matchTest(r1: Int, c1: Int, r2: Int, c2: Int, piece: Char,board:Array[Array[Char]]): Boolean = piece match {
      case 'R' => Rook(r1, c1, r2, c2,board)
      case 'r' => Rook(r1, c1, r2, c2,board)
      case 'H' => Horse(r1, c1, r2, c2,board)
      case 'h' => Horse(r1, c1, r2, c2,board)
      case 'B' => Bishop(r1, c1, r2, c2,board)
      case 'b' => Bishop(r1, c1, r2, c2,board)
      case 'k' => King(r1, c1, r2, c2,board)
      case 'K' => King(r1, c1, r2, c2,board)
      case 'P' => BlackPawn(r1, c1, r2, c2,board)
      case 'p' => WhitePawn(r1, c1, r2, c2,board)
      case 'q' => Queen(r1, c1, r2, c2,board)
      case 'Q' => Queen(r1, c1, r2, c2,board)
      case 's' => false

    }

  def start(st:String):Array[Any]= st match{
    case null => Array(Array(Array('R', 'H', 'B', 'Q', 'K', 'B', 'H', 'R'),
      Array('P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'),
      Array('s', 's', 's', 's', 's', 's', 's', 's'),
      Array('s', 's', 's', 's', 's', 's', 's', 's'),
      Array('s', 's', 's', 's', 's', 's', 's', 's'),
      Array('s', 's', 's', 's', 's', 's', 's', 's'),
      Array('p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'),
      Array('r', 'h', 'b', 'q', 'k', 'b', 'h', 'r'),
    ),0,true)
    case _ => checkLength(st.length() != 5 || st.indexOf(" ") != 2)
  }
   def checkLength(B:Boolean):Array[Any]= B match{
     case true => Array(input(0).asInstanceOf[Array[Array[Char]]],input(1).asInstanceOf[Int],false)
     case false => checkBounds(st.charAt(0).toInt - 'A'.toInt,st.charAt(1).toInt - '0'.toInt - 1,st.charAt(3).toInt - 'A'.toInt,st.charAt(4).toInt - '0'.toInt - 1,input(0).asInstanceOf[Array[Array[Char]]],input(1).asInstanceOf[Int])
   }
   def checkBounds(r1:Int,c1:Int,r2:Int,c2:Int,board:Array[Array[Char]],turn:Int):Array[Any]={
       bounds(r1 < 0 || r1 > 7 || c1 < 0 || c1 > 7 || r2 < 0 || r2 > 7 || c2 < 0 || c2 > 7||board(r1)(c1)=='s'||
       turn == 0 && (board(r1)(c1)).isUpper || turn == 1 && (board(r1)(c1)).isLower||
       turn == 0 && (board(r2)(c2)).isLower&&board(r2)(c2)!='s'|| turn == 1 && (board(r2)(c2)).isUpper,
       r1,c1,r2,c2,board,turn)
   }
   def bounds(b:Boolean,r1:Int,c1:Int,r2:Int,c2:Int,board:Array[Array[Char]],turn:Int) :Array[Any]= b match{
     case true => Array(board,turn,false)
     case false => check(r1,c1,r2,c2,board,turn)
   }
   def check(r1:Int,c1:Int,r2:Int,c2:Int,board:Array[Array[Char]],turn:Int):Array[Any]=  matchTest(r1, c1, r2, c2,board(r1)(c1),board) match{
     case true => Assignment(r1,c1,r2,c2,board,turn)
     case false => Array(board,turn,false)
   }
    def Assignment(r1:Int,c1:Int,r2:Int,c2:Int,board:Array[Array[Char]],turn:Int):Array[Any]= {
        board(r2)(c2) = board(r1)(c1)
        board(r1)(c1) = 's'
        Array(board, (turn - 1).abs, true)
    }
 start(st)
  }
  def chess_draw(input:Array[Any]):Any={

    def createFrame(frame: JFrame):Any={
      frame.setBounds(15, 0, 1500, 800)
      frame.getContentPane().setLayout(null)
      drawLetters(1)

      def drawLetters(i: Int): Unit = {
        val l = new JLabel(((64 + i).toChar).toString())
        l.setBounds(370, 20 + 75 * i, 75, 75)
        l.setFont(new Font("Tahoma", Font.ROMAN_BASELINE, 30))
        l.setForeground(Color.ORANGE)
        frame.getContentPane().add(l)
        i match
          case 8 =>
          case _ => drawLetters(i + 1)
      }

      drawNumbers(1)

      def drawNumbers(i: Int): Unit = {
        val l = new JLabel(((i + '0').toChar).toString())
        l.setBounds(375 + i * 75, 20, 75, 75)
        l.setFont(new Font("Tahoma", Font.ROMAN_BASELINE, 30))
        l.setForeground(Color.ORANGE)
        frame.getContentPane().add(l)
        i match
          case 8 =>
          case _ => drawNumbers(i + 1)
      }
      frame.setVisible(true)
      update(frame,input(0).asInstanceOf[Array[Array[Char]]])
    }
    def preUpdate(frame: JFrame):Any={
      frame.remove(frame.getContentPane().getComponentAt(415, 90))
      frame.repaint()
      update(frame,input(1).asInstanceOf[Array[Array[Char]]])
    }
    def update(frame:JFrame,board:Array[Array[Char]]):Any={
      frame.getContentPane().add(new JPanel() {
        this.setBounds(415, 90, 602, 602)
        this.setLayout(null)

        override def paint(g: Graphics) = {

          draw(0)

          def draw(i: Int): Unit = ((i / 8) + (i % 8)) % 2 match {
            case 0 => checkBoard(i, Color.WHITE)
            case 1 => checkBoard(i, Color.magenta)
          }

          def checkBoard(i: Int, color: Color): Unit = board(i / 8)(i % 8) match {

            case 's' => addEmptyLabel(i, color)
            case _ => addPiece(i, color)
          }

          def addPiece(i: Int, color: Color): Unit = {
            val b = new JLabel("")
            b.setBounds((i % 8) * 75, (i / 8) * 75, 8, 75)
            b.setBackground(color)
            b.setForeground(Color.BLACK)
            b.setFont(new Font("Tahoma", Font.ROMAN_BASELINE, 50))
            b.setOpaque(true)
            this.add(b)
            val l = new JLabel("")

            board(i / 8)(i % 8).isLower match
              case true =>l.setIcon(new ImageIcon(board(i / 8)(i % 8).toString() + "w" + ".png"))
              case false =>l.setIcon(new ImageIcon(board(i / 8)(i % 8).toString() + ".png"))

            l.setBounds((i % 8) * 75 + 8, (i / 8) * 75, 67, 75)
            l.setFont(new Font("Tahoma", Font.ROMAN_BASELINE, 30))
            l.setForeground(Color.BLACK)
            l.setBackground(color)
            l.setOpaque(true)
            this.add(l)
            i match
              case 63 =>
              case _ => draw(i + 1)
          }

          def addEmptyLabel(i: Int, color: Color): Unit = {
            val l = new JLabel("")
            l.setBounds((i % 8) * 75, (i / 8) * 75, 75, 75)
            l.setFont(new Font("Tahoma", Font.ROMAN_BASELINE, 50))
            l.setForeground(Color.BLACK)
            l.setBackground(color)
            l.setOpaque(true)
            this.add(l)
            i match
              case 63 =>
              case _ => draw(i + 1)
          }

          super.paint(g)
          g.drawRect(0, 0, 600, 600)
          drawVerticalLines(1)

          def drawVerticalLines(i: Int): Unit = {
            g.drawLine(i * 75, 0, i * 75, 600)
            i match
              case 7 =>
              case _ => drawVerticalLines(i + 1)
          }

          drawHorizontalLines(1)

          def drawHorizontalLines(i: Int): Unit = {
            g.drawLine(0, i * 75, 600, i * 75)
            i match
              case 7 =>
              case _ => drawHorizontalLines(i + 1)
          }
        }
      })
      frame.setVisible(true)
      return frame
    }
    def END(frame: JFrame): Any = {
      frame.dispose()
      return null
    }
    def start(L: Int): Any = L match {
      case 4 => END(input(0).asInstanceOf[JFrame])
      case 1 => createFrame(new JFrame())
      case _ => preUpdate(input(0).asInstanceOf[JFrame])
    }
    start(input.length)

  }
  def checkers_control(st: String, input: Array[Any]): Array[Any] ={

    def start(st:String):Array[Any]= st match{
      case null => Array(Array(Array(-1, 1, -1, 1, -1, 1, -1, 1),
        Array(1, -1, 1, -1, 1, -1, 1, -1),
        Array(-1, 1, -1, 1, -1, 1, -1, 1),
        Array(-1, -1, -1, -1, -1, -1, -1, -1),
        Array(-1, -1, -1, -1, -1, -1, -1, -1),
        Array(0, -1, 0, -1, 0, -1, 0, -1),
        Array(-1, 0, -1, 0, -1, 0, -1, 0),
        Array(0, -1, 0, -1, 0, -1, 0, -1),
      ),1,true)
      case _ => checkLength(st.length() != 5 || st.indexOf(" ") != 2)
    }
   def checkLength(B: Boolean):Array[Any]=B match{
     case false => checkBounds(st.charAt(0).toInt - 'A'.toInt,st.charAt(1).toInt - '0'.toInt - 1,st.charAt(3).toInt - 'A'.toInt,st.charAt(4).toInt - '0'.toInt - 1,input(0).asInstanceOf[Array[Array[Int]]],input(1).asInstanceOf[Int])
     case true => Array(input(0).asInstanceOf[Array[Array[Int]]],input(1).asInstanceOf[Int],false)
   }
   def checkBounds(r1:Int,c1:Int,r2:Int,c2:Int,board:Array[Array[Int]],turn:Int):Array[Any]={
     return check(((!(r1 < 0 || r1 > 7 || c1 < 0 || c1 > 7 || r2 < 0 || r2 > 7 || c2 < 0 || c2 > 7 || board(r1)(c1) != turn||board(r2)(c2)!= -1))&&((turn == 0 && r1 - r2 == 1 && (c1 - c2).abs == 1  || turn == 0 && r1 - r2 == 2 && (c1 - c2).abs == 2 && board(r1 - 1)((c1 + c2) / 2) == 1)&&board(r2)(c2)== -1||
       (turn == 1 && r2 - r1 == 1 && (c1 - c2).abs == 1  || turn == 1 && r2 - r1 == 2 && (c1 - c2).abs == 2 && board(r1 + 1)((c1 + c2) / 2)== 0)&&board(r2)(c2)== -1)),r1,c1,r2,c2)
   }
   def check(b:Boolean,r1:Int,c1:Int,r2:Int,c2:Int):Array[Any]= b match{
     case false => Array(input(0).asInstanceOf[Array[Array[Int]]],input(1).asInstanceOf[Int],false)
     case true => Assignment(r1,c1,r2,c2,input(0).asInstanceOf[Array[Array[Int]]],input(1).asInstanceOf[Int])
   }
   def Assignment(r1: Int, c1: Int, r2: Int, c2: Int, board:Array[Array[Int]],turn:Int):Array[Any]={
     board(r2)(c2) = board(r1)(c1)
     board(r1)(c1) = -1
     Eaten((r1-r2).abs==2,r1,c1,r2,c2,board)
     return Array(board,(turn-1).abs,true)
   }
    def Eaten(B: Boolean, r1: Int, c1: Int, r2: Int, c2: Int,board:Array[Array[Int]]):Unit= B match{
      case true => board((r1+r2)/2)((c1+c2)/2)= -1
      case false =>
    }
    return start(st)
  }

def checkers_draw(input:Array[Any]):Any={

def createFrame(frame: JFrame):Any={
  frame.setBounds(15, 0, 1500, 800)
  frame.getContentPane().setLayout(null)
  drawLetters(1)

  def drawLetters(i: Int): Unit = {
    val l = new JLabel(((64 + i).toChar).toString())
    l.setBounds(370, 20 + 75 * i, 75, 75)
    l.setFont(new Font("Tahoma", Font.ROMAN_BASELINE, 30))
    l.setForeground(Color.magenta)
    frame.getContentPane().add(l)
    i match
      case 8 =>
      case _ => drawLetters(i + 1)
  }

  drawNumbers(1)

  def drawNumbers(i: Int): Unit = {
    val l = new JLabel(((i + '0').toChar).toString())
    l.setBounds(370 + i * 75, 20, 75, 75)
    l.setFont(new Font("Tahoma", Font.ROMAN_BASELINE, 30))
    l.setForeground(Color.magenta)
    frame.getContentPane().add(l)
    i match
      case 8 =>
      case _ => drawNumbers(i + 1)
  }
  update(frame,input(0).asInstanceOf[Array[Array[Int]]])
}
def preUpdate(frame: JFrame):Any={
  frame.remove(frame.getContentPane().getComponentAt(415, 90))
  frame.repaint()
  update(frame,input(1).asInstanceOf[Array[Array[Int]]])
}
 def update(frame:JFrame,board:Array[Array[Int]]):Any={
   frame.getContentPane().add(new JPanel() {
     this.setBounds(415, 90, 602, 602)
     this.setLayout(null)
     this.repaint()

     override def paint(g: Graphics) = {
       super.paint(g)

       drawBoard(0)

       def drawBoard(i: Int): Unit = ((i / 8) + (i % 8)) % 2 match {
         case 0 => drawEmptyLabel(i, Color.WHITE)
         case _ => drawEmptyLabel(i, Color.BLACK)
       }

       def drawEmptyLabel(i: Int, color: Color): Unit = {
         g.setColor(color)
         g.fillRect((i / 8) * 75, (i % 8) * 75, 75, 75)
         i match
           case 63 =>
           case _ => drawBoard(i + 1)
       }

       g.setColor(Color.BLACK)
       g.drawRect(0, 0, 600, 600)
       drawVertcalLines(1)

       def drawVertcalLines(i: Int): Unit = {
         g.drawLine(i * 75, 0, i * 75, 600)
         i match
           case 7 =>
           case _ => drawVertcalLines(i + 1)
       }

       drawHorizontalLines(1)

       def drawHorizontalLines(i: Int): Unit = {
         g.drawLine(0, i * 75, 600, i * 75)
         i match
           case 7 =>
           case _ => drawHorizontalLines(i + 1)
       }


       g.setColor(Color.WHITE)
       drawPiece(0)

       def drawPiece(i: Int): Unit = board(i / 8)(i % 8) match {
         case 1 => drawBlack(i)
         case 0 => drawWhite(i)
         case _ => checkFull(i)
       }

       def checkFull(i: Int): Unit = i match {
         case 63 =>
         case _ => drawPiece(i + 1)
       }

       def drawWhite(i: Int): Unit = {
         g.fillOval((i % 8) * 75 + 20, (i / 8) * 75 + 20, 35, 35)
         i match
           case 63 =>
           case _ => drawPiece(i + 1)
       }

       def drawBlack(i: Int): Unit = {
         g.drawOval((i % 8) * 75 + 20, (i / 8) * 75 + 20, 35, 35)
         i match
           case 63 =>
           case _ => drawPiece(i + 1)
       }

     }
   })
   frame.setVisible(true)
   return frame
 }
def END(frame: JFrame): Any = {
  frame.dispose()
  return null
}

    def start(L: Int): Any = L match {
      case 4 => END(input(0).asInstanceOf[JFrame])
      case 1 => createFrame(new JFrame())
      case _ => preUpdate(input(0).asInstanceOf[JFrame])
    }
start(input.length)

  }

