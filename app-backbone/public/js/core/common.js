/**
 * global define
 *
 * @author Touseef
 */
"use strict";

define([], function () {
    var baseApi = "http://localhost/api/";
    var v1 = baseApi + "v1/";

    return {
        // Base API URL (used by models & collections)
        BASE_API: baseApi,
        V1: v1,
        V1_USERS: v1 + "users/",
        // What is the enter key constant?
        ENTER_KEY: 13,
        ESCAPE_KEY: 27
    };
});
