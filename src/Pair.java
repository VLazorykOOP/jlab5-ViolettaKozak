abstract class Pair implements Comparable<Pair>  {
    public abstract Pair add(Pair other);
    public abstract Pair subtract(Pair other);
    public abstract Pair multiply(Pair other);

    @Override
    public int compareTo(Pair other){return 0;}


}