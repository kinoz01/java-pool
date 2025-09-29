public class BubbleSort extends Sorter {
    @Override
    public void sort() {
        int[] a = getArray();
        if (a == null)
            return;
        for (int i = a.length - 1; i > 0; i--) {
            boolean s = false;
            for (int j = 0; j < i; j++)
                if (a[j] > a[j + 1]) {
                    int t = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = t;
                    s = true;
                }
            if (!s)
                break;
        }
    }
}