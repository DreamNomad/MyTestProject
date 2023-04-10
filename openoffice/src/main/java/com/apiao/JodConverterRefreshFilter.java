package com.apiao;

import com.sun.star.awt.Size;
import com.sun.star.beans.PropertyValue;
import com.sun.star.lang.IllegalArgumentException;
import com.sun.star.lang.XComponent;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.view.PaperFormat;
import com.sun.star.view.XPrintable;
import lombok.extern.slf4j.Slf4j;
import org.jodconverter.core.office.OfficeContext;
import org.jodconverter.local.filter.FilterChain;
import org.jodconverter.local.filter.RefreshFilter;

/**
 * 重写 RefreshFilter
 *
 * @author shanhy
 * @date 2020/12/17 10:05
 */
@Slf4j
public class JodConverterRefreshFilter extends RefreshFilter {

    public final static Size A6, A5, A4, A3, A2, A1, A0;
    public final static Size B6, B5, B4, B3, B2, B1, B0;

    static {
        A6 = new Size(10500, 14800);
        A5 = new Size(14800, 21000);
        A4 = new Size(21000, 29700);
        A3 = new Size(29700, 42000);
        A2 = new Size(42000, 59400);
        A1 = new Size(59400, 84100);
        A0 = new Size(84100, 118900);

        B6 = new Size(12500, 17600);
        B5 = new Size(17600, 25000);
        B4 = new Size(25000, 35300);
        B3 = new Size(35300, 50000);
        B2 = new Size(50000, 70700);
        B1 = new Size(70700, 100000);
        B0 = new Size(100000, 141400);
    }

    /**
     * Creates a new refresh filter.
     */
    public JodConverterRefreshFilter() {
        this(false);
    }

    /**
     * Creates a new refresh filter that will call or not the next filter in the chain according to
     * the specified argument.
     *
     * @param lastFilter If {@code true}, then the filter won't call the next filter in the chain. If
     * {@code false}, the next filter in the chain, if any, will be applied.
     */
    public JodConverterRefreshFilter(final boolean lastFilter) {
        super(lastFilter);
    }

    @Override
    public void doFilter(
            final OfficeContext context,
            final XComponent document,
            final FilterChain chain)
            throws Exception {

        //setPaperInfo(document, new Size(25000, 29700));

        super.doFilter(context, document, chain);
    }

    /**
     * 设置纸张信息
     * 请注意！！！PaperOrientation 和 PaperSize 不可以同时配置，否则有一个无效！
     * <p>
     * The default paper format and orientation is A4 and portrait.
     *
     * @param paperSize
     */
    private void setPaperInfo(final XComponent document, final Size paperSize) throws IllegalArgumentException {

        XPrintable xPrintable = UnoRuntime.queryInterface(XPrintable.class, document);
        PropertyValue[] printerDesc = new PropertyValue[2];

        // Paper Orientation（纵向横向）
//        printerDesc[0] = new PropertyValue();
//        printerDesc[0].Name = "PaperOrientation";
//        printerDesc[0].Value = PaperOrientation.LANDSCAPE;

        // Paper Format
        printerDesc[0] = new PropertyValue();
        printerDesc[0].Name = "PaperFormat";
        printerDesc[0].Value = PaperFormat.USER;

        // Paper Size
        printerDesc[1] = new PropertyValue();
        printerDesc[1].Name = "PaperSize";
        printerDesc[1].Value = paperSize;

        xPrintable.setPrinter(printerDesc);

    }
}