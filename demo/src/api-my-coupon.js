import {API_URL, getAuthHeaders} from "./common.js";

export async function fetchMyCoupon() {
    const response = await fetch(`${API_URL}/my-coupons/detail`, {
        // method: "GET",
        headers: {
            "Content-Type": "application/json",
            ...getAuthHeaders()
        },
        credentials: "include",  // 인증 정보 포함
    });

    if (!response.ok) {
        throw new Error();
    }
    return response.json();
}
