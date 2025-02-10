import {useNavigate} from "react-router-dom";
import {login} from "../api-login.js";

export default function Login() {
    const navigate = useNavigate();
    const handleLogin = (id) => {
        login(id).then(result => {
            console.log(result);
            localStorage.setItem('token', result.accessToken);
            navigate("/");
        });
    }

    return (
        <>
        <button
            onClick={() => handleLogin('1234567890')}
            className="bg-blue-500 text-white px-4 py-2 rounded-lg hover:bg-blue-600"
        >
            로그인(A)
        </button>
        <button
            onClick={() => handleLogin('0123456789')}
            className="bg-blue-500 text-white px-4 py-2 rounded-lg hover:bg-blue-600"
        >
            로그인(B)
        </button>
        </>
    );
}
