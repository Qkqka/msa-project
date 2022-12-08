package com.msa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.msa.mapper.reader.CodeReaderMapper;
import com.msa.mapper.writer.CodeWriterMapper;
import com.msa.model.Code;
import com.msa.model.CodeList;
import com.msa.model.CodeSearch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 코드 service
 * @author fnfnksb@gmail.com
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CodeService {

    private final CodeReaderMapper codeReaderMapper;

    private final CodeWriterMapper codeWriterMapper;

    /**
     * 코드 목록 조회
     * @param param
     * @return
     */
    public List<CodeList> selectCodeList(CodeSearch param) {
        log.debug("CodeService.selectCodeList: {}", param);

        // 테스트용
        param.setPaging(false);

        // 목록 개수 조회
        int totalCount = this.codeReaderMapper.selectCodeListCount(param);

        List<CodeList> list = new ArrayList<>();
        if (totalCount > 0) {
            // 목록 조회
            list = this.codeReaderMapper.selectCodeList(param)
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
     * 코드 상세정보 조회
     * @param codeGrp
     * @param code
     * @return
     */
    public Code selectCode(String codeGrp, String code) {
        log.debug("CodeService.selectCode: {}, {}", codeGrp, code);

        if (StringUtils.isBlank(codeGrp) || StringUtils.isBlank(code)) {
            throw new IllegalArgumentException("필수값을 다시 확인해주세요.");
        }

        Code codeInfo = this.codeReaderMapper.selectCode(codeGrp, code);
        if (codeInfo == null) {
            throw new NullPointerException("코드가 존재하지 않습니다.");
        }

        return codeInfo;
    }

    /**
     * 코드 등록
     * @param code
     * @return
     */
    public int insertCode(Code codeInfo) {
        log.debug("CodeService.insertCode: {}", codeInfo);

        if (codeInfo == null) {
            throw new NullPointerException("등록할 정보를 확인해주세요.");
        }

        // 필수값 체크
        if (StringUtils.isBlank(codeInfo.getCodeGrp()) || StringUtils.isBlank(codeInfo.getCode()) || 
                StringUtils.isBlank(codeInfo.getCodeNm()) || StringUtils.isBlank(codeInfo.getUseYn())) {
            throw new NullPointerException("필수값을 확인해주세요.");
        }

        // 유효성 체크
        if (!ArrayUtils.contains(new String[] {"Y", "N"}, codeInfo.getUseYn())) {
            throw new IllegalArgumentException("필수값을 확인해주세요.");
        }

        // 코드 등록
        int result = this.codeWriterMapper.insertCode(codeInfo);
        if (result != 1) {
            throw new NullPointerException("뭐");
        }

        return result;
    }

    /**
     * 코드 수정
     * @param code
     * @return
     */
    public int updateCode(Code codeInfo) {
        log.debug("CodeService.updateCode: {}", codeInfo);

        if (codeInfo == null) {
            throw new NullPointerException("수정할 정보를 확인해주세요.");
        }

        // 필수값 체크
        if (StringUtils.isBlank(codeInfo.getCodeGrp()) || StringUtils.isBlank(codeInfo.getCode())) {
            throw new NullPointerException("필수값을 확인해주세요.");
        }

        // 유효성 체크
        if (!StringUtils.isBlank(codeInfo.getUseYn()) && !ArrayUtils.contains(new String[] {"Y", "N"}, codeInfo.getUseYn())) {
            throw new IllegalArgumentException("필수값을 확인해주세요.");
        }

        // 코드 수정
        int result = this.codeWriterMapper.updateCode(codeInfo);
        if (result != 1) {
            throw new NullPointerException("뭐");
        }

        return result;
    }
}
