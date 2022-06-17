package frontend.Map;

import frontend.Tool.MyColor;
import frontend.Tool.MyTextArea;
import frontend.Tool.MyView;
import frontend.Tool.V2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Map extends MyView {

    public Map() {
        setLayout(null);

        setPreferredSize(new Dimension(800, 800));

        EdgeLayer edgeLayer = new EdgeLayer();
        edgeLayer.addEdge(new Edge(100, 100, 200, 200));

        add(new Plot(100, 100, "黑猫厨房"));
        add(new Plot(200, 200, "黑猫厨房"));
        add(edgeLayer);




        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println(e.getX());
            }
        });


    }

    class Plot extends JPanel{

        /**为0或者1，小或者大*/
        private int state = 0;
        private int smallR = 20;
        private int bigR = 30;
        /**图形的半径*/
        private int R = smallR;

        /**文字区域的宽度*/
        private int width = smallR * 4;

        public Plot(int x, int y, String name) {
            Graph graph = new Graph(R);
            graph.setLocation(R, 0);

            JLabel nameLabel = new JLabel();
            nameLabel.setText(name);
            nameLabel.setPreferredSize(new Dimension(width, getPreferredSize().height));
            nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
            JPanel nameArea = new JPanel();
            nameArea.add(nameLabel);
            nameArea.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
            nameArea.setSize(nameArea.getPreferredSize());
            nameArea.setLocation(0, R * 3);
            nameArea.setOpaque(false);
//            nameArea.setBackground(MyColor.color2());

//            setBackground(MyColor.color3());
            setLayout(null);
            add(graph);
            add(nameArea);

            setLocation(new Point(x, y));
            setSize(width, R*3 + nameArea.getHeight());
            setOpaque(false);

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    System.out.println("mouseClicked");
                }
            });
        }

        @Override
        public void setLocation(Point location) {
            super.setLocation(location.x - 2*R, location.y - 3*R);
        }


        /**
         * 一个具有固定尺寸的图形
         */
        class Graph extends JPanel{
            int r;
            public Graph(int r){
//                setBackground(MyColor.color1());
//                setLayout(null);
                setPreferredSize(new Dimension(2*r, 3*r));
                setSize(new Dimension(2*r, 3*r));
                this.r = r;
                setOpaque(false);

            }

            @Override
            public void paint(Graphics g) {
                super.paint(g);
                Polygon p = new Polygon();
                p.addPoint(r, 3*r);
                p.addPoint(0, r);
                p.addPoint(2*r, r);
                g.setColor(MyColor.color3());

                g.fillOval(0, 0 , r*2, r*2);
                g.fillPolygon(p);
            }
        }
    }


}
class EdgeLayer extends JPanel{
    List<Edge> edges = new ArrayList<>();
    public EdgeLayer(){
        setSize(800, 800);
        setLocation(0, 0);
        setOpaque(false);

    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for(Edge e : edges){
            e.paint(g);
        }
    }

}
class Edge {

    int x1, y1, x2, y2;

    public Edge(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public void paint(Graphics g) {
        g.setColor(MyColor.color4());
        Polygon p = new Polygon();
        V2 v1 = new V2(x2 - x1, y2 - y1);
        v1 = v1.rot(Math.PI / 2);
        v1 = v1.changeLen(4);
        p.addPoint(x1 + (int)v1.x, y1 + (int)v1.y);
        p.addPoint(x2 + (int)v1.x, y2 + (int)v1.y);
        p.addPoint(x2 - (int)v1.x, y2 - (int)v1.y);
        p.addPoint(x1 - (int)v1.x, y1 - (int)v1.y);
//            p.addPoint(- (int)v1.x, - (int)v1.y);
//            p.addPoint(x2 - x1 - (int)v1.x, y2 - y1  - (int)v1.y);
//            p.addPoint(x2 -x1 + (int)v1.x, y2 - y1 + (int)v1.y);
//            p.addPoint((int)v1.x,   (int)v1.y);

//            Point p1 = new Point(x1, y1);
//            Point p2 = new Point(x2, y2);

//            p.addPoint();
        g.drawPolygon(p);
        g.fillPolygon(p);
        g.drawLine(0, 0, x2 - x1, y2 - y1);
    }
}