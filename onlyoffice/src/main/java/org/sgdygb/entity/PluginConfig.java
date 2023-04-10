package org.sgdygb.entity;

import java.util.List;

/**
 * 插件config
 *
 * @author Administrator
 * @date 2023/03/07
 */
public class PluginConfig {

    /**
     * baseUrl :
     * guid : asc.{FFE1F462-1EA2-4391-990D-4CC84940B754}
     * help :
     * minVersion : 6.3.0
     * name : plugin name
     * nameLocale : {"en":"English plugin name","zh":"Chinese plugin name"}
     * variations : [{"buttons":[{"text":"Cancel","primary":false,"isviewer":false,"textLocale":{"en":"Cancel","zh":"取消"}}],"cryptoDisabledForExternalCloud":"true","cryptoDisabledForInternalCloud":"true","cryptoDisabledOnStart":"true","cryptoMode":"1","description":"plugin description","descriptionLocale":{"en":"English plugin description","zh":"Chinese plugin description"},"EditorsSupport":["word","cell","slide"],"events":["onClick"],"icons":["icon.png","icon@2x.png"],"icons2":[{},{"style":"dark"}],"initData":"","initDataType":"ole","initOnSelectionChanged":true,"isCustomWindow":true,"isDisplayedInViewer":true,"isInsideMode":false,"isModal":true,"isSystem":false,"isUpdateOleOnResize":true,"isViewer":true,"isVisual":false,"size":[600,700],"store":{"background":{"light":"#F5F5F5","dark":"#444444"},"icons":{"light":"resources/store/icons","dark":"resources/store/icons"},"screenshots":["resources/store/screenshots/screen_1.png"]},"url":"index.html"}]
     * version : 1.0
     */

    private String baseUrl;
    private String guid;
    private String help;
    private String minVersion;
    private String name;
    private NameLocaleBean nameLocale;
    private String version;
    private List<VariationsBean> variations;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public String getMinVersion() {
        return minVersion;
    }

