import { AdminInfo } from "~~/utils/type";

export default function () {
    const adminInfo = useState("adminInfo");
    if (adminInfo === null) {
        console.log("zzz");
        // throw createError({ statusCode: 404, statusMessage: "Page Not Found" });
    }
}
