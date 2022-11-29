<script setup lang="ts">
import * as common from "~~/utils/type";

interface StudentInfo {
    studentId: number;
    studentName: string;
    age: number;
    subject?: string;
    graduated: boolean;
}

function getStudentMoreInfo(studentId: number): common.CommonCode {
    return {
        adminSeq: 10,
        compSeq: 10,
        adminId: "admin",
        adminStatCd: "정상",
        adminTypeCd: "",
        lastLoginDt: "",
        pwChgDt: "",
        regDt: "",
        modDt: "",
        regSeq: 1,
        modSeq: 1,
        adminGrpIds: "",
        adminMenuList: [
            {
                adminMenuSeq: 10,
                url: "/",
            },
        ],
    };
}
console.log(getStudentMoreInfo(10));

definePageMeta({
    layout: false,
});
</script>

<template>
    <v-progress-circular indeterminate :size="128"></v-progress-circular>
</template>
