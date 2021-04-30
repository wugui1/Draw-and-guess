package fzu.zrf.dng.client.gui.component;

import java.util.LinkedList;

import fzu.zrf.dng.both.tuple.BinaryDoubleTuple;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DngCanvas extends Canvas {
    int count = 0;

    SimpleObjectProperty<Color> nowColor = new SimpleObjectProperty<Color>();

    SimpleDoubleProperty nowSize = new SimpleDoubleProperty();

    GraphicsContext g = getGraphicsContext2D();

    class PainInfo {
        final Color color;
        final double size;
        final LinkedList<BinaryDoubleTuple> xys = new LinkedList<>();

        public PainInfo() {
            color = nowColor.get();
            size = nowSize.get();
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((color == null) ? 0 : color.hashCode());
            long temp;
            temp = Double.doubleToLongBits(size);
            result = prime * result + (int) (temp ^ (temp >>> 32));
            result = prime * result + ((xys == null) ? 0 : xys.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            PainInfo other = (PainInfo) obj;
            if (color == null) {
                if (other.color != null)
                    return false;
            } else if (!color.equals(other.color))
                return false;
            if (Double.doubleToLongBits(size) != Double.doubleToLongBits(other.size))
                return false;
            if (xys == null) {
                if (other.xys != null)
                    return false;
            } else if (!xys.equals(other.xys))
                return false;
            return true;
        }

        @Override
        public String toString() {
            return String.format("PainInfo [color=%s, size=%s, xys=%s]", color, size, xys);
        }
    }

    final LinkedList<PainInfo> pis = new LinkedList<>();
    PainInfo nowPi = null;

    public DngCanvas() {
        nowSize.addListener(evt -> {
            g.setLineWidth(nowSize.get() * Math.min(getWidth(), getHeight()));
        });
        nowColor.addListener(evt -> g.setStroke(nowColor.get()));

        widthProperty().addListener(evt -> refresh());
        heightProperty().addListener(evt -> refresh());

        setOnMousePressed(evt -> {
            nowPi = new PainInfo();
            pis.add(nowPi);

            g.beginPath();
            double x = evt.getX() / getWidth();
            double y = evt.getY() / getHeight();

            nowPi.xys.add(new BinaryDoubleTuple(x, y));

            g.moveTo(x * getWidth(), y * getHeight());
            g.lineTo(x * getWidth(), y * getHeight());
            g.stroke();

            count = 0;
        });
        setOnMouseDragged(evt -> {
            count++;
            if (count % 20 == 0) {
                double x = evt.getX() / getWidth();
                double y = evt.getY() / getHeight();
                nowPi.xys.add(new BinaryDoubleTuple(x, y));

                g.lineTo(x * getWidth(), y * getHeight());
                g.stroke();
            }
        });
        setOnMouseReleased(evt -> {
            g.closePath();
        });
    }

    public void setColor(Color color) {
        nowColor.set(color);
    }

    public void setSize(double size) {
        nowSize.set(size);
    }

    private void refresh() {
        Color color = nowColor.get();
        double size = nowSize.get();
        g.clearRect(0, 0, getWidth(), getHeight());
        for (PainInfo pi : pis) {
            nowColor.set(pi.color);
            nowSize.set(size);
            g.beginPath();
            BinaryDoubleTuple first = pi.xys.getFirst();
            g.moveTo(first.first * getWidth(), first.second * getHeight());
            for (BinaryDoubleTuple xy : pi.xys) {
                g.lineTo(xy.first * getWidth(), xy.second * getHeight());
                g.stroke();
            }
            g.closePath();
        }
        nowColor.set(color);
        nowSize.set(size);
    }

    @Override
    public boolean isResizable() {
        return true;
    }

    @Override
    public double prefWidth(double var1) {
        return getWidth();
    }

    @Override
    public double prefHeight(double var1) {
        return getHeight();
    }
}
