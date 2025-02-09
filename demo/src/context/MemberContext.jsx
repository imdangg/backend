import React, {createContext, useContext, useEffect, useState} from "react";
import {fetchMember} from "../api-member.js";

// Context 생성 (전역 상태 관리)
const MemberContext = createContext(null);

// Provider 생성
export const MemberProvider = ({ children }) => {
    const [member, setMember] = useState(null);

    useEffect(() => {
        fetchMember()
            .then(data => {
                console.log(data)
                return data;
            }).then(setMember);
    }, []);

    return (
        <MemberContext.Provider value={{ member, setMember }}>
            {children}
        </MemberContext.Provider>
    );
};

export const useMember = () => useContext(MemberContext);
