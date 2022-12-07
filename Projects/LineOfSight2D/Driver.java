package Projects.LineOfSight2D;
import javax.swing.JFrame;
// import javax.swing.event.MouseInputListener;

// import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.geom.Line2D;
import java.util.LinkedList;

class Driver implements Runnable, MouseMotionListener {
    private static final int WIDTH = 1280, HEIGHT = 600, BORDER = 50;
    private JFrame frame;
    private Canvas canvas;
    LinkedList<Line2D.Double> lines;

    public Driver() {
        lines = buildlines();
        frame = new JFrame("Java Raycasting");
        canvas = new Canvas();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(canvas);
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (true){
            tick();
            render();
        }
    }

    private void tick() {
        // set frame limit
    }

    private void render() {
        BufferStrategy bs = canvas.getBufferStrategy();
        if (bs == null) {
            canvas.createBufferStrategy(2);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Drawing lines
        g.setColor(Color.GREEN);
        for (Line2D.Double line : lines) {
            g.drawLine((int) line.x1, (int) line.y1, (int) line.x2, (int) line.y2);
        }

        g.dispose();
        bs.show();
    }

    private LinkedList<Line2D.Double> buildlines() {
        LinkedList<Line2D.Double> lines = new LinkedList<>();
        // first is the border or the screen
        lines.add(new Line2D.Double(BORDER, BORDER, BORDER, HEIGHT-BORDER));
        lines.add(new Line2D.Double(BORDER, HEIGHT-BORDER, WIDTH-BORDER, HEIGHT-BORDER));
        lines.add(new Line2D.Double(WIDTH-BORDER, HEIGHT-BORDER, WIDTH-BORDER, BORDER));
        lines.add(new Line2D.Double(WIDTH-BORDER, BORDER, BORDER, BORDER));

        return lines;
    }

    public void mouseDragged(MouseEvent e){}
    public void mouseMoved(MouseEvent e){}

    public static void main(String [] args) {
        new Driver();
    }

}