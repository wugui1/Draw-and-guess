package fzu.zrf.dng.both.tuple;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BinaryDoubleTuple implements Serializable {
    public final double first;
    public final double second;

    public BinaryDoubleTuple(double first, double second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(first);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(second);
        result = prime * result + (int) (temp ^ (temp >>> 32));
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
        BinaryDoubleTuple other = (BinaryDoubleTuple) obj;
        if (Double.doubleToLongBits(first) != Double.doubleToLongBits(other.first))
            return false;
        if (Double.doubleToLongBits(second) != Double.doubleToLongBits(other.second))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("BinaryDoubleTuple [first=%s, second=%s]", first, second);
    }
}
