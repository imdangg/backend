import React, {useEffect, useState} from "react";
import {fetchDistricts} from "../api-my-insight.js";

export default function MyInsight() {
    const [districts, setDistricts] = useState([]);
    const [insights, setInsights] = useState([]);

    useEffect(() => {
        fetchDistricts()
            .then(data => {
                console.log(data);
                return data;
            })
            .then(setDistricts);
    }, []);

    return (
        <div className="min-h-screen bg-gray-100 py-10 px-5">
            <h2 className="text-3xl font-bold text-black-800 mb-4">보관함</h2>
            <div className="max-w-6xl mx-auto space-y-8">

                <div className="flex space-x-8 mb-8">
                    <div className="bg-white shadow-lg rounded-lg p-6">
                        <table className="min-w-full table-auto border border-black-300">
                            <thead>
                            <tr className="bg-orange-400">
                                <th className="px-4 py-2 text-left text-black-700 font-medium border border-black-300">자치구</th>
                                <th className="px-4 py-2 text-left text-black-700 font-medium border border-black-300">단지</th>
                                <th className="px-4 py-2 text-left text-black-700 font-medium border border-black-300">인사이트</th>
                            </tr>
                            </thead>
                            <tbody>
                            {districts.map((district) => (
                                <tr className="bg-gray-50" key={`${district.siDo}-${district.siGunGu}-${district.eupMyeonDong}`}>
                                    <td className="px-4 py-2 text-gray-700 border border-black-300">{district.siDo} {district.siGunGu} {district.eupMyeonDong}</td>
                                    <td className="px-4 py-2 text-gray-700 border border-black-300">{district.apartmentComplexCount}</td>
                                    <td className="px-4 py-2 text-gray-700 border border-black-300">{district.insightCount}</td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    );
}
