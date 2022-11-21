package com.msa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.NullArgumentException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.msa.mapper.reader.CommonCodeReaderMapper;
import com.msa.mapper.writer.CommonCodeWriterMapper;
import com.msa.model.Code;
import com.msa.model.CommonCodeList;
import com.msa.model.CommonCodeSearch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 공통코드 service
 * @author fnfnksb@gmail.com
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CommonCodeService {

    private final CommonCodeReaderMapper commonCodeReaderMapper;

    private final CommonCodeWriterMapper commonCodeWriterMapper;

    /**
     * 공통코드 목록 조회
     * @param param
     * @return
     */
    public List<CommonCodeList> selectCodeList(CommonCodeSearch param) {
        // 테스트용
        param.setPaging(false);

        // 목록 개수 조회
        int totalCount = this.commonCodeReaderMapper.selectCodeListCount(param);

        List<CommonCodeList> list = new ArrayList<>();
        if (totalCount > 0) {
            // 목록 조회
            list = this.commonCodeReaderMapper.selectCodeList(param)
                    .stream()
                    .map(code -> {
                        code.setTotalCount(totalCount);
                        return code;
                    })
                    .collect(Collectors.toList());
        }

        return list;
    }

    /**
     * 공통코드 상세정보 조회
     * @param codeGrp
     * @param code
     * @return
     */
    public Code selectCode(String codeGrp, String code) {
        if (StringUtils.isBlank(codeGrp) || StringUtils.isBlank(code)) {
            throw new IllegalArgumentException("필수값을 다시 확인해주세요.");
        }

        Code commonCode = this.commonCodeReaderMapper.selectCode(codeGrp, code);
        if (commonCode == null) {
            throw new NullPointerException("공통코드가 존재하지 않습니다.");
        }

        return commonCode;
    }

    /**
     * 공통코드 등록
     * @param commonCode
     * @return
     */
    public int insertCommonCode(Code commonCode) {
        if (commonCode == null) {
            throw new NullPointerException("등록할 정보를 확인해주세요.");
        }

        // 필수값 체크
        if (StringUtils.isBlank(commonCode.getCodeGrp()) || StringUtils.isBlank(commonCode.getCode()) || 
                StringUtils.isBlank(commonCode.getCodeNm()) || StringUtils.isBlank(commonCode.getUseYn()) ||
                StringUtils.isBlank(commonCode.getRegSeq()) || StringUtils.isBlank(commonCode.getModSeq())) {
            throw new NullArgumentException("필수값을 확인해주세요.");
        }

        // 유효성 체크
        if (!ArrayUtils.contains(new String[] {"Y", "N"}, commonCode.getUseYn())) {
            throw new IllegalArgumentException("필수값을 확인해주세요.");
        }

        // 공통코드 등록
        int result = this.commonCodeWriterMapper.insertCommonCode(commonCode);
        if (result != 1) {
            throw new NullPointerException("뭐");
        }

        return result;
    }

    /**
     * 공통코드 수정
     * @param commonCode
     * @return
     */
    public int updateCommonCode(Code commonCode) {
        if (commonCode == null) {
            throw new NullPointerException("수정할 정보를 확인해주세요.");
        }

        // 필수값 체크
        if (StringUtils.isBlank(commonCode.getCodeGrp()) || StringUtils.isBlank(commonCode.getCode()) || StringUtils.isBlank(commonCode.getModSeq())) {
            throw new NullArgumentException("필수값을 확인해주세요.");
        }

        // 유효성 체크
        if (!StringUtils.isBlank(commonCode.getUseYn()) && !ArrayUtils.contains(new String[] {"Y", "N"}, commonCode.getUseYn())) {
            throw new IllegalArgumentException("필수값을 확인해주세요.");
        }

        // 공통코드 수정
        int result = this.commonCodeWriterMapper.updateCommonCode(commonCode);
        if (result != 1) {
            throw new NullPointerException("뭐");
        }

        return result;
    }
}
