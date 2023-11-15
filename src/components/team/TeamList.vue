<script setup>
import { ref, onMounted } from "vue";
import { myTeamList } from "@/api/team";
import { useRouter } from "vue-router";

const router = useRouter();

const userId = "ssafy";

const teamList = ref([]);

function getTeamList() {
  myTeamList(
    //여기 나중에 로그인으로 바꿔야댐
    userId,
    ({ data }) => {
      teamList.value = data;
    },
    console.error()
  );
}

onMounted(() => {
  getTeamList();
});
</script>

<template>
  <div>
    <h1>팀 목록</h1>
    <ul>
      <li v-for="team in teamList" :key="team.teamId">
        <h2>팀 아이디: {{ team.teamId }}, 팀 이름: {{ team.teamName }}</h2>
        <a href="">여행계획 바로가기</a>
        <ul>
          <li v-for="(teamMember, index) in team.teamList" :key="teamMember.userId">
            <h3>{{ index + 1 }}: {{ teamMember.userId }}</h3>
          </li>
        </ul>
      </li>
    </ul>
  </div>
</template>

<style scoped></style>
