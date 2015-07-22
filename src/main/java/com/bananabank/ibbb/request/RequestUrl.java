/**
 * Copyright (c) 2015, Patryk Roszczyniała
 */
package com.bananabank.ibbb.request;

/**
 * Stores all urls that can be requested.
 *
 * @author Patryk Roszczyniala (p.roszczyniala@gmail.com)
 */
public class RequestUrl {

    /** Generates one time token/password used during authorization. */
    public static final String GENERATE_TOKEN_URL = "/tokens/user/{userId}";

    /** Increases balance of the account. */
    public static final String INCREASE_BALANCE_URL = "/balance/user/{userId}/increase";

    /** Decreases balance of the account. */
    public static final String DECREASE_BALANCE_URL = "/balance/user/{userId}/decrease";

    public static final String CHECK_BALANCE_URL = "/balance/user/{userId}";

    /** Returns list of all transactions done by the user. */
    public static final String CHECK_HISTORY_URL = "/history/user/{userId}";

    /**
     * Creates the request url.
     *
     * @param urlPattern the url pattern
     * @param userId the user id to be used in url
     * @return the created url
     */
    public static String create(final String urlPattern, final String userId) {
        return urlPattern.replace("{userId}", userId);
    }

}
