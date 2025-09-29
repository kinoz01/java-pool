public class InsertionSort extends Sorter {
    @Override
    public void sort() {
        int[] a = getArray();
        if (a == null)
            return;
        for (int i = 1; i < a.length; i++) {
            int k = a[i], j = i - 1;
            while (j >= 0 && a[j] > k) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = k;
        }
    }
}