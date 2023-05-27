import org.jpl7.Query
import java.awt.*
import java.awt.event.*
import java.io.PrintWriter
import javax.lang.model.`type`.PrimitiveType
import javax.swing.*
object Main extends App{

  val m = new JFrame()
  m.setBounds(15, 0, 1500, 800)
  m.setLayout(null)
  m.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
  initQuery(new PrintWriter("board.pl"))

  val T = new JButton("Tic_Tac_Toe")
  T.setBounds(325, 200, 200, 50)
  T.setBackground(Color.ORANGE)
  T.setForeground(Color.BLACK)
  T.setFont(new Font("Tahoma", Font.BOLD, 20))
  T.addActionListener(new ActionListener() {
    def actionPerformed(e: ActionEvent)= {
      Play(Tic_Tac_Toe_control,Tic_Tac_Toe_draw)
    }
  })
  m.getContentPane().add(T)
  val S = new JButton("Sudoku")
  S.setBounds(575, 200, 200, 50)
  S.setBackground(Color.ORANGE)
  S.setForeground(Color.BLACK)
  S.setFont(new Font("Tahoma", Font.BOLD, 20))
  S.addActionListener(new ActionListener() {
    def actionPerformed(e: ActionEvent) = {
      Play(sudoku_control,sudoku_draw)
    }
  })
  m.getContentPane().add(S)

  val E = new JButton("Eight_Queen")
  E.setBounds(825, 200, 200, 50)
  E.setBackground(Color.ORANGE)
  E.setForeground(Color.BLACK)
  E.setFont(new Font("Tahoma", Font.BOLD, 20))
  E.addActionListener(new ActionListener() {
    def actionPerformed(e: ActionEvent) = {
      Play(eight_queen_control,eight_queen_draw)
    }
  })
  m.getContentPane().add(E)


  val C = new JButton("Connect_4")
  C.setBounds(325, 300, 200, 50)
  C.setBackground(Color.ORANGE)
  C.setForeground(Color.BLACK)
  C.setFont(new Font("Tahoma", Font.BOLD, 20))
  C.addActionListener(new ActionListener() {
    def actionPerformed(e: ActionEvent) = {
      Play(connect_4_control,connect_4_draw)
    }
  })
  m.getContentPane().add(C)


  val CH1 = new JButton("Chess")
  CH1.setBounds(575, 300, 200, 50)
  CH1.setBackground(Color.ORANGE)
  CH1.setForeground(Color.BLACK)
  CH1.setFont(new Font("Tahoma", Font.BOLD, 20))
  CH1.addActionListener(new ActionListener() {
    def actionPerformed(e: ActionEvent) = {
      Play(chess_control,chess_draw)
    }
  })
  m.getContentPane().add(CH1)


  val CH2 = new JButton("Checkers")
  CH2.setBounds(825, 300, 200, 50)
  CH2.setBackground(Color.ORANGE)
  CH2.setForeground(Color.BLACK)
  CH2.setFont(new Font("Tahoma", Font.BOLD, 20))
  CH2.addActionListener(new ActionListener() {
    def actionPerformed(e: ActionEvent) = {
      Play(checkers_control,checkers_draw)
    }
  })
  m.getContentPane().add(CH2)
  m.setVisible(true)


}

