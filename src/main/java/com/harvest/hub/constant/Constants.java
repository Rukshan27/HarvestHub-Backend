package com.harvest.hub.constant;

public class Constants {
    public static class Auth {

        public static String AUTHORIZATION_HEADER = "Authorization";
        public static String AUTHORIZATION_HEADER_PREFIX = "Bearer ";

        /**
         * The period of time for which an access token is issued.
         * Currently: 1 hour.
         */
        public static int ACCESS_TOKEN_PERIOD_MS = 1000 * 60 * 60 * 48;

        /**
         * The period of time for which a refresh token is issued.
         * Currently: 1 day.
         */
        public static int REFRESH_TOKEN_PERIOD_MS = 1000 * 60 * 60 * 24;


        /** The secret with which issued tokens are signed. */
        public static String TOKEN_SECRET = "7693942b706f444f51420169fc9718f1c21113b7085d535e3deca066508e8745499577c4b7b1e95692e23b8092fbf69e716fbe354cb012fe0924c47767e1fc8b";
    }

    public static class Jwt {

        public static class TokenType {

            /** Key for the `typ` claim of the JWT body */
            public static String CLAIM = "typ";

            public static String ACCESS_TOKEN = "access";
            public static String REFRESH_TOKEN = "refresh";
        }
    }

}
