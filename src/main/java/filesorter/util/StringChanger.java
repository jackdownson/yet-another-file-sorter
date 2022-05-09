package filesorter.util;

public final class StringChanger {

    private StringChanger() {

    }

    public static String cleanStringFrom(String string, String symbols){
        String modified = null;

        if (symbols == null || symbols.isEmpty() ) {
            throw new RuntimeException("Can't modify string");
        }

        String[] symbolsArr = symbols.split("");
        for (String s : symbolsArr) {
            modified = string.replaceAll(s, "");
        }

        return modified;
    }
}
