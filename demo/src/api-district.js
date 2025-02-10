import {API_URL, getAuthHeaders} from "./common.js";

export async function fetchSiGunGus() {
    const response = await fetch(`${API_URL}/districts/si-gun-gu`, {
        // method: "GET",
        headers: {
            "Content-Type": "application/json",
            ...getAuthHeaders()
        },
        credentials: "include",  // 인증 정보 포함
    });
    console.log(response);
    if (!response.ok) {
        throw new Error("인사이트 목록을 불러오지 못했습니다.");
    }
    return response.json();
}

export async function fetchEupMyeonDongs(siGunGu) {
    console.log(siGunGu);
    const response = await fetch(`${API_URL}/districts/eup-myeon-dong?siGunGu=${siGunGu}`, {
        // method: "GET",
        headers: {
            "Content-Type": "application/json",
            ...getAuthHeaders()
        },
        credentials: "include",  // 인증 정보 포함
    });

    if (!response.ok) {
        throw new Error("인사이트 목록을 불러오지 못했습니다.");
    }
    return response.json();
}
