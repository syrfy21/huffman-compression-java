import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String text = FileManager.readFile("input.txt");

        HuffmanCoding hc = new HuffmanCoding();

        
        hc.analyzeFrequency(text);
        hc.displayFrequencies();

        System.out.println("\nChoose Mode:");
        System.out.println("1. Standard Huffman");
        System.out.println("2. Extended Huffman");

        int choice = input.nextInt();

        if (choice == 1) {
            hc.buildHuffmanTree();
        } else {

            System.out.print("Enter number of pairs k: ");
            int k = input.nextInt();

            hc.buildExtendedTree(k);
        }

        hc.displayCodes();

        
        String compressed = hc.compress(text);

        FileManager.writeFile("compressed.txt", compressed);

        System.out.println("\nCompressed Data:");
        System.out.println(compressed);

        
        String decompressed = hc.decompress(compressed);

        FileManager.writeFile("decompressed.txt", decompressed);

        System.out.println("\nDecompressed Text:");
        System.out.println(decompressed);

        
        int originalBits = text.length() * 8;
        int compressedBits = compressed.length();

        System.out.println("\nOriginal Size: " + originalBits + " bits");
        System.out.println("Compressed Size: " + compressedBits + " bits");

        double ratio = ((double) compressedBits / originalBits) * 100;

        System.out.println("Compression Ratio: " + ratio + "%");

        input.close();
    }
}