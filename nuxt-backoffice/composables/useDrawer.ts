/**
 * navigation click 변수
 */
export default function (open?: boolean) {
    return useState("drawer", () =>
        open === null || typeof open === "undefined" ? false : true
    );
}
