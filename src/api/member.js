import { localAxios } from "../utils/http-common";

const local = localAxios();

function memberLogin(param,success,fail){
    // console.log("login 호출")
    local.post(`/member/login`,JSON.stringify(param)).then(success).catch(fail);
} 

function memberRegist(param,success,fail){
    local.post(`/member`,JSON.stringify(param)).then(success).catch(fail);
}

export{
    memberLogin,
    memberRegist,
}