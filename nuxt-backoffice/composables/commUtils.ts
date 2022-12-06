/**
 * navigation click 변수
 */
export default function useDrawer(open?: boolean) {
    return useState("drawer", () =>
        open === null || typeof open === "undefined" ? false : true
    );
}
