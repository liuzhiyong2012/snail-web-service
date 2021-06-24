package com.snail.web.constants;

public class DtoConstants {
    public final static String STATUS_NORMAL = "1";
    public final static String STATUS_PAUSE = "2";

    public final static String IS_DELETE_YES = "1";
    public final static String IS_DELETE_NO = "0";

    public final static String SOURCE_ADMIN = "1";
    public final static String SOURCE_USER = "2";
    public final static String SOURCE_SPIDER = "3";



    public static enum STATUS
    {
        /**
         * 正常
         */
        NORMAL("1"),
        /**
         * 暂停
         */
        PAUSE("2");

        private String value;

        private STATUS(String value)
        {
            this.value = value;
        }

        public String getValue()
        {
            return value;
        }
    }

    public enum IS_DELETE
    {
        /**
         * 正常
         */
        YES("1"),
        /**
         * 暂停
         */
        NO("2");

        public String value;

        private IS_DELETE(String value)
        {
            this.value = value;
        }

        public String getValue()
        {
            return value;
        }
    }
}
