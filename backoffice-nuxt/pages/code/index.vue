<script setup>
const { data: codeList } = await useFetch(
    "/sys/code/list",
    {
        server: false,
    }
    // {
    //     method: "post",
    //     headers: {
    //         "Content-Type": "application/json",
    //     },
    //     body: JSON.stringify({
    //         rowCount: 30,
    //         currentPage: 1,
    //     }),
    // }
);
console.log(codeList);
</script>

<template>
    <div id="codeList" style="border: 1px solid black">
        <table>
            <tr>
                <th>index</th>
                <th>codeGrp</th>
                <th>code</th>
                <th>codeNm</th>
                <th>codeVal1</th>
                <th>useYn</th>
                <th>dispNo</th>
                <th>regDt</th>
                <th>regSeq</th>
                <th>modDt</th>
                <th>modSeq</th>
            </tr>
            <tr v-for="(code, i) in codeList">
                <td v-text="i"></td>
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
        </table>
    </div>
</template>
