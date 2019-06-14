package Program;

import javax.swing.text.DefaultHighlighter;
import java.awt.*;

public class HighlightPainter extends DefaultHighlighter.DefaultHighlightPainter {
    /**
     * Constructs a new highlight painter. If <code>c</code> is null,
     * the JTextComponent will be queried for its selection color.
     *
     * @param c the color for the highlight
     */
    public HighlightPainter(Color c) {
        super(c);
    }
}
