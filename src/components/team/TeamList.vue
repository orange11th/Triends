<script setup>
import { ref, onMounted } from "vue";
import { myTeamList, registTeam } from "@/api/team";
import { useRouter } from "vue-router";
<<<<<<< HEAD
import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";

import TeamMemberItem from "./item/TeamMemberItem.vue";
import TeamInviteItem from "./item/TeamInviteItem.vue";

=======
>>>>>>> e539e633f92986ff346083544944bdd546e73dc7
const router = useRouter();
const memberStore = useMemberStore();
const { checkToken } = memberStore;
const { isValidToken, userInfo } = storeToRefs(memberStore);

const userId = ref();

const teamName = ref();

const teamList = ref([]);

function getTeamList() {
  myTeamList(
    userId.value,
    ({ data }) => {
      teamList.value = data;
    },
    console.error()
  );
}

function makeTeam() {
  if (teamName.value != null)
    registTeam(teamName.value, userId.value, console.log("makeTeam") , console.error());
}

onMounted(() => {
  checkToken(sessionStorage.getItem("accessToken"));  
  if (!isValidToken.value) {
    alert("토큰이 만료되었습니다.");
    router.replace({ name: "member-login" });
  } else {
    userId.value = userInfo.value.userId
    console.log(userInfo)
    getTeamList();
  }
});

<<<<<<< HEAD
=======
const modalStates = reactive(new Map());

// 초기화
teamList.value.forEach((team) => {
  modalStates.set(team.teamId, false);
});

// 모달 열기
const openModal = (teamId) => {
  teamInviteList(
    teamId,
    ({ data }) => {
      inviteList.value = data;
    },
    console.error()
  );
  modalStates.set(teamId, true);
};

// 모달 닫기
const closeModal = (teamId) => {
  modalStates.set(teamId, false);
};

const inviteList = ref([]);

function inviteTeam(teamId, userId) {
  teamInvite(
    teamId,
    userId,
    (response) => {
      console.log(response);
    },
    console.error()
  );
}
function movePlan(teamId) {
  router.push({ name: "plan-list", params: { teamId: teamId } });
}
>>>>>>> e539e633f92986ff346083544944bdd546e73dc7
</script>

<template>
  <div class="container">
    <h1 class="team-title">My team</h1>
    <input type="text" placeholder="팀 이름" v-model="teamName">
    <button @click.prevent="makeTeam">팀 만들기</button>
    <TeamInviteItem/>
    <ul class="team-list">
      <TeamMemberItem v-for="team in teamList" :key="team.teamId" :team="team" />
    </ul>
  </div>
</template>

<style scoped>
.team-title {
  color: black;
  margin: 50px 100px;
  font-family: Impact, Haettenschweiler, "Arial Narrow Bold", sans-serif;
}

.team-list {
  list-style: none;
  padding: 0;
}
</style>
