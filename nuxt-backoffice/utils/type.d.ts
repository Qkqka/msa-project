// 전역 사용 필수
export global {}

/**
 * 공통 응답 모델
 */
interface Result {
    resultCode: number;
    resultData: object;
    resultMsg: string;
}

/**
 * 세션 관리자 정보 모델
 */
interface AdminInfo {
    adminSeq: number;
    compSeq: number;
    adminId: string;
    adminStatCd: string;
    adminTypeCd: string;
    lastLoginDt: string;
    pwChgDt: string;
    regDt: string;
    modDt: string;
    regSeq: number;
    modSeq: number;
    adminGrpIds: string;
    adminMenuList: object[];
}

/**
 * 공통코드 모델
 */
interface CommonCode {}
