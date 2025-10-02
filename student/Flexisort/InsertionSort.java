public class InsertionSort extends Sorter {
    @Override public void sort() {
        int[] a = getArray();
        if (a == null) return;
        for (int i=1; i<a.length; i++) {
            for (int j=i; j> 0 && a[j-1] > a[j]; j--) {
                int t = a[j];
                a[j] = a[j-1];
                a[j-1] = t;
            }
        }
    }
}