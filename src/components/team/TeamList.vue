<script setup>
import { ref, onMounted } from "vue";
import { myInviteList, myTeamList, registTeam } from "@/api/team";
import { useRouter, useRoute } from "vue-router";
import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";

import TeamMemberItem from "./item/TeamMemberItem.vue";
import TeamInviteItem from "./item/TeamInviteItem.vue";

const router = useRouter();
const route = useRoute();

const memberStore = useMemberStore();
const { checkToken } = memberStore;
const { isValidToken, userInfo } = storeToRefs(memberStore);

const userId = ref();

const teamName = ref();

const teamList = ref([]);

const inviteList = ref([]);

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
    registTeam(teamName.value, userId.value, router.push(route.path), console.error());
}

onMounted(() => {
  checkToken(sessionStorage.getItem("accessToken"));
  if (!isValidToken.value) {
    alert("토큰이 만료되었습니다.");
    router.replace({ name: "member-login" });
  } else {
    userId.value = userInfo.value.userId;
    getTeamList();
    myInviteList(
      userId.value,
      ({ data }) => {
        inviteList.value = data;
      },
      console.error()
    );
  }
});
</script>

<template>
  <div class="container">
    <h1 class="team-title">My team</h1>
    <input type="text" placeholder="팀 이름" v-model="teamName" />
    <button @click.prevent="makeTeam">팀 만들기</button>
    <TeamInviteItem :userId="userId" :inviteList="inviteList" />
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
