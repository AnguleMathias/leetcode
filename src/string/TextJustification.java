package string;

import java.util.ArrayList;
import java.util.List;

/**
Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

For example,
words: ["This", "is", "an", "example", "of", "text", "justification."]
L: 16.

Return the formatted lines as:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Note: Each word is guaranteed not to exceed L in length.
*/

public class TextJustification
{
    public List<String> fullJustify( String[] words, int maxWidth )
    {
       List<String> justifiedLines = new ArrayList<>();
       int wordPos = 0;
       while ( wordPos < words.length )
       {
    	   // calculate num of words in this line
    	   int startPos = wordPos;
    	   int endPos = wordPos;
    	   int usedCharNum = 0;
    	   while ( endPos < words.length 
    			   && usedCharNum + words[endPos].length() <= maxWidth )
    	   {
    		   // if word is equal to remaining space
    		   usedCharNum += words[endPos].length();
    		   endPos++;
    		   if ( usedCharNum == maxWidth )
    		   {
    			   break;
    		   }
    		   
    		   // if word is smaller than remaining space
    		   usedCharNum += 1;  
    	   }
    	   int numWords = endPos - startPos;

    	   // handle edge case: single word a line
    	   if ( numWords == 1 )
    	   {
    		   StringBuilder currLine = new StringBuilder();
    		   currLine.append( words[startPos] );
    		   int spaces = maxWidth - words[startPos].length();
    		   for ( int i = 0; i < spaces; i++ )
    		   {
    			   currLine.append(' ');
    		   }
    		   wordPos = endPos;
    		   continue;
    	   }
    	   
    	   // calculate how to disperse space
    	   int numSpaces = maxWidth;
    	   for ( int currPos = startPos; currPos < endPos; currPos++ )
    	   {
    		   numSpaces -= words[currPos].length();
    	   }    	   
    	   int averageSpaces = numSpaces / ( numWords - 1 );
    	   int additionSpaces = numSpaces % ( numWords - 1 );
    	       	   
    	   StringBuilder currLine = new StringBuilder();
    	   for ( int currPos = startPos; currPos < endPos; currPos++ )
    	   {
    		   // append word
    		   currLine.append( words[currPos] );
    		   
    		   if ( currPos != endPos - 1 )
    		   {
	    		   // append space
	    		   for ( int i = 0; i < averageSpaces; i++ )
	    		   {
	    			   currLine.append(' ');
	    		   }
	    		   
	    		   // append additional space
	    		   if ( additionSpaces > 0 )
	    		   {
	    			   currLine.append(' ');
	    			   additionSpaces--;
	    		   }
    		   }    		   
    	   }
    	   
    	   // build this line
    	   justifiedLines.add( currLine.toString() );
       }
       
       return justifiedLines;
    }
}
