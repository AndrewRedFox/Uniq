public class Pair {

    public String first;
    public int second;

    public Pair(String str, int b) {
        first = str;
        second = b;
    }

    public Pair() {

    }

    public void set(String a, int b) {
        first = a;
        second = b;
    }

    public void set(int a) {
        second = a;
    }

    public String printWithPrefics() {
        StringBuilder str = new StringBuilder();
        return str.append(first + " ").append(second).toString();
    }
    public String printNonPrefics() {
        StringBuilder str = new StringBuilder();
        return str.append(first).toString();
    }

}


