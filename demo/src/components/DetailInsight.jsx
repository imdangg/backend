import React, {useEffect, useState} from 'react';
import {useParams} from 'react-router-dom';
import {accuseInsight, fetchInsight, recommendInsight} from "../api-insight.js";
import {fetchInsightsCreatedByMe} from "../api-my-insight.js";
import {acceptExchangeRequest, rejectExchangeRequest, requestExchange} from "../api-exchange.js";
import {fetchMyCoupon} from "../api-my-coupon.js";

export default function DetailInsight() {
    const { id } = useParams();
    const [insight, setInsight] = useState(null);
    const [recommended, setRecommended] = useState(null);
    const [accused, setAccused] = useState(null);
    const [exchangeRequestStatus, setExchangeRequestStatus] = useState(null);
    const [exchangeRequestCreatedByMe, setExchangeRequestCreatedByMe] = useState(null);

    const [exchangeRequested, setExchangeRequested] = useState(false);
    const [myInsights, setMyInsights] = useState([]);
    const [myInsightId, setMyInsightId] = useState(null);

    const [myCoupon, setMyCoupon] = useState(null);
    const [myCouponId, setMyCouponId] = useState(null);

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
                setExchangeRequestStatus(insight.exchangeRequestStatus);
                setExchangeRequestCreatedByMe(insight.exchangeRequestCreatedByMe);
            });
    }, [recommended, accused, exchangeRequestStatus]);

    const handleExchangeRequest = () => {
        if (!exchangeRequested) {
            setExchangeRequested(true);
            fetchMyCoupon()
                .then(data => {
                    console.log(data);
                    return data;
                })
                .then(setMyCoupon);
            fetchInsightsCreatedByMe()
                .then(data => {
                    // console.log(data);
                    return data.content;
                })
                .then(setMyInsights)
        } else {
            setExchangeRequested(false);
            setMyInsightId(null);
        }
    }

    const handleChooseMyInsight = (myInsightId) => {
        setMyInsightId(myInsightId);
        setMyCouponId(null);
    }

    const handleChooseMyCoupon = (myCouponId) => {
        setMyCouponId(myCouponId);
        setMyInsightId(null);
    }

    const handleConfirm = () => {
        requestExchange(id, myInsightId, myCouponId).then(result => {
            handleExchangeRequest();
            // console.log(result);
            fetchInsight(id)
                .then(insight => {
                    // console.log(insight);
                    return insight;
                })
                .then(insight => {
                    setInsight(insight);
                    setRecommended(insight.recommended);
                    setAccused(insight.accused);
                    setExchangeRequestStatus(insight.exchangeRequestStatus);
                    setExchangeRequestCreatedByMe(insight.exchangeRequestCreatedByMe);
                });
        });
    }

    const handleAcceptExchangeRequest = (insight) => {
        acceptExchangeRequest(insight.exchangeRequestId).then(result => {
            fetchInsight(insight.insightId)
                .then(insight => {
                    // console.log(insight);
                    return insight;
                })
                .then(insight => {
                    setInsight(insight);
                    setRecommended(insight.recommended);
                    setAccused(insight.accused);
                    setExchangeRequestStatus(insight.exchangeRequestStatus);
                    setExchangeRequestCreatedByMe(insight.exchangeRequestCreatedByMe);
                });
        });
    }

    const handleRejectExchangeRequest = (insight) => {
        rejectExchangeRequest(insight.exchangeRequestId).then(result => {
            fetchInsight(insight.insightId)
                .then(insight => {
                    // console.log(insight);
                    return insight;
                })
                .then(insight => {
                    setInsight(insight);
                    setRecommended(insight.recommended);
                    setAccused(insight.accused);
                    setExchangeRequestStatus(insight.exchangeRequestStatus);
                    setExchangeRequestCreatedByMe(insight.exchangeRequestCreatedByMe);
                });
        });
    };

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
                    setExchangeRequestStatus(insight.exchangeRequestStatus);
                    setExchangeRequestCreatedByMe(insight.exchangeRequestCreatedByMe);
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
                    setExchangeRequestStatus(insight.exchangeRequestStatus);
                    setExchangeRequestCreatedByMe(insight.exchangeRequestCreatedByMe);
                });
        });
    }

    let content;
    if (insight) {
        if (insight.createdByMe === true) {
            content = <button className="mr-2 px-6 py-2 bg-orange-400 text-white font-semibold rounded-lg shadow-md hover:bg-orange-600 focus:outline-none focus:ring-2 focus:ring-orange-400 focus:ring-opacity-75 transition duration-200 ease-in-out"
                              disabled>수정하기</button>
        } else {
            if (exchangeRequestStatus === null || exchangeRequestStatus === 'REJECTED') {
                content = <button className="mr-2 px-6 py-2 bg-orange-400 text-white font-semibold rounded-lg shadow-md hover:bg-orange-600 focus:outline-none focus:ring-2 focus:ring-orange-400 focus:ring-opacity-75 transition duration-200 ease-in-out"
                                  onClick={() => handleExchangeRequest()}>교환 요청</button>
            } else if (exchangeRequestStatus === 'ACCEPTED') {
                content = <button className="mr-2 px-6 py-2 bg-gray-400 text-white font-semibold rounded-lg shadow-md hover:bg-gray-600 transition duration-200 ease-in-out" disabled>교환 완료</button>
            } else {
                // PENDING
                if (exchangeRequestCreatedByMe) {
                    content = <button className="mr-2 px-6 py-2 bg-gray-400 text-white font-semibold rounded-lg shadow-md hover:bg-gray-600 transition duration-200 ease-in-out" disabled>대기중</button>
                } else {
                    content = <>
                        <button className="mr-2 px-6 py-2 bg-orange-400 text-white font-semibold rounded-lg shadow-md hover:bg-orange-600 focus:outline-none focus:ring-2 focus:ring-orange-400 focus:ring-opacity-75 transition duration-200 ease-in-out"
                                onClick={() => handleRejectExchangeRequest(insight)}>거절</button>
                        <button className="mr-2 px-6 py-2 bg-orange-400 text-white font-semibold rounded-lg shadow-md hover:bg-orange-600 focus:outline-none focus:ring-2 focus:ring-orange-400 focus:ring-opacity-75 transition duration-200 ease-in-out"
                                onClick={() => handleAcceptExchangeRequest(insight)}>수락</button>
                    </>
                }
            }

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
                            {exchangeRequested && (
                                <>
                                    <table className="mt-2 mb-2 min-w-full table-auto border-collapse border border-gray-300">
                                        <thead>
                                        <tr className="bg-gray-200">
                                            <th className="px-4 py-2 text-left text-black font-bold border border-gray-300">MemberCoupon ID</th>
                                            <th className="px-4 py-2 text-left text-black font-bold border border-gray-300">Coupon Count</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        {myCoupon && (
                                            <tr className="bg-gray-200" key={myCoupon.memberCouponId}
                                                onClick={() => handleChooseMyCoupon(myCoupon.memberCouponId)}
                                                style={{
                                                    cursor: 'pointer',
                                                    backgroundColor: myCouponId === myCoupon.memberCouponId ? '#f0f0f0' : 'white',
                                                }}>
                                                <td className="px-4 py-2 text-gray-700 border border-gray-300">{myCoupon.memberCouponId}</td>
                                                <td className="px-4 py-2 text-gray-700 border border-gray-300">{myCoupon.couponCount}</td>
                                            </tr>
                                        )}
                                        </tbody>
                                    </table>
                                    <table className="mt-2 mb-2 min-w-full table-auto border-collapse border border-gray-300">
                                        <thead>
                                        <tr className="bg-gray-200">
                                            <th className="px-4 py-2 text-left text-black font-bold border border-gray-300">Title</th>
                                            <th className="px-4 py-2 text-left text-black font-bold border border-gray-300">Recommended Count</th>
                                            <th className="px-4 py-2 text-left text-black font-bold border border-gray-300">Address</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        {myInsights.map((myInsight) => (
                                            <tr className="bg-gray-200" key={myInsight.insightId}
                                                 onClick={() => handleChooseMyInsight(myInsight.insightId)}
                                                 style={{
                                                     cursor: 'pointer',
                                                     backgroundColor: myInsightId === myInsight.insightId ? '#f0f0f0' : 'white',
                                                 }}>
                                                <td className="px-4 py-2 text-gray-700 border border-gray-300">{myInsight.title}</td>
                                                <td className="px-4 py-2 text-gray-700 border border-gray-300">{myInsight.recommendedCount}</td>
                                                <td className="px-4 py-2 text-gray-700 border border-gray-300">{myInsight.address.siDo} {myInsight.address.siGunGu} {myInsight.address.eupMyeonDong}</td>
                                            </tr>
                                        ))}
                                        </tbody>
                                    </table>
                                    <button
                                        className="px-6 py-2 bg-gray-400 text-white font-semibold rounded-lg shadow-md hover:bg-gray-600 focus:outline-none focus:ring-2 focus:ring-gray-400 focus:ring-opacity-75 transition duration-200 ease-in-out"
                                        onClick={handleConfirm}>확인</button>
                                </>
                            )}
                            <div className="mt-4 text-sm text-gray-500">
                                <p className="text-blue-700"><strong>access:</strong> {insight.access}</p>
                                <p><strong>accused:</strong> {insight.accused ? 'O' : 'X'}</p>
                                <p><strong>accusedCount:</strong> {insight.accusedCount}</p>
                                <p className="text-blue-700"><strong>address:</strong> {insight.address.siDo} {insight.address.siGunGu} {insight.address.eupMyeonDong}</p>
                                <p className="text-blue-700"><strong>apartmentComplex:</strong> {insight.apartmentComplex.name}</p>

                                <p><strong>createdAt:</strong> {insight.createdAt}</p>
                                <p className="text-blue-700"><strong>exchangeRequestCreatedByMe:</strong> {insight.exchangeRequestCreatedByMe ? 'O' : 'X'}</p>
                                <p className="text-blue-700"><strong>exchangeRequestId:</strong> {insight.exchangeRequestId}</p>
                                <p className="text-blue-700"><strong>exchangeRequestStatus:</strong> {insight.exchangeRequestStatus}</p>

                                {insight.infra && (
                                    <p><strong>infra:</strong><br />
                                        amenities: {insight.infra.amenities}<br />
                                        facilities: {insight.infra.facilities}<br />
                                        landmarks: {insight.infra.landmarks}<br />
                                        schoolDistricts: {insight.infra.schoolDistricts}<br />
                                        surroundings: {insight.infra.surroundings}<br />
                                        transportations: {insight.infra.transportations}<br />
                                        unpleasantFacilities: {insight.infra.unpleasantFacilities}<br />
                                        text: {insight.infra.text}
                                    </p>
                                )}
                                {insight.complexEnvironment && (
                                    <p><strong>complexEnvironment:</strong><br />
                                        buildingCondition: {insight.complexEnvironment.buildingCondition}<br />
                                        childrenFacility: {insight.complexEnvironment.childrenFacility}<br />
                                        security: {insight.complexEnvironment.security}<br />
                                        seniorFacility: {insight.complexEnvironment.seniorFacility}<br />
                                        text: {insight.complexEnvironment.text}
                                    </p>
                                )}
                                {insight.complexFacility && (
                                    <p><strong>complexFacility:</strong><br />
                                        familyFacilities: {insight.complexFacility.familyFacilities}<br />
                                        leisureFacilities: {insight.complexFacility.leisureFacilities}<br />
                                        multipurposeFacilities: {insight.complexFacility.multipurposeFacilities}<br />
                                        surroundings: {insight.complexFacility.surroundings}<br />
                                        text: {insight.complexFacility.text}
                                    </p>
                                )}
                                {insight.favorableNews && (
                                    <p><strong>favorableNews:</strong><br />
                                        cultures: {insight.favorableNews.cultures}<br />
                                        developments: {insight.favorableNews.developments}<br />
                                        educations: {insight.favorableNews.educations}<br />
                                        environments: {insight.favorableNews.environments}<br />
                                        industries: {insight.favorableNews.industries}<br />
                                        policies: {insight.favorableNews.policies}<br />
                                        transportations: {insight.favorableNews.transportations}<br />
                                        text: {insight.favorableNews.text}
                                    </p>
                                )}

                                <p className="text-blue-700"><strong>insightId:</strong> {insight.insightId}</p>
                                <p className="text-blue-700"><strong>mainImage:</strong> {insight.mainImage}</p>
                                <p><strong>memberId:</strong> {insight.memberId}</p>
                                <p className="text-blue-700"><strong>memberNickname:</strong> {insight.memberNickname}</p>
                                <p className="text-blue-700"><strong>recommended:</strong> {insight.recommended ? 'O' : 'X'}</p>
                                <p className="text-blue-700"><strong>recommendedCount:</strong> {insight.recommendedCount}</p>
                                <p><strong>score:</strong> {insight.score}</p>
                                <p><strong>snapshotId:</strong> {insight.snapshotId}</p>
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
