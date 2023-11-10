import { localAxios } from "@/utils/http-commons";

const local = localAxios();

function listBoard(param, success, fail) {
    local.get(`/board`, { params: param }).then(success).catch(fail);
}

export {
    listBoard,
}