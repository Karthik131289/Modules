package net.swingx.component;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.LinearGradientPaint;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.geom.RoundRectangle2D;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;

import com.alee.extended.painter.AbstractPainter;
import com.alee.utils.GraphicsUtils;

/**
 * Custom SeaGlass button painter.
 */
public class SeaGlassButtonPainter extends AbstractPainter<AbstractButton>
{
    /**
     * Border colors.
     */
    protected Color topBorder = new Color ( 140, 176, 220 );
    protected Color bottomBorder = new Color ( 94, 138, 188 );
    protected Color topPressedBorder = new Color ( 83, 129, 186 );
    protected Color bottomPressedBorder = new Color ( 69, 124, 186 );
    protected Color topDisabledBorder = new Color ( 190, 207, 230 );
    protected Color bottomDisabledBorder = new Color ( 171, 192, 217 );
    protected float[] borderFractions = { 0f, 1f };

    /**
     * Background colors.
     */
    protected Color sideBg = Color.WHITE;
    protected Color middleBg = new Color ( 216, 233, 247 );
    protected Color sidePressedBg = new Color ( 174, 190, 206 );
    protected Color middlePressedBg = new Color ( 112, 146, 180 );
    protected Color sideSelectedBg = new Color ( 190, 206, 221 );
    protected Color middleSelectedBg = new Color ( 135, 174, 207 );
    protected Color sideDisabledBg = Color.WHITE;
    protected Color middleDisabledBg = new Color ( 242, 248, 252 );
    protected float[] bgFractions = { 0f, 0.55f, 1f };

    /**
     * Focus color.
     */
    protected Color focus = new Color ( 122, 166, 205, 200 );

    /**
     * Constructs new SeaGlass button painter.
     */
    public SeaGlassButtonPainter ()
    {
        super ();
        margin = new Insets ( 5, 5, 5, 5 );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paint ( final Graphics2D g2d, final Rectangle bounds, final AbstractButton c )
    {
        final ButtonModel buttonModel = c.getModel ();

        // Focus
        if ( c.isFocusOwner () )
        {
            GraphicsUtils
                    .drawShade ( g2d, new RoundRectangle2D.Double ( 2, 2, c.getWidth () - 5, c.getHeight () - 5, 6, 6 ), focus, 2 );
        }

        // Background
        g2d.setPaint ( getBackgroundPaint ( c, buttonModel ) );
        g2d.fillRoundRect ( 2, 2, c.getWidth () - 4, c.getHeight () - 4, 7, 7 );

        // Border
        g2d.setPaint ( getBorderPaint ( c, buttonModel ) );
        g2d.drawRoundRect ( 2, 2, c.getWidth () - 5, c.getHeight () - 5, 6, 6 );
    }

    /**
     * Returns background paint.
     *
     * @param button button
     * @param model  button model
     * @return background paint
     */
    protected Paint getBackgroundPaint ( final AbstractButton button, final ButtonModel model )
    {
        final Color[] colors;
        if ( !button.isEnabled () )
        {
            colors = new Color[]{ sideDisabledBg, middleDisabledBg, sideDisabledBg };
        }
        else if ( model.isPressed () )
        {
            colors = new Color[]{ sidePressedBg, middlePressedBg, sidePressedBg };
        }
        else if ( model.isSelected () )
        {
            colors = new Color[]{ sideSelectedBg, middleSelectedBg, sideSelectedBg };
        }
        else
        {
            colors = new Color[]{ sideBg, middleBg, sideBg };
        }
        return new LinearGradientPaint ( 0, 2, 0, button.getHeight () - 2, bgFractions, colors );
    }

    /**
     * Returns border paint.
     *
     * @param button button
     * @param model  button model
     * @return border paint
     */
    protected Paint getBorderPaint ( final AbstractButton button, final ButtonModel model )
    {
        final Color[] colors;
        if ( !button.isEnabled () )
        {
            colors = new Color[]{ topDisabledBorder, bottomDisabledBorder };
        }
        else if ( model.isPressed () || model.isSelected () )
        {
            colors = new Color[]{ topPressedBorder, bottomPressedBorder };
        }
        else
        {
            colors = new Color[]{ topBorder, bottomBorder };
        }
        return new LinearGradientPaint ( 0, 2, 0, button.getHeight () - 2, borderFractions, colors );
    }
}