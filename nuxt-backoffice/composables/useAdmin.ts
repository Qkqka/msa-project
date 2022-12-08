import { AdminInfo } from "./type";

export default function useAdmin() {
    const adminInfo = useState("adminInfo").value as AdminInfo;

    if (adminInfo === null) {
        console.log("zzz");
        // throw createError({ statusCode: 404, statusMessage: "Page Not Found" });
        throw createError({
            statusCode: -1,
            statusMessage: "로그인 해주세요.",
        });
    }

    return adminInfo;
}
