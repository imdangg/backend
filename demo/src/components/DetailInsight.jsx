import React, {useEffect, useState} from 'react';
import {useParams} from 'react-router-dom';
import {accuseInsight, fetchInsight, recommendInsight} from "../api-insight.js";

export default function DetailInsight() {
    const { id } = useParams();
    const [insight, setInsight] = useState(null);
    const [recommended, setRecommended] = useState(null);
    const [accused, setAccused] = useState(null);
    const [myInsights, setMyInsights] = useState([]);
    const [myInsightId, setMyInsightId] = useState(null);

    useEffect(() => {
        fetchInsight(id)
            .then(insight => {
                console.log(insight);
                return insight;
            })
            .then(insight => {
                setInsight(insight);
                setRecommended(insight.recommended);
                setAccused(insight.accused);
            });
    }, [recommended, accused]);

    const handleChooseMyInsight = (myInsightId) => {
        setMyInsightId(myInsightId);
    }

    const handleConfirm = () => {};
    const handleRecommendInsight = (insight) => {
        if (recommended) return;
        recommendInsight(insight.insightId).then(result => {
            fetchInsight(id)
                .then(insight => {
                    // console.log(insight);
                    return insight;
                })
                .then(insight => {
                    setInsight(insight);
                    setRecommended(insight.recommended);
                    setAccused(insight.accused);
                });
        });
    }

    const handleAccuseInsight = (insight) => {
        if (accused) return;
        accuseInsight(insight.insightId).then(result => {
            fetchInsight(id)
                .then(insight => {
                    // console.log(insight);
                    return insight;
                })
                .then(insight => {
                    setInsight(insight);
                    setRecommended(insight.recommended);
                    setAccused(insight.accused);
                });
        });
    }

    let content;
    if (insight) {
        if (insight.createdByMe === true) {
            content = <button className="mr-2 px-6 py-2 bg-orange-400 text-white font-semibold rounded-lg shadow-md hover:bg-orange-600 focus:outline-none focus:ring-2 focus:ring-orange-400 focus:ring-opacity-75 transition duration-200 ease-in-out"
                              disabled>수정하기</button>
        }
    }


    return (
        <>
            <div className="min-h-screen bg-gray-100 py-10 px-5">
                <h2 className="text-3xl font-bold text-black-800 mb-4">인사이트 상세</h2>
                <div className="mt-8 p-6 bg-white shadow-md rounded-lg">

                    <div className="flex justify-end mb-2">
                        <button className="mr-2 px-6 py-2 bg-blue-400 text-white font-semibold rounded-lg shadow-md hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-400 focus:ring-opacity-75 transition duration-200 ease-in-out"
                                onClick={() => handleRecommendInsight(insight)}>추천({recommended ? 'O' : 'X'})</button>
                        <button className="mr-2 px-6 py-2 bg-red-400 text-white font-semibold rounded-lg shadow-md hover:bg-red-600 focus:outline-none focus:ring-2 focus:ring-red-400 focus:ring-opacity-75 transition duration-200 ease-in-out"
                                onClick={() => handleAccuseInsight(insight)}>신고({accused ? 'O' : 'X'})</button>
                    </div>
                    {insight && (
                        <>
                            <h2 className="text-2xl font-semibold text-gray-800 mb-4">{insight.title}</h2>
                            {content}
                            <div className="mt-4 text-sm text-gray-500">
                                <p className="text-blue-700"><strong>access:</strong> {insight.access}</p>
                                <p><strong>accused:</strong> {insight.accused ? 'O' : 'X'}</p>
                                <p><strong>accusedCount:</strong> {insight.accusedCount}</p>
                                <p className="text-blue-700"><strong>address:</strong>
                                    {insight.address.siDo} {insight.address.siGunGu} {insight.address.eupMyeonDong}
                                </p>
                                <p className="text-blue-700"><strong>apartmentComplex:</strong> {insight.apartmentComplex.name}</p>
                                <p><strong>createdAt:</strong> {insight.createdAt}</p>

                                {insight.infra && (
                                    <p><strong>infra:</strong><br />
                                        amenities: {insight.infra.amenities}<br />
                                        facilities: {insight.infra.facilities}<br />
                                        schoolDistricts: {insight.infra.schoolDistricts}<br />
                                        surroundings: {insight.infra.surroundings}<br />
                                        transportations: {insight.infra.transportations}<br />
                                        text: {insight.infra.text}
                                    </p>
                                )}
                                {insight.complexEnvironment && (
                                    <p><strong>complexEnvironment:</strong><br />
                                        buildingCondition: {insight.complexEnvironment.buildingCondition}<br />
                                        childrenFacility: {insight.complexEnvironment.childrenFacility}<br />
                                        security: {insight.complexEnvironment.security}<br />
                                        text: {insight.complexEnvironment.text}
                                    </p>
                                )}
                                <p className="text-blue-700"><strong>insightId:</strong> {insight.insightId}</p>
                                <p className="text-blue-700"><strong>mainImage:</strong> {insight.mainImage}</p>
                                <p><strong>memberId:</strong> {insight.memberId}</p>
                                <p className="text-blue-700"><strong>memberNickname:</strong> {insight.memberNickname}</p>
                                <p className="text-blue-700"><strong>recommended:</strong> {insight.recommended ? 'O' : 'X'}</p>
                                <p className="text-blue-700"><strong>recommendedCount:</strong> {insight.recommendedCount}</p>
                                <p><strong>score:</strong> {insight.score}</p>
                                <p className="text-blue-700"><strong>summary:</strong> {insight.summary}</p>
                                <p className="text-blue-700"><strong>title:</strong> {insight.title}</p>
                                <p><strong>viewCount:</strong> {insight.viewCount}</p>
                                <p className="text-blue-700"><strong>visitAt:</strong> {insight.visitAt}</p>
                                <p className="text-blue-700"><strong>visitMethods:</strong> {insight.visitMethods}</p>
                                <p className="text-blue-700"><strong>visitTimes:</strong> {insight.visitTimes}</p>
                            </div>
                        </>
                    )}
                </div>
            </div>
        </>
    );
}
