public abstract class Sorter {
    private int[] array;

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] a) {
        this.array = a;
    }

    public abstract void sort();
}