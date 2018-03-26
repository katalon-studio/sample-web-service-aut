package com.katalon.wsaut.config.constant;

public class Resources {

    private static final String API = "/api";

    private static final String ID = "/{id}";

    public static final class User {
        public static final String COLLECTIONS = API + "/users";

        public static final String COLLECTIONS_ACCEPT_JSON = COLLECTIONS + "/accept-json";

        public static final String COLLECTIONS_ACCEPT_XML = COLLECTIONS + "/accept-xml";

        public static final String COLLECTIONS_CONSUME_JSON = COLLECTIONS + "/json";

        public static final String COLLECTIONS_CONSUME_XML = COLLECTIONS + "/xml";

        public static final String COLLECTIONS_CONSUME_URLENCODED = COLLECTIONS + "/urlencoded";

        public static final String COLLECTIONS_CONSUME_FORM_DATA = COLLECTIONS + "/form-data";

        public static final String ITEM = COLLECTIONS + ID;
    }

    public static final class File {
        public static final String UPLOAD = API +  "/upload";
    }
}
