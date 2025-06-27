package com.project.imdang.common.application.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RequestPath {
    // ******************* domain *******************

    private static final String INSIGHT = "/insights";

    // TODO - CHECK : /insights/me
    private static final String MY_INSIGHT = "my-insights";

    private static final String MEMBER = "/members";
    private static final String TERMS = "/terms";

    // ******************* login *******************
    public static final String LOGIN = "/login";
    public static final String REISSUE = "/reissue";

    // ******************* insight *******************

    public static final String DETAIL_INSIGHT = INSIGHT + "/detail";

    public static final String CREATE_INSIGHT = INSIGHT + "/create";
    public static final String UPDATE_INSIGHT = INSIGHT + "/update";
    public static final String DELETE_INSIGHT = INSIGHT + "/delete";
    public static final String RECOMMEND_INSIGHT = INSIGHT + "/recommend";
    public static final String ACCUSE_INSIGHT = INSIGHT + "/accuse";

    // ******************* my-insight *******************
    public static final String LIST_DISTRICT_OF_MY_INSIGHT = "";

    // ******************* member *******************
    public static final String DETAIL_MY_PAGE = MEMBER + "/me";
    public static final String DETAIL_MEMBER = MEMBER + "/detail";
    public static final String LIST_MEMBER = MEMBER;

    public static final String JOIN_MEMBER = MEMBER + "/join";
    public static final String WITHDRAW_MEMBER = MEMBER + "/withdraw";

    // TODO - CHECK : 위치
    public static final String LOGOUT = MEMBER + "/logout";
    // ******************* terms *******************
}
