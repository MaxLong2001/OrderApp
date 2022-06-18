package frontend.Tool;

public class V2 {
    public double x, y;

    public V2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public V2 rot(double fai){
        return new V2(x*Math.cos(fai) - y*Math.sin(fai), y*Math.cos(fai) + x*Math.sin(fai));
    }
    public double getLen(){
        return Math.sqrt(x * x + y * y);
    }
    public V2 changeLen(double a){
        return new V2(x * a / getLen(), y * a /getLen());
    }
}
