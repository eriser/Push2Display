package push22bitwig.model.grid;

import push22bitwig.LayoutSettings;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Label;


/**
 * An element in the grid which can display on option on top and on the bottom of the element. In
 * the middle two texts can be displayed. The texts are not clipped horizontally and can reach into
 * the next elements.
 * 
 * Licensed under LGPLv3 - http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * @author J&uuml;rgen Mo&szlig;graber
 */
public class OptionsGridElement extends AbstractGridElement
{
    private final String  headerBottom;
    private final String  headerTop;
    private final String  menuBottomName;
    private final boolean isMenuBottomSelected;
    private final boolean useSmallTopMenu;


    /**
     * Constructor.
     *
     * @param headerTop A header for the top menu options (may span multiple grids), may be null
     * @param menuTopName A name for the to menu, may be null
     * @param isMenuTopSelected Is the top menu selected?
     * @param headerBottom A header for the bottom menu options (may span multiple grids), may be
     *            null
     * @param menuBottomName A name for the bottom menu, may be null
     * @param isMenuBottomSelected Is the bottom menu selected?
     * @param useSmallTopMenu Draw the small version of the top menu if true
     */
    public OptionsGridElement (final String headerTop, final String menuTopName, final boolean isMenuTopSelected, final String headerBottom, final String menuBottomName, final boolean isMenuBottomSelected, final boolean useSmallTopMenu)
    {
        super (menuTopName, isMenuTopSelected, null, null, null, false);
        this.headerTop = headerTop;
        this.headerBottom = headerBottom;
        this.menuBottomName = menuBottomName;
        this.isMenuBottomSelected = isMenuBottomSelected;
        this.useSmallTopMenu = useSmallTopMenu;
    }


    /** {@inheritDoc} */
    @Override
    public void draw (final Graphics2D gc, final int left, final int width, final int height, final LayoutSettings layoutSettings)
    {
        final int menuHeight = MENU_HEIGHT * 2;

        if (this.useSmallTopMenu)
            this.drawMenu (gc, left, width, layoutSettings);
        else
            drawLargeMenu (gc, left, 0, width, menuHeight, this.menuName, this.isMenuSelected, layoutSettings);
        drawLargeMenu (gc, left, DISPLAY_HEIGHT - 2 * MENU_HEIGHT, width, menuHeight, this.menuBottomName, this.isMenuBottomSelected, layoutSettings);

        final boolean hasTopHeader = this.headerTop != null && this.headerTop.length () > 0;
        final boolean hasBottomHeader = this.headerBottom != null && this.headerBottom.length () > 0;
        if (!hasTopHeader && !hasBottomHeader)
            return;

        final int headerHeight = (DISPLAY_HEIGHT - 2 * menuHeight) / 2;

        gc.setColor (layoutSettings.getTextColor ());
        gc.setFont (layoutSettings.getTextFont (headerHeight / 2));

        if (hasTopHeader)
            drawTextInHeight (gc, this.headerTop, left, menuHeight, headerHeight);
        if (hasBottomHeader)
            drawTextInHeight (gc, this.headerBottom, left, menuHeight + headerHeight, headerHeight);
    }


    /**
     * Draws a menu at the top of the element.
     *
     * @param gc The graphics context
     * @param left The left bound of the menus drawing area
     * @param top The top bound of the menu
     * @param width The width of the menu
     * @param height The height of the menu
     * @param menu The menu text
     * @param isSelected True if the menu is selected
     * @param layoutSettings The layout settings to use
     */
    protected static void drawLargeMenu (final Graphics2D gc, final int left, final int top, final int width, final int height, final String menu, final boolean isSelected, final LayoutSettings layoutSettings)
    {
        if (menu == null || menu.length () == 0)
            return;

        final Color textColor = layoutSettings.getTextColor ();

        gc.setColor (isSelected ? textColor : layoutSettings.getBackgroundColor ());
        gc.fillRect (left, top, width, height);

        gc.setColor (isSelected ? layoutSettings.getBorderColor () : textColor);
        gc.setFont (layoutSettings.getTextFont (height / 2));
        drawTextInBounds (gc, menu, left, top, width, height, Label.CENTER);
    }
}
