package nodepara;

public class Home {

    private final double FAR = 4;
    private Home previous = null, next = null;
    private double place;
    private boolean enganged = false;

    public Home(double place) {
        this.place = NodePara.reduceDemicals(place);
    }

    public Home getPrevious() {
        return previous;
    }

    public void setPrevious(Home previous) {
        this.previous = previous;
    }

    public Home getNext() {
        return next;
    }

    public double getDistance() {
        if (this.hasPrevious()) {
            return place - getPrevious().getPlace();
        }
        return FAR;
    }

    public void setNext(Home next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return String.format("%.1f ", place);
    }

    public double getPlace() {
        return place;
    }

    public boolean hasNext() {
        return this.getNext() != null ? true : false;
    }

    public boolean hasPrevious() {
        return this.getPrevious() != null ? true : false;
    }

    public boolean isEnganged() {
        return enganged;
    }

    public void setEnganged(boolean enganged) {
        this.enganged = enganged;
    }
}
