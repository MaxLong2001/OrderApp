package frontend.Map;

import backend.Owner;
import frontend.Tool.MyColor;
import frontend.Tool.MyView;
import frontend.Tool.V2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * 一个地图应该包括三层
 * 地图层（包括本城市地图的可视化显示）
 * 地点层（显示饭店，点击可弹出详情并进入）
 * 添加层（商家可以设置自己的位置和路线）
 *
 */
public class Map extends MyView {
    /**
     * 地图的实际尺寸
     */
    int width = 1000;
    int height = 1000;

    public Map() {
        setLayout(null);
        setPreferredSize(new Dimension(width, height));

        Point node1 = new Point(100, 100);
        Point node2 = new Point(100, 200);
        Point node3 = new Point(200, 200);
        Point node4 = new Point(200, 100);
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(node1, node2));
        edges.add(new Edge(node2, node3));
        edges.add(new Edge(node3, node4));
        edges.add(new Edge(node4, node1));
        edges.add(new Edge(node1, node3));

        MapStruct mapStruct = new MapStruct();
        mapStruct.edges = edges;
        mapStruct.compute();
        System.out.println(mapStruct.factor);

//        EdgeLayer edgeLayer = new EdgeLayer();
//        edgeLayer.addEdge(new Edge(100, 100, 200, 200));

        add(new PlotLayer(100, 100, "黑猫厨房"));
        add(new PlotLayer(200, 200, "黑猫厨房"));
//        add(edgeLayer);




        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println(e.getX());
            }
        });


    }

    /**
     * 先向该类加入所有街道和地点，然后计算
     */
    class MapStruct {
        public List<Edge> edges;
        public List<frontend.Map.Plot> plots = new ArrayList<>();
        int xOriginSize;
        int yOriginSize;
        int xOffset;
        int yOffset;
        double factor;
        void compute(){
            //先找到最小和最大的横纵坐标
            int minx = 0x3fffffff;
            int maxx = 0xffffffff;
            int miny = 0x3fffffff;
            int maxy = 0xffffffff;
            for(Edge e : edges){
                minx = Math.min(e.end.x, minx);
                minx = Math.min(e.start.x, minx);
                miny = Math.min(e.end.y, miny);
                miny = Math.min(e.start.y, miny);
                maxx = Math.max(e.end.x, maxx);
                maxx = Math.max(e.start.x, maxx);
                maxy = Math.max(e.end.y, maxy);
                maxy = Math.max(e.start.y, maxy);
                xOriginSize = maxx - minx;
                yOriginSize = maxy - miny;
                xOffset = - minx + 20;
                yOffset = - miny + 20;

                factor = Math.max(width, height) * 1.0 / Math.min(xOriginSize, yOriginSize);
            }
            for(Edge e : edges){
                e.x1 = (int)((e.start.x + xOffset) * factor);
                e.x2 = (int)((e.end.x + xOffset) * factor);
                e.y1 = (int)((e.start.y + yOffset) * factor);
                e.y2 = (int)((e.end.y + yOffset) * factor);
            }
            for (Plot p : plots){
                double dx = Math.sqrt((p.distance + p.edge.end.y - p.edge.start.y) *
                        (p.distance - p.edge.end.y + p.edge.start.y));
                double xOrigin = p.edge.start.x + (p.edge.start.x > p.edge.end.x ? - dx : dx);
                double dy = Math.sqrt((p.distance + p.edge.end.x - p.edge.start.x) *
                        (p.distance - p.edge.end.x + p.edge.start.x));
                double yOrigin = p.edge.start.y + (p.edge.start.y > p.edge.end.y ? - dy : dy);
                p.x = (int)((xOrigin + xOffset) * factor);
                p.y = (int)((yOrigin + yOffset) * factor);
            }
        }

    }
    class PlotLayer extends JPanel{

        /**为0或者1，小或者大*/
        private int state = 0;
        private int smallR = 20;
        private int bigR = 30;
        /**图形的半径*/
        private int R = smallR;

        /**文字区域的宽度*/
        private int width = smallR * 4;

        public PlotLayer(int x, int y, String name) {
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
class Plot {
    /**
     * 实际位置
     */
    int x;
    int y;

    Edge edge;
    /**
     * 与edge的起点的距离
     */
    int distance;
    Owner owner;
}

class Edge {
    Point start;
    Point end;
    String edgeName;

    /**
     * 实际位置
     */
    int x1;
    int y1;
    int x2;
    int y2;

    public Edge(Point start, Point end) {
        this.start = start;
        this.end = end;
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
        g.drawPolygon(p);
        g.fillPolygon(p);
        g.drawLine(0, 0, x2 - x1, y2 - y1);
    }
}
