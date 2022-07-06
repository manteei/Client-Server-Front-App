package Grafic;

import java.awt.*;
import java.awt.geom.*;

public class Visualize {
    private String username;
    private String name;
    private String id;
    private String x;
    private String y;
    private String creationDate;
    private String minimal_point;
    private String difficulty;
    private String authorname;
    private String weight;
    private String passportid;
    private String haircolor;
    private String nationality;
    private Head had;

    int hatX;
    int hatY;
    int size;

    public Visualize(String username, String name, String id, String x, String y, String creationDate, String minimal_point, String difficulty,
                     String authorname, String weight, String passportid, String haircolor, String nationality) {
        this.username = username;
        this.name = name;
        this.id = id;
        this.x = x;
        this.y = y;
        this.creationDate = creationDate;
        this.minimal_point = minimal_point;
        this.difficulty = difficulty;
        this.authorname = authorname;
        this.weight = weight;
        this.passportid = passportid;
        this.haircolor = haircolor;
        this.nationality = nationality;

        hatX = (int) (Long.parseLong(x))*5 + 300;
        hatY = (int) (Long.parseLong(y))*5 + 300;
        try {
            size = 30 * (int) (Double.parseDouble(minimal_point));
        }catch (NumberFormatException e){
            size = 1;
            // значения по умолчанию
        }
        had = new Head(new Point(hatX, hatY), size, size, 30, 400);

    }


    /**
     * Метод рисует элементы
     *
     * @param g2
     * @param color
     */
    public void drawHat(Graphics2D g2, Color color) {
        g2.setColor(color);
        g2.fill(had);
        g2.draw(had);



        g2.setColor(Color.BLACK);
        g2.drawOval(hatX, hatY, size, size);
        Ellipse2D ec1 = new Ellipse2D.Float(hatX+size/4, hatY+size/2-size/30, size/8, size/8);
        g2.draw(ec1);
        g2.setPaint(Color.white);
        g2.fill(ec1);

        Ellipse2D ec2 = new Ellipse2D.Float(hatX+size/2+size/30, hatY+size/2-size/30, size/8, size/8);
        g2.draw(ec2);
        g2.setPaint(Color.white);
        g2.fill(ec2);

        g2.setColor(Color.BLACK);
        g2.drawOval(hatX+size/4, hatY+size/2-size/30, size/8, size/8);
        g2.drawOval(hatX+size/2+size/30, hatY+size/2-size/30, size/8, size/8);

        g2.setColor(Color.BLACK);
        g2.drawOval(hatX, hatY, size, size);
        Ellipse2D ec3 = new Ellipse2D.Float(hatX+size/2+size/30, hatY+size/2-size/30, size/12, size/12);
        g2.draw(ec3);
        g2.setPaint(Color.black);
        g2.fill(ec3);

        Ellipse2D ec4 = new Ellipse2D.Float(hatX+size/3-size/20, hatY+size/2-size/30, size/12, size/12);
        g2.draw(ec4);
        g2.setPaint(Color.black);
        g2.fill(ec4);

        g2.drawLine(hatX, hatY+size/2, hatX + size/3  ,hatY+size/2+size/5 + size/5);

        Rectangle2D r2D = new Rectangle2D.Float(hatX-size/10, hatY+size/2+size/5, size/2 + size/5, size/2-size/5);
        g2.draw(r2D);
        g2.setPaint(Color.lightGray);
        g2.fill(r2D);

        g2.setColor(Color.BLACK);
        g2.drawLine(hatX + size/2+size/2, hatY+size/2, hatX + size/2 + size/6 ,hatY+size/2+size/5 + size/10);
        g2.drawLine(hatX + size/2 + size/6 ,hatY+size/2+size/5 + size/10, hatX + size/2 + size/10 ,hatY+size/2+size/5);

    }




    public String getUsername() {
        return username;
    }

    public String getId() {
        return id;
    }

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }

    public String getName() {
        return name;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getMinimal_point() {
        return minimal_point;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getAuthorname() {
        return authorname;
    }

    public String getWeight() {
        return weight;
    }

    public String getHaircolor() {
        return haircolor;
    }

    public String getNationality() {
        return nationality;
    }

    public String getPassportid() {
        return passportid;
    }



    public Head getHad() {
        return had;
    }


    class Head extends Arc2D {
        private Point point;
        private double w;
        private double h;
        private double start;
        private double extent;


        public Head(Point point, double w, double h, double start, double extent) {
            super(Arc2D.PIE);
            this.point = point;
            this.w = w;
            this.h = h;
            this.start = start;
            this.extent = extent;
        }

        @Override
        public double getAngleStart() {
            return start;
        }

        @Override
        public double getAngleExtent() {
            return extent;
        }

        @Override
        public void setArc(double x, double y, double w, double h, double angSt, double angExt, int closure) {
            point.setLocation(x, y);
            this.w = w;
            this.h = h;
            this.start = angSt;
            this.extent = angExt;
        }

        @Override
        public void setAngleStart(double angSt) {
            this.start = angSt;
        }

        @Override
        public void setAngleExtent(double angExt) {
            this.extent = angExt;
        }

        @Override
        protected Rectangle2D makeBounds(double x, double y, double w, double h) {
            return null;
        }

        @Override
        public double getX() {
            return point.x;
        }

        @Override
        public double getY() {
            return point.y;
        }

        @Override
        public double getWidth() {
            return w;
        }

        @Override
        public double getHeight() {
            return h;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }


    }

}