    public void setMinVersion(String minVersion) {
        this.minVersion = minVersion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NameLocaleBean getNameLocale() {
        return nameLocale;
    }

    public void setNameLocale(NameLocaleBean nameLocale) {
        this.nameLocale = nameLocale;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<VariationsBean> getVariations() {
        return variations;
    }

    public void setVariations(List<VariationsBean> variations) {
        this.variations = variations;
    }

    public static class NameLocaleBean {
        /**
         * en : English plugin name
         * zh : Chinese plugin name
         */

        private String en;
        private String zh;

        public String getEn() {
            return en;
        }

        public void setEn(String en) {
            this.en = en;
        }

        public String getZh() {
            return zh;
        }

        public void setZh(String zh) {
            this.zh = zh;
        }
    }

    public static class VariationsBean {
        /**
         * buttons : [{"text":"Cancel","primary":false,"isviewer":false,"textLocale":{"en":"Cancel","zh":"取消"}}]
         * cryptoDisabledForExternalCloud : true
         * cryptoDisabledForInternalCloud : true
         * cryptoDisabledOnStart : true
         * cryptoMode : 1
         * description : plugin description
         * descriptionLocale : {"en":"English plugin description","zh":"Chinese plugin description"}
         * EditorsSupport : ["word","cell","slide"]
         * events : ["onClick"]
         * icons : ["icon.png","icon@2x.png"]
         * icons2 : [{},{"style":"dark"}]
         * initData :
         * initDataType : ole
         * initOnSelectionChanged : true
         * isCustomWindow : true
         * isDisplayedInViewer : true
         * isInsideMode : false
         * isModal : true
         * isSystem : false
         * isUpdateOleOnResize : true
         * isViewer : true
         * isVisual : false
         * size : [600,700]
         * store : {"background":{"light":"#F5F5F5","dark":"#444444"},"icons":{"light":"resources/store/icons","dark":"resources/store/icons"},"screenshots":["resources/store/screenshots/screen_1.png"]}
         * url : index.html
         */

        private String cryptoDisabledForExternalCloud;
        private String cryptoDisabledForInternalCloud;
        private String cryptoDisabledOnStart;
        private String cryptoMode;
        private String description;
        private DescriptionLocaleBean descriptionLocale;
        private String initData;
        private String initDataType;
        private boolean initOnSelectionChanged;
        private boolean isCustomWindow;
        private boolean isDisplayedInViewer;
        private boolean isInsideMode;
        private boolean isModal;
        private boolean isSystem;
        private boolean isUpdateOleOnResize;
        private boolean isViewer;
        private boolean isVisual;
        private StoreBean store;
        private String url;
        private List<ButtonsBean> buttons;
        private List<String> EditorsSupport;
        private List<String> events;
        private List<String> icons;
        private List<Icons2Bean> icons2;
        private List<Integer> size;

        public String getCryptoDisabledForExternalCloud() {
            return cryptoDisabledForExternalCloud;
        }

        public void setCryptoDisabledForExternalCloud(String cryptoDisabledForExternalCloud) {
            this.cryptoDisabledForExternalCloud = cryptoDisabledForExternalCloud;
        }

        public String getCryptoDisabledForInternalCloud() {
            return cryptoDisabledForInternalCloud;
        }

        public void setCryptoDisabledForInternalCloud(String cryptoDisabledForInternalCloud) {
            this.cryptoDisabledForInternalCloud = cryptoDisabledForInternalCloud;
        }

        public String getCryptoDisabledOnStart() {
            return cryptoDisabledOnStart;
        }

        public void setCryptoDisabledOnStart(String cryptoDisabledOnStart) {
            this.cryptoDisabledOnStart = cryptoDisabledOnStart;
        }

        public String getCryptoMode() {
            return cryptoMode;
        }

        public void setCryptoMode(String cryptoMode) {
            this.cryptoMode = cryptoMode;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public DescriptionLocaleBean getDescriptionLocale() {
            return descriptionLocale;
        }

        public void setDescriptionLocale(DescriptionLocaleBean descriptionLocale) {
            this.descriptionLocale = descriptionLocale;
        }

        public String getInitData() {
            return initData;
        }

        public void setInitData(String initData) {
            this.initData = initData;
        }

        public String getInitDataType() {
            return initDataType;
        }

        public void setInitDataType(String initDataType) {
            this.initDataType = initDataType;
        }

        public boolean isInitOnSelectionChanged() {
            return initOnSelectionChanged;
        }

        public void setInitOnSelectionChanged(boolean initOnSelectionChanged) {
            this.initOnSelectionChanged = initOnSelectionChanged;
        }

        public boolean isIsCustomWindow() {
            return isCustomWindow;
        }

        public void setIsCustomWindow(boolean isCustomWindow) {
            this.isCustomWindow = isCustomWindow;
        }

        public boolean isIsDisplayedInViewer() {
            return isDisplayedInViewer;
        }

        public void setIsDisplayedInViewer(boolean isDisplayedInViewer) {
            this.isDisplayedInViewer = isDisplayedInViewer;
        }

        public boolean isIsInsideMode() {
            return isInsideMode;
        }

        public void setIsInsideMode(boolean isInsideMode) {
            this.isInsideMode = isInsideMode;
        }

        public boolean isIsModal() {
            return isModal;
        }

        public void setIsModal(boolean isModal) {
            this.isModal = isModal;
        }

        public boolean isIsSystem() {
            return isSystem;
        }

        public void setIsSystem(boolean isSystem) {
            this.isSystem = isSystem;
        }

        public boolean isIsUpdateOleOnResize() {
            return isUpdateOleOnResize;
        }

        public void setIsUpdateOleOnResize(boolean isUpdateOleOnResize) {
            this.isUpdateOleOnResize = isUpdateOleOnResize;
        }

        public boolean isIsViewer() {
            return isViewer;
        }

        public void setIsViewer(boolean isViewer) {
            this.isViewer = isViewer;
        }

        public boolean isIsVisual() {
            return isVisual;
        }

        public void setIsVisual(boolean isVisual) {
            this.isVisual = isVisual;
        }

        public StoreBean getStore() {
            return store;
        }

        public void setStore(StoreBean store) {
            this.store = store;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<ButtonsBean> getButtons() {
            return buttons;
        }

        public void setButtons(List<ButtonsBean> buttons) {
            this.buttons = buttons;
        }

        public List<String> getEditorsSupport() {
            return EditorsSupport;
        }

        public void setEditorsSupport(List<String> EditorsSupport) {
            this.EditorsSupport = EditorsSupport;
        }

        public List<String> getEvents() {
            return events;
        }

        public void setEvents(List<String> events) {
            this.events = events;
        }

        public List<String> getIcons() {
            return icons;
        }

        public void setIcons(List<String> icons) {
            this.icons = icons;
        }

        public List<Icons2Bean> getIcons2() {
            return icons2;
        }

        public void setIcons2(List<Icons2Bean> icons2) {
            this.icons2 = icons2;
        }

        public List<Integer> getSize() {
            return size;
        }

        public void setSize(List<Integer> size) {
            this.size = size;
        }

        public static class DescriptionLocaleBean {
            /**
             * en : English plugin description
             * zh : Chinese plugin description
             */

            private String en;
            private String zh;

            public String getEn() {
                return en;
            }

            public void setEn(String en) {
                this.en = en;
            }

            public String getZh() {
                return zh;
            }

            public void setZh(String zh) {
                this.zh = zh;
            }
        }

        public static class StoreBean {
            /**
             * background : {"light":"#F5F5F5","dark":"#444444"}
             * icons : {"light":"resources/store/icons","dark":"resources/store/icons"}
             * screenshots : ["resources/store/screenshots/screen_1.png"]
             */

            private BackgroundBean background;
            private IconsBean icons;
            private List<String> screenshots;

            public BackgroundBean getBackground() {
                return background;
            }

            public void setBackground(BackgroundBean background) {
                this.background = background;
            }

            public IconsBean getIcons() {
                return icons;
            }

            public void setIcons(IconsBean icons) {
                this.icons = icons;
            }

            public List<String> getScreenshots() {
                return screenshots;
            }

            public void setScreenshots(List<String> screenshots) {
                this.screenshots = screenshots;
            }

            public static class BackgroundBean {
                /**
                 * light : #F5F5F5
                 * dark : #444444
                 */

                private String light;
                private String dark;

                public String getLight() {
                    return light;
                }

                public void setLight(String light) {
                    this.light = light;
                }

                public String getDark() {
                    return dark;
                }

                public void setDark(String dark) {
                    this.dark = dark;
                }
            }

            public static class IconsBean {
                /**
                 * light : resources/store/icons
                 * dark : resources/store/icons
                 */

                private String light;
                private String dark;

                public String getLight() {
                    return light;
                }

                public void setLight(String light) {
                    this.light = light;
                }

                public String getDark() {
                    return dark;
                }

                public void setDark(String dark) {
                    this.dark = dark;
                }
            }
        }

        public static class ButtonsBean {
            /**
             * text : Cancel
             * primary : false
             * isviewer : false
             * textLocale : {"en":"Cancel","zh":"取消"}
             */

            private String text;
            private boolean primary;
            private boolean isviewer;
            private TextLocaleBean textLocale;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public boolean isPrimary() {
                return primary;
            }

            public void setPrimary(boolean primary) {
                this.primary = primary;
            }

            public boolean isIsviewer() {
                return isviewer;
            }

            public void setIsviewer(boolean isviewer) {
                this.isviewer = isviewer;
            }

            public TextLocaleBean getTextLocale() {
                return textLocale;
            }

            public void setTextLocale(TextLocaleBean textLocale) {
                this.textLocale = textLocale;
            }

            public static class TextLocaleBean {
                /**
                 * en : Cancel
                 * zh : 取消
                 */

                private String en;
                private String zh;

                public String getEn() {
                    return en;
                }

                public void setEn(String en) {
                    this.en = en;
                }

                public String getZh() {
                    return zh;
                }

                public void setZh(String zh) {
                    this.zh = zh;
                }
            }
        }

        public static class Icons2Bean {
            /**
             * style : dark
             */

            private String style;

            public String getStyle() {
                return style;
            }

            public void setStyle(String style) {
                this.style = style;
            }
        }
    }
}
