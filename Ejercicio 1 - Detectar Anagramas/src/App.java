import java.util.Arrays;
public class App {

    public static String sortString(String inputString)
    {
        char tempArray[]=inputString.toCharArray();
        Arrays.sort(tempArray);
        return new String(tempArray);
    }
    public static void main(String[] args) throws Exception {
        // Variables
        String Word1="Animal";
        String Word2="Manila";
        String nWord1=sortString(Word1.toLowerCase());
        String nWord2=sortString(Word2.toLowerCase());

        // Print de Valores de variables
        System.out.println("Palabra1 = "+Word1);
        System.out.println("Palabra2 = "+Word2);
        System.out.println("Palabra Ordenada1 = "+nWord1);
        System.out.println("Palabra Ordenada2 = "+nWord2);

        // Detección de Anagrama
        if (nWord1.equals(nWord2)) System.out.println("Resultado: Son anagramas");
        else System.out.println("Resultado: No son anagramas");
    }
}
