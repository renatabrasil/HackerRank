import java.util.Arrays;
import java.util.Scanner;

public class LeftRotation {



    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
    	
    	long start = System.currentTimeMillis();  
    	
        String[] nd = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int[] a = new int[n];

        String[] aItems = scanner.nextLine().split(" ");
//        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        
        System.out.println("Começou...");
        
        String[] prefix = Arrays.copyOfRange(aItems, d, n);
        String[] sufix = Arrays.copyOfRange(aItems, 0, d);
        
        String[] resultado = Arrays.copyOf(prefix, prefix.length + sufix.length);
        System.arraycopy(sufix, 0, resultado, prefix.length, sufix.length);
        
        System.out.println(Arrays.toString(resultado).replace("[", "").replace("]", "").replace(",", ""));
        
        long elapsedTime = System.currentTimeMillis() - start;
        System.out.println("\nTempo de execução: "+elapsedTime+ " ms");
    }
}
