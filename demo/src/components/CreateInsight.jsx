import {useState} from "react";
import {createInsight} from "../api-insight.js";

export default function CreateInsight() {
    const [formData, setFormData] = useState({
        // memberId: "",
        score: 0,
        mainImage: null,
        title: "",
        address: {
            // buildingNumber: "",
            // detail: "",
            eupMyeonDong: "효제동",
            // roadName: "",
            siDo: "서울특별시",
            siGunGu: "종로구",
            // latitude: "",
            // longitude: ""
        },
        apartmentComplex: {
            name: "태릉해링턴플레이스"
        },
        visitAt: "2024-12-31",
        visitTimes: ["아침", "저녁"],
        visitMethods: ["자차", "도보"],
        summary: "지하철역과 도보 10분 거리로 접근성이 좋지만, 근처 공사로 소음 문제가 있을 수 있을 것 같아요. 하지만 단지 내 공원이 잘 조성되어 있어 가족 단위 거주자에게 적합할 것 같아요. 지하철역과 도보 10분 거리로 접근성이 좋지만, 근처 공사로 소음 문제가 있을 수 있을 것 같아요.",
        access: "허락시_가능",
        infra: {
            transportations: ["해당_없음"],
            schoolDistricts: ["초품아"],
            amenities: ["소형마트"],
            facilities: ["헬스장"],
            surroundings: ["산책로"],
            landmarks: ["전망대"],
            unpleasantFacilities: ["쓰레기_소각장"],
            text: "총평"
        },
        complexEnvironment: {
            buildingCondition: "잘_모르겠어요",
            security: "잘_모르겠어요",
            childrenFacility: "잘_모르겠어요",
            seniorFacility: "잘_모르겠어요",
            text: "총평"
        },
        complexFacility: {
            familyFacilities: ["해당_없음"],
            multipurposeFacilities: ["다목적실"],
            leisureFacilities: ["독서실"],
            surroundings: ["테이블_및_의자"],
            text: "총평"
        },
        favorableNews: {
            transportations: ["지하철_개통"],
            developments: ["재개발"],
            educations: ["초등학교_신설_예정"],
            environments: ["대형_공원"],
            cultures: ["잘_모르겠어요"],
            industries: ["잘_모르겠어요"],
            policies: ["잘_모르겠어요"],
            text: "총평"
        }
    });

    const handleChange = (e) => {
        const { name, value, type, files } = e.target;
        setFormData((prev) => {
            const keys = name.split(".");
            if (keys.length > 1) {
                return {
                    ...prev,
                    [keys[0]]: {
                        ...prev[keys[0]],
                        [keys[1]]: value
                    },
                };
            }
            return {
                ...prev,
                [name]: type === "file" ? files[0] : value,
            };
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        // console.log(formData);
        const payload = new FormData();
        payload.append(
            "createInsightCommand",
            new Blob([JSON.stringify({
                access: formData.access,
                address: formData.address,
                apartmentComplex: formData.apartmentComplex,
                complexEnvironment: formData.complexEnvironment,
                complexFacility: formData.complexFacility,
                favorableNews: formData.favorableNews,
                infra: formData.infra,
                score: formData.score,
                summary: formData.summary,
                title: formData.title,
                visitAt: formData.visitAt,
                visitMethods: formData.visitMethods,
                visitTimes: formData.visitTimes
            })], { type: "application/json" }));
        payload.append("mainImage", formData.mainImage);
        createInsight(payload);
    };

    return (
            <form onSubmit={handleSubmit}>
                <label>Main Image</label>
                <input type="file" name="mainImage" onChange={handleChange} required />

                <label>Title</label>
                <input name="title" value={formData.title} onChange={handleChange} required />

                {/*<label>Address</label>*/}
                {/*<input name="address.siDo" value={formData.address.siDo} onChange={handleChange} required />*/}
                {/*<input name="address.siGunGu" value={formData.address.siGunGu} onChange={handleChange} required />*/}
                {/*<input name="address.eupMyeonDong" value={formData.address.eupMyeonDong} onChange={handleChange} required />*/}
                {/*<input name="address.roadName" value={formData.address.roadName} onChange={handleChange} required />*/}
                {/*<input name="address.buildingNumber" value={formData.address.buildingNumber} onChange={handleChange} required />*/}
                {/*<input name="address.detail" value={formData.address.detail} onChange={handleChange} required />*/}
                {/*<input name="address.latitude" value={formData.address.latitude} onChange={handleChange} required />*/}
                {/*<input name="address.longitude" value={formData.address.longitude} onChange={handleChange} required />*/}

                {/*<label>Apartment Complex</label>*/}
                {/*<input name="apartmentComplex.name" value={formData.apartmentComplex.name} onChange={handleChange} required />*/}

                <button type="submit">작성 완료</button>
            </form>
    );
}
