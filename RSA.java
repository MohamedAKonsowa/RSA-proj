import java.io.*;
import java.util.*;
import java.math.*;

class RSA {

    public void Decrypt() throws FileNotFoundException {
        File encrypt = new File("Encrypt.txt");
        Scanner s2 = new Scanner(encrypt);
        String read1 = s2.nextLine();
        String[] z = read1.split(" ");
        int d = Integer.parseInt(z[0]);
        int n = Integer.parseInt(z[1]);
        while (s2.hasNextLine()) {
            String read = s2.nextLine();
            String[] x = read.split(" ");
            for (String s : x) {
                int c = Integer.parseInt(s);

                BigInteger N = BigInteger.valueOf(n);
                BigInteger C = BigDecimal.valueOf(c).toBigInteger();
                BigInteger m = (C.pow(d)).mod(N);
                String letters = "~.$,;:'\"\\/[]{}()-_=+/*-+abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ12345678910";
                int h = Integer.parseInt(String.valueOf(m));
                System.out.print(letters.charAt(h));
            }
            System.out.print(" ");
        }
    }

    public void Encrypt() throws FileNotFoundException {
        File decrypt = new File("Decrypt.txt");
        Scanner s2 = new Scanner(decrypt);
        String read1 = s2.nextLine();
        String[] z = read1.split(" ");
        int e = Integer.parseInt(z[0]);
        int n = Integer.parseInt(z[1]);
        while (s2.hasNextLine()) {
            String read = s2.nextLine();
            String[] x = read.split(" ");
            for (String s : x) {
                int m = Integer.parseInt(s);
                BigInteger N = BigInteger.valueOf(n);
                BigInteger M = BigDecimal.valueOf(m).toBigInteger();
                BigInteger c = (M.pow(e)).mod(N);
                System.out.print(c + " ");
            }
            System.out.print(" ");
        }
    }


    public List<Integer> sieveOfEratosthenes(int n) {
        boolean[] prime = new boolean[n + 1];
        Arrays.fill(prime, true);
        for (int p = 2; p * p <= n; p++) {
            if (prime[p]) {
                for (int i = p * 2; i <= n; i += p) {
                    prime[i] = false;
                }
            }
        }
        List<Integer> primeNumbers = new LinkedList<>();
        for (int i = 2; i <= n; i++) {
            if (prime[i]) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }

    int gcd(int e, int z) {
        if (e == 0)
            return z;
        else
            return gcd(z % e, e);
    }

    public void createFile() throws IOException {
        Scanner s1 = new Scanner(System.in);
        s1.useDelimiter("\n");
        File encrypt = new File("Encrypt.txt");
        File decrypt = new File("Decrypt.txt");
        System.out.println("Enter word you want to create");

        String inputX = s1.next();
        String[] msgSplit = inputX.split(" ");
        System.out.println(Arrays.toString(msgSplit));


        FileWriter fileWriter = new FileWriter(decrypt);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        FileWriter fileWriter1 = new FileWriter(encrypt);
        BufferedWriter bufferedWriter1 = new BufferedWriter(fileWriter1);


        Random rand = new Random();

        String letters = "~.$,;:'\"\\/[]{}()-_=+/*-+abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ12345678910";

// p , q are two prime numbers
// n = p*q
// k = p-1 * q-1
// d * e = 1 (mod k)
        int p, q, n, k, d = 0, e, i;
        double c;
        p = sieveOfEratosthenes(1000).get(rand.nextInt(sieveOfEratosthenes(1000).size()));
        q = sieveOfEratosthenes(1000).get(rand.nextInt(sieveOfEratosthenes(1000).size()));
        n = p * q;
        k = (p - 1) * (q - 1);
        for (e = 2; e < k; e++) {
            if (gcd(e, k) == 1) {
                break;
            }
        }

        for (i = 0; i <= 9; i++) {
            int x = 1 + (i * k);
            if (x % e == 0) {
                d = x / e;
                break;
            }
        }
        bufferedWriter.write(e + " " + n + "\n"); // public key
        bufferedWriter1.write(d + " " + n + "\n"); // private key


        for (String s : msgSplit) { // number of words
            for (int j = 0; j < s.length(); j++) { // number of letters in a word
                int msg = letters.indexOf(s.charAt(j));
                c = (Math.pow(msg, e)) % n;
                bufferedWriter1.write((int) c + " ");
/////////////////////////////////////////////////////
                bufferedWriter.write(msg + " ");
///////////////////////////////////////////////////////
            }
            bufferedWriter1.write("\n");
            bufferedWriter.write("\n");

        }
        bufferedWriter.close();
        bufferedWriter1.close();

    }
}

