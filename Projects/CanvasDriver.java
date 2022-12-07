// package Projects;
// // This file will be used for the basis of most of my projects with lines and so on

// import javax.swing.event.MouseInputListener;
// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.MouseEvent;
// import java.util.LinkedList;
// import java.awt.geom.Line2D;
// import java.awt.image.BufferStrategy;

// public class CanvasDriver implements Runnable, MouseInputListener{
//     private Canvas canvas;
//     // private LinkedList<Line2D.Float> lines;
//     private int WIDTH = 1680, HEIGHT = 900, BORDER = 10;
//     // private int mouseX, mouseXc;
//     // private int mouseY, mouseYc;
//     // private boolean mouse_clicked;

//     CanvasDriver() {
//         JFrame frame = new JFrame("Poggers");
//         canvas = new Canvas();

//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         frame.setLocationRelativeTo(null);
//         frame.setLayout(null);
//         frame.setSize(WIDTH, HEIGHT+BORDER);
//         canvas.setSize(WIDTH, HEIGHT);
//         frame.setResizable(true);
//         frame.add(canvas);
//         frame.setVisible(true);
//         canvas.addMouseListener(this);
//         canvas.addMouseMotionListener(this);

//         new Thread(this).start();
//     }
    

//     @Override
//     public void mouseClicked(MouseEvent e) {        
//     }

//     @Override
//     public void mousePressed(MouseEvent e) {        
//     }

//     @Override
//     public void mouseReleased(MouseEvent e) {        
//     }

//     @Override
//     public void mouseEntered(MouseEvent e) {        
//     }

//     @Override
//     public void mouseExited(MouseEvent e) {        
//     }

//     @Override
//     public void mouseDragged(MouseEvent e) {        
//     }

//     @Override
//     public void mouseMoved(MouseEvent e) {    
//         mouseXc = e.getX();
//         mouseYc = e.getY();    
//     }

//     @Override
//     public void run() {
//         while (true) {
//             render();
//             tick();
//         }        
//     }

//     private void tick() {

//     }
//     private void render() {
//         BufferStrategy bs = canvas.getBufferStrategy();
//         if (bs == null) {
//             canvas.createBufferStrategy(2);
//         }
//         Graphics gCanvas = bs.getDrawGraphics();

//         // adding background
//         gCanvas.setColor(Color.BLACK);
//         gCanvas.drawRect(0, 0, WIDTH, HEIGHT);
        
        
//     }
//     public static void main(String[] args) {
//         new CanvasDriver();
//     }
    
// }
