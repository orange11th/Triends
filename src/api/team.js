import { localAxios } from "../utils/http-common";

const local = localAxios();

function myTeamList(userId, success, fail) {
  local.get(`team/${userId}`).then(success).catch(fail);
}

async function teamInviteList(teamId, success, fail) {
  await local.get(`/team/invite/${teamId}`).then(success).catch(fail);
}

async function teamInvite(teamId, userId, success, fail) {
  await local.get(`/team/registMember/${teamId}/${userId}`).then(success).catch(fail);
}

function planList(teamId, success, fail) {
  local.get(`plan/team/${teamId}`).then(success).catch(fail);
}

function placeList(planId, success, fail) {
  local.get(`plan/place/${planId}`).then(success).catch(fail);
}

function saveNewPlan(teamId, plans, success, fail) {
  local.post(`plan/save/${teamId}`, plans).then(success).catch(fail);
}
export { myTeamList, teamInviteList, teamInvite, planList, placeList, saveNewPlan };
