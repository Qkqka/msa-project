<template>
    <ag-grid-vue
        style="width: 100%; height: 500px"
        class="ag-theme-alpine"
        :columnDefs="columnDefs"
        :rowData="rowData"
        @grid-ready="onGridReady"
        :debounceVerticalScrollbar="true"
        :defaultColDef="defaultColDef"
    >
    </ag-grid-vue>
</template>

<script>
export default {
    name: "App",
    setup() {
        return {
            columnDefs: [
                { field: "codeGrp" },
                { field: "code" },
                { field: "codeNm" },
                { field: "codeVal1" },
                { field: "useYn" },
                { field: "dispNo" },
                { field: "regDt" },
                { field: "regSeq" },
                { field: "modDt" },
                { field: "modSeq" },
            ],
            rowData: null,
            defaultColDef: {
                resizable: true,
            },
            gridApi: null,
            columnApi: null,
            onGridReady: function (params) {
                // console.log(params);
                this.gridApi = params.api;
                this.gridColumnApi = params.columnApi;

                const updateData = (data) => params.api.setRowData(data);

                fetch(
                    // "https://www.ag-grid.com/example-assets/olympic-winners.json"
                    "/sys/code/list"
                    // { method: "get" }
                )
                    .then((resp) => resp.json())
                    .then((data) => {
                        updateData(data.resultData.data);
                    })
                    .finally((res) => console.log(res));
            },
        };
    },
};
</script>
