import java.util.ArrayList;

class MinHeap {

    private ArrayList<HuffmanNode> heap;

    public MinHeap() {
        heap = new ArrayList<>();
    }

    public int size() {
        return heap.size();
    }

    public void insert(HuffmanNode node) {
        heap.add(node);
        heapifyUp(heap.size() - 1);
    }

    public HuffmanNode removeMin() {

        if (heap.isEmpty()) {
            return null;
        }

        HuffmanNode min = heap.get(0);
        HuffmanNode last = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }

        return min;
    }

    private void heapifyUp(int index) {

        while (index > 0) {

            int parent = (index - 1) / 2;

            if (heap.get(index).frequency < heap.get(parent).frequency) {

                swap(index, parent);
                index = parent;

            } else {
                break;
            }
        }
    }

    private void heapifyDown(int index) {

        while (true) {

            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int smallest = index;

            if (left < heap.size() &&
                    heap.get(left).frequency < heap.get(smallest).frequency) {
                smallest = left;
            }

            if (right < heap.size() &&
                    heap.get(right).frequency < heap.get(smallest).frequency) {
                smallest = right;
            }

            if (smallest != index) {
                swap(index, smallest);
                index = smallest;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        HuffmanNode temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}