<template>
    <v-btn><nuxt-link to="/code/create">등록</nuxt-link></v-btn>

    <v-table fixed-header height="500px" density="compact">
        <thead>
            <tr>
                <th class="text-left">index</th>
                <th class="text-left">codeGrp</th>
                <th class="text-left">code</th>
                <th class="text-left">codeNm</th>
                <th class="text-left">codeVal1</th>
                <th class="text-left">useYn</th>
                <th class="text-left">dispNo</th>
                <th class="text-left">regDt</th>
                <th class="text-left">regSeq</th>
                <th class="text-left">modDt</th>
                <th class="text-left">modSeq</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="(code, i) in codeList">
                <td v-text="i + 1"></td>
                <td v-text="code.codeGrp"></td>
                <td v-text="code.code"></td>
                <td v-text="code.codeNm"></td>
                <td v-text="code.codeVal1"></td>
                <td v-text="code.useYn"></td>
                <td v-text="code.dispNo"></td>
                <td v-text="code.regDt"></td>
                <td v-text="code.regSeq"></td>
                <td v-text="code.modDt"></td>
                <td v-text="code.modSeq"></td>
            </tr>
        </tbody>
    </v-table>
    <div class="text-center">
        <v-pagination
            v-model="pageParam.page"
            :total-visible="5"
            :length="pageParam.totalPage"
            >1</v-pagination
        >
        <v-btn @click="getCodeList()"></v-btn>
    </div>
</template>

<script setup>
const codeList = ref([]);
const pageParam = ref({
    page: 1,
    startPage: 1,
    totalPage: 0,
    rowCount: 30,
});

function getCodeList(page) {
    console.log("뭐뭐뭐ㅝ" + page);
    const d = useFetch("sys/code/list", {
        server: false,
        initialCache: false,
    }).then((res) => {
        this.codeList = res.data.value.resultData.data;

        if (this.codeList != null && this.codeList.length != 0) {
            this.pageParam.totalPage =
                this.codeList[0].totalCount / this.pageParam.rowCount;
        }
    });
}
</script>
