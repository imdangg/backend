import {useNavigate} from "react-router-dom";
import {login} from "../api-login.js";
import React from "react";

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
            className="ml-2 my-2 px-6 py-2 bg-orange-400 text-white font-semibold rounded-lg shadow-md hover:bg-orange-600 focus:outline-none focus:ring-2 focus:ring-orange-400 focus:ring-opacity-75 transition duration-200 ease-in-out"
        >
            로그인(A)
        </button>
        <button
            onClick={() => handleLogin('0123456789')}
            className="ml-2 my-2 px-6 py-2 bg-orange-400 text-white font-semibold rounded-lg shadow-md hover:bg-orange-600 focus:outline-none focus:ring-2 focus:ring-orange-400 focus:ring-opacity-75 transition duration-200 ease-in-out"
        >
            로그인(B)
        </button>
        </>
    );
}
