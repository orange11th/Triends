import { localAxios } from "../utils/http-common";

const local = localAxios();

function myTeamList(userId, success, fail) {
  local.get(`team/${userId}`).then(success).catch(fail);
}

export { myTeamList };
