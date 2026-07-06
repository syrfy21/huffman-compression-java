import java.util.*;

class HuffmanCoding {

    private Map<Character, Integer> frequencyMap;
    private Map<Character, String> huffmanCodes;
    private HuffmanNode root;

    public HuffmanCoding() {
        frequencyMap = new HashMap<>();
        huffmanCodes = new HashMap<>();
    }

    
    public void analyzeFrequency(String text) {

        for (char c : text.toCharArray()) {

            frequencyMap.put(c,
                    frequencyMap.getOrDefault(c, 0) + 1);
        }
    }

    public void displayFrequencies() {

        System.out.println("Character Frequencies:");

        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {

            if (entry.getKey() == ' ') {
                System.out.println("SPACE : " + entry.getValue());
            } else if (entry.getKey() == '\n') {
                System.out.println("NEWLINE : " + entry.getValue());
            } else {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        }
    }

    
    public void buildHuffmanTree() {

        MinHeap heap = new MinHeap();

        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            heap.insert(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        while (heap.size() > 1) {

            HuffmanNode left = heap.removeMin();
            HuffmanNode right = heap.removeMin();

            HuffmanNode merged =
                    new HuffmanNode(left.frequency + right.frequency,
                            left,
                            right);

            heap.insert(merged);
        }

        root = heap.removeMin();

        generateCodes(root, "");
    }

    
    public void buildExtendedTree(int k) {

        MinHeap heap = new MinHeap();

        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            heap.insert(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        while (heap.size() > 1) {

            ArrayList<HuffmanNode> newNodes = new ArrayList<>();

            for (int i = 0; i < k; i++) {

                if (heap.size() < 2)
                    break;

                HuffmanNode left = heap.removeMin();
                HuffmanNode right = heap.removeMin();

                HuffmanNode merged =
                        new HuffmanNode(left.frequency + right.frequency,
                                left,
                                right);

                newNodes.add(merged);
            }

            for (HuffmanNode node : newNodes) {
                heap.insert(node);
            }
        }

        root = heap.removeMin();

        huffmanCodes.clear();
        generateCodes(root, "");
    }

    private void generateCodes(HuffmanNode node, String code) {

        if (node == null)
            return;

        if (node.isLeaf()) {
            huffmanCodes.put(node.character, code);
        }

        generateCodes(node.left, code + "0");
        generateCodes(node.right, code + "1");
    }

    public void displayCodes() {

        System.out.println("\nHuffman Codes:");

        for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {

            if (entry.getKey() == ' ') {
                System.out.println("SPACE : " + entry.getValue());
            } else {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        }
    }

    public String compress(String text) {

        StringBuilder compressed = new StringBuilder();

        for (char c : text.toCharArray()) {
            compressed.append(huffmanCodes.get(c));
        }

        return compressed.toString();
    }

    public String decompress(String compressedText) {

        StringBuilder result = new StringBuilder();

        HuffmanNode current = root;

        for (char bit : compressedText.toCharArray()) {

            if (bit == '0') {
                current = current.left;
            } else {
                current = current.right;
            }

            if (current.isLeaf()) {
                result.append(current.character);
                current = root;
            }
        }

        return result.toString();
    }
}