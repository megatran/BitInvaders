package bitinvaders;

import java.awt.Toolkit;

import javax.swing.JOptionPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class DigitDocumentFilter extends DocumentFilter {
    @Override
    public void insertString(DocumentFilter.FilterBypass bypassFilter
            , int offset, String wordTyped, AttributeSet attriSet)
                                throws BadLocationException
    {
        int len = wordTyped.length();
        boolean isValidInt = true;

        for (int i = 0; i < len; i++)
        {
            if (!Character.isDigit(wordTyped.charAt(i)))
            {
                isValidInt = false;
                break;
            }
        }
        if (isValidInt)
            super.insertString(bypassFilter, offset, wordTyped, attriSet);
        else {
            Toolkit.getDefaultToolkit().beep();
    		JOptionPane.showMessageDialog(null,"Please only type Number"  +"!","Input Error",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void replace(DocumentFilter.FilterBypass bypassFilter, int offset
                    , int length, String wordTyped, AttributeSet attriSet)
                                        throws BadLocationException
    {
        int wordLen = wordTyped.length();
        boolean isValidInt = true;

        for (int i = 0; i < wordLen; i++)
        {
            if (!Character.isDigit(wordTyped.charAt(i)))
            {
                isValidInt = false;
                break;
            }
        }
        if (isValidInt)
            super.replace(bypassFilter, offset, length, wordTyped, attriSet);
        else {
            Toolkit.getDefaultToolkit().beep();
    		JOptionPane.showMessageDialog(null,"Please only type Number"  +"!","Input Error",JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
