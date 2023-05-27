
import java.awt._
import java.awt.event._
import javax.swing._
import java.io._
import scala.io.Source
import org.jpl7._
import scala.util.Using
def Play(control: (String, Array[Any]) => Array[Any], draw: (Array[Any]) => Any) = {

  var controlReturn = Array[Any]()
  var drawReturn: Any = 0
  controlReturn = control(null, controlReturn)
  drawReturn = draw(Array(controlReturn(0)))
  val frame = new JFrame()
  frame.setBounds(650, 720, 260, 100)
  frame.setLayout(null)
  val error = new JLabel()
  error.setBounds(85, 30, 100, 30)
  error.setForeground(Color.RED)
  frame.getContentPane().add(error)
  val END = new JButton("END")
  END.setBounds(160, 10, 60, 30)
  END.setBackground(Color.ORANGE)
  END.setForeground(Color.BLACK)
  END.setFont(new Font("Tahoma", Font.BOLD, 10))
  END.addActionListener(new ActionListener() {
    def actionPerformed(e: ActionEvent) = {
      draw(Array(drawReturn, null, null, null))
      frame.dispose()
    }
  })
  frame.getContentPane().add(END)
  val input = new JTextField()
  input.setBackground(Color.WHITE)
  input.setBounds(10, 10, 50, 30)
  frame.getContentPane().add(input)
  val solve = new JButton("solve")
  solve.setBounds(75, 10, 70, 30)
  solve.setBackground(Color.ORANGE)
  solve.setForeground(Color.BLACK)
  solve.setFont(new Font("Tahoma", Font.BOLD, 10))
  input.addKeyListener(new KeyAdapter() {
    override def keyPressed(e: KeyEvent) = e.getKeyCode() == KeyEvent.VK_ENTER match{
      case true => Enter()
      case false =>
    }
  })
  
  solve.addActionListener(new ActionListener() {
    def actionPerformed(e: ActionEvent) = {
      controlReturn=checkSolve()
      isValid(controlReturn(controlReturn.length - 1).asInstanceOf[Boolean])
      ifSolvable(checkSolvable(controlReturn(0),controlReturn(1)))
    }
  })
  ifSolvable(checkSolvable(controlReturn(0),controlReturn(1)))
  def ifSolvable(condition:Boolean):Unit= condition match {
    case true =>  addSolve()
    case false => removeSolve()
  }
  def removeSolve():Unit={
    frame.remove(solve)
    frame.revalidate()
    frame.repaint()
  }
  def addSolve():Unit={
    frame.getContentPane().add(solve)
    frame.revalidate()
    frame.repaint()
  }

  def Enter(): Unit = {
    controlReturn = control(input.getText(), controlReturn)
    isValid(controlReturn(controlReturn.length - 1).asInstanceOf[Boolean])
     ifSolvable(checkSolvable(controlReturn(0),controlReturn(1)))
  }

  def isValid(B: Boolean): Unit = B match {
    case true => proceed()
    case false => Error()
  }

  def proceed(): Unit = {
    drawReturn = draw(Array(drawReturn, controlReturn(0), controlReturn(1)))
    error.setText("")
    frame.setVisible(true)
  }

  def Error(): Unit = {
    error.setText("Not Valid!")
    val timer: Timer = new Timer(1000, (event) => {
      def foo(event: ActionEvent) = {
        error.setText("")
      }

      foo(event)
    })
    timer.setRepeats(false)
    timer.start()
  }
  def checkSolve():Array[Any]= controlReturn(1)==null match{
    case true =>solveQueens(new PrintWriter("board.pl"),controlReturn(0).asInstanceOf[Array[Array[Int]]])
    case false =>solveSudoku(new PrintWriter("board.pl"),controlReturn(0).asInstanceOf[Array[Array[Int]]],controlReturn(1).asInstanceOf[Array[Array[Int]]],0)
  }

  frame.setVisible(true)

}
