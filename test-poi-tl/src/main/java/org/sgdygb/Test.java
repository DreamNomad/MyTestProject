package org.sgdygb;

public class Test {

    /**
     * text : Sayi style :
     * {"strike":false,"bold":true,"italic":false,"color":"00FF00","underLine":false,"fontFamily":"微软雅黑","fontSize":12,"highlightColor":"green","vertAlign":"superscript","characterSpacing":20}
     */

    private String text;
    private StyleBean style;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public StyleBean getStyle() {
        return style;
    }

    public void setStyle(StyleBean style) {
        this.style = style;
    }

    public static class StyleBean {
        /**
         * strike : false bold : true italic : false color : 00FF00 underLine : false fontFamily : 微软雅黑 fontSize : 12
         * highlightColor : green vertAlign : superscript characterSpacing : 20
         */

        private boolean strike;
        private boolean bold;
        private boolean italic;
        private String color;
        private boolean underLine;
        private String fontFamily;
        private int fontSize;
        private String highlightColor;
        private String vertAlign;
        private int characterSpacing;

        public boolean isStrike() {
            return strike;
        }

        public void setStrike(boolean strike) {
            this.strike = strike;
        }

        public boolean isBold() {
            return bold;
        }

        public void setBold(boolean bold) {
            this.bold = bold;
        }

        public boolean isItalic() {
            return italic;
        }

        public void setItalic(boolean italic) {
            this.italic = italic;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public boolean isUnderLine() {
            return underLine;
        }

        public void setUnderLine(boolean underLine) {
            this.underLine = underLine;
        }

        public String getFontFamily() {
            return fontFamily;
        }

        public void setFontFamily(String fontFamily) {
            this.fontFamily = fontFamily;
        }

        public int getFontSize() {
            return fontSize;
        }

        public void setFontSize(int fontSize) {
            this.fontSize = fontSize;
        }

        public String getHighlightColor() {
            return highlightColor;
        }

        public void setHighlightColor(String highlightColor) {
            this.highlightColor = highlightColor;
        }

        public String getVertAlign() {
            return vertAlign;
        }

        public void setVertAlign(String vertAlign) {
            this.vertAlign = vertAlign;
        }

        public int getCharacterSpacing() {
            return characterSpacing;
        }

        public void setCharacterSpacing(int characterSpacing) {
            this.characterSpacing = characterSpacing;
        }
    }
}
