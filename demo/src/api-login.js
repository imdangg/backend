import {API_URL, getAuthHeaders} from "./common.js";

export async function login(id) {
    const response = await fetch(`${API_URL}/auth/mock`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            ...getAuthHeaders()
        },
        body: JSON.stringify({ id: id }),
    });

    if (!response.ok) {
        throw new Error();
    }
    return response.json();
}
