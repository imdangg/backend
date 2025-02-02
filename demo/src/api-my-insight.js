import {API_URL, getAuthHeaders} from "./common.js";

export async function fetchDistricts() {
    const response = await fetch(`${API_URL}/my-insights/districts`, {
        // method: "GET",
        headers: {
            "Content-Type": "application/json",
            ...getAuthHeaders()
        },
        credentials: "include",  // 인증 정보 포함
    });

    if (!response.ok) {
        throw new Error("목록을 불러오지 못했습니다.");
    }
    return response.json();
}

export async function fetchInsightsCreatedByMe() {
    const response = await fetch(`${API_URL}/my-insights/created-by-me`, {
        // method: "GET",
        headers: {
            "Content-Type": "application/json",
            ...getAuthHeaders()
        },
        credentials: "include",  // 인증 정보 포함
    });

    if (!response.ok) {
        throw new Error("목록을 불러오지 못했습니다.");
    }
    return response.json();
}
