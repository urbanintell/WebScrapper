

/**
 *
 * @author Code by Kromah,Lusenii
 */
public class Word implements Comparable<Word> {

    private String word;
    boolean isWord;

    public Word(String word) {
        this.word = word;
        this.word = word.replaceAll("[\\W]", "");
        this.word = word.replaceAll("\\s", "");
        this.word = word.toLowerCase();
    }

    public int length() {

        return word.length();
    }

    public boolean hasThreeLetters() {

        if (word.length() <= 3) {

            return false;

        } else {

            return true;

        }

    }

    public boolean isPronoun() {

        //array of pronouns
        String pronouns[] = {"it", "i", "you", "he", "they",
            "we", "she", "who", "them", "me", "him", "one", "her",
            "us", "something", "nothing",
            "anything", "himself", "everything", "someone",
            "themselves", "everyone", "itself", "anyone", "myself", "other"};

        for (int i = 0; i < pronouns.length; i++) {

            if (word.equals(pronouns[i])) {

                return true;
            }
        }
        return false;
    }
    
    public boolean isJavaScript(){
    
        String []javascript={"quot","document","function","scrpit","onload","jquery","scrolling","click"};
           for (int i = 0; i < javascript.length; i++) {

            if (word.equals(javascript[i])) {

                return true;
            }
        }
        return false;
    }

    public boolean isArticle() {

        //array of articles
        String[] articles = {"a", "the", "there", "to", "for", "and", "an", "in",
            "of", "in", "this", "by", "it", "be", "is", "thus", "have", "has", "or",
            "from", "them", "that", "with", "about", "which", "use", "will"};

        for (int i = 0; i < articles.length; i++) {

            if (word.equals(articles[i])) {

                return true;
            }
        }
        return false;

    }

    /**
     *
     * @return
     */
    public boolean isAdverb() {
        String pronouns[] = {"always", "often", "after","also", "already", "last", "next", 
            "soon", "then", "while", "when", "been", "while", "during"};
        for (int i = 0; i < pronouns.length; i++) {
            if (word.equals(pronouns[i])) {
                return true;
            }
        }
        return false;
    }

    public boolean isPreposition() {

        String prepositions[] = {"abroad", "about", "above", "across", "after", "agaisnt",
            "along", "amid", "among", "anti", "around", "as", "at", "before", "behind", "below", 
            "beneath", "besides", "between", "beyond", "but", "by", "concerning", "considering",
            "despite", "down", "during", "except", "excepting", "excluding", "been", "following",
            "for", "from", "here", "in", "inside", "into", "like", "minus", "near", "of", "off", "on", 
            "onto", "opposite", "outside", "over", "past", "per", "pus", "regarding", "round", "save",
            "since", "than", "through", "to", "toward", "towards", "under", "underneath", "unlike", "until",
            "up", "upon", "versus", "via", "within", "without"};

        for (int i = 0; i < prepositions.length; i++) {
            if (word.equals(prepositions[i])) {
                return true;
            }
        }

        return false;
    }

    public boolean isWord() {

        if (isPreposition() == false && isArticle() == false && isPronoun()==false&&isJavaScript()==false
                &&isAdverb()==false  && hasThreeLetters() == true) {
            return true;
        }
        return false;

    }

    public String toString() {

        if (isWord() == true) {

            return word;

        }
        return null;
    }

    @Override
    public int compareTo(Word t) {

        if (this.word.equals(t.toString())) {
            return 0;
        } else if (this.word.compareTo(t.toString()) > 0) {
            return 1;
        } else {
            return -1;
        }
    }
}
