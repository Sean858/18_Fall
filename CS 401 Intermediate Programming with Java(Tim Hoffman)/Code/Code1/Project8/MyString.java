public class MyString
{
    private char[] letters;

    public MyString( String other )
    {	
        letters = new char[other.length()];
        System.arraycopy(other.toCharArray(), 0, letters, 0, other.length());
    }

    public MyString( MyString other )
    {	
        this(other.toString());
    }
    public int length()
    {
        return letters.length;
    }
    public char charAt(int index) // IF INDEX OUT OF BOUNDS. EXIT PROGRAM! (dont return the null char)
    {
        if(index < 0 && index >= this.length()) System.exit(0);
        return letters[index];
        // THE null CHAR JUST TO MAKE It COMPILE
    }
    int compareTo(MyString other)
    {
        int m = other.length() < this.length() ? other.length() : this.length();
        for(int i = 0; i < m; i++) {
            if (this.charAt(i) < other.charAt(i)){
                return -1;
            }
            if (this.charAt(i) > other.charAt(i)) {
                return 1;
            }
        }
        if (this.length() == other.length()) return 0;
        else return (this.length() - other.length()) > 0 ? 1 : -1;
    }
    public boolean equals(MyString other)
    {
        return this.compareTo(other) == 0;
    }
    // LOOKING for c but starting at [startIndex],  not at [0]
    // You should use this in your other Indexof Method
    public int indexOf(int startIndex, char ch)
    {
        for(int i = startIndex; i < this.length(); i++) {
            if (this.charAt(i) == ch) {
                return i;
            }
        }
        return -1;
    }
    public int indexOf(MyString other)
    {	// RE-USE the indexOf( int startIndex, char ch) method above in here
        int i = 0;
        while( i < this.length() - other.length() + 1) 
        {
            int j = this.indexOf(i, other.charAt(0));
            if (j == -1) break;
            for (int k = 0; k < other.length(); k++) 
            {
                if (this.charAt(j + k) != other.charAt(k)) 
                {
                    break;
                }
                if(k == other.length() - 1) 
                {
                    return j;
                }
            }
            i = j + 1 ;
        }
        return -1;
    }

    public String toString()
    {
        return new String(letters);
    }
} // END MYSTRING CLASS
