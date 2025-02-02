export const API_URL = "http://localhost:8080";

export const getAuthHeaders = () => {
    // const token = localStorage.getItem("token");
    return { Authorization: `Bearer ` };
}
