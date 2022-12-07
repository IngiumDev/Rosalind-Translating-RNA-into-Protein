import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class RNAtoProtein {
    public static void main(String[] args) {
        String RNAfile = "src/Rosalind-prot.txt";
        String RNA = readRNA(RNAfile);
        String filename = "src/RNA-Codons.txt";
        System.out.println(RNAtoProtein(RNA, filename));
    }

    /**
     * Purpose: Convert RNA to protein
     *
     * @param RNA      - RNA string
     * @param filename - file containing codon table
     * @return protein string
     */
    public static String RNAtoProtein(String RNA, String filename) {
        StringBuilder protein = new StringBuilder();
        HashMap<String, String> RNAtoProteinTable = getRNAtoProteinTable(filename);
        // Reads 3 characters at a time from the RNA string and gets the protein from the hashmap
        for (int i = 0; i < RNA.length(); i += 3) {
            String codon = RNA.substring(i, i + 3);
            // Get the protein from the RNA-to-protein table based on the codon and add it to the protein string
            // Checks if the codon is a stop codon and then breaks out of the loop
            String proteinString = RNAtoProteinTable.get(codon);
            if (proteinString.equals("Z")) {
                break;
            } else {
                protein.append(proteinString);
            }
            protein.append(proteinString);
        }
        return protein.toString();
    }

    /**
     * @param filename - file containing codon table
     * @return HashMap containing codon table
     */
    public static HashMap<String, String> getRNAtoProteinTable(String filename) {
        HashMap<String, String> RNAtoProteinTable = new HashMap<String, String>();
        // Create Arraylist for the lines of the input
        ArrayList<String> lines = new ArrayList<String>();
        // Read the codon table from the file
        String nextline = "";
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            while ((nextline = br.readLine()) != null) {
                lines.add(nextline);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        // Parse the lines into the HashMap
        for (String line : lines) {
            String[] codon = line.split(" ");
            RNAtoProteinTable.put(codon[0], codon[1]);
            RNAtoProteinTable.put(codon[7], codon[8]);
            RNAtoProteinTable.put(codon[14], codon[15]);
            RNAtoProteinTable.put(codon[21], codon[22]);
        }

        return RNAtoProteinTable;
    }

    /**
     * @param filename - file containing RNA string
     * @return RNA string from file
     */
    public static String readRNA(String filename) {
        StringBuilder RNA = new StringBuilder();
        String nextline = "";
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            while ((nextline = br.readLine()) != null) {
                RNA.append(nextline);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return RNA.toString();
    }
}