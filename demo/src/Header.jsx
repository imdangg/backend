import React from "react";
import {useMember} from "./context/MemberContext.jsx";

const Header = () => {
    const { member } = useMember();

    return (
        <header className="flex justify-between items-center p-4 bg-orange-400 text-black">
            <h1 className="text-xl font-bold">아파트임당</h1>
            <div className="flex items-center gap-3">
                {member ? (
                    <>
                        {/*<span className="text-lg">memberId: {member.memberId}</span>*/}
                        <span className="text-lg">nickname: {member.nickname}</span>
                        {/*<span className="text-lg">birthDate: {member.birthDate}</span>*/}
                        {/*<span className="text-lg">gender: {member.gender}</span>*/}
                        {/*<span className="text-lg">deviceToken: {member.deviceToken}</span>*/}
                        <span className="text-lg">exchangeCount: {member.exchangeCount}</span>
                        <span className="text-lg">insightCount: {member.insightCount}</span>
                        <span className="text-lg">requestCount: {member.requestCount}</span>
                    </>
                ) : (
                    <span className="text-gray-400">?</span>
                )}
            </div>
        </header>
    );
};

export default Header;
