package com.project.imdang.common.application.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RequestPath {

    // ******************* swagger *******************
    public static final String SWAGGER_RESOURCE = "/swagger-resources/**";
    public static final String SWAGGER_UI = "/swagger-ui/**";
    public static final String SWAGGER_DOC = "/v3/api-docs/**";

    // ******************* login *******************
    public static final String LOGIN = "/login";
    public static final String REISSUE = "/reissue";
    public static final String LOGOUT = "/logout";

    // ******************* domain *******************
    private static final String DISTRICT = "/districts";
    private static final String INSIGHT = "/insights";
        private static final String BOOKMARKED_INSIGHT = INSIGHT + "/bookmarked";
    private static final String MEMBER = "/members";
    private static final String TERMS = "/terms";
    private static final String NOTIFICATION = "/notifications";

    // ******************* district *******************
    public static final String LIST_SI_GUN_GU = DISTRICT + "/si-gun-gu";
    public static final String LIST_EUP_MYEON_DONG = DISTRICT + "/eup-myeon-dong";

    // ******************* insight *******************
    public static final String LIST_INSIGHT = INSIGHT;
    // 날짜별 인사이트 목록 조회
    public static final String LIST_INSIGHT_BY_DATE = INSIGHT + "/by-date";
    // 지역별 인사이트 목록 조회
    public static final String LIST_INSIGHT_BY_DISTRICT = INSIGHT + "/by-district";
    // 아파트 단지별 인사이트 목록 조회
    public static final String LIST_INSIGHT_BY_APARTMENT_COMPLEX = INSIGHT + "/by-apartment-complex";
    public static final String LIST_INSIGHT_CREATED_BY_ME = INSIGHT + "/created-by-me";
    public static final String LIST_APARTMENT_COMPLEX_OF_INSIGHT_CREATED_BY_ME = LIST_INSIGHT_CREATED_BY_ME + "/apartment-complexes";

    public static final String DETAIL_INSIGHT = INSIGHT + "/detail";

    public static final String CREATE_INSIGHT = INSIGHT + "/create";
    public static final String UPDATE_INSIGHT = INSIGHT + "/update";
    public static final String DELETE_INSIGHT = INSIGHT + "/delete";
    public static final String RECOMMEND_INSIGHT = INSIGHT + "/recommend";
    public static final String ACCUSE_INSIGHT = INSIGHT + "/accuse";

    // ******************* bookmarked-insight *******************
    // 보관중인 인사이트의 자치구 목록 조회
    public static final String LIST_DISTRICT_OF_BOOKMARKED_INSIGHT = BOOKMARKED_INSIGHT + "/districts";

    // '단지별 보기' 클릭 시, 지역별 단지-인사이트 개수 목록 조회
    public static final String LIST_APARTMENT_COMPLEX_OF_BOOKMARKED_INSIGHT = BOOKMARKED_INSIGHT + "/apartment-complexes";
    public static final String LIST_BOOKMARKED_INSIGHT = BOOKMARKED_INSIGHT;
    // ******************* member *******************
    public static final String DETAIL_MY_PAGE = MEMBER + "/me";
    public static final String DETAIL_MEMBER = MEMBER + "/detail";
    public static final String LIST_MEMBER = MEMBER;

    public static final String JOIN_MEMBER = MEMBER + "/join";
    public static final String CONDITION_MEMBER = MEMBER + "/condition";
    public static final String WITHDRAW_MEMBER = MEMBER + "/withdraw";

    // ******************* terms *******************
    public static final String LIST_TERMS = TERMS;
    public static final String AGREE_TERMS = TERMS + "/agree";

    // ******************* notification *******************
    public static final String LIST_NOTIFICATION = NOTIFICATION;
    public static final String CHECK_NOTIFICATION = NOTIFICATION + "/check";
}
